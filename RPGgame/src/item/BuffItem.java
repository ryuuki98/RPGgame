package item;

import unit.Unit;


//버프효과 : 공격력 2배
public class BuffItem implements Item{
	
	public static final String name = "요정의 축복";
	public static final int price = 500;

	@Override
	public void used(Unit unit) {
		unit.setBuffOn(true);
	}

}
