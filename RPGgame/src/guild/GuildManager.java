package guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import rpggame.Game;
import rpggame.User;
import rpggame.UserManager;

public class GuildManager {
	private final int CREATE_GUILD = 1;
	private final int JOIN_GUILD = 2;
	private final int LEAVE_GUILD = 3;
	private HashMap<String, ArrayList<User>> guildList = new HashMap<String, ArrayList<User>>();
	private ArrayList<User> users = UserManager.getUserManager().getUsers();
	private int log = Game.getLog();
	private String key;

	public GuildManager(UserManager userManager) {
		key = "";
	}

	private void findMyGuild() {
		key = users.get(log).getGuildName();
	}

	public String getKey() {
		findMyGuild();
		return key;
	}

	public HashMap<String, ArrayList<User>> getGuildList() {
		return guildList;
	}

	// 길드 생성
	private void createGuild() {
		// 현재 로그인한 유저
		User user = users.get(log);
		if (!user.getGuildName().equals("")) {
			System.out.println("길드가 있으신 경우 길드를 새로 만드실 수 없습니다.");
			return;
		}

		String guildName = inputString("Guild name");
		// 길드명이 중복되지 않는경우
		if (!isDupilcated(guildName)) {

			// 해당 유저 정보에 길드명 저장
			user.setGuildName(guildName);
			// 신생 길드의 유저리스트
			ArrayList<User> temp = new ArrayList<User>();
			// 본인 가입
			temp.add(user);

			// 맵에 추가
			guildList.put(guildName, temp);
			System.out.println("길드 생성이 완료 되었습니다.");
		}
	}

	// 길드 가입
	private void joinGuildMenu() {
		if (guildList.size() == 0) {
			System.out.println("현재 길드가 존재하지 않습니다");
			return;
		}
		// 현재 로그인한 유저
		User user = users.get(log);
		if (!user.getGuildName().equals("")) {
			// 이미 길드에 소속되어 있는지 확인
			System.out.println("이미 길드에 소속되어 있습니다.");
			return;
		}

		// 가입할 수 있는 길드 목록
		System.out.println("가입할 수 있는 길드 목록:");
		int index = 1;
		for (String guildName : guildList.keySet()) {
			System.out.println(index + ". " + guildName);
			index++;
		}

		// 사용자 선택 메뉴
		System.out.println("가입할 길드의 번호를 입력하세요:");
		int guildIndex = inputIndex(1, guildList.size());

		// 입력받은 인덱스에 해당하는 길드에 가입
		joinGuild(guildIndex);
	}

	private void joinGuild(int guildIndex) {
		// 현재 로그인한 유저
		User user = users.get(log);

		// 입력받은 인덱스에 해당하는 길드 찾기
		String guildName = getGuildNameByIndex(guildIndex);

		if (guildName != null) {
			// 해당 길드에 유저를 추가
			ArrayList<User> guildUsers = guildList.get(guildName);
			user.setGuildName(guildName);
			guildUsers.add(user);
			// 유저 정보에 길드명을 저장
			System.out.println("길드 가입이 완료되었습니다.");
		} else {
			System.out.println("올바른 번호를 입력하세요.");
		}
	}

	private String getGuildNameByIndex(int index) {
		int count = 1;
		for (String guildName : guildList.keySet()) {
			if (count == index) {
				return guildName;
			}
			count++;
		}
		return null;
	}

	// 길드 탈퇴
	private void leaveGuild() {
		// 현재 로그인한 유저
		User user = users.get(log);
		String guildName = user.getGuildName();
		if (guildName.equals("")) {
			// 길드에 소속되어 있지 않은 경우
			System.out.println("길드에 소속되어 있지 않습니다.");
			return;
		}

		// 길드에서 해당 유저를 제거
		ArrayList<User> guildUsers = guildList.get(guildName);
		guildUsers.remove(user);
		// 유저정보에서 길드명을 초기화
		user.setGuildName("");
		System.out.println("길드 탈퇴가 완료되었습니다.");
	}

	private int inputIndex(int minIndex, int maxIndex) {
		Scanner scanner = new Scanner(System.in);
		int index;
		do {
			while (!scanner.hasNextInt()) {
				System.out.println("올바른 번호를 입력하세요.");
				scanner.next();
			}
			index = scanner.nextInt();
		} while (index < minIndex || index > maxIndex);
		return index;
	}

	private boolean isDupilcated(String guildName) {

		// 길드 리스트에서 이름을 키값으로 길드 유저들을 불러옴
		ArrayList<User> temp = guildList.get(guildName);

		if (temp != null) {
			return true;
		}
		return false;
	}

	private String inputString(String message) {
		System.out.println(message + " : ");
		return new Scanner(System.in).next();
	}

	public void run() {
		log = Game.getLog();
		printGuildMenu();
		int select = inputIndex(1, 3);
		runGuildMenu(select);
	}

	private void runGuildMenu(int select) {
		if (select == CREATE_GUILD) {
			createGuild();
		} else if (select == JOIN_GUILD) {
			joinGuildMenu();
		} else if (select == LEAVE_GUILD) {
			leaveGuild();
		}
	}

	private void printGuildMenu() {
		System.out.println("======GUILD======");
		System.out.println(
				"현재 가입중인 길드 : " + (users.get(log).getGuildName().equals("") ? "없음" : users.get(log).getGuildName()));
		System.out.println("1.길드 생성");
		System.out.println("2.길드 가입");
		System.out.println("3.길드 탈퇴");
	}
}
