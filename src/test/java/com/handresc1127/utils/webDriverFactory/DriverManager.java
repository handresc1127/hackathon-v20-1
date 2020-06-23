package com.handresc1127.utils.webDriverFactory;

import org.openqa.selenium.WebDriver;

import java.awt.*;

public abstract class DriverManager {
    protected WebDriver driver;
    protected abstract void createDriver(String type);
    protected String browser;
    protected String viewport;
    protected String device;

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getDriver(String browser) {
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

    public void setDeviceSize(String device_){
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        org.openqa.selenium.Dimension targetSize ;
        int x, y;
        switch (device_.toLowerCase()){
            case "laptop": x=1200; y=700; device_="Laptop"; break;
            case "tablet": x=768; y=700; device_="Tablet"; break;
            case "mobile": x=500; y=700; device_="Mobile"; break;
            default: x=(int) screenSize.getWidth(); y=(int) screenSize.getHeight(); break;
        }
        device=device_;
        targetSize = new org.openqa.selenium.Dimension(x, y);
        viewport=x+"x"+y;
        driver.manage().window().setSize(targetSize);
    }
}
