package API_Tests;

import java.io.File;
import java.io.IOException;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class CreateBug_AndAttachScreenshot {

	public static void main(String[] args) throws IOException
	{
		RestAssured.baseURI= "https://sujaninalla11.atlassian.net/";
		
		String createIssueResponse = given()		
		.header("Content-Type","application/json")		
		.header("Authorization","U3VqYW5pbmFsbGExMUBnbWFpbC5jb206QVRBVFQzeEZmR0YwYWNDQnZTQmcydVhfdXZVY0VsUXlIU1VDcGxWLWlFSFFtSjVYRkltTjhMcWVLdmpKNnRoNUZBbWVnak13ZTVqLVZjeEFta1p0dE4yWE1VTFNyYXFfM0tqZUF1S1kxYXhVQmtqem1NdHd2YzNPc0t6ZEFqdEw2TW1ZMV9xaTJjd2E0WlkyWlEyX1gySnV3X2ZpS3lHWUlxdVFQek1KS3B3RnR3YVBMUEh4U0hNPTMzNTBCOTkwIA==")		
		.body("{ \r\n"
				+ "\r\n"
				+ "    \"fields\": { \r\n"
				+ "\r\n"
				+ "       \"project\": \r\n"
				+ "\r\n"
				+ "       { \r\n"
				+ "\r\n"
				+ "          \"key\": \"API\" \r\n"
				+ "\r\n"
				+ "       }, \r\n"
				+ "\r\n"
				+ "       \"summary\": \"Dropdown are not working\", \r\n"
				+ "\r\n"
				+ "           \"issuetype\": { \r\n"
				+ "\r\n"
				+ "          \"name\": \"Bug\" \r\n"
				+ "\r\n"
				+ "       } \r\n"
				+ "\r\n"
				+ "   } \r\n"
				+ "\r\n"
				+ "} ")		
		.log().all()		
		.post("rest/api/3/issue")
		.then()
		.log().all()
		.assertThat()
		.statusCode(201)
		.contentType("application/json")		
		.extract().response().asString();		 		 
		
		JsonPath js = new JsonPath(createIssueResponse);		 
		String issueId = js.getString("id");		 
		System.out.println(issueId);		 		 
		
		given()			
		.pathParam("key", issueId)			
		.header("X-Atlassian-Token","no-check")			
		.header("Authorization","U3VqYW5pbmFsbGExMUBnbWFpbC5jb206QVRBVFQzeEZmR0YwYWNDQnZTQmcydVhfdXZVY0VsUXlIU1VDcGxWLWlFSFFtSjVYRkltTjhMcWVLdmpKNnRoNUZBbWVnak13ZTVqLVZjeEFta1p0dE4yWE1VTFNyYXFfM0tqZUF1S1kxYXhVQmtqem1NdHd2YzNPc0t6ZEFqdEw2TW1ZMV9xaTJjd2E0WlkyWlEyX1gySnV3X2ZpS3lHWUlxdVFQek1KS3B3RnR3YVBMUEh4U0hNPTMzNTBCOTkwIA==")
		.multiPart("file",new File("D:\\S_Selenium\\Projects\\API-Testing\\screenshot_JIRA_BUG.jpeg"))
		.log().all()			
		.post("rest/api/3/issue/{key}/attachments")
		.then()
		.log().all()
		.assertThat()
		.statusCode(200);	
	}
}
