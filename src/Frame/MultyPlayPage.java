package Frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import Client.Client;
import Code.MusicPlayer;
import Game.Bomb;
import Game.BomberMan;
import Player.PlayerQuery;

public class MultyPlayPage extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7474539039683830589L;
	private JPanel contentPane;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int witchNumber;
	public static boolean isHost;
	public static int numOfPlayers;
	public static int counter = 1;
	private JLabel username;
	public static JLabel Fields[][];
	public static String loc[][];
	public String[] watch = { "🕐", "🕑", "🕒", "🕓", "🕔", "🕕", "🕖", "🕗", "🕘", "🕙", "🕚", "🕛" };
	static int witchwatch = 0;
	static int minute = 0;
	static int second = 0;
	public static int bmi;
	public static int bmj;
	public static boolean loose = false;
	public static int lastDir;
	public static int rad = 1;
	JLabel showTime;
	JLabel time;
	JPanel panel;
	public JLabel msg;
	public static JLabel showPoint;
	public static JLabel showBomb;
	public static JLabel showSuper;
	public static JLabel showHarth;
	public static JLabel b1name;
	public static JLabel b1point;
	public static JLabel b2name;
	public static JLabel b2point;
	public static JLabel b3name;
	public static JLabel b3point;
	public static JLabel b4name;
	public static JLabel b4point;
	private static Socket socket = MainPage.socket;
	PlayerQuery pq = new PlayerQuery();
	BomberMan bm;
	public static Client client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultyPlayPage frame = new MultyPlayPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MultyPlayPage() {
		client = new Client(socket);
		client.start();
		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time.setText(watch[witchwatch]);
				witchwatch++;
				if (witchwatch == 12) {
					witchwatch = 0;
				}
				if (second < 10) {
					showTime.setText("0" + String.valueOf(minute) + " : 0" + String.valueOf(second));
				} else {
					showTime.setText("0" + String.valueOf(minute) + " : " + String.valueOf(second));
				}
				if (minute == 3) {
					if (isHost) {
						client.sendToServer("end");
					}
				} else if (minute == 2) {
					time.setForeground(Color.RED);
					showTime.setForeground(Color.RED);
				}
				second++;
				if (second == 60) {
					minute++;
					second = 0;
				}
			}
		});
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {
					String line = null;
					line = client.giveFromServer();
					System.out.println(line);
					String[] message = line.split("-");
					if (message[0].equals("new")) {
						if (isHost) {
							counter++;
							if (counter == numOfPlayers) {
								client.sendToServer(
										"startgame-" + pq.players().get(MainPage.currentPlayerId - 1).getUserName());
							}
						}
						if (Integer.parseInt(message[1]) == 2) {
							b2name.setText(message[2]);
							b2point.setText("0");
						} else if (Integer.parseInt(message[1]) == 3) {
							b3name.setText(message[2]);
							b3point.setText("0");
						} else if (Integer.parseInt(message[1]) == 4) {
							b3name.setText(message[2]);
							b3point.setText("0");
						}
					} else if (message[0].equals("generatefield")) {
						// msg.setText("wait for server");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// msg.setBounds(0, 0, 0, 0);
						String[] temp = message[1].split("\\|");
						int c = 0;
						for (int i = 0; i < 20; i++) {
							for (int j = 0; j < 20; j++) {
								loc[i][j] = temp[c];
								c++;
							}
						}
						for (int i = 0; i < 20; i++) {
							for (int j = 0; j < 20; j++) {
								if (loc[i][j].equals("ground")) {
									ImageIcon bg = new ImageIcon(new ImageIcon("img/onfield.png").getImage()
											.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
									Fields[i][j].setIcon(bg);
								} else if (loc[i][j].equals("block")) {
									ImageIcon bg = new ImageIcon(new ImageIcon("img/block.png").getImage()
											.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
									Fields[i][j].setIcon(bg);
								} else if (loc[i][j].equals("wall")) {
									ImageIcon bg = new ImageIcon(new ImageIcon("img/onblock.png").getImage()
											.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
									Fields[i][j].setIcon(bg);
								} else if (loc[i][j].equals("bombermanwhitefront")) {
									ImageIcon bg = new ImageIcon(
											new ImageIcon("img/online characters/bombermanwhitefront.png").getImage()
													.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
									Fields[i][j].setIcon(bg);
								} else if (loc[i][j].equals("bombermanblackfront")) {
									ImageIcon bg = new ImageIcon(
											new ImageIcon("img/online characters/bombermanblackfront.png").getImage()
													.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
									Fields[i][j].setIcon(bg);
								} else if (loc[i][j].equals("bombermanbluefront")) {
									ImageIcon bg = new ImageIcon(
											new ImageIcon("img/online characters/bombermanbluefront.png").getImage()
													.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
									Fields[i][j].setIcon(bg);
								} else if (loc[i][j].equals("bombermanredfront")) {
									ImageIcon bg = new ImageIcon(
											new ImageIcon("img/online characters/bombermanredfront.png").getImage()
													.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
									Fields[i][j].setIcon(bg);
								}
							}
						}
						bm = new BomberMan(true, witchNumber);
						if (witchNumber == 1) {
							bmi = 1;
							bmj = 1;
						} else if (witchNumber == 2) {
							bmi = 1;
							bmj = 18;
						} else if (witchNumber == 3) {
							bmi = 18;
							bmj = 1;
						} else if (witchNumber == 4) {
							bmi = 18;
							bmj = 18;
						}
						timer.start();
					} else if (message[0].equals("update")) {
						if (message[1].equals("bombermanwhiteback")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanwhiteback.png")
									.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "bombermanwhiteback";
							if (Integer.parseInt(message[4]) == 1) {
								b1point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 2) {
								b2point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 3) {
								b3point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 4) {
								b4point.setText(message[5]);
							}
						} else if (message[1].equals("bombermanwhitefront")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanwhitefront.png")
									.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "bombermanwhitefront";
							if (Integer.parseInt(message[4]) == 1) {
								b1point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 2) {
								b2point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 3) {
								b3point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 4) {
								b4point.setText(message[5]);
							}
						} else if (message[1].equals("bombermanwhiteright")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanwhiteright.png")
									.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "bombermanwhiteright";
							if (Integer.parseInt(message[4]) == 1) {
								b1point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 2) {
								b2point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 3) {
								b3point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 4) {
								b4point.setText(message[5]);
							}
						} else if (message[1].equals("bombermanwhiteleft")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanwhiteleft.png")
									.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "bombermanwhiteleft";
							if (Integer.parseInt(message[4]) == 1) {
								b1point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 2) {
								b2point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 3) {
								b3point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 4) {
								b4point.setText(message[5]);
							}
						} else if (message[1].equals("bombermanblackback")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblackback.png")
									.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "bombermanblackback";
							if (Integer.parseInt(message[4]) == 1) {
								b1point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 2) {
								b2point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 3) {
								b3point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 4) {
								b4point.setText(message[5]);
							}
						} else if (message[1].equals("bombermanblackfront")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblackfront.png")
									.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "bombermanblackfront";
							if (Integer.parseInt(message[4]) == 1) {
								b1point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 2) {
								b2point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 3) {
								b3point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 4) {
								b4point.setText(message[5]);
							}
						} else if (message[1].equals("bombermanblackleft")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblackleft.png")
									.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "bombermanblackleft";
							if (Integer.parseInt(message[4]) == 1) {
								b1point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 2) {
								b2point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 3) {
								b3point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 4) {
								b4point.setText(message[5]);
							}
						} else if (message[1].equals("bombermanblackright")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblackright.png")
									.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "bombermanblackright";
							if (Integer.parseInt(message[4]) == 1) {
								b1point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 2) {
								b2point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 3) {
								b3point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 4) {
								b4point.setText(message[5]);
							}
						} else if (message[1].equals("bombermanblueback")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblueback.png")
									.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "bombermanblueback";
							if (Integer.parseInt(message[4]) == 1) {
								b1point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 2) {
								b2point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 3) {
								b3point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 4) {
								b4point.setText(message[5]);
							}
						} else if (message[1].equals("bombermanbluefront")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanbluefront.png")
									.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "bombermanbluefront";
							if (Integer.parseInt(message[4]) == 1) {
								b1point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 2) {
								b2point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 3) {
								b3point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 4) {
								b4point.setText(message[5]);
							}
						} else if (message[1].equals("bombermanblueleft")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblueleft.png")
									.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "bombermanblueleft";
							if (Integer.parseInt(message[4]) == 1) {
								b1point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 2) {
								b2point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 3) {
								b3point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 4) {
								b4point.setText(message[5]);
							}
						} else if (message[1].equals("bombermanblueright")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblueright.png")
									.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "bombermanblueright";
							if (Integer.parseInt(message[4]) == 1) {
								b1point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 2) {
								b2point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 3) {
								b3point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 4) {
								b4point.setText(message[5]);
							}
						} else if (message[1].equals("bombermanredback")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanredback.png")
									.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "bombermanredback";
							if (Integer.parseInt(message[4]) == 1) {
								b1point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 2) {
								b2point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 3) {
								b3point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 4) {
								b4point.setText(message[5]);
							}
						} else if (message[1].equals("bombermanredfront")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanredfront.png")
									.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "bombermanredfront";
							if (Integer.parseInt(message[4]) == 1) {
								b1point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 2) {
								b2point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 3) {
								b3point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 4) {
								b4point.setText(message[5]);
							}
						} else if (message[1].equals("bombermanredleft")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanredleft.png")
									.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "bombermanredleft";
							if (Integer.parseInt(message[4]) == 1) {
								b1point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 2) {
								b2point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 3) {
								b3point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 4) {
								b4point.setText(message[5]);
							}
						} else if (message[1].equals("bombermanredright")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanredright.png")
									.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "bombermanredright";
							if (Integer.parseInt(message[4]) == 1) {
								b1point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 2) {
								b2point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 3) {
								b3point.setText(message[5]);
							} else if (Integer.parseInt(message[4]) == 4) {
								b4point.setText(message[5]);
							}
						} else if (message[1].equals("ground")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/onfield.png").getImage()
									.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "ground";
						} else if (message[1].equals("bomb")) {
							new Bomb(Integer.parseInt(message[2]), Integer.parseInt(message[3]),
									Integer.parseInt(message[4]), true).start();
						} else if (message[1].equals("point")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/onpoint.png").getImage()
									.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "point";
						} else if (message[1].equals("ubomb")) {
							ImageIcon bg = new ImageIcon(new ImageIcon("img/characters/firebrown.png").getImage()
									.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
							Fields[Integer.parseInt(message[2])][Integer.parseInt(message[3])].setIcon(bg);
							loc[Integer.parseInt(message[2])][Integer.parseInt(message[3])] = "ubomb";
						} else if (message[1].equals("pointt")) {
							if (message[2].equals("1")) {
								b1point.setText(String.valueOf(Integer.parseInt(b1point.getText()) + Integer.valueOf(message[3])));
							} else if (message[2].equals("2")) {
								b2point.setText(String.valueOf(Integer.parseInt(b2point.getText()) + Integer.valueOf(message[3])));
							} else if (message[2].equals("3")) {
								b3point.setText(String.valueOf(Integer.parseInt(b3point.getText()) + Integer.valueOf(message[3])));
							} else if (message[2].equals("4")) {
								b4point.setText(String.valueOf(Integer.parseInt(b4point.getText()) + Integer.valueOf(message[3])));
							}
						}
					} else if (message[0].equals("end")) {
						timer.stop();
						JOptionPane.showMessageDialog(null, "time is over :((((((((");
						loose = true;
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ResultScreenPage rsp = new ResultScreenPage();
						rsp.mod.setText("online game");
						rsp.showTime.setText(showTime.getText());
						int p1, p2, p3, p4;
						p1 = Integer.parseInt(b1point.getText());
						p2 = Integer.parseInt(b2point.getText());
						if (b3point.getText().equals("-")) {
							p3 = 0;
						} else {
							p3 = Integer.parseInt(b3point.getText());
						}
						if (b4point.getText().equals("-")) {
							p4 = 0;
						} else {
							p4 = Integer.parseInt(b4point.getText());
						}
						if (witchNumber == 1) {
							rsp.showPoint.setText(b1point.getText());
							if (p1 >= p2 && p1 >= p3 && p1 >= p4) {
								rsp.win.setText("you are the winner of battle :D");
							} else {
								rsp.win.setText("you loose the battle :((");
							}
						} else if (witchNumber == 2) {
							rsp.showPoint.setText(b2point.getText());
							if (p2 >= p1 && p2 >= p3 && p2 >= p4) {
								rsp.win.setText("you are the winner of battle :D");
							} else {
								rsp.win.setText("you loose the battle :((");
							}
						} else if (witchNumber == 3) {
							rsp.showPoint.setText(b3point.getText());
							if (p3 >= p2 && p3 >= p1 && p3 >= p4) {
								rsp.win.setText("you are the winner of battle :D");
							} else {
								rsp.win.setText("you loose the battle :((");
							}
						} else if (witchNumber == 4) {
							rsp.showPoint.setText(b4point.getText());
							if (p4 >= p2 && p4 >= p3 && p4 >= p1) {
								rsp.win.setText("you are the winner of battle :D");
							} else {
								rsp.win.setText("you loose the battle :((");
							}
						}
						rsp.setVisible(true);
						dispose();
					}
				}
			}
		});
		t.start();
		addKeyListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon iconLogo = new ImageIcon(new ImageIcon("img/SettingPage.png").getImage()
				.getScaledInstance((int) screenSize.getWidth(), (int) screenSize.getHeight(), Image.SCALE_SMOOTH));
		contentPane.setLayout(null);
		setUndecorated(true);

		JLabel back = new JLabel();
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				MainPage mpa = new MainPage();
				mpa.setVisible(true);
				dispose();
			}
		});
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setBounds(8, 14, 205, 80);
		back.setBorder(null);
		contentPane.add(back);

		JLabel profilePic = new JLabel();
		profilePic.setBounds(20, 110, 97, 97);
		contentPane.add(profilePic);
		ImageIcon pic = new ImageIcon(new ImageIcon(pq.players().get(MainPage.currentPlayerId - 1).getPic()).getImage()
				.getScaledInstance(97, 97, Image.SCALE_SMOOTH));
		profilePic.setIcon(pic);

		username = new JLabel("");
		username.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		username.setForeground(new Color(255, 204, 0));
		username.setBounds(20, 205, 126, 28);
		contentPane.add(username);
		username.setText(pq.players().get(MainPage.currentPlayerId - 1).getUserName());

		time = new JLabel("🕛");
		time.setForeground(Color.WHITE);
		time.setFont(time.getFont().deriveFont(22f));
		time.setBounds(30, 250, 100, 50);
		contentPane.add(time);

		showTime = new JLabel("00 : 00");
		showTime.setForeground(Color.WHITE);
		showTime.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		showTime.setBounds(63, 250, 100, 50);
		contentPane.add(showTime);

		JLabel b1 = new JLabel();
		b1.setBounds(30, 300, 30, 30);
		contentPane.add(b1);
		ImageIcon b11 = new ImageIcon(new ImageIcon("img/online characters/whitebomberman.png").getImage()
				.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		b1.setIcon(b11);

		b1name = new JLabel("-");
		b1name.setForeground(Color.WHITE);
		b1name.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		b1name.setBounds(73, 290, 100, 50);
		contentPane.add(b1name);

		b1point = new JLabel("-");
		b1point.setForeground(Color.WHITE);
		b1point.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		b1point.setBounds(173, 290, 100, 50);
		contentPane.add(b1point);

		if (isHost) {
			b1name.setText(pq.players().get(MainPage.currentPlayerId - 1).getUserName());
			b1point.setText("0");
		}

		JLabel b2 = new JLabel();
		b2.setBounds(30, 340, 30, 30);
		contentPane.add(b2);
		ImageIcon b22 = new ImageIcon(new ImageIcon("img/online characters/blackbomberman.png").getImage()
				.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		b2.setIcon(b22);

		b2name = new JLabel("-");
		b2name.setForeground(Color.WHITE);
		b2name.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		;
		b2name.setBounds(73, 330, 100, 50);
		contentPane.add(b2name);

		b2point = new JLabel("-");
		b2point.setForeground(Color.WHITE);
		b2point.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		b2point.setBounds(173, 330, 100, 50);
		contentPane.add(b2point);

		JLabel b3 = new JLabel();
		b3.setBounds(30, 380, 30, 30);
		contentPane.add(b3);
		ImageIcon b33 = new ImageIcon(new ImageIcon("img/online characters/bluebomberman.png").getImage()
				.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		b3.setIcon(b33);

		b3name = new JLabel("-");
		b3name.setForeground(Color.WHITE);
		b3name.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		b3name.setBounds(73, 370, 100, 50);
		contentPane.add(b3name);

		b3point = new JLabel("-");
		b3point.setForeground(Color.WHITE);
		b3point.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		b3point.setBounds(173, 370, 100, 50);
		contentPane.add(b3point);

		JLabel b4 = new JLabel();
		b4.setBounds(30, 420, 30, 30);
		contentPane.add(b4);
		ImageIcon b44 = new ImageIcon(new ImageIcon("img/online characters/redbomberman.png").getImage()
				.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		b4.setIcon(b44);

		b4name = new JLabel("-");
		b4name.setForeground(Color.WHITE);
		b4name.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		b4name.setBounds(73, 410, 100, 50);
		contentPane.add(b4name);

		b4point = new JLabel("-");
		b4point.setForeground(Color.WHITE);
		b4point.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		b4point.setBounds(173, 410, 100, 50);
		contentPane.add(b4point);

		msg = new JLabel();
		msg.setBackground(new Color(50, 50, 50));
		msg.setForeground(new Color(255, 204, 0));
		msg.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		msg.setBounds(700, 400, 500, 200);
		contentPane.add(msg);

		loc = new String[20][20];
		Fields = new JLabel[20][20];
		panel = new JPanel();
		panel.setBackground(new Color(50, 50, 50));
		panel.setBounds((int) (screenSize.getWidth()*14/100), 0,(int) (screenSize.getWidth() - (screenSize.getWidth()*14/100)), (int) screenSize.getHeight());
		contentPane.add(panel);
		panel.setLayout(new GridLayout(20, 20, 0, 0));
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				Fields[i][j] = new JLabel();
				Fields[i][j].setOpaque(true);
				Fields[i][j].setBackground(new Color(50, 50, 50));
				panel.add(Fields[i][j]);
			}
		}

		JLabel backGround = new JLabel("");
		backGround.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
		contentPane.add(backGround);
		backGround.setIcon(iconLogo);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP:
			if (!loose) {
				bm.moveUp();
			}
			break;
		case KeyEvent.VK_DOWN:
			if (!loose) {
				bm.moveDown();
			}
			break;
		case KeyEvent.VK_LEFT:
			if (!loose) {
				bm.moveLeft();
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (!loose) {
				bm.moveRight();
			}
			break;
		case KeyEvent.VK_SHIFT:
			if (lastDir == 1) {
				bm.placeBomb(bmi - 1, bmj, rad);
				client.sendToServer(
						"bomb-" + String.valueOf(rad) + "-" + String.valueOf(bmi - 1) + "-" + String.valueOf(bmj));
			} else if (lastDir == 2) {
				bm.placeBomb(bmi, bmj + 1, rad);
				client.sendToServer(
						"bomb-" + String.valueOf(rad) + "-" + String.valueOf(bmi) + "-" + String.valueOf(bmj + 1));
			} else if (lastDir == 3) {
				bm.placeBomb(bmi + 1, bmj, rad);
				client.sendToServer(
						"bomb-" + String.valueOf(rad) + "-" + String.valueOf(bmi + 1) + "-" + String.valueOf(bmj));
			} else if (lastDir == 4) {
				bm.placeBomb(bmi, bmj - 1, rad);
				client.sendToServer(
						"bomb-" + String.valueOf(rad) + "-" + String.valueOf(bmi) + "-" + String.valueOf(bmj - 1));
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
