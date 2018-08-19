package com.automation.pages;

import com.automation.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public abstract class Page {
    private By searchField = By.cssSelector("input#lst-ib");
    Driver driver;
    public Page(Driver driver){
        this.driver = driver;
    }

    public void setSearchFieldEnter(String text) {
        WebElement searchFieldElement = driver.find(searchField);
        searchFieldElement.sendKeys(text);
        searchFieldElement.sendKeys(Keys.ENTER);
    }

    public String getSearchFieldValue () {
        return driver.find(searchField).getAttribute("value");
    }
}
