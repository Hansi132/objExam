package no.MCH.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DatabaseConnection {
	private static Logger log = Logger.getLogger(DatabaseConnection.class);
	
	private static String url;
	private static String driverName;
	private static String username;
	private static String password;
	private static Connection con;
	
	public static Connection getConnection() {
		try {
			Class.forName(driverName);
			try {
				con = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
			}
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
		}
		return con;
	}
}
