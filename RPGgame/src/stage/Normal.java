package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import guild.GuildManager;
import item.Item;
import rpggame.Game;
import rpggame.User;
import rpggame.UserManager;
import unit.Monster;
import unit.MonsterManager;
import unit.Player;

public class Normal extends Battle{
	private Random ran = new Random();
	private ArrayList<Monster> monsters;
	private ArrayList<Player> players;
	private MonsterManager monsterManager;
	private UserManager userManager;
	private GuildManager guildManager;
	
	public Normal(UserManager userManager, GuildManager guildManager) {
		monsters = null;
		monsterManager = new MonsterManager();
		this.userManager = userManager;
		this.guildManager = guildManager;
	}
	
	@Override
	public void init() {
		// 일반 몬스터 생성
		monsterManager.monsters.clear();
		monsterManager.settingMonster(ran.nextInt(3)+2);
		monsters = null;
		monsters = monsterManager.monsters;
		// 여기서 길드원들 데려와야하는데....
		players = null;
		HashMap<String, ArrayList<User>> guildList = guildManager.getGuildList();
		ArrayList<User> user = userManager.getUsers();
		User loguser = user.get(Game.getLog());
		String key = loguser.getGuildName();
		ArrayList<User> users = guildList.get(key);
		if(users!=null) {
			// 길드 O
			for(User guildUser : users) {
				players.add(guildUser.getPlayer());
			}			
		}else {
			// 솔플
			players.add(loguser.getPlayer());
		}
	}
	
	private void printInfo() {
		System.out.println("======[PLAYER]======");
		for(Player player : players) {
			
		}
		System.out.println("======[MONSTER]=====");
		for(Monster monster : monsters) {
			
		}
	}
	
	private void printItemList(Player player) {
//		ArrayList<Item> items = player.getItems();
//		ArrayList<String> itemNames = new ArrayList<>();
//		ArrayList<Integer> itemCounts = new ArrayList<>();
//		for(int i = 0; i<items.size(); i++) {
//			Item item = items.get(i);
//			String itemName = item.getName();
//			int idx = -1;
//			for(int j = 0; j<itemNames.size(); j++) {
//				if(itemName.equals(itemNames.get(j))) {
//					idx = j;
//				}
//			}
//			if(idx != -1) {
//				int count = itemCounts.get(idx);
//				itemCounts.set(idx, count+1);
//			}else {
//				itemNames.add(itemName);
//				itemCounts.add(1);
//			}
//		}
//		
//		for(int i = 0; i<itemNames.size(); i++) {
//			String itemName = itemNames.get(i);
//			int itemCount = itemCounts.get(i);
//			System.out.printf("[%d] %s X %d\n", i, itemName, itemCount);
//		}
	}
	
	private void runUseItem(Player player) {
		printItemList(player);
		
	}
	
	private void playerAttack(int idx) {
		Player player = players.get(idx);
		while(true) {
			System.out.println("[1]어택 [2]스킬 [3]아이템사용");
			int select = inputNumber();
			if(select == 3) {
				runUseItem(player);
				continue;
			}else if(select == 1) {
				
			}else if(select == 2) {
				
			}
			break;
		}
	}
	
	private void monsterAttack(int index) {
		Monster monster = monsters.get(index);
		if(monster.getHp() <= 0) {
			return;
		}
		while(true) {
			int idx = ran.nextInt(players.size());
			Player player = players.get(index);
			if(player.getHp() > 0) {
				monster.attack(player);
				break;
			}
		}
	}
	
	private boolean isGameOver() {
		int cnt = 0;
		for(Player player : players) {
			if(player.getHp() <= 0) {
				cnt++;
			}
			if(cnt == players.size()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isStageClear() {
		int cnt = 0;
		for(Monster monster : monsters) {
			if(monster.getHp() <= 0) {
				cnt++;
			}
			if(cnt == monsters.size()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update() {
		while(true) {
			if(isGameOver()) {
				return false;
			}
			for(int i = 0; i<players.size(); i++) {
				printInfo();
				playerAttack(i);				
				if(isStageClear()) {
					return true;
				}
			}
			System.out.println("====!!Monster Attack!!====");
			for(int i = 0; i<monsters.size(); i++) {
				monsterAttack(i);
			}
		}
	}

}
