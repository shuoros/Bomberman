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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import Code.MusicPlayer;
import Game.BomberMan;
import Game.Field;
import Game.Ghost;
import Player.PlayerQuery;

public class SinglePlayPage extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3977531676857620607L;
	private JPanel contentPane;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JLabel username;
	public static JLabel Fields[][];
	public static String loc[][];
	public Field field = new Field(type);
	public static String type;
	public static int rad = 1;
	public static int ghosts;
	public static boolean loose = false;
	public static int bmi;
	public static int bmj;
	public static int lastDir;
	public String[] watch = { "🕐", "🕑", "🕒", "🕓", "🕔", "🕕", "🕖", "🕗", "🕘", "🕙", "🕚", "🕛" };
	static int witchwatch = 0;
	static int minute = 0;
	static int second = 0;
	JLabel showTime;
	JLabel time;
	public static JLabel showPoint;
	public static JLabel showBomb;
	public static JLabel showSuper;
	public static JLabel showHarth;
	PlayerQuery pq = new PlayerQuery();
	BomberMan bm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SinglePlayPage frame = new SinglePlayPage();
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
	public SinglePlayPage() {

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

				}
				second++;
				if (second == 60) {
					minute++;
					second = 0;
				}
			}
		});
		timer.start();

		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("im in thread t");
				while (true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (ghosts == 0) {
						JOptionPane.showMessageDialog(null, "you win :D");
						timer.stop();
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						};
						ResultScreenPage rsp = new ResultScreenPage();
						rsp.mod.setText(type + " Mode");
						rsp.showTime.setText(showTime.getText());
						rsp.showPoint.setText(showPoint.getText());
						rsp.win.setText("you win :D");
						rsp.setVisible(true);
						dispose();
					} else if (Integer.valueOf(showHarth.getText()) == 0) {
						System.out.println("im dead");
						loose = true;
						ImageIcon bg1 = new ImageIcon(new ImageIcon("img/characters/bombermandeadgreen.png").getImage()
								.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
						ImageIcon bg2 = new ImageIcon(new ImageIcon("img/characters/bombermandeadbrown.png").getImage()
								.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
						if (loc[bmi][bmj].equals("BMgreen")) {
							Fields[bmi][bmj].setIcon(bg1);
							loc[bmi][bmj] = "dead";
						} else if (loc[bmi][bmj].equals("BMbrown")) {
							Fields[bmi][bmj].setIcon(bg2);
							loc[bmi][bmj] = "dead";
						}
						JOptionPane.showMessageDialog(null, "you died D:");
						timer.stop();
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ResultScreenPage rsp = new ResultScreenPage();
						rsp.mod.setText(type + " Mode");
						rsp.showTime.setText(showTime.getText());
						rsp.showPoint.setText(showPoint.getText());
						rsp.win.setText("you died D:");
						rsp.setVisible(true);
						dispose();
						break;
					} else if (type.equals("Eazy")) {
						if (minute == 3) {
							time.setForeground(Color.RED);
							showTime.setForeground(Color.RED);
						} else if (minute == 4) {
							JOptionPane.showMessageDialog(null, "time is over :((((((((");
							timer.stop();
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							ResultScreenPage rsp = new ResultScreenPage();
							rsp.mod.setText(type + " Mode");
							rsp.showTime.setText(showTime.getText());
							rsp.showPoint.setText(showPoint.getText());
							rsp.win.setText("time is over :((((((((");
							rsp.setVisible(true);
							dispose();
							break;
						}
					} else if (type.equals("Normal")) {
						if (minute == 2) {
							time.setForeground(Color.RED);
							showTime.setForeground(Color.RED);
						} else if (minute == 3) {
							JOptionPane.showMessageDialog(null, "time is over :((((((((");
							timer.stop();
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							ResultScreenPage rsp = new ResultScreenPage();
							rsp.mod.setText(type + " Mode");
							rsp.showTime.setText(showTime.getText());
							rsp.showPoint.setText(showPoint.getText());
							rsp.win.setText("time is over :((((((((");
							rsp.setVisible(true);
							dispose();
							break;
						}
					} else if (type.equals("Hard")) {
						if (minute == 1) {
							time.setForeground(Color.RED);
							showTime.setForeground(Color.RED);
						} else if (minute == 2) {
							JOptionPane.showMessageDialog(null, "time is over :((((((((");
							timer.stop();
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							ResultScreenPage rsp = new ResultScreenPage();
							rsp.mod.setText(type + " Mode");
							rsp.showTime.setText(showTime.getText());
							rsp.showPoint.setText(showPoint.getText());
							rsp.win.setText("time is over :((((((((");
							rsp.setVisible(true);
							dispose();
							break;
						}
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

		time = new JLabel();
		time.setForeground(Color.WHITE);
		time.setFont(time.getFont().deriveFont(22f));
		time.setBounds(30, 250, 100, 50);
		contentPane.add(time);

		showTime = new JLabel();
		showTime.setForeground(Color.WHITE);
		showTime.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		showTime.setBounds(63, 250, 100, 50);
		contentPane.add(showTime);

		JLabel pointlbl = new JLabel("💎");
		pointlbl.setForeground(Color.blue);
		pointlbl.setFont(pointlbl.getFont().deriveFont(22f));
		pointlbl.setBounds(30, 290, 100, 50);
		contentPane.add(pointlbl);

		showPoint = new JLabel("0");
		showPoint.setForeground(Color.WHITE);
		showPoint.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		showPoint.setBounds(63, 290, 100, 50);
		contentPane.add(showPoint);

		JLabel harth = new JLabel("❤");
		harth.setForeground(Color.RED);
		harth.setFont(harth.getFont().deriveFont(22f));
		harth.setBounds(30, 330, 100, 50);
		contentPane.add(harth);

		showHarth = new JLabel();
		showHarth.setForeground(Color.WHITE);
		showHarth.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		showHarth.setBounds(63, 330, 100, 50);
		contentPane.add(showHarth);
		showHarth.setText("100");

		JLabel bomb = new JLabel("💣");
		bomb.setForeground(Color.orange);
		bomb.setFont(bomb.getFont().deriveFont(22f));
		bomb.setBounds(30, 370, 100, 50);
		contentPane.add(bomb);

		showBomb = new JLabel();
		showBomb.setForeground(Color.WHITE);
		showBomb.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		showBomb.setBounds(63, 370, 100, 50);
		contentPane.add(showBomb);

		JLabel superp = new JLabel("super power");
		superp.setForeground(Color.WHITE);
		superp.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		superp.setBounds(30, 410, 100, 50);
		contentPane.add(superp);

		showSuper = new JLabel("nothing");
		showSuper.setForeground(Color.WHITE);
		showSuper.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
		showSuper.setBounds(120, 410, 100, 50);
		contentPane.add(showSuper);

		loc = new String[20][20];
		loc = field.generateField();
		Fields = new JLabel[20][20];
		JPanel panel = new JPanel();
		panel.setBackground(new Color(50, 50, 50));
		panel.setBounds((int) (screenSize.getWidth()*14/100), 0,(int) (screenSize.getWidth() - (screenSize.getWidth()*14/100)), (int) screenSize.getHeight());
		contentPane.add(panel);
		panel.setLayout(new GridLayout(20, 20, 0, 0));
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				Fields[i][j] = new JLabel();
				Fields[i][j].setOpaque(true);
				if (loc[i][j].equals("green")) {
					ImageIcon bg = new ImageIcon(
							new ImageIcon("img/grass1.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					Fields[i][j].setIcon(bg);
				} else if (loc[i][j].equals("brown")) {
					ImageIcon bg = new ImageIcon(
							new ImageIcon("img/grass2.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					Fields[i][j].setIcon(bg);
				} else if (loc[i][j].equals("block")) {
					ImageIcon bg = new ImageIcon(
							new ImageIcon("img/block.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					Fields[i][j].setIcon(bg);
				} else if (loc[i][j].equals("unblock")) {
					ImageIcon bg = new ImageIcon(
							new ImageIcon("img/unblock.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					Fields[i][j].setIcon(bg);
				} else if (loc[i][j].equals("wall")) {
					ImageIcon bg = new ImageIcon(
							new ImageIcon("img/wall.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					Fields[i][j].setIcon(bg);
				}
				panel.add(Fields[i][j]);
			}
		}
		if (type.equals("Eazy")) {
			bm = new BomberMan(30, true, 100);
			showBomb.setText("30");
			int x, y;
			x = 1 + (int) (Math.random() * (18));
			y = 1 + (int) (Math.random() * (18));
			while (loc[x][y].equals("block") || loc[x][y].equals("unblock") || loc[x][y].equals("wall")
					|| loc[x][y].equals("ghost")) {
				x = 1 + (int) (Math.random() * (18));
				y = 1 + (int) (Math.random() * (18));
			}
			bmi = x;
			bmj = y;
			if (loc[bmi][bmj].equals("green")) {
				ImageIcon bg = new ImageIcon(new ImageIcon("img/characters/bombermanfrontgreen.png").getImage()
						.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				Fields[bmi][bmj].setIcon(bg);
				loc[bmi][bmj] = "BMgreen";
			} else if (loc[bmi][bmj].equals("brown")) {
				ImageIcon bg = new ImageIcon(new ImageIcon("img/characters/bombermanfrontbrown.png").getImage()
						.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				Fields[bmi][bmj].setIcon(bg);
				loc[bmi][bmj] = "BMbrown";
			}
			ghosts = 3;

			Ghost g = new Ghost(3);

		} else if (type.equals("Normal")) {
			bm = new BomberMan(20, true, 100);
			showBomb.setText("20");
			int x, y;
			x = 1 + (int) (Math.random() * (18));
			y = 1 + (int) (Math.random() * (18));
			while (loc[x][y].equals("block") || loc[x][y].equals("unblock") || loc[x][y].equals("wall")
					|| loc[x][y].equals("ghost")) {
				x = 1 + (int) (Math.random() * (18));
				y = 1 + (int) (Math.random() * (18));
			}
			bmi = x;
			bmj = y;
			if (loc[bmi][bmj].equals("green")) {
				ImageIcon bg = new ImageIcon(new ImageIcon("img/characters/bombermanfrontgreen.png").getImage()
						.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				Fields[bmi][bmj].setIcon(bg);
				loc[bmi][bmj] = "BMgreen";
			} else if (loc[bmi][bmj].equals("brown")) {
				ImageIcon bg = new ImageIcon(new ImageIcon("img/characters/bombermanfrontbrown.png").getImage()
						.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				Fields[bmi][bmj].setIcon(bg);
				loc[bmi][bmj] = "BMbrown";
			}
			ghosts = 6;
			Ghost g = new Ghost(6);
		} else if (type.equals("Hard")) {
			bm = new BomberMan(10, true, 100);
			showBomb.setText("10");
			int x, y;
			x = 1 + (int) (Math.random() * (18));
			y = 1 + (int) (Math.random() * (18));
			while (loc[x][y].equals("block") || loc[x][y].equals("unblock") || loc[x][y].equals("wall")
					|| loc[x][y].equals("ghost")) {
				x = 1 + (int) (Math.random() * (18));
				y = 1 + (int) (Math.random() * (18));
			}
			bmi = x;
			bmj = y;
			if (loc[bmi][bmj].equals("green")) {
				ImageIcon bg = new ImageIcon(new ImageIcon("img/characters/bombermanfrontgreen.png").getImage()
						.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				Fields[bmi][bmj].setIcon(bg);
				loc[bmi][bmj] = "BMgreen";
			} else if (loc[bmi][bmj].equals("brown")) {
				ImageIcon bg = new ImageIcon(new ImageIcon("img/characters/bombermanfrontbrown.png").getImage()
						.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				Fields[bmi][bmj].setIcon(bg);
				loc[bmi][bmj] = "BMbrown";
			}
			ghosts = 10;
			Ghost g = new Ghost(10);
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
			if (Integer.valueOf(showBomb.getText()) > 0) {
				if (lastDir == 1) {
					bm.placeBomb(bmi - 1, bmj, rad);
				} else if (lastDir == 2) {
					bm.placeBomb(bmi, bmj + 1, rad);
				} else if (lastDir == 3) {
					bm.placeBomb(bmi + 1, bmj, rad);
				} else if (lastDir == 4) {
					bm.placeBomb(bmi, bmj - 1, rad);
				}
			}
			break;
		case KeyEvent.VK_SPACE:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}
