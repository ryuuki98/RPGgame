package rpggame;

import java.util.ArrayList;
import java.util.Scanner;

import unit.Player;

public class PlayerManager {
	private ArrayList<Player> players;
	
	public PlayerManager() {
		players = new ArrayList<Player>();
	}

	public void join() {
	
	}

	private String inputString(String message) {
		System.out.println(message + " : ");
		return new Scanner(System.in).next();
	}

	public void login() {
		// TODO Auto-generated method stub
		
	}

}
