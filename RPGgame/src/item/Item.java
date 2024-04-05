package item;

import unit.Unit;

public abstract class Item {
	private String name = "";
	private int price = 0;
	public abstract void used(Unit unit);
	
	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
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
