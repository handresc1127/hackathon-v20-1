package com.handresc1127.TraditionalTestsV1;

import com.handresc1127.pages.homePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Task1 {

    @Test(testName = "Cross-Device Elements")
    public void task1() {
        SoftAssert softAssertions = new SoftAssert();

        homePage.navigateToV1();
        softAssertions.assertTrue(homePage.validateFilterVisible());
        softAssertions.assertTrue(homePage.validateSearchBarVisible());

        softAssertions.assertAll();

    }

}
