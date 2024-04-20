package com.ciq.demo.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTableDemo {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");

			preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE_TAB");

			resultSet = preparedStatement.executeQuery();
			
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

				if (preparedStatement != null) {
					preparedStatement.close();
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
