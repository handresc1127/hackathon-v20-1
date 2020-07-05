package com.handresc1127.pages;

import com.handresc1127.utils.HackathonReports;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class homePage extends HackathonReports {

    static final String urlV1="https://demo.applitools.com/gridHackathonV1.html";
    static final String urlV2="https://demo.applitools.com/gridHackathonV2.html";

    static final By filterBlack = By.id("SPAN__checkmark__107");
    static final By filterBtn = By.id("filterBtn");
    static final By filterPanel = By.id("filter_col");
    static final By filterIcon = By.id("ti-filter");

    static final By productsGrid = By.id("product_grid");
    static final By fistProduct= By.id("product_1");

    static final By gridViewBtn = By.className("ti-view-grid");
    static final By listViewBtn = By.className("ti-view-list");

    static final By productOnGrid = By.cssSelector("#product_grid .grid_item");
    static final By productHeart = By.cssSelector(".ti-heart");
    static final By productShuffle = By.cssSelector(".ti-control-shuffle");
    static final By productShopping = By.cssSelector(".ti-shopping-cart");


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
            driver.findElement(filterIcon).click();
        }
    }

    public static void fistProductDetails(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(fistProduct));
        driver.findElement(fistProduct).click();
    }

    // ==============================
    public static boolean validateParentVisible(By locatorPattern, By locatorChild, String deviceRule){
        List<WebElement> products= driver.findElements(locatorPattern);
        boolean returnValue=true;
        for (WebElement product:products) {
            WebElement heart= product.findElement(locatorChild);
            if(validateDisplayed(heart, deviceRule, false)){
                returnValue= false;
            }
        }
        return returnValue;
    }
    // ==============================
    public static boolean validateGridViewVisible(){
        return validateDisplayed(gridViewBtn, "laptop", true);
    }
    public static boolean validateListViewVisible(){
        return validateDisplayed(listViewBtn, "laptop", true);
    }
    public static boolean validateFilterIconVisible(){
        return validateDisplayed(filterIcon, "laptop", false);
    }

    public static boolean validateCountElement(int sizeExpected){
        List<WebElement> products= driver.findElements(productOnGrid);
        return hackathonReporter(productOnGrid.toString(),products.size()==sizeExpected);
    }

    public static boolean validateProductHeartVisible(){
        return validateParentVisible(productOnGrid,productHeart,"laptop");
    }
    public static boolean validateProductShuffleVisible(){
        return validateParentVisible(productOnGrid,productShuffle,"laptop");
    }
    public static boolean validateProductShoppingVisible(){
        return validateParentVisible(productOnGrid,productShopping,"laptop");
    }

}
