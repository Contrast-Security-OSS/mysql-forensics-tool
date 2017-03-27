package com.contrastsecurity;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.SecurityConfiguration;
import org.owasp.esapi.reference.DefaultSecurityConfiguration;
import org.owasp.esapi.reference.Log4JLogFactory;
import org.owasp.esapi.reference.crypto.ReferenceEncryptedProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EsapiFileDecryptor {
	
   private static Properties propertiesObj;
 
   private static String esapiProperties;
   private static String propertyFile;
   
   public void setEsapiProperties (String esapiFile) {
	   esapiProperties = esapiFile;
   }
   
   public  void setPropertyFile (String fileName) {
	   propertyFile = fileName;
   }
   
   private static void decryptProperties () {
	   
       try {
		   
    	   Properties esapiProps = new Properties();    
	       esapiProps.load(new FileInputStream(esapiProperties ));      
	       SecurityConfiguration configuration = new DefaultSecurityConfiguration(esapiProps);  
	       esapiProps.setProperty("ESAPI.Authenticator", NoopAuthenticator.class.getName());
	       esapiProps.setProperty("ESAPI.Logger", Log4JLogFactory.class.getName());
	       esapiProps.setProperty("LogLevel", "ERROR");
	       ESAPI.override(configuration); 
	       propertiesObj = new ReferenceEncryptedProperties();       
	       propertiesObj.load(new FileInputStream(propertyFile));
      
       } catch (Exception e) {
    	   
    	   System.out.println("Not able to decrypt the file");
    	   e.printStackTrace();
    	   
       }
       
	   
   }

   public String getProperty (String property) {
	   decryptProperties();
	   return propertiesObj.getProperty(property);
   }   

   
} 