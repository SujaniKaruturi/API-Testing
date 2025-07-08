package API_Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import InputDetails.CommonFunctions_ReUsable;
import InputDetails.Payload_Details;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson_LibraryAPI {

	@Test(dataProvider = "books_data")
	public void addBookToLibrary(String isbn,String aisle)
	{
		RestAssured.baseURI= "http://216.10.245.166";
		
		String postBook_responce = given().log().all()
		.header("Content-Type","application/json")
		//control data from testcase dynamically in payload
		.body(Payload_Details.addBook(isbn,aisle))
		.when()
		.post("Library/Addbook.php")
		.then()
		.log().all()
		.assertThat()
		.statusCode(200)
		.extract().response().asString();
		
		JsonPath js= CommonFunctions_ReUsable.RawToJson(postBook_responce);
		String id=js.get("ID");
		System.out.println("Value of ID is " + id);
	}
	
	@DataProvider(name="books_data")
	public Object[][] inputData()
	{
		return new Object[][] {{"Book_","1"},{"Book_","2"}};
	}
}
