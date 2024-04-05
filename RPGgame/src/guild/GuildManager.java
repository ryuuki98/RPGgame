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

	public GuildManager() {
		guildList = new HashMap<String, ArrayList<User>>();
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
