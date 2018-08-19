package com.automation.pages;

import com.automation.driver.Driver;
import org.openqa.selenium.By;

public class SearchPage extends Page {

    private By firstResult = By.cssSelector("h3.r a");
    private By headerOfPage = By.cssSelector("h1.entry-title *");
    private By titleElement = By.cssSelector("img#hplogo");
    private By searchResultLink = By.cssSelector("div.g:nth-of-type(2) h3 a");


    public SearchPage(Driver driver){
        super(driver);
    }


    public void clickFirstResult() {
        driver.find(firstResult).click();
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
