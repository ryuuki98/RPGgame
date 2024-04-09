package storage;

import java.util.HashMap;
import java.util.Scanner;

import rpggame.Game;
import rpggame.User;
import rpggame.UserManager;
import unit.Player;

public class StorageManager {
	private final int DEPOSIT_GOLD = 1;
	private final int WITHDRAW_GOLD = 2;
	private String title = "======STORAGE======";
	private HashMap<String, Integer> storage;
	private UserManager userManager = UserManager.getUserManager();
	private static StorageManager storageManager = new StorageManager();
	private boolean isRun = true;

	private StorageManager() {
		storage = new HashMap<String, Integer>();
	}
	
	public static StorageManager getStorageManager() {
		return storageManager;
	}
	
	public HashMap<String, Integer> getStorage() {
		return storage;
	}

	public void run() {
		printStorage();
		int select = inputNumber("menu");
		runStorageMenu(select);
	}

	private void runStorageMenu(int select) {
		if (select == DEPOSIT_GOLD) {
			depositGold();
		} else if (select == WITHDRAW_GOLD) {
			withdrawGold();
		}
	}

	private Player getCurrentPlayer() {
		User user = userManager.getUsers().get(Game.getLog());
		Player player = user.getPlayer();
		return player;
	}

	private void withdrawGold() {
		User user = userManager.getUsers().get(Game.getLog());
		Player player = getCurrentPlayer();
		int cash = inputNumber("출금하실 골드를 입력하세요");
		if ((int) storage.get(user.getGuildName()) < cash) {
			System.out.println("창고의 잔액이 부족합니다.");
			return;
		}

		player.setMoney(player.getMoney() + cash);
		storage.put(user.getGuildName(), (Integer) ((int) storage.get(user.getGuildName()) - cash));
		System.out.println("출금이 완료 되었습니다.");
	}

	private void depositGold() {
		User user = userManager.getUsers().get(Game.getLog());
		Player player = getCurrentPlayer();
		System.out.println(title);
		int gold = inputNumber("예금하실 골드를 입력하세요");
		if (player.getMoney() < gold) {
			System.out.println("소지하신 금액이 부족합니다.");
			return;
		}

		player.setMoney(player.getMoney() - gold);
		storage.put(user.getGuildName(), (Integer) ((int) storage.get(user.getGuildName()) + gold));
		System.out.println("예금이 완료 되었습니다.");

	}

	private void printStorage() {
		User user = userManager.getUsers().get(Game.getLog());
		String guildName = user.getGuildName();
		if (storage.get(guildName) == null) {
			storage.put(guildName, 0);
		}
		System.out.println(title);
		System.out.println("[길드창고 골드 : " + storage.get(guildName) + "]");
		System.out.println("[소지골드 : " + user.getPlayer().getMoney() + "원]");
		System.out.println("[1.예금]");
		System.out.println("[2.출금]");
	}

	private int inputNumber(String message) {
		int number = -1;
		System.out.println(message + " : ");
		try {
			String numberString = new Scanner(System.in).next();
			number = Integer.parseInt(numberString);
		} catch (Exception e) {
			System.out.println("숫자를 입력하세요");
			return inputNumber(message);
		}
		return number;
	}
}
