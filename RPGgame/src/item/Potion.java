package item;

import unit.Player;

public class Potion implements Item {
	public static final String name = "생명의 물약";
	public void used(Player player) {
		if (player.getHp() + 50 >player.getMax_hp()) {
			player.setHp(player.getMax_hp());
		}else {
			player.setHp(player.getHp() + 50);
		}
	}

}
