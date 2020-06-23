package com.handresc1127.utils;


import com.handresc1127.utils.webDriverFactory.DriverManager;
import com.handresc1127.utils.webDriverFactory.DriverManagerFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.awt.*;
import java.util.HashMap;

public class BaseTests {

    protected static DriverManager driverManager;
    protected static WebDriver driver;
    protected static EyesManager eyesManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTests.class);

    @Parameters({ "browser", "device" })
    @BeforeClass
    public static void beforeAll(@Optional("Chrome") String browser, @Optional("Laptop") String device){
        PropertyLoader.loadProperties();
        System.out.println();
        System.out.println("Browser:" + browser+" Device:"+device);

        driverManager = DriverManagerFactory.getManager(browser);
        driver=driverManager.getDriver(browser);
        driverManager.setDeviceSize(device);


        //eyesManager = new EyesManager(driver, "TAU - Visual Testing Applitools");

    }

    @AfterClass
    public static void afterAll(){
        driverManager.quitDriver();
        //eyesManager.abort();
    }

}
