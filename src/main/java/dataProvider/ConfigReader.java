package dataProvider;

import org.slf4j.*;
import org.slf4j.Logger;

import java.io.*;
import java.util.Properties;

public class ConfigReader {
    private static Properties propertiesObject;
    private static Logger logger = LoggerFactory.getLogger(ConfigReader.class);

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
        } catch (FileNotFoundException e) {
            logger.info("File not found exception occurred");
        } catch (IOException e) {
            logger.info("IO exception occurred");
        }
        return propertiesObject;
    }
}