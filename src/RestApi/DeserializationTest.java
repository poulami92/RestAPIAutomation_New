package RestApi;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import pojo.CourseDetails;

public class DeserializationTest {

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
		
		CourseDetails courseDetailsResponse = given().queryParam("access_token", accessToken)
		.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.then().log().all().extract().as(CourseDetails.class);
		
		System.out.println(courseDetailsResponse.getInstructor());
		
		System.out.println(courseDetailsResponse.getCourses().getWebAutomation().get(1).getCourseTitle());
		
		//Get price of cypress course
		
		String expectedCourseTitile="Cypress";
		int webAutomationCount=courseDetailsResponse.getCourses().getWebAutomation().size();
		
		for(int i=0;i<webAutomationCount;i++)
		{
			String courseTitle=courseDetailsResponse.getCourses().getWebAutomation().get(i).getCourseTitle();
			if(courseTitle.equals(expectedCourseTitile))
			{
				System.out.println("price of "+courseTitle+" :"+courseDetailsResponse.getCourses().getWebAutomation().get(i).getPrice());
				break;
			}
		}
		
		//Verify course names of Api
		
		String expectedApiCourses[] = {"Rest Assured Automation using Java","SoapUI Webservices testing"};
		
		List<String> actualApiCourses= new ArrayList<String>();
		
		int apiCourseCount=courseDetailsResponse.getCourses().getApi().size();
		
		for(int i=0;i<apiCourseCount;i++)
		{
			String courseTitle=courseDetailsResponse.getCourses().getApi().get(i).getCourseTitle();
			actualApiCourses.add(courseTitle);
		}
		
		System.out.println(actualApiCourses);
		
		List<String> expectedApiCoursesList = Arrays.asList(expectedApiCourses);
		Assert.assertTrue(actualApiCourses.equals(expectedApiCoursesList));
		

	}
			
		

}
