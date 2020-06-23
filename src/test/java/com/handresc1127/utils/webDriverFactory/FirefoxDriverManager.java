package com.handresc1127.utils.webDriverFactory;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

public class FirefoxDriverManager extends DriverManager {
    @Override
    protected void createDriver(String type) {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("marionette", true);
        firefoxProfile.setPreference("browser.download.folderList", 2);
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
        firefoxProfile.setPreference("browser.download.dir", System.getProperty("user.dir") + File.separator + "downloads");
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv;audio/wav;audio/vnd.wave;audio/wave;audio/x-wav");

        options.setProfile(firefoxProfile);
        driver = new FirefoxDriver(options);
    }

}
