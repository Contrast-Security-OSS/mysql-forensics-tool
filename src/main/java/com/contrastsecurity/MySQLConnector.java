package com.contrastsecurity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MySQLConnector extends DefaultConfiguration {

	private static Connection connection = null;

	public static Connection getConnection() throws IOException {

		try {
			String dbUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbSchema;
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			return connection;

		} catch (SQLException e) {

			System.out.println(e.toString());
			return connection;

		}
	}

}
