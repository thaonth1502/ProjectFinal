package com.thaonth.pages;

import static keywords.WebUI.*;

import keywords.WebUI;
import org.checkerframework.checker.index.qual.UpperBoundUnknown;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CategoryPage extends CommonPage{

    private WebDriver driver;

    public CategoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        new WebUI(driver);
    }

    private By titleCategoryPage = By.xpath("//h1[normalize-space()='All categories']");
    private By btnAddNewCategory = By.xpath("//span[normalize-space()='Add New category']");
    private By inputSearchCategory = By.xpath("//input[@id='search']");
    private By iconDelete = By.xpath("(//a[@title='Delete'])[1]");
    private By iconEdit = By.xpath("(//a[@title='Edit'])[1]");
    private By btnDelete = By.xpath("//div[@class='modal-content']//a[@id='delete-link']");
    private By btnCancel = By.xpath("//div[@class='modal-content']//button[normalize-space()='Cancel']");
    private By contentDeleteConfirm = By.xpath("//div[@class='modal-content']//p");
    private By deleteConfirmDialog = By.xpath("//div[@class='modal-content']");
    private By successMessage = By.xpath("//span[@data-notify='message']");
    private By tableCategory = By.xpath("(//div[@class='card-body']/descendant::tbody)/tr");
    private By firstItemCategoryName = By.xpath("(//div[@class='card-body']/descendant::tbody//tr)[1]/td[2]");



   public void checkCategoryPage(){
       verifyCurrentURL("https://cms.anhtester.com/admin/categories");
       assertTrue(elementIsDisplay(titleCategoryPage),"FAIL!!! Title Category page not display");
       assertEquals(getElementText(titleCategoryPage), "All categories","The content title page not match" );
       assertTrue(elementIsDisplay(btnAddNewCategory),"FAIL!!! Add New Category button not display");
   }

    public AddNewCategoryPage clickButtonAddNewCategory(){
        clickElement(btnAddNewCategory);
        verifyCurrentURL("https://cms.anhtester.com/admin/categories/create");
        return new AddNewCategoryPage(driver);
    }

    public void searchCategory(String categoryName){
        setTextAndKey(inputSearchCategory, categoryName, Keys.ENTER);
    }

    public boolean verifyFistItem(String categoryName){
       waitForPageLoaded();
       boolean check = elementIsDisplay(firstItemCategoryName);
       if(check){
           System.out.println(check);
           assertEquals(getElementText(firstItemCategoryName),categoryName, "FAIL !!! Category Name not match");
       }
       System.out.println(check);
       return check;
    }

    public void deleteCategorySuccess(){
        clickElement(iconDelete);
        elementIsDisplay(deleteConfirmDialog);
        Assert.assertTrue(elementIsDisplay(deleteConfirmDialog), "FAIL!!! Delete Confirmation not display");
        assertEquals(getElementText(contentDeleteConfirm), "Are you sure to delete this?", "FAIL!! The content delete confirmation not correct");
        clickElement(btnDelete);
    }

    public void deleteCategoryUnSuccess(){
        sleep(1);
        int numberBefor = getTotalCategory();
        clickElement(iconDelete);
        Assert.assertTrue(elementIsDisplay(deleteConfirmDialog), "FAIL!!! Delete Confirmation not display");
        assertEquals(getElementText(contentDeleteConfirm), "Are you sure to delete this?", "FAIL!! The content delete confirmation not cor confirmation not correct");
        clickElement(btnCancel);
        sleep(1);
        waitForElementVisible(tableCategory);
        int numberAfter =  getTotalCategory();
        assertEquals(numberAfter, numberBefor, "FAIL!!! Number Category after delete unsuccessful not match before delete");
    }

    public void verifyDeleteCategorySuccess(String message){
        waitForPageLoaded();
        waitForElementVisible(successMessage);
        assertTrue(elementIsDisplay(successMessage), "FAIL!!! Message delete success not display");
        assertEquals(getElementText(successMessage), message, "FAIL !!! The content message delete success not match");
    }

    public int getTotalCategory(){
        List<WebElement> elements = getListWebElement(tableCategory);
        return elements.size();
    }
}
