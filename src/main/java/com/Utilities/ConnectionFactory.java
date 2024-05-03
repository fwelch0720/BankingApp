package com.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection createConnection () {
		Connection con = null;
	    
		final String DB_NAME = "banking_system";
		final String DB_URL ="jdbc:mysql://localhost:3306/"+DB_NAME;
		final String DB_USERNAME = "root";
		final String DB_PASS = "Lilsizzle3!";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
		} 
		
		catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}

}
