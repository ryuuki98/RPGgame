package unit;

public class Wizard extends Player{

	public Wizard() {
		super("마법사", 800, 8);
	}

	
	//타겟의 파워를 5 낮춤
	@Override
	public void Skill(Unit unit) {
		System.out.println("[스킬] 허약 - 적의 power를 5하락 시킴");
		unit.setPower(unit.getPower() - 5);
		System.out.println(unit.getJob() + "은 power가 5 하락했다.");
	}

}
