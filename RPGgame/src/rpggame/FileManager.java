package rpggame;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import guild.GuildManager;
import storage.StorageManager;
import unit.Player;

//저장 형식

//user.txt
//jobClassName/mapHp/power/money/maxMp/(item)integer
//id/password
//jobClassName/mapHp/power/money/maxMp/(item)integer
//id/password

//guild.txt
//guildName/id,id,id,id/gold
//guildName/id,id,id,id/gold

public class FileManager {
	private FileWriter fileWriter;

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
		saveUserInfo();
		saveGuildInfo();
	}

	private void saveGuildInfo() {
		File file = new File("guild.txt");
		String data = "";
		try {
			fileWriter = new FileWriter(file);
			Set<String> keySet = guildList.keySet();
			for(String guildName : keySet) {
				data += guildName + "/";
				for (int i = 0; i < users.size(); i++) {
					User user = users.get(i);
					if (user.getGuildName().equals(guildName)) {
						if (i != 0) {
							data +=",";
						}
						data += user.getId();
					}
				}
				data +=  "/" + storage.get(guildName) + "\n";
				
			}
			
			fileWriter.write(data);
			fileWriter.close();
			System.out.println("저장 완료");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("저장 실패");
		}
	}

	private void saveUserInfo() {
		File file = new File("user.txt");
		try {
			fileWriter = new FileWriter(file);
			String data = "";
			for (int i = 0; i < users.size(); i++) {
				User user = users.get(i);
				Player player = user.getPlayer();
				Map<String, Integer> items = player.getItems();

				int potion = (int) items.get("생명의 물약");
				int shield = (int) items.get("쉴드");
				int buffItem = (int) items.get("요정의 축복");
				int debuffItem = (int) items.get("쇠약");

				if (player.getJob().equals("전사")) {
					data += String.format("Warrior/%d/%d/%d/%d/%d,%d,%d,%d\n", player.getMax_hp(), player.getPower(),
							player.getMoney(), player.getMax_mp(), potion, shield, buffItem, debuffItem);
				} else if (player.getJob().equals("마법사")) {
					data += String.format("Wizard/%d/%d/%d/%d/%d,%d,%d,%d\n", player.getMax_hp(), player.getPower(),
							player.getMoney(), player.getMax_mp(), potion, shield, buffItem, debuffItem);
				} else if (player.getJob().equals("치료사")) {
					data += String.format("Healer/%d/%d/%d/%d/%d,%d,%d,%d\n", player.getMax_hp(), player.getPower(),
							player.getMoney(), player.getMax_mp(), potion, shield, buffItem, debuffItem);
				}
				data += user.getId() + "/" + user.getPassword();
				if (i < users.size() - 1) {
					data += "\n";
				}
			}

			fileWriter.write(data);
			System.out.println(data);
			fileWriter.close();
			System.out.println("저장 완료");

		} catch (Exception e) {
			System.out.println("저장 실패");
		}
	}

	public void load() {

	}
}
