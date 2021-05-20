package no.MCH.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import no.MCH.database.DatabaseConnection;
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
			log.error(e.getMessage(), e);
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

}
