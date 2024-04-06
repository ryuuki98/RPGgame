package shop;

import java.util.Scanner;

public class ShopManager {
	private final int BUY_ITEM = 1;
	private final int SELL_ITEM = 2;
	private final int END_SHOP = 0;
	private boolean isRun = true;
	
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
		System.out.println("아이템 사기 메뉴");
	}

	private void printShopMenu() {
		System.out.println("======SHOP======");
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
