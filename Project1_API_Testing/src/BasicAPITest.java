import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jsonData_Payload.Payload;
import jsonData_Payload.ReusableMethods;

import static io.restassured.RestAssured.*; //for given()

import static org.hamcrest.Matchers.*; //for equalTo()

import org.testng.Assert;

public class BasicAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Initial Commit");
		//System.out.println("Project location change from C drive to E drive");
		
		//Validating the AddPlace API is working fine or not
		
		//step-1: Setting up base URL 
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//Working on AddPlace API
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
		.body(Payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js = ReusableMethods.rawToJson(response);
		String placeID = js.getString("place_id"); // storing the place_id in placeID variable
		System.out.println(placeID); // printing the placeID value 

		//Working on UpdatePlace API
		String newAddress = "Summer walk, London";
		
		/*we have already created placeID variable and place the value there and 
		  we need to add that to the body() JSON. So we can do that by adding variable in the string. 
		  And to do that we need to write like this “+placeId+”\*/
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+	"\"place_id\":\""+placeID+"\",\r\n"
				+	"\"address\":\""+newAddress+"\", \r\n"
				+	"\"key\":\"qaclick123\"\r\n"
				+	"}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

		//Verifying the data correctly updated or not by using GetPlace API
		//Working with GetPlace API
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id", placeID)
				.when().get("maps/api/place/get/json")
				.then().assertThat().log().all().statusCode(200).extract().asString();
		
		JsonPath js1 = ReusableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		
		//compare actualAddress of Get Place with newAddress of Update place with the help of TestNG Assertions
		Assert.assertEquals(actualAddress, newAddress);
		
		//But if we change the value here the assertion will fail.
		//Assert.assertEquals(actualAddress, "Random");
		
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

//Note-4
/*we need not to give any header because when we use GET http method, we are not sending any body to GetPlace API. Everything should be part of URL only.
But we need to give one query parameter which is place ID. */

//NOTE-5
/*We are calling the class ReUsableMethods which have rawToJson() method, which will return an Object in JsonPath format so we are storing that returned value in JsonPath js1. */