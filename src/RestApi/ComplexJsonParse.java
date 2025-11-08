package RestApi;
import Utility.payLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args)
	{
		
		JsonPath js=new JsonPath(payLoad.CoursePrice());
		
		//1. Print No of courses returned by API
		
		int courseCount=js.getInt("courses.size()");
		System.out.println("Number of courses: "+courseCount);
		
		//2. Print Purchase Amount
		
	    int purchaseAmount=js.getInt("dashboard.purchaseAmount");
	    System.out.println("Purchase Amount: "+purchaseAmount);
		
	    //3. Print Title of the first course
	    String firstCourseTitle=js.getString("courses[0].title");
	    System.out.println("Title of the first course: "+firstCourseTitle);
	    
	    //4. Print All course Titles and their prices
	    for(int i=0;i<courseCount;i++)
	    {
	    	String courseTitle = js.getString("courses["+i+"].title");
	    	System.out.println("Course Title: "+courseTitle);
	    	
	    	int coursePrice = js.getInt("courses["+i+"].price");
	    	System.out.println("Course Price of "+courseTitle+": "+coursePrice);
	    	
	    	
	    }
	    
	    //5. Print no of copies sold by RPA
	    String expextedCourse="RPA";
	    for(int i=0;i<courseCount;i++)
	    {
	    	String courseTitle = js.getString("courses["+i+"].title");
	    	if(courseTitle.equals(expextedCourse))
	    	{
	    		int copies = js.getInt("courses["+i+"].copies");
	    		System.out.println("Copies of "+courseTitle+": "+copies);
	    		break;
	    	}
	    	
	    	
	    }
	    
	    
		
	}
}
		
		

