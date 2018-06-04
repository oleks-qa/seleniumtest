import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Driver {
    WebDriver webDriver;
    private static final Logger LOGGER = Logger.getLogger(Driver.class.getName());

    public Driver(BrowserType browserType) {
        try {
            LOGGER.addHandler( new FileHandler("driver.log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Settings settings = new Settings();
        if (settings.getSetting("BROWSER_TYPE").toLowerCase().equals("firefox")) {
            webDriver = new FirefoxDriver();
        } else System.out.println("Browser not defined");
    }

    public WebElement find(By selector) {
        LOGGER.log(Level.INFO, "Searching element: " + selector.toString());
        WebElement element = fluentWait(30, 500, selector);
        return element;
    }
    public WebElement fluentWait(int timeout, int interval, By selector){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(interval))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(selector);
            }
        });
        return element;
    }


    public void get(String url){
        webDriver.get(url);
    }

    public void close(){
        webDriver.quit();
    }
}
