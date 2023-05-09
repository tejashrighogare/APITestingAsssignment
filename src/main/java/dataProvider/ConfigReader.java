package dataProvider;

import org.apache.log4j.*;

import java.io.*;
import java.util.Properties;

public class ConfigReader {
    private static Properties propertiesObject;

    public static String getPropertyValue(String propertyName) {
        String configFilePath = System.getProperty("user.dir");
        Properties configProperties = ConfigReader.readPropertiesFile(configFilePath + "/configuration.properties");
        return configProperties.getProperty(propertyName);
    }

    public static Properties readPropertiesFile(String fileName) {
        try {
            FileInputStream fileStream = new FileInputStream(fileName);
            propertiesObject = new Properties();
            propertiesObject.load(fileStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return propertiesObject;
    }
}