package com.test.lakshmanan.corelayer.libraries.api;

import com.test.lakshmanan.corelayer.FrameworkWrapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RestAssuredBase {

    private static final RestAssuredBase instance = new RestAssuredBase();

    // Singleton pattern to get the browser action instance
    private RestAssuredBase() {
    }

    public static RestAssuredBase getInstance() {
        return instance;
    }

    public void setBaseUri() throws FileNotFoundException, IOException {
        RestAssured.baseURI = "https://"
                + FrameworkWrapper.globalProperties().getProperty("server");

    }

    /* Function to return all the verification methods
       @param  : n/a
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    public Verify verify() {
        return new Verify();
    }

    public  RequestSpecification setLogs() {
        return RestAssured
                .given()
                .log()
                .all()
                .contentType(getContentType());
    }

    public  Response get(String URL) {
        return setLogs()
                .when()
                .get(URL);
    }

    public Response get() {
        return setLogs()
                .get();
    }

    public  Response get(Map<String, String> headers, String URL) {
        return setLogs()
                .when()
                .headers(headers)
                .get(URL);
    }


    public Response post() {
        return setLogs()
                .post();
    }

    public Response post(String URL) {
        return setLogs()
                .post(URL);
    }

    public Response post(Map<String, Object> jsonObject, String URL) {
        return setLogs()
                .body(jsonObject)
                .post(URL);
    }

    public Response post(Map<String, String> headers,Map<String, Object> jsonObject, String URL) {
        return setLogs()
                .when()
                .headers(headers)
                .body(jsonObject)
                .post(URL);
    }

    public Response delete(String URL) {
        return setLogs()
                .when()
                .delete(URL);
    }

    public Response put(Map<String, String> headers,String jsonObject, String URL) {
        return setLogs()
                .when()
                .headers(headers)
                .body(jsonObject)
                .put(URL);
    }
    public Response put(Map<String, Object> jsonObject, String URL) {
        return setLogs()
                .when()
                .body(jsonObject)
                .put(URL);
    }

    private ContentType getContentType() {
        return ContentType.JSON;
    }

    public List<String> getContentsWithKey(Response response, String key) {
        if (response.getContentType().contains("json")) {
            JsonPath jsonPath = response.jsonPath();
            return jsonPath.getList(key);
        } else {
            return null;
        }
    }

    public String getContentWithKey(Response response, String key) {
        if (response.getContentType().contains("json")) {
            JsonPath jsonPath = response.jsonPath();
            try {
                return String.valueOf(jsonPath.get(key));
            }catch (ClassCastException e){
                return Long.toString(jsonPath.get(key));
            }
        } else {
            return null;
        }
    }
}
