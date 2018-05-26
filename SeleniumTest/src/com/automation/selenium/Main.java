// verezhevych
// Zhugan
// orel
package com.automation.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.junit.*;

import java.util.concurrent.TimeUnit;

public class Main {


    public By searchField = By.cssSelector("input#lst-ib");
    public By searchFieldGmailLink = By.cssSelector("div.gb_Q:nth-child(1) > a:nth-child(1)");
    public By firstResult = By.cssSelector("h3.r a");
    public By headerOfPage = By.cssSelector("h1.entry-title *");
    public String searchValue = "selenium";
    public String searchValue2 = "почему гит такой сложный";
    public String titleOfTestPage = "Почему я ненавижу Git или Git не должен быть таким сложным для изучения";
    public WebDriver webDriver;

    public  By titleElement = By.cssSelector("img#hplogo");
    public By searchResultLink = By.cssSelector("div.g:nth-of-type(2) h3 a");

    @Before
    public void setUp () {
        webDriver = new FirefoxDriver();
        webDriver.get("https://google.com");
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
    public void firstResultTest() {
        search(searchValue2);
        clickOnFirstResult();
        WebElement headerOfPageElement = webDriver.findElement(headerOfPage);
        String searchFieldValue = headerOfPageElement.getText();
        Assert.assertTrue("Почему я ненавижу Git или Git не должен быть таким сложным для изучения", searchFieldValue.equals(titleOfTestPage));
    }

    public void search(String text) {
        WebElement searchFieldElement = webDriver.findElement(searchField);
        searchFieldElement.sendKeys(text);
        searchFieldElement.sendKeys(Keys.ENTER);
    }

    public void clickOnFirstResult() {
        WebElement firstResultElement = webDriver.findElement(firstResult);
        firstResultElement.click();
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

    @Test
    public void searchGmailLinkTest() {
        WebElement searchGmailElement = webDriver.findElement(searchFieldGmailLink);
        if (searchGmailElement.isDisplayed()) {
            System.out.println("Gmail link has been found. Test PASSED.");
        } else {
            System.out.println("Gmail link is not found. Test FAILED.");
        }
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
