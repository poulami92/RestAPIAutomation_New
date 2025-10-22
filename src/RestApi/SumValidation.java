package RestApi;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utility.payLoad;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void sumOfCourses()
	{
		JsonPath js=new JsonPath(payLoad.CoursePrice());
		int courseCount=js.getInt("courses.size()");
		
		int actualTotalPrice=0;
		
	    for(int i=0;i<courseCount;i++)
	    {
	    	int coursePrice = js.getInt("courses["+i+"].price");
	    	int copies = js.getInt("courses["+i+"].copies");
	    	
	    	int totalCoursePrice = coursePrice*copies;
	    	
	    	actualTotalPrice = actualTotalPrice+totalCoursePrice;
	    	
	    	
	    }
	    
	    System.out.println("Total courses price: "+actualTotalPrice);
	    int expectedTotalPrice= js.getInt("dashboard.purchaseAmount");
	    
	    Assert.assertEquals(actualTotalPrice,expectedTotalPrice);
	}

}
