package com.handresc1127.utils.webDriverFactory;

public class DriverManagerFactory {

    public static DriverManager getManager(String type) {

        DriverManager driverManager;

        switch (type.toUpperCase()) {
            case "EDGE":
                driverManager = new EdgeDriverManager();
                break;
            case "FIREFOX":
                driverManager = new FirefoxDriverManager();
                break;
            default:
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;
    }
}