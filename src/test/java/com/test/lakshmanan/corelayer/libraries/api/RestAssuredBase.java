package com.test.lakshmanan.corelayer.libraries.api;

import com.test.lakshmanan.corelayer.FrameworkWrapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

public class RestAssuredBase {

    private RequestSpecification requestSpecification;
    private static final RestAssuredBase instance = new RestAssuredBase();

    // Singleton pattern to get the rest assured methods instance
    private RestAssuredBase() {
    }
    public static RestAssuredBase getInstance() {
        return instance;
    }

    public void setRequestSpecification()  {
        requestSpecification = RestAssured
                .given()
                .baseUri("https://" + FrameworkWrapper.globalProperties().getProperty("server"));

    }

    public RequestSpecification setLogs() {
        if (FrameworkWrapper.globalProperties().getProperty("disableApiLogging").equals("True")){
            return requestSpecification
                    .given()
                    .contentType(getContentType());
        }else {
            return requestSpecification
                    .given()
                    .log()
                    .all()
                    .contentType(getContentType());
        }
    }

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

    public Response post(Map<String, String> headers, Map<String, Object> jsonObject, String URL) {
        return setLogs()
                .when()
                .headers(headers)
                .body(jsonObject)
                .post(URL);
    }

    public Response put() {
        return setLogs()
                .when()
                .put();
    }

    public Response put(String URL) {
        return setLogs()
                .when()
                .put(URL);
    }

    public Response put(Map<String, Object> jsonObject, String URL) {
        return setLogs()
                .when()
                .body(jsonObject)
                .put(URL);
    }

    public Response put(Map<String, String> headers, String jsonObject, String URL) {
        return setLogs()
                .when()
                .headers(headers)
                .body(jsonObject)
                .put(URL);
    }

    public Response delete() {
        return setLogs()
                .when()
                .delete();
    }

    public Response delete(String URL) {
        return setLogs()
                .when()
                .delete(URL);
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
