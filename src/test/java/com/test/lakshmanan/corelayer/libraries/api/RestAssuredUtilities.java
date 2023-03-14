/*--------------------------------------------------------------------------------------------------------------
        * Purpose  :   class which holds all the methods to perform action on API's using Rest Assured library
        * author   :   Lakshmanan Chellappan
--------------------------------------------------------------------------------------------------------------*/
package com.test.lakshmanan.corelayer.libraries.api;

import com.test.lakshmanan.corelayer.FrameworkWrapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredUtilities {

    private RequestSpecification requestSpecification;
    private static final RestAssuredUtilities instance = new RestAssuredUtilities();

    // Singleton pattern to get the rest assured methods instance
    private RestAssuredUtilities() {
    }

    public static RestAssuredUtilities getInstance() {
        return instance;
    }

    /*  Function to set the base uri in the rest assured object
        @param  : n/a
        @return : n/a
        @Author : Lakshmanan Chellappan
    */
    public void setRequestSpecification() {
        requestSpecification = RestAssured
                .given()
                .baseUri("https://" + FrameworkWrapper.globalProperties().getProperty("apiServer"));
    }

    /*  Function to set logging for api calls
        @param  : n/a
        @return : request specification
        @Author : Lakshmanan Chellappan
    */
    public RequestSpecification setLogs() {
        if (FrameworkWrapper.globalProperties().getProperty("disableApiLogging").equals("True")) {
            return requestSpecification
                    .given()
                    .contentType(ContentType.JSON);
        } else {
            return requestSpecification
                    .given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON);
        }
    }

    /*  Function to hit the api and make get request
        @param  : overloaded using 3 different params - 1. without URL , 2. with URL only, 3. With Headers and URL
        @return : rest assured Response object
        @Author : Lakshmanan Chellappan
    */
    public Response get() {
        return setLogs()
                .get();
    }

    public Response get(String URL) {
        return setLogs()
                .when()
                .get(URL);
    }

    public Response get(Map<String, String> headers, String URL) {
        return setLogs()
                .when()
                .headers(headers)
                .get(URL);
    }

    /*  Function to hit the api and make post request
        @param  : request body as json object and url as string
        @return : rest assured Response object
        @Author : Lakshmanan Chellappan
    */
    public Response post(Map<String, Object> jsonObject, String URL) {
        return setLogs()
                .body(jsonObject)
                .post(URL);
    }

    /*  Function to hit the api and make put request
        @param  : request body as json object and url as string
        @return : rest assured Response object
        @Author : Lakshmanan Chellappan
    */
    public Response put(Map<String, Object> jsonObject, String URL) {
        return setLogs()
                .when()
                .body(jsonObject)
                .put(URL);
    }

    /*  Function to hit the api and make delete request
        @param  : url as string
        @return : rest assured Response object
        @Author : Lakshmanan Chellappan
    */
    public Response delete(String URL) {
        return setLogs()
                .when()
                .delete(URL);
    }

    /*  Function to get the value from the json response object
        @param  : response and key
        @return : value of the Key as string
        @Author : Lakshmanan Chellappan
    */

    public String getContentWithKey(Response response, String key) {
        if (response.getContentType().contains("json")) {
            JsonPath jsonPath = response.jsonPath();
            try {
                return String.valueOf(jsonPath.get(key));
            } catch (ClassCastException e) {
                return Long.toString(jsonPath.get(key));
            }
        } else {
            return null;
        }
    }

    /* Function to return all the verification methods
      @param  : n/a
      @return : n/a
      @Author : Lakshmanan Chellappan
   */
    public Verify verify() {
        return new Verify();
    }

}
