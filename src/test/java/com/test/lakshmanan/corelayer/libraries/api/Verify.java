package com.test.lakshmanan.corelayer.libraries.api;

import com.test.lakshmanan.corelayer.logging.LoggingUtilities;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

public class Verify {

    LoggingUtilities logger = new LoggingUtilities();

    public void verifyContentType(Response response, String type) {
        if (response.getContentType().toLowerCase().contains(type.toLowerCase())) {
            logger.addStepLog("The Content type " + type + " matches the expected content type");
        } else {
            logger.addStepError("The Content type " + type + " does not match the expected content type " + response.getContentType());
        }
    }

    public void verifyResponseCode(Response response, int code) {
        if (response.statusCode() == code) {
            logger.addStepLog("The status code " + code + " matches the expected code");
        } else {
            logger.addStepError("The status code " + code + " does not match the expected code" + response.statusCode());
        }
    }

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

    public void verifyContentsWithKey(Response response, String key, String expVal) {
        if (response.getContentType().contains("json")) {
            JsonPath jsonPath = response.jsonPath();
            List<String> actValue = jsonPath.getList(key);
            if (actValue.get(0).equalsIgnoreCase(expVal)) {
                logger.addStepLog("The JSON response has value " + expVal + " as expected. ");
            } else {
                logger.addStepError("The JSON response :" + actValue + " does not have the value " + expVal + " as expected. ");
            }
        }
    }
}
