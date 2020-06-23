package com.handresc1127.pages;

import com.handresc1127.utils.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class homePage extends BaseTests {
    static String urlV1="https://demo.applitools.com/gridHackathonV1.html";
    static String urlV2="https://demo.applitools.com/gridHackathonV2.html";

    static By filterLabel = By.id("ti-filter");



    public static String getUrlV1() {
        return urlV1;
    }

    public static String getUrlV2() {
        return urlV2;
    }

    public static boolean validateFilterVisible(boolean isVisible){
        WebElement filter = driver.findElement(filterLabel);
        return hackathonReporter(filterLabel.toString(),filter.isDisplayed()==isVisible);
    }
}
