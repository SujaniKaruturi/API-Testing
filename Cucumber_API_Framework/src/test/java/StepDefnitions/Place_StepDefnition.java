package StepDefnitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import POJO_Classes.AddPlace_Main_POJO_Class;
import POJO_Classes.Location_SubJSON_POJOClass;
import Resources.APIResources_EnumClass;
import Resources.TestDataBuild;
import Resources.Utilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;


public class Place_StepDefnition extends Utilities
{
	
	//RequestSpecification requestspec ;
	RequestSpecification resp;
	Response response;
	static String placeid;
	TestDataBuild td=new TestDataBuild();
	
@Given("AddPlace payload")
public void add_place_payload() throws IOException
{
	resp=given().spec(requestSpecification()).body(td.AddPlacePayloaddata());  
	//requestSpecification is return type of 1 method from utilities as we are inheriting Utilities class.
}

@Given("AddPlace payload with {string} {string} {string}")
public void add_place_payload_with(String name, String language, String address) throws IOException {
	resp=given().spec(requestSpecification()).body(td.AddPlacePayload_dynamicInput(name,language,address)); 
}

@When("user calls {string} with POST Http Request")
public void user_calls_with_post_http_request(String string) 
{
	response =resp
			.log().all()
			.when()
			.post("/maps/api/place/add/json")
			.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.extract().response();
	System.out.println("Response Body: " + response.asString());
}
@When("user calls {string} with {string} Http Request")
public void user_calls_with_http_request(String resource, String httpmethod) {
	Resources.APIResources_Class rp=new Resources.APIResources_Class();
	//constructor will be called with value of resource which u pass "getPlaceAPI"
	APIResources_EnumClass enum_api= APIResources_EnumClass.valueOf(resource);   //retrieve the addplaceAPI string value gets fetched
	System.out.println("Http method api resource path is  " + enum_api.getResource());
	if(httpmethod.equalsIgnoreCase("POST"))
		response =resp.when().post(enum_api.getResource()); 		//sending api resource from Enum class
	else if(httpmethod.equalsIgnoreCase("GET"))
		response =resp.when().get(enum_api.getResource()); 	
	
	response =resp
			.when()
			//.post(rp.addPlaceAPI()) 
			.post(enum_api.getResource())//sending api resource from another class
			//.post("/maps/api/place/add/json")   	//sending direct api resource
			.then()
			.assertThat()
			.statusCode(200)
			.extract().response();
	System.out.println("Response Body: " + response.asString());
			
}
@Then("API call is success with status code {int}")
public void api_call_is_success_with_status_code(Integer int1)
{
	System.out.println("Status Code: " + response.getStatusCode());
	
	
	if (response == null || response.asString().trim().isEmpty()) {
	    throw new RuntimeException("Response body is null or empty. Cannot parse JSON.");
	}
	JsonPath js= new JsonPath(response.asString());
	System.out.println(js.getString(placeid));
	System.out.println(js.getString(basePath));
	System.out.println("Response status code is " + response.getStatusCode());
	Assert.assertEquals(response.getStatusCode(),200);
	//we need to import this package when we want to directly use AsserEquals method import static org.junit.Assert.*;
	//assertEquals(response.getStatusCode(),200);
}
@Then("{string} in response body is {string}")
public void in_response_body_is(String ActualValue, String ExpectedValue) {
	/*
		System.out.println("Response String  is " + response.asString());
	    assertEquals(getJsonPath(response,ActualValue),ExpectedValue);
	    */
}

@Then("Verify placeid Created in maps to {string} using {string}")
public void verify_placeid_created_in_maps_to_using(String ExpectedName, String resource) throws IOException {
	
    placeid=getJsonPath(response,"place_id");
	resp=given().spec(requestSpecification()).queryParam("place_id",placeid);
	user_calls_with_http_request(resource,"GET");
	String actualName=getJsonPath(response,"name");
	assertEquals(actualName,ExpectedName);
		
}

@Given("DeletePlace payload")
public void delete_place_payload() throws IOException 
{
   resp = given().log().all().
    spec(requestSpecification())
    .body(td.deletePlacePayload(placeid));
   
    
}

}

