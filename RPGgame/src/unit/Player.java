package unit;

import java.util.ArrayList;

import item.Item;

public abstract class Player extends Unit{
	
	private ArrayList<Item> items;
	
	public Player(String job, int hp, int power) {
		super(job);
		this.setHp(hp);
		this.setMax_hp(hp);
		this.setPower(power);
		items = new ArrayList<Item>();
	}
	
	abstract public void Skill(Unit unit);

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	
}
