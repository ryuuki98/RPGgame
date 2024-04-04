package unit;

import java.util.ArrayList;
import java.util.Random;

public class MonsterManager {
	private Random ran = new Random();
	
	private String packageName = "unit.";
	private String[] monsterNames = {"Bat","Orc","Wolf"};
	
	public ArrayList<Monster> monsters;
	
	private void settingUnit(String[] unitName, int index) {
		try {
			Class<?> unitClass = Class.forName(packageName+unitName[index]);
			Object obj = unitClass.getDeclaredConstructor().newInstance();
			
			if(obj instanceof Monster) {
				Monster monster = (Monster) obj;
				monster.setHp(ran.nextInt(200)+100);
				monsters.add(monster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void settingMonster(int size) {
		for(int i = 0; i<size; i++) {
			int n = ran.nextInt(3);
			settingUnit(monsterNames, n);
		}
	}
}
