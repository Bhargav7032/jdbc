package com.ciq.demo.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTableDemo {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");

			preparedStatement = connection.prepareStatement("INSERT INTO EMPLOYEE_TAB VALUES(?,?,?)");
			preparedStatement.setInt(1, 1001);
			preparedStatement.setString(2, "Balaji");
			preparedStatement.setDouble(3, 50000.00);

			int rowsUpdated = preparedStatement.executeUpdate();

			System.out.println(rowsUpdated + "  Rows inserted successfully");
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
