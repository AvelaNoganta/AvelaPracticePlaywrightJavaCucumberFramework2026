package com.kkautomation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration utility to load properties from config.properties file
 */
public class ConfigurationManager {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream("config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            System.out.println("Error loading properties file: " + e.getMessage());
        }
    }

    /**
     * Get property value by key
     *
     * @param key the property key
     * @return the property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get property value with default fallback
     *
     * @param key          the property key
     * @param defaultValue the default value if key not found
     * @return the property value or default
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get property as integer
     *
     * @param key the property key
     * @return the integer value
     */
    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key, "0"));
    }

    /**
     * Get property as boolean
     *
     * @param key the property key
     * @return the boolean value
     */
    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(properties.getProperty(key, "false"));
    }
}
