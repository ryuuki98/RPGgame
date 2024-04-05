package item;

import unit.Player;

public class Shield implements Item{

	public static final String name = "쉴드";
	@Override
	public void used(Player player) {
		player.setShieldOn(true);
	}

}
