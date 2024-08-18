package com.thaonth.pages;

import static keywords.WebUI.*;

import static constants.DataConfig.*;
import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;



public class LoginPage extends CommonPage{

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        new WebUI(driver);
    }

    //Elements
   private By headerPageLogin = By.xpath("//h1");
   private By inputEmail = By.xpath("//input[@id='email']");
   private By inputPassword = By.xpath("//input[@id='password']");
   private By linkForgotPassword = By.xpath("//a[normalize-space()='Forgot password ?']");
   private By checkboxRememberMe = By.xpath("//input[@id='remember']");
   private By btnLogin = By.xpath("//button[normalize-space()='Login']");
   private By errorMessage = By.xpath("//span[@data-notify='message']");

    public void verifyLoginPage(){
        openURL(URL);
        Assert.assertTrue(elementIsDisplay(inputEmail),"FAIL!!! Email not display.");
        Assert.assertTrue(elementIsDisplay(inputPassword),"FAIL!!! Password not display.");
        Assert.assertTrue(elementIsDisplay(linkForgotPassword),"FAIL!!! ForgotPassword not display.");
        Assert.assertTrue(checkElementExist(checkboxRememberMe),"FAIL!!! Checkbox Remember Me not display.");
        Assert.assertTrue(elementIsDisplay(btnLogin),"FAIL!!! Login button not display.");
        Assert.assertTrue(elementIsDisplay(headerPageLogin),"FAIL!!! Header Page not display.");
        assertEquals(getElementText(headerPageLogin), "Welcome to Active eCommerce CMS", "FAIL!!! HeaderPage Login not match");
    }

    public DashboardPage loginCMS(String email, String password){
        openURL(URL);
        inputElement(inputEmail, email);
        inputElement(inputPassword, password);
        clickElement(btnLogin);
        return new DashboardPage(driver);
    }

    public void verifyLoginSuccess(){
        waitForPageLoaded();
        verifyCurrentURL("https://cms.anhtester.com/admin");
//        assertEquals(driver.getCurrentUrl(), "https://cms.anhtester.com/admin", "\uD83D\uDC1E Current URL not match");
    }

    public void verifyLoginFail(String expectedMessage){
        Assert.assertTrue(elementIsDisplay(errorMessage), "\uD83D\uDC1E FAIL!!! Error Message not display");
        assertEquals(getElementText(errorMessage), expectedMessage, "\uD83D\uDC1E FAIL!!! The content error message not match");
    }

    public void verifyEmailBlank(String expectedMessage){
       String validateMessage =  getElementAttribute(inputEmail, "validationMessage");
       assertEquals(validateMessage, expectedMessage, "FAIL!!! The content validation message not match");
    }

    public void verifyPasswordBlank(String expectedMessage){
        String validateMessage =  getElementAttribute(inputPassword, "validationMessage");
        assertEquals(validateMessage, expectedMessage, "FAIL!!! The content validation message not match");
    }
}

