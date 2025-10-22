package RestApi;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;

public class OAuthTest {

	public static void main(String[] args) {
		
		//get access token
		
		String authServerResponse = given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
		.formParam("scope","trust")
		.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then().log().all().extract().asString();
		
		JsonPath js = new JsonPath(authServerResponse);
		String accessToken = js.getString("access_token");
		
		System.out.println(accessToken);
		
		//Get course details
		
		given().queryParam("access_token", accessToken)
		.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.then().log().all();

	}

}
