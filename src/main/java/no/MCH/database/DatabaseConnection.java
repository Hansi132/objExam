package no.MCH.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import no.MCH.Run;

public class DatabaseConnection {
	private static Logger log = Logger.getLogger(DatabaseConnection.class);
	
	private static String databaseUrl = Run.getInstance().getConfig().databaseUrl;
	private static String driverName = Run.getInstance().getConfig().driverName;
	private static String databaseUsername = Run.getInstance().getConfig().databaseUsername;
	private static String databasePassword = Run.getInstance().getConfig().databasePassword;
	private static Connection con;
	
	public static Connection getConnection() {
		try {
			Class.forName(driverName);
			try {
				con = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
			}
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
		}
		return con;
	}
}
