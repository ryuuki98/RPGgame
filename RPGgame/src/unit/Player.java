package unit;

public abstract class Player extends Unit{
	
	private int[] ItemCount = new int[5];
	
	public Player(String job, int hp, int power) {
		super(job);
		this.setHp(hp);
		this.setMax_hp(hp);
		this.setPower(power);
	}
	
	abstract public void Skill(Unit unit);

	
}
