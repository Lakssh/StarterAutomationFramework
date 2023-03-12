package com.test.lakshmanan.corelayer.libraries.web;

import com.test.lakshmanan.corelayer.logging.LoggingUtilities;

public class Verify {

    LoggingUtilities logger = new LoggingUtilities();

    /*    Function to Validate if actual and expected texts are same
          @param  : Actual and Expected values as String
          @return : n/a
          @Author : Lakshmanan Chellappan
    */
    public void validateText(String actual, String expected) {
        if (actual.equals(expected))
            logger.addStepLog("Actual and Expected text match : " + actual);
        else
            logger.addStepError("Actual Text : " + actual + " is not same as expected : " + expected);
    }

    /*    Function to Validate if expected text is present in the actual text
          @param  : Actual and Expected values as String
          @return : n/a
          @Author : Lakshmanan Chellappan
    */
    public void validateTextContains(String actual, String expected) {
        if (actual.contains(expected))
            logger.addStepLog("Actual text : " + actual + " contains the expected text  : " + expected);
        else
            logger.addStepError("Actual Text : " + actual + " do not contain the expected text : " + expected);
    }
}
