package rpggame;

import java.util.Scanner;

public class Game {
	private final int JOIN = 1;
	private final int LOGIN = 2;
	private final int END_SYSTEM = 0;

	private Scanner scanner;
	
	private boolean isRun;
	private final String title = "======RPG GAME======";
	private int log;
	
	public Game() {
		isRun = true;
		scanner = new Scanner(System.in);
		log = -1;
	}
	
	public void run() {
		while (isRun) {
			
			printMenu();
			int select = inputNumber("menu");
			runMenu(select);
		}
	}

	private void runMenu(int select) {
		if (select == JOIN) {
		}else if (select == LOGIN) {
		}else if (select == END_SYSTEM) {
			isRun = false;
		}
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

	private void printMenu() {
		System.out.println(title);
		System.out.println("[1.회원가입]");
		System.out.println("[2.로그인]");
		System.out.println("[0.게임 종료]");
	}

}
