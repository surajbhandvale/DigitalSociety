package com.dao;

import java.sql.*;

public class DbConnection {

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/project";
	private static String user_name = "root";
	private static String pass = "root";
	static Connection con;

	public static Connection getConnection() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user_name, pass);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	}
}
