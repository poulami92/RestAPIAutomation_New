package RestApi;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class MockResponse {
	
	WireMockServer wireMock ;
	
	@BeforeClass
	public void setUpMockServer()
	{
		//start mock server at port 8089
		wireMock = new WireMockServer(8089);
		wireMock.start();
		configureFor("localhost",8089);
		
		//Mock response
		stubFor(get(urlEqualTo("/users/1"))
				.willReturn(aResponse().withStatus(200).withHeader("Content-Type","application/json")
				.withBody("{\r\n"
						+ "  \"id\": 1,\r\n"
						+ "  \"name\": \"John\",\r\n"
						+ "  \"role\": \"admin\"\r\n"
						+ "}")));
	}
	
	@Test
	public void testMockApi()
	{
		given().when().get("http://localhost:8089/users/1")
		.then().statusCode(200).body("name", equalTo("John"));
	}
	
	@AfterClass
	public void stopMockServer()
	{
		wireMock.start();
	}
	

}

