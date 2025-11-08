package RestApi;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;

import Utility.payLoad;


public class SerializationTest {

	public static void main(String[] args) {
		
		double lat = -38.383494;
		double lng = 33.427362;
		int accuracy=50;
		String name = "Frontline house";
		String phoneNo = "(+91) 983 893 3937";
		String address = "\"29, side layout, cohen 09";
		String website = "http://google.com";
		String language= "French-IN";
	
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		
		
		RestAssured.baseURI= "https://rahulshettyacademy.com/";
		
		String addPlaceResp = given().header("Content-Type","application/json")
				.queryParam("key", "qaclick123").body(payLoad.createAddPlace(lat, lng, accuracy, name, phoneNo, address, types, website, language))
				.when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().asString();
		
		System.out.println(addPlaceResp);

	}

}
