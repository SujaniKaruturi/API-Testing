package Deserialiazation_JSON_POJOClass;

import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

//
public class OAuth_Authentication_DeSerailiazation {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String response = given()
						.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                        .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                        .formParams("grant_type", "client_credentials")
                        .formParams("scope", "trust")
                        .when().log().all()
                        .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		System.out.println("Response is"+ response);

		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");
		System.out.println("Access token is "+ accessToken);

		String r2= given()
					.queryParams("access_token", accessToken)
					.when()
					.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
					.asString();
		//System.out.println(r2);
		
		//Deserailiazation Topic: retreiveing values from JSON
		//for DEserialiazation we need to store data in java object ,so we need to convert whole JSON into java object
		//to store into java object ,we need supportive class which is MainJson_POJOClass parent class
		MainJson_POJOClass de_Serial= given()     //return type is object of that class  
				.queryParams("access_token", accessToken)
				.when()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
				.as(MainJson_POJOClass.class);
		
		
		
		 System.out.println(de_Serial.getLinkedIn());
		 System.out.println(de_Serial.getInstructor());
		 System.out.println(de_Serial.getCourses().getApi().get(1).getCourseTitle());
		 System.out.println(de_Serial.getCourses().getApi().get(1).getPrice());
		 
		
		//dynamically getting the course price without using static indexing
		List<api_POJOClass> api_courses =de_Serial.getCourses().getApi() ;
		for(int i=0;i<api_courses.size();i++)
		{
			api_courses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing");
			{
				System.out.println(api_courses.get(i).getPrice());
			}
		}
		
		String coursetitles[]= {"Selenium Webdriver java", "Cypress" , "Protractor"};  //expected course lists
		List<String> expected_courses = Arrays.asList(coursetitles);  //coverted from Array to ArrayList as its size may vary
		System.out.println("Expected course is" + expected_courses);
		
		//created new arraylist to add the courses list from Json.
		List<String> json_courses = new ArrayList<String>();
		
		List<WebAutomation_POJOClass> auto_courses =de_Serial.getCourses().getWebAutomation();
		for(int i=0;i<api_courses.size();i++)
		{
			json_courses.add(api_courses.get(i).getCourseTitle());   //actual course titles
		}
		System.out.println("Actual course is" + json_courses);
		
		Assert.assertTrue(expected_courses.equals(json_courses));
		
		
		//not able to output,facing UnrecodnisedPropertyException	
	}

}	

