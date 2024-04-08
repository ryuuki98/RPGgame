package rpggame;

import java.util.ArrayList;
import java.util.Scanner;

import unit.Healer;
import unit.Player;
import unit.Warrior;
import unit.Wizard;


public class UserManager {
	
	private static UserManager userManager = new UserManager();
	private final int WARRIOR = 0;
	private final int MAGICIAN = 1;
	private final int HEALER = 2;

	private Scanner scanner = new Scanner(System.in);
	
	private ArrayList<User> users;
;
	private String[] jobList = {"전사","법사","치료사"};
	
	private UserManager() {
		users = new ArrayList<User>();
	}
	
	public static UserManager getUserManager() {
		return userManager;
	}
	
	
	
	public ArrayList<User> getUsers() {
		return users;
	}

	public void join() {
		String id = inputString("id");
		int index = findIndexById(id);
		if (index == -1) {
			String password = inputString("password");
			Player player = chooseJob();
			User user = new User(id,password,player);
			users.add(user);
			System.out.println("회원가입이 완료 되었습니다.");
		}else {
			System.out.println("중복 아이디 입니다.");
		}
	}

	private Player chooseJob() {
		System.out.println("======[CLASS LIST]======");
		Player player = null;
		for (int i = 0; i < jobList.length; i++) {
			System.out.println(i + ") " +jobList[i]);
		}
		int index = inputNumber("직업을 선택하세요");
		if (index<0 ||index >= jobList.length) {
			return chooseJob();
		}
		
		if (index == WARRIOR) {
			player = new Warrior();
		}else if (index == MAGICIAN) {
			player = new Wizard();
		}else if (index == HEALER) {
			player = new Healer();
		}
		return player;
	}

	private int inputNumber(String message) {
		int number = -1;
		System.out.println(message + " : ");
		try {
			String numberString = scanner.next();
			number = Integer.parseInt(numberString);
		} catch (Exception e) {
			System.out.println("숫자를 입력하세요");
			return inputNumber(message);
		}
		return number;
	}
	private int findIndexById(String id) {
		int index = -1;
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			if (user.getId().equals(id)) {
				index = i;
			}
		}
		return index;
	}

	private String inputString(String message) {
		System.out.println(message + " : ");
		return new Scanner(System.in).next();
	}

	public void login() {
		String id = inputString("id");
		int index = findIndexById(id);
		if (index != -1) {
			User user = users.get(index);
			String password = inputString("password");
			if (isValid(user,password)) {
				System.out.println("로그인 되었습니다.");
				Game.setLog(index);
			}else {
				System.out.println("비밀번호를 확인하세요.");
			}
		}else {
			System.out.println("해당 유저가 존재하지 않습니다.");
		}
	}

	private boolean isValid(User user, String password) {
		if (user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

}
