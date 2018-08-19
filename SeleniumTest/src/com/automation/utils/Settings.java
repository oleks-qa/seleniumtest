package com.automation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private Properties properties;
    public Settings() {
            properties = new Properties();
            try {
                properties.load(new FileInputStream("driver.settings"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException io) {
                io.printStackTrace();
            }
    }

    public String getSetting (String propertyName) {
       return properties.getProperty(propertyName);
    }
}
