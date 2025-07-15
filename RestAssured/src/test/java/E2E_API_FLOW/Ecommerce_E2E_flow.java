package E2E_API_FLOW;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class Ecommerce_E2E_flow {

	public static void main(String[] args) throws IOException
	{
		
		//SSL certification bypassing from restassured can be done using method .relaxedHTTPSValidation()
		
	RequestSpecification req =new RequestSpecBuilder()
	.setBaseUri("https://rahulshettyacademy.com/")
	.setContentType(ContentType.JSON)
	.build();
	
	Login_Request_POJOClass_E2E lreq=new Login_Request_POJOClass_E2E();
	lreq.setUserEmail("sujaninalla11@gmail.com");
	lreq.setUserPassword("Sujani@11");
	
	Login_response_payload_Deserialization lres=new Login_response_payload_Deserialization();
		
	//Login to application successfully with token and user id as response
	Login_response_payload_Deserialization loginResponse = given()
	.relaxedHTTPSValidation()
	//.log().all()
	.spec(req)
	.body(lreq)
	.when()
	.post("/api/ecom/auth/login")
	.then()
	//.log().all()
	.extract().response().as(Login_response_payload_Deserialization.class);
	//values are outputting as null ?but y
	String token=lres.getToken();
	String userid= lres.getUserId();
	System.out.println(token);  
	System.out.println(userid);
	
	//Create/add a new product
	RequestSpecification addproduct_basespec =new RequestSpecBuilder()
			.setBaseUri("https://rahulshettyacademy.com/")
			.addHeader("authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2ODc1ZWRmZjZlYjM3Nzc1MzA5ZDM2NWIiLCJ1c2VyRW1haWwiOiJzdWphbmluYWxsYTExQGdtYWlsLmNvbSIsInVzZXJNb2JpbGUiOjk1NTAzMzY4NTgsInVzZXJSb2xlIjoiY3VzdG9tZXIiLCJpYXQiOjE3NTI1NzcxOTksImV4cCI6MTc4NDEzNDc5OX0.Ar-8iHUdoQBGUtslm-8UedODbpoIDJa-dVQWo1humUw")
			.build();
	
	 String addProductResponse= given().log().all().spec(addproduct_basespec)
	.param("productName", "Flower Skirt")
	.param("productAddedBy", userid)
	.param("productCategory", "fashion")
	.param("productSubCategory", "skirt")
	.param("productPrice", "500")
	.param("productDescription", "pink skirt")
	.param("productFor", "Kids")
	.multiPart("productImage" , new File("D:\\S_Selenium\\Projects\\API-Testing\\screenshot_JIRA_BUG.png"))
	.when()
	.post("/api/ecom/product/add-product")
	.then()//.log().all()
	.statusCode(201)
	.extract().response().asString();
	 
	 JsonPath js=new JsonPath(addProductResponse);
	 String productId=js.get("productId");
	 System.out.println(productId);
	 //Create Order
	 RequestSpecification createOrder_Spec =new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2ODc1ZWRmZjZlYjM3Nzc1MzA5ZDM2NWIiLCJ1c2VyRW1haWwiOiJzdWphbmluYWxsYTExQGdtYWlsLmNvbSIsInVzZXJNb2JpbGUiOjk1NTAzMzY4NTgsInVzZXJSb2xlIjoiY3VzdG9tZXIiLCJpYXQiOjE3NTI1NzcxOTksImV4cCI6MTc4NDEzNDc5OX0.Ar-8iHUdoQBGUtslm-8UedODbpoIDJa-dVQWo1humUw")
				.setContentType(ContentType.JSON)
				.build();
	 
	 OrderList_POJO ol=new OrderList_POJO();
	 ol.setCountry("India");
	 ol.setProductOrderedId(productId);
	 
	 List<OrderList_POJO> orderDetailList =new ArrayList<>();
	 orderDetailList.add(ol);	
	 
	 CreateOrder_POJO create_order=new CreateOrder_POJO();
	 create_order.setOrders(orderDetailList);
	 
	 String createOrder=given().log().all()
			 .spec(createOrder_Spec)
			 .body(create_order)
			 .when()
			 .post("/api/ecom/order/create-order")
			 .then()//.log().all()
			 .extract().response().asString();
	 
	 System.out.println(createOrder);  
	
	 
	 //Delete the Product
	 String delete_Response= given().log().all()
	 .spec(createOrder_Spec)  //re-using create order spec
	 .pathParam("productId",productId)
	 .when()
	 .delete("/api/ecom/order/delete-product/{productId}")    //path parameter usage
	 .then().log().all()
	 .extract().response().asString();
	 
	 System.out.println(delete_Response);
	 
	 JsonPath js1=new JsonPath(addProductResponse);
	 String delete_message=js1.get("message");
	 System.out.println(delete_message);
	 
	 Assert.assertEquals("Product Deleted Successfully" , delete_message);
	 
	 
	 
	}
}
