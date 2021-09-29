package org.openjfx;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration class that load informations in properties file
 */
public class ConfigurationLoader {


    public static final String CHOICE_NUMBERS = "choiceNumbers";
    public static final String TITLE = "video.";

    /**
     * The Prop.
     */
    Properties prop;

    /**
     * Instantiates a new Configuration.
     *
     * @param file the file
     */
    public ConfigurationLoader(String file) {
        try (InputStream input = new FileInputStream(file)) {

            prop = new Properties();
            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets property.
     *
     * @param property the property
     * @return the property
     */
    public String getProperty(String property) {
        return prop.getProperty(property);
    }

    public String getTitle(int step, int choice) {
        return prop.getProperty(TITLE + step + "." + choice);
    }

    /**
     * Gets integer.
     *
     * @param name         the name
     * @param defaultValue the default value
     * @return the integer
     */
    public int getInteger(String name, int defaultValue) {
        String result = prop.getProperty(name);
        if(result != null && !result.equals("")) {
            return Integer.parseInt(result);
        }
        return defaultValue;
    }
}
