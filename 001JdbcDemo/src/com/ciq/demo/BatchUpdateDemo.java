package com.ciq.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdateDemo {

	public static void main(String[] args) {
			Connection connection = null;
			Statement statement = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
				statement = connection.createStatement();
				
				statement.addBatch("INSERT INTO EMPLOYEE_TAB VALUES(1001,'Balaji',70000.00)");
				statement.addBatch("INSERT INTO EMPLOYEE_TAB VALUES(1002,'Vivek',70000.00)");
				statement.addBatch("INSERT INTO EMPLOYEE_TAB VALUES(1003,'Bhargav',70000.00)");
				statement.addBatch("UPDATE EMPLOYEE_TAB SET ESAL = 80000 WHERE ESAL > 65000");
				statement.addBatch("DELETE FROM EMPLOYE WHERE ESAL < 65000");
				
				
				int rowsUpdatedArr[] = statement.executeBatch();
				
				for (int i : rowsUpdatedArr) {
					System.out.println(rowsUpdatedArr[i]);
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
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
