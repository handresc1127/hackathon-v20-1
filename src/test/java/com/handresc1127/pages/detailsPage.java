package com.handresc1127.pages;

import com.handresc1127.utils.HackathonReports;
import org.openqa.selenium.By;

public class detailsPage extends HackathonReports {

    static final By shoesName= By.id("shoe_name");
    static final By shoeImage= By.id("shoe_img");
    static final By ranking = By.id("SPAN__rating__76");
    static final By sku = By.id("SMALL____84");
    static final By currentSize = By.className("current");
    static final By quantity = By.id("quantity_1");
    static final By btnAddToCart = By.id("A__btn__114");
    static final By divAddToCart = By.id("DIV__btnaddtoca__113");
    static final By price = By.id("new_price");
    static final By oldPrice = By.id("old_price");
    static final By discount= By.id("discount");

    public static boolean validateShoesNameVisible(){
        return validateDisplayed(shoesName);
    }

    public static boolean validateImageShoeVisible(){
        return validateDisplayed(shoeImage);
    }

    public static boolean validateRankingVisible(){
        return validateDisplayed(ranking);
    }
    public static boolean validateSkuVisible(){
        return validateDisplayed(sku);
    }
    public static boolean validateCurrentSizeVisible(){
        return validateDisplayed(currentSize);
    }
    public static boolean validateQuantityVisible(){
        return validateDisplayed(quantity);
    }
    public static boolean validateAddToCartVisible(){
        return validateDisplayed(btnAddToCart);
    }

    public static boolean validateAddToCartMargin(){
        String currentValue=driver.findElement(divAddToCart).getCssValue("margin-top");
        return hackathonReporter(divAddToCart.toString(),currentValue.equals("0px"));
    }

    public static boolean validatePriceVisible(){
        return validateDisplayed(price);
    }
    public static boolean validateOldPriceVisible(){
        return validateDisplayed(oldPrice);
    }
    public static boolean validateDiscountVisible(){
        return validateDisplayed(discount);
    }

    public static boolean validateSizeText(){
        return validateText(currentSize,"Small (S)");
    }

    public static boolean validateOldPriceStyle(){
        String currentValue=driver.findElement(oldPrice).getCssValue("text-decoration");
        return hackathonReporter(oldPrice.toString(),currentValue.contains("line-through"));
    }

    public static boolean validateSKUStyle(){
        String currentValue=driver.findElement(sku).getCssValue("color");
        return hackathonReporter(sku.toString(),currentValue.contains("(68, 68, 68"));
    }

    public static boolean validatePriceText(){
        return validateText(price,"$33.00");
    }

    public static boolean validateOldPriceText(){
        return validateText(oldPrice,"$48.00");
    }

    public static boolean validateImageContent(){
        return validateAttributeContains(shoeImage,"style","grid/img/products/shoes/1.jpg");
    }

}
