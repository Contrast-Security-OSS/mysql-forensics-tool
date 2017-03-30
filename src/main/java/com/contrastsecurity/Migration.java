package com.contrastsecurity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.flywaydb.core.Flyway;

import com.mysql.jdbc.Connection;

public class Migration extends DefaultConfiguration {
	
	private static Connection connection = null;
	private static String myCnf = null;
	private static String executeScript = null;
	private static String sqlScript = null;
	private static String mysqlPath = null;
	private static DBUtil dbCall;
	
	public Migration() {
		dbCall = new DBUtil();
	}

	public static boolean utilExistsTest(String util) throws InterruptedException, IOException {
		try {
			
			Process p = Runtime.getRuntime().exec(util);
			p.waitFor();
			int retVal = p.exitValue();
			if ( retVal == 0)
				return true;
			else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	private String getRandomFile() {
		String randomStr = RandomStringUtils.randomAlphanumeric(20).toUpperCase();
		return  System.getProperty("user.dir") + "/contrast-" + randomStr;
	}
	
	private void generateSqlScript() throws IOException {
		String fileName = getRandomFile() + ".sql";	
		
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("db/migration/sys_1.5.1_56_inline.sql");	
		OutputStream outputStream =
                new FileOutputStream(new File(fileName));

		int read = 0;
		byte[] bytes = new byte[1024];
	
		while ((read = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
		sqlScript = fileName;	
	}

	private void generateExecuteScript () throws IOException, InterruptedException {	
		String cmd = String.format( "%s --defaults-extra-file=%s --binary-mode --protocol=tcp -h %s -P %s < %s", 
						mysqlPath, myCnf, dbHost, dbPort, sqlScript);
		String fileName = getRandomFile() + ".exec";	
		Path path = Paths.get(fileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path)) 
		{
		    writer.write(cmd);
		}
		executeScript = fileName;
	}
	
	private void generateMyCnf () throws IOException {
		String fileName = System.getProperty("user.dir") + "/.my.cnf";
		Path path = Paths.get(fileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path)) 
		{
		    writer.write("[client]\n");
		    writer.write("user=" + dbUser + "\n");
		    writer.write("password=" + dbPass + "\n");
		}
		this.myCnf = fileName;
	}
	
	private static void deleteFile (String filePath) {	
	    Path fileToDeletePath = Paths.get(filePath);
	    try {
			Files.delete(fileToDeletePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void generateMysqlPath() throws InterruptedException, IOException {
		
		String mysqlUtility = null;

		if (utilExistsTest("mysql -V")) {
			mysqlUtility = "mysql";
		} else if (utilExistsTest("mysql.exe -V")) {
			mysqlUtility = contrastHome + "mysql.exe";
		} else if (utilExistsTest(contrastHome + "/mysql/bin/mysql -V")) {
			mysqlUtility = contrastHome + "/mysql/bin/mysql";
		} else if (utilExistsTest(contrastHome + "/mysql/bin/mysql.exe -V")) {
			mysqlUtility = contrastHome + "/mysql/bin/mysql.exe";	
		} else if (System.getProperty("mysql.bin") != null) {
			mysqlUtility = System.getProperty("mysql.bin");
		} else {
			System.out.println("Please specify -Dmysql.bin=/path/to/your/mysql/executable");
			System.exit(1);
		}
		
		mysqlPath = mysqlUtility;
	}	
	
	public boolean schemaExists (String schema) throws SQLException {
		List<Map<String, Object>> listOfMaps = dbCall.query("show databases");
		boolean exists = false;
		for (Map<String, Object> map : listOfMaps ) {
			if (map.containsValue(schema)) {
				exists = true;
			}
		}
		return exists;
	}

	public void apply() throws SQLException {
		
		try {
			generateMysqlPath();
			generateSqlScript();
			generateMyCnf();
			generateExecuteScript();	
			executeScript();
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			deleteFile(myCnf);
			deleteFile(executeScript);
			deleteFile(sqlScript);
		}			

	}
	
	public static void executeScript () throws IOException {
		String[] cmd = new String[]{ "/bin/bash", executeScript };
				
		String s = null;		
		Process p = Runtime.getRuntime().exec(cmd);
        
        BufferedReader stdInput = new BufferedReader(new 
             InputStreamReader(p.getInputStream()));

        BufferedReader stdError = new BufferedReader(new 
             InputStreamReader(p.getErrorStream()));

        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }
        
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
        

	}
}