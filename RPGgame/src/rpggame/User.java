package rpggame;

import unit.Player;

public class User {
	private String id;
	private String password;
	private Player player;
	private String guildName;
	
	public User(String id, String password , Player player) {
		this.id = id;
		this.password = password;
		this.player = player;
		this.guildName = "";
	}

	public String getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}

	public Player getPlayer() {
		return player;
	}

	public String getGuildName() {
		return guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s %s" + this.player, this.id,this.guildName);
	}
}
