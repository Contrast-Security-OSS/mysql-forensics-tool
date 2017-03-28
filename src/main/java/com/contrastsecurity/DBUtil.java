package com.contrastsecurity;

import com.google.gson.Gson;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class DBUtil {

	private static List<Map<String, Object>> listOfMaps = null;
	private static Connection connection = null;
	private static final Logger logger = LogManager.getLogger(DBUtil.class);

	private static void createConnection()  {
		MySQLConnector db = new MySQLConnector();
		try {
			connection = db.getConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void resultSetToJson(String query, String param) throws SQLException {
		createConnection();
		try {
			QueryRunner queryRunner = new QueryRunner();
			listOfMaps = queryRunner.query(connection, query, param, new MapListHandler());
			for (Map<String, Object> map : listOfMaps) {
				//System.out.println(new Gson().toJson(map));
				
				
				logger.info(new Gson().toJson(map));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.close();
	}

	public void resultSetToJson(String query) throws SQLException {
		createConnection();
		QueryRunner queryRunner = new QueryRunner();
		listOfMaps = queryRunner.query(connection, query, new MapListHandler());
		for (Map<String, Object> map : listOfMaps) {
			//System.out.println(new Gson().toJson(map));
			//logger.info(new Gson().toJson(map));
		}
		connection.close();
	}

}