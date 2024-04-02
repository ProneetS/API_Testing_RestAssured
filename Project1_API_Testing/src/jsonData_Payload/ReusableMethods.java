package jsonData_Payload;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	public static JsonPath rawToJson(String response) {
		JsonPath js1 = new JsonPath(response);
		return js1;
		
	}
}


//Note-1
/*We make this class static so that we can directly use this in class.
The return type is JsonPath.
We pass a String argument as it will take String as an argument because JsonPath will convert String to Json.*/
