package unit;

//import java.util.ArrayList;

public abstract class Player extends Unit{
	
//	ArrayList<Item> items;
	
	public Player(String job, int hp, int power) {
		super(job);
		this.setHp(hp);
		this.setMax_hp(hp);
		this.setPower(power);
	}
	
	abstract public void Skill(Unit unit);

}
