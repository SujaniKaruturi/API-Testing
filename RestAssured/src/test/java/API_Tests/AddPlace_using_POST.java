package API_Tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import InputDetails.CommonFunctions_ReUsable;
import InputDetails.Payload_Details;
public class AddPlace_using_POST {

	public static void main(String[] args) throws IOException
	{	//given : all input details
		//When: submit the API
		//Then: validate the response
		//End to End Scenario :Add Place ---> Update Address with New Address---> get place to validate if New address is present in response.
		//validate if Add Place is working as expected using POST method.
		RestAssured.baseURI= "https://rahulshettyacademy.com/";
		String response = given()    //chaining all input details to given method
		.log().all()
		.queryParam("key","qaclick123")
		.header("Content-Type","application/json")
		//.body(Payload_Details.AddPlace())    //body section is seperated in another class
		//how to call statuc payload from json file
		//1.convert content of file to byte--->then byte data to String which body method accepts
		//using Files.readAllBytes() - reads all bytes from file and converts it into bytes format
		//using new String object ,bytes are converting into string.
		.body(new String(Files.readAllBytes(Paths.get("D:\\S_Selenium\\Projects\\API-Testing\\AddPlace.json"))))
		.when()
		.post("/maps/api/place/add/json")
		.then()
		//.log().all()
		.assertThat()
		.statusCode(200)  //.statusCode(209) -then test gets failed .
		.body("scope",equalTo( "APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)")
		.extract().response().asString();
		
		
		//System.out.println(response);
		
		JsonPath jp=new JsonPath(response);
		String placeid = jp.getString("place_id");   //any path should start from Parent Ex: location.lng in body
		System.out.println("Place id string value is " + placeid);
		
		String newAddress = "70 Summer walk, USA";
		//Update Place
		given()    //chaining all input details to given method
		.log().all()
		.queryParam("key","qaclick123")
		.header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")    //body section is seperated in another class
		.when()
		.put("/maps/api/place/update/json")
		.then()
		.log().all()
		.assertThat()
		.statusCode(200)  //.statusCode(209) -then test gets failed .
		.body("msg",equalTo("Address successfully updated"));
		
		
		
		//Get Place
		String getRequest_Response =given()    //chaining all input details to given method
		.log().all()
		.queryParam("key","qaclick123")
		.queryParam("place_id", placeid)
		//.header("Content-Type","application/json") header is not required in get method
		.when()
		.get("/maps/api/place/get/json")
		.then()
		//.log().all()
		.assertThat()
		.statusCode(200)  //.statusCode(209) -then test gets failed .
		//.body("address",equalTo("29, side layout, cohen 09"));
		.extract().response().asString();
		
		System.out.println(getRequest_Response);
		
		//JsonPath address_json=new JsonPath(getRequest_Response);
		JsonPath address_json = CommonFunctions_ReUsable.RawToJson(getRequest_Response);    //Reusable method
		String actualaddress = address_json.getString("address");   //any path should start from Parent Ex: location.lng in body
		System.out.println("Updated Address is " + actualaddress);
		
		//Assertion using TESTNG framework
		Assert.assertEquals(actualaddress, newAddress);
	}

}
