package InputDetails;

import io.restassured.path.json.JsonPath;

public class CommonFunctions_ReUsable {
	
	public static JsonPath RawToJson(String response)
	{
		JsonPath json=new JsonPath(response);
		return json;
	}
}
