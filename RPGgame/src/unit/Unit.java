package unit;

public abstract class Unit {
	private String job;
	private int hp;
	private int max_hp;
	private int power;
	private int max_mp;
	private int mp;
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
	
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	
	public int getMax_mp() {
		return max_mp;
	}
	
	public void setMax_mp(int max_mp) {
		this.max_mp = max_mp;
	}


	public void printData() {
		System.out.printf("[%s]HP[%d/%d][%d]\n",this.job,this.hp,this.max_hp,this.power);
		if (this.max_mp != 0) {
			System.out.printf("    MP[%d/%d]\n",this.mp,this.max_mp);			
		}
	}
	
	public abstract void attack(Unit unit);
}
