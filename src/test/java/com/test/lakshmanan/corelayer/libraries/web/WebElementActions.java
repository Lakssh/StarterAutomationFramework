/*--------------------------------------------------------------------------------------------------------------
        * Purpose  :   class which holds all the methods to perform action on a web element in a page
        * author   :   Lakshmanan Chellappan
--------------------------------------------------------------------------------------------------------------*/
package com.test.lakshmanan.corelayer.libraries.web;

import com.test.lakshmanan.corelayer.FrameworkWrapper;
import com.test.lakshmanan.corelayer.logging.LoggingUtilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebElementActions {

    private final WebDriver driver;
    private final By by;
    LoggingUtilities logger = FrameworkWrapper.logger();

    /* Constructor (overloaded with and without locator parameters)
       @param  : web driver , locator
    */
    public WebElementActions(WebDriver driver, By locator) {
        this.driver = driver;
        this.by = locator;
    }
    public WebElementActions(WebDriver driver) {
        this.driver = driver;
        this.by = null;
    }

    /* Function to make the driver wait until element visible
       @param  : timeout in seconds
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public void waitUntilElementVisible(long timeOutInSeconds) {
        (new WebDriverWait(driver, Duration.ofMinutes(timeOutInSeconds)))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /* Function to make the driver wait until element clickable
       @param  : timeout in seconds
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public void waitUntilElementClickable(long timeOutInSeconds) {
        (new WebDriverWait(driver, Duration.ofMinutes(timeOutInSeconds)))
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    /* Function to clear and enter value in a field
       @param  : value as String
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public void enterValue(String value) {
        try {
            waitUntilElementVisible(30);
            WebElement field = driver.findElement(by);
            if (field.isDisplayed()) {
                    field.clear();
                }
            field.sendKeys(value);
            logger.addStepLog(value + " entered in " + by);
        } catch (InvalidElementStateException e) {
            logger.addStepError("The value: "+value+" could not be entered in the field :"+ by);
        } catch (NoSuchElementException e) {
            logger.addStepError("Unable to find the element -- " + by );
            e.printStackTrace();
        } catch (WebDriverException e) {
            logger.addStepError("Unknown exception occured while entering "+value+" in the field :"+by);
            e.printStackTrace();
        } catch (Exception e) {
            logger.addStepError("Operation Failed, Exception: ");
            e.printStackTrace();
        }
    }
    /* Function to click on a web element
       @param  : n/a
       @return : n/a
       @Author : Lakshmanan Chellappan
    */

    public void click() {
        try {
            waitUntilElementClickable(30);
            driver.findElement(by).click();
        } catch (InvalidElementStateException e) {
            logger.addStepError("The element: "+by+" could not be clicked :");
        } catch (NoSuchElementException e) {
            logger.addStepError("Unable to find the element -- " + by );
            e.printStackTrace();
        } catch (WebDriverException e) {
            logger.addStepError("Unknown exception occured while clicking the element "+by);
            e.printStackTrace();
        } catch (Exception e){
            logger.addStepError("unable to click the element :" +by+ " caused by exception : ");
            e.printStackTrace();
        }
    }

    /* Function to click on a web element using javascript
       @param  : n/a
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public void clickUsingJS() {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(by));
            logger.addStepLog(by + " is clicked");
        } catch (InvalidElementStateException e) {
            logger.addStepError("The element: "+by+" could not be clicked :");
        } catch (NoSuchElementException e) {
            logger.addStepError("Unable to find the element -- " + by );
            e.printStackTrace();
        } catch (WebDriverException e) {
            logger.addStepError("Unknown exception occured while clicking the element "+by);
            e.printStackTrace();
        } catch (Exception e){
            logger.addStepError("unable to click the element :" +by+ " caused by exception : ");
            e.printStackTrace();
        }
    }

    /* Function to get text attribute from a webElement
       @param  : n/a
       @return : Text attribute from the web element as a String
       @Author : Lakshmanan Chellappan
    */
    public String getText() {
        try {
            waitUntilElementVisible(30);
            WebElement element = driver.findElement(by);
            return element.getText().trim();
        } catch (NoSuchElementException e) {
            logger.addStepError("Unable to find the element -- " + by );
            e.printStackTrace();
            return null;
        } catch (WebDriverException e) {
            logger.addStepError("Unknown exception occured while getting text from the element " + by);
            e.printStackTrace();
            return null;
        }
    }
    /* Function to get text attribute from a webElement
      @param  : n/a
      @return : Text attribute from the web element as a String
      @Author : Lakshmanan Chellappan
   */
    public String getTextByElement(WebElement element) {
        try {
            return element.getText().trim();
        } catch (NoSuchElementException e) {
            logger.addStepError("Unable to find the element -- " + by );
            e.printStackTrace();
            return null;
        } catch (WebDriverException e) {
            logger.addStepError("Unknown exception occured while getting text from the element " + by);
            e.printStackTrace();
            return null;
        }
    }

}
