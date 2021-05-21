package no.MCH.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import no.MCH.database.DatabaseConnection;
import no.MCH.model.ProductModel;

public class ProductController {
	private static Logger log = Logger.getLogger(ProductController.class);

	public void addProduct(ProductModel product) throws SQLException {
		String sql = "INSERT INTO products VALUES (?,?,?,?,?,?,?,?,?);";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			int ps = 1;
			pstmt.setString(ps++, product.getProductCode());
			pstmt.setString(ps++, product.getProductName());
			pstmt.setString(ps++, product.getProductLine().getProductLine());
			pstmt.setString(ps++, product.getProductScale());
			pstmt.setString(ps++, product.getProductVendor());
			pstmt.setString(ps++, product.getProductDescription());
			pstmt.setShort(ps++, product.getQuantityInStock());
			pstmt.setDouble(ps++, product.getBuyPrice());
			pstmt.setDouble(ps, product.getMSRP());
			pstmt.execute();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} finally {
			con.close();
			pstmt.close();
		}
	}
	
	public void addProduct(List<ProductModel> productList) throws SQLException {
		String sql = "INSERT INTO products VALUES (?,?,?,?,?,?,?,?,?);";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			for (ProductModel product : productList) {
				int ps = 1;
				pstmt.setString(ps++, product.getProductCode());
				pstmt.setString(ps++, product.getProductName());
				pstmt.setString(ps++, product.getProductLine().getProductLine());
				pstmt.setString(ps++, product.getProductScale());
				pstmt.setString(ps++, product.getProductVendor());
				pstmt.setString(ps++, product.getProductDescription());
				pstmt.setShort(ps++, product.getQuantityInStock());
				pstmt.setDouble(ps++, product.getBuyPrice());
				pstmt.setDouble(ps, product.getMSRP());
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

