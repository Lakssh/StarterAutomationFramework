package com.test.lakshmanan.businesslayer.stepdefinitions;

import com.test.lakshmanan.businesslayer.pageactions.AmazonHomePageActions;
import com.test.lakshmanan.corelayer.FrameworkWrapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class SearchProductStepDef extends FrameworkWrapper {

    AmazonHomePageActions homePage = new AmazonHomePageActions();

    @Given ("^I launch the (.+) and validate page title contains (.+)$")
    public void i_launch_the_application(String application, String expectedPageTitle) throws InterruptedException, IOException {
        String url = globalProperties().getProperty(application); ;
        browser().launchUrl(url);
        String actualPageTitle = browser().getPageTitle();
        browser().verify().validateTextContains(actualPageTitle, expectedPageTitle);
        browser().addScreenshot (getTestParameters().getScenario());
    }

    @When("^I search for the product (.+)$")
    public void i_search_for_the_product(String product) throws InterruptedException, IOException {
        homePage.enterSearchItem(product)
                .clickSearchIcon();
        browser().addScreenshot (getTestParameters().getScenario());
    }

    @Then("^I validate the first result contains the text (.+)$")
    public void i_validate_the_first_result_contains_the_text(String productText) throws InterruptedException, IOException {
        String actualValue = homePage.getProductText(1);
        browser().verify().validateTextContains(actualValue,productText);
        browser().addScreenshot (getTestParameters().getScenario());
    }
}
