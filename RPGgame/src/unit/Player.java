package unit;

import java.util.HashMap;
import java.util.Map;

public abstract class Player extends Unit{
	
	private Map<String, Integer> items = new HashMap<String,Integer>();
	private int money;
	
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	public Player(String job, int hp, int power) {
		super(job);
		this.setHp(hp);
		this.setMax_hp(hp);
		this.setPower(power);
		this.money = 1000;
		items.put("생명의 물약", 0);
		items.put("요정의 축복", 0);
		items.put("쇠약", 0);
		items.put("쉴드", 0);
	}
	
	public Map<String, Integer> getItems() {
		return items;
	};
	
	abstract public void Skill(Unit unit);
	
}
