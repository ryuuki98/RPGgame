package shop;

import java.util.ArrayList;
import java.util.Scanner;

import item.BuffItem;
import item.DebuffItem;
import item.Item;
import item.Potion;
import item.Shield;

public class ShopManager {
	private ArrayList<Item> itemList;
	private String title = "======SHOP======";
	
	private final int BUY_ITEM = 1;
	private final int SELL_ITEM = 2;
	private final int END_SHOP = 0;
	private boolean isRun = true;
	
	public ShopManager() {
		itemList = new ArrayList<Item>();
		itemList = createItemList();
		
	}
	
	private ArrayList<Item> createItemList() {
		ArrayList<Item>items = new ArrayList<Item>();
		
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
		}else if (select == SELL_ITEM) {
			sellItem();
		}
	}

	private void sellItem() {
		
		System.out.println("아이템 팔기 메뉴");
	}

	private void buyItem() {
		System.out.println(title);
		showItemList();
	}

	private void showItemList() {
		for (int i = 0; i < itemList.size(); i++) {
			Item item = itemList.get(i);
			System.out.printf("%d) %s - %d원\n",i, item.getName(), item.getPrice());
		}
	}

	private void printShopMenu() {
		System.out.println(title);
		System.out.println("[1.물건 구매]");
		System.out.println("[2.물건 판매]");
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
