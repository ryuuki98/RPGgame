package stage;

public class StageManager {
	private int count;
	
	public StageManager() {
		count = 1;
	}
	
	public void run() {
		while(true) {
			Battle battle = new Normal();
			if(count < 10) {
				System.out.printf("[%d 스테이지 입장]",count);
				battle.init();
				if(!battle.update()) {
					System.out.println("[레이드 실패]");
					break;
				}
				System.out.printf("[%d 스테이지 클리어]\n",count);
			}else {
				System.out.println("[보스 스테이지 입장]");
				battle = new Boss();
				battle.init();
				if(!battle.update()) {
					System.out.println("[레이드 실패]");
					break;
				}
				System.out.println("[레이드 성공]");
				break;
			}
			count++;
		}
	}
}
