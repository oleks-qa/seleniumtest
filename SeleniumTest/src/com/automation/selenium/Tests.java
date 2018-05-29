package com.automation.selenium;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

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
        driver = new Driver(BrowserType.FIREFOX);
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
        searchPage.clickFirstResult();
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
        try {
            File screenshotFile = ((TakesScreenshot) driver.webDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("screenshot.png"));
            driver.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
