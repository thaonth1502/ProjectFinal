package com.thaonth.pages;

import static keywords.WebUI.*;

import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonPage {
    private WebDriver driver;

    public CommonPage(WebDriver driver) {
        this.driver = driver;
        new WebUI(driver);
    }

    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    private By menuProducts = By.xpath("//span[normalize-space()='Products']");
    private By menuCategory = By.xpath("//span[normalize-space()='Category']");
    private By menuAddNewProduct = By.xpath("//span[normalize-space()='Add New Product']");


    public DashboardPage clickMenuDashboard(){
        waitForPageLoaded();
        clickElement(menuDashboard);
        return new DashboardPage(driver);
    }

    public CategoryPage clickMenuCategory(){
        waitForPageLoaded();
        clickElement(menuProducts);
        clickElement(menuCategory);
        return new CategoryPage(driver);
    }

    public ProductPage clickMenuAddNewProduct(){
        waitForPageLoaded();
        clickElement(menuProducts);
        clickElement(menuAddNewProduct);
        return new ProductPage(driver);
    }

    public void verifyCurrentURL(String currentURL){
        waitForPageLoaded();
        assertEquals(driver.getCurrentUrl(), currentURL, "FAIL!!! The current URL not match");
    }


}
