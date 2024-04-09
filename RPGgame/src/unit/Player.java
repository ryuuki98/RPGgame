package unit;

import java.util.HashMap;
import java.util.Map;

public abstract class Player extends Unit{
	
	private Map<String, Integer> items = new HashMap<String,Integer>();
	private int money;
<<<<<<< HEAD
	private int mp;
	private int max_mp;
=======
>>>>>>> refs/heads/develop
	
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
		this.setMax_mp(100);
		this.setMp(100);
		this.money = 1000;
		items.put("생명의 물약", 0);
		items.put("요정의 축복", 0);
		items.put("쇠약", 0);
		items.put("쉴드", 0);;
		this.mp = 100;
		this.max_mp = 100;
	}
	
	public Map<String, Integer> getItems() {
		return items;
	};
	
	@Override
	public void attack(Unit unit) {
		int damage = getPower();
		
		//버프가 켜져있다면 주는 데미지 2배 , 한턴 사용후 버프 꺼짐
		if (isBuffOn()) {
			damage *= 2;
			System.out.println("버프 사용으로 공격력이 두배가 됩니다.");
			setBuffOn(false);
		}
		
		unit.setHp(unit.getHp() - damage <= 0 ? 0 : unit.getHp() - damage);
		System.out.printf("[%s]는 [%s]에게 %d의 데미지를 입혔다.\n",getJob(),unit.getJob(),damage);
	}
	
	abstract public void Skill(Unit unit);
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public int getMax_mp() {
		return max_mp;
	}
	public void setMax_mp(int max_mp) {
		this.max_mp = max_mp;
	}
}
