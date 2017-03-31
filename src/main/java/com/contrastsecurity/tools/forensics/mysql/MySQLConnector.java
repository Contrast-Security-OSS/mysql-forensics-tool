package com.contrastsecurity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class MySQLConnector extends DefaultConfiguration {

	private static final Logger logger = LogManager.getLogger(MySQLConnector.class);
	private static Connection connection = null;

	public static Connection getConnection() throws IOException {

		try {
			String dbUrl = "jdbc:mysql://" + getDbHost() + ":" + getDbPort() + "/" + getDbSchema();
			connection = DriverManager.getConnection(dbUrl, getDbUser(), getDbPass());
			return connection;

		} catch (SQLException e) {
			logger.error( Thread.currentThread().getStackTrace()[2].getMethodName()
					+ " => " + e.toString() );			
			return connection;

		}
	}

}
