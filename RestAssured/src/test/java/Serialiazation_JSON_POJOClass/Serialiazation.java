package Serialiazation_JSON_POJOClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Serialiazation {

	public static void main(String[] args) throws IOException
	{
	AddPlace_Main_POJO_Class ap =new AddPlace_Main_POJO_Class();
		ap.setAccuracy(50);
		ap.setName("Sujani");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setAddress("29, side layout, cohen 09");
		ap.setWebsite("http://google.com");
		ap.setLanguage("French-IN");
		List<String> type= new ArrayList();
		type.add("shoe park");
		type.add("shop");
		ap.setTypes(type);
		Location_SubJSON_POJOClass loc=new Location_SubJSON_POJOClass();
		loc.setIng(-38.383494);
		loc.setLat(33.427362);
		ap.setLocation(loc);
		
	RestAssured.baseURI= "https://rahulshettyacademy.com/";
	Response resp=given()    //chaining all input details to given method
	.log().all()
	.queryParam("key","qaclick123")
	.body(ap)
	.when()
	.post("/maps/api/place/add/json")
	.then()
	
	.assertThat()
	.statusCode(200)
	.extract().response();
	
	String resp_Status = resp.asString();
	System.out.println(resp_Status);	
	
	Response status with place id is not printing
	
	}
}
