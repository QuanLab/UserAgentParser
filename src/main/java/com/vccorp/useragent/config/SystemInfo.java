package com.vccorp.useragent.config;

import java.io.IOException;

public class SystemInfo {
	static {
		try {
			ConfigUtil.loadConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String THRIFT_SERVER_HOST;
	public static int THRIFT_SERVER_PORT;

}
