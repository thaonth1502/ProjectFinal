package com.thaonth.testcases;

import com.thaonth.common.BaseTest;
import com.thaonth.pages.DashboardPage;
import com.thaonth.pages.LoginPage;
import static constants.DataConfig.*;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test
    public void verifyDashboardPage(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMS(EMAIL, PASSWORD);
        dashboardPage.verifyDashboard();
    }

    @Test
    public void verifyTotalOfSectionDashboard(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMS(EMAIL, PASSWORD);
        dashboardPage.verifyTotalCustomer("234");
        dashboardPage.verifyTotalOrder("753");
        dashboardPage.verifyTotalProductCategory("868");
        dashboardPage.verifyTotalProductBrand("109");
    }
}
