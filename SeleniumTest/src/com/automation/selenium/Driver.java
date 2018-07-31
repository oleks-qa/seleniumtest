import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.function.Function;
import java.util.logging.Level;

public class Driver {
    WebDriver webDriver;
    Log log;
    boolean screenshotEnabled = false;

    public Driver(String fileName) {


        Settings settings = new Settings();

        // reading properties
        Json json = new Json("test_data.json");
        screenshotEnabled = json.getBoolean("screenshot_enabled");
        BrowserType browserType = BrowserType.valueOf(settings.getSetting("BROWSER_TYPE").toUpperCase());
        String hubUrl = settings.getSetting("HUB_URL");
        boolean isRemote = Boolean.parseBoolean(settings.getSetting("IS_REMOTE"));
        boolean logEnabled = json.getBoolean("log_enabled");

        log = Log.getInstance(logEnabled);
        log.setFileName(fileName);

        Capabilities capabilities = null;
        Class<?> webDriverClass = WebDriver.class;
        switch (browserType) {
            case FIREFOX:
                capabilities = new FirefoxOptions();
                webDriverClass = FirefoxDriver.class;
                break;
            case CHROME:
                capabilities = new ChromeOptions();
                webDriverClass = ChromeDriver.class;
                break;
            case IE:
                capabilities = new InternetExplorerOptions();
                webDriverClass = InternetExplorerDriver.class;
                break;
            case SAFARI:
                capabilities = new SafariOptions();
                webDriverClass = SafariDriver.class;
                break;
            default:
                log.log(Level.SEVERE, "Incorrect browser");
                break;
        }
        if (isRemote) {
            try {
                webDriver = new RemoteWebDriver(new URL(hubUrl), capabilities);
            } catch (MalformedURLException e) {
                log.log(Level.SEVERE, "Incorrect selenium hub URL");
            }
        } else {
            try {
                webDriver = (WebDriver) webDriverClass.getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public WebElement find(By selector) {
        log.log(Level.INFO, "Searching element: " + selector.toString());
        WebElement element = fluentWait(30, 500, selector);
        return element;
    }

    public void execJs(String jsCode) {
        ((JavascriptExecutor) webDriver).executeScript(jsCode);
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
