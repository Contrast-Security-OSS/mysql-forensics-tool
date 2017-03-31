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
			String dbUrl = "jdbc:mysql://" + getDbHost() + ":" + getDbPort() + "/" + getDbSchema();
			connection = DriverManager.getConnection(dbUrl, getDbUser(), getDbPass());
			return connection;

		} catch (SQLException e) {

			System.out.println(e.toString());
			return connection;

		}
	}

}
