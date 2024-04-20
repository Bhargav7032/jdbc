package com.ciq.demo.transactionmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionManagementDemo {

	public static void main(String[] args) {

		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
			connection.setAutoCommit(false);
			statement = connection.createStatement();

			statement.addBatch("UPDATE ACCOUNT SET ABAL = ABAL - 80000 WHERE AID = 1001");
			statement.addBatch("UPDATE ACCOUNT SET ABAL = ABAL + 80000 WHERE AID = 1002");

			statement.executeBatch();
			connection.commit();
			System.out.println("Done");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {

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
