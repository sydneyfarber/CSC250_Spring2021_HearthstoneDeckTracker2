import java.net.URL;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;
	
public class Driver 
{

	public static void main(String[] args) 
	{
		URLReader hearthstoneURLReader = new URLReader("https://api.hearthstonejson.com/v1/25770/enUS/cards.json");
		//System.out.println(hearthstoneURLReader.getTheURLContents()); 
		 Object obj = JSONValue.parse(hearthstoneURLReader.getTheURLContents());
		 //HearthstoneCard[] theMinions = new HearthstoneCard[1924];
		 ArrayList<HearthstoneCard> theMinions = new ArrayList<HearthstoneCard>(); 

		 //System.out.println(obj instanceof JSONArray);
		 if(obj instanceof JSONArray)
		 { 
			 
			 // I am only in here if obj IS a JSONArray
			 JSONArray array = (JSONArray)obj;
		
			 for(int i = 0; i < array.size(); i++)
			 {
			 	JSONObject cardData = (JSONObject)array.get(i);
			 	if(cardData.containsKey("cost")&& cardData.containsKey("name"))
			 	{
			 		if(cardData.containsKey("type") && cardData.get("type").equals("MINION")) 
			 	    {
			 			//Only in here if a minion card
			 			String name = (String)cardData.get("name");
			 			int cost = Integer.parseInt(cardData.get("cost").toString());
			 			int attack = Integer.parseInt(cardData.get("attack").toString());
			 			int health = Integer.parseInt(cardData.get("health").toString());
			 			HearthstoneCard temp = new HearthstoneCard(name, cost, attack, health);
			 			theMinions.add(temp); 
			 			temp.display();
			 		}
			 		
			 	}
			 	
			 } 
		     System.out.println(theMinions.size()); 
		 }	 
	}	
} 
