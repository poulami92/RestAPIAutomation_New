package RestApi;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;


public class JiraBugTest {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://poulami-datta.atlassian.net/";
		
		//Create Bug
		
		String createIssueResponse=given().header("Content-Type","application/json").header("Authorization","Basic cG91bGFtaTkyZGF0dGFAZ21haWwuY29tOkFUQVRUM3hGZkdGMHFXdDVwbzNNTUxiZnE0bDJ3TV82XzltSEI2eTF4YWR6d0VpaVdiTkI0bWlXcUF4U1BwMWJIQkJCejZ0X1JRT3dIbEVWRzlVRnczdUtMVC1vTkFyeGJFMTRUWWYzcUNjVl82V3ZrNEpzQ1RpRk45QWZncnVWQi05MDdKYWpyWXk0bEp3NF9IckNVcTVIT04ybzRVbDJqSTlBSG40M1p6YkljLTdvVjF5TkN2TT03NzM4M0Y1RQ")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"RES\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Radio Button not working-Automation\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}")
		.when().post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201).extract().asString();
		
		JsonPath js = new JsonPath(createIssueResponse);
		String issueId=js.getString("id");
		
		System.out.println("Issue created with id :"+issueId);
		
		//Add attachment
		given().header("X-Atlassian-Token","no-check")
		.pathParam("issueId", issueId)
		.header("Authorization","Basic cG91bGFtaTkyZGF0dGFAZ21haWwuY29tOkFUQVRUM3hGZkdGMHFXdDVwbzNNTUxiZnE0bDJ3TV82XzltSEI2eTF4YWR6d0VpaVdiTkI0bWlXcUF4U1BwMWJIQkJCejZ0X1JRT3dIbEVWRzlVRnczdUtMVC1vTkFyeGJFMTRUWWYzcUNjVl82V3ZrNEpzQ1RpRk45QWZncnVWQi05MDdKYWpyWXk0bEp3NF9IckNVcTVIT04ybzRVbDJqSTlBSG40M1p6YkljLTdvVjF5TkN2TT03NzM4M0Y1RQ")
		.multiPart("file",new File("C:\\Users\\MSUSERSL123\\OneDrive\\Pictures\\Screenshots\\Screenshot 2023-07-02 171842.png"))
		.when().post("/rest/api/3/issue/{issueId}/attachments")
		.then().log().all().assertThat().statusCode(200);

	}

}
