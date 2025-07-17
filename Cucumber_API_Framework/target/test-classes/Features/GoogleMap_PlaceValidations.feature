Feature: Validating Place API's
@AddPlace
Scenario: Verify if place is successfully added using AddPlaceAPI
	Given AddPlace payload
	#When user calls AddPlaceAPI with POST Http Request
	#When user calls DeleteAPI with POST Http Request
	When user calls "AddPlaceAPI" with POST Http Request
	Then API call is success with status code 200
	And "status" in response body is "OK" 
	And "scope" in response body is "APP"


Scenario Outline: Verify if place is successfully added using AddPlaceAPI2
	Given AddPlace payload with "<name>" "<language>" "<address>"
	#When user calls AddPlaceAPI with POST Http Request
	#When user calls DeleteAPI with POST Http Request
	#When user calls "getPlaceAPI" with "POST" Http Request
	#When user calls "deletePlaceAPI" with "GET" Http Request 
	When user calls "AddPlaceAPI" with "POST" Http Request 
	#the above step deletePlaceAPI with GET method is getting pass ideally should get fail as deleteAPI calling Get Http method should fail. Need to check on this
	Then API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And Verify placeid Created in maps to "<name>" using "getPlaceAPI" 
Examples:
	| name  | language | address  |
	|Rani	  | telugu   | Tanuku   |
	#|Ravi   | Hindi    | Tadiparru| 
#added 2 data sets but in output i find only ravi got added then y Rani data did not get added?
#first test data is replaced with 2nd data sets value ,which should not happen.

@DeletePlace
Scenario: Verify if Delete Place functionality is working
Given DeletePlace payload
When user calls "deletePlaceAPI" with "POST" Http Request
Then API call is success with status code 200
And "status" in response body is "OK"
	
	