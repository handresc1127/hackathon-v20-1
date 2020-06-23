package com.handresc1127.utils;


import com.handresc1127.utils.webDriverFactory.DriverManager;
import com.handresc1127.utils.webDriverFactory.DriverManagerFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class BaseTests{

    protected static DriverManager driverManager;
    protected static WebDriver driver;
    protected static EyesManager eyesManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTests.class);
    protected static String browser;
    protected static String viewport;
    protected static String device;


    @Parameters({ "browser", "device" })
    @BeforeClass
    public static void beforeAll(@Optional("Chrome") String _browser, @Optional("Laptop") String _device){
        PropertyLoader.loadProperties();
        System.out.println();
        System.out.println("Browser:" + _browser+" Device:"+_device);

        driverManager = DriverManagerFactory.getManager(_browser);
        driver=driverManager.getDriver(_browser);
        driverManager.setDeviceSize(_device);

        browser=driverManager.getBrowser();
        device=driverManager.getDevice();
        viewport=driverManager.getViewport();

        //eyesManager = new EyesManager(driver, "TAU - Visual Testing Applitools");

    }

    @AfterClass
    public static void afterAll(){
        driverManager.quitDriver();
        //eyesManager.abort();
    }

    /**
     * A Helper to print the test result in the following format:
     * DOM Locator: <DOM Locator>,
     * Browser: <Browser>,
     * Viewport: <Width x Height>,
     * Device <Device type>,
     * Status: <Pass | Fail>
     *
     * @param domLocator              string - DOM ID of the element
     * @param comparisonResult        boolean - The result of comparing the "Expected" value and the "Actual" value.
     * @return                        boolean - returns the same comparison result back so that it can be used for further Assertions in the test code.
     */

    public static boolean hackathonReporter(String domLocator, boolean comparisonResult) {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        String task=ste[3].getFileName().replace(".java","");
        String testName=ste[3].getMethodName();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Traditional-V1-TestResults.txt", true))){
            writer.write("Task: " + task + ", Test Name: " + testName +", DOM Locator: " + domLocator + ", Browser: " + browser
                    + ", Viewport: " + viewport + ", Device: " + device + ", Status: " + (comparisonResult ? "Pass" : "Fail"));
            writer.newLine();
        }catch(Exception e){
            System.out.println("Error writing to report file");
            e.printStackTrace();
        }
        //returns the result so that it can be used for further Assertions in the test code.
        return comparisonResult;
    }

}
