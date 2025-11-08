package RestApi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import Utility.ReUsableMethods;

public class GraphQLTest {

	public static void main (String args[])
	{
		
		JsonPath js;
		
		//Mutations
		
		String locationName="Kolkata";
		String chracterName="Loki";
		String episodeName="Thor";
				
		String mutationResp= given().log().all().header("Content-Type","application/json")
			.body("{\"query\":\"mutation($locationName: String!, $chracterName: String!,$episodeName: String!)\\n{\\n  createLocation(location: \\n    {\\n      name: $locationName\\n      type: \\\"Home\\\"\\n      dimension : \\\"200px\\\"\\n      \\n    })\\n  {\\n    id\\n  }\\n  \\n  createCharacter(character: \\n    {\\n      name:  $chracterName\\n      type: \\\"God\\\"\\n      status:\\\"alive\\\"\\n      species: \\\"Monkey\\\"\\n      gender: \\\"Male\\\"\\n      image: \\\"photo\\\"\\n      originId: 25452\\n      locationId: 25452\\n    })\\n  {\\n    id\\n  }\\n  \\n  createEpisode(episode: \\n  {\\n    name: $episodeName\\n    air_date:\\\"1992\\\"\\n    episode: \\\"Netflix\\\"\\n  })\\n  {\\n    id\\n  }\\n  \\n  deleteLocations(locationIds:[25454])\\n  {\\n    locationsDeleted\\n  }\\n}\",\"variables\":{\"locationName\":\""+locationName+"\",\"chracterName\":\""+chracterName+"\",\"episodeName\":\""+episodeName+"\"}}")
			.when().post("https://rahulshettyacademy.com/gq/graphql")
			.then().log().all().statusCode(200).extract().asString();
		
		js = new JsonPath(mutationResp);
		int characterId= js.getInt("data.createCharacter.id");
		int episodeId= js.getInt("data.createEpisode.id");
		
		
//		 Query 
//		int characterId = 18342;
//		int episodeId=	17048;
		
		String queryResponse = given().log().all().header("Content-Type","application/json")
		.body("{\"query\":\"query($characterId: Int!,$episodeId: Int!)\\n{\\n  \\n character(characterId: $characterId)\\n  \\n  {\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  \\n  location(locationId: 25459)\\n  {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: $episodeId)\\n  {\\n    name\\n    air_date\\n    episode\\n  }\\n  \\n  characters(filters:{name:\\\"Shrutika\\\",status:\\\"Married\\\"})\\n  {\\n    info\\n    {\\n      count\\n    }\\n    result\\n    {\\n      name\\n      type\\n    }\\n  }\\n  \\n  episodes(filters:{episode: \\\"Amazon Prime\\\"} )\\n  {\\n    info\\n    {\\n      count\\n    }\\n    result\\n    {\\n      id\\n      name\\n      air_date\\n      episode\\n    }\\n  }\\n  \\n  \\n}\\n\\n\",\"variables\":{\"characterId\":"+characterId+",\"episodeId\":"+episodeId+"}}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().log().all().statusCode(200).extract().asString();
		
		js = new JsonPath(queryResponse);
		String actualLocationName= js.getString("data.location.name");
		String actualcharacterName= js.getString("data.character.name");
		String actualEpisodeName= js.getString("data.episode.name");
		
		Assert.assertEquals(actualLocationName, locationName);
		Assert.assertEquals(actualcharacterName, chracterName);
		Assert.assertEquals(actualEpisodeName, episodeName);
		
		
		
		
				
	
	}

}
