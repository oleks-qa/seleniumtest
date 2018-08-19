package com.automation.tests.Listener;

import com.automation.driver.Driver;
import com.automation.driver.Screenshot;
import org.testng.*;

public class Listener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Test started");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Driver driver = (Driver) iTestResult.getAttribute("driver");
        Screenshot screenshot = new Screenshot(driver, iTestResult.getTestName());
        screenshot.takeScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
