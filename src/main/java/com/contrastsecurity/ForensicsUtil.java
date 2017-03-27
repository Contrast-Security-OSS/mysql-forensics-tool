package com.contrastsecurity;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.SecurityConfiguration;
import org.owasp.esapi.reference.DefaultSecurityConfiguration;
import org.owasp.esapi.reference.Log4JLogFactory;
import org.owasp.esapi.reference.crypto.ReferenceEncryptedProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class ForensicsUtil {

   public static void main(String[] args) throws Exception {
	   
	   if ( System.getProperty("contrast.home" ) == null) {
       		System.out.println("Please define contrast.home as a jvm argument");
       		System.exit(1);
       }

	   BasicInfo mysqlInfo = new BasicInfo();
	   mysqlInfo.stats();
        
   }
   
  
} 
