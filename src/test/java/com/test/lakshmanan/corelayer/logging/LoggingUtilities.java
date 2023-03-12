package com.test.lakshmanan.corelayer.logging;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Calendar;

public class LoggingUtilities {

    Calendar calendar = Calendar.getInstance();

    /*    Function to add message to log and Extent report
          @param  : String message
          @return : n/a
          @Author : Lakshmanan Chellappan
    */
    public void addStepLog(String message) {
        ExtentCucumberAdapter.addTestStepLog(message);
        Logger logger = LoggerFactory.getLogger(calendar.getTime() + "\t" + "Step");
        logger.info(message);
    }

    /*    Function to add error message to log and Extent report
          @param  : String message
          @return : n/a
          @Author : Lakshmanan Chellappan
    */
    public void addStepError(String message) {
        ExtentCucumberAdapter.addTestStepLog(message);
        Logger logger = LoggerFactory.getLogger(calendar.getTime() + "\t" + "Step");
        logger.error(message);
        Assert.fail(message);
    }
}
