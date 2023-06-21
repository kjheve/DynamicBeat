package dynamic_beat_16;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	
	
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	
	private Image blueFlareImage;
	private Image judgeImage;
	
	
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
	}
	
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 228, 30, null); // 노트 S 경로바 
		g.drawImage(noteRouteDImage, 332, 30, null); // 노트 D 경로바
		g.drawImage(noteRouteFImage, 436, 30, null); // 노트 F 경로바
		g.drawImage(noteRouteSpace1Image, 540, 30, null); // 노트 Space1 경로바
		g.drawImage(noteRouteSpace2Image, 640, 30, null); // 노트 Space2 경로바
		g.drawImage(noteRouteJImage, 744, 30, null); // 노트 J 경로바
		g.drawImage(noteRouteKImage, 848, 30, null); // 노트 K 경로바
		g.drawImage(noteRouteLImage, 952, 30, null); // 노트 L 경로바
		g.drawImage(noteRouteLineImage, 224, 30, null); // 노트 경로바 구분선
		g.drawImage(noteRouteLineImage, 328, 30, null); // 노트 경로바 구분선
		g.drawImage(noteRouteLineImage, 432, 30, null); // 노트 경로바 구분선
		g.drawImage(noteRouteLineImage, 536, 30, null); // 노트 경로바 구분선
		g.drawImage(noteRouteLineImage, 740, 30, null); // 노트 경로바 구분선
		g.drawImage(noteRouteLineImage, 844, 30, null); // 노트 경로바 구분선
		g.drawImage(noteRouteLineImage, 948, 30, null); // 노트 경로바 구분선
		g.drawImage(noteRouteLineImage, 1052, 30, null); // 노트 경로바 구분선
		g.drawImage(gameInfoImage, 0, 660, null); // 게임 정보창 (제일 하단바)
		g.drawImage(judgementLineImage, 0, 580, null); // 게임 판정바
		
		
		for(int i = 0; i < noteList.size(); i++) // 노트 출력
		{
			Note note = noteList.get(i);
			
			if(note.getY() > 620) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
			}
			
			if(!note.isProceeded()) { // 현재 노트가 작동중이 아니면
				noteList.remove(i); // 지우기
				i--;
			} else {
				note.screenDraw(g);
			}
		}
			
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // 게임 하단 바 글자 안티앨리어싱 적용 Graphic2D를 이용
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		g.drawString(difficulty, 1190, 702);
		
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("000000", 565, 702);
		
		g.drawImage(blueFlareImage, 290, 290, null);
		g.drawImage(judgeImage, 505, 330, null);
		
	}
	
	public void pressS() { // S 키를 눌렸을 때
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start(); // 한번 눌렸을 때 반복 안해야 하기 때문에 false
	}
	public void releaseS() { // S 키를 땠을 때
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressD() { // D 키를 눌렸을 때
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	public void releaseD() { // D 키를 땠을 때
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressF() { // F 키를 눌렸을 때
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	public void releaseF() { // F 키를 땠을 때
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressSpace() { // 스페이스 키를 눌렸을 때
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumBig1.mp3", false).start();
	}
	public void releaseSpace() { // F 키를 땠을 때
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressJ() { // J 키를 눌렸을 때
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	public void releaseJ() { // J 키를 땠을 때
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressK() { // K 키를 눌렸을 때
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	public void releaseK() { // K 키를 땠을 때
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressL() { // L 키를 눌렸을 때
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	public void releaseL() { // L 키를 땠을 때
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	@Override
	public void run() {
		dropNotes(this.titleName); // 노트 출력
	}
	
	public void close() { // 뒤로가기 했을 때에도 음악이 안꺼지고 계속 흘러 나와서
		gameMusic.close();
		this.interrupt(); // Thread 종료하는 것을 만들어줌
	}
	
	public void dropNotes(String titleName) {
		Beat[] beats = null;
		if(titleName.equals("Alan Walker - Faded") && difficulty.equals("Easy")) {
			int startTime = 8000 - Main.REACH_TIME * 1000;
			
			int gap = 100; // 최소 박자의 갭
			
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					new Beat(startTime + gap * 6, "D"),
					new Beat(startTime + gap * 6, "K"),
					new Beat(startTime + gap * 8, "S"),
					new Beat(startTime + gap * 8, "L"),
					new Beat(startTime + gap * 10, "S"),
					new Beat(startTime + gap * 12, "D"),
					new Beat(startTime + gap * 14, "S"),
					new Beat(startTime + gap * 16, "D"),
					new Beat(startTime + gap * 18, "F"),
					new Beat(startTime + gap * 20, "K"),
					new Beat(startTime + gap * 22, "L"),
					new Beat(startTime + gap * 24, "K"),
					new Beat(startTime + gap * 26, "L"),
					new Beat(startTime + gap * 28, "K"),
					new Beat(startTime + gap * 30, "J"),
					new Beat(startTime + gap * 32, "Space"),
			};
		}
		else if(titleName.equals("Alan Walker - Faded") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
			};
		}
		else if(titleName.equals("Among Us - Theme") && difficulty.equals("Easy")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
			};
		}
		else if(titleName.equals("Among Us - Theme") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
			};
		}
		else if(titleName.equals("Better Now (Ver.TikTok)") && difficulty.equals("Easy")) {
			int startTime = 1900 - Main.REACH_TIME * 1000;;
			
			int gap = 2280;
			
			beats = new Beat[] {
					new Beat(startTime, "S"),
					new Beat(startTime+2760-gap ,"Space"),
					new Beat(startTime+3260-gap ,"L"),
					new Beat(startTime+3470-gap ,"J"),
					new Beat(startTime+3760-gap ,"K"),
					new Beat(startTime+4540-gap ,"S"),
					new Beat(startTime+4790-gap ,"F"),
					new Beat(startTime+5070-gap ,"D"),
					new Beat(startTime+5630-gap ,"S"),
					new Beat(startTime+5870-gap ,"D"),
					new Beat(startTime+6120-gap ,"F"),
					new Beat(startTime+6350-gap ,"Space"),
					new Beat(startTime+6550-gap ,"J"),
					new Beat(startTime+6800-gap ,"K"),
					new Beat(startTime+7010-gap ,"L"),
					new Beat(startTime+7250-gap ,"S"),
					new Beat(startTime+7520-gap ,"F"),
					new Beat(startTime+7780-gap ,"S"),
					new Beat(startTime+8490-gap ,"L"),
					new Beat(startTime+8700-gap ,"J"),
					new Beat(startTime+8930-gap ,"L"),
					new Beat(startTime+9600-gap ,"S"),
					new Beat(startTime+9840-gap ,"D"),
					new Beat(startTime+10090-gap ,"F"),
					new Beat(startTime+10360-gap ,"Space"),
					new Beat(startTime+10620-gap ,"J"),
					new Beat(startTime+10810-gap ,"K"),
					new Beat(startTime+11050-gap ,"L"),
					new Beat(startTime+11290-gap ,"K"),
					new Beat(startTime+11540-gap ,"J"),
					new Beat(startTime+12210-gap ,"L"),
					new Beat(startTime+12430-gap ,"K"),
					new Beat(startTime+12690-gap ,"J"),
					new Beat(startTime+13410-gap ,"S"),
					new Beat(startTime+13670-gap ,"D"),
					new Beat(startTime+14000-gap ,"L"),
					new Beat(startTime+14210-gap ,"K"),
					new Beat(startTime+14470-gap ,"L"),
					new Beat(startTime+14720-gap ,"S"),
					new Beat(startTime+15150-gap ,"D"),
					new Beat(startTime+15350-gap ,"S"),
					new Beat(startTime+15560-gap ,"D"),
					new Beat(startTime+15850-gap ,"L"),
					new Beat(startTime+16050-gap ,"K"),
					new Beat(startTime+16260-gap ,"L"),
					new Beat(startTime+16600-gap ,"S"),
					new Beat(startTime+16600-gap ,"L"),
					new Beat(startTime+16980-gap ,"K"),
					new Beat(startTime+16990-gap ,"D"),
					new Beat(startTime+18660-gap ,"F"),
					new Beat(startTime+18680-gap ,"J"),
					new Beat(startTime+18980-gap ,"D"),
					new Beat(startTime+18980-gap ,"K"),
					new Beat(startTime+19810-gap ,"Space"),
					new Beat(startTime+20070-gap ,"Space"),
			};
		}
		else if(titleName.equals("Better Now (Ver.TikTok)") && difficulty.equals("Hard")) {
			int startTime = 1900 - Main.REACH_TIME * 1000;;
			
			int gap = 2280;
			
			beats = new Beat[] {
					new Beat(startTime, "S"),
					new Beat(startTime, "L"),
					new Beat(startTime+2760-gap ,"Space"),
					new Beat(startTime+3260-gap ,"L"),
					new Beat(startTime+3470-gap ,"J"),
					new Beat(startTime+3760-gap ,"K"),
					new Beat(startTime+4540-gap ,"S"),
					new Beat(startTime+4790-gap ,"F"),
					new Beat(startTime+5070-gap ,"D"),
					new Beat(startTime+5630-gap ,"S"),
					new Beat(startTime+5870-gap ,"D"),
					new Beat(startTime+6120-gap ,"F"),
					new Beat(startTime+6350-gap ,"Space"),
					new Beat(startTime+6550-gap ,"J"),
					new Beat(startTime+6800-gap ,"K"),
					new Beat(startTime+7010-gap ,"L"),
					new Beat(startTime+7250-gap ,"S"),
					new Beat(startTime+7520-gap ,"F"),
					new Beat(startTime+7780-gap ,"S"),
					new Beat(startTime+8490-gap ,"L"),
					new Beat(startTime+8700-gap ,"J"),
					new Beat(startTime+8930-gap ,"L"),
					new Beat(startTime+9600-gap ,"S"),
					new Beat(startTime+9840-gap ,"D"),
					new Beat(startTime+10090-gap ,"F"),
					new Beat(startTime+10360-gap ,"Space"),
					new Beat(startTime+10620-gap ,"J"),
					new Beat(startTime+10810-gap ,"K"),
					new Beat(startTime+11050-gap ,"L"),
					new Beat(startTime+11290-gap ,"K"),
					new Beat(startTime+11540-gap ,"J"),
					new Beat(startTime+12210-gap ,"L"),
					new Beat(startTime+12430-gap ,"K"),
					new Beat(startTime+12690-gap ,"J"),
					new Beat(startTime+13410-gap ,"S"),
					new Beat(startTime+13670-gap ,"D"),
					new Beat(startTime+14000-gap ,"L"),
					new Beat(startTime+14210-gap ,"K"),
					new Beat(startTime+14470-gap ,"L"),
					new Beat(startTime+14720-gap ,"S"),
					new Beat(startTime+15150-gap ,"D"),
					new Beat(startTime+15350-gap ,"S"),
					new Beat(startTime+15560-gap ,"D"),
					new Beat(startTime+15850-gap ,"L"),
					new Beat(startTime+16050-gap ,"K"),
					new Beat(startTime+16260-gap ,"L"),
					new Beat(startTime+16600-gap ,"S"),
					new Beat(startTime+16600-gap ,"L"),
					new Beat(startTime+16980-gap ,"K"),
					new Beat(startTime+16990-gap ,"D"),
					new Beat(startTime+18660-gap ,"F"),
					new Beat(startTime+18680-gap ,"J"),
					new Beat(startTime+18980-gap ,"D"),
					new Beat(startTime+18980-gap ,"K"),
					new Beat(startTime+19600-gap ,"Space"),
					new Beat(startTime+19810-gap ,"Space"),
					new Beat(startTime+20070-gap ,"Space")		
			};
		}
			
		int i = 0;
		
		gameMusic.start();
		
		while(i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void judge(String input) {
		for(int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}
	
	public void judgeEvent(String judge) {
		if(!judge.equals("None")) {
			blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
		}
		if(judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage(); 
		}
		else if(judge.equals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeLate.png")).getImage();
		}
		else if(judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();
		}
		else if(judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGreat.png")).getImage();
		}
		else if(judge.equals("Perfect")) {

			judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.png")).getImage();
		}
		else if(judge.equals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeEarly.png")).getImage();
		}
	}
}
