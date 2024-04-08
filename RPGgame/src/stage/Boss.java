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
import unit.BossMonster;
import unit.Player; 

public class Boss extends Battle{
	private Random ran = new Random();
	private BossMonster boss;
	private ArrayList<Player> players;
	private ArrayList<Item> itemList;
	private UserManager userManager;
	private GuildManager guildManager;
	
	public Boss(UserManager userManager, GuildManager guildManager) {
		this.userManager = userManager;
		this.guildManager = guildManager;
		itemList = new ArrayList<>();
		itemList.add(new Potion());
		itemList.add(new Shield());
		itemList.add(new BuffItem());
		itemList.add(new DebuffItem());
	}
	
	@Override
	public void init() {
		boss = new BossMonster();
		boss.setPower(30);
		players = null;
		HashMap<String, ArrayList<User>> guildList = guildManager.getGuildList();
		ArrayList<User> user = userManager.getUsers();
		User loguser = user.get(Game.getLog());
		String key = loguser.getGuildName();
		ArrayList<User> users = guildList.get(key);
		players = new ArrayList<Player>();
		
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
			player.printData();
		}
		System.out.println("=======[BOSS]=======");
		boss.printData();
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
					System.out.printf("[%s] 아이템을 [BOSS]에게 사용하였습니다.\n",itemName);
					item.used(boss);
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
	
	private void runPlayerSkill(Player player) {
		if(player.getJob().equals("치료사")) {
			while(true) {
				int idx = ran.nextInt(players.size());
				Player target = players.get(idx);
				if(target.getHp() > 0) {
					player.Skill(target);
					break;
				}
			}
		} else {
			if (boss.getHp() > 0) {
				player.Skill(boss);
			}

		}
	}
	
	private void playerTurn(int idx) {
		Player player = players.get(idx);
		while(true) {
			System.out.println("["+player.getJob()+"]");
			System.out.println("[1]어택 [2]스킬 [3]아이템사용");
			int select = inputNumber();
			if(select == 3) {
				runUseItem(player);
				continue;
			}else if(select == 1) {
				player.attack(boss);
			}else if(select == 2) {
				runPlayerSkill(player);
			}
			break;
		}
	}
	
	private void bossAttack() {
		for(int i = 0; i<players.size(); i++) {
			Player player = players.get(i);
			if(player.getHp()> 0 ) {
				boss.attack(player);
			}
		}
		if(boss.isDebuffOn()) {
			boss.setDebuffOn(false);
		}
	}
	
	private boolean isGameOver() {
		int cnt = 0;
		for(Player player : players) {
			if(player.getHp() <= 0) {
				cnt++;
			}
			if(cnt == players.size()) {
				for(int i = 0; i<players.size(); i++) {
					Player target = players.get(i);
					target.setHp(target.getMax_hp());
				}
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
				if(players.get(i).getHp() > 0) {
					printInfo();
					playerTurn(i);
				}
				if(boss.getHp() <= 0) {
					for(int j = 0; j<players.size(); j++) {
						Player player = players.get(j);
						int money = player.getMoney()+1000;
						player.setMoney(money);
						player.setMax_hp(player.getMax_hp()+100);
						player.setHp(player.getMax_hp());
						player.setPower(player.getPower()+10);
					}
					System.out.println("+1000 gold");
					System.out.println("최대체력 +100");
					System.out.println("공격력 + 10");
					return true;
				}
			}
			//보스공격
			System.out.println("====!!BOSS Attack!!====");
			bossAttack();
		}
	}

}
