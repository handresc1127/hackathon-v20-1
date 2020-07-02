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
import java.lang.reflect.Method;

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
    protected static String methodName;
    private static String suiteType;


    @Parameters({"suiteType"})
    @BeforeSuite
    public static void beforeSuitAll(@Optional("suiteType") String suiteType_){
        suiteType=suiteType_;
        PropertyLoader.loadProperties();

        if(suiteType.toLowerCase().equals("modern")){
            eyesManager = new EyesManager();
        }

        LOGGER.info("Suited Configured...");
        LOGGER.info("  suite type: " + suiteType);
        LOGGER.info("  applitools.appName= "+PropertyLoader.getProperty("applitools.appName"));
        LOGGER.info("  applitools.batchName= "+PropertyLoader.getProperty("applitools.batchName"));
        LOGGER.info("  applitools.forceFullScreen= "+PropertyLoader.getProperty("applitools.forceFullScreen"));
    }

    @Parameters({ "browser", "device" })
    @BeforeTest
    public static void beforeAll(@Optional("Chrome") String _browser, @Optional("Laptop") String _device, ITestContext thisTest){
        browser=_browser;
        device=_device;
        testName=thisTest.getName();

        LOGGER.info("Test Configured...");
        LOGGER.info("  Browser:" + _browser+" Device:"+_device);
        LOGGER.info("  Test Name: "+thisTest.getName());
    }

    @BeforeMethod
    public static void beforeEach(Method method){
        methodName=method.getName();
        LOGGER.info("Method beginning...");
        LOGGER.info("  Method name: " + methodName);
        driverManager = DriverManagerFactory.getManager(browser);
        driver=driverManager.getDriver(browser);

        wait=new WebDriverWait(driver, 30);
        driverManager.setDeviceSize(device);

        browser=driverManager.getBrowser();
        device=driverManager.getDevice();
        viewport=driverManager.getViewport();

        if(suiteType.toLowerCase().equals("modern")){
            eyesManager.setDriver(driver);
        }

        LOGGER.info("  Browser:" + browser+" Device:"+device+" Viewport:"+viewport);
    }

    @AfterMethod
    public static void afterEach(){
        driverManager.quitDriver();
        if(suiteType.toLowerCase().equals("modern")) {
            eyesManager.closeEyes();
        }
    }

    @AfterSuite
    public static void afterAll(){
        if(suiteType.toLowerCase().equals("modern")) {
            eyesManager.abort();
        }
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
        String testName=Thread.currentThread().getStackTrace()[3].getMethodName();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Traditional-V1-TestResults.txt", true))){
            writer.write("Task: " + methodName.replace("task","")
                    + ", Test Name: " + normalize(testName+", ",32)
                    + "DOM Locator: " + normalize(domLocator+", ",30)
                    + "Browser: " +     normalize(browser+", ",10)
                    + "Viewport: " +    normalize(viewport+", ",10)
                    + "Device: " +    normalize(device+", ",9)
                    + "Status: " +    (comparisonResult ? "Pass" : "Fail"));
            writer.newLine();
        }catch(Exception e){
            LOGGER.error("Error writing to report file "+e);
        }
        //returns the result so that it can be used for further Assertions in the test code.
        return comparisonResult;
    }

    static String normalize(String text, int maxLen){
        int len=text.length();
        for (int i=0;i<(maxLen-len);i++){
            text+=" ";
        }
        return text;
    }

}
