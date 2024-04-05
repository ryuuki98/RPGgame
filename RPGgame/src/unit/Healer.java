package unit;

public class Healer extends Player {

	public Healer() {
		super("치료사", 500, 10);
	}

	
	//체력 70 회복
	@Override
	public void Skill(Unit unit) {
		System.out.println("[스킬] 치료 - 체력 70을 회복시킴");
		if (unit.getHp() + 70 > unit.getMax_hp()) {
			unit.setHp(unit.getMax_hp());
		}else {
			unit.setHp(unit.getHp() + 70);
		}
		System.out.println(unit.getJob() + "의 체력이 70 회복되었다.");
	}
}
