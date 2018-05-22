// kucherenko
package com.automation.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.junit.*;

public class Main {

    public By searchField = By.cssSelector("input#lst-ib");
    public WebDriver webDriver;
    public By luckyButton = By.cssSelector("center:nth-child(1) > input:nth-child(2)");
    public String searchValue = "selenium";

    @Before
    public void setUp () {
        webDriver = new FirefoxDriver();
        webDriver.get("https://google.com");
    }

   @Test
        public void searchFieldTest(){

            WebElement searchFieldElement = webDriver.findElement(searchField);
            searchFieldElement.sendKeys(searchValue);
            searchFieldElement.sendKeys(Keys.ENTER);
            String searchFieldValue = searchFieldElement.getAttribute("value");
            Assert.assertTrue("Search field value is incorrect", searchFieldValue.equals(searchValue));
        }

    @Test
        public  void selectFirstElemnOnPage() {
            WebElement luckyButtonElement = webDriver.findElement(luckyButton);
            luckyButtonElement.click();
            String url = webDriver.getCurrentUrl();
            Assert.assertFalse("Url has not changed", url.equals("https://google.com"));

    }

}
