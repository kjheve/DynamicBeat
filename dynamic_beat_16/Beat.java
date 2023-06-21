package dynamic_beat_16;

public class Beat { // 박자 타이밍, 노트의 종류
	private int time;
	private String noteName;

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	public Beat(int time, String noteName) {
		super();
		this.time = time;
		this.noteName = noteName;
	}
}

// Getters와 Setters를 만들고 생성자를 만들고
// Note Class로 이동한다
