package unit;

public abstract class Unit {
	private String job;
	private int hp;
	private int max_hp;
	private int power;
	private int defense;
	private boolean isShieldOn;
	private boolean isBuffOn;
	private boolean isDebuffOn;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("[%s] HP[%d/%d] POWER[%d]",this.job, this.hp,this.max_hp,this.power);
	}
	
	public Unit(String job) {
		this.job = job;
	}
	
	public String getJob() {
		return job;
	}
	
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMax_hp() {
		return max_hp;
	}
	
	public void setMax_hp(int max_hp) {
		this.max_hp = max_hp;
	}
	
	public boolean isShieldOn() {
		return isShieldOn;
	}

	public void setShieldOn(boolean isShieldOn) {
		this.isShieldOn = isShieldOn;
	}

	public boolean isBuffOn() {
		return isBuffOn;
	}

	public void setBuffOn(boolean isBuffOn) {
		this.isBuffOn = isBuffOn;
	}
	
	public boolean isDebuffOn() {
		return isDebuffOn;
	}

	public void setDebuffOn(boolean isDebuffOn) {
		this.isDebuffOn = isDebuffOn;
	}
	
	public void attack(Unit unit) {
		int damage = this.power;
		if (unit.isShieldOn) {
			unit.setShieldOn(false);
			System.out.println(unit.getJob() + "은 쉴드효과로 인하여 데미지를 받지 않았습니다.");
			return;
		}
		
		
		//버프가 켜져있다면 주는 데미지 2배 , 한턴 사용후 버프 꺼짐
		if (isBuffOn) {
			damage *= 2;
			setBuffOn(false);
		}
		
		//타겟 유닛이 디버프 상태라면 받는 데미지 2배 , 턴이 지나도 꺼지지 않음
		if (unit.isDebuffOn) {
			damage *= 2;
		}
		
		unit.setHp(unit.getHp() - damage);
		System.out.printf("[%s]는 [%s]에게 %d의 데미지를 입혔다.",this.job,unit.getJob(),damage);
	};
}
