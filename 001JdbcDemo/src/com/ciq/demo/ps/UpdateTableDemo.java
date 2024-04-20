package com.ciq.demo.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTableDemo {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");

			preparedStatement = connection.prepareStatement("UPDATE EMPLOYEE_TAB SET ENAME = ? , ESAL = ? WHERE EID = ?");
			
			preparedStatement.setString(1, "Balaji G");
			preparedStatement.setDouble(2, 60000.00);
			preparedStatement.setInt(3, 1001);

			int rowsUpdated = preparedStatement.executeUpdate();

			System.out.println(rowsUpdated + "  Rows updated successfully");
			System.out.println("done");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

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
