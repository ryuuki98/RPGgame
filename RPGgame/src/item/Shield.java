package item;

import unit.Unit;

//쉴드효과 : 1턴간 무적
public class Shield implements Item{

	public static final String name = "쉴드";
	public static final int price = 500;

	@Override
	public void used(Unit unit) {
		unit.setShieldOn(true);
	}

}
