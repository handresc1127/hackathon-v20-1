package com.handresc1127.utils;


import com.handresc1127.utils.webDriverFactory.DriverManager;
import com.handresc1127.utils.webDriverFactory.DriverManagerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class BaseTests{

    protected static DriverManager driverManager;
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static EyesManager eyesManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTests.class);
    protected static String browser;
    protected static String viewport;
    protected static String device;
    protected static String testName;


    @Parameters({ "browser", "device" })
    @BeforeClass
    public static void beforeAll(@Optional("Chrome") String _browser, @Optional("Laptop") String _device){
        PropertyLoader.loadProperties();
        LOGGER.info("");
        LOGGER.info("Browser:" + _browser+" Device:"+_device);

        driverManager = DriverManagerFactory.getManager(_browser);
        driver=driverManager.getDriver(_browser);
        wait=new WebDriverWait(driver, 30);
        driverManager.setDeviceSize(_device);

        browser=driverManager.getBrowser();
        device=driverManager.getDevice();
        viewport=driverManager.getViewport();

        //TODO solo modern tests
        eyesManager = new EyesManager(driver, PropertyLoader.getProperty("applitools.appName"));
    }

    @BeforeTest
    public static void beforeTest(ITestContext thisTest){
        testName=thisTest.getName();
    }

    @AfterClass
    public static void afterAll(){
        driverManager.quitDriver();

        //TODO solo modern tests
        eyesManager.abort();
    }

    public static String getTestName() {
            return testName;
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
        //TODO fix test Name from test content
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Traditional-V1-TestResults.txt", true))){
            writer.write("Task: " + task + ", Test Name: " + testName +", DOM Locator: " + domLocator + ", Browser: " + browser
                    + ", Viewport: " + viewport + ", Device: " + device + ", Status: " + (comparisonResult ? "Pass" : "Fail"));
            writer.newLine();
        }catch(Exception e){
            LOGGER.error("Error writing to report file "+e);
        }
        //returns the result so that it can be used for further Assertions in the test code.
        return comparisonResult;
    }

}
