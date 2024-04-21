package com.ciq.demo.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class CallFunctionDemo2 {

	public static void main(String[] args) {
		Connection connection = null;
		CallableStatement callableStatement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");

			callableStatement = connection.prepareCall("{? = call fn_getFullName(?,?)}");
			callableStatement.setString(2, "Balaji");
			callableStatement.setString(3, "G");
			callableStatement.registerOutParameter(1, Types.VARCHAR);
	        callableStatement.execute();

			System.out.println("Result : "+callableStatement.getString(1));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (callableStatement != null) {
					callableStatement.close();
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
