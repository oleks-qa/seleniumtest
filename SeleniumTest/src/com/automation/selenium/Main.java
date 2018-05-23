// Zhugan
package com.automation.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.junit.*;

import java.util.concurrent.TimeUnit;

public class Main {


    public  By titleElement = By.cssSelector("img#hplogo");
    public By searchField = By.cssSelector("input#lst-ib");
    public By searchResultLink = By.cssSelector("div.g:nth-of-type(2) h3 a");
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

    @Test
    public void altTextTitleTest() {
        WebElement searchFieldElement = webDriver.findElement(titleElement);
        String titleElementTextValue = searchFieldElement.getAttribute("alt");
        Assert.assertTrue("Alternative text is incorrect", titleElementTextValue.equals("Google"));
    }

    @Test
    public void searchResultLinkTest() {
        WebElement searchFieldElement = webDriver.findElement(searchField);
        searchFieldElement.sendKeys(searchValue);
        searchFieldElement.sendKeys(Keys.ENTER);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String searchResultLinkText = webDriver.findElement(searchResultLink).getAttribute("href");
        Assert.assertTrue(searchResultLinkText.toLowerCase().contains(searchValue));
    }


    @After
    public void stop () {
        webDriver.quit();
    }
}
