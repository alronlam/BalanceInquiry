package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {
	private static String url = "jdbc:mysql://localhost:3306/test_server";
	private static String username = "root";
	private static String password = "1234";
	
	private static Connection conn; 

	public static void initializeConnection(){
		try{
			if(conn != null)
				conn = DriverManager.getConnection(url, username, password);
		    System.out.println("Database connected!");
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public static Connection getConnection(){
		return conn;
	}

	public static void closeConnection() throws SQLException{
		if(conn != null)
			conn.close();
	}
}
