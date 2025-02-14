package com.megacitycab.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/mega-city-cab_db";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";

	private static DBConnection instance;
	private Connection connection;

	private DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (ClassNotFoundException e) {
	        throw new RuntimeException("MySQL JDBC Driver not found!", e);
	    } catch (SQLException e) {
	        throw new RuntimeException("Failed to connect to the database!", e);
	    }
	}

	public static DBConnection getInstance() {
		if (instance == null) {
			synchronized (DBConnection.class) {
				if (instance == null) {
					instance = new DBConnection();
				}
			}
		}
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}
