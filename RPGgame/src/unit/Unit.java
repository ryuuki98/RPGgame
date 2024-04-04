package unit;

public abstract class Unit {
	private String job;
	private int hp;
	private int max_hp;
	
	public Unit(String job, int hp) {
		this.job = job;
		this.hp = hp;
		this.max_hp = hp;
	}
	
	public String getJob() {
		return job;
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
	
	public abstract void attack(Unit unit);
}
