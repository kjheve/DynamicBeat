package dynamic_beat_16;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	
	private Image background = new ImageIcon(Main.class.getResource("../images/introbackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private JButton exitButton = new JButton(exitButtonBasicImage);

	private JButton startButton = new JButton(startButtonBasicImage); // 스타트 버튼 추가
	private JButton quitButton = new JButton(quitButtonBasicImage); // 나가는 버튼 추가

	private JButton leftButton = new JButton(leftButtonBasicImage); // 왼쪽 버튼
	private JButton rightButton = new JButton(rightButtonBasicImage); // 오른쪽 버튼
	
	private JButton easyButton = new JButton(easyButtonBasicImage); // easy 버튼 변수
	private JButton hardButton = new JButton(hardButtonBasicImage); // hard 버튼 변수
	
	private JButton backButton = new JButton(backButtonBasicImage); // 뒤로가기 버튼

	private int mouseX, mouseY; // 메뉴바를 드래그 할 때 변경 될 때 만들기 시작

	private boolean isMainScreen = false; // 처음에는 메인화면이 아니니 false 값을 가짐
	
	private boolean isGameScreen = false; // 현재 게임 화면으로 넘어왔는지에 대한 변수
	
	ArrayList<Track> trackList = new ArrayList<Track>();

	private Image titleImage;
	private Image selectedImage;

	private Music selectedMusic;
	
	private Music introMusic = new Music("introMusic.mp3", true);
	
	private int nowSelected = 0; // ArrayList 인덱스 초기값 0
	
	public static Game game; // 게임이 실행 할 때 단 하나의 게임만 가능하니깐 public static을 넣어 준다.

	public DynamicBeat() {
		
		// ArrayList 0~2 까지의 값
		trackList.add(new Track("alanTitleImage.png", "alanStartImage.png", "alanGameImage.jpg", "alanWalkerFadedSelected.mp3", "alanWalkerFaded.mp3", "Alan Walker - Faded"));
		trackList.add(new Track("amongTitleImage.png", "amongStartImage.png", "amongGameImage.jpg", "amongUsThemeSongSelected.mp3", "amongUsThemeSong.mp3", "Among Us - Theme"));
		trackList.add(new Track("betterTitleImage.png", "betterStartImage.png", "betterGameImage.jpg", "postMaloneBetterNowSelected.mp3", "postMaloneBetterNow.mp3", "Better Now (Ver.TikTok)"));
		
		setUndecorated(true); // 메뉴바 없애기
		setTitle("Dynamic Sim Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0)); // paintComponents를 했을 때 배경이 회색이 아니라 전부 하얀색으로 바꾼다
		setLayout(null); // 버튼이나 라벨 넣었을 때 그 위치에 넣기
		
		
		addKeyListener(new KeyListener()); // 패키지 안넣으면 왜 오류 뜸? 시발!! => import 수정 하니깐 고쳐진듯?
		
		introMusic.start(); // 인트로 뮤직 시작
		
		exitButton.setBounds(1245, 0, 30, 30); // 오른쪽 상단 Exit 버튼
		exitButton.setBorderPainted(false); // 이미지 PNG파일 제대로 넣기
		exitButton.setContentAreaFilled(false); // 이미지 PNG파일 제대로 넣기
		exitButton.setFocusable(false); // 이미지 PNG파일 제대로 넣기
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼이 마우스가 올라갈 때 커서가 바뀜
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 버튼을 마우스 커서 올렸을 때 음악 실행 false는
																						// 한번만 실행
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서가 바뀐 거에서 원래대로 되돌림
			}

			@Override
			public void mousePressed(MouseEvent e) { // 버튼을 클릭했을 때
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				try {
					Thread.sleep(1000); // 버튼을 누르자마자 종료가 되면 음악이 안나오고 종료가 되기 때문에 쓰레드를 사용, 1초 후에 종료되게 만듬
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);

		startButton.setBounds(440, 230, 400, 100); // Solo Play 버튼
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusable(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼이 마우스가 올라갈 때 커서가 바뀜
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 버튼을 마우스 커서 올렸을 때 음악 실행 false는
																						// 한번만 실행
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서가 바뀐 거에서 원래대로 되돌림
			}

			@Override
			public void mousePressed(MouseEvent e) { // 버튼을 클릭했을 때
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				// ★게임 시작 이벤트★
				enterMain();
			}
		});
		add(startButton);

		quitButton.setBounds(435, 410, 400, 100); // Good Night 버튼
		quitButton.setBorderPainted(false); // 이미지 PNG파일 제대로 넣기
		quitButton.setContentAreaFilled(false); // 이미지 PNG파일 제대로 넣기
		quitButton.setFocusable(false); // 이미지 PNG파일 제대로 넣기
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼이 마우스가 올라갈 때 커서가 바뀜
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 버튼을 마우스 커서 올렸을 때 음악 실행 false는
																						// 한번만 실행
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서가 바뀐 거에서 원래대로 되돌림
			}

			@Override
			public void mousePressed(MouseEvent e) { // 버튼을 클릭했을 때
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				try {
					Thread.sleep(1000); // 버튼을 누르자마자 종료가 되면 음악이 안나오고 종료가 되기 때문에 쓰레드를 사용, 1초 후에 종료되게 만듬
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);

		leftButton.setVisible(false); // 첫 화면에는 안보여야 하기 때문에 setVisible_false
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false); // 이미지 PNG파일 제대로 넣기
		leftButton.setContentAreaFilled(false); // 이미지 PNG파일 제대로 넣기
		leftButton.setFocusable(false); // 이미지 PNG파일 제대로 넣기
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼이 마우스가 올라갈 때 커서가 바뀜
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 버튼을 마우스 커서 올렸을 때 음악 실행 false는
																						// 한번만 실행
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서가 바뀐 거에서 원래대로 되돌림
			}

			@Override
			public void mousePressed(MouseEvent e) { // 버튼을 클릭했을 때
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				// 왼쪽 버튼 이벤트
				selectLeft();
			}
		});
		add(leftButton);

		rightButton.setVisible(false); // 첫 화면에는 안보여야 하기 때문에 false
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false); // 이미지 PNG파일 제대로 넣기
		rightButton.setContentAreaFilled(false); // 이미지 PNG파일 제대로 넣기
		rightButton.setFocusable(false); // 이미지 PNG파일 제대로 넣기
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼이 마우스가 올라갈 때 커서가 바뀜
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 버튼을 마우스 커서 올렸을 때 음악 실행 false는 한번만 실행
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서가 바뀐 거에서 원래대로 되돌림
			}

			@Override
			public void mousePressed(MouseEvent e) { // 버튼을 클릭했을 때
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				// 오른쪽 버튼 이벤트
				selectRight();
			}
		});
		add(rightButton);
		
		backButton.setVisible(false); // 첫 화면에는 안보여야 하기 때문에 false
		backButton.setBounds(1215, 0, 30, 30); // 오른쪽 상단 뒤로가기 버튼
		backButton.setBorderPainted(false); // 이미지 PNG파일 제대로 넣기
		backButton.setContentAreaFilled(false); // 이미지 PNG파일 제대로 넣기
		backButton.setFocusable(false); // 이미지 PNG파일 제대로 넣기
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼이 마우스가 올라갈 때 커서가 바뀜
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 버튼을 마우스 커서 올렸을 때 음악 실행 false는 한번만 실행
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서가 바뀐 거에서 원래대로 되돌림
			}

			@Override
			public void mousePressed(MouseEvent e) { // 버튼을 클릭했을 때
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				// 뒤로가기 이벤트 (선택 화면 이벤트)
				backMain();
				
			}
		});
		add(backButton);

		menuBar.setBounds(0, 0, 1280, 30); // 위치와 크기 정하기
		menuBar.addMouseListener(new MouseAdapter() { // 메뉴바 잡고 이동 시키기
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() { // 메뉴바 잡고 이동 시키기2
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);
		
		easyButton.setVisible(false); // 첫 화면에는 안보여야 하기 때문에 false
		easyButton.setBounds(225, 580, 250, 67);
		easyButton.setBorderPainted(false); // 이미지 PNG파일 제대로 넣기
		easyButton.setContentAreaFilled(false); // 이미지 PNG파일 제대로 넣기
		easyButton.setFocusable(false); // 이미지 PNG파일 제대로 넣기
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼이 마우스가 올라갈 때 커서가 바뀜
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 버튼을 마우스 커서 올렸을 때 음악 실행 false는
																						// 한번만 실행
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서가 바뀐 거에서 원래대로 되돌림
			}

			@Override
			public void mousePressed(MouseEvent e) { // 버튼을 클릭했을 때
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				// 난이도 쉬움 이벤트
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);
		
		hardButton.setVisible(false); // 첫 화면에는 안보여야 하기 때문에 false
		hardButton.setBounds(785, 580, 250, 67);
		hardButton.setBorderPainted(false); // 이미지 PNG파일 제대로 넣기
		hardButton.setContentAreaFilled(false); // 이미지 PNG파일 제대로 넣기
		hardButton.setFocusable(false); // 이미지 PNG파일 제대로 넣기
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼이 마우스가 올라갈 때 커서가 바뀜
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 버튼을 마우스 커서 올렸을 때 음악 실행 false는
																						// 한번만 실행
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서가 바뀐 거에서 원래대로 되돌림
			}

			@Override
			public void mousePressed(MouseEvent e) { // 버튼을 클릭했을 때
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				// 난이도 어려움 이벤트
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);
		
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		// screenDraw(screenGraphic) 게임 정보 창 화면에 글씨가 깨져서 보이는거 부드럽게 보이게 만들려면 Graphics2D 하고 import를 해준다.
		// 밑에 screeDraw 함수에 Graphics에서 Graphics2D로 바꿔주고
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) { // 역동적인 이미지는 드로우 함수, 고정적인 것은 paintComponents 함수
		g.drawImage(background, 0, 0, null);

		if (isMainScreen) {
			g.drawImage(selectedImage, 340, 100, null); // 곡 선택 이미지 넣기
			g.drawImage(titleImage, 340, 70, null);
		}
		
		if (isGameScreen) {
			game.screenDraw(g); // Game(.java) Class를 불러 온다 
		}
		

		paintComponents(g); // 위에 add를 통해서 추가 된다
		
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.repaint();
	}
	
	public void selectTrack(int nowSelected) { // Track.class를 만들고 ArrayList를 활용
		if(selectedMusic != null)
			selectedMusic.close();
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}
	
	public void selectLeft() { // 왼쪽 버튼 함수
		if(nowSelected == 0) // 0번 째 (첫번 째) 곡 일 때
			nowSelected = trackList.size() - 1; // 가장 오른쪽 일 때
		else
			nowSelected--;
		selectTrack(nowSelected);
	}

	public void selectRight() { // 오른쪽 버튼 함수
		if(nowSelected == trackList.size() - 1) // 가장 오른쪽 일 때
			nowSelected = 0; // 첫번째 곡으로 만들 기
		else
			nowSelected++;
		selectTrack(nowSelected);
	}

	
	public void gameStart(int nowSelected, String difficulty) {
		if(selectedMusic != null)
			selectedMusic.close();
		isMainScreen = false;
		leftButton.setVisible(false); // 게임시작 하면 버튼들이 안보여야함
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/"  + trackList.get(nowSelected).getGameImage())).getImage();
		backButton.setVisible(true);
		isGameScreen = true;
		
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
		game.start();
		
		setFocusable(true); // 키보드 포커스 맞추기
	}
	
	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true); // 게임시작 버튼 으로 돌아가면 메뉴들이 보여야함
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen = false;
		game.close(); // 뒤로가기 했을 때 음악 종료하기
	}
	
	public void enterMain() {
		
		startButton.setVisible(false); // 시작(Solo Play)버튼 없애기
		quitButton.setVisible(false); // GoodNight 버튼 없애기
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		isMainScreen = true;
		leftButton.setVisible(true); // 게임 시작 하면 왼쪽 버튼 보이기
		rightButton.setVisible(true); // 오른쪽 버튼 보이기
		easyButton.setVisible(true); // 이지 버튼 보이기
		hardButton.setVisible(true); // 하드 버튼 보이기
		introMusic.close(); // 시작 버튼을 누르면 게임 실행 시 음악은 꺼짐
		selectTrack(0);
	}
	
}