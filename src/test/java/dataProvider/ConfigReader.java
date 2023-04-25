package dataProvider;

import java.io.*;
import java.util.Properties;

public class ConfigReader {
    public static Properties readPropertiesFile(String fileName) //throws IOException
    {
        FileInputStream fileStream = null;
        Properties propertiesObject = null;
        try {
            fileStream = new FileInputStream(fileName);
            propertiesObject = new Properties();
            propertiesObject.load(fileStream);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return propertiesObject;
    }

    public static String getURL() {
        String configFilePath = "src\\test\\ConfigProperties\\configuration.properties";
        Properties configProperties = ConfigReader.readPropertiesFile(configFilePath);
        return configProperties.getProperty("baseURL");
    }
}