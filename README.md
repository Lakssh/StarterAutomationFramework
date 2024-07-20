# Starter Automation Framework

    This is the basic test automation framework built for technical assessment.
    Hybrid framework built using different component - Selenium with Java Binding ,TestNG ,Cucumber surfaced by
    Page Object Model, Singleton and factory design pattern with extent report driven by 3 Layered Architecture


## Steps to Execute a Test

    1. Pre-requisites - Global installations :
            - Install Java 1.8 
            - Install maven
            - For Execution in Mac Safari 
                1. Enable Show developer menu in Safari preferences
                2. "Allow remote automation" under Develop options
                3. Run /usr/bin/safaridriver –-enable from terminal
    2. From command line : mvn clean test -PrunRegressionTest
    3. Using an IDE (Eclipse or Intellij ) : Run from the TestNG file in the path src/test/java/com/test/lakshmanan/orchestrationlayer/testngxmls
       (use RegressionTest.xml for regression tests)
    4. Parallel execution can be achieved by modifying the data-provider-thread-count value in the testing XML file 
       (Grid configurations yet to be done. However parallel execution in a single machine can be achieved )
    5. Github Actions : Github workflow action is configured to trigger rgression execution on any commit using the configs defined in .github/workflows/maven.yml
       Report : Cucumber report can be viewed by following the link at the end of the Test in the task "Run Regression Test"  
                sample report link https://reports.cucumber.io/reports/<dynamic id generated for every run> expires in 24 hrs
       Note  : Multiple executions done to ensure the script robustness and couple of genuine failures noted as below for reference
                1. Assertion error on validating the text of first result, when executed in machine allocated in US region
                     Actual Text : Apple 2022 iPhone SE (64 GB, Starlight) [Locked] + Carrier Subscription do not contain the expected text : Apple iPhone
                2. Pet store API's are sporadic and some time's donot return the expected pet name and details.

## Steps to Design a Test

    **FrameworkWrapper in Core Layer is the heart of the Framework which wraps up all the reusable librariries and 
    enables one to use it in their Tests. Framework follows Bottom-Up Approach as,**
    FrameworkWrapper -> [Pagelocators -> pageactions -> stepdefinitions -> features]
    1. pagelocators 
        - should inherit FrameworkWrapper class
        - all locators should be defined with type as Selenium "By" class
    2. pageactions
        - should inherit pagelocators class
        - all actions on page objects should be created as methods without any logic & verifications
    3. Stepdefinitions
        - instance for all required pageactions should be created here
        - business process including Verifications will be defined here with different business logics using Page actions
    4. Features
        - Please write the Gherkin syntax as understandable steps with _**"What to"**_ Test and _**"not How to"**_ Test
    5. Scenario Context - data handshake between the step definitions
        - Use setScenarioContext(key, value) from FrameworkWrapper to set the Test data in the step definitions
        - use getScenarioContext(key) from FrameworkWrapper to get the Test data in the step definitions
        - key should always be one of the variables in the Context enum defined in src/test/java/com/test/lakshmanan/orchestrationlayer/enums
          Add new enum variables as required
    6. Test Properties
        - Test Properties(like URL ,flags etc..,) are maintained in src/test/resources/GlobalSettings.properties file and 
          can be accessed at any point using FrameworkWrapper.globalProperties().getProperty(<propertyName>)
    7. Test Parameters
        - Test parameters are the parameter values like execution mode, browser etc.., configured in the testNG xml and 
          can be accessed at any point using FrameworkWrapper.getTestParameters()
    8. How to use Reusable utilities
        Reusable utilities can be used at any class / methods by inheriting the FrameworkWrapper or instantiating the FrameworkWrapper object
        Few examples:
            To perform click on mobile element          - browser().element(<locator>).click()
            To perform verification on WinApp element   - browser().verify().validateTextContains(actualValue,expectedValue)
            To perform action on Web browser window     - browser().launchUrl(url)
            To wait on an webElement                    - browser().element(<locator>).wait().until_element_is_clickable()
    9. Logging
        - Use logger() from FrameworkUtilities for logging at any point of execution.
          logger().addStepLog for providing any info
          logger().addStepError for failures
    10. Report
        - At the end of execution Extent html and spark html reports are generated in Reports/ folder
        - Junit and TestNG reports in target/surefire-reports when executed through maven commandline
        - Extent and cucumber report configurations can be done in respective files in src/test/resources


