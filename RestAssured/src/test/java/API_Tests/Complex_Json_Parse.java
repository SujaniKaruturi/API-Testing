package API_Tests;

import org.testng.Assert;

import InputDetails.Payload_Details;
import io.restassured.path.json.JsonPath;

public class Complex_Json_Parse {

	public static void main(String[] args) {
		//successfully created a mock/dummy payload response ,this response is getting from any api
		JsonPath json=new JsonPath(Payload_Details.CoursePrice()); 
		//As courses are array( [ ] ),we can use size method to get count of courses.
		
		System.out.println("Print No of courses returned by API");
		int courses_count = json.getInt("courses.size()");   
		System.out.println("Courses count " + courses_count );
		
		
		System.out.println("Print Purchase Amount");
		int total_amount = json.getInt("dashboard.purchaseAmount");
		System.out.println("Total amount " + total_amount );
		
		//get method by default pulls up the sting
		String firstCourse_title3 = json.get("courses[3].title");
		System.out.println("third course title is " + firstCourse_title3 );
		
		
		System.out.println("Print Title of the first course");
		String firstCourse_title = json.getString("courses[0].title");
		System.out.println("first course title is " + firstCourse_title );
		
		
		System.out.println("Print All course titles and their respective Prices");
		for(int i=0;i<courses_count;i++)
		{
			String Course_titles = json.getString("courses[" +i+ "].title");
			System.out.println(i+ " course title is " + Course_titles );
			int course_price = json.getInt("courses[" +i+ "].price");
			System.out.println(i+ " course price is " + course_price );
		}
		
		
		System.out.println("Print no of copies sold by RPA Course");
		for(int i=0;i<courses_count;i++)
		{
			String Course_titles = json.getString("courses[" +i+ "].title");
			//System.out.println(i+ " course title is " + Course_titles );
			if(Course_titles.equalsIgnoreCase("RPA"))
			{
				String Course_copies = json.getString("courses[" +i+ "].copies");
				System.out.println(Course_titles + " course copies is " +  Course_copies );
				break;
			}
		}
		
		
		System.out.println("Verify if Sum of all Course prices matches with Purchase Amount");
		int cal_amount = 0;
		for(int i=0;i<courses_count;i++)
		{
			int Course_prices = json.getInt("courses[" +i+ "].price");
			System.out.println(i+ " course title is " + Course_prices );
			int Course_copies = json.getInt("courses[" +i+ "].copies");
			System.out.println(" course copies is " +  Course_copies );
			cal_amount = cal_amount + Course_prices * Course_copies;
		}
		System.out.println("sum of course codes is " + cal_amount );
		
		Assert.assertEquals(cal_amount, cal_amount);
		
		
		
	}

}
