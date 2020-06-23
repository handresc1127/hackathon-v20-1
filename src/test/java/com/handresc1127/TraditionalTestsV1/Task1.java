package com.handresc1127.TraditionalTestsV1;

import com.handresc1127.pages.homePage;
import com.handresc1127.utils.BaseTests;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Task1 extends BaseTests {

    @Test
    public void dataDrivenTest() {
        SoftAssert softAssertions = new SoftAssert();
        driver.get(homePage.getUrlV1());

        softAssertions.assertTrue(homePage.validateFilterVisible(true));

        softAssertions.assertAll();

    }

}
