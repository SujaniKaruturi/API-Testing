package Resources;

import java.util.ArrayList;
import java.util.List;

import POJO_Classes.AddPlace_Main_POJO_Class;
import POJO_Classes.Location_SubJSON_POJOClass;

public class TestDataBuild {

	public AddPlace_Main_POJO_Class AddPlacePayload_dynamicInput(String name,String language,String address)
	{
		AddPlace_Main_POJO_Class ap =new AddPlace_Main_POJO_Class();
		ap.setAccuracy(50);
		ap.setName(name);
		//ap.setName("Sujani");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setAddress(address);
		//ap.setAddress("29, side layout, cohen 09");
		ap.setWebsite("http://google.com");
		ap.setLanguage(language);
		//ap.setLanguage("French-IN");
		List<String> type= new ArrayList();
		type.add("shoe park");
		type.add("shop");
		ap.setTypes(type);
		Location_SubJSON_POJOClass loc=new Location_SubJSON_POJOClass();
		loc.setIng(-38.383494);
		loc.setLat(33.427362);
		ap.setLocation(loc);
		
		return ap;
	}
	
	public AddPlace_Main_POJO_Class AddPlacePayloaddata()
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
		
		return ap;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "\r\n    \"place_id\":\""+placeId+"\"\r\\n";
	}
	
}
