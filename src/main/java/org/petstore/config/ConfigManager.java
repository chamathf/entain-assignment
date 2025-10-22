package org.petstore.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * This class helps keep things like base URLs or timeouts in one central place.
 */
public class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {


            if (input == null) {
                throw new RuntimeException("Configuration file not found");
            }


            properties.load(input);

        } catch (Exception e) {

            throw new RuntimeException("Failed to load config file", e);
        }
    }

    /**
     * This get a specific property value by its key.
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * This get the base URL for API requests.
     */
    public static String getBaseUrl() {
        return getProperty("base.url");
    }
}
