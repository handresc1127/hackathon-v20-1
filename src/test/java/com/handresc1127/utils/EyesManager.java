package com.handresc1127.utils;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.Eyes;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EyesManager {
    Eyes eyes;
    String appName;
    WebDriver driver;

    public EyesManager(WebDriver driver, String appName) {
        this.driver = driver;
        this.appName = appName;

        eyes = new Eyes();
        eyes.setApiKey(PropertyLoader.getProperty("APPLITOOLS_API_KEY"));
    }

    public void setBatchName(String batchName) {
        eyes.setBatch(new BatchInfo(batchName));
    }

    public void setForceFullScreen(boolean beForce){
        eyes.setForceFullPageScreenshot(beForce);
    }

    public void validateWindows(){
        eyes.open(driver, appName, Thread.currentThread().getStackTrace()[2].getMethodName());
        eyes.checkWindow();
        eyes.close();
    }

    public void validateWindows(String tagWindows){
        eyes.open(driver, appName, Thread.currentThread().getStackTrace()[2].getMethodName());
        eyes.checkWindow(tagWindows);
        eyes.close();
    }

    public void validateWindows(MatchLevel matchLevel){
        eyes.open(driver, appName, Thread.currentThread().getStackTrace()[2].getMethodName());
        Thread x = Thread.currentThread();
        eyes.setMatchLevel(matchLevel);
        eyes.checkWindow();
        eyes.close();
    }

    public void validateElement(By locator,String tagElement){
        eyes.open(driver, appName, Thread.currentThread().getStackTrace()[2].getMethodName());
        eyes.checkElement(locator,tagElement);
        eyes.close();
    }

    public void validateFrame(String frameNameOrId){
        eyes.open(driver, appName, Thread.currentThread().getStackTrace()[2].getMethodName());
        eyes.checkFrame(frameNameOrId);
        eyes.close();
    }

    public void abort(){
        eyes.abortIfNotClosed();
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
            result = IOUtils.toString(process.getInputStream(), "UTF-8");
        }catch (Exception ex){
            return false;
        }
        System.out.println(result);
        if (result != null){
            if (result.contains("Passed")){
                return true;
            }
        }
        return false;
    }
}