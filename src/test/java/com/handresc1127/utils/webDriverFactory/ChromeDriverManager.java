package com.handresc1127.utils.webDriverFactory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

public class ChromeDriverManager extends DriverManager {


    @Override
    public void createDriver(String type) {
        setDriverFile("chrome");
        ChromeOptions options = new ChromeOptions();
        if ("PORTRAIT".equals(type.toUpperCase())) {
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("deviceName", "iPhone X");
            options.setExperimentalOption("mobileEmulation", chromePrefs);
        }
        //else arguments
        //options.addArguments("--whitelisted-ips='"+ PropertyLoader.getProperty("chrome.whitelistedIps") +"'");
        //options.addArguments("--start-maximized");
        browser="Chrome";
        driver = new ChromeDriver(options);
    }

}