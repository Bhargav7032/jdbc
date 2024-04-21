package com.ciq.demo.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class CallProcDemo {

	public static void main(String[] args) {
		Connection connection = null;
		CallableStatement callableStatement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");

			callableStatement = connection.prepareCall("{call cal_proc(?,?,?,?,?,?)}");
			callableStatement.setInt(1, 200);
			callableStatement.setInt(2, 200);
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.registerOutParameter(4, Types.INTEGER);
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.registerOutParameter(6, Types.INTEGER);
	        callableStatement.execute();

			System.out.println("Sum : "+callableStatement.getInt(3));
			System.out.println("Sub : "+callableStatement.getInt(4));
			System.out.println("Mul : "+callableStatement.getInt(5));
			System.out.println("Div : "+callableStatement.getInt(6));
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
