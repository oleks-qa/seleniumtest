package com.automation.driver;
import com.automation.driver.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class Screenshot {
    private TakesScreenshot scr;
    private String filename;
    private boolean screenshotEnabled = false;

    public Screenshot(Driver driver, String filename){
        screenshotEnabled = driver.screenshotEnabled;
        this.filename = filename;
        scr = ((TakesScreenshot) driver.webDriver);
    }

    public void takeScreenshot() {
        if  (!screenshotEnabled) return;
        try {
            File screenshotFile = scr.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File(filename + ".png"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void setFileName(String filename)  {
        this.filename = filename;
    }
}
