package com.vccorp.useragent.config;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class PropertiesConfig {

    public static int PORT;
    public static String STRING_VALUE;
    public static long LONG_VALUE;

    public static void loadProperties() {
        String propertiesFilePath = "conf/config.properties";
        // ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //prop.load(classLoader.getResourceAsStream(propertiesFilePath));
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(propertiesFilePath));
            String key;
            Class c = PropertiesConfig.class;
            Field f;

            for (Object okey : prop.keySet()) {
                key = okey.toString();
                f = c.getDeclaredField(key);
                String typeName = f.getType().getName();
                if (typeName.equals(int.class.getName()) || typeName.equals(Integer.class.getName())) {
                    f.set(PropertiesConfig.class, Integer.valueOf(prop.getProperty(key)));
                } else if (typeName.equals(String.class.getName())) {
                    f.set(PropertiesConfig.class, prop.getProperty(key));
                } else if (typeName.equals(long.class.getName()) || typeName.equals(Long.class.getName())) {
                    f.set(PropertiesConfig.class, Long.valueOf(prop.getProperty(key)));
                } else {
                    f.set(PropertiesConfig.class, prop.getProperty(key));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
