package com.vccorp.useragent.client;

import com.vccorp.useragent.config.SystemInfo;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * User Agent Parser Client
 * 
 * @author thanhnd
 * 
 */
public class UAParser {

	private static UserAgentParser.Client uaParser;
	private static TTransport transport;
	private static UAParser instance = null;


	/**
	 * Constructor for class parser user agent string
	 */
	public UAParser() {
		try {
			String host= SystemInfo.THRIFT_SERVER_HOST;
			System.err.println(host);
			transport = new TSocket(host, SystemInfo.THRIFT_SERVER_PORT);

			transport.open();
			TProtocol protocol = new TBinaryProtocol(transport);
			uaParser = new UserAgentParser.Client(protocol);
		} catch (TException x) {
			x.printStackTrace();
		}
	}

	/**
	 * create single instance
	 * @return
	 */
	public static UAParser getInstance () {
		if (instance==null) {
			instance = new UAParser();
		}
		return instance;
	}

	/**
	 *
	 * @param userAgentString raw string user agent</br>
	 * @return type of os ; os version; model name of device
	 */
	public String parse(String userAgentString) {
		try {
			String info = uaParser.parse(userAgentString);
			String[] splits = info.split(";");
			String value = splits[0] + ";" + splits[1] + ";" + splits[3] + ";"
					+ splits[4];
			return value;
		}catch (TException e) {
			return null;
		}
	}


	/**
	 * close transport
	 */
	public static void close() {
		transport.close();
	}
	
	public static void main(String[] args) {
		UAParser userAgentParser = UAParser.getInstance();
		String userAgentString =
					"Mozilla/5.0 (iPhone; CPU iPhone OS 7_1_2 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) CriOS/46.0.2490.85 Mobile/11D257 Safari/9537.53";
		String info = userAgentParser.parse(userAgentString);
		System.out.println(info);
	}
}