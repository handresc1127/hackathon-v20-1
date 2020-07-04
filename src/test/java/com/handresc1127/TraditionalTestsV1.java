package com.handresc1127;

import com.handresc1127.pages.homePage;
import com.handresc1127.pages.detailsPage;
import com.handresc1127.utils.BaseTests;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TraditionalTestsV1 extends BaseTests {


    @Test(testName = "Cross-Device Elements")
    public void task1() {
        homePage.navigateToV1();

        SoftAssert softAssertions = new SoftAssert();

        softAssertions.assertTrue(homePage.validateMainMenuVisible());

        softAssertions.assertTrue(homePage.validateSearchIconVisible());
        softAssertions.assertTrue(homePage.validateSearchBarDivVisible());
        softAssertions.assertTrue(homePage.validateSearchBarIconVisible());
        softAssertions.assertTrue(homePage.validateSearchBarInputVisible());
        softAssertions.assertTrue(homePage.validateSearchBarText());

        softAssertions.assertTrue(homePage.validateAccessLinkVisible());
        softAssertions.assertTrue(homePage.validateWishListVisible());
        softAssertions.assertTrue(homePage.validateCartVisible());
        softAssertions.assertTrue(homePage.validateCartValueVisible());
        softAssertions.assertTrue(homePage.validateCartValue());

        softAssertions.assertTrue(homePage.validateFilterIconVisible());
        softAssertions.assertTrue(homePage.validateGridViewVisible());
        softAssertions.assertTrue(homePage.validateListViewVisible());

        softAssertions.assertTrue(homePage.validateCountElement(9));

        softAssertions.assertTrue(homePage.validateProductHeartVisible());
        softAssertions.assertTrue(homePage.validateProductShuffleVisible());
        softAssertions.assertTrue(homePage.validateProductShoppingVisible());

        softAssertions.assertAll();
    }


    @Test(testName = "Filter Results")
    public void task2(){
        homePage.navigateToV1();
        homePage.openFilterIfIsNotDisplayed();
        homePage.filterForBlack();

        SoftAssert softAssertions = new SoftAssert();

        softAssertions.assertTrue(homePage.validateMainMenuVisible());

        softAssertions.assertTrue(homePage.validateSearchIconVisible());
        softAssertions.assertTrue(homePage.validateSearchBarDivVisible());
        softAssertions.assertTrue(homePage.validateSearchBarIconVisible());
        softAssertions.assertTrue(homePage.validateSearchBarInputVisible());
        softAssertions.assertTrue(homePage.validateSearchBarText());

        softAssertions.assertTrue(homePage.validateAccessLinkVisible());
        softAssertions.assertTrue(homePage.validateWishListVisible());
        softAssertions.assertTrue(homePage.validateCartVisible());
        softAssertions.assertTrue(homePage.validateCartValueVisible());
        softAssertions.assertTrue(homePage.validateCartValue());

        softAssertions.assertTrue(homePage.validateFilterIconVisible());
        softAssertions.assertTrue(homePage.validateGridViewVisible());
        softAssertions.assertTrue(homePage.validateListViewVisible());

        softAssertions.assertTrue(homePage.validateCountElement(2));

        softAssertions.assertTrue(homePage.validateProductHeartVisible());
        softAssertions.assertTrue(homePage.validateProductShuffleVisible());
        softAssertions.assertTrue(homePage.validateProductShoppingVisible());

        softAssertions.assertAll();
    }

    @Test(testName = "Product Details test")
    public void task3(){
        homePage.navigateToV1();
        homePage.openFilterIfIsNotDisplayed();
        homePage.filterForBlack();
        homePage.fistProductDetails();


        SoftAssert softAssertions = new SoftAssert();

        softAssertions.assertTrue(detailsPage.validateShoesNameVisible());
        softAssertions.assertTrue(detailsPage.validateImageShoeVisible());

        softAssertions.assertTrue(detailsPage.validateRankingVisible());
        softAssertions.assertTrue(detailsPage.validateSkuVisible());
        softAssertions.assertTrue(detailsPage.validateCurrentSizeVisible());
        softAssertions.assertTrue(detailsPage.validateQuantityVisible());
        softAssertions.assertTrue(detailsPage.validateAddToCartVisible());
        softAssertions.assertTrue(detailsPage.validatePriceVisible());
        softAssertions.assertTrue(detailsPage.validateOldPriceVisible());
        softAssertions.assertTrue(detailsPage.validateDiscountVisible());

        softAssertions.assertTrue(detailsPage.validateSizeText());
        softAssertions.assertTrue(detailsPage.validateOldPriceStyle());
        softAssertions.assertTrue(detailsPage.validatePriceText());
        softAssertions.assertTrue(detailsPage.validateOldPriceText());
        softAssertions.assertTrue(detailsPage.validateImageContent());
        softAssertions.assertTrue(detailsPage.validateSKUStyle());

        softAssertions.assertAll();
    }
}
