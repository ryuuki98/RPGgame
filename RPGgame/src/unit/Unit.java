package unit;

import java.util.Collections;

public abstract class Unit {
	private String job;
	private int hp;
	private int max_hp;
	private int power;
	private int max_mp;
	private int mp;
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
	    String colorHP = "\033[31m"; // 빨간색
	    String colorMP = "\033[34m"; // 파란색
	    String reset = "\033[0m"; // 색상 초기화
	    String barColorHP = "\033[32m"; // 초록색
	    String barColorMP = "\033[34m"; // 파란색
	    String barReset = "\033[0m"; // 색상 초기화
	    
	    int barLength = 10; // HP 바와 MP 바의 길이
	    int barFillLengthHP = (int) (barLength * (hp / (float) max_hp));
	    int barFillLengthMP = (int) (barLength * (mp / (float) max_mp));
	    
	    if (max_mp != 0) {
	        System.out.println(job + " : " + colorHP + "HP : [" + String.join("", Collections.nCopies(barFillLengthHP, "#")) + "]" + reset + "  " + colorMP + "MP : [" + barColorMP + String.join("", Collections.nCopies(barFillLengthMP, "*")) + barReset + colorMP + "]" + reset + "    [" + power + "]");
	        System.out.println("              " + hp + "/" + max_hp + "            " + mp + "/" + max_mp);
	    } else {
	        System.out.println(job + " : " + colorHP + "HP : [" + String.join("", Collections.nCopies(barFillLengthHP, "#")) + "]" + reset + "    [" + power + "]");
	        System.out.println("             " + hp + "/" + max_hp);
	    }
	}
	
//	
//	public void printData() {
//	    System.out.printf("\033[31m[%s]\033[0m HP[%d/%d] [%d]\n", this.job, this.hp, this.max_hp, this.power);
//	    if (this.max_mp != 0) {
//	        System.out.printf("    MP[%d/%d]\n", this.mp, this.max_mp);
//	    }
//	    int hpBarLength = 10; // HP 바의 길이
//	    int hpBarFillLength = (int) (hpBarLength * (this.hp / (float) this.max_hp));
//	    System.out.printf("    HP: [%-" + hpBarLength + "s] (%d%%)\n",
//	            String.join("", Collections.nCopies(hpBarFillLength, "#")),
//	            (int) (hpBarFillLength * 100 / hpBarLength));
//	}
	
//
//	public void printData() {
//		System.out.printf("[%s]HP[%d/%d][%d]\n", this.job, this.hp, this.max_hp, this.power);
//		if (this.max_mp != 0) {
//			System.out.printf("    MP[%d/%d]\n", this.mp, this.max_mp);
//		}
//	}

	public abstract void attack(Unit unit);
}
