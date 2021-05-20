package no.MCH.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import no.MCH.database.DatabaseConnection;
import no.MCH.exception.CustomerNotFoundException;
import no.MCH.model.CustomerModel;
import no.MCH.model.EmployeeModel;

public class CustomerController {
	private static Logger log = Logger.getLogger(CustomerController.class);
	
	public List<CustomerModel> getAllCustomers(CustomerModel filter) throws SQLException, CustomerNotFoundException {
		List<CustomerModel> customerList = new ArrayList<>();
		StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM customers");
		
		if (filter != null) { 
			sqlBuilder.append(" WHERE");
			if (filter.getAddressLine1() != null) sqlBuilder.append(" addressLine1 LIKE '" + filter.getAddressLine1() + "' AND");
			if (filter.getAddressLine2() != null) sqlBuilder.append(" addressLine2 LIKE '" + filter.getAddressLine2() + "' AND");
			if (filter.getCity() != null) sqlBuilder.append(" city LIKE '" + filter.getCity() + "' AND");
			if (filter.getContactFirstName() != null) sqlBuilder.append(" contactFirstName LIKE '" + filter.getContactFirstName() + "' AND");
			if (filter.getContactLastName() != null) sqlBuilder.append(" contactLastName LIKE '" + filter.getContactLastName() + "' AND"); 
			if (filter.getCountry() != null) sqlBuilder.append(" country LIKE '" + filter.getCountry() + "' AND");
			if (filter.getCreditLimit() != null) sqlBuilder.append(" creditLimit = " + filter.getCreditLimit() + " AND");
			if (filter.getCustomerName() != null) sqlBuilder.append(" customerName LIKE '" + filter.getCustomerName() + "' AND");
			if (filter.getCustomerNumber() != null) sqlBuilder.append(" customerNumber LIKE '" + filter.getCustomerNumber() + "' AND");
			if (filter.getPhone() != null) sqlBuilder.append(" phone LIKE '" + filter.getPhone() + "' AND");
			if (filter.getPostalCode() != null) sqlBuilder.append(" postalCode LIKE '" + filter.getPostalCode() + "' AND");
			if (filter.getSalesRepEmployee() != null) sqlBuilder.append(" salesRepEmployeeNumber = " + filter.getSalesRepEmployee().getEmployeeNumber() + " AND");
			if (filter.getState() != null) sqlBuilder.append(" state LIKE '" + filter.getState() + "' AND");
			sqlBuilder.append(" 1=1");
		}
		sqlBuilder.append(";");
		
		Connection con = null;
		PreparedStatement selectStmt = null;
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getConnection();
			selectStmt = con.prepareStatement(sqlBuilder.toString());
			rs = selectStmt.executeQuery();
			while(rs.next()) {
				int ps = 1;
				customerList.add(
						new CustomerModel(
								rs.getInt(ps++),
								rs.getString(ps++),
								rs.getString(ps++),
								rs.getString(ps++),
								rs.getString(ps++),
								rs.getString(ps++),
								rs.getString(ps++),
								rs.getString(ps++),
								rs.getString(ps++),
								rs.getString(ps++),
								rs.getString(ps++),
								new EmployeeModel(rs.getInt(ps++)),
								rs.getDouble(ps)
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			selectStmt.close();
			rs.close();
		}
		if (customerList.isEmpty() || customerList == null) {
			throw new CustomerNotFoundException("Customer was not found.");
		}
		return customerList;
	}
	
	public CustomerModel getCustomer(Integer customerNumber) throws CustomerNotFoundException, SQLException {
		if (customerNumber == null) {
			throw new CustomerNotFoundException("No customer number as input.");
		}
		
		CustomerModel customerModel = null;
		
		String sql = "SELECT * FROM customers WHERE customerNumer = ?";
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerNumber);
			rs = pstmt.executeQuery();
			
			int ps = 1;
			customerModel = new CustomerModel(
					rs.getInt(ps++),
					rs.getString(ps++),
					rs.getString(ps++),
					rs.getString(ps++),
					rs.getString(ps++),
					rs.getString(ps++),
					rs.getString(ps++),
					rs.getString(ps++),
					rs.getString(ps++),
					rs.getString(ps++),
					rs.getString(ps++),
					new EmployeeModel(rs.getInt(ps++)),
					rs.getDouble(ps)
			);
			
		} catch(SQLException e) {
			log.error(e.getMessage(), e);
		} finally {
			con.close();
			pstmt.close();
			rs.close();
		}
		if (customerModel == null) {
			throw new CustomerNotFoundException("No customers with current customerNumber found.");
		}
		return customerModel;
	}
	
