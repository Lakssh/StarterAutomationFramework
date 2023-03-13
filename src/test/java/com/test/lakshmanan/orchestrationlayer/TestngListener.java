/*--------------------------------------------------------------------------------------------------------------
        * Purpose  :   To listen the the events occurring in the Test and
                       orchestrate the flow of execution accordingly
        * author   :   Lakshmanan Chellappan
--------------------------------------------------------------------------------------------------------------*/
package com.test.lakshmanan.orchestrationlayer;

import com.test.lakshmanan.corelayer.FrameworkWrapper;
import com.test.lakshmanan.corelayer.drivermanager.DriverManager;
import com.test.lakshmanan.corelayer.testdata.TestParameters;
import com.test.lakshmanan.orchestrationlayer.enums.Browser;
import com.test.lakshmanan.orchestrationlayer.enums.ExecutionMode;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestngListener implements ITestListener {

    TestParameters parameters = TestParameters.getInstance();

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onTestStart(ITestResult result) {

        // Set default parameters from TestNG XML to the parameters object before start of Test
        setDefaultTestParameters(result.getTestContext());
        switch (parameters.getExecutionMode()) {
            case LOCAL:
                FrameworkWrapper.setWebDriver(parameters.getBrowser());
                break;
            case GRID:
                break;
            case MOBILE:
                break;
            case API:
                break;
            default:
                FrameworkWrapper.logger().addStepError("************ Please provide the Correct Test Execution mode ************");
        }

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onFinish(ITestContext context) {
        DriverManager.getInstance().quitDriver();
    }

    /* Function to set default Test parameters from the TestNG XML
       @param  : Test context
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public void setDefaultTestParameters(ITestContext context) {
        ExecutionMode executionMode = ExecutionMode.valueOf(context.getCurrentXmlTest().getParameter("executionMode"));
        parameters.setExecutionMode(executionMode);
        try {
            Browser browser = Browser.valueOf(context.getCurrentXmlTest().getParameter("browser"));
            parameters.setBrowser(browser);
        } catch (NullPointerException ignored){

        }
        FrameworkWrapper.setParameters(parameters);
        // Initialize scenario context for data transfer between step definitions
        FrameworkWrapper.ScenarioContext();
    }

}
