package com.vccorp.useragent.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {

	/**
	 * load value in paired of config file conf.properties
	 * @throws IOException
	 */
	public static void loadConfig() throws IOException {
		Properties properties = new Properties();
		File f = new File("conf.properties");
		FileInputStream fileInputStream = new FileInputStream(f);
		properties.load(fileInputStream);

		SystemInfo.THRIFT_SERVER_HOST = properties.getProperty("THRIFT_SERVER_HOST");
		try{
			String port = properties.getProperty("THRIFT_SERVER_PORT");
			SystemInfo.THRIFT_SERVER_PORT = Integer.parseInt(port);
		} catch (NumberFormatException e){
			System.out.println("Thrift port in conf.properties file is not valid numnber format");
			System.exit(0);
		}
	}
}
