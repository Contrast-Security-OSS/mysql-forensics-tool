package com.contrastsecurity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class BasicInfo {

   
 
   public static void getTableSizes() throws Exception {
	   
	   String installDir = System.getProperty("contrast.home");
		 
		 EsapiFileDecryptor prop = new EsapiFileDecryptor();
		 prop.setEsapiProperties(FileFinder.fileFind(installDir, "ESAPI.properties"));
		 prop.setPropertyFile(FileFinder.fileFind(installDir, "database.properties"));

	     String dbSchema = prop.getProperty("jdbc.schema");
	   
	     
	    

	   
       String query = "SELECT  table_name AS `Table`, " +  
                      "round(((data_length + index_length) / 1024 / 1024), 2) `MB`, " +
    		          "index_length as 'Objects' " + 
                      "FROM information_schema.TABLES " +
                      "WHERE table_schema = ? " + 
                      "order by index_length DESC";
   	   
       
       DBUtil dbCall = new DBUtil();
       dbCall.resultSetToJson(query, dbSchema);
       
       
   }
   
   public static void getVersion() throws FileNotFoundException, SQLException, IOException  {
	   DBUtil dbCall = new DBUtil();
	   dbCall.resultSetToJson("SELECT VERSION() as version");
   }
   
   public static void stats() throws Exception {
	   getVersion();
	   getGlobalStatusStats();
	   getTableSizes();	   
   }
   
	private static void getGlobalStatusStats() throws SQLException, FileNotFoundException, IOException {
		 DBUtil dbCall = new DBUtil();
		 dbCall.resultSetToJson("SHOW GLOBAL STATUS");
	
}

	public static String nullParams = null;

   
  
} 
