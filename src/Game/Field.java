package Game;

import java.awt.Image;

import javax.swing.ImageIcon;

import Frame.MultyPlayPage;
import Frame.SinglePlayPage;

public class Field {
	private String type;
	private static String[][] loc = new String[20][20];

	public Field(String type) {
		this.type = type;
		if (type.equals("online")) {
			new randomItemsDistributionOnline().start();
		} else {
			new randomItemsDistribution().start();
		}
	}

	public String[][] generateField() {
		for (int i = 0; i < 20; i++) {
			loc[0][i] = "wall";
		}
		for (int i = 0; i < 20; i++) {
			loc[19][i] = "wall";
		}
		for (int i = 1; i < 19; i++) {
			for (int j = 0; j < 20; j++) {
				if ((j + 1) % 3 == 0 && i % 2 != 0) {
					loc[i][j] = "block";
				} else if (j == 0 || j == 19) {
					loc[i][j] = "wall";
				} else {
					int rand = 1 + (int) (Math.random() * ((1) + 1));
					if (rand == 1) {
						loc[i][j] = "green";
					} else if (rand == 2) {
						loc[i][j] = "brown";
					}
				}
			}
		}
		if (type.equals("Eazy")) {
			for (int i = 0; i < 50; i++) {
				int x, y;
				x = (int) (Math.random() * (20));
				y = (int) (Math.random() * (20));
				while (loc[x][y].equals("block") || loc[x][y].equals("wall")) {
					x = (int) (Math.random() * (20));
					y = (int) (Math.random() * (20));
				}
				loc[x][y] = "unblock";
			}
		} else if (type.equals("Normal")) {
			for (int i = 0; i < 100; i++) {
				int x, y;
				x = (int) (Math.random() * (20));
				y = (int) (Math.random() * (20));
				while (loc[x][y].equals("block") || loc[x][y].equals("wall")) {
					x = (int) (Math.random() * (20));
					y = (int) (Math.random() * (20));
				}
				loc[x][y] = "unblock";
			}
		} else if (type.equals("Hard")) {
			for (int i = 0; i < 200; i++) {
				int x, y;
				x = (int) (Math.random() * (20));
				y = (int) (Math.random() * (20));
				while (loc[x][y].equals("block") || loc[x][y].equals("wall")) {
					x = (int) (Math.random() * (20));
					y = (int) (Math.random() * (20));
				}
				loc[x][y] = "unblock";
			}
		}
		return loc;
	}

	public String[][] generateOnlineField() {
		for (int i = 0; i < 20; i++) {
			loc[0][i] = "wall";
		}
		for (int i = 0; i < 20; i++) {
			loc[19][i] = "wall";
		}
		for (int i = 1; i < 19; i++) {
			for (int j = 0; j < 20; j++) {
				if ((j + 1) % 3 == 0 && i % 2 != 0) {
					loc[i][j] = "block";
				} else if (j == 0 || j == 19) {
					loc[i][j] = "wall";
				} else {
					loc[i][j] = "ground";
				}
			}
		}
		return loc;
	}

	public String[][] updateField() {
		return loc;
	}

	public void changeLoc(int x, int y, String dir) {
		loc[x][y] = dir;
	}

	private static class randomItemsDistribution extends Thread {

		public randomItemsDistribution() {
		}

		public int random(int min, int max) {
			int rand = min + (int) (Math.random() * ((max - min) + 1));
			return rand;
		}

		public void run() {
			int rand;
			while (true) {
				rand = random(1, 6);
				System.out.println("item in " + rand);
				try {
					Thread.sleep(10000 * rand);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				rand = random(1, 3);
				int x, y;
				x = 1 + (int) (Math.random() * (18));
				y = 1 + (int) (Math.random() * (18));
				while (SinglePlayPage.loc[x][y].equals("block") || SinglePlayPage.loc[x][y].equals("unblock")
						|| SinglePlayPage.loc[x][y].equals("wall") || SinglePlayPage.loc[x][y].equals("ghostgreen")
						|| SinglePlayPage.loc[x][y].equals("ghostbrown") || SinglePlayPage.loc[x][y].equals("BMgreen")
						|| SinglePlayPage.loc[x][y].equals("BMbrown")) {
					x = 1 + (int) (Math.random() * (18));
					y = 1 + (int) (Math.random() * (18));
				}
				if (rand == 1) {
					new Item(x, y, "blod").start();
				} else if (rand == 2) {
					new Item(x, y, "ubomb").start();
				} else if (rand == 3) {
					new Item(x, y, "point").start();
				}
			}
		}
	}
	private static class randomItemsDistributionOnline extends Thread{
		
		public randomItemsDistributionOnline() {
			
		}
		
		public int random(int min, int max) {
			int rand = min + (int) (Math.random() * ((max - min) + 1));
			return rand;
		}
		
		public void run() {
			int rand;
			while(true) {
				rand = random(1, 6);
				System.out.println("item in " + rand);
				try {
					Thread.sleep(10000 * rand);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				rand = random(1, 2);
				int x, y;
				x = 1 + (int) (Math.random() * (18));
				y = 1 + (int) (Math.random() * (18));
				while (MultyPlayPage.loc[x][y].equals("block")
						|| MultyPlayPage.loc[x][y].equals("wall") 
						|| MultyPlayPage.loc[x][y].equals("bombermanblackback")
						|| MultyPlayPage.loc[x][y].equals("bombermanblackfront")
						|| MultyPlayPage.loc[x][y].equals("bombermanblackleft")
						|| MultyPlayPage.loc[x][y].equals("bombermanblackright")
						|| MultyPlayPage.loc[x][y].equals("bombermanblueback")
						|| MultyPlayPage.loc[x][y].equals("bombermanbluefront")
						|| MultyPlayPage.loc[x][y].equals("bombermanblueleft")
						|| MultyPlayPage.loc[x][y].equals("bombermanblueright")
						|| MultyPlayPage.loc[x][y].equals("bombermanredback")
						|| MultyPlayPage.loc[x][y].equals("bombermanredfront")
						|| MultyPlayPage.loc[x][y].equals("bombermanredleft")
						|| MultyPlayPage.loc[x][y].equals("bombermanredright")
						|| MultyPlayPage.loc[x][y].equals("bombermanwhiteback")
						|| MultyPlayPage.loc[x][y].equals("bombermanwhitefront")
						|| MultyPlayPage.loc[x][y].equals("bombermanwhiteleft")
						|| MultyPlayPage.loc[x][y].equals("bombermanwhiteright")) {
					x = 1 + (int) (Math.random() * (18));
					y = 1 + (int) (Math.random() * (18));
				}
				if (rand == 1) {
					new Item(x, y, true, "ubomb").start();
					
				} else if (rand == 2) {
					new Item(x, y, true, "point").start();
				} 
			}
		}
	}
}
