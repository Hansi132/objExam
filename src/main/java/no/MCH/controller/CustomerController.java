package no.MCH.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import no.MCH.database.DatabaseConnection;
import no.MCH.model.CustomerModel;
import no.MCH.model.EmployeeModel;

public class CustomerController {
	private static final Logger log = Logger.getLogger(CustomerController.class);
	
	public List<CustomerModel> getAllCustomers(CustomerModel filter) throws SQLException {
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
			if (filter.getPhone() != null) sqlBuilder.append("phone LIKE '" + filter.getPhone() + "' AND");
			if (filter.getPostalCode() != null) sqlBuilder.append("postalCode LIKE '" + filter.getPostalCode() + "' AND");
			if (filter.getSalesRepEmployee() != null) sqlBuilder.append("salesRepEmployeeNumber = " + filter.getSalesRepEmployee().getEmployeeNumber() + " AND");
			if (filter.getState() != null) sqlBuilder.append("state LIKE '" + filter.getState() + "' AND");
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
								rs.getDouble(ps++)
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			selectStmt.close();
			rs.close();
		}
		return customerList;
	}
	
	public CustomerModel getCustomer(Integer customerNumber) {
		return null;
	}

}
