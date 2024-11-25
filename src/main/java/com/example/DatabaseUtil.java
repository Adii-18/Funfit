package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/ZumbaDB?useSSL=false&serverTimezone=UTC";
	private static final String USER = "root"; // Replace with your DB username
	private static final String PASSWORD = "Aditi@2002"; // Replace with your DB password

	// Static block to load the MySQL JDBC driver
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// Consider logging this exception to a file for troubleshooting
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}