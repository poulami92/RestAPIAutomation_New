package RestApi;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.CreateOrderRequest;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetails;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Utility.ReUsableMethods;

public class EcommAPITest {

	public static void main(String[] args) {
		
		
		RequestSpecification loginReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
		//Login User
		
		LoginRequest login = new LoginRequest();
		login.setUserEmail("Gpd@gmail.com");
		login.setUserPassword("Kolkata@1");
		
		
		LoginResponse loginResp= given().spec(loginReq).body(login)
		.when().post("/api/ecom/auth/login")
		.then().log().all().assertThat().statusCode(200).extract().as(LoginResponse.class);	
		
		String token = loginResp.getToken();
		String userId = loginResp.getUserId();
		
		//Create Product
		
		RequestSpecification createProductReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).build();
		
		String createProductResp=  given().log().all().spec(createProductReq)
		.formParam("productName", "qwerty")
		.formParam("productAddedBy", userId)
		.formParam("productCategory", "fashion")
		.formParam("productSubCategory", "shirts")
		.formParam("productPrice", "11500")
		.formParam("productDescription", "Addias Originals")
		.formParam("productFor", "women")
		.multiPart("productImage",new File("C:\\Users\\MSUSERSL123\\OneDrive\\Pictures\\Screenshots\\Screenshot 2023-07-02 171842.png"))		
		.when().post("/api/ecom/product/add-product")
		.then().log().all().extract().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(createProductResp);
		
		String productId=js.getString("productId");
		
		//Place Order
		
		RequestSpecification createOrderReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
				.addHeader("Authorization", token).build();
		
		OrderDetails orderDetails = new OrderDetails();
		
		orderDetails.setCountry("India");
		orderDetails.setProductOrderedId(productId);
		
		List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
		orderDetailsList.add(orderDetails);
		
		CreateOrderRequest createOrder = new CreateOrderRequest();
		createOrder.setOrders(orderDetailsList);
		
		String createOrderResp= given().spec(createOrderReq).body(createOrder)
		.when().post("/api/ecom/order/create-order")
		.then().log().all().extract().asString();
		
		String orderId= ReUsableMethods.rawToJson(createOrderResp).getString("orders[0]");
		
		
		//View Order
		
		RequestSpecification viewOrderReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token)
				.addQueryParam("id", orderId).build();
		
		String viewOrderResp= given().spec(viewOrderReq)
				.when().get("/api/ecom/order/get-orders-details")
				.then().log().all().extract().asString();
		
		
		
		//Delete Product
		
		RequestSpecification deleteProductReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token)
				.addPathParam("productId", productId).build();
		
		String deleteOrderResp= given().spec(deleteProductReq)
				.when().delete("/api/ecom/product/delete-product/{productId}")
				.then().log().all().extract().asString();
		
	}

}
