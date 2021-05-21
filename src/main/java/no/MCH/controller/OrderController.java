package no.MCH.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import no.MCH.database.DatabaseConnection;
import no.MCH.exception.OrderNotFoundException;
import no.MCH.model.CustomerModel;
import no.MCH.model.OrderModel;

public class OrderController {
	private static Logger log = Logger.getLogger(OrderController.class);

	public void addOrder(OrderModel order) throws SQLException {
		String sql = "INSERT INTO orders VALUES (?,?,?,?,?,?,?);";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			int ps = 1;
			pstmt.setInt(ps++, order.getOrderNumber());
			pstmt.setDate(ps++, order.getOrderDate());
			pstmt.setDate(ps++, order.getRequiredDate());
			pstmt.setDate(ps++, order.getShippedDate());
			pstmt.setString(ps++, order.getStatus());
			pstmt.setString(ps++, order.getComments());
			pstmt.setInt(ps, order.getCustomer().getCustomerNumber());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
		}
	}
	
	public void addOrder(List<OrderModel> orderList) throws SQLException {
		String sql = "INSERT INTO orders VALUES (?,?,?,?,?,?,?);";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			for (OrderModel order : orderList) {
				int ps = 1;
				pstmt.setInt(ps++, order.getOrderNumber());
				pstmt.setDate(ps++, order.getOrderDate());
				pstmt.setDate(ps++, order.getRequiredDate());
				pstmt.setDate(ps++, order.getShippedDate());
				pstmt.setString(ps++, order.getStatus());
				pstmt.setString(ps++, order.getComments());
				pstmt.setInt(ps, order.getCustomer().getCustomerNumber());
				pstmt.execute();
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} finally {
			con.close();
			pstmt.close();
		}
	}
	
	public List<OrderModel> getAllOrders(OrderModel filter, Date from, Date to) throws SQLException {
		List<OrderModel> orderList = new ArrayList<>();
		StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM orders");
		if (filter != null) {
			sqlBuilder.append(" WHERE");
			if (filter.getOrderNumber() != null) sqlBuilder.append(" orderNumber = " + filter.getOrderNumber() + " AND");
			if (filter.getOrderDate() != null) sqlBuilder.append(" orderDate = '" + filter.getOrderDate() + "' AND");
			if (filter.getRequiredDate() != null) sqlBuilder.append(" requiredDate = '" + filter.getRequiredDate() + "' AND");
			if (filter.getShippedDate() != null) sqlBuilder.append(" shippedDate = '" + filter.getShippedDate() + "' AND");
			if (filter.getStatus() != null) sqlBuilder.append(" status LIKE '" + filter.getStatus() + "' AND");
			if (filter.getComments() != null) sqlBuilder.append(" comments LIKE '" + filter.getComments() + "' AND");
			if (filter.getCustomer() != null) sqlBuilder.append("customerNumber = " + filter.getCustomer().getCustomerNumber() + " AND");
			sqlBuilder.append(" 1=1");
		}
		if (from != null && to != null) {
			sqlBuilder.append(" WHERE orderDate BETWEEN '" + from + "' AND '" + to + "';");
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
				orderList.add(
						new OrderModel(
								rs.getInt(ps++),
								rs.getDate(ps++),
								rs.getDate(ps++),
								rs.getDate(ps++),
								rs.getString(ps++),
								rs.getString(ps++),
								new CustomerModel(rs.getInt(ps))
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			selectStmt.close();
			rs.close();
		}
		return orderList;
	}
	
	public void updateOrder(OrderModel order, Integer key) throws OrderNotFoundException, SQLException {
		if (key == null) {
			throw new OrderNotFoundException("No key or order found");
		}
		String sql = "UPDATE orders SET "
				+ "orderNumber = ?, "
				+ "orderDate = ?, "
				+ "requiredDate = ?, "
				+ "shippedDate = ?, "
				+ "status = ?, "
				+ "comments = ?, "
				+ "customerNumber = ? "
				+ "WHERE orderNumber = ?;";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			int ps = 1;
			pstmt.setInt(ps++, order.getOrderNumber());
			pstmt.setDate(ps++, order.getOrderDate());
			pstmt.setDate(ps++, order.getRequiredDate());
			pstmt.setDate(ps++, order.getShippedDate());
			pstmt.setString(ps++, order.getStatus());
			pstmt.setString(ps++, order.getComments());
			pstmt.setInt(ps++, order.getCustomer().getCustomerNumber());
			pstmt.setInt(ps, key);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
		}
	}
	
	public void deleteOrder(Integer key) throws OrderNotFoundException, SQLException {
		if (key == null) {
			throw new OrderNotFoundException("No key or order found");
		}
		String sql = "DELETE FROM orders WHERE orderNumber = ?;";
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

}
