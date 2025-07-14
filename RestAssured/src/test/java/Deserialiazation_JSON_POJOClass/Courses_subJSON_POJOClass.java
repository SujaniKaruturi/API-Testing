package Deserialiazation_JSON_POJOClass;

import java.util.List;

public class Courses_subJSON_POJOClass {

	//List<> indicates that WebAutomation is an array of JSON's
	private List<WebAutomation_POJOClass> webAutomation;
	private List<api_POJOClass> api;
	private List<Mobile_POJOClass> Mobile;
	public List<WebAutomation_POJOClass> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation_POJOClass> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<api_POJOClass> getApi() {
		return api;
	}
	public void setApi(List<api_POJOClass> api) {
		this.api = api;
	}
	public List<Mobile_POJOClass> getMobile() {
		return Mobile;
	}
	public void setMobile(List<Mobile_POJOClass> mobile) {
		Mobile = mobile;
	}
	
}
