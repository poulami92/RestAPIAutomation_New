package RestApi;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;

public class OAuthTest {

	public static void main(String[] args) {
		
		JsonPath js;
		
		//get access token
		
		String authServerResponse = given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
		.formParam("scope","trust")
		.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then().log().all().extract().asString();
		
		js = new JsonPath(authServerResponse);
		String accessToken = js.getString("access_token");
		
		System.out.println(accessToken);
		
		//Get course details
		
		String courseResp=given().queryParam("access_token", accessToken)
		.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.then().log().all().extract().asString();
		
		js = new JsonPath(courseResp);
		
        System.out.println(js.getString("instructor"));
		System.out.println(js.getString("courses.webAutomation[1].courseTitle"));
		
		//Get price of cypress course
		
		String expectedCourseTitile="Cypress";
		int webAutomationCount=js.getInt("courses.webAutomation.size()");
				
				for(int i=0;i<webAutomationCount;i++)
				{
					String courseTitle=js.getString("courses.webAutomation["+i+"].courseTitle");
					if(courseTitle.equals(expectedCourseTitile))
					{
						System.out.println("price of "+courseTitle+" :"+js.getString("courses.webAutomation["+i+"].price"));
						break;
					}
				}

	}

}
