package rpggame;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerManager {
	private ArrayList<Player> players;
	
	public PlayerManager() {
		players = new ArrayList<Player>();
	}

	public void join() {
		String id = inputString("id");
		int index = findIndexById(id);
		if (index == -1) {
			String password = inputString("password");
		}
	}

	private String inputString(String message) {
		System.out.println(message + " : ");
		return new Scanner(System.in).next();
	}

	public void login() {
		// TODO Auto-generated method stub
		
	}

}
