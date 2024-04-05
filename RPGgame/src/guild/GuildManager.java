package guild;

import java.util.ArrayList;
import java.util.HashMap;

import rpggame.User;

public class GuildManager {
	public static HashMap<String, ArrayList<User>> guildList;
	
	public GuildManager() {
		guildList = new HashMap<String, ArrayList<User>>();
	}
	
	private void createGuild() {
		String guildName = inputString("Guild name");
	}

	private String inputString(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
