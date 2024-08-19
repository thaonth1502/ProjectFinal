package com.thaonth.testcases;

import static constants.DataConfig.*;
import com.thaonth.common.BaseTest;
import com.thaonth.pages.AddNewCategoryPage;
import com.thaonth.pages.CategoryPage;
import com.thaonth.pages.DashboardPage;
import com.thaonth.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddNewCategoryTest extends BaseTest {
    static String NAME_CATEGORY = "Category 18082024C1";
    static String PARENT_CATEGORY = "No Parent";
//    static String CHILD_CATEGORY = "Category 18082024C3-1";
    static String ORDERING_NO = "1";
    static String TYPE = "Digital";
    static String META_TITLE = NAME_CATEGORY + "Meta title";
    static String META_DESCRIPTION = NAME_CATEGORY + " Meta Description";
    static String ATTRIBUTE = "Quality";
    static String BANNER_NAME = "JORDAN1";
    static String ICON_NAME = "jordanLogo";

    LoginPage loginPage;
    DashboardPage dashboardPage;
    CategoryPage categoryPage;
    AddNewCategoryPage createNewCategory;


    @Test
//    @Parameters({"nameCategory", "parentCategory", "orderingNo", "type", "metaTitle", "meteDescription", "attribute"})
    public void createNewParentCategory(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMS(EMAIL, PASSWORD);
        int totalProductCategoryBefore =  dashboardPage.getTotalProductCategory();
        categoryPage = dashboardPage.clickMenuCategory();
        createNewCategory = categoryPage.clickButtonAddNewCategory();
        createNewCategory.inputDataToFieldsInAddNewCategory( NAME_CATEGORY,
                                                                PARENT_CATEGORY,
                                                                ORDERING_NO,
                                                                TYPE,
                                                                META_TITLE,
                                                                META_DESCRIPTION,
                                                                ATTRIBUTE);
        createNewCategory.attImageBanner(BANNER_NAME);
        createNewCategory.attImageIcon(ICON_NAME);
        createNewCategory.clickButtonSave();
        createNewCategory.verifyCreateCategorySuccess("Category has been inserted successfully");
        categoryPage.searchCategory(NAME_CATEGORY);
        Assert.assertTrue(categoryPage.verifyFistItem(NAME_CATEGORY));
        dashboardPage = createNewCategory.clickMenuDashboard();
        int totalProductCategoryAfter =  dashboardPage.getTotalProductCategory();
        Assert.assertEquals(totalProductCategoryBefore + 1, totalProductCategoryAfter, "Total Product Category before and after not match");
    }

    @Test
    public void verifyCategoryNameInParentCategoryField(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMS(EMAIL, PASSWORD);
        categoryPage = dashboardPage.clickMenuCategory();
        createNewCategory = categoryPage.clickButtonAddNewCategory();
        Assert.assertTrue(createNewCategory.checkCategoryNameExistInParentCategory(NAME_CATEGORY),"Create Category not success");
    }
}
