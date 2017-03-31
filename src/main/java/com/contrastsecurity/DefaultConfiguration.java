package com.contrastsecurity;

public class DefaultConfiguration {

	protected static String getContrastHome() {
		return System.getProperty("contrast.home");
	}
	
	protected static String getDbPass() {
		return getEncryptedProp("jdbc.pass");
	}

	protected static String getDbUser() {
		return getEncryptedProp("jdbc.user");
	}

	protected static String getDbPort() {
		return getEncryptedProp("jdbc.port");
	}
	
	protected static String getDbHost() {
		return  getEncryptedProp("jdbc.host");
	}

	protected static String getDbSchema() {
		return  getEncryptedProp("jdbc.schema");
	}

	
	protected static String getEsapiProperties() {
		return FileFinder.fileFind(getContrastHome(), "ESAPI.properties");
	}

	protected static String getDatabaseProperties() {
		return FileFinder.fileFind(getContrastHome(), "database.properties");
	}

	protected static String getEncryptedProp(String property) {
		EsapiFileDecryptor prop = new EsapiFileDecryptor();
		prop.setEsapiProperties(getEsapiProperties());
		prop.setPropertyFile(getDatabaseProperties());
		return prop.getProperty(property);

	}

}
