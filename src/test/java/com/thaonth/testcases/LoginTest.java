package com.thaonth.testcases;

import static constants.DataConfig.*;
import com.thaonth.common.BaseTest;
import com.thaonth.pages.DashboardPage;
import com.thaonth.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test
    public void verifyLoginPage(){
        loginPage = new LoginPage(driver);
        loginPage.verifyLoginPage();
    }
    @Test
    public void loginCSMSuccess(){
        loginPage = new LoginPage(driver);
        loginPage.loginCMS(EMAIL, PASSWORD);
        loginPage.verifyLoginSuccess();
    }

    @Test
    public void loginCSMFailWithEmailInvalid(){
        loginPage = new LoginPage(driver);
        loginPage.loginCMS("email1@example.com", PASSWORD);
        loginPage.verifyLoginFail("Invalid login credentials");
    }

    @Test
    public void loginCSMFailWithPasswordInvalid(){
        loginPage = new LoginPage(driver);
        loginPage.loginCMS(EMAIL, "123");
        loginPage.verifyLoginFail("Invalid login credentials");
    }

    @Test
    public void loginCSMFailWithPasswordBlank(){
        loginPage = new LoginPage(driver);
        loginPage.loginCMS(EMAIL, "");
        loginPage.verifyPasswordBlank("Please fill out this field.");
    }

    @Test
    public void loginCSMFailWithEmailBlank(){
        loginPage = new LoginPage(driver);
        loginPage.loginCMS("", PASSWORD);
        loginPage.verifyEmailBlank("Please fill out this field.");
    }
}
