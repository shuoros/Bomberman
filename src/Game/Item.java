package Game;

import java.awt.Image;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;

import Client.Client;
import Frame.MultyPlayPage;
import Frame.SinglePlayPage;

public class Item extends Thread {
	private int i, j;
	private String type;
	private boolean online = false;
	private Socket socket;
	private Client client;
	String color;

	public Item(int i, int j, String type) {
		this.i = i;
		this.j = j;
		this.type = type;
	}

	public Item(int i, int j, boolean online, String type) {
		this.i = i;
		this.j = j;
		this.online = online;
		try {
			this.socket = new Socket("localhost", 8585);
			this.client = new Client(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.type = type;
	}

	public void run() {
		if (online) {
			if (type.equals("ubomb")) {
				client.sendToServer("item-ubomb-" + String.valueOf(i)
				+ "-" + String.valueOf(j));
			} else if(type.equals("point")) {
				client.sendToServer("item-point-" + String.valueOf(i)
				+ "-" + String.valueOf(j));
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			client.sendToServer("item-ground-" + String.valueOf(i)
			+ "-" + String.valueOf(j));
		} else {
			if (type.equals("blod")) {
				System.out.println("here");
				if (SinglePlayPage.loc[i][j].equals("green")) {
					color = "green";
					ImageIcon bg = new ImageIcon(new ImageIcon("img/characters/healthgreen.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					SinglePlayPage.Fields[i][j].setIcon(bg);
					SinglePlayPage.loc[i][j] = "blodgreen";
				} else if (SinglePlayPage.loc[i][j].equals("brown")) {
					color = "brown";
					ImageIcon bg = new ImageIcon(new ImageIcon("img/characters/healthbrown.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					SinglePlayPage.Fields[i][j].setIcon(bg);
					SinglePlayPage.loc[i][j] = "blodbrown";
				}
			} else if (type.equals("ubomb")) {
				System.out.println("here");
				if (SinglePlayPage.loc[i][j].equals("green")) {
					color = "green";
					ImageIcon bg = new ImageIcon(new ImageIcon("img/characters/firegreen.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					SinglePlayPage.Fields[i][j].setIcon(bg);
					SinglePlayPage.loc[i][j] = "ubombgreen";
				} else if (SinglePlayPage.loc[i][j].equals("brown")) {
					color = "brown";
					ImageIcon bg = new ImageIcon(new ImageIcon("img/characters/firebrown.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					SinglePlayPage.Fields[i][j].setIcon(bg);
					SinglePlayPage.loc[i][j] = "ubombbrown";
				}
			} else if (type.equals("point")) {
				System.out.println("here");
				if (SinglePlayPage.loc[i][j].equals("green")) {
					color = "green";
					ImageIcon bg = new ImageIcon(new ImageIcon("img/characters/pointgreen.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					SinglePlayPage.Fields[i][j].setIcon(bg);
					SinglePlayPage.loc[i][j] = "pointgreen";
				} else if (SinglePlayPage.loc[i][j].equals("brown")) {
					color = "brown";
					ImageIcon bg = new ImageIcon(new ImageIcon("img/characters/pointbrown.png").getImage()
							.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
					SinglePlayPage.Fields[i][j].setIcon(bg);
					SinglePlayPage.loc[i][j] = "pointbrown";
				}
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (color.equals("green")) {
				ImageIcon bg = new ImageIcon(
						new ImageIcon("img/grass1.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				SinglePlayPage.Fields[i][j].setIcon(bg);
				SinglePlayPage.loc[i][j] = "green";
			} else if (color.equals("brown")) {
				ImageIcon bg = new ImageIcon(
						new ImageIcon("img/grass2.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
				SinglePlayPage.Fields[i][j].setIcon(bg);
				SinglePlayPage.loc[i][j] = "brown";
			}
		}
	}
}
