package unit;

//import java.util.ArrayList;

public abstract class Player extends Unit{

	private String name;
	private String guildName;
	private String id;
	private String password;
//	ArrayList<Item> items;
	
	public Player(String job, int hp, String name, String id, String password) {
		super(job, hp);
		this.name = name;
		this.id = id;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getGuildName() {
		return guildName;
	}
	
	public void setGildName(String guildName) {
		this.guildName = guildName;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
	
}
