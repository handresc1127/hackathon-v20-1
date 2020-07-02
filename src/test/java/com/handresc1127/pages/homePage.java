package com.handresc1127.pages;

import com.handresc1127.utils.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class homePage extends BaseTests {

    static String urlV1="https://demo.applitools.com/gridHackathonV1.html";
    static String urlV2="https://demo.applitools.com/gridHackathonV2.html";

    static By mainMenu = By.id("DIV__mainmenu__15");

    static By filterLabel = By.id("ti-filter");
    static By filterBlack = By.id("SPAN__checkmark__107");
    static By filterBtn = By.id("filterBtn");
    static By filterPanel = By.id("filter_col");
    static By filterIcon = By.id("ti-filter");
    static By openFilter = By.id("A__openfilter__206");

    static By searchBarDiv = By.id("DIV__customsear__41");
    static By searchBarInput= By.id("INPUTtext____42");
    static By searchBarIcon= By.id("BUTTONsubmit____43");
    static By searchBtn = By.id("A__btnsearchm__59");

    static By productsGrid = By.id("product_grid");
    static By fistProduct= By.id("product_1");

    static By wishListIcon = By.id("A__wishlist__52");
    static By accessLinkIcon = By.id("A__accesslink__56");
    static By cartIcon = By.id("A__cartbt__49");
    static By cartValue = By.id("STRONG____50");

    static By gridViewBtn = By.id("I__tiviewgrid__202");
    static By listViewBtn = By.id("I__tiviewlist__204");

    public static void navigateToV1(){
        driver.get(urlV1);
    }
    public static void navigateToV2(){
        driver.get(urlV2);
    }

    public static By getGridProducts(){
        return productsGrid;
    } //Modern test locator

    public static void filterForBlack(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(filterBlack));
        driver.findElement(filterBlack).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(filterBtn));
        driver.findElement(filterBtn).click();
    }

    public static void openFilterIfIsNotDisplayed(){
        WebElement filterColumn= driver.findElement(filterPanel);
        if(!filterColumn.isDisplayed()){
            driver.findElement(openFilter).click();
        }
    }

    public static void fistProductDetails(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(fistProduct));
        driver.findElement(fistProduct).click();
    }

    // ==============================
    private static boolean validateDisplayed(By locator, String deviceRule, boolean isElementVisible){
        if(!device.toLowerCase().equals(deviceRule.toLowerCase())){
            isElementVisible=!isElementVisible;
        }
        WebElement element = driver.findElement(locator);
        return hackathonReporter(locator.toString(),element.isDisplayed()==isElementVisible);
    }

    private static boolean validateDisplayed(By locator){
        WebElement element = driver.findElement(locator);
        return hackathonReporter(locator.toString(),element.isDisplayed()==true);
    }
    // ==============================

    public static boolean validateFilterVisible(){
        return validateDisplayed(filterLabel,"laptop",false);
    }

    public static boolean validateSearchBarDivVisible(){
        return validateDisplayed(searchBarDiv,"laptop",true);
    }
    public static boolean validateSearchBarIconVisible(){
        return validateDisplayed(searchBarIcon,"laptop",true);
    }
    public static boolean validateSearchBarInputVisible(){
        return validateDisplayed(searchBarInput,"laptop",true);
    }

    public static boolean validateSearchIconVisible() {
        return validateDisplayed(searchBtn,"laptop",false);
    }

    public static boolean validateWishListVisible() {
        return validateDisplayed(wishListIcon,"laptop",true);
    }

    public static boolean validateAccessLinkVisible() {
        return validateDisplayed(accessLinkIcon);
    }

    public static boolean validateCartVisible() {
        return validateDisplayed(cartIcon);
    }

    public static boolean validateGridViewVisible(){
        return validateDisplayed(gridViewBtn, "laptop", true);
    }
    public static boolean validateListViewVisible(){
        return validateDisplayed(listViewBtn, "laptop", true);
    }

    public static boolean validateMainMenuVisible(){
        return validateDisplayed(mainMenu, "laptop", true);
    }

    public static boolean validateFilterIconVisible(){
        return validateDisplayed(filterIcon, "laptop", false);
    }





    public static boolean validateSearchBarText() {
        //TODO
        //Search over 10,000 shoes!
        String text= driver.findElement(searchBarInput).getAttribute("placeholder");
        System.out.println("Text: "+text);
        return true;
    }

    public static boolean validateCartValue() {
        //TODO
        String value= driver.findElement(cartValue).getText();
        System.out.println("Value: "+value);
        return true;
    }
}
