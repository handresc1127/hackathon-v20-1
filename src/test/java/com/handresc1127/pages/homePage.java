package com.handresc1127.pages;

import com.handresc1127.utils.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class homePage extends BaseTests {
    static String urlV1="https://demo.applitools.com/gridHackathonV1.html";
    static String urlV2="https://demo.applitools.com/gridHackathonV2.html";

    static By filterLabel = By.id("ti-filter");
    static By searchBar = By.id("DIV__customsear__41");



    public static void navigateToV1(){
        driver.get(urlV1);
    }
    public static void navigateToV2(){
        driver.get(urlV2);
    }

    public static boolean validateFilterVisible(){
        boolean isFilterVisible=true;
        if(device.toLowerCase().equals("laptop")){
            isFilterVisible=false;
        }
        WebElement filter = driver.findElement(filterLabel);
        return hackathonReporter(filterLabel.toString(),filter.isDisplayed()==isFilterVisible);
    }

    public static boolean validateSearchBarVisible(){
        boolean isSearchVisible=false;
        if(device.toLowerCase().equals("laptop")){
            isSearchVisible=true;
        }
        WebElement search = driver.findElement(searchBar);
        return hackathonReporter(searchBar.toString(),search.isDisplayed()==isSearchVisible);
    }

}
