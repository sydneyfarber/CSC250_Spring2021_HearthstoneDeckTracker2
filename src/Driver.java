
public class Driver 
{

	public static void main(String[] args) 
	{
		HearthstoneCard c1 = new HearthstoneCard("Armor Vendor", 1, 1, 3);
		HearthstoneCard c2 = new HearthstoneCard("Wandmaker", 2, 2, 2);
		c1.display();
		c2.display();
		
		c1.setName("woot");
		c1.display();
		
	}

}
