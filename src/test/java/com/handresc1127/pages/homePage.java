package com.handresc1127.pages;

import com.handresc1127.utils.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class homePage extends BaseTests {

    static String urlV1="https://demo.applitools.com/gridHackathonV1.html";
    static String urlV2="https://demo.applitools.com/gridHackathonV2.html";

    static By mainMenu = By.id("DIV__mainmenu__15");

    static By filterLabel = By.id("SPAN____208");
    static By filterBlack = By.id("SPAN__checkmark__107");
    static By filterBtn = By.id("filterBtn");
    static By filterPanel = By.id("filter_col");
    static By filterIcon = By.id("ti-filter");

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

    static By gridViewBtn = By.className("ti-view-grid");
    static By listViewBtn = By.className("ti-view-list");

    static By productOnGrid = By.cssSelector("#product_grid .grid_item");
    static By productHeart = By.cssSelector(".ti-heart");
    static By productShuffle = By.cssSelector(".ti-control-shuffle");
    static By productShopping = By.cssSelector(".ti-shopping-cart");

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
    private static boolean validateDisplayed(By locator, String deviceRule, boolean isElementVisible){
        if(!device.toLowerCase().equals(deviceRule.toLowerCase())){
            isElementVisible=!isElementVisible;
        }
        WebElement element = driver.findElement(locator);
        return hackathonReporter(locator.toString(),element.isDisplayed()==isElementVisible);
    }

    private static boolean validateDisplayed(WebElement element, String deviceRule, boolean isElementVisible){
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

    private static boolean validateEnabled(WebElement element, Boolean isEnabled){
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

    private static boolean validateDisplayed(By locator){
        WebElement element = driver.findElement(locator);
        return hackathonReporter(locator.toString(),element.isDisplayed()==true);
    }

    public static boolean validateParentVisible(By locatorPattern, By locatorChild, String deviceRule){
        List<WebElement> products= driver.findElements(locatorPattern);
        for (WebElement product:products) {
            WebElement heart= product.findElement(locatorChild);
            if(!validateDisplayed(heart, deviceRule, false)){
                return false;
            }
        }
        return true;
    }

    private static boolean validateAttribute(By locator,String attribute, String expectedValue){
        String currentValue= driver.findElement(locator).getAttribute(attribute);
        return hackathonReporter(locator.toString(),currentValue.equals(expectedValue));
    }

    private static boolean validateText(By locator, String deviceRule, String valueDevice, String valueNoDevice){
        String currentValue=driver.findElement(locator).getText();
        if(device.toLowerCase().equals(deviceRule.toLowerCase())){
            return hackathonReporter(locator.toString(),currentValue.equals(valueDevice));
        }else{
            return hackathonReporter(locator.toString(),currentValue.equals(valueNoDevice));
        }
    }
    // ==============================

    public static boolean validateFilterLabelVisible(){
        System.out.println(device+" displayed "+driver.findElement(filterLabel).isDisplayed());
        System.out.println(device+" enabled "+driver.findElement(filterLabel).isEnabled());
        System.out.println(device+" selected "+driver.findElement(filterLabel).isSelected());
        System.out.println(device+" location "+driver.findElement(filterLabel).getLocation());
        System.out.println(device+" display "+driver.findElement(filterLabel).getAttribute("display"));
        return validateDisplayed(filterLabel,"tablet",true);
    }

    public static boolean validateSearchBarDivVisible(){
        return validateDisplayed(searchBarDiv,"mobile",false);
    }
    public static boolean validateSearchBarIconVisible(){
        return validateDisplayed(searchBarIcon,"mobile",false);
    }
    public static boolean validateSearchBarInputVisible(){
        return validateDisplayed(searchBarInput,"mobile",false);
    }

    public static boolean validateSearchIconVisible() {
        return validateDisplayed(searchBtn,"mobile",true);
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

    public static boolean validateCartValueVisible() {
        return validateDisplayed(cartValue, "mobile", false);
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
        return validateAttribute(searchBarInput,"placeholder","Search over 10,000 shoes!");
    }

    public static boolean validateCartValue() {
        return validateText(cartValue,"mobile","","2");
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
