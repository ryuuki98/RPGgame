package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import guild.GuildManager;
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
	
	private void playerAttack(int idx) {
		
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
