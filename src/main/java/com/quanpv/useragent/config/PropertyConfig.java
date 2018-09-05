package com.quanpv.useragent.config;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Properties;


public class PropertyConfig {

	public static String THRIFT_SERVER_HOST;
	public static int THRIFT_SERVER_PORT;


    static {
        loadProperties();
    }


    /**
     * load config from property file
     *
     */
    private static void loadProperties() {

        String propertiesFilePath = "etc/config.properties";

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(propertiesFilePath));
            String key;
            Class c = PropertyConfig.class;
            Field f;

            for (Object okey : prop.keySet()) {
                key = okey.toString();
                f = c.getDeclaredField(key);
                String typeName = f.getType().getName();
                if (typeName.equals(int.class.getName()) || typeName.equals(Integer.class.getName())) {
                    f.set(PropertyConfig.class, Integer.valueOf(prop.getProperty(key)));
                } else if (typeName.equals(String.class.getName())) {
                    f.set(PropertyConfig.class, prop.getProperty(key));
                } else if (typeName.equals(long.class.getName()) || typeName.equals(Long.class.getName())) {
                    f.set(PropertyConfig.class, Long.valueOf(prop.getProperty(key)));
                } else {
                    f.set(PropertyConfig.class, prop.getProperty(key));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
