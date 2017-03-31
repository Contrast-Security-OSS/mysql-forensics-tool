package com.contrastsecurity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Metrics extends DefaultConfiguration {

	private static DBUtil dbCall;

	protected Metrics() {
		dbCall = new DBUtil();
	}

	private static void getTableSizes() throws Exception {
		String parameterizedQuery = "SELECT  table_name AS `Table`, "
				+ "round(((data_length + index_length) / 1024 / 1024), 2) `MB`, " + "index_length as 'Objects' "
				+ "FROM information_schema.TABLES " + "WHERE table_schema = ? " + "order by index_length DESC";
		dbCall.resultSetToJson(parameterizedQuery, getDbSchema());

	}

	private static void getVariables() throws SQLException {
		dbCall.resultSetToJson("SHOW GLOBAL STATUS");

	}

	private static void getVersion() throws SQLException {
		dbCall.resultSetToJson("SELECT VERSION() as version");
	}

	public static void stats() throws Exception {
		getVersion();
		getVariables();
		getTableSizes();
		
		dbCall.close();
	}

}
