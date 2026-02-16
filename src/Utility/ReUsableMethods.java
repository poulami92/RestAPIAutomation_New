package Utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

	public static JsonPath rawToJson(String response)
	{
		JsonPath js =new JsonPath(response);
		return js;
	}
	
	public static String getDataFromJsonFile(String filePath) throws IOException
	{
		Path path = Path.of(filePath);
		String jsonData=new String(Files.readAllBytes(path));
		return jsonData;
	}
	
	  
}
