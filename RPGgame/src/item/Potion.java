package item;

import unit.Unit;

//포션효과 : hp50 회복
public class Potion extends Item {
	public Potion() {
		super("생명의 물약", 500);
	}
	
	public void used(Unit unit) {
		if (unit.getHp() + 50 >unit.getMax_hp()) {
			unit.setHp(unit.getMax_hp());
		}else {
			unit.setHp(unit.getHp() + 50);
		}
	}
}
