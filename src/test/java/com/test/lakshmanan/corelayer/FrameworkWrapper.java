/*--------------------------------------------------------------------------------------------------------------
        * Purpose  :   Wrapper class which acts as an core abstraction layer for all the framework utilities
        * author   :   Lakshmanan Chellappan
--------------------------------------------------------------------------------------------------------------*/
package com.test.lakshmanan.corelayer;

import com.test.lakshmanan.corelayer.drivermanager.DriverManager;
import com.test.lakshmanan.corelayer.libraries.api.RestAssuredBase;
import com.test.lakshmanan.corelayer.libraries.web.BrowserActions;
import com.test.lakshmanan.corelayer.logging.LoggingUtilities;
import com.test.lakshmanan.corelayer.testdata.PropertySettings;
import com.test.lakshmanan.corelayer.testdata.TestParameters;
import com.test.lakshmanan.orchestrationlayer.enums.Browser;
import com.test.lakshmanan.orchestrationlayer.enums.Context;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FrameworkWrapper {

    private static final ThreadLocal<TestParameters> testParameters = new ThreadLocal<TestParameters>();
    private static Map<String, Object> scenarioContext;

    /* Getter and setter methods for web driver
       @Author : Lakshmanan Chellappan
    */
    public static void setWebDriver(Browser browser) {
        DriverManager.getInstance().instantiateWebDriver(browser);
        browser().implicitlyWait(Integer.parseInt(globalProperties().getProperty("defaultWait")));

    }
    public static WebDriver getWebDriver() {
        return DriverManager.getInstance().getDriver();
    }

    /* Initialisation method , Getter and setter methods for Scenario context -
       To transfer data between the step definitions
       @Author : Lakshmanan Chellappan
    */
    public static void ScenarioContext() {
        scenarioContext = new HashMap<>();
    }
    public void setScenarioContext(Context key, Object value) {
        scenarioContext.put(key.toString(), value);
    }
    public Object getScenarioContext(Context key) {
        return scenarioContext.get(key.toString());
    }

    /* Getter and setter methods for Test Parameters read from the TestNG xml and cucumber scenario object
       @Author : Lakshmanan Chellappan
    */
    public static void setParameters(TestParameters parameters) {
        testParameters.set(parameters);
    }
    public TestParameters getTestParameters() {
        return testParameters.get();
    }

    /* Function to return all the browser action methods
       @param  : n/a
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public static BrowserActions browser() {
        return  BrowserActions.getInstance();
    }

    /* Function to return all the logging methods
       @param  : n/a
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public static LoggingUtilities logger() {
        return new LoggingUtilities();
    }

    /* Function to return all the global settings properties
       @param  : n/a
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public static Properties globalProperties() {
        return  PropertySettings.getGlobalSettingsPropertyInstance();
    }

    /* Function to return all the api methods
       @param  : n/a
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public static RestAssuredBase api() {
        return  RestAssuredBase.getInstance();
    }

}
