package unit;

//import java.util.ArrayList;

public abstract class Player extends Unit{

	private String name;
	private String guildName;
	private String id;
	private String password;
//	ArrayList<Item> items;
	
	public Player(String job, int hp) {
		super(job, hp);
	}
	
}
