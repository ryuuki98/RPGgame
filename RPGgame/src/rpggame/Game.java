package rpggame;

import java.util.ArrayList;
import java.util.Scanner;

import guild.GuildManager;
import item.Shop;
import shop.ShopManager;
import stage.StageManager;
import storage.StorageManager;

public class Game {
	private final int JOIN = 1;
	private final int LOGIN = 2;
	private final int END_SYSTEM = 0;
	private final int LOGOUT = 5;

	private final int BATTLE = 1;
	private final int GUILD = 2;
	private final int SHOP = 3;
	private final int STORAGE = 4;

	private Scanner scanner;
	private UserManager userManager;
	private GuildManager guildManager;
	private ShopManager shopManager;
	private StageManager stageManager;
	private StorageManager storageManager;
	private FileManager fileManager;
	
	private ArrayList<StageManager> stages;
	
	private boolean isRun;
	private final String title = "======RPG GAME======";
	private static int log;
	
	public Game() {
		fileManager = new FileManager();
		storageManager = StorageManager.getStorageManager();
		shopManager = new ShopManager();
		userManager = UserManager.getUserManager();
		guildManager = GuildManager.getGuildManager();
		stageManager = new StageManager(userManager, guildManager);
		isRun = true;
		scanner = new Scanner(System.in);
		stages = new ArrayList<>();
		log = -1;
	}
	public static void setLog(int log) {
		Game.log = log;
	}
	public static int getLog() {
		return log;
	}
	
	
	
	public void run() {
		while (isRun) {
			
			if (!isLogin()) {
				printMenu();
				int select = inputNumber("menu");
				runMenu(select);
				if(isLogin()) {
					stageManager = new StageManager(userManager, guildManager);
					// 로그인 할 때 배틀 스테이지 초기화
				}
			}else {
				printSubMenu();
				int select = inputNumber("menu");
				if(select == LOGOUT) {
					log = -1;
				}else {
					runStage(select);
				}
			}
		}
	}
	
	
	private void runStage(int select) {
		if(select == BATTLE) {
			stageManager.run();
		}else if(select == GUILD) {
			guildManager.run();
		}else if(select == SHOP) {
			shopManager.run();
		}else if (select == STORAGE) {
			if (userManager.getUsers().get(log).getGuildName().equals("")) {
				System.out.println("해당 메뉴는 길드에 가입되어 있어야 이용 가능합니다.");
				return;
			}
			storageManager.run();
		}
	}
	
	private void printSubMenu() {
		System.out.println(title);
		System.out.println("=====유저목록=====");
		for (int i = 0; i < userManager.getUsers().size(); i++) {
			System.out.println(userManager.getUsers().get(i));
		}
		System.out.println("현재 로그인 : " + userManager.getUsers().get(log).getId());
		System.out.println("[1]배틀 [2]길드 [3]상점 [4]길드창고 [5]로그아웃");
	}

	private boolean isLogin() {
		return log == -1 ? false : true;
	}
	private void runMenu(int select) {
		if (select == JOIN) {
			userManager.join();
		}else if (select == LOGIN) {
			userManager.login();
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
