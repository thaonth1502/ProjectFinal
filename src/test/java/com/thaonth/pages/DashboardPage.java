package com.thaonth.pages;

import static keywords.WebUI.*;

import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage extends CommonPage{
    private WebDriver driver;


    public DashboardPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        new WebUI(driver);
    }
    private By iconAvatar = By.xpath("//span[@class='avatar avatar-sm mr-md-2']");
    private By totalCustomer = By.xpath("(//span[normalize-space()='Total'])[1]/parent::div/following-sibling::div");
    private By totalOrder = By.xpath("(//span[normalize-space()='Total'])[2]/parent::div/following-sibling::div");
    private By totalProductCategory = By.xpath("(//span[normalize-space()='Total'])[3]/parent::div/following-sibling::div");
    private By totalProductBrand = By.xpath("(//span[normalize-space()='Total'])[4]/parent::div/following-sibling::div");
    private By sectionProducts = By.xpath("//h6[normalize-space()='Products']/ancestor::div[@class='card']");
    private By sectionSellers = By.xpath("//h6[normalize-space()='Sellers']/ancestor::div[@class='card']");
    private By sectionCategoryWiseProductSale = By.xpath("//h6[normalize-space()='Category wise product sale']/ancestor::div[@class='card']");
    private By sectionCategoryWiseProductStock = By.xpath("//h6[normalize-space()='Category wise product stock']/ancestor::div[@class='card']");


    public void verifyDashboard(){
        Assert.assertTrue(elementIsDisplay(iconAvatar), "FAIL! Icon avatar not display");
        Assert.assertTrue(elementIsDisplay(sectionProducts),"FAIL! Section Products not display");
        Assert.assertTrue(elementIsDisplay(sectionSellers),"FAIL! Section Sellers not display");
        Assert.assertTrue(elementIsDisplay(sectionCategoryWiseProductSale),"FAIL! Section Category Wise Product Sale not display");
        Assert.assertTrue(elementIsDisplay(sectionCategoryWiseProductStock),"FAIL! Section Category Wise Product Stock not display");
    }

    public void verifyTotalCustomer(String expectedTotal){
        Assert.assertTrue(elementIsDisplay(totalCustomer), "FAIL!! The Total Customer not display");
        assertEquals(getElementText(totalCustomer), expectedTotal, "FAIL !!! The total Customer not match");
    }

    public void verifyTotalOrder(String expectedTotal){
        Assert.assertTrue(elementIsDisplay(totalOrder), "FAIL!! The Total Order not display");
        Assert.assertEquals(getElementText(totalOrder), expectedTotal, "FAIL !!! The total Order not match");
    }

    public void verifyTotalProductCategory(String expectedTotal){
        Assert.assertTrue(elementIsDisplay(totalProductCategory), "FAIL!! The Total Product Category not display");
        assertEquals(getElementText(totalProductCategory), expectedTotal, "FAIL !!! The total Total Product Category not match");
    }

    public void verifyTotalProductBrand(String expectedTotal){
        Assert.assertTrue(elementIsDisplay(totalProductBrand), "FAIL!! The Total Product Brand not display");
        assertEquals(getElementText(totalProductBrand), expectedTotal, "FAIL !!! The total Product Brand not match");
    }

    public int getTotalProductCategory(){
        return Integer.parseInt(getElementText(totalProductCategory));
    }
}
