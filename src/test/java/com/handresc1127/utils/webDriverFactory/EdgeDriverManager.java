package com.handresc1127.utils.webDriverFactory;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverManager extends DriverManager {

    @Override
    protected void createDriver(String type) {
        EdgeOptions options = new EdgeOptions();
        //options.setCapability("useAutomationExtension", false);
        // options.addArguments("user-agent=foo;bar");
        driver = new EdgeDriver(options);
    }
}
