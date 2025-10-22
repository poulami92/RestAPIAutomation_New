package RestApi;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.ReUsableMethods;
import Utility.payLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DynamicJson {
	
	@Test(dataProvider="BooksData") 
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		
		String addBookResponse= given().header("Content-Type","application/json").body(payLoad.AddBook(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.body("Msg", equalTo("successfully added")).extract().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(addBookResponse);
		
		String bookId = js.getString("ID");
		
		System.out.println(bookId);
		
		//bcdeert2678
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getDate()
	{
		return new Object[][] {
			{"atyufd","26554"},
			{"yytre","5666"},
			{"yyuuf","888543"}
			
		};
	}
	

}
