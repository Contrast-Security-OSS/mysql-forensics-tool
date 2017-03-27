package com.contrastsecurity;

public class ForensicsUtil {

	public static void main(String[] args) throws Exception {

		if (System.getProperty("contrast.home") == null) {
			System.out.println("Please define contrast.home as a jvm argument");
			System.exit(1);
		}

		BasicInfo mysqlInfo = new BasicInfo();
		mysqlInfo.stats();

	}

}
