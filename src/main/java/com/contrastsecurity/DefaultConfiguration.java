package com.contrastsecurity;

import java.io.IOException;

public class DefaultConfiguration {

	protected static String contrastHome = System.getProperty("contrast.home");

	protected static String dbPass = getEncryptedProp("jdbc.pass");

	protected static String dbUser = getEncryptedProp("jdbc.user");

	protected static String dbPort = getEncryptedProp("jdbc.port");

	protected static String dbHost = getEncryptedProp("jdbc.host");

	protected static String dbSchema = getEncryptedProp("jdbc.schema");

	public static String getEsapiProperties() {
		return FileFinder.fileFind(contrastHome, "ESAPI.properties");
	}

	public static String getDatabaseProperties() {
		return FileFinder.fileFind(contrastHome, "database.properties");
	}

	public static String getEncryptedProp(String property) {

		String esapiProperties = getEsapiProperties();
		String databaseProperties = getDatabaseProperties();
		EsapiFileDecryptor prop = new EsapiFileDecryptor();
		prop.setEsapiProperties(esapiProperties);
		prop.setPropertyFile(databaseProperties);
		return prop.getProperty(property);

	}

}
