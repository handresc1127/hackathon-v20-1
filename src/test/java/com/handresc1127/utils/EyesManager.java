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
    private static Eyes eyes;
    private static String appName;
    private static WebDriver driver;
    private static Configuration eyesConfig;
    private static EyesRunner runner=null;
    private static boolean eyesIsOpen=false;
    final private static String apiKeyEnv ="APPLITOOLS_API_KEY"; //APPLITOOLS_API_KEY_PERSONAL

    public void EyesConfig(){
        int concurrentSessions = Integer.parseInt(PropertyLoader.getProperty("applitools.concurrentSessions"));
        runner = new VisualGridRunner(concurrentSessions);
        appName=PropertyLoader.getProperty("applitools.appName");
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
                .setApiKey(PropertyLoader.getProperty(apiKeyEnv))
                .setAppName(appName)
        ;

    }

    public EyesManager() {
        eyesIsOpen=false;
        if(runner==null) EyesConfig();
        if(eyes==null) {
            eyes = new Eyes(runner);
            eyes.setConfiguration(eyesConfig);
            eyes.setBatch(new BatchInfo(PropertyLoader.getProperty("applitools.batchName")));
            eyes.setForceFullPageScreenshot(Boolean.parseBoolean(PropertyLoader.getProperty("applitools.forceFullScreen")));
        }
    }

    /**
     * Mandatory for each method.
     * @param myDriver              WebDriver
     */
    public void setDriver(WebDriver myDriver){
        driver = myDriver;
        eyesIsOpen=false;
    }

    public void openEyes(){
        eyes.open(driver, appName, BaseTests.getTestName());
        eyesIsOpen=true;
    }

    public void closeEyes(){
        eyesIsOpen=false;
        eyes.close();
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

        public void validateFullWindows(String tagWindows){
        if(!eyesIsOpen) openEyes();
        eyes.checkWindow(tagWindows,true); //equal check(tag,Target.window().fully(true));
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
        if (null != eyes) {
            eyes.abortIfNotClosed();
            runner.getAllTestResults(false);
            eyes = null;
            runner=null;
        }
    }

    public Eyes getEyes(){
        return eyes;
    }

    public boolean validatePDF(String filePath) {
        String command = String.format(
                "java -jar resources/ImageTester.jar -k %s -f \"%s\"",
                PropertyLoader.getProperty(apiKeyEnv),
                filePath);
        String result;
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            result = IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8);
        }catch (Exception ex){
            return false;
        }
        if (result != null){
            return result.contains("Passed");
        }
        return false;
    }
}