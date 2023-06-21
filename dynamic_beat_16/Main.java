package dynamic_beat_16; // 16강 노트판정 디자인 입히기

public class Main {
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 5; // 노트 떨어지는 스피드
	public static final int SLEEP_TIME = 10; // 노트 간격 (떨어지는 주기) => 10이면 1초에 100번
	public static final int REACH_TIME = 2; // 판정바에 도달할 때 까지의 시간
	
	public static void main(String[] args) {
		new DynamicBeat();
	}
}