/*--------------------------------------------------------------------------------------------------------------
        * Purpose  :   class which holds all the verification methods for Api
        * author   :   Lakshmanan Chellappan
--------------------------------------------------------------------------------------------------------------*/
package com.test.lakshmanan.corelayer.libraries.api;

import com.test.lakshmanan.corelayer.logging.LoggingUtilities;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

public class Verify {

    LoggingUtilities logger = new LoggingUtilities();

    /*    Function to verify the response code from the api response
          @param  : response object and expected response code
          @return : n/a
          @Author : Lakshmanan Chellappan
    */
    public void verifyResponseCode(Response response, int code) {
        if (response.statusCode() == code) {
            logger.addStepLog("The status code " + code + " matches the expected code");
        } else {
            logger.addStepError("The status code " + code + " does not match the expected code" + response.statusCode());
        }
    }

    /*    Function to verify the content type from the api response
          @param  : response object and expected content type
          @return : n/a
          @Author : Lakshmanan Chellappan
    */
    public void verifyContentType(Response response, String type) {
        if (response.getContentType().toLowerCase().contains(type.toLowerCase())) {
            logger.addStepLog("The Content type " + type + " matches the expected content type");
        } else {
            logger.addStepError("The Content type " + type + " does not match the expected content type " + response.getContentType());
        }
    }

    /*    Function to verify the content with key from the api response
          @param  : response json object and expected content value from key
          @return : n/a
          @Author : Lakshmanan Chellappan
    */

    public void verifyContentWithKey(Response response, String key, String expVal) {
        if (response.getContentType().contains("json")) {
            JsonPath jsonPath = response.jsonPath();
            String actValue = jsonPath.get(key);
            if (actValue.equalsIgnoreCase(expVal)) {
                logger.addStepLog("The JSON response has value " + expVal + " as expected. ");
            } else {
                logger.addStepError("The JSON response :" + actValue + " does not have the value " + expVal + " as expected. ");
            }
        }
    }
}
