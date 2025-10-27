package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.TestDataBuild;
import resources.utils;


public class placeValidationStepDefination extends utils {
	
	RequestSpecification addPlaceReq;
	Response addPlaceResp;

	@Given("Add Place Payload")
	public void add_place_payload(DataTable dataTable) throws IOException {
		
		
		
		List<Map<String, String>> placeData= dataTable.asMaps();
		
		double lat = Double.parseDouble(placeData.get(0).get("lat"));
		double lng = Double.parseDouble(placeData.get(0).get("lng"));
		int accuracy=Integer.parseInt(placeData.get(0).get("accuracy"));
		String name = placeData.get(0).get("name");
		String phoneNo = placeData.get(0).get("phoneNo");
		String address = placeData.get(0).get("address");
		String website = placeData.get(0).get("website");
		String language= placeData.get(0).get("language");
	
		List<String> types = new ArrayList<String>();
		String typesData[] = placeData.get(0).get("types").split(",");
		for(int i=0;i<typesData.length;i++)
		{
			types.add(typesData[i]);
		}
		
		
		addPlaceReq= given().spec(requestSpecification())
		.body(TestDataBuild.createAddPlace(lat, lng, accuracy, name, phoneNo, address, types, website, language));
	}
	
	@When("user calls {string} with Post request")
	public void user_calls_with_post_request(String string) {
	    
		addPlaceResp= addPlaceReq.when().post("maps/api/place/add/json");
	}
	
	@Then("API call is success with success code {int}")
	public void api_call_is_success_with_success_code(int statusCode) {
		
//		ResponseSpecification resp= new ResponseSpecBuilder().expectStatusCode(statusCode).expectContentType(ContentType.JSON).build();
//		addPlaceResp.then().spec(resp);
		int actualStatusCode=addPlaceResp.getStatusCode();
		Assert.assertEquals(actualStatusCode, statusCode);
		
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		addPlaceResp.then().assertThat().body(key, equalTo(value)).extract().asString();
	}



}
