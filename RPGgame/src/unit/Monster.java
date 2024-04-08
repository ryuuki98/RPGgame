package unit;

import java.util.Random;

public abstract class Monster extends Unit{

	public Monster(String job) {
		super(job);
		this.setPower(new Random().nextInt(10) + 10);
	}
	
	@Override
	public void attack(Unit unit) {
		int damage = getPower();
		if (unit.isShieldOn()) {
			unit.setShieldOn(false);
			System.out.println(unit.getJob() + "은 쉴드효과로 인하여 데미지를 받지 않았습니다.");
			return;
		}
		
		// 공격력 / 2 
		if (isDebuffOn()) {
			damage /= 2;
			System.out.println("디버프에 걸려 공격력이 2배 낮아집니다.");
			this.setDebuffOn(false);
		}
		
		unit.setHp(unit.getHp() - damage <= 0 ? 0 : unit.getHp() - damage);
		System.out.printf("[%s]는 [%s]에게 %d의 데미지를 입혔다.\n",getJob(),unit.getJob(),damage);
	}
}
