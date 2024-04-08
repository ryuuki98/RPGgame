package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import guild.GuildManager;
import item.BuffItem;
import item.DebuffItem;
import item.Item;
import item.Potion;
import item.Shield;
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
	private ArrayList<Item> itemList;
	private MonsterManager monsterManager;
	private UserManager userManager;
	private GuildManager guildManager;
	
	public Normal(UserManager userManager, GuildManager guildManager) {
		monsters = null;
		monsterManager = new MonsterManager();
		this.userManager = userManager;
		this.guildManager = guildManager;
		itemList.add(new Potion());
		itemList.add(new Shield());
		itemList.add(new BuffItem());
		itemList.add(new DebuffItem());
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
	
	private String useItemName(Player player) {
		String itemName = "";
		List keySet = new ArrayList(player.getItems().keySet());
		System.out.print("사용할 아이템 번호");
		int number = inputNumber();
		int n = 1;
		for(Object key : keySet) {
			if(player.getItems().get(key) > 0) {
				if(n == number) {
					itemName = (String) key;
					break;
				}
				n++;
			}
		}
		return itemName;
	}
	
	private void runUseItem(Player player) {
		List keySet = new ArrayList(player.getItems().keySet());
		int n = 1;
		for(Object key : keySet) {
			if(player.getItems().get(key) > 0) {
				System.out.printf("[%d] %s X%d\n",n++,key,player.getItems().get(key));
			}
		}
		String itemName = useItemName(player);
		boolean isItemUsed = false;
		for(Item item : itemList) {
			if(item.getName().equals(itemName)) {
				if(itemName.equals("쇠약")) {
					int monsterIdx = ran.nextInt(monsters.size());
					Monster monster = monsters.get(monsterIdx);
					System.out.printf("[%s] 아이템을 [%s]에게 사용하였습니다.\n",itemName, monster.getJob());
					item.used(monster);
				}else {
					item.used(player);
					System.out.printf("[%s] 아이템이 사용되었습니다.\n",itemName);
				}
				int count = player.getItems().get(itemName)-1;
				player.getItems().put(itemName, count);
				isItemUsed = true;
			}
		}
		if(!isItemUsed) {
			System.err.println("사용할 수 없는 아이템 입니다.");
		}
	}
	
	private void runPlayerAttack(Player player) {
		int monsterIdx = ran.nextInt(monsters.size());
		Monster monster = monsters.get(monsterIdx);
		player.attack(monster);
	}
	
	private void runPlayerSkill(Player player) {
		
	}
	
	private void playerTurn(int idx) {
		Player player = players.get(idx);
		while(true) {
			System.out.println("[1]어택 [2]스킬 [3]아이템사용");
			int select = inputNumber();
			if(select == 3) {
				runUseItem(player);
				continue;
			}else if(select == 1) {
				runPlayerAttack(player);
			}else if(select == 2) {
				runPlayerSkill(player);
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
				playerTurn(i);				
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
