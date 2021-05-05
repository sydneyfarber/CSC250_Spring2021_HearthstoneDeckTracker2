
public class HearthstoneCard 
{
	private int cost;
	private int attack;
	private int defense;
	private String name;
	
	public HearthstoneCard(String name, int cost, int attack, int defense)
	{
		this.cost = cost;
		this.attack = attack;
		this.defense = defense;
		this.name = name; 
	}
	
	
	public void setName (String name)
	{
		if(name.length() >=5)
		{
			this.name = name;
		}	
	}
	
	void display()
	{
		//System.out.println("Name:" + this.name + "\nCost" + this.cost + "\nAttack: " + this.attack + " Defense:" + this.defense);
		System.out.format("Name: %s \n Cost: %d \nAttack: %d Defense: %d \n", this.name, this.cost, this.attack, this.defense);
	}
}
