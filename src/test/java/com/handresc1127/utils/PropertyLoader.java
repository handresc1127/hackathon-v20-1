package com.handresc1127.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertyLoader {

    private static Properties properties = System.getProperties();
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyLoader.class);
    private static boolean isLoaded=false;


    static void loadProperties(){
        try{
            properties.load(new FileInputStream(new File("resources/test.properties")));
            isLoaded=true;
        }catch (Exception ex){
            LOGGER.debug("Loading properties error ",ex);
        }
    }

    public static String getProperty(String property) {
        if(!isLoaded) {
            loadProperties();
        }
        String propertyValue = properties.getProperty(property);
        if (propertyValue == null){
            propertyValue = System.getenv(property);
        }

        if (propertyValue == null){
            printProperties();
            throw new RuntimeException("No property found; Please set environment variable '"+property+"'.");
        }
        LOGGER.debug(property+"= "+propertyValue);
        return propertyValue;
    }

    public static void setProperty(String property, String value) {
        properties.setProperty(property, value);
    }

    static void printProperties(){
        Enumeration keys = properties.keys();
        while (keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            String value = (String)properties.get(key);
            LOGGER.info(key + ": " + value);
        }
    }

}
