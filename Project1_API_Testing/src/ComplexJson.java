import io.restassured.path.json.JsonPath;
import jsonData_Payload.Payload;

public class ComplexJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js2 = new JsonPath(Payload.CoursePrice()); //Here the response we are not getting from any API 
		
		//Retrieving the JSON Array size and its element using JsonPath
		//Q1 - 1.	Print Number of courses returned by API
		//Ans1 - js2.getInt("courses.size()"); method used
		int count = js2.getInt("courses.size()");
		System.out.println(count);
		
		//Q2 - 2.	Print purchase amount
		//As its present in a nested fashion 
		int purchaseAmt = js2.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmt);
		
		//Q3 - 3.	Print title of the 1st course
		String title1 = js2.getString("courses[0].title");
		System.out.println(title1);


	}

}

/*Note-1
In this program, learning about nested JSON and How to access all those Child JSON value.*/