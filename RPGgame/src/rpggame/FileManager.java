package rpggame;

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
	
	public FileManager() {
		userManager = UserManager.getUserManager();
		storageManager = StorageManager.getStorageManager();
	}
	public void save() {

	}

	public void load() {

	}
}
