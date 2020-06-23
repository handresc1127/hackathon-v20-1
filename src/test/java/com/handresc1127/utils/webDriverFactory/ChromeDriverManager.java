package com.handresc1127.utils.webDriverFactory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

public class ChromeDriverManager extends DriverManager {


    @Override
    public void createDriver(String type) {

        ChromeOptions options = new ChromeOptions();
        switch (type.toUpperCase()) {
            case "PORTRAIT":
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("deviceName", "iPhone X");
                options.setExperimentalOption("mobileEmulation", chromePrefs);
                break;
            default:
                //options.addArguments("--whitelisted-ips='"+ PropertyLoader.getProperty("chrome.whitelistedIps") +"'");
                //options.addArguments("--start-maximized");
                break;
        }
        browser="Chrome";
        driver = new ChromeDriver(options);
    }

}