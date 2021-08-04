package Game;

import java.awt.Image;

import javax.swing.ImageIcon;

import Code.MusicPlayer;
import Frame.MultyPlayPage;
import Frame.SinglePlayPage;

public class Bomb extends Thread {
	private byte[] pic;
	private int radiation;
	private int i, j;
	private boolean online = false;
	private boolean someoneElse = false;
	private boolean dropped;
	ImageIcon bg = new ImageIcon(
			new ImageIcon("img/grass2.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
	private ImageIcon bg1 = new ImageIcon(
			new ImageIcon("img/characters/bombgreen.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
	private ImageIcon bg2 = new ImageIcon(
			new ImageIcon("img/characters/bombbrown.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
	ImageIcon ground = new ImageIcon(
			new ImageIcon("img/onfield.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
	private ImageIcon onbomb = new ImageIcon(
			new ImageIcon("img/online characters/onbomb.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
	private ImageIcon bombc = new ImageIcon(
			new ImageIcon("img/characters/blastcenter.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
	private ImageIcon bombu = new ImageIcon(
			new ImageIcon("img/characters/blastup.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
	private ImageIcon bombr = new ImageIcon(
			new ImageIcon("img/characters/blastright.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
	private ImageIcon bombd = new ImageIcon(
			new ImageIcon("img/characters/blastdown.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
	private ImageIcon bombl = new ImageIcon(
			new ImageIcon("img/characters/blastleft.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));

	public Bomb(int radiation, int i, int j) {
		this.radiation = radiation;
		this.i = i;
		this.j = j;
		if (SinglePlayPage.loc[i][j].equals("wall") || SinglePlayPage.loc[i][j].equals("block")
				|| SinglePlayPage.loc[i][j].equals("unblock")) {
			this.dropped = false;
		} else if (SinglePlayPage.loc[i][j].equals("green")) {
			new MusicPlayer("song/dropbomb.mp3", false).start();
			SinglePlayPage.Fields[i][j].setIcon(bg1);
			SinglePlayPage.loc[i][j] = "bomb";
			this.dropped = true;
			SinglePlayPage.showBomb.setText(String.valueOf(Integer.valueOf(SinglePlayPage.showBomb.getText()) - 1));
		} else if (SinglePlayPage.loc[i][j].equals("brown")) {
			new MusicPlayer("song/dropbomb.mp3", false).start();
			SinglePlayPage.Fields[i][j].setIcon(bg2);
			SinglePlayPage.loc[i][j] = "bomb";
			this.dropped = true;
			SinglePlayPage.showBomb.setText(String.valueOf(Integer.valueOf(SinglePlayPage.showBomb.getText()) - 1));
		}
	}

	public Bomb(int radiation, int i, int j, boolean online) {
		this.radiation = radiation;
		this.i = i;
		this.j = j;
		this.online = online;
		if (MultyPlayPage.loc[i][j].equals("wall") || MultyPlayPage.loc[i][j].equals("block")
				|| MultyPlayPage.loc[i][j].equals("bomb") || MultyPlayPage.loc[i][j].equals("bombermanblackback")
				|| MultyPlayPage.loc[i][j].equals("bombermanblackfront")
				|| MultyPlayPage.loc[i][j].equals("bombermanblackleft")
				|| MultyPlayPage.loc[i][j].equals("bombermanblackright")
				|| MultyPlayPage.loc[i][j].equals("bombermanblueback")
				|| MultyPlayPage.loc[i][j].equals("bombermanbluefront")
				|| MultyPlayPage.loc[i][j].equals("bombermanblueleft")
				|| MultyPlayPage.loc[i][j].equals("bombermanblueright")
				|| MultyPlayPage.loc[i][j].equals("bombermanredback")
				|| MultyPlayPage.loc[i][j].equals("bombermanredfront")
				|| MultyPlayPage.loc[i][j].equals("bombermanredleft")
				|| MultyPlayPage.loc[i][j].equals("bombermanredright")
				|| MultyPlayPage.loc[i][j].equals("bombermanwhiteback")
				|| MultyPlayPage.loc[i][j].equals("bombermanwhitefront")
				|| MultyPlayPage.loc[i][j].equals("bombermanwhiteleft")
				|| MultyPlayPage.loc[i][j].equals("bombermanwhiteright")) {
			this.dropped = false;
		} else {
			new MusicPlayer("song/dropbomb.mp3", false).start();
			MultyPlayPage.Fields[i][j].setIcon(onbomb);
			MultyPlayPage.loc[i][j] = "bomb";
			this.dropped = true;
		}
	}

	public Bomb(int radiation, int i, int j, boolean online, boolean someoneElse) {
		this.radiation = radiation;
		this.i = i;
		this.j = j;
		this.online = online;
		this.someoneElse = someoneElse;
		if (MultyPlayPage.loc[i][j].equals("wall") || MultyPlayPage.loc[i][j].equals("block")
				|| MultyPlayPage.loc[i][j].equals("bomb") || MultyPlayPage.loc[i][j].equals("bombermanblackback")
				|| MultyPlayPage.loc[i][j].equals("bombermanblackfront")
				|| MultyPlayPage.loc[i][j].equals("bombermanblackleft")
				|| MultyPlayPage.loc[i][j].equals("bombermanblackright")
				|| MultyPlayPage.loc[i][j].equals("bombermanblueback")
				|| MultyPlayPage.loc[i][j].equals("bombermanbluefront")
				|| MultyPlayPage.loc[i][j].equals("bombermanblueleft")
				|| MultyPlayPage.loc[i][j].equals("bombermanblueright")
				|| MultyPlayPage.loc[i][j].equals("bombermanredback")
				|| MultyPlayPage.loc[i][j].equals("bombermanredfront")
				|| MultyPlayPage.loc[i][j].equals("bombermanredleft")
				|| MultyPlayPage.loc[i][j].equals("bombermanredright")
				|| MultyPlayPage.loc[i][j].equals("bombermanwhiteback")
				|| MultyPlayPage.loc[i][j].equals("bombermanwhitefront")
				|| MultyPlayPage.loc[i][j].equals("bombermanwhiteleft")
				|| MultyPlayPage.loc[i][j].equals("bombermanwhiteright")) {
			this.dropped = false;
		} else {
			new MusicPlayer("song/dropbomb.mp3", false).start();
			MultyPlayPage.Fields[i][j].setIcon(onbomb);
			MultyPlayPage.loc[i][j] = "bomb";
			this.dropped = true;
		}
	}

	@SuppressWarnings("deprecation")
	public void run() {
		if (this.dropped == true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			new MusicPlayer("song/bomb.mp3", false).start();
			if (online) {
				MultyPlayPage.Fields[i][j].setIcon(bombc);
				for (int m = 1; m <= radiation; m++) {
					if (MultyPlayPage.loc[i - m][j].equals("wall") || MultyPlayPage.loc[i - m][j].equals("block")) {
					} else if (someoneElse) {
						if (MultyPlayPage.loc[i - m][j].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanwhiteleft")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackleft")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackright")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblueback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblueleft")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblueright")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredfront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredleft")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredright")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else {
							MultyPlayPage.Fields[i - m][j].setIcon(bombu);
						}
					} else if (MultyPlayPage.witchNumber == 1) {
						if (MultyPlayPage.loc[i - m][j].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanwhiteleft")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else if (MultyPlayPage.loc[i - m][j].equals("bombermanblackback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackleft")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackright")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblueback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblueleft")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblueright")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredfront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredleft")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredright")) {
							new MusicPlayer("song/scream.mp3", false).start();
							MultyPlayPage.b1point
									.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b1point.getText()) + 100));
							MultyPlayPage.client.sendToServer("point-1-100");
						} else {
							MultyPlayPage.Fields[i - m][j].setIcon(bombu);
						}
					} else if (MultyPlayPage.witchNumber == 2) {
						if (MultyPlayPage.loc[i - m][j].equals("bombermanblackback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackright")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackleft")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else if (MultyPlayPage.loc[i - m][j].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanwhiteleft")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblueback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblueleft")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblueright")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredfront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredleft")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredright")) {
							new MusicPlayer("song/scream.mp3", false).start();
							MultyPlayPage.b2point
									.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b2point.getText()) + 100));
							MultyPlayPage.client.sendToServer("point-2-100");
						} else {
							MultyPlayPage.Fields[i - m][j].setIcon(bombu);
						}
					} else if (MultyPlayPage.witchNumber == 3) {
						if (MultyPlayPage.loc[i - m][j].equals("bombermanblueback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblueright")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblueleft")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else if (MultyPlayPage.loc[i - m][j].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanwhiteleft")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackleft")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackright")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredfront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredleft")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredright")) {
							new MusicPlayer("song/scream.mp3", false).start();
							MultyPlayPage.b3point
									.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b3point.getText()) + 100));
							MultyPlayPage.client.sendToServer("point-3-100");
						} else {
							MultyPlayPage.Fields[i - m][j].setIcon(bombu);
						}
					} else if (MultyPlayPage.witchNumber == 4) {
						if (MultyPlayPage.loc[i - m][j].equals("bombermanredback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredfront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredright")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanredleft")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else if (MultyPlayPage.loc[i - m][j].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanwhiteleft")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackleft")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblackright")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblueback")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblueleft")
								|| MultyPlayPage.loc[i - m][j].equals("bombermanblueright")) {
							new MusicPlayer("song/scream.mp3", false).start();
							MultyPlayPage.b4point
									.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b4point.getText()) + 100));
							MultyPlayPage.client.sendToServer("point-4-100");
						} else {
							MultyPlayPage.Fields[i - m][j].setIcon(bombu);
						}
					}
					if (MultyPlayPage.loc[i][j + m].equals("wall") || MultyPlayPage.loc[i][j + m].equals("block")) {
					} else if (someoneElse) {
						if (MultyPlayPage.loc[i][j + m].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanwhiteleft")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackleft")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackright")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblueback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblueleft")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblueright")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredfront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredleft")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredright")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else {
							MultyPlayPage.Fields[i][j + m].setIcon(bombr);
						}
					} else if (MultyPlayPage.witchNumber == 1) {
						if (MultyPlayPage.loc[i][j + m].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanwhiteleft")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else if (MultyPlayPage.loc[i][j + m].equals("bombermanblackback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackleft")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackright")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblueback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblueleft")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblueright")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredfront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredleft")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredright")) {
							new MusicPlayer("song/scream.mp3", false).start();
							MultyPlayPage.b1point
									.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b1point.getText()) + 100));
							MultyPlayPage.client.sendToServer("point-1-100");
						} else {
							MultyPlayPage.Fields[i][j + m].setIcon(bombr);
						}
					} else if (MultyPlayPage.witchNumber == 2) {
						if (MultyPlayPage.loc[i][j + m].equals("bombermanblackback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackright")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackleft")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else if (MultyPlayPage.loc[i][j + m].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanwhiteleft")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblueback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblueleft")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblueright")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredfront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredleft")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredright")) {
							new MusicPlayer("song/scream.mp3", false).start();
							MultyPlayPage.b2point
									.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b2point.getText()) + 100));
							MultyPlayPage.client.sendToServer("point-2-100");
						} else {
							MultyPlayPage.Fields[i][j + m].setIcon(bombr);
						}
					} else if (MultyPlayPage.witchNumber == 3) {
						if (MultyPlayPage.loc[i][j + m].equals("bombermanblueback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblueright")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblueleft")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else if (MultyPlayPage.loc[i][j + m].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanwhiteleft")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackleft")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackright")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredfront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredleft")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredright")) {
							new MusicPlayer("song/scream.mp3", false).start();
							MultyPlayPage.b3point
									.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b3point.getText()) + 100));
							MultyPlayPage.client.sendToServer("point-3-100");
						} else {
							MultyPlayPage.Fields[i][j + m].setIcon(bombr);
						}
					} else if (MultyPlayPage.witchNumber == 4) {
						if (MultyPlayPage.loc[i][j + m].equals("bombermanredback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredfront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredright")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanredleft")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else if (MultyPlayPage.loc[i][j + m].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanwhiteleft")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackleft")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblackright")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblueback")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblueleft")
								|| MultyPlayPage.loc[i][j + m].equals("bombermanblueright")) {
							new MusicPlayer("song/scream.mp3", false).start();
							MultyPlayPage.b4point
									.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b4point.getText()) + 100));
							MultyPlayPage.client.sendToServer("point-4-100");
						} else {
							MultyPlayPage.Fields[i][j + m].setIcon(bombr);
						}
					}
					if (MultyPlayPage.loc[i + m][j].equals("wall") || MultyPlayPage.loc[i + m][j].equals("block")) {
					} else if (someoneElse) {
						if (MultyPlayPage.loc[i + m][j].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanwhiteleft")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackleft")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackright")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblueback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblueleft")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblueright")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredfront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredleft")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredright")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else {
							MultyPlayPage.Fields[i + m][j].setIcon(bombd);
						}
					} else if (MultyPlayPage.witchNumber == 1) {
						if (MultyPlayPage.loc[i + m][j].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanwhiteleft")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else if (MultyPlayPage.loc[i + m][j].equals("bombermanblackback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackleft")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackright")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblueback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblueleft")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblueright")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredfront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredleft")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredright")) {
							new MusicPlayer("song/scream.mp3", false).start();
							MultyPlayPage.b1point
									.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b1point.getText()) + 100));
							MultyPlayPage.client.sendToServer("point-1-100");
						} else {
							MultyPlayPage.Fields[i + m][j].setIcon(bombd);
						}
					} else if (MultyPlayPage.witchNumber == 2) {
						if (MultyPlayPage.loc[i + m][j].equals("bombermanblackback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackright")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackleft")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else if (MultyPlayPage.loc[i + m][j].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanwhiteleft")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblueback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblueleft")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblueright")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredfront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredleft")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredright")) {
							new MusicPlayer("song/scream.mp3", false).start();
							MultyPlayPage.b2point
									.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b2point.getText()) + 100));
							MultyPlayPage.client.sendToServer("point-2-100");
						} else {
							MultyPlayPage.Fields[i + m][j].setIcon(bombd);
						}
					} else if (MultyPlayPage.witchNumber == 3) {
						if (MultyPlayPage.loc[i + m][j].equals("bombermanblueback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblueright")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblueleft")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else if (MultyPlayPage.loc[i + m][j].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanwhiteleft")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackleft")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackright")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredfront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredleft")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredright")) {
							new MusicPlayer("song/scream.mp3", false).start();
							MultyPlayPage.b3point
									.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b3point.getText()) + 100));
							MultyPlayPage.client.sendToServer("point-3-100");
						} else {
							MultyPlayPage.Fields[i + m][j].setIcon(bombd);
						}
					} else if (MultyPlayPage.witchNumber == 4) {
						if (MultyPlayPage.loc[i + m][j].equals("bombermanredback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredfront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredright")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanredleft")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else if (MultyPlayPage.loc[i + m][j].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanwhiteleft")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackleft")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblackright")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblueback")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblueleft")
								|| MultyPlayPage.loc[i + m][j].equals("bombermanblueright")) {
							new MusicPlayer("song/scream.mp3", false).start();
							MultyPlayPage.b4point
									.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b4point.getText()) + 100));
							MultyPlayPage.client.sendToServer("point-4-100");
						} else {
							MultyPlayPage.Fields[i + m][j].setIcon(bombd);
						}
					}
					if (MultyPlayPage.loc[i][j - m].equals("wall") || MultyPlayPage.loc[i][j - m].equals("block")) {
					} else if (someoneElse) {
						if (MultyPlayPage.loc[i][j - m].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanwhiteleft")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackleft")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackright")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblueback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblueleft")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblueright")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredfront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredleft")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredright")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else {
							MultyPlayPage.Fields[i][j - m].setIcon(bombl);
						}
					} else if (MultyPlayPage.witchNumber == 1) {
						if (MultyPlayPage.loc[i][j - m].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanwhiteleft")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else if (MultyPlayPage.loc[i][j - m].equals("bombermanblackback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackleft")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackright")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblueback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblueleft")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblueright")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredfront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredleft")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredright")) {
							new MusicPlayer("song/scream.mp3", false).start();
							MultyPlayPage.b1point
									.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b1point.getText()) + 100));
							MultyPlayPage.client.sendToServer("point-1-100");
						} else {
							MultyPlayPage.Fields[i][j - m].setIcon(bombl);
						}
					} else if (MultyPlayPage.witchNumber == 2) {
						if (MultyPlayPage.loc[i][j - m].equals("bombermanblackback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackright")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackleft")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else if (MultyPlayPage.loc[i][j - m].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanwhiteleft")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblueback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblueleft")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblueright")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredfront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredleft")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredright")) {
							new MusicPlayer("song/scream.mp3", false).start();
							MultyPlayPage.b2point
									.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b2point.getText()) + 100));
							MultyPlayPage.client.sendToServer("point-2-100");
						} else {
							MultyPlayPage.Fields[i][j - m].setIcon(bombl);
						}
					} else if (MultyPlayPage.witchNumber == 3) {
						if (MultyPlayPage.loc[i][j - m].equals("bombermanblueback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblueright")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblueleft")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else if (MultyPlayPage.loc[i][j - m].equals("bombermanwhiteback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanwhiteleft")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackleft")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackright")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredfront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredleft")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredright")) {
							new MusicPlayer("song/scream.mp3", false).start();
							MultyPlayPage.b3point
									.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b3point.getText()) + 100));
							MultyPlayPage.client.sendToServer("point-3-100");
						} else {
							MultyPlayPage.Fields[i][j - m].setIcon(bombl);
						}
					} else if (MultyPlayPage.witchNumber == 4) {
						if (MultyPlayPage.loc[i][j - m].equals("bombermanredback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredfront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredright")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanredleft")) {
							new MusicPlayer("song/scream.mp3", false).start();
						} else if (MultyPlayPage.loc[i][j - m].equals("bombermanwhitefront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanwhiteleft")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanwhiteright")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackfront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackleft")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblackright")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblueback")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanbluefront")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblueleft")
								|| MultyPlayPage.loc[i][j - m].equals("bombermanblueright")) {
							new MusicPlayer("song/scream.mp3", false).start();
							MultyPlayPage.b4point
									.setText(String.valueOf(Integer.parseInt(MultyPlayPage.b4point.getText()) + 100));
							MultyPlayPage.client.sendToServer("point-4-100");
						} else {
							MultyPlayPage.Fields[i][j - m].setIcon(bombl);
						}
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				MultyPlayPage.Fields[i][j].setIcon(ground);
				MultyPlayPage.loc[i][j] = "ground";
				for (int m = 1; m <= radiation; m++) {
					if (MultyPlayPage.loc[i - m][j].equals("wall") || MultyPlayPage.loc[i - m][j].equals("block")
							|| MultyPlayPage.loc[i - m][j].equals("bombermanwhiteback")
							|| MultyPlayPage.loc[i - m][j].equals("bombermanwhitefront")
							|| MultyPlayPage.loc[i - m][j].equals("bombermanwhiteright")
							|| MultyPlayPage.loc[i - m][j].equals("bombermanwhiteleft")
							|| MultyPlayPage.loc[i - m][j].equals("bombermanblackback")
							|| MultyPlayPage.loc[i - m][j].equals("bombermanblackfront")
							|| MultyPlayPage.loc[i - m][j].equals("bombermanblackleft")
							|| MultyPlayPage.loc[i - m][j].equals("bombermanblackright")
							|| MultyPlayPage.loc[i - m][j].equals("bombermanblueback")
							|| MultyPlayPage.loc[i - m][j].equals("bombermanbluefront")
							|| MultyPlayPage.loc[i - m][j].equals("bombermanblueleft")
							|| MultyPlayPage.loc[i - m][j].equals("bombermanblueright")
							|| MultyPlayPage.loc[i - m][j].equals("bombermanredback")
							|| MultyPlayPage.loc[i - m][j].equals("bombermanredfront")
							|| MultyPlayPage.loc[i - m][j].equals("bombermanredleft")
							|| MultyPlayPage.loc[i - m][j].equals("bombermanredright")) {
					} else {
						MultyPlayPage.Fields[i - m][j].setIcon(ground);
						MultyPlayPage.loc[i - m][j] = "ground";
					}
					if (MultyPlayPage.loc[i][j + m].equals("wall") || MultyPlayPage.loc[i][j + m].equals("block")
							|| MultyPlayPage.loc[i][j + m].equals("bombermanwhiteback")
							|| MultyPlayPage.loc[i][j + m].equals("bombermanwhitefront")
							|| MultyPlayPage.loc[i][j + m].equals("bombermanwhiteright")
							|| MultyPlayPage.loc[i][j + m].equals("bombermanwhiteleft")
							|| MultyPlayPage.loc[i][j + m].equals("bombermanblackback")
							|| MultyPlayPage.loc[i][j + m].equals("bombermanblackfront")
							|| MultyPlayPage.loc[i][j + m].equals("bombermanblackleft")
							|| MultyPlayPage.loc[i][j + m].equals("bombermanblackright")
							|| MultyPlayPage.loc[i][j + m].equals("bombermanblueback")
							|| MultyPlayPage.loc[i][j + m].equals("bombermanbluefront")
							|| MultyPlayPage.loc[i][j + m].equals("bombermanblueleft")
							|| MultyPlayPage.loc[i][j + m].equals("bombermanblueright")
							|| MultyPlayPage.loc[i][j + m].equals("bombermanredback")
							|| MultyPlayPage.loc[i][j + m].equals("bombermanredfront")
							|| MultyPlayPage.loc[i][j + m].equals("bombermanredleft")
							|| MultyPlayPage.loc[i][j + m].equals("bombermanredright")) {
					} else {
						MultyPlayPage.Fields[i][j + m].setIcon(ground);
						MultyPlayPage.loc[i][j + m] = "ground";
					}
					if (MultyPlayPage.loc[i + m][j].equals("wall") || MultyPlayPage.loc[i + m][j].equals("block")
							|| MultyPlayPage.loc[i + m][j].equals("bombermanwhiteback")
							|| MultyPlayPage.loc[i + m][j].equals("bombermanwhitefront")
							|| MultyPlayPage.loc[i + m][j].equals("bombermanwhiteright")
							|| MultyPlayPage.loc[i + m][j].equals("bombermanwhiteleft")
							|| MultyPlayPage.loc[i + m][j].equals("bombermanblackback")
							|| MultyPlayPage.loc[i + m][j].equals("bombermanblackfront")
							|| MultyPlayPage.loc[i + m][j].equals("bombermanblackleft")
							|| MultyPlayPage.loc[i + m][j].equals("bombermanblackright")
							|| MultyPlayPage.loc[i + m][j].equals("bombermanblueback")
							|| MultyPlayPage.loc[i + m][j].equals("bombermanbluefront")
							|| MultyPlayPage.loc[i + m][j].equals("bombermanblueleft")
							|| MultyPlayPage.loc[i + m][j].equals("bombermanblueright")
							|| MultyPlayPage.loc[i + m][j].equals("bombermanredback")
							|| MultyPlayPage.loc[i + m][j].equals("bombermanredfront")
							|| MultyPlayPage.loc[i + m][j].equals("bombermanredleft")
							|| MultyPlayPage.loc[i + m][j].equals("bombermanredright")) {
					} else {
						MultyPlayPage.Fields[i + m][j].setIcon(ground);
						MultyPlayPage.loc[i + m][j] = "ground";
					}
					if (MultyPlayPage.loc[i][j - m].equals("wall") || MultyPlayPage.loc[i][j - m].equals("block")
							|| MultyPlayPage.loc[i][j - m].equals("bombermanwhiteback")
							|| MultyPlayPage.loc[i][j - m].equals("bombermanwhitefront")
							|| MultyPlayPage.loc[i][j - m].equals("bombermanwhiteright")
							|| MultyPlayPage.loc[i][j - m].equals("bombermanwhiteleft")
							|| MultyPlayPage.loc[i][j - m].equals("bombermanblackback")
							|| MultyPlayPage.loc[i][j - m].equals("bombermanblackfront")
							|| MultyPlayPage.loc[i][j - m].equals("bombermanblackleft")
							|| MultyPlayPage.loc[i][j - m].equals("bombermanblackright")
							|| MultyPlayPage.loc[i][j - m].equals("bombermanblueback")
							|| MultyPlayPage.loc[i][j - m].equals("bombermanbluefront")
							|| MultyPlayPage.loc[i][j - m].equals("bombermanblueleft")
							|| MultyPlayPage.loc[i][j - m].equals("bombermanblueright")
							|| MultyPlayPage.loc[i][j - m].equals("bombermanredback")
							|| MultyPlayPage.loc[i][j - m].equals("bombermanredfront")
							|| MultyPlayPage.loc[i][j - m].equals("bombermanredleft")
							|| MultyPlayPage.loc[i][j - m].equals("bombermanredright")) {
					} else {
						MultyPlayPage.Fields[i][j - m].setIcon(ground);
						MultyPlayPage.loc[i][j - m] = "ground";
					}
				}
			} else {
				SinglePlayPage.Fields[i][j].setIcon(bombc);
				for (int m = 1; m <= radiation; m++) {
					if (SinglePlayPage.loc[i - m][j].equals("wall") || SinglePlayPage.loc[i - m][j].equals("block")) {
					} else if (SinglePlayPage.loc[i - m][j].equals("BMgreen")
							|| SinglePlayPage.loc[i - m][j].equals("BMbrown")) {
						new MusicPlayer("song/scream.mp3", false).start();
						System.out.println("i faced a bomb");
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20 < 0) {
							SinglePlayPage.showHarth.setText("0");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20));
						}
					} else if (SinglePlayPage.loc[i - m][j].equals("ghostgreen")
							|| SinglePlayPage.loc[i - m][j].equals("ghostbrown")) {
						new MusicPlayer("song/sono_moo.mp3", false).start();
						for (int k = 0; k < Ghost.num; k++) {
							if (Ghost.ighost.get(k) == i - m && Ghost.jghost.get(k) == j) {
								Ghost.bool.set(k, false);
							}
						}
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 100));
						SinglePlayPage.loc[i - m][j].equals("block");
					} else if (SinglePlayPage.loc[i - m][j].equals("unblock")) {
						SinglePlayPage.Fields[i - m][j].setIcon(bombu);
					} else {
						SinglePlayPage.Fields[i - m][j].setIcon(bombu);
					}
					if (SinglePlayPage.loc[i][j + m].equals("wall") || SinglePlayPage.loc[i][j + m].equals("block")) {
					} else if (SinglePlayPage.loc[i][j + m].equals("BMgreen")
							|| SinglePlayPage.loc[i][j + m].equals("BMbrown")) {
						new MusicPlayer("song/scream.mp3", false).start();
						System.out.println("i faced a bomb");
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20 < 0) {
							SinglePlayPage.showHarth.setText("0");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20));
						}
					} else if (SinglePlayPage.loc[i][j + m].equals("ghostgreen")
							|| SinglePlayPage.loc[i][j + m].equals("ghostbrown")) {
						new MusicPlayer("song/sono_moo.mp3", false).start();
						for (int k = 0; k < Ghost.num; k++) {
							if (Ghost.ighost.get(k) == i && Ghost.jghost.get(k) == j + m) {
								Ghost.bool.set(k, false);
							}
						}
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 100));
						SinglePlayPage.loc[i][j + m].equals("block");
					} else if (SinglePlayPage.loc[i][j + m].equals("unblock")) {
						SinglePlayPage.Fields[i][j + m].setIcon(bombr);
					} else {
						SinglePlayPage.Fields[i][j + m].setIcon(bombr);
					}
					if (SinglePlayPage.loc[i + m][j].equals("wall") || SinglePlayPage.loc[i + m][j].equals("block")) {
					} else if (SinglePlayPage.loc[i + m][j].equals("BMgreen")
							|| SinglePlayPage.loc[i + m][j].equals("BMbrown")) {
						new MusicPlayer("song/scream.mp3", false).start();
						System.out.println("i faced a bomb");
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20 < 0) {
							SinglePlayPage.showHarth.setText("0");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20));
						}
					} else if (SinglePlayPage.loc[i + m][j].equals("ghostgreen")
							|| SinglePlayPage.loc[i + m][j].equals("ghostbrown")) {
						new MusicPlayer("song/sono_moo.mp3", false).start();
						for (int k = 0; k < Ghost.num; k++) {
							if (Ghost.ighost.get(k) == i + m && Ghost.jghost.get(k) == j) {
								Ghost.bool.set(k, false);
							}
						}
						SinglePlayPage.loc[i + m][j].equals("block");
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 100));
					} else if (SinglePlayPage.loc[i + m][j].equals("unblock")) {
						SinglePlayPage.Fields[i + m][j].setIcon(bombd);
					} else {
						SinglePlayPage.Fields[i + m][j].setIcon(bombd);
					}
					if (SinglePlayPage.loc[i][j - m].equals("wall") || SinglePlayPage.loc[i][j - 1].equals("block")) {
					} else if (SinglePlayPage.loc[i][j - m].equals("BMgreen")
							|| SinglePlayPage.loc[i][j - m].equals("BMbrown")) {
						new MusicPlayer("song/scream.mp3", false).start();
						System.out.println("i faced a bomb");
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20 < 0) {
							SinglePlayPage.showHarth.setText("0");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20));
						}
					} else if (SinglePlayPage.loc[i][j - m].equals("ghostgreen")
							|| SinglePlayPage.loc[i][j - m].equals("ghostbrown")) {
						new MusicPlayer("song/sono_moo.mp3", false).start();
						for (int k = 0; k < Ghost.num; k++) {
							if (Ghost.ighost.get(k) == i && Ghost.jghost.get(k) == j - m) {
								Ghost.bool.set(k, false);
							}
						}
						SinglePlayPage.showPoint
								.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showPoint.getText()) + 100));
						SinglePlayPage.loc[i][j - m].equals("block");
					} else if (SinglePlayPage.loc[i][j - m].equals("unblock")) {
						SinglePlayPage.Fields[i][j - m].setIcon(bombl);
					} else {
						SinglePlayPage.Fields[i][j - m].setIcon(bombl);
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				SinglePlayPage.Fields[i][j].setIcon(bg);
				SinglePlayPage.loc[i][j] = "brown";
				for (int m = 1; m <= radiation; m++) {
					if (SinglePlayPage.loc[i - m][j].equals("wall") || SinglePlayPage.loc[i - m][j].equals("block")
							|| SinglePlayPage.loc[i - m][j].equals("BMgreen")
							|| SinglePlayPage.loc[i - m][j].equals("BMbrown")) {
					} else {
						SinglePlayPage.Fields[i - m][j].setIcon(bg);
						SinglePlayPage.loc[i - m][j] = "brown";
					}
					if (SinglePlayPage.loc[i][j + m].equals("wall") || SinglePlayPage.loc[i][j + m].equals("block")
							|| SinglePlayPage.loc[i][j + m].equals("BMgreen")
							|| SinglePlayPage.loc[i][j + m].equals("BMbrown")) {
					} else {
						SinglePlayPage.Fields[i][j + m].setIcon(bg);
						SinglePlayPage.loc[i][j + m] = "brown";
					}
					if (SinglePlayPage.loc[i + m][j].equals("wall") || SinglePlayPage.loc[i + m][j].equals("block")
							|| SinglePlayPage.loc[i + m][j].equals("BMgreen")
							|| SinglePlayPage.loc[i + m][j].equals("BMbrown")) {
					} else {
						SinglePlayPage.Fields[i + m][j].setIcon(bg);
						SinglePlayPage.loc[i + m][j] = "brown";
					}
					if (SinglePlayPage.loc[i][j - m].equals("wall") || SinglePlayPage.loc[i][j - m].equals("block")
							|| SinglePlayPage.loc[i][j - m].equals("BMgreen")
							|| SinglePlayPage.loc[i][j - m].equals("BMbrown")) {
					} else {
						SinglePlayPage.Fields[i][j - m].setIcon(bg);
						SinglePlayPage.loc[i][j - m] = "brown";
					}
				}
			}
		}
	}
}
