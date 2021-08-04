package Game;

import java.awt.Image;

import javax.swing.ImageIcon;

import Code.MusicPlayer;
import Frame.MultyPlayPage;
import Frame.SinglePlayPage;

public class BomberMan {
	private int numOfBombs;
	private boolean alive;
	private int health;
	private boolean bomb;
	private int onlineMode;
	private boolean online = false;

	public BomberMan(boolean online, int onlineMode) {
		this.onlineMode = onlineMode;
		this.online = online;
	}

	public BomberMan(int numOfBombs, boolean alive, int health) {
		this.numOfBombs = numOfBombs;
		this.alive = alive;
		this.health = health;
	}

	public Thread t2 = new Thread(new Runnable() {
		public void run() {
			System.out.println("im here in t2");
			if (online) {
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				MultyPlayPage.rad = MultyPlayPage.rad / 2;
			} else {
				SinglePlayPage.showSuper.setText("bomb radiaton  x" + SinglePlayPage.rad);
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				SinglePlayPage.rad = SinglePlayPage.rad / 2;
				SinglePlayPage.showSuper.setText("nothing");
			}
		}
	});

	@SuppressWarnings("deprecation")
	public void moveUp() {
		if (online) {
			MultyPlayPage.lastDir = 1;
			if (MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("wall")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("block")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bomb")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bombermanblackback")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bombermanblackfront")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bombermanblackleft")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bombermanblackright")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bombermanblueback")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bombermanbluefront")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bombermanblueleft")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bombermanblueright")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bombermanredback")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bombermanredfront")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bombermanredleft")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bombermanredright")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bombermanwhiteback")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bombermanwhitefront")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bombermanwhiteleft")
					|| MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("bombermanwhiteright")) {
				new MusicPlayer("song/wall.mp3", false).start();
				if (onlineMode == 1) {
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanwhiteback.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "bombermanwhiteback";
					MultyPlayPage.client.sendToServer("bm-bombermanwhiteback-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
									+ String.valueOf(MultyPlayPage.b1point.getText()));
				} else if (onlineMode == 2) {
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblackback.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "bombermanblackback";
					MultyPlayPage.client.sendToServer("bm-bombermanblackback-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
									+ String.valueOf(MultyPlayPage.b2point.getText()));
				} else if (onlineMode == 3) {
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblueback.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "bombermanblueback";
					MultyPlayPage.client.sendToServer("bm-bombermanblueback-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
									+ String.valueOf(MultyPlayPage.b3point.getText()));
				} else if (onlineMode == 4) {
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanredback.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "bombermanredback";
					MultyPlayPage.client.sendToServer("bm-bombermanredback-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
									+ String.valueOf(MultyPlayPage.b4point.getText()));
				}
			} else {
				new MusicPlayer("song/footstep.mp3", false).start();
				ImageIcon ground = new ImageIcon(
						new ImageIcon("img/onfield.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(ground);
				MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "ground";
				MultyPlayPage.client.sendToServer(
						"update-ground-" + String.valueOf(MultyPlayPage.bmi) + "-" + String.valueOf(MultyPlayPage.bmj));
				if (onlineMode == 1) {
					if (MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("point")) {
						MultyPlayPage.b1point
								.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b1point.getText()) + 10));
					} else if (MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("ubomb")) {
						if (MultyPlayPage.rad * 2 > 8) {
							MultyPlayPage.rad = 8;
						} else {
							MultyPlayPage.rad = MultyPlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
					}
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanwhiteback.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj] = "bombermanwhiteback";
					MultyPlayPage.client.sendToServer("bm-bombermanwhiteback-" + String.valueOf(MultyPlayPage.bmi - 1)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
							+ String.valueOf(MultyPlayPage.b1point.getText()));
				} else if (onlineMode == 2) {
					if (MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("point")) {
						MultyPlayPage.b2point
								.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b2point.getText()) + 10));
					} else if (MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("ubomb")) {
						if (MultyPlayPage.rad * 2 > 8) {
							MultyPlayPage.rad = 8;
						} else {
							MultyPlayPage.rad = MultyPlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
					}
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblackback.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj] = "bombermanblackback";
					MultyPlayPage.client.sendToServer("bm-bombermanblackback-" + String.valueOf(MultyPlayPage.bmi - 1)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
							+ String.valueOf(MultyPlayPage.b2point.getText()));
				} else if (onlineMode == 3) {
					if (MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("point")) {
						MultyPlayPage.b3point
								.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b3point.getText()) + 10));
					} else if (MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("ubomb")) {
						if (MultyPlayPage.rad * 2 > 8) {
							MultyPlayPage.rad = 8;
						} else {
							MultyPlayPage.rad = MultyPlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
					}
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblueback.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj] = "bombermanblueback";
					MultyPlayPage.client.sendToServer("bm-bombermanblueback-" + String.valueOf(MultyPlayPage.bmi - 1)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
							+ String.valueOf(MultyPlayPage.b3point.getText()));
				} else if (onlineMode == 4) {
					if (MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("point")) {
						MultyPlayPage.b4point
								.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b4point.getText()) + 10));
					} else if (MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].equals("ubomb")) {
						if (MultyPlayPage.rad * 2 > 8) {
							MultyPlayPage.rad = 8;
						} else {
							MultyPlayPage.rad = MultyPlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
					}
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanredback.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi - 1][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi - 1][MultyPlayPage.bmj] = "bombermanredback";
					MultyPlayPage.client.sendToServer("bm-bombermanredback-" + String.valueOf(MultyPlayPage.bmi - 1)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
							+ String.valueOf(MultyPlayPage.b1point.getText()));
				}
				MultyPlayPage.bmi--;
			}
		} else {
			SinglePlayPage.lastDir = 1;
			if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("wall")
					|| SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("block")
					|| SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("unblock")
					|| SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("bomb")) {
				new MusicPlayer("song/wall.mp3", false).start();
				ImageIcon bg1 = new ImageIcon(new ImageIcon("img/characters/bombermanbackgreen.png").getImage()
						.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				ImageIcon bg2 = new ImageIcon(new ImageIcon("img/characters/bombermanbackbrown.png").getImage()
						.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj].equals("BMgreen")) {
					SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj].setIcon(bg1);
				} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj].equals("BMbrown")) {
					SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj].setIcon(bg2);
				}
			} else if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("ghostgreen")
					|| SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("ghostbrown")) {
				System.out.println("i faced a ghost");
				new MusicPlayer("song/scream.mp3", false).start();
				if (Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20 < 0) {
					SinglePlayPage.showHarth.setText("0");
				} else {
					SinglePlayPage.showHarth
							.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20));
				}
			} else {
				new MusicPlayer("song/footstep.mp3", false).start();
				if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj].equals("BMgreen")) {
					ImageIcon bg = new ImageIcon(
							new ImageIcon("img/grass1.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					ImageIcon bg1 = new ImageIcon(new ImageIcon("img/characters/bombermanbackgreen.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					ImageIcon bg2 = new ImageIcon(new ImageIcon("img/characters/bombermanbackbrown.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj].setIcon(bg);
					SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj] = "green";
					if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("green")) {
						SinglePlayPage.Fields[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("brown")) {
						SinglePlayPage.Fields[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("blodgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20 >= 100) {
							SinglePlayPage.showHarth.setText("100");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20));
						}
						SinglePlayPage.Fields[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("blodbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20 >= 100) {
							SinglePlayPage.showHarth.setText("100");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20));
						}
						SinglePlayPage.Fields[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("ubombgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (SinglePlayPage.rad * 2 > 8) {
							SinglePlayPage.rad = 8;
						} else {
							SinglePlayPage.rad = SinglePlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
						SinglePlayPage.Fields[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("ubombbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (SinglePlayPage.rad * 2 > 8) {
							SinglePlayPage.rad = 8;
						} else {
							SinglePlayPage.rad = SinglePlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
						SinglePlayPage.Fields[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("pointgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 10));
						SinglePlayPage.Fields[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("pointbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 10));
						SinglePlayPage.Fields[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj] = "BMbrown";
					}
				} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj].equals("BMbrown")) {
					ImageIcon bg = new ImageIcon(
							new ImageIcon("img/grass2.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					ImageIcon bg1 = new ImageIcon(new ImageIcon("img/characters/bombermanbackgreen.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					ImageIcon bg2 = new ImageIcon(new ImageIcon("img/characters/bombermanbackbrown.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj].setIcon(bg);
					SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj] = "brown";
					if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("green")) {
						SinglePlayPage.Fields[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("brown")) {
						SinglePlayPage.Fields[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("blodgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20 >= 100) {
							SinglePlayPage.showHarth.setText("100");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20));
						}
						SinglePlayPage.Fields[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("blodbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20 >= 100) {
							SinglePlayPage.showHarth.setText("100");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20));
						}
						SinglePlayPage.Fields[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("ubombgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (SinglePlayPage.rad * 2 > 8) {
							SinglePlayPage.rad = 8;
						} else {
							SinglePlayPage.rad = SinglePlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
						SinglePlayPage.Fields[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("ubombbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (SinglePlayPage.rad * 2 > 8) {
							SinglePlayPage.rad = 8;
						} else {
							SinglePlayPage.rad = SinglePlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
						SinglePlayPage.Fields[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("pointgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 10));
						SinglePlayPage.Fields[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("pointbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 10));
						SinglePlayPage.Fields[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj] = "BMbrown";
					}
				}
				SinglePlayPage.bmi--;
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void moveDown() {
		if (online) {
			MultyPlayPage.lastDir = 3;
			if (MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("wall")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("block")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bomb")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bombermanblackback")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bombermanblackfront")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bombermanblackleft")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bombermanblackright")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bombermanblueback")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bombermanbluefront")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bombermanblueleft")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bombermanblueright")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bombermanredback")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bombermanredfront")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bombermanredleft")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bombermanredright")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bombermanwhiteback")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bombermanwhitefront")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bombermanwhiteleft")
					|| MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("bombermanwhiteright")) {
				new MusicPlayer("song/wall.mp3", false).start();
				if (onlineMode == 1) {
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanwhitefront.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "bombermanwhitefront";
					MultyPlayPage.client.sendToServer("bm-bombermanwhitefront-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
									+ String.valueOf(MultyPlayPage.b1point.getText()));
				} else if (onlineMode == 2) {
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblackfront.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "bombermanblackfront";
					MultyPlayPage.client.sendToServer("bm-bombermanblackfront-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
									+ String.valueOf(MultyPlayPage.b2point.getText()));
				} else if (onlineMode == 3) {
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanbluefront.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "bombermanbluefront";
					MultyPlayPage.client.sendToServer("bm-bombermanbluefront-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
									+ String.valueOf(MultyPlayPage.b3point.getText()));
				} else if (onlineMode == 4) {
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanredfront.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "bombermanredfront";
					MultyPlayPage.client.sendToServer("bm-bombermanredfront-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
									+ String.valueOf(MultyPlayPage.b4point.getText()));
				}
			} else {
				new MusicPlayer("song/footstep.mp3", false).start();
				ImageIcon ground = new ImageIcon(
						new ImageIcon("img/onfield.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(ground);
				MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "ground";
				MultyPlayPage.client.sendToServer(
						"update-ground-" + String.valueOf(MultyPlayPage.bmi) + "-" + String.valueOf(MultyPlayPage.bmj));
				if (onlineMode == 1) {
					if (MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("point")) {
						MultyPlayPage.b1point
								.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b1point.getText()) + 10));
					} else if (MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("ubomb")) {
						if (MultyPlayPage.rad * 2 > 8) {
							MultyPlayPage.rad = 8;
						} else {
							MultyPlayPage.rad = MultyPlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
					}
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanwhitefront.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj] = "bombermanwhitefront";
					MultyPlayPage.client.sendToServer("bm-bombermanwhitefront-"
							+ String.valueOf(MultyPlayPage.bmi + 1) + "-" + String.valueOf(MultyPlayPage.bmj)+ "-"
									+ String.valueOf(onlineMode) + "-" + String.valueOf(MultyPlayPage.b1point.getText()));
				} else if (onlineMode == 2) {
					if (MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("point")) {
						MultyPlayPage.b2point
								.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b2point.getText()) + 10));
					} else if (MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("ubomb")) {
						if (MultyPlayPage.rad * 2 > 8) {
							MultyPlayPage.rad = 8;
						} else {
							MultyPlayPage.rad = MultyPlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
					}
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblackfront.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj] = "bombermanblackfront";
					MultyPlayPage.client.sendToServer("bm-bombermanblackfront-"
							+ String.valueOf(MultyPlayPage.bmi + 1) + "-" + String.valueOf(MultyPlayPage.bmj)+ "-"
									+ String.valueOf(onlineMode) + "-" + String.valueOf(MultyPlayPage.b2point.getText()));
				} else if (onlineMode == 3) {
					if (MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("point")) {
						MultyPlayPage.b3point
								.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b3point.getText()) + 10));
					} else if (MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("ubomb")) {
						if (MultyPlayPage.rad * 2 > 8) {
							MultyPlayPage.rad = 8;
						} else {
							MultyPlayPage.rad = MultyPlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
					}
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanbluefront.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj] = "bombermanbluefront";
					MultyPlayPage.client.sendToServer("bm-bombermanbluefront-"
							+ String.valueOf(MultyPlayPage.bmi + 1) + "-" + String.valueOf(MultyPlayPage.bmj)+ "-"
									+ String.valueOf(onlineMode) + "-" + String.valueOf(MultyPlayPage.b3point.getText()));
				} else if (onlineMode == 4) {
					if (MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("point")) {
						MultyPlayPage.b4point
								.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b4point.getText()) + 10));
					} else if (MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].equals("ubomb")) {
						if (MultyPlayPage.rad * 2 > 8) {
							MultyPlayPage.rad = 8;
						} else {
							MultyPlayPage.rad = MultyPlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
					}
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanredfront.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi + 1][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi + 1][MultyPlayPage.bmj] = "bombermanredfront";
					MultyPlayPage.client.sendToServer("bm-bombermanredfront-"
							+ String.valueOf(MultyPlayPage.bmi + 1) + "-" + String.valueOf(MultyPlayPage.bmj)+ "-"
									+ String.valueOf(onlineMode) + "-" + String.valueOf(MultyPlayPage.b4point.getText()));
				}
				MultyPlayPage.bmi++;
			}
		} else {
			SinglePlayPage.lastDir = 2;
			if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("wall")
					|| SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("block")
					|| SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("unblock")
					|| SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("bomb")) {
				new MusicPlayer("song/wall.mp3", false).start();
				ImageIcon bg1 = new ImageIcon(new ImageIcon("img/characters/bombermanfrontgreen.png").getImage()
						.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				ImageIcon bg2 = new ImageIcon(new ImageIcon("img/characters/bombermanfrontbrown.png").getImage()
						.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj].equals("BMgreen")) {
					SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj].setIcon(bg1);
				} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj].equals("BMbrown")) {
					SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj].setIcon(bg2);
				}
			} else if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("ghostgreen")
					|| SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("ghostbrown")) {
				System.out.println("i faced a ghost");
				new MusicPlayer("song/scream.mp3", false).start();
				if (Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20 < 0) {
					SinglePlayPage.showHarth.setText("0");
				} else {
					SinglePlayPage.showHarth
							.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20));
				}
			} else {
				new MusicPlayer("song/footstep.mp3", false).start();
				if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj].equals("BMgreen")) {
					ImageIcon bg = new ImageIcon(
							new ImageIcon("img/grass1.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					ImageIcon bg1 = new ImageIcon(new ImageIcon("img/characters/bombermanfrontgreen.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					ImageIcon bg2 = new ImageIcon(new ImageIcon("img/characters/bombermanfrontbrown.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj].setIcon(bg);
					SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj] = "green";
					if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("green")) {
						SinglePlayPage.Fields[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("brown")) {
						SinglePlayPage.Fields[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("blodgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20 >= 100) {
							SinglePlayPage.showHarth.setText("100");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20));
						}
						SinglePlayPage.Fields[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("blodbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20 >= 100) {
							SinglePlayPage.showHarth.setText("100");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20));
						}
						SinglePlayPage.Fields[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("ubombgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (SinglePlayPage.rad * 2 > 8) {
							SinglePlayPage.rad = 8;
						} else {
							SinglePlayPage.rad = SinglePlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
						SinglePlayPage.Fields[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("ubombbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (SinglePlayPage.rad * 2 > 8) {
							SinglePlayPage.rad = 8;
						} else {
							SinglePlayPage.rad = SinglePlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
						SinglePlayPage.Fields[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("pointgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 10));
						SinglePlayPage.Fields[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("pointbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 10));
						SinglePlayPage.Fields[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj] = "BMbrown";
					}
				} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj].equals("BMbrown")) {
					ImageIcon bg = new ImageIcon(
							new ImageIcon("img/grass2.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					ImageIcon bg1 = new ImageIcon(new ImageIcon("img/characters/bombermanfrontgreen.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					ImageIcon bg2 = new ImageIcon(new ImageIcon("img/characters/bombermanfrontbrown.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj].setIcon(bg);
					SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj] = "brown";
					if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("green")) {
						SinglePlayPage.Fields[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("brown")) {
						SinglePlayPage.Fields[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("blodgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20 >= 100) {
							SinglePlayPage.showHarth.setText("100");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20));
						}
						SinglePlayPage.Fields[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("blodbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20 >= 100) {
							SinglePlayPage.showHarth.setText("100");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20));
						}
						SinglePlayPage.Fields[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("ubombgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (SinglePlayPage.rad * 2 > 8) {
							SinglePlayPage.rad = 8;
						} else {
							SinglePlayPage.rad = SinglePlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
						SinglePlayPage.Fields[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("ubombbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (SinglePlayPage.rad * 2 > 8) {
							SinglePlayPage.rad = 8;
						} else {
							SinglePlayPage.rad = SinglePlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
						SinglePlayPage.Fields[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("pointgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 10));
						SinglePlayPage.Fields[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].equals("pointbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 10));
						SinglePlayPage.Fields[SinglePlayPage.bmi + 1][SinglePlayPage.bmj].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi + 1][SinglePlayPage.bmj] = "BMbrown";
					}
				}
				SinglePlayPage.bmi++;
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void moveRight() {
		if (online) {
			MultyPlayPage.lastDir = 2;
			if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("wall")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("block")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bomb")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bombermanblackback")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bombermanblackfront")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bombermanblackleft")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bombermanblackright")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bombermanblueback")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bombermanbluefront")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bombermanblueleft")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bombermanblueright")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bombermanredback")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bombermanredfront")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bombermanredleft")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bombermanredright")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bombermanwhiteback")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bombermanwhitefront")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bombermanwhiteleft")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("bombermanwhiteright")) {
				new MusicPlayer("song/wall.mp3", false).start();
				if (onlineMode == 1) {
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanwhiteright.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "bombermanwhiteright";
					MultyPlayPage.client.sendToServer("bm-bombermanwhiteright-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
									+ String.valueOf(MultyPlayPage.b1point.getText()));
				} else if (onlineMode == 2) {
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblackright.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "bombermanblackright";
					MultyPlayPage.client.sendToServer("bm-bombermanblackright-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
									+ String.valueOf(MultyPlayPage.b2point.getText()));
				} else if (onlineMode == 3) {
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblueright.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "bombermanblueright";
					MultyPlayPage.client.sendToServer("bm-bombermanblueright-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
									+ String.valueOf(MultyPlayPage.b3point.getText()));
				} else if (onlineMode == 4) {
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanredright.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "bombermanredright";
					MultyPlayPage.client.sendToServer("bm-bombermanredright-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
									+ String.valueOf(MultyPlayPage.b4point.getText()));
				}
			} else {
				new MusicPlayer("song/footstep.mp3", false).start();
				ImageIcon ground = new ImageIcon(
						new ImageIcon("img/onfield.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(ground);
				MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "ground";
				MultyPlayPage.client.sendToServer(
						"update-ground-" + String.valueOf(MultyPlayPage.bmi) + "-" + String.valueOf(MultyPlayPage.bmj));
				if (onlineMode == 1) {
					if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("point")) {
						MultyPlayPage.b1point
								.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b1point.getText()) + 10));
					} else if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("ubomb")) {
						if (MultyPlayPage.rad * 2 > 8) {
							MultyPlayPage.rad = 8;
						} else {
							MultyPlayPage.rad = MultyPlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
					}
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanwhiteright.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1] = "bombermanwhiteright";
					MultyPlayPage.client.sendToServer("bm-bombermanwhiteright-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj + 1)+ "-"
									+ String.valueOf(onlineMode) + "-" + String.valueOf(MultyPlayPage.b1point.getText()));
				} else if (onlineMode == 2) {
					if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("point")) {
						MultyPlayPage.b2point
								.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b2point.getText()) + 10));
					} else if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("ubomb")) {
						if (MultyPlayPage.rad * 2 > 8) {
							MultyPlayPage.rad = 8;
						} else {
							MultyPlayPage.rad = MultyPlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
					}
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblackright.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1] = "bombermanblackright";
					MultyPlayPage.client.sendToServer("bm-bombermanblackright-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj + 1)+ "-"
									+ String.valueOf(onlineMode) + "-" + String.valueOf(MultyPlayPage.b2point.getText()));
				} else if (onlineMode == 3) {
					if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("point")) {
						MultyPlayPage.b3point
								.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b3point.getText()) + 10));
					} else if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("ubomb")) {
						if (MultyPlayPage.rad * 2 > 8) {
							MultyPlayPage.rad = 8;
						} else {
							MultyPlayPage.rad = MultyPlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
					}
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblueright.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1] = "bombermanblueright";
					MultyPlayPage.client.sendToServer("bm-bombermanblueright-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj + 1)+ "-"
									+ String.valueOf(onlineMode) + "-" + String.valueOf(MultyPlayPage.b3point.getText()));
				} else if (onlineMode == 4) {
					if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("point")) {
						MultyPlayPage.b4point
								.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b4point.getText()) + 10));
					} else if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].equals("ubomb")) {
						if (MultyPlayPage.rad * 2 > 8) {
							MultyPlayPage.rad = 8;
						} else {
							MultyPlayPage.rad = MultyPlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
					}
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanredright.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj + 1].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj + 1] = "bombermanredright";
					MultyPlayPage.client.sendToServer("bm-bombermanredright-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj + 1)+ "-"
									+ String.valueOf(onlineMode) + "-" + String.valueOf(MultyPlayPage.b4point.getText()));
				}
				MultyPlayPage.bmj++;
			}
		} else {
			SinglePlayPage.lastDir = 2;
			if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("wall")
					|| SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("block")
					|| SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("unblock")
					|| SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("bomb")) {
				new MusicPlayer("song/wall.mp3", false).start();
				ImageIcon bg1 = new ImageIcon(new ImageIcon("img/characters/bombermanrightgreen.png").getImage()
						.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				ImageIcon bg2 = new ImageIcon(new ImageIcon("img/characters/bombermanrightbrown.png").getImage()
						.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj].equals("BMgreen")) {
					SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj].setIcon(bg1);
				} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj].equals("BMbrown")) {
					SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj].setIcon(bg2);
				}
			} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("ghostgreen")
					|| SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("ghostbrown")) {
				System.out.println("i faced a ghost");
				new MusicPlayer("song/scream.mp3", false).start();
				if (Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20 < 0) {
					SinglePlayPage.showHarth.setText("0");
				} else {
					SinglePlayPage.showHarth
							.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20));
				}
			} else {
				new MusicPlayer("song/footstep.mp3", false).start();
				if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj].equals("BMgreen")) {
					ImageIcon bg = new ImageIcon(
							new ImageIcon("img/grass1.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					ImageIcon bg1 = new ImageIcon(new ImageIcon("img/characters/bombermanrightgreen.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					ImageIcon bg2 = new ImageIcon(new ImageIcon("img/characters/bombermanrightbrown.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj].setIcon(bg);
					SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj] = "green";
					if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("green")) {
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("brown")) {
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("blodgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20 >= 100) {
							SinglePlayPage.showHarth.setText("100");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20));
						}
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("blodbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20 >= 100) {
							SinglePlayPage.showHarth.setText("100");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20));
						}
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("ubombgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (SinglePlayPage.rad * 2 > 8) {
							SinglePlayPage.rad = 8;
						} else {
							SinglePlayPage.rad = SinglePlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("ubombbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (SinglePlayPage.rad * 2 > 8) {
							SinglePlayPage.rad = 8;
						} else {
							SinglePlayPage.rad = SinglePlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("pointgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 10));
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("pointbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 10));
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1] = "BMbrown";
					}
				} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj].equals("BMbrown")) {
					ImageIcon bg = new ImageIcon(
							new ImageIcon("img/grass2.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					ImageIcon bg1 = new ImageIcon(new ImageIcon("img/characters/bombermanrightgreen.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					ImageIcon bg2 = new ImageIcon(new ImageIcon("img/characters/bombermanrightbrown.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj].setIcon(bg);
					SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj] = "brown";
					if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("green")) {
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("brown")) {
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("blodgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20 >= 100) {
							SinglePlayPage.showHarth.setText("100");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20));
						}
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("blodbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20 >= 100) {
							SinglePlayPage.showHarth.setText("100");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20));
						}
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("ubombgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (SinglePlayPage.rad * 2 > 8) {
							SinglePlayPage.rad = 8;
						} else {
							SinglePlayPage.rad = SinglePlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("ubombbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (SinglePlayPage.rad * 2 > 8) {
							SinglePlayPage.rad = 8;
						} else {
							SinglePlayPage.rad = SinglePlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("pointgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 10));
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].equals("pointbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 10));
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj + 1].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj + 1] = "BMbrown";
					}
				}
				SinglePlayPage.bmj++;
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void moveLeft() {
		if (online) {
			MultyPlayPage.lastDir = 4;
			if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("wall")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("block")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bomb")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bombermanblackback")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bombermanblackfront")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bombermanblackleft")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bombermanblackright")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bombermanblueback")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bombermanbluefront")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bombermanblueleft")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bombermanblueright")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bombermanredback")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bombermanredfront")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bombermanredleft")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bombermanredright")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bombermanwhiteback")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bombermanwhitefront")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bombermanwhiteleft")
					|| MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("bombermanwhiteright")) {
				new MusicPlayer("song/wall.mp3", false).start();
				if (onlineMode == 1) {
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanwhiteleft.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "bombermanwhiteleft";
					MultyPlayPage.client.sendToServer("bm-bombermanwhiteleft-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
									+ String.valueOf(MultyPlayPage.b1point.getText()));
				} else if (onlineMode == 2) {
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblackleft.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "bombermanblackleft";
					MultyPlayPage.client.sendToServer("bm-bombermanblackleft-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
									+ String.valueOf(MultyPlayPage.b2point.getText()));
				} else if (onlineMode == 3) {
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblueleft.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "bombermanblueleft";
					MultyPlayPage.client.sendToServer("bm-bombermanblueleft-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
									+ String.valueOf(MultyPlayPage.b3point.getText()));
				} else if (onlineMode == 4) {
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanredleft.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "bombermanredleft";
					MultyPlayPage.client.sendToServer("bm-bombermanredleft-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj) + "-" + String.valueOf(onlineMode) + "-"
									+ String.valueOf(MultyPlayPage.b4point.getText()));
				}
			} else {
				new MusicPlayer("song/footstep.mp3", false).start();
				ImageIcon ground = new ImageIcon(
						new ImageIcon("img/onfield.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj].setIcon(ground);
				MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj] = "ground";
				MultyPlayPage.client.sendToServer(
						"update-ground-" + String.valueOf(MultyPlayPage.bmi) + "-" + String.valueOf(MultyPlayPage.bmj));
				if (onlineMode == 1) {
					if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("point")) {
						MultyPlayPage.b1point
								.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b1point.getText()) + 10));
					} else if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("ubomb")) {
						if (MultyPlayPage.rad * 2 > 8) {
							MultyPlayPage.rad = 8;
						} else {
							MultyPlayPage.rad = MultyPlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
					}
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanwhiteleft.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1] = "bombermanwhiteleft";
					MultyPlayPage.client.sendToServer("bm-bombermanwhiteleft-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj - 1)+ "-"
									+ String.valueOf(onlineMode) + "-" + String.valueOf(MultyPlayPage.b1point.getText()));
				} else if (onlineMode == 2) {
					if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("point")) {
						MultyPlayPage.b2point
								.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b2point.getText()) + 10));
					} else if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("ubomb")) {
						if (MultyPlayPage.rad * 2 > 8) {
							MultyPlayPage.rad = 8;
						} else {
							MultyPlayPage.rad = MultyPlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
					}
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblackleft.png")
							.getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1] = "bombermanblackleft";
					MultyPlayPage.client.sendToServer("bm-bombermanblackleft-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj - 1)+ "-"
									+ String.valueOf(onlineMode) + "-" + String.valueOf(MultyPlayPage.b2point.getText()));
				} else if (onlineMode == 3) {
					if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("point")) {
						MultyPlayPage.b3point
								.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b3point.getText()) + 10));
					} else if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("ubomb")) {
						if (MultyPlayPage.rad * 2 > 8) {
							MultyPlayPage.rad = 8;
						} else {
							MultyPlayPage.rad = MultyPlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
					}
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanblueleft.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1] = "bombermanblueleft";
					MultyPlayPage.client.sendToServer("bm-bombermanblueleft-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj - 1)+ "-"
									+ String.valueOf(onlineMode) + "-" + String.valueOf(MultyPlayPage.b3point.getText()));
				} else if (onlineMode == 4) {
					if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("point")) {
						MultyPlayPage.b4point
								.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b4point.getText()) + 10));
					} else if (MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].equals("ubomb")) {
						if (MultyPlayPage.rad * 2 > 8) {
							MultyPlayPage.rad = 8;
						} else {
							MultyPlayPage.rad = MultyPlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
					}
					ImageIcon bg = new ImageIcon(new ImageIcon("img/online characters/bombermanredleft.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					MultyPlayPage.Fields[MultyPlayPage.bmi][MultyPlayPage.bmj - 1].setIcon(bg);
					MultyPlayPage.loc[MultyPlayPage.bmi][MultyPlayPage.bmj - 1] = "bombermanredleft";
					MultyPlayPage.client.sendToServer("bm-bombermanredleft-" + String.valueOf(MultyPlayPage.bmi)
							+ "-" + String.valueOf(MultyPlayPage.bmj - 1)+ "-"
									+ String.valueOf(onlineMode) + "-" + String.valueOf(MultyPlayPage.b4point.getText()));
				}
				MultyPlayPage.bmj--;
			}
		} else {
			SinglePlayPage.lastDir = 4;
			if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("wall")
					|| SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("block")
					|| SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("unblock")
					|| SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("bomb")) {
				new MusicPlayer("song/wall.mp3", false).start();
				ImageIcon bg1 = new ImageIcon(new ImageIcon("img/characters/bombermanleftgreen.png").getImage()
						.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				ImageIcon bg2 = new ImageIcon(new ImageIcon("img/characters/bombermanleftbrown.png").getImage()
						.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj].equals("BMgreen")) {
					SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj].setIcon(bg1);
				} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj].equals("BMbrown")) {
					SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj].setIcon(bg2);
				}
			} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("ghostgreen")
					|| SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("ghostbrown")) {
				System.out.println("i faced a ghost");
				new MusicPlayer("song/scream.mp3", false).start();
				if (Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20 < 0) {
					SinglePlayPage.showHarth.setText("0");
				} else {
					SinglePlayPage.showHarth
							.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20));
				}
			} else {
				new MusicPlayer("song/footstep.mp3", false).start();
				if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj].equals("BMgreen")) {
					ImageIcon bg = new ImageIcon(
							new ImageIcon("img/grass1.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					ImageIcon bg1 = new ImageIcon(new ImageIcon("img/characters/bombermanleftgreen.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					ImageIcon bg2 = new ImageIcon(new ImageIcon("img/characters/bombermanleftbrown.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj].setIcon(bg);
					SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj] = "green";
					if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("green")) {
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("brown")) {
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi - 1][SinglePlayPage.bmj].equals("blodgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20 >= 100) {
							SinglePlayPage.showHarth.setText("100");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20));
						}
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("blodbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20 >= 100) {
							SinglePlayPage.showHarth.setText("100");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20));
						}
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("ubombgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (SinglePlayPage.rad * 2 > 8) {
							SinglePlayPage.rad = 8;
						} else {
							SinglePlayPage.rad = SinglePlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("ubombbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (SinglePlayPage.rad * 2 > 8) {
							SinglePlayPage.rad = 8;
						} else {
							SinglePlayPage.rad = SinglePlayPage.rad * 2;
						}
						if (t2.isAlive()) {
							t2.stop();
						}
						t2.start();
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("pointgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 10));
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("pointbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 10));
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1] = "BMbrown";
					}
				} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj].equals("BMbrown")) {
					ImageIcon bg = new ImageIcon(
							new ImageIcon("img/grass2.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					ImageIcon bg1 = new ImageIcon(new ImageIcon("img/characters/bombermanleftgreen.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					ImageIcon bg2 = new ImageIcon(new ImageIcon("img/characters/bombermanleftbrown.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj] = "brown";
					SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj].setIcon(bg);
					if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("green")) {
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("brown")) {
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("blodgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20 >= 100) {
							SinglePlayPage.showHarth.setText("100");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20));
						}
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("blodbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20 >= 100) {
							SinglePlayPage.showHarth.setText("100");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) + 20));
						}
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("ubombgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (SinglePlayPage.rad * 2 > 8) {
							SinglePlayPage.rad = 8;
						} else {
							SinglePlayPage.rad = SinglePlayPage.rad * 2;
						}
						t2.start();
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("ubombbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						if (SinglePlayPage.rad * 2 > 8) {
							SinglePlayPage.rad = 8;
						} else {
							SinglePlayPage.rad = SinglePlayPage.rad * 2;
						}
						t2.start();
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1] = "BMbrown";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("pointgreen")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 10));
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].setIcon(bg1);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1] = "BMgreen";
					} else if (SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].equals("pointbrown")) {
						new MusicPlayer("song/dropbomb.mp3", false).start();
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 10));
						SinglePlayPage.Fields[SinglePlayPage.bmi][SinglePlayPage.bmj - 1].setIcon(bg2);
						SinglePlayPage.loc[SinglePlayPage.bmi][SinglePlayPage.bmj - 1] = "BMbrown";
					}
				}
				SinglePlayPage.bmj--;
			}
		}
	}

	public void placeBomb(int i, int j, int rad) {
		if (online) {
			new Bomb(rad, i, j, true).start();
		} else {
			new Bomb(rad, i, j).start();
		}

	}

	public void kickBomb() {

	}
}
