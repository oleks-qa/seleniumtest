// kucherenko
package com.automation.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;

public class Main {

    public static void main(String[] args) {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://google.com");
        webDriver.findElement(By.id("lst-ib")).sendKeys("selenium");
        webDriver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
    }
}
