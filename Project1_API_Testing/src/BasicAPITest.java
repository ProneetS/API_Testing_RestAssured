import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

public class BasicAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Initial Commit");
		//System.out.println("Project location change from C drive to E drive");
		
		//Validating the AddPlace API is working fine or not
		
		//step-1: Setting up base URL
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}")
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200);
	}

}

//Notes
/*Rest Assured works on 3 principles 
given, when and then 
given – method will take all input details to submit for an API
when – method submit the API
then – method used to validate the response*/

