package Utility;

import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class payLoad {
	
		public static String AddPlace()
		{
			return "{\r\n"
					+ "  \"location\": {\r\n"
					+ "    \"lat\": -38.383494,\r\n"
					+ "    \"lng\": 33.427362\r\n"
					+ "  },\r\n"
					+ "  \"accuracy\": 50,\r\n"
					+ "  \"name\": \"Frontline house\",\r\n"
					+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
					+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
					+ "  \"types\": [\r\n"
					+ "    \"shoe park\",\r\n"
					+ "    \"shop\"\r\n"
					+ "  ],\r\n"
					+ "  \"website\": \"http://google.com\",\r\n"
					+ "  \"language\": \"French-IN\"\r\n"
					+ "}";
		}
		
		public static String CoursePrice()
		{
			return "{\r\n"
					+ "\r\n"
					+ "\"dashboard\": {\r\n"
					+ "\r\n"
					+ "\"purchaseAmount\": 1360,\r\n"
					+ "\r\n"
					+ "\"website\": \"rahulshettyacademy.com\"\r\n"
					+ "\r\n"
					+ "},\r\n"
					+ "\r\n"
					+ "\"courses\": [\r\n"
					+ "\r\n"
					+ "{\r\n"
					+ "\r\n"
					+ "\"title\": \"Selenium Python\",\r\n"
					+ "\r\n"
					+ "\"price\": 50,\r\n"
					+ "\r\n"
					+ "\"copies\": 6\r\n"
					+ "\r\n"
					+ "},\r\n"
					+ "\r\n"
					+ "{\r\n"
					+ "\r\n"
					+ "\"title\": \"Cypress\",\r\n"
					+ "\r\n"
					+ "\"price\": 40,\r\n"
					+ "\r\n"
					+ "\"copies\": 4\r\n"
					+ "\r\n"
					+ "},\r\n"
					+ "\r\n"
					+ "{\r\n"
					+ "\r\n"
					+ "\"title\": \"RPA\",\r\n"
					+ "\r\n"
					+ "\"price\": 45,\r\n"
					+ "\r\n"
					+ "\"copies\": 20\r\n"
					+ "\r\n"
					+ "}\r\n"
					+ "\r\n"
					+ "]\r\n"
					+ "\r\n"
					+ "}";
		}
		
		public static String AddBook(String isbn,String aisle)
		{
			String payload ="{\r\n"
					+ "    \"name\": \"Learn Cypress Automation with JS\",\r\n"
					+ "    \"isbn\": \""+isbn+"\",\r\n"
					+ "    \"aisle\": \""+aisle+"\",\r\n"
					+ "    \"author\": \"John foe\"\r\n"
					+ "}";
			
			return payload;
		}
		
		public static AddPlace createAddPlace(double lat,double lng,int accuracy,String name,String phoneNo,String address,List<String>types,String website,String language){
			
			AddPlace addPlace = new AddPlace();
			
			addPlace.setLocation(createLoaction(lat,lng));
			addPlace.setAccuracy(accuracy);
			addPlace.setName(name);
			addPlace.setPhoneNumber(phoneNo);
			addPlace.setAddress(address);
			addPlace.setTypes(types);
			addPlace.setWebsite(website);
			addPlace.setLanguage(language);
			
			return addPlace;
			
		}
		
		  public static Location createLoaction(double lat,double lng)
		  {
			  Location location = new Location();
				
			  location.setLat(lat);
			  location.setLng(lng);
			  
			  return location;
		  }

	}

