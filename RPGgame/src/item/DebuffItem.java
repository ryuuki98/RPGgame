package item;

import unit.Unit;


//디버프 아이템 효과 : 공격력 / 2 
public class DebuffItem extends Item{
	
	public DebuffItem() {
		super("쇠약", 500);
	}

	@Override
	public void used(Unit unit) {
		unit.setDebuffOn(true);
	}

}
