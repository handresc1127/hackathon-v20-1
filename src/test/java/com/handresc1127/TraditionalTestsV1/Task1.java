package com.handresc1127.TraditionalTestsV1;

import com.handresc1127.pages.homePage;
import com.handresc1127.utils.BaseTests;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Task1 extends BaseTests {


    @Test(testName = "Cross-Device Elements")
    public void task1() {
        homePage.navigateToV1();

        SoftAssert softAssertions = new SoftAssert();
        softAssertions.assertTrue(homePage.validateFilterVisible());
        softAssertions.assertTrue(homePage.validateSearchBarVisible());
        //TODO
        softAssertions.assertAll();
    }


    @Test
    public void task2(){
        homePage.navigateToV1();
        homePage.openFilterIfIsNotDisplayed();
        homePage.filterForBlack();

        SoftAssert softAssertions = new SoftAssert();
        softAssertions.assertTrue(homePage.validateFilterVisible());
        softAssertions.assertTrue(homePage.validateSearchBarVisible());
        //TODO
        softAssertions.assertAll();
    }
}
