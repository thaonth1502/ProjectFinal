package com.thaonth.pages;

import static keywords.WebUI.*;
import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddNewCategoryPage extends CommonPage{

    WebDriver driver;

    public AddNewCategoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        new WebUI(driver);
    }

    private By headerCreateNewCategory = By.xpath("//h5[normalize-space()='Category Information']");
    private By inputName = By.xpath("//input[@id='name']");
    private By btnSave = By.xpath("//button[normalize-space()='Save']");
    private By dropdownParentCategory = By.xpath("//label[normalize-space()='Parent Category']/following-sibling::div//button");
    private By inputOrderingNumber = By.xpath("//input[@id='order_level']");
    private By dropdownType = By.xpath("//select[@name='digital']");
    private By attachBanner = By.xpath("(//div[@class = 'input-group' and @data-type = 'image'])[1]");
    private By inputSearchFile = By.xpath("//input[@placeholder='Search your files']");
    private By btnAddFiles = By.xpath("//button[normalize-space()='Add Files']");
    private By attachIcon = By.xpath("(//div[@class = 'input-group' and @data-type = 'image'])[2]");
    private By inputMetaTitle = By.xpath("//input[@name='meta_title']");
    private By textAreaMetaDescription = By.xpath("//textarea[@name='meta_description']");
    private By dropdownFilteringAttributes = By.xpath("//label[normalize-space()='Filtering Attributes']/following-sibling::div//button");
    private By inputSearchDropdown = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By successMessage = By.xpath("//span[@data-notify='message']");


    public boolean checkCategoryNameExistInParentCategory(String parentCat){
        clickElement(dropdownParentCategory);
        inputElement(inputSearchDropdown, parentCat);
        By item = By.xpath("//span[normalize-space()='"+parentCat+"']");
        if (elementIsDisplay(item)) {
            return true;
        } else return false;
    }
    public void inputDataToFieldsInAddNewCategory(String name, String parentCat, String orderingNo, String type, String metaTitle, String metaDescription, String attribute){
        inputElement(inputName, name);
        clickElement(dropdownParentCategory);
        setTextAndKey(inputSearchDropdown, parentCat, Keys.ENTER);
        inputElement(inputOrderingNumber, orderingNo);
//        Select select = new Select(getWebElement(dropdownType));
//        select.selectByVisibleText(type);
        selectVisibleText(dropdownType, type);
        inputElement(inputMetaTitle, metaTitle);
        inputElement(textAreaMetaDescription, metaDescription);
        clickElement(dropdownFilteringAttributes);
        setTextAndKey(inputSearchDropdown, attribute, Keys.ENTER);
    }

    public void attImageBanner(String bannerName){
        clickElement(attachBanner);
        sleep(1);
        inputElement(inputSearchFile, bannerName);
        sleep(1);
        String image = "//div[contains(@title,'"+bannerName+"')]";
        System.out.println("Image: " + image);
        waitForElementVisible(By.xpath(image));
        sleep(1);
        clickElement(image);
        sleep(1);
        clickElement(btnAddFiles);
        sleep(1);
    }

    public void attImageIcon(String iconName){
        clickElement(attachIcon);
        sleep(1);
        inputElement(inputSearchFile, iconName);
        sleep(1);
        String image = "//div[contains(@title,'"+iconName+"')]";
        waitForElementVisible(By.xpath(image));
        clickElement(image);
        sleep(1);
        clickElement(btnAddFiles);
        sleep(1);
    }

    public void clickButtonSave(){
        moveToElement(btnSave);
        clickElement(btnSave);
        sleep(1);
    }

    public void verifyCreateCategorySuccess(String expectedMessage){
        waitForElementVisible(successMessage);
        assertTrue(elementIsDisplay(successMessage), "FAIL!!! The message success not display");
        assertEquals(getElementText(successMessage), expectedMessage, "FAIL !! The content message is match");
    }

}
