package unit;

import java.util.Random;

public abstract class Monster extends Unit{

	public Monster(String job) {
		super(job);
		this.setPower(new Random().nextInt(10) + 10);
	}
	
	
}
