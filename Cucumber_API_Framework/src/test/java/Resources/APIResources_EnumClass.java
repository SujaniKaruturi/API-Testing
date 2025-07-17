package Resources;

//enum is special class in java which has collection of constants or methods
public enum APIResources_EnumClass 
{
	
	
		AddPlaceAPI("/maps/api/place/add/json"),
		getPlaceAPI("/maps/api/place/get/json"),    
		//this method name and feature file teststep input name should same to same.
		//feature file step - When user calls "getPalceAPI" with "POST" Http Request
		//"getPlaceAPI" with method name getPlaceAPI();
		deletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;
	APIResources_EnumClass(String resource)    //constructor to load the resource and pass it to global variable 
	{
		this.resource= resource;
	}
	public String getResource()
	{
		return resource;
	}
}
