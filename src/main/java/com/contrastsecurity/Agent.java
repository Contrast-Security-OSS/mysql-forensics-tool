package com.contrastsecurity;

import java.util.Scanner;

public class Agent {

	public static void main(String[] args) throws Exception {

		String contrastHome = System.getProperty("contrast.home");
		String performanceEnabled = System.getProperty("performance.enabled");
		String pollingEnabled = System.getProperty("polling.enabled");

		if (contrastHome == null) {
			System.out.println("Please define contrast.home as a jvm argument");
			System.exit(1);
		}

		

		Metrics mysqlInfo = new Metrics();
		mysqlInfo.stats();
		
		if (performanceEnabled != null && performanceEnabled.equals("true")) {
			Migration migration = new Migration();
			migration.apply();
			
			AdvancedMetrics advancedMysqlInfo = new AdvancedMetrics();
			advancedMysqlInfo.stats();

		}
		
		
		

	}

}