	public void addCustomer(CustomerModel customer) throws SQLException {
		if (customer != null) {
			String sql = "INSERT INTO customers VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
			Connection con = null;
			PreparedStatement pstmt = null;
			int ps = 1;
			
			try {
				con = DatabaseConnection.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(ps++, customer.getCustomerNumber());
				pstmt.setString(ps++, customer.getCustomerName());
				pstmt.setString(ps++, customer.getContactLastName());
				pstmt.setString(ps++, customer.getContactFirstName());
				pstmt.setString(ps++, customer.getPhone());
				pstmt.setString(ps++, customer.getAddressLine1());
				pstmt.setString(ps++, customer.getAddressLine2());
				pstmt.setString(ps++, customer.getCity());
				pstmt.setString(ps++, customer.getState());
				pstmt.setString(ps++, customer.getPostalCode());
				pstmt.setString(ps++, customer.getCountry());
				pstmt.setInt(ps++, customer.getSalesRepEmployee().getEmployeeNumber());
				pstmt.setDouble(ps, customer.getCreditLimit());
				
				pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				con.close();
				pstmt.close();
			}
		}
		
	}
	
	public void addCustomer(List<CustomerModel> customerModelList) throws SQLException {
		String sql = "INSERT INTO customers VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			for (CustomerModel customer : customerModelList) {
				int ps = 1;
				
				pstmt.setInt(ps++, customer.getCustomerNumber());
				pstmt.setString(ps++, customer.getCustomerName());
				pstmt.setString(ps++, customer.getContactLastName());
				pstmt.setString(ps++, customer.getContactFirstName());
				pstmt.setString(ps++, customer.getPhone());
				pstmt.setString(ps++, customer.getAddressLine1());
				pstmt.setString(ps++, customer.getAddressLine2());
				pstmt.setString(ps++, customer.getCity());
				pstmt.setString(ps++, customer.getState());
				pstmt.setString(ps++, customer.getPostalCode());
				pstmt.setString(ps++, customer.getCountry());
				pstmt.setInt(ps++, customer.getSalesRepEmployee().getEmployeeNumber());
				pstmt.setDouble(ps, customer.getCreditLimit());
				
				pstmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
		}
	}
	
	public void updateCustomer(CustomerModel customer, int key) throws SQLException {
		String sql = "UPDATE customers SET "
				+ "customerNumber = ?, "
				+ "customerName = ?, "
				+ "contactLastName = ?, "
				+ "contactFirstName = ?, "
				+ "phone = ?, "
				+ "addressLine1 = ?, "
				+ "addressLine2 = ?, "
				+ "city = ?, "
				+ "state = ?, "
				+ "postalCode = ?, "
				+ "country = ?, "
				+ "salesRepEmployeeNumber = ?, "
				+ "creditLimit = ? "
				+ "WHERE customerNumber = ?;";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			int ps = 1;
			
			pstmt.setInt(ps++, customer.getCustomerNumber());
			pstmt.setString(ps++, customer.getCustomerName());
			pstmt.setString(ps++, customer.getContactLastName());
			pstmt.setString(ps++, customer.getContactFirstName());
			pstmt.setString(ps++, customer.getPhone());
			pstmt.setString(ps++, customer.getAddressLine1());
			pstmt.setString(ps++, customer.getAddressLine2());
			pstmt.setString(ps++, customer.getCity());
			pstmt.setString(ps++, customer.getState());
			pstmt.setString(ps++, customer.getPostalCode());
			pstmt.setString(ps++, customer.getCountry());
			pstmt.setInt(ps++, customer.getSalesRepEmployee().getEmployeeNumber());
			pstmt.setDouble(ps++, customer.getCreditLimit());
			pstmt.setInt(ps, key);
			
			pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
		}
		
		
	}

}
