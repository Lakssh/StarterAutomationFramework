package com.test.lakshmanan.orchestrationlayer.cucumberrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(features = "src/test/java/com/test/lakshmanan/businesslayer/features" , glue = "com.test.lakshmanan.businesslayer.stepdefinitions" ,
        tags = ("@Regression"), plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},monochrome = true)

public class RegressionTestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
