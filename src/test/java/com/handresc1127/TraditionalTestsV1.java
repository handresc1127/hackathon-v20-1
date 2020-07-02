package com.handresc1127;

import com.handresc1127.pages.homePage;
import com.handresc1127.utils.BaseTests;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TraditionalTestsV1 extends BaseTests {


    @Test(testName = "Cross-Device Elements")
    public void task1() {
        homePage.navigateToV1();

        SoftAssert softAssertions = new SoftAssert();
        softAssertions.assertTrue(homePage.validateFilterVisible());
        softAssertions.assertTrue(homePage.validateSearchIconVisible());
        softAssertions.assertTrue(homePage.validateSearchBarDivVisible());
        softAssertions.assertTrue(homePage.validateSearchBarIconVisible());
        softAssertions.assertTrue(homePage.validateSearchBarInputVisible());

        softAssertions.assertTrue(homePage.validateAccessLinkVisible());
        softAssertions.assertTrue(homePage.validateWishListVisible());
        softAssertions.assertTrue(homePage.validateCartVisible());

        softAssertions.assertTrue(homePage.validateGridViewVisible());
        softAssertions.assertTrue(homePage.validateListViewVisible());

        softAssertions.assertTrue(homePage.validateSearchBarText());
        softAssertions.assertTrue(homePage.validateCartValue());

//TODO

        softAssertions.assertAll();
    }


    @Test(testName = "Filter Results")
    public void task2(){
        homePage.navigateToV1();
        homePage.openFilterIfIsNotDisplayed();
        homePage.filterForBlack();

        SoftAssert softAssertions = new SoftAssert();
        softAssertions.assertTrue(homePage.validateFilterVisible());
        softAssertions.assertTrue(homePage.validateSearchBarDivVisible());
        //TODO
        softAssertions.assertAll();
    }

    @Test(testName = "Product Details test")
    public void task3(){
        homePage.navigateToV1();
        homePage.openFilterIfIsNotDisplayed();
        homePage.filterForBlack();
        homePage.fistProductDetails();
    }
}
