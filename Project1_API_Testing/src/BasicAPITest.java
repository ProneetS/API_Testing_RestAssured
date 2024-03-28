import io.restassured.RestAssured;
import jsonData.Payload;

import static io.restassured.RestAssured.*; //for given()

import static org.hamcrest.Matchers.*; //for equalTo()

public class BasicAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Initial Commit");
		//System.out.println("Project location change from C drive to E drive");
		
		//Validating the AddPlace API is working fine or not
		
		//step-1: Setting up base URL 
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
		.body(Payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)");
		
	}

}

//Notes
/*Rest Assured works on 3 principles 
given, when and then 
given – method will take all input details to submit for an API
when – method submit the API
then – method used to validate the response
queryParam() method taken input parameter from the param tab of postman*/

//Notes-2
/*given () method header () is for input whereas then () method header () is for response*/

