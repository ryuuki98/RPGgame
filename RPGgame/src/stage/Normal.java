package stage;

import java.util.ArrayList;
import java.util.Random;

import unit.Monster;
import unit.MonsterManager;

public class Normal extends Battle{
	private Random ran = new Random();
	private ArrayList<Monster> monsters;
	private MonsterManager monsterManager;
	
	public Normal() {
		monsters = null;
		monsterManager = new MonsterManager();
	}
	
	@Override
	public void init() {
		// 일반 몬스터 생성
		monsterManager.monsters.clear();
		monsterManager.settingMonster(ran.nextInt(3)+2);
		monsters = null;
		monsters = monsterManager.monsters;
		// 여기서 길드원들 데려와야하는데....
		
	}

	@Override
	public boolean update() {
		
		return false;
	}

}
