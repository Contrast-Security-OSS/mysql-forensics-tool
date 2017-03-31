package com.contrastsecurity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;

public class Migration extends DefaultConfiguration {
	
	private static DBUtil dbCall;
	private static List<String> trashCan;
	
	protected Migration() {
		trashCan = new ArrayList<String>();
		dbCall = new DBUtil();
	}

	private static boolean utilExistsTest(String util) throws InterruptedException, IOException {
		try {
			
			Process p = Runtime.getRuntime().exec(util);
			p.waitFor();
			if ( p.exitValue() == 0)
				return true;
			else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	private static String getRandomFile() {
		String randomStr = RandomStringUtils.randomAlphanumeric(20).toUpperCase();
		return System.getProperty("user.dir") + "/contrast-" + randomStr;
	}
	
	private static String getSqlScript() throws IOException {
		String sqlScript = getRandomFile() + ".sql";
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream input = loader.
				getResourceAsStream("db/migration/sys_1.5.1_56_inline.sql");
		OutputStream outputStream =
                new FileOutputStream(new File(sqlScript));
		int read = 0;
		byte[] bytes = new byte[1024];
		while ((read = input.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
		trashCan.add(sqlScript);
		return sqlScript;
	}

	private static String getExecScript() throws IOException, InterruptedException {	
		String execScript = getRandomFile() + ".exec";
		Path path = Paths.get(execScript);
		try (BufferedWriter writer = Files.newBufferedWriter(path)) 
		{
		    writer.write(String.format( "%s --defaults-extra-file=%s --binary-mode "
		    		+ "--protocol=tcp -h %s -P %s < %s", 
					getMysqlPath(), getMyCnf(), getDbHost(), getDbPort(), getSqlScript()));
		}
		trashCan.add(execScript);
		return execScript;
	}
	
	private static String getMyCnf() throws IOException {
		String myCnf = getRandomFile() + ".cnf";
		Path path = Paths.get(myCnf);
		try (BufferedWriter writer = Files.newBufferedWriter(path)) 
		{
		    writer.write("[client]\n");
		    writer.write("user=" + getDbUser() + "\n");
		    writer.write("password=" + getDbPass() + "\n");
		}
		trashCan.add(myCnf);
		return myCnf;
	}
	
	private static void deleteFile (String filePath) {	
	    Path fileToDeletePath = Paths.get(filePath);
	    try {
			Files.delete(fileToDeletePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String  getMysqlPath() throws InterruptedException, IOException  {
		
		String mysqlUtility = null;

		if (utilExistsTest("mysql -V")) {
			mysqlUtility = "mysql";
		} else if (utilExistsTest("mysql.exe -V")) {
			mysqlUtility = getContrastHome() + "/mysql.exe";
		} else if (utilExistsTest(getContrastHome() + "/mysql/bin/mysql -V")) {
			mysqlUtility = getContrastHome() + "/mysql/bin/mysql";
		} else if (utilExistsTest(getContrastHome() + "/mysql/bin/mysql.exe -V")) {
			mysqlUtility = getContrastHome() + "/mysql/bin/mysql.exe";	
		} else if (System.getProperty("mysql.bin") != null) {
			mysqlUtility = System.getProperty("mysql.bin");
		} else {
			System.exit(1);
		}
		return mysqlUtility;
	}	
	
	private boolean schemaExists (String schema) throws SQLException {
		List<Map<String, Object>> listOfMaps = dbCall.query("show databases");
		boolean exists = false;
		for (Map<String, Object> map : listOfMaps ) {
			if (map.containsValue(schema)) {
				exists = true;
			}
		}
		return exists;
	}

	public void apply() throws 
			SQLException, IOException, InterruptedException {
		
		if( !schemaExists("sys") ) 
				executeScript();
				for (String file : trashCan) {
					deleteFile(file);				
			
		}
	}
	
	private static void executeScript () throws IOException, InterruptedException {
		String[] cmd = new String[]{ "/bin/bash", getExecScript() };		
		Process p = Runtime.getRuntime().exec(cmd);
	    p.waitFor();
	    if (p.exitValue() != 0) {
	    	System.out.println("I wasn't able to appy the migration");
	    }
	    
        

	}
}