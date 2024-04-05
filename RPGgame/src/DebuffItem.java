import java.util.ArrayList;

import item.Item;
import unit.Player;
import unit.Unit;


//디버프 아이템 효과 : 공격력 / 2 
public class DebuffItem implements Item{
	
	public static final String name = "쇠약";
	public static final int price = 500;

	@Override
	public void used(Unit unit) {
		unit.setDebuffOn(true);
	}

}
