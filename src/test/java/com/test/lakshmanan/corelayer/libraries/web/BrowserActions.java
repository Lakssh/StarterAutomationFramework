/*--------------------------------------------------------------------------------------------------------------
        * Purpose  :   class which holds all the methods to perform action on a browser
        * author   :   Lakshmanan Chellappan
--------------------------------------------------------------------------------------------------------------*/
package com.test.lakshmanan.corelayer.libraries.web;

import com.test.lakshmanan.corelayer.FrameworkWrapper;
import com.test.lakshmanan.corelayer.logging.LoggingUtilities;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BrowserActions {

    private final WebDriver driver;
    LoggingUtilities logger = FrameworkWrapper.logger();
    private static final BrowserActions instance = new BrowserActions(FrameworkWrapper.getWebDriver());

    // Singleton pattern to get the browser action instance
    private BrowserActions(WebDriver driver) {
        this.driver = driver;
    }
    public static BrowserActions getInstance() {
        return instance;
    }

    /* Function to return all the web element actions by passing locator
       @param  : locator as By class
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public WebElementActions element(By locator) {
        return new WebElementActions(this.driver, locator);
    }

    /* Function to return all the web element actions without passing locator
       @param  : locator as By class
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public WebElementActions element() {
        return new WebElementActions(this.driver);
    }


    /* Function to return all the verification methods
       @param  : n/a
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public Verify verify() {
        return new Verify();
    }

    /* Function to launch given url
       @param : URL as string
       @return : n/a
       @Author : Lakshmanan Chellappan
   */
    public void launchUrl(String url) {
        if (url == null) {
            logger.addStepError("URL is NULL. Please provide valid URL");
        } else {
            try {
                driver.get(url);
                waitForPageLoad(30);
                logger.addStepLog("Application Launched successfully: " + url);
            } catch (WebDriverException e) {
                logger.addStepError("Unknown exception occured while launching the URL : " + url);
                e.printStackTrace();
            }
        }
    }

    /* Function to get the page title
       @param : n/a
       @return : Page Title as String
       @Author : Lakshmanan Chellappan
   */
    public String getPageTitle() {
        try {
            return driver.getTitle();
        } catch (WebDriverException e) {
            logger.addStepError("Unknown Exception Occured While fetching Title");
            return null;
        }
    }

    /* Function to wait until the page loads completely
       @param : timeOutInSeconds
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public void waitForPageLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(timeOutInSeconds));
            wait.until(pageLoadCondition);
        } catch (Exception e) {
            logger.addStepError("Timeout waiting for Page Load Request to complete.<br><b>Exception : </b>" + e);
            e.printStackTrace();
        }
    }

    /* Function to define the implicit wait
       @param  : timeout in seconds
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public void implicitlyWait(long timeOutInSeconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(timeOutInSeconds));
    }

    /* Function to attach screen shot to Extent report
       @param  : Cucumber Scenario
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public void addScreenshot(Scenario scenario) throws IOException {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
            scenario.attach(fileContent, "image/png", "screenshot");
        } catch (Exception e) {
            logger.addStepError("Unable to attach screen shot");
            e.printStackTrace();
        }

    }
}
