package unit;

//import java.util.ArrayList;

public abstract class Player extends Unit{
	
//	ArrayList<Item> items;
	
	public Player(String job, int hp) {
		super(job, hp);
	}
	
	abstract public void Skill(Unit unit);

}
