package item;

import unit.Player;
import unit.Unit;

public abstract class Item {
	private String name = "";
	private int price = 0;
	public abstract void used(Unit unit);
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
}
