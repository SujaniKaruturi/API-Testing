package StepDefnitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks 
{
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
			Place_StepDefnition sd=new Place_StepDefnition();
			if(Place_StepDefnition.placeid==null)  //as placeid is static variable
			{
			sd.add_place_payload_with("Krishna", "Marati","Pune" );
			sd.user_calls_with_http_request("AddPlaceAPI", "POST");
		    sd.verify_placeid_created_in_maps_to_using("Krishna", "getPlaceAPI");
			}
	}
}
