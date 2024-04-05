package unit;

public class Warrior extends Player{

	public Warrior() {
		super("전사", 1000, 30);
	}

	
	//타겟에게 3회 공격
	@Override
	public void Skill(Unit unit) {
		System.out.println("[스킬] 삼연격 - 동일한 적을 3번 타격");
		for (int i = 0; i < 3; i++) {
			this.attack(unit);	
		}
		System.out.println(unit.getJob() + "은 스킬로 인해 3회 타격 받았다." );
	}

	
}
