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
import no.MCH.exception.EmployeeNotFoundException;
import no.MCH.model.EmployeeModel;
import no.MCH.model.OfficeModel;

public class EmployeeController {
	private static Logger log = Logger.getLogger(EmployeeController.class);
	
	public List<EmployeeModel> getAllEmployees(EmployeeModel filter) throws SQLException, EmployeeNotFoundException {
		List<EmployeeModel> employeeList = new ArrayList<>();
		StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM employees");
		if (filter != null) {
			sqlBuilder.append(" WHERE");
			if (filter.getEmployeeNumber() != null) sqlBuilder.append(" employeeNumber = " + filter.getEmployeeNumber() + " AND");
			if (filter.getLastName() != null) sqlBuilder.append(" lastName LIKE '" + filter.getLastName() + "' AND");
			if (filter.getFirstName() != null) sqlBuilder.append(" firstName LIKE '" + filter.getFirstName() + "' AND");
			if (filter.getExtension() != null) sqlBuilder.append(" extension LIKE '" + filter.getExtension() + "' AND");
			if (filter.getEmail() != null) sqlBuilder.append(" email LIKE '" + filter.getEmail() + "' AND");
			if (filter.getOffice() != null) sqlBuilder.append(" officeCode LIKE '" + filter.getOffice().getOfficeCode() + "' AND");
			if (filter.getReportsTo() != null) sqlBuilder.append("reportsTo = " + filter.getReportsTo().getEmployeeNumber() + " AND");
			if (filter.getJobTitle() != null) sqlBuilder.append(" jobTitle LIKE '" + filter.getJobTitle() + "' AND");
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
				employeeList.add(
						new EmployeeModel(
								rs.getInt(ps++),
								rs.getString(ps++),
								rs.getString(ps++),
								rs.getString(ps++),
								rs.getString(ps++),
								new OfficeModel(rs.getString(ps++)),
								new EmployeeModel(rs.getInt(ps++)),
								rs.getString(ps)
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			selectStmt.close();
			rs.close();
		}
		if (employeeList.isEmpty() || employeeList == null) {
			throw new EmployeeNotFoundException("Customer was not found.");
		}
		return employeeList;
	}
	
	public EmployeeModel getEmployee(Integer employeeNumber) throws SQLException, EmployeeNotFoundException {
		if (employeeNumber == null) {
			throw new EmployeeNotFoundException("No employeeNumber as input.");
		}
		
		EmployeeModel employeeModel = null;
		
		String sql = "SELECT * FROM employees WHERE employeeNumber = ?";
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeNumber);
			rs = pstmt.executeQuery();
			
			int ps = 1;
			employeeModel = new EmployeeModel(
					rs.getInt(ps++),
					rs.getString(ps++),
					rs.getString(ps++),
					rs.getString(ps++),
					rs.getString(ps++),
					new OfficeModel(rs.getString(ps++)),
					new EmployeeModel(rs.getInt(ps++)),
					rs.getString(ps)
			);
			
		} catch(SQLException e) {
			log.error(e.getMessage(), e);
		} finally {
			con.close();
			pstmt.close();
			rs.close();
		}
		if (employeeModel == null) {
			throw new EmployeeNotFoundException("No employee with current employeeNumber found.");
		}
		return employeeModel;
	}
	
	public void addEmployee(EmployeeModel employee) throws SQLException {
		String sql = "INSERT INTO employees VALUES (?,?,?,?,?,?,?,?);";
		Connection con = null;
		PreparedStatement pstmt = null;
		int ps = 1;
		
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(ps++, employee.getEmployeeNumber());
			pstmt.setString(ps++, employee.getLastName());
			pstmt.setString(ps++, employee.getFirstName());
			pstmt.setString(ps++, employee.getExtension());
			pstmt.setString(ps++, employee.getEmail());
			pstmt.setString(ps++, employee.getOffice().getOfficeCode());
			pstmt.setInt(ps++, employee.getReportsTo().getEmployeeNumber());
			pstmt.setString(ps, employee.getJobTitle());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
		}
	}
	
	public void addEmployee(List<EmployeeModel> employeeList) throws SQLException {
		String sql = "INSERT INTO employees VALUES (?,?,?,?,?,?,?,?);";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			for (EmployeeModel employee : employeeList) {
				int ps = 1;
				pstmt.setInt(ps++, employee.getEmployeeNumber());
				pstmt.setString(ps++, employee.getLastName());
				pstmt.setString(ps++, employee.getFirstName());
				pstmt.setString(ps++, employee.getExtension());
				pstmt.setString(ps++, employee.getEmail());
				pstmt.setString(ps++, employee.getOffice().getOfficeCode());
				pstmt.setInt(ps++, employee.getReportsTo().getEmployeeNumber());
				pstmt.setString(ps, employee.getJobTitle());
				pstmt.execute();
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} finally {
			con.close();
			pstmt.close();
		}
	}
	
	public void deleteEmployee(Integer key) throws CustomerNotFoundException, SQLException {
		if (key == null) {
			throw new CustomerNotFoundException("No key or customer found");
		}
		String sql = "DELETE FROM employees WHERE employeeNumber = ?;";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, key);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
	}
	
	public void updateEmployee(EmployeeModel employee, Integer key) throws EmployeeNotFoundException, SQLException {
		if (employee == null) {
			throw new EmployeeNotFoundException("No employee found on key");
		}
		String sql = "UPDATE employees SET "
				+ "employeeNumber = ?, "
				+ "lastName = ?, "
				+ "firstName = ?, "
				+ "extension = ?, "
				+ "email = ?, "
				+ "officeCode = ?, "
				+ "reportsTo = ?, "
				+ "jobTitle = ? "
				+ "WHERE employeeNumber = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			int ps = 1;
			pstmt.setInt(ps++, employee.getEmployeeNumber());
			pstmt.setString(ps++, employee.getLastName());
			pstmt.setString(ps++, employee.getFirstName());
			pstmt.setString(ps++, employee.getExtension());
			pstmt.setString(ps++, employee.getEmail());
			pstmt.setString(ps++, employee.getOffice().getOfficeCode());
			pstmt.setInt(ps++, employee.getReportsTo().getEmployeeNumber());
			pstmt.setString(ps++, employee.getJobTitle());
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
