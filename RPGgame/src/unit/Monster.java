package unit;

public abstract class Monster extends Unit{

	public Monster(String job, int hp) {
		super(job, hp);
	}
	
	@Override
	public void attack(Unit unit) {
		
	}
}
