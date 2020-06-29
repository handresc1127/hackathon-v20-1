package com.handresc1127.utils;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.nio.charset.StandardCharsets;

public class EyesManager {
    private Eyes eyes;
    private String appName;
    private WebDriver driver;
    private Configuration eyesConfig;
    private EyesRunner runner;
    private int concurrentSessions = 10;
    private static boolean eyesIsOpen=false;

    public EyesManager(WebDriver driver, String appName) {
        this.driver = driver;
        this.appName = appName;

        runner = new VisualGridRunner(concurrentSessions);
        eyesConfig = (Configuration) new Configuration()
                // Visual Grid configurations
        .addBrowser(1200, 700, BrowserType.CHROME)
        .addBrowser(1200, 700, BrowserType.FIREFOX)
        .addBrowser(1200, 700, BrowserType.EDGE_CHROMIUM)
        .addBrowser(768, 700, BrowserType.CHROME)
        .addBrowser(768, 700, BrowserType.FIREFOX)
        .addBrowser(768, 700, BrowserType.EDGE_CHROMIUM)
        .addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT)
                //  ...other configurations
        .setViewportSize(new RectangleSize(800, 600))
        .setApiKey(PropertyLoader.getProperty("APPLITOOLS_API_KEY"))
        .setAppName(appName)
        ;

        eyes = new Eyes(runner);
        eyes.setConfiguration(eyesConfig);
    }

    public void setBatchName(String batchName) {
        eyes.setBatch(new BatchInfo(batchName));
    }

    public void setForceFullScreen(boolean beForce){
        eyes.setForceFullPageScreenshot(beForce);
    }

    public void openEyes(){
        eyes.open(driver, appName, BaseTests.getTestName());
        eyesIsOpen=true;
    }

    public void closeEyes(){
        eyes.close();
        eyesIsOpen=false;
    }

    public void validateWindows(){
        if(!eyesIsOpen) openEyes();
        eyes.checkWindow();
    }

    public void validateWindows(String tagWindows){
        if(!eyesIsOpen) openEyes();
        eyes.checkWindow(tagWindows);
    }

    public void validateWindows(MatchLevel matchLevel){
        if(!eyesIsOpen) openEyes();
        eyes.setMatchLevel(matchLevel);
        eyes.checkWindow();
    }

    public void validateElement(By locator,String tagElement){
        if(!eyesIsOpen) openEyes();
        eyes.checkElement(locator,tagElement); //equal check(tag, Target.region(selector));
    }

    public void validateFrame(String frameNameOrId){
        if(!eyesIsOpen) openEyes();
        eyes.checkFrame(frameNameOrId);
    }

    public void abort(){
        eyes.abortIfNotClosed();
        runner.getAllTestResults(false);
    }

    public Eyes getEyes(){
        return eyes;
    }

    public boolean validatePDF(String filePath) {
        String command = String.format(
                "java -jar resources/ImageTester.jar -k %s -f \"%s\"",
                PropertyLoader.getProperty("APPLITOOLS_API_KEY"),
                filePath);
        String result="";
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            result = IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8);
        }catch (Exception ex){
            return false;
        }
        System.out.println(result);
        if (result != null){
            return result.contains("Passed");
        }
        return false;
    }
}