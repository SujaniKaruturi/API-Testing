package Deserialiazation_JSON_POJOClass;

public class MainJson_POJOClass {

	private String url;
	private String services;
	private String expertise;
	private Courses_subJSON_POJOClass courses;   //as courses have subjson ,so using object of that class with class name will be return type.
	private String instructor;
	private String linkedin;
	//right click and check for source--->generate getter and setter methods
	
	//linked subJSON courses with Main JSON
	//but in courses we have Array of 3 items which is having another 3 sub Nested JSON's
	public Courses_subJSON_POJOClass getCourses() {
		return courses;
	}
	public void setCourses(Courses_subJSON_POJOClass courses) {
		this.courses = courses;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	
	
	
}
