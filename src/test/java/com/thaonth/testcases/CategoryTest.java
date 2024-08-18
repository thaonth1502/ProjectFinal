package com.thaonth.testcases;

import static constants.DataConfig.*;
import com.thaonth.common.BaseTest;
import com.thaonth.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CategoryTest extends BaseTest {
    static String CATEGORY_NAME = "Category 18082024C1-1";
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CategoryPage categoryPage;
    AddNewCategoryPage createNewCategory;

    @Test
    public void verifyCategoryPage(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMS(EMAIL, PASSWORD);
        categoryPage = dashboardPage.clickMenuCategory();
        categoryPage.checkCategoryPage();
    }

    @Test
    public void deleteCategoryByClickDeleteButtonInConfirmationDelete(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMS(EMAIL, PASSWORD);
        int totalCategoryBefore =  dashboardPage.getTotalProductCategory();
        categoryPage = dashboardPage.clickMenuCategory();
        categoryPage.searchCategory(CATEGORY_NAME);
        Assert.assertTrue(categoryPage.verifyFistItem(CATEGORY_NAME));
        categoryPage.deleteCategorySuccess();
        categoryPage.verifyDeleteCategorySuccess("Category has been deleted successfully");
        categoryPage.searchCategory(CATEGORY_NAME);
        Assert.assertFalse(categoryPage.verifyFistItem(CATEGORY_NAME));
        dashboardPage = categoryPage.clickMenuDashboard();
        int totalCategoryAfter =  dashboardPage.getTotalProductCategory();
        Assert.assertEquals(totalCategoryBefore, totalCategoryAfter + 1, "FAIL!!!! total after delete not match");

    }

    public void deleteCategoryByClickCancelButtonInConfirmationDelete(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMS(EMAIL, PASSWORD);
        int totalCategoryBefore =  dashboardPage.getTotalProductCategory();
        categoryPage = dashboardPage.clickMenuCategory();
        categoryPage.deleteCategoryUnSuccess();
        categoryPage.searchCategory(CATEGORY_NAME);
        Assert.assertTrue(categoryPage.verifyFistItem(CATEGORY_NAME));
        dashboardPage = categoryPage.clickMenuDashboard();
        int totalCategoryAfter =  dashboardPage.getTotalProductCategory();
        Assert.assertEquals(totalCategoryBefore, totalCategoryAfter, "FAIL!!!! total after delete not match");

    }
}
