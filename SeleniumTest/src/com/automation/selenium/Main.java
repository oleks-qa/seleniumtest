// Zhugan
package com.automation.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.junit.*;

public class Main {


    public  By searchField = By.cssSelector("input#lst-ib");
    public  String searchValue = "selenium";
    public  WebDriver webDriver;

    @Before
    public void setUp () {
        webDriver = new FirefoxDriver();
        webDriver.get("https://google.com");
    }

    @Test
    public void searchFieldTest() {
        WebElement searchFieldElement = webDriver.findElement(searchField);
        searchFieldElement.sendKeys(searchValue);
        searchFieldElement.sendKeys(Keys.ENTER);
        String searchFieldValue = searchFieldElement.getAttribute("value");
        Assert.assertTrue("Search field value is incorrect", searchFieldValue.equals(searchValue));
    }
}
