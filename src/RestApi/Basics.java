package RestApi;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import Utility.ReUsableMethods;

public class Basics {

	public static void main(String[] args) throws IOException {
		
	RestAssured.baseURI="https://rahulshettyacademy.com";	
		
	JsonPath js;
		
		
       // Add Place		
		
		
		String addPlaceRespopnse=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\MSUSERSL123\\eclipse-workspace\\RestApiAutomation\\src\\Utility\\addPlace.json"))))
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
		.body("scope",equalTo("APP"))
		.header("SErver","Apache/2.4.52 (Ubuntu)").extract().asString();
		
		System.out.println(addPlaceRespopnse);
		
		js = ReUsableMethods.rawToJson(addPlaceRespopnse);
		String placeId=js.getString("place_id");
		
		System.out.println(placeId);
		
		
		
		//Update Place
		
		String newAddress="70 winter walk, USA";
		
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId).header("Content-Type","application/json")
		.body("{\r\n"
				+ "  \"place_id\": \""+placeId+"\",\r\n"
				+ "  \"address\": \""+newAddress+"\",\r\n"
				+ "  \"key\": \"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		
		//Get Place
		
		String getPlaceRespopnse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).body("address", equalTo(newAddress)).extract().asString();
		
		js = ReUsableMethods.rawToJson(getPlaceRespopnse);
		String actualAddress=js.getString("address");
		
		System.out.println(actualAddress);
		
		Assert.assertEquals(actualAddress, newAddress);
		
		//Delete Place
		
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId)
		.body("{\r\n"
				+ "  \"place_id\": \""+placeId+"\"\r\n"
				+ "}")
		.when().delete("/maps/api/place/delete/json")
		.then().log().all().assertThat().statusCode(200).body("status", equalTo("OK")).extract().asString();
		

	}

}
