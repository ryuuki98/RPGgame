package item;

import unit.Unit;

//쉴드효과 : 1턴간 무적
public class Shield extends Item{

	public Shield() {
		super("쉴드", 500);
	}
	
	@Override
	public void used(Unit unit) {
		unit.setShieldOn(true);
	}

}
