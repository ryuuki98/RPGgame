package shop;

import java.util.ArrayList;
import java.util.Scanner;

import item.BuffItem;
import item.DebuffItem;
import item.Item;
import item.Potion;
import item.Shield;
import rpggame.Game;
import rpggame.User;
import rpggame.UserManager;
import unit.Player;

public class ShopManager {
	private ArrayList<Item> itemList;
	private UserManager userManager;
	private String title = "======SHOP======";

	private final int BUY_ITEM = 1;
	private final int SELL_ITEM = 2;
	private final int END_SHOP = 0;
	private boolean isRun = true;

	public ShopManager() {
		itemList = new ArrayList<Item>();
		itemList = createItemList();
		userManager = UserManager.getUserManager();
	}

	private ArrayList<Item> createItemList() {
		ArrayList<Item> items = new ArrayList<Item>();

		BuffItem buffItem = new BuffItem();
		items.add(buffItem);

		DebuffItem debuffItem = new DebuffItem();
		items.add(debuffItem);

		Potion potion = new Potion();
		items.add(potion);

		Shield shield = new Shield();
		items.add(shield);

		return items;
	}

	public void run() {
		while (true) {
			printShopMenu();
			int select = inputNumber("menu");
			if (select == END_SHOP) {
				break;
			}
			runShopMenu(select);

		}
	}

	private void runShopMenu(int select) {
		if (select == BUY_ITEM) {
			buyItem();
		} else if (select == SELL_ITEM) {
			sellItem();
		}
	}

	private void sellItem() {

		System.out.println("아이템 팔기 메뉴");
	}

	private void buyItem() {
		System.out.println(title);
		showItemList();
		int select = inputNumber("구매하실 물품의 번호를 입력하세요");
		addItemToPlayer(select);
	}

	private void addItemToPlayer(int select) {
		System.out.println(userManager.getUsers());
		
		// 현재 로그인한 유저의 캐릭터
		Player player = userManager.getUsers().get(Game.getLog()).getPlayer();

		// 선택한 아이템
		Item item = itemList.get(select);
		// 아이템의 이름
		String itemName = item.getName();

		// 소지금 부족시 구매 불가
		if (player.getMoney() - item.getPrice() < 0) {
			System.out.println("소지하신 금액이 부족합니다.");
			return;
		}

		// 맵에 수량 추가
		player.getItems().put(itemName, player.getItems().get(itemName) + 1);
		// 유저 보유 골드 차감
		player.setMoney(player.getMoney() - item.getPrice());
		System.out.println("구매가 완료 되었습니다.");

	}

	private void showItemList() {
		for (int i = 0; i < itemList.size(); i++) {
			Item item = itemList.get(i);
			System.out.printf("%d) %s - %d원\n", i, item.getName(), item.getPrice());
		}
	}

	private void printShopMenu() {
		System.out.println(title);
		System.out.println("[1.물건 구매]");
		System.out.println("[0.종료]");
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
