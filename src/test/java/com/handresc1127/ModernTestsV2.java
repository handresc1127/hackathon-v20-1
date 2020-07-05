package com.handresc1127;

import com.handresc1127.pages.homePage;
import com.handresc1127.utils.BaseTests;
import org.testng.annotations.Test;

public class ModernTestsV2 extends BaseTests {

    @Test(testName = "Cross-Device Elements Test")
    public void task1()
    {
        homePage.navigateToV2();
        eyesManager.validateWindows("Cross-Device Elements Test");
    }

    @Test(testName = "Filter Results")
    public void task2(){
        homePage.navigateToV2();
        homePage.openFilterIfIsNotDisplayed();
        homePage.filterForBlack();
        eyesManager.validateWindows("Filter Results");
        eyesManager.validateElement(homePage.getGridProducts(),"Product Grid");
    }

    @Test(testName = "Product Details test")
    public void task3(){
        homePage.navigateToV2();
        homePage.openFilterIfIsNotDisplayed();
        homePage.filterForBlack();
        homePage.fistProductDetails();
        eyesManager.validateFullWindows("Product Details test"); //Only task3 says "Take a full-page screenshot"
    }

}
