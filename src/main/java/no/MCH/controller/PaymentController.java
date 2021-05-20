package no.MCH.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import no.MCH.database.DatabaseConnection;
import no.MCH.model.PaymentModel;

public class PaymentController {
	private static Logger log = Logger.getLogger(PaymentController.class);

	public void addPayment(PaymentModel payment) throws SQLException {
		String sql = "INSERT INTO payments VALUES (?,?,?,?);";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			int ps = 1;
			pstmt.setInt(ps++, payment.getCustomer().getCustomerNumber());
			pstmt.setString(ps++, payment.getCheckNumber());
			pstmt.setDate(ps++, payment.getPaymentDate());
			pstmt.setDouble(ps, payment.getAmount());
			pstmt.execute();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} finally {
			con.close();
			pstmt.close();
		}
	}
	
	public void addPayment(List<PaymentModel> paymentList) throws SQLException {
		String sql = "INSERT INTO payments VALUES (?,?,?,?);";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			for (PaymentModel payment : paymentList) {
				int ps = 1;
				pstmt.setInt(ps++, payment.getCustomer().getCustomerNumber());
				pstmt.setString(ps++, payment.getCheckNumber());
				pstmt.setDate(ps++, payment.getPaymentDate());
				pstmt.setDouble(ps, payment.getAmount());
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
