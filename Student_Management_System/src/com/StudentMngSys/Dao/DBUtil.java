package com.StudentMngSys.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	public static Connection provideConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/Student_registration";
		
		try {
			conn = DriverManager.getConnection(url,"root","Akash@890");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return conn;
	}

}
