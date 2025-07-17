package Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utilities 
{
	public static RequestSpecification  requestspec; //single instance for entire execution for all tests 1,2,3,4 etc

	public RequestSpecification requestSpecification() throws IOException
	
	{
		if(requestspec==null)
		{
			PrintStream logging= new PrintStream(new FileOutputStream("logging.txt"));  //location of file
			requestspec =new RequestSpecBuilder().setBaseUri(getGlobalVariables("baseUrl"))
					.addQueryParam("key","qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(logging))
					.addFilter(ResponseLoggingFilter.logResponseTo(logging))
					.setContentType("application/json")  //optional
					.build();
			
			return requestspec;
		}
		else
		{
			return requestspec;
		}
		
	}
	
	public static String getGlobalVariables(String key) throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fs= new FileInputStream("D:\\Sujani\\Projects\\API-Testing\\Cucumber_API_Framework\\src\\test\\java\\Resources\\GlobalVariables.properties");
		prop.load(fs);
		//prop.getProperty("baseUrl");
		System.out.println(prop.getProperty(key));
		return prop.getProperty(key);
		
	}
	
	public String getJsonPath(Response response,String key)
	{
		String response_Payload =response.asString();
		JsonPath js=new JsonPath(response_Payload);
		return js.get(key).toString();
	}
}
