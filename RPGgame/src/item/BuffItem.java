package item;

import unit.Unit;


//버프효과 : 공격력 2배
public class BuffItem extends Item{
	
	public BuffItem() {
		super("요정의 축복", 500);
	}

	@Override
	public void used(Unit unit) {
		unit.setBuffOn(true);
	}

}
