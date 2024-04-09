package rpggame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	private FileReader fileReader;
	private BufferedReader bufferedReader;

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

	private void addUser(String[] playerData, String[] userData, String[] itemData) {
		String packageName = "unit.";
		try {
			Class<?> playerClass = Class.forName(packageName+playerData[0]);
			Object obj = playerClass.getConstructor().newInstance();
			if(obj instanceof Player) {
				Player player = (Player) obj;
				playerSetting(playerData, player);
				Map<String, Integer> map = player.getItems();
				itemSetting(itemData, map);
				User user = new User(userData[0],userData[1],player);
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void itemSetting(String[] itemData, Map<String, Integer> map) {
		map.clear();
		map.put("생명의 물약", Integer.parseInt(itemData[0]));
		map.put("쉴드", Integer.parseInt(itemData[1]));
		map.put("요정의 축복", Integer.parseInt(itemData[2]));
		map.put("쇠약", Integer.parseInt(itemData[3]));
	}

	private void playerSetting(String[] playerData, Player player) {
		player.setMax_hp(Integer.parseInt(playerData[1]));
		player.setHp(player.getMax_hp());
		player.setPower(Integer.parseInt(playerData[2]));
		player.setMoney(Integer.parseInt(playerData[3]));
		player.setMax_mp(Integer.parseInt(playerData[4]));
		player.setMp(player.getMp());
	}
	
	private void parseLoadedUserData(String data) {
		String[] lines = data.split("\n");
		for(int i = 0; i<lines.length-1; i+=2) {
			if(i%2==0) {
				String[] playerData = lines[i].split("/");
				String[] userData = lines[i+1].split("/");
				String[] itemData = playerData[5].split(",");
				addUser(playerData, userData, itemData);
			}
		}
	}
	
	private void parseLoadedGuildData(String data) {
		String[] lines = data.split("\n");
		for(int i = 0; i<lines.length; i++) {
			String[] guildData = lines[i].split("/");
			storage.put(guildData[0], Integer.parseInt(guildData[2]));
			String[] userId = guildData[1].split(",");
			ArrayList<User> userList = new ArrayList<>();
			for(int j = 0; j<userId.length; j++) {
				for(User user : users) {
					if(user.getId().equals(userId[j])) {
						userList.add(user);
					}
				}
			}
			guildList.put(guildData[0], userList);
		}
	}
	
	public void load() {
		users.clear();
		storage.clear();
		guildList.clear();
		String fileName = "user.txt";
		File file = new File(fileName);
		if(file.exists()) {
			String data = "";
			try {
				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);
				
				while(bufferedReader.ready()) {
					data += bufferedReader.readLine()+"\n";
				}
				
				parseLoadedUserData(data);
				
				System.out.println("파일로드 성공");
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("파일로드 실패");
			}
		}
		fileName = "guild.txt";
		file = new File(fileName);
		if(file.exists()) {
			String data = "";
			try {
				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);
				
				while(bufferedReader.ready()) {
					data += bufferedReader.readLine()+"\n";
				}
				
				parseLoadedGuildData(data);
				
				System.out.println("파일로드 성공");
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("파일로드 실패");
			}
		}
	}
}
