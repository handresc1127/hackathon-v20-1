package com.handresc1127.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class HackathonReports extends BaseTests{

    private static final Logger LOGGER = LoggerFactory.getLogger(HackathonReports.class);

    public static boolean hackathonReporter(String domLocator, boolean comparisonResult) {
        StackTraceElement[] stack= Thread.currentThread().getStackTrace();
        String last="";
        String testName="";
        String lastClassName="";
        for (StackTraceElement trace:stack){
            String className=trace.getClassName();
            if(className.contains("com.handresc1127")){
                lastClassName=className;
                testName=last;
                last=trace.getMethodName();
            }
        }

        String fileName=lastClassName.replace("com.handresc1127.","").replace("Tests","-")+"-TestResults.txt";

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write("Task: " + methodName.replace("task","")
                    + ", Test Name: " + testName    //normalize(testName+", ",32)
                    + ", DOM Locator: " + domLocator  //normalize(domLocator+", ",64)
                    + ", Browser: " +    browser      //  normalize(browser+", ",9)
                    + ", Viewport: " +   viewport     // normalize(viewport+", ",10)
                    + ", Device: " +     device       //normalize(device+", ",8)
                    + ", Status: " +    (comparisonResult ? "Pass" : "Fail"));
            writer.newLine();
        }catch(Exception e){
            LOGGER.error("Error writing to report file "+e);
        }
        return comparisonResult;
    }

    static String normalize(String text, int maxLen){
        int len=text.length();
        StringBuilder textBuilder = new StringBuilder(text);
        for (int i = 0; i<(maxLen-len); i++){
            textBuilder.append(" ");
        }
        text = textBuilder.toString();
        return text;
    }

    // ==============================
    protected static boolean validateDisplayed(By locator, boolean isElementVisible){
        WebElement element = driver.findElement(locator);
        return hackathonReporter(locator.toString(),element.isDisplayed()==isElementVisible);
    }

    protected static boolean validateDisplayed(By locator){
        return validateDisplayed(locator,true);
    }

    protected static boolean validateDisplayed(By locator, String deviceRule, boolean isElementVisible){
        if(!device.toLowerCase().equals(deviceRule.toLowerCase())){
            isElementVisible=!isElementVisible;
        }
        return validateDisplayed(locator,isElementVisible);
    }

    protected static boolean validateDisplayed(WebElement element, String deviceRule, boolean isElementVisible){
        if(!device.toLowerCase().equals(deviceRule.toLowerCase())){
            isElementVisible=!isElementVisible;
        }
        String[] locators = element.toString().split("->");
        String locator="";
        boolean hasLocator=false;
        for(int i=1;i<locators.length;i++){
            if(hasLocator){
                locator+=" -> "+locators[i].replace("]","").split(":")[1].trim();
            }else{
                locator+=locators[i].replace("]","").trim();
            }
            hasLocator=true;
        }
        return hackathonReporter(locator,element.isDisplayed()==isElementVisible);
    }

    protected static boolean validateAttribute(By locator,String attribute, String expectedValue){
        String currentValue= driver.findElement(locator).getAttribute(attribute);
        return hackathonReporter(locator.toString(),currentValue.equals(expectedValue));
    }

    protected static boolean validateAttributeContains(By locator,String attribute, String expectedValue){
        String currentValue= driver.findElement(locator).getAttribute(attribute);
        return hackathonReporter(locator.toString(),currentValue.contains(expectedValue));
    }

    protected static boolean validateText(By locator, String valueExpected){
        String currentValue=driver.findElement(locator).getText();
        return hackathonReporter(locator.toString(),currentValue.equals(valueExpected));
    }

    protected static boolean validateText(By locator, String deviceRule, String valueDevice, String valueNoDevice){
        String valueExpected="";
        if(device.toLowerCase().equals(deviceRule.toLowerCase())){
            valueExpected=valueDevice;
        }else{
            valueExpected=valueNoDevice;
        }
        return validateText(locator,valueExpected);
    }


    protected static boolean validateParentVisible(By locatorPattern, By locatorChild, String deviceRule){
        List<WebElement> products= driver.findElements(locatorPattern);
        boolean returnValue=true;
        for (WebElement product:products) {
            WebElement heart= product.findElement(locatorChild);
            if(!validateDisplayed(heart, deviceRule, false)){
                returnValue= false;
            }
        }
        return returnValue;
    }

    protected static boolean validateEnabled(WebElement element, Boolean isEnabled){
        String[] locators = element.toString().split("->");
        String locator="";
        boolean hasLocator=false;
        for(int i=1;i<locators.length;i++){
            if(hasLocator){
                locator+=" -> "+locators[i].replace("]","").split(":")[1].trim();
            }else{
                locator+=locators[i].replace("]","").trim();
            }
            hasLocator=true;
        }
        return hackathonReporter(locator,element.isEnabled()==isEnabled);
    }
}
