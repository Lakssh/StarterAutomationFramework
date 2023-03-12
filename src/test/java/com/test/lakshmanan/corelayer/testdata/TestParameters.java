/*--------------------------------------------------------------------------------------------------------------
        * Purpose  :   To set and get the execution parameters - browsers, execution mode from testng xml and
                       scenario object from cucumber
        * author   :   Lakshmanan Chellappan
--------------------------------------------------------------------------------------------------------------*/

package com.test.lakshmanan.corelayer.testdata;

import com.test.lakshmanan.orchestrationlayer.enums.Browser;
import com.test.lakshmanan.orchestrationlayer.enums.ExecutionMode;
import io.cucumber.java.Scenario;

public class TestParameters {

    private ExecutionMode executionMode;
    private Browser browser;
    private Scenario scenario;
    private static final TestParameters instance = new TestParameters();

    // Singleton pattern to get the Test Parameter instance
    private TestParameters() {
    }
    public static TestParameters getInstance() {
        return instance;
    }

    /* Getter and setter methods for Test Execution mode
       @Author : Lakshmanan Chellappan
    */
    public ExecutionMode getExecutionMode() {
        return this.executionMode;
    }
    public void setExecutionMode(ExecutionMode executionMode) {
        this.executionMode = executionMode;
    }

    /* Getter and setter methods for Browser
       @Author : Lakshmanan Chellappan
    */
    public Browser getBrowser() {
        return this.browser;
    }
    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    /* Getter and setter methods for Scenario object
       @Author : Lakshmanan Chellappan
    */
    public Scenario getScenario() {
        return this.scenario;
    }
    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }


}
