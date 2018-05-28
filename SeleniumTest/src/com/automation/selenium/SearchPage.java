package com.automation.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class SearchPage {

    private By searchField = By.cssSelector("input#lst-ib");
    private By firstResult = By.cssSelector("h3.r a");
    private By headerOfPage = By.cssSelector("h1.entry-title *");
    private By titleElement = By.cssSelector("img#hplogo");
    private By searchResultLink = By.cssSelector("div.g:nth-of-type(2) h3 a");

    private Driver driver;

    public SearchPage(Driver driver){
        this.driver = driver;
    }

    public void setSearchFieldEnter(String text) {
        WebElement searchFieldElement = driver.find(searchField);
        searchFieldElement.sendKeys(text);
        searchFieldElement.sendKeys(Keys.ENTER);
        driver.wait(5);
    }

    public String getSearchFieldValue () {
        return driver.find(searchField).getAttribute("value");
    }

    public void clickFirstResult() {
        driver.find(firstResult).click();
        driver.wait(5);
    }

    public String getPageHeader() {
        return driver.find(headerOfPage).getText();
    }

    public String getTitleAltText() {
        return driver.find(titleElement).getAttribute("alt");
    }

    public String getSecondResultUrl(){
        return driver.find(searchResultLink).getAttribute("href");
    }


}
