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
	
	public abstract void attack(Unit unit);
}
