package rpggame;

import java.util.ArrayList;
import java.util.Scanner;


public class UserManager {
	private ArrayList<User> users;
	
	public UserManager() {
		users = new ArrayList<User>();
	}

	public void join() {
		String id = inputString("id");
		int index = findIndexById(id);
		if (index == -1) {
			String password = inputString("password");
			User user = new User(id,password);
			users.add(user);
			System.out.println("회원가입이 완료 되었습니다.");
		}else {
			System.out.println("중복 아이디 입니다.");
		}
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
