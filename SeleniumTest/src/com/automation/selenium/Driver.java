package com.automation.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class Driver {
    WebDriver webDriver;

    public Driver(BrowserType browserType) {
        if (browserType == BrowserType.FIREFOX) {
            webDriver = new FirefoxDriver();
        }
    }

    public WebElement find(By selector) {
        System.out.println("Searching element" + selector.toString());
        return webDriver.findElement(selector);
    }

    public void get(String url){
        webDriver.get(url);
    }

    public void close(){
        webDriver.quit();
    }

    public void wait(int pause) {
        webDriver.manage().timeouts().implicitlyWait(pause, TimeUnit.SECONDS);
    }
}
