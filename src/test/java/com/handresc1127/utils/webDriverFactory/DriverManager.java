package com.handresc1127.utils.webDriverFactory;

import org.openqa.selenium.WebDriver;

import java.awt.*;

public abstract class DriverManager {
    protected WebDriver driver;
    protected abstract void createDriver(String type);

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

    public void setDeviceSize(String device){
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        org.openqa.selenium.Dimension targetSize ;
        switch (device.toLowerCase()){
            case "laptop": targetSize = new org.openqa.selenium.Dimension(1200, 700); break;
            case "tablet": targetSize = new org.openqa.selenium.Dimension(768, 700); break;
            case "mobile": targetSize = new org.openqa.selenium.Dimension(500, 700); break;
            default: targetSize = new org.openqa.selenium.Dimension(
                    (int) screenSize.getWidth(), (int) screenSize.getHeight());
                    break;
        }
        driver.manage().window().setSize(targetSize);
    }
}
