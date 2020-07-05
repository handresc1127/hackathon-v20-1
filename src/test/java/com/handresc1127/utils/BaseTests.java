package com.handresc1127.utils;

import com.handresc1127.utils.webDriverFactory.DriverManager;
import com.handresc1127.utils.webDriverFactory.DriverManagerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTests{

    protected static DriverManager driverManager;
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static EyesManager eyesManager;
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
    }

    @Parameters({ "browser", "device" })
    @BeforeTest
    public static void beforeAll(@Optional("Chrome") String _browser, @Optional("Laptop") String _device, ITestContext thisTest){
        browser=_browser;
        device=_device;
        testName=thisTest.getName();
    }

    @BeforeMethod
    public static void beforeEach(Method method){
        methodName=method.getName();
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
}
