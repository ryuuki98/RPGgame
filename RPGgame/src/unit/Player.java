package unit;

public abstract class Player extends Unit{
	
	public Player(String job, int hp) {
		super(job, hp);
	}
	
	abstract public void Skill(Unit unit);

}
