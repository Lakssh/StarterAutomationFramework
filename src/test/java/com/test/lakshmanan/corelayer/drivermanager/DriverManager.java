/*--------------------------------------------------------------------------------------------------------------
        * Purpose  :   class to instantiate the web driver and perform action on it
        * author   :   Lakshmanan Chellappan
--------------------------------------------------------------------------------------------------------------*/
package com.test.lakshmanan.corelayer.drivermanager;

import com.test.lakshmanan.corelayer.FrameworkWrapper;
import com.test.lakshmanan.corelayer.logging.LoggingUtilities;
import com.test.lakshmanan.orchestrationlayer.enums.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    LoggingUtilities logger = FrameworkWrapper.logger();
    protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private static final DriverManager instance = new DriverManager();

    // Singleton pattern to get the driver manager instance
    private DriverManager() {
    }
    public static DriverManager getInstance() {
        return instance;
    }

    /* Function to instantiate the web driver
       @param  : browser as defined in enum
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public void instantiateWebDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver.set(new ChromeDriver(options));
                break;
            case CHROME_HEADLESS:
                ChromeOptions chOptions = new ChromeOptions();
                chOptions.addArguments("--headless");
                driver.set(new ChromeDriver(chOptions));
                break;
            case FIREFOX:
                driver.set(new FirefoxDriver());
                break;
            case EDGE:
                driver.set(new EdgeDriver());
                break;
            case SAFARI:
                driver.set(new SafariDriver());
                break;
            default:
                logger.addStepError("Browser parameter not provided in TestNG XML");
        }
        driver.get().manage().window().maximize();
    }

    /* Function to get the driver for the instance
       @param  : n/a
       @return : web driver object
       @Author : Lakshmanan Chellappan
    */
    public WebDriver getDriver() {
        return driver.get();
    }

    /* Function to close the driver window under focus
       @param  : n/a
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public void closeDriver() {
        driver.get().close();
    }

    /* Function to quit all the driver windows
       @param  : n/a
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public void quitDriver() {
        driver.get().quit();
    }


}