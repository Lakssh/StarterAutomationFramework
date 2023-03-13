/*--------------------------------------------------------------------------------------------------------------
        * Purpose  :   class to instantiate the web driver and perform action on it
        * author   :   Lakshmanan Chellappan
--------------------------------------------------------------------------------------------------------------*/
package com.test.lakshmanan.corelayer.drivermanager;

import com.test.lakshmanan.corelayer.FrameworkWrapper;
import com.test.lakshmanan.corelayer.logging.LoggingUtilities;
import com.test.lakshmanan.orchestrationlayer.enums.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver.set(new ChromeDriver(options));
                driver.get().manage().window().maximize();
                break;
            case CHROME_HEADLESS:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chOptions = new ChromeOptions();
                chOptions.addArguments("--headless");
                driver.set(new ChromeDriver(chOptions));
                driver.get().manage().window().maximize();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                driver.get().manage().window().maximize();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                driver.get().manage().window().maximize();
                break;
            default:
                logger.addStepError("Browser parameter not provided in TestNG XML");
        }


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