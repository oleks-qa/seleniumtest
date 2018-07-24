import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;
import java.util.function.Function;
import java.util.logging.Level;

public class Driver {
    WebDriver webDriver;
    Log log;
    boolean screenshotEnabled = false;
    boolean logEnabled = false;

    public Driver(BrowserType browserType, String fileName) {


        Settings settings = new Settings();

        Json json = new Json("test_data.json");
        screenshotEnabled = json.getBoolean("screenshot_enabled");

        logEnabled = json.getBoolean("log_enabled");

        log = new Log(this);
        log.setFileName(fileName);

        if (settings.getSetting("BROWSER_TYPE").toLowerCase().equals("firefox")) {
            webDriver = new FirefoxDriver();
        } else System.out.println("Browser not defined");
    }

    public WebElement find(By selector) {
        log.log(Level.INFO, "Searching element: " + selector.toString());
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
