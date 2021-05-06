import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class CardParser 
{
	private String urlString;
	private ArrayList<HearthstoneCard> theMinions;
	
	public CardParser(String urlString)
	{
		this.urlString = urlString;
		theMinions = new ArrayList<HearthstoneCard>(); 
		
		URLReader hearthstoneURLReader = new URLReader(this.urlString);
		Object obj = JSONValue.parse(hearthstoneURLReader.getTheURLContents());
		 
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
			 			System.out.println(cardData.keySet().toString());
			 			String name = (String)cardData.get("name");
			 			int cost = Integer.parseInt(cardData.get("cost").toString());
			 			int attack = Integer.parseInt(cardData.get("attack").toString());
			 			int health = Integer.parseInt(cardData.get("health").toString());
			 			HearthstoneCard temp = new HearthstoneCard(name, cost, attack, health);
			 			theMinions.add(temp); 
			 		}
			 	}
			 	
			 } 
		 }
	}
	
	public void showMinions()
	{ 
		for(int i = 0; i < this.theMinions.size(); i++)
		{ 
			this.theMinions.get(i).display(); 
		}	
	}
	
	public void selectionSortHighestCostToLowestCost()
	{
		for(int max = 0; max < this.theMinions.size(); max++)
		{
			int maxIndex = this.findIndexOfLargestCostFromPosition(max);
			HearthstoneCard temp = this.theMinions.get(max);
			this.theMinions.set(max, this.theMinions.get(maxIndex));
			this.theMinions.set(maxIndex, temp); 
		}
	}
	
	public void insertionSortLowestCostToHighestCost()
	{
		for(int currStart =1; currStart < this.theMinions.size(); currStart++)
		{
			//try to move the value at currStart as far up the array as possible
			//then move on to the next currStart 
			int currIndex = currStart;
			HearthstoneCard temp;
			while(currIndex > 0 && this.theMinions.get(currIndex-1).getCost() < 
				this.theMinions.get(currIndex-1).getCost())
			{  
				//we swap the 2 places
				temp = this.theMinions.get(currIndex);
				this.theMinions.set(currIndex, this.theMinions.get(currIndex-1));
				this.theMinions.set(currIndex-1, temp); 
				currIndex--; 
				
			}
		}
	}
	
	public void sortLowestCostToHighestCost()
	{
		//this method's job is to take our ArrayList of minions and re-arrange it so that 
		//it is in the order of cards with the lowest cost first and cards with the
		//highest cost last
		//Note: this.theMinions.get(3).getCost() will give you the cost of card #3
		//Note: this.theMinions.remove(3) will remove the card that used to be at bucket 3
		//You will need to cobble together your own algorithm for getting this array list sorted
		ArrayList<HearthstoneCard> theSortedList = new ArrayList<HearthstoneCard>();
		HearthstoneCard nextSmallest; 
		while(this.theMinions.size() > 0)
		{
			nextSmallest = this.findSmallest();
			theSortedList.add(nextSmallest);
		}
		this.theMinions = theSortedList; 
	}
	
	private int findIndexOfLargestCostFromPosition(int pos) 
	{
		
		//find the largest cost card from this position forward and return it
		HearthstoneCard currWinner = this.theMinions.get(pos);
		int indexOfWinner = pos;
		for(int i = pos+1; i < this.theMinions.size(); i++)
		{
			if(this.theMinions.get(i).getCost() > currWinner.getCost())
			{ 
				//we have a new winner 
				currWinner = this.theMinions.get(i);
				indexOfWinner = i;

			} 
		}
		//We know that currWinner is the card with the highest cost starting
		//at index pos and we know it is found at indexOfWinner in theMinions
		return indexOfWinner;	
	}
	
	private HearthstoneCard findSmallest()
	{
		//Go through current state of theMinions and remove and return the card
		//with the smallest value
		HearthstoneCard currWinner = this.theMinions.get(0); 
		int indexOfWinner = 0;
		
		for(int i = 1; i < this.theMinions.size(); i++)
		{
			if(this.theMinions.get(i).getCost() < currWinner.getCost())
			{
				currWinner = this.theMinions.get(i); 
				indexOfWinner = i; 
			}
		}
		//the card with the smallest cost should be in currWinner
		//the position of the card with the smallest cost should be in indexOfWinner
		this.theMinions.remove(indexOfWinner);
		return currWinner;
	}
}
