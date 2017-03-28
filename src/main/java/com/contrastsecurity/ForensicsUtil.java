package com.contrastsecurity;

import java.util.Scanner;

public class ForensicsUtil {

	public static void main(String[] args) throws Exception {

		String contrastHome = System.getProperty("contrast.home");
		String performanceEnabled = System.getProperty("performance.enabled");
		String pollingEnabled = System.getProperty("polling.enabled");

		if (contrastHome == null) {
			System.out.println("Please define contrast.home as a jvm argument");
			System.exit(1);
		}

		if (performanceEnabled != null && performanceEnabled.equals("true")) {
			Migration.apply();

		}

		BasicInfo mysqlInfo = new BasicInfo();
		mysqlInfo.stats();

	}

}
