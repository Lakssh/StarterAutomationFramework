/*--------------------------------------------------------------------------------------------------------------
        * Purpose  :   To listen the the events occurring in the Cucumber Test and
                       orchestrate the flow of execution accordingly
        * author   :   Lakshmanan Chellappan
--------------------------------------------------------------------------------------------------------------*/
package com.test.lakshmanan.businesslayer.stepdefinitions;

import com.test.lakshmanan.corelayer.drivermanager.DriverManager;
import com.test.lakshmanan.corelayer.FrameworkWrapper;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;

public class CukeHooks extends FrameworkWrapper{

    @Before
    public void setUp(Scenario scenario) {
        getTestParameters().setScenario(scenario);
        logger().addStepLog("Execution started for Scenario: " +getTestParameters().getScenario().getName() );
    }

    @After
    public void tearDown(Scenario scenario){
        DriverManager.getInstance().closeDriver();
        logger().addStepLog("Execution completed for Scenario: " +getTestParameters().getScenario().getName() + " and driver closed");
    }

    @AfterStep
    public void afterStep(Scenario scenario) throws IOException {
    }
}

