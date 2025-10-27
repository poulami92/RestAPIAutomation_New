package RestApi;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;

import Utility.payLoad;


public class SpecBuilderTest {

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
		
		
		RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON)
		.addQueryParam("key", "qaclick123").build();
		
		ResponseSpecification resp= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		String addPlaceResp =given().spec(req)
		.body(payLoad.createAddPlace(lat, lng, accuracy, name, phoneNo, address, types, website, language))
		.when().post("maps/api/place/add/json")
		.then().assertThat().spec(resp).body("scope", equalTo("APP")).extract().asString();
		
		System.out.println(addPlaceResp);

	}

}
