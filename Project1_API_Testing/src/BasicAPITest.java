import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
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
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
		.body(Payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeID = js.getString("place_id"); // storing the place_id in placeID variable
		System.out.println(placeID); // printing the placeID value 

		
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

//Notes-3
/*using .extract().response() to extract the response to String And asString() method is used to format in string. */

