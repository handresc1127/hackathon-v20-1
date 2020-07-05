package com.handresc1127.utils.webDriverFactory;

import com.handresc1127.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public abstract class DriverManager {
    protected WebDriver driver;
    protected abstract void createDriver(String type);
    protected String browser;
    protected String viewport;
    protected String device;
    protected OS osName = null;
    public enum OS {
        WINDOWS, LINUX, MAC
    }// Operating systems.

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getDriver(String browser) {
        if (osName == null) {
            String operativeSys = System.getProperty("os.name").toLowerCase();
            if (operativeSys.contains("win")) {
                osName = OS.WINDOWS;
            } else if (operativeSys.contains("nix") || operativeSys.contains("nux")
                    || operativeSys.contains("aix")) {
                osName = OS.LINUX;
            } else if (operativeSys.contains("mac")) {
                osName = OS.MAC;
            }
        }

        if (null == driver) {
            createDriver(browser);
        }
        return driver;
    }

    public String getViewport(){
        return viewport;
    }

    public String getBrowser() {
        return browser;
    }

    public String getDevice() {
        return device;
    }

    protected void setDriverFile(String browserFile){
        if(osName!=null){
            final String driverKey="webdriver."+browserFile+".driver";
            String driverValue="driver.os."+browserFile;
            switch(osName)
            {
                case WINDOWS: driverValue= driverValue.replace("os","windows");break;
                case LINUX: driverValue= driverValue.replace("os","linux");break;
                case MAC: driverValue= driverValue.replace("os","mac");break;
            }
            PropertyLoader.setProperty(driverKey,PropertyLoader.getProperty(driverValue));
        }
    }

    public void setDeviceSize(String device_){
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        org.openqa.selenium.Dimension targetSize ;
        int x, y;
        switch (device_.toLowerCase()){
            case "laptop": x=1200; y=700; device_="Laptop"; break;
            case "tablet": x=800; y=700; device_="Tablet"; break;
            case "mobile": x=500; y=700; device_="Mobile"; break;
            default: x=(int) screenSize.getWidth(); y=(int) screenSize.getHeight(); break;
        }
        device=device_;
        targetSize = new org.openqa.selenium.Dimension(x, y);
        viewport=x+"x"+y;
        driver.manage().window().setSize(targetSize);
    }
}
