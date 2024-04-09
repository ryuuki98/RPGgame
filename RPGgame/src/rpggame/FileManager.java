package rpggame;

import java.util.ArrayList;
import java.util.HashMap;

import guild.GuildManager;
import storage.StorageManager;

//저장 형식

//jobClassName/mapHp/power/money/maxMp/(item)integer
//id/password
//jobClassName/mapHp/power/money/maxMp/(item)integer
//id/password
//guildName/id,id,id,id/gold
//guildName/id,id,id,id/gold

public class FileManager {
	private UserManager userManager;
	private StorageManager storageManager;
	private GuildManager guildManager;
	private ArrayList<User> users;
	private HashMap<String, Integer> storage;
	private HashMap<String, ArrayList<User>> guildList;

	public FileManager() {
		userManager = UserManager.getUserManager();
		storageManager = StorageManager.getStorageManager();
		guildManager = GuildManager.getGuildManager();
		users = userManager.getUsers();
		storage = storageManager.getStorage();
		guildList = guildManager.getGuildList();

	}

	public void save() {

	}

	public void load() {

	}
}
