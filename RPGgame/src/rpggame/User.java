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
	
	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}
	
	public String getPassword() {
		return password;
	}
}
