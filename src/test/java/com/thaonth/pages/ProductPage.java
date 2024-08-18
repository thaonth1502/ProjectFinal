package com.thaonth.pages;

import static keywords.WebUI.*;
import keywords.WebUI;
import org.openqa.selenium.WebDriver;

public class ProductPage extends CommonPage{

    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        new WebUI(driver);
    }
}
