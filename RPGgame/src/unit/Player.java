package unit;

import java.util.HashMap;
import java.util.Map;

public abstract class Player extends Unit{
	
	public Map<String, Integer> items = new HashMap<String,Integer>();
	public int money;
	
	public Player(String job, int hp, int power) {
		super(job);
		this.setHp(hp);
		this.setMax_hp(hp);
		this.setPower(power);
		items.put("생명의 물약", 0);
		items.put("요정의 축복", 0);
		items.put("쇠약", 0);
		items.put("쉴드", 0);
		money = 100;
	}
	
	abstract public void Skill(Unit unit);
	
}
