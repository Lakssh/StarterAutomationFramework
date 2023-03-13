package com.test.lakshmanan.businesslayer.stepdefinitions;

import com.test.lakshmanan.businesslayer.pageactions.AmazonHomePageActions;
import com.test.lakshmanan.corelayer.FrameworkWrapper;
import com.test.lakshmanan.orchestrationlayer.enums.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PetStoreApiStepDef extends FrameworkWrapper {

    AmazonHomePageActions homePage = new AmazonHomePageActions();

    @Given ("^I hit the pet store api$")
    public void i_hit_the_pet_store_api() throws InterruptedException, IOException {
        api().setBaseUri();
    }
    @When ("^I add a new pet with name (.+) and status (.+)$")
    public void i_add_a_new_pet(String name, String status) throws InterruptedException {
        Response response = api().
                post(createJsonInput(name,status),"v2/pet");
        setScenarioContext(Context.APIRESPONSE,response);
    }

    @Then ("^I validate the response code (.+)$")
    public void i_validate_response_code(int statusCode) throws InterruptedException {
        Response response = (Response) getScenarioContext(Context.APIRESPONSE);
        api().verify().verifyResponseCode(response,200);
    }

    @And("^I capture the newly created id$")
    public void i_capture_the_newly_created_id() throws InterruptedException {
        Response response = (Response) getScenarioContext(Context.APIRESPONSE);
        String id = api().getContentWithKey(response,"id");
        setScenarioContext(Context.ID, id);
    }

    @Then ("^I update the name (.+) and status (.+)$")
    public void i_update_pet(String name, String status) throws InterruptedException {
        String id = (String)getScenarioContext(Context.ID);
        Response response = api().
                put(createJsonInput(id,name,status),"v2/pet");
        setScenarioContext(Context.APIRESPONSE,response);
    }

    @Then ("^I get and validate the updated pet details and response code (.+)$")
    public void i_get_and_validate_the_updated_pet_details(int status) throws InterruptedException {
        String id = (String)getScenarioContext(Context.ID);
        Response response = api().
                get("v2/pet/"+(String) getScenarioContext(Context.ID));
        api().verify().verifyResponseCode(response,status);
        api().verify().verifyContentWithKey(response,"name",(String)getScenarioContext(Context.NAME));
        api().verify().verifyContentWithKey(response,"status",(String)getScenarioContext(Context.STATUS));
    }

    @And ("^I delete the pet and validate the response code (.+)$")
    public void i_delete_the_pet(int status) throws InterruptedException {
        String id = (String)getScenarioContext(Context.ID);
        Response response = api().
                delete("v2/pet/"+(String) getScenarioContext(Context.ID));
        api().verify().verifyResponseCode(response,status);
        api().verify().verifyContentWithKey(response,"type","unknown");
        api().verify().verifyContentWithKey(response,"message",(String)getScenarioContext(Context.ID));
    }
    @Then ("^I get and validate response code (.+)$")
    public void i_get_and_validate_response_code(int status) throws InterruptedException {
        String id = (String)getScenarioContext(Context.ID);
        Response response = api().
                get("v2/pet/"+(String) getScenarioContext(Context.ID));
        api().verify().verifyResponseCode(response,status);
        api().verify().verifyContentWithKey(response,"type","error");
        api().verify().verifyContentWithKey(response,"message","Pet not found");
    }
    public  Map<String, Object> createJsonInput(String name, String status){
        Map<String, Object> jsonBodyUsingMap = new HashMap<String, Object>();
        jsonBodyUsingMap.put("name",name);
        jsonBodyUsingMap.put("status",status);
        setScenarioContext(Context.NAME,name);
        setScenarioContext(Context.STATUS,status);
        return jsonBodyUsingMap;
    }
    public  Map<String, Object> createJsonInput(String id,String name, String status){
        Map<String, Object> jsonBodyUsingMap = new HashMap<String, Object>();
        jsonBodyUsingMap.put("id",id);
        jsonBodyUsingMap.put("name",name);
        jsonBodyUsingMap.put("status",status);
        setScenarioContext(Context.NAME,name);
        setScenarioContext(Context.STATUS,status);
        return jsonBodyUsingMap;
    }
}
