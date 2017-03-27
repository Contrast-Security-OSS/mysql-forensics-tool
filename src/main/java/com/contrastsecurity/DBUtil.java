package com.contrastsecurity;

import com.google.gson.Gson;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DBUtil {
	
	private static List<Map<String, Object>> listOfMaps = null;
	private static  Connection connection = null;

    
	public DBUtil () throws FileNotFoundException, SQLException, IOException {
		createConnection();
	}
	
	private static void createConnection () throws SQLException, FileNotFoundException, IOException {
         MySQLConnector db =  new MySQLConnector();
         connection =  db.getConnection();
    }
	
	@SuppressWarnings("deprecation")
	public static void resultSetToJson(String query,  String param) throws SQLException {
		
		try {
			QueryRunner queryRunner = new QueryRunner();
			listOfMaps = queryRunner.query(connection, query, param, new MapListHandler()); 
	        for (Map<String, Object> map : listOfMaps) {
	        	System.out.println( new Gson().toJson(map));
	        }
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
        connection.close();
    }
	
	public static void resultSetToJson(String query) throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		listOfMaps = queryRunner.query(connection, query, new MapListHandler()); 
        for (Map<String, Object> map : listOfMaps) {
        	System.out.println( new Gson().toJson(map));
        }
        connection.close();
    }
	
}