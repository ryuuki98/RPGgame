package stage;

import java.util.Scanner;

public abstract class Battle {
	public Scanner sc = new Scanner(System.in);
	
	public int inputNumber() {
		while(true) {
			System.out.print(": ");
			String select =sc.next();
			int number = -1;
			try {
				number = Integer.parseInt(select);				
			} catch (Exception e) {
				System.err.println("숫자를 입력하세요.");
			}
			if(number == 1||number == 2) {
				return number;
			}else {
				System.err.println("잘못된 메뉴");
			}
		}
	}
	
	public abstract boolean update();
	public abstract void init();
}
