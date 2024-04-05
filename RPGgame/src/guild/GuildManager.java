package guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import rpggame.Game;
import rpggame.User;
import rpggame.UserManager;

public class GuildManager {
	private static HashMap<String, ArrayList<User>> guildList;
	private ArrayList<User> users = UserManager.getUsers();
	private int log = Game.getLog();
	private String key;

	public GuildManager() {
		guildList = new HashMap<String, ArrayList<User>>();
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
	
	private void joinGuildMenu() {
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
	        guildUsers.add(user);
	        // 유저 정보에 길드명을 저장
	        user.setGuildName(guildName);
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
}
