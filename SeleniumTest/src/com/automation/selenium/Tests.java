package com.automation.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Tests {

    // Test data
    public String searchValueSelenium = "selenium";
    public String searchValueWhy = "почему гит такой сложный";
    public String expectedTitleGitHate = "Почему я ненавижу Git или Git не должен быть таким сложным для изучения";
    public String expectedTitleAltGoogle = "Google";

    // Error messages
    public String altTextIncorrect = "Alternative text is incorrect";
    public String searchValueIncorrect = "Search field value is incorrect";

    // WebDriver wrapper
    public Driver driver;


    @Before
    public void setUp () {
        driver = new Driver(BrowserType.Firefox);
        driver.get("https://google.com");
    }

    @Test
    public void searchFieldTest() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.setSearchFieldEnter(searchValueSelenium);
        Assert.assertTrue(searchValueIncorrect, searchPage.getSearchFieldValue().equals(searchValueSelenium));
    }

    @Test
    public void firstResultTest() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.setSearchFieldEnter(searchValueWhy);
        searchPage.clickOnSecondResult();
        String pageHeader = searchPage.getPageHeader();
        Assert.assertEquals(expectedTitleGitHate, pageHeader);
    }

    @Test
    public void altTextTitleTest() {
        SearchPage searchPage = new SearchPage(driver);
        String titleAltText = searchPage.getTitleAltText();
        Assert.assertEquals(expectedTitleAltGoogle, titleAltText);
    }

    @Test
    public void searchResultLinkTest() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.setSearchFieldEnter(searchValueSelenium);
        String searchResultLinkText = searchPage.getSecondResultUrl();
        Assert.assertTrue(searchResultLinkText.toLowerCase().contains(searchValueSelenium));
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
