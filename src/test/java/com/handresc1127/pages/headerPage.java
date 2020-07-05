package com.handresc1127.pages;

import com.handresc1127.utils.HackathonReports;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class headerPage extends HackathonReports {
    static final By mainMenu = By.id("DIV__mainmenu__15");
    static final By searchBarDiv = By.id("DIV__customsear__41");
    static final By searchBarInput= By.id("INPUTtext____42");
    static final By searchBarIcon= By.id("BUTTONsubmit____43");
    static final By searchBtn = By.id("A__btnsearchm__59");

    static final By wishListIcon = By.id("A__wishlist__52");
    static final By accessLinkIcon = By.id("A__accesslink__56");
    static final By cartIcon = By.id("A__cartbt__49");
    static final By cartValue = By.id("STRONG____50");

    static final By tools= By.id("UL__toptools__46");
    static final By toolsButton= By.tagName("li");


    public static boolean validateMainMenuVisible(){
        return validateDisplayed(mainMenu, "laptop", true);
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
        if(!methodName.equals("task3")){
            return validateDisplayed(wishListIcon,"laptop",true);
        }else{
            return validateDisplayed(wishListIcon);
        }
    }

    public static boolean validateAccessLinkVisible() {
        return validateDisplayed(accessLinkIcon);
    }

    public static boolean validateCartVisible() {
        return validateDisplayed(cartIcon);
    }

    public static boolean validateCartValueVisible() {
        if(!methodName.equals("task3")){
            return validateDisplayed(cartValue, "mobile", false);
        }else{
            return validateDisplayed(cartValue);
        }
    }
    public static boolean validateCartValue() {
        if(!methodName.equals("task3")){
            return validateText(cartValue,"mobile","","2");
        }else{
            return validateText(cartValue,"2");
        }
    }

    public static boolean validateSearchBarText() {
        return validateAttribute(searchBarInput,"placeholder","Search over 10,000 shoes!");
    }

    public static boolean validateToolsMargin(){
        WebElement toolBox= driver.findElement(tools);
        List<WebElement> toolsList= toolBox.findElements(toolsButton);

        boolean returnValue=true;
        int toolsSize=toolsList.size();
        for (int j=0;j<toolsSize;j++) {
            WebElement toolBtn= toolsList.get(j);
            String currentValue=toolBtn.getCssValue("margin-left");

            String[] locators = toolBtn.toString().split("->");
            StringBuilder locator= new StringBuilder();
            boolean hasLocator=false;
            for(int i=1;i<locators.length;i++){
                if(hasLocator){
                    locator.append(" -> ").append(locators[i].replace("]", "").split(":")[1].trim());
                }else{
                    locator.append(locators[i].replace("]", "").trim());
                }
                hasLocator=true;
            }

            String margin="20px";
            if(j==toolsSize-1){
                if(device.toLowerCase().equals("mobile")){
                    margin="0px";
                }
            }
            if(!hackathonReporter(locator.toString(),currentValue.equals(margin))){
                returnValue= false;
            }
        }
        return returnValue;

    }
}
