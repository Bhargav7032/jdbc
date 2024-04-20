package com.ciq.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTableDemo {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");

			statement = connection.createStatement();

			resultSet = statement.executeQuery("SELECT * FROM EMPLOYEE_TAB");
			
		     while(resultSet.next()) {
		    	 System.out.println("Eid : "+resultSet.getInt("EID"));
		    	 System.out.println("Ename : "+resultSet.getString("ENAME"));
		    	 System.out.println("Esal : "+resultSet.getDouble("ESAL"));
		    	 System.out.println("----------------------------------------");
		     }
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(resultSet != null) {
					resultSet.close();
				}

				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
