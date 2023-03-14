package com.test.lakshmanan.businesslayer.stepdefinitions;

import com.test.lakshmanan.corelayer.FrameworkWrapper;
import com.test.lakshmanan.orchestrationlayer.enums.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class PetStoreApiStepDef extends FrameworkWrapper {

    private static final String resource = globalProperties().getProperty("apiPetResource");
    private static Response response;

    @Given ("^I hit the pet store api$")
    public void i_hit_the_pet_store_api() {
        api().setRequestSpecification();
    }
    @When ("^I add a new pet with name (.+) and status (.+)$")
    public void i_add_a_new_pet(String name, String status) {
        response = api().
                post(createJsonInput(name,status),resource);
        setScenarioContext(Context.APIRESPONSE,response);
    }

    @Then ("^I capture the newly created pet details and validate the response$")
    public void i_capture_the_newly_created_pet_details_and_validate_the_response(){
        response = (Response) getScenarioContext(Context.APIRESPONSE);
        api().verify().verifyResponseCode(response,200);
        api().verify().verifyContentWithKey(response,"name",(String) getScenarioContext(Context.NAME));
        api().verify().verifyContentWithKey(response,"status",(String) getScenarioContext(Context.STATUS));
        String id = api().getContentWithKey(response,"id");
        setScenarioContext(Context.ID, id);
    }

    @Then ("^I update the name (.+) and status (.+)$")
    public void i_update_pet(String name, String status){
        String id = (String)getScenarioContext(Context.ID);
        response = api().put(createJsonInput(id,name,status),resource);
        setScenarioContext(Context.NAME,name);
        setScenarioContext(Context.STATUS,status);
        setScenarioContext(Context.APIRESPONSE,response);
    }

    @Then ("^I validate the updated response details$")
    public void i_validate_the_updated_response_details(){
        response = (Response) getScenarioContext(Context.APIRESPONSE);
        api().verify().verifyResponseCode(response,200);
        api().verify().verifyContentWithKey(response,"name",(String) getScenarioContext(Context.NAME));
        api().verify().verifyContentWithKey(response,"status",(String) getScenarioContext(Context.STATUS));
    }

    @Then ("^I query using get operation and validate the pet details$")
    public void i_get_and_validate_the_pet_details(){
        String id = (String)getScenarioContext(Context.ID);
        response = api().get(resource+"/"+ id);
        api().verify().verifyResponseCode(response,200);
        api().verify().verifyContentWithKey(response,"name",(String)getScenarioContext(Context.NAME));
        api().verify().verifyContentWithKey(response,"status",(String)getScenarioContext(Context.STATUS));
    }

    @And ("^I delete the pet and validate the response$")
    public void i_delete_the_pet(){
        String id = (String)getScenarioContext(Context.ID);
        response = api().delete(resource+"/"+ id);
        api().verify().verifyResponseCode(response,200);
        api().verify().verifyContentWithKey(response,"type","unknown");
        api().verify().verifyContentWithKey(response,"message",(String)getScenarioContext(Context.ID));
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
