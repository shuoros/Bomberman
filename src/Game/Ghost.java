package Game;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Code.MusicPlayer;
import Frame.SinglePlayPage;

public class Ghost {
	public static int num;
	public static ArrayList<Integer> ighost = new ArrayList<>();
	public static ArrayList<Integer> jghost = new ArrayList<>();
	public static ArrayList<Boolean> bool = new ArrayList<>();

	public Ghost(int num) {
		this.num = num;
			
			for (int i = 0; i < num; i++) {
				int x, y;
				x = 1 + (int) (Math.random() * (18));
				y = 1 + (int) (Math.random() * (18));
				while (SinglePlayPage.loc[x][y].equals("block") || SinglePlayPage.loc[x][y].equals("unblock")
						|| SinglePlayPage.loc[x][y].equals("wall") || SinglePlayPage.loc[x][y].equals("ghost")
						|| SinglePlayPage.loc[x][y].equals("BMgreen") || SinglePlayPage.loc[x][y].equals("BMbrown")) {
					x = 1 + (int) (Math.random() * (18));
					y = 1 + (int) (Math.random() * (18));
				}
				ighost.add(x);
				jghost.add(y);
				bool.add(true);
				new Handler(x, y, i).start();

		}
	}

	public int random(int min, int max) {
		int rand = min + (int) (Math.random() * ((max - min) + 1));
		return rand;
	}

	private static class Handler extends Thread {
		private int i, j, num;
		ImageIcon ghostfrontbrown = new ImageIcon(new ImageIcon("img/characters/ghostfrontbrown.png").getImage()
				.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
		ImageIcon ghostfrontgreen = new ImageIcon(new ImageIcon("img/characters/ghostfrontgreen.png").getImage()
				.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
		ImageIcon ghostleftbrown = new ImageIcon(new ImageIcon("img/characters/ghostleftbrown.png").getImage()
				.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
		ImageIcon ghostleftgreen = new ImageIcon(new ImageIcon("img/characters/ghostleftgreen.png").getImage()
				.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
		ImageIcon ghostrightgreen = new ImageIcon(new ImageIcon("img/characters/ghostrightgreen.png").getImage()
				.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
		ImageIcon ghostrightbrown = new ImageIcon(new ImageIcon("img/characters/ghostrightbrown.png").getImage()
				.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
		ImageIcon ghostbackbrown = new ImageIcon(new ImageIcon("img/characters/ghostbackbrown.png").getImage()
				.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
		ImageIcon ghostbackgreen = new ImageIcon(new ImageIcon("img/characters/ghostbackgreen.png").getImage()
				.getScaledInstance(63, 53, Image.SCALE_SMOOTH));
		ImageIcon bg1 = new ImageIcon(
				new ImageIcon("img/grass1.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));
		ImageIcon bg2 = new ImageIcon(
				new ImageIcon("img/grass2.png").getImage().getScaledInstance(63, 53, Image.SCALE_SMOOTH));

		public Handler(int firstI, int firstJ, int num) {
			this.i = firstI;
			this.j = firstJ;
			this.num = num;
			if (SinglePlayPage.loc[firstI][firstJ].equals("green")) {
				SinglePlayPage.Fields[firstI][firstJ].setIcon(ghostfrontgreen);
				SinglePlayPage.loc[firstI][firstJ] = "ghostgreen";
			} else if (SinglePlayPage.loc[firstI][firstJ].equals("brown")) {
				SinglePlayPage.Fields[firstI][firstJ].setIcon(ghostfrontbrown);
				SinglePlayPage.loc[firstI][firstJ] = "ghostbrown";
			}
		}

		public int random(int min, int max) {
			int rand = min + (int) (Math.random() * ((max - min) + 1));
			return rand;
		}

		public void run() {
			while (bool.get(num)) {
				int rand = random(1, 4);
				if (rand == 1) {
					if (SinglePlayPage.loc[i - 1][j].equals("wall") || SinglePlayPage.loc[i - 1][j].equals("block")
							|| SinglePlayPage.loc[i - 1][j].equals("unblock")
							|| SinglePlayPage.loc[i - 1][j].equals("ghostbrown")
							|| SinglePlayPage.loc[i - 1][j].equals("ghostgreen")
							|| SinglePlayPage.loc[i - 1][j].equals("bomb")
							|| SinglePlayPage.loc[i - 1][j].equals("blodgreen")
							|| SinglePlayPage.loc[i - 1][j].equals("blodbrown")
							|| SinglePlayPage.loc[i - 1][j].equals("ubombgreen")
							|| SinglePlayPage.loc[i - 1][j].equals("ubombbrown")
							|| SinglePlayPage.loc[i - 1][j].equals("pointgreen")
							|| SinglePlayPage.loc[i - 1][j].equals("pointbrown")
							|| SinglePlayPage.loc[i - 1][j].equals("dead")) {
						continue;
					} else if (SinglePlayPage.loc[i - 1][j].equals("BMgreen")) {
						i++;
						System.out.println("a ghost faced me");
						new MusicPlayer("song/scream.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20 < 0) {
							SinglePlayPage.showHarth.setText("0");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20));
						}
					} else if (SinglePlayPage.loc[i - 1][j].equals("BMbrown")) {
						i++;
						System.out.println("a ghost faced me");
						new MusicPlayer("song/scream.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20 < 0) {
							SinglePlayPage.showHarth.setText("0");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20));
						}
					} else if (SinglePlayPage.loc[i - 1][j].equals("green")) {
						SinglePlayPage.Fields[i - 1][j].setIcon(ghostbackgreen);
						SinglePlayPage.loc[i - 1][j] = "ghostgreen";
						if (SinglePlayPage.loc[i][j].equals("ghostgreen")) {
							SinglePlayPage.Fields[i][j].setIcon(bg1);
							SinglePlayPage.loc[i][j] = "green";
						} else if (SinglePlayPage.loc[i][j].equals("ghostbrown")) {
							SinglePlayPage.Fields[i][j].setIcon(bg2);
							SinglePlayPage.loc[i][j] = "brown";
						}
					} else if (SinglePlayPage.loc[i - 1][j].equals("brown")) {
						SinglePlayPage.Fields[i - 1][j].setIcon(ghostbackbrown);
						SinglePlayPage.loc[i - 1][j] = "ghostbrown";
						if (SinglePlayPage.loc[i][j].equals("ghostgreen")) {
							SinglePlayPage.Fields[i][j].setIcon(bg1);
							SinglePlayPage.loc[i][j] = "green";
						} else if (SinglePlayPage.loc[i][j].equals("ghostbrown")) {
							SinglePlayPage.Fields[i][j].setIcon(bg2);
							SinglePlayPage.loc[i][j] = "brown";
						}
					}
					i--;
					ighost.set(num, i);
					jghost.set(num, j);
				} else if (rand == 2) {
					if (SinglePlayPage.loc[i][j + 1].equals("wall") || SinglePlayPage.loc[i][j + 1].equals("block")
							|| SinglePlayPage.loc[i][j + 1].equals("unblock")
							|| SinglePlayPage.loc[i][j + 1].equals("ghostbrown")
							|| SinglePlayPage.loc[i][j + 1].equals("ghostgreen")
							|| SinglePlayPage.loc[i][j + 1].equals("bomb")
							|| SinglePlayPage.loc[i][j + 1].equals("blodgreen")
							|| SinglePlayPage.loc[i][j + 1].equals("blodbrown")
							|| SinglePlayPage.loc[i][j + 1].equals("ubombgreen")
							|| SinglePlayPage.loc[i][j + 1].equals("ubombbrown")
							|| SinglePlayPage.loc[i][j + 1].equals("pointgreen")
							|| SinglePlayPage.loc[i][j + 1].equals("pointbrown")
							|| SinglePlayPage.loc[i][j + 1].equals("dead")) {
						continue;
					} else if (SinglePlayPage.loc[i][j + 1].equals("BMgreen")) {
						j--;
						System.out.println("a ghost faced me");
						new MusicPlayer("song/scream.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20 < 0) {
							SinglePlayPage.showHarth.setText("0");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20));
						}
					} else if (SinglePlayPage.loc[i][j + 1].equals("BMbrown")) {
						j--;
						System.out.println("a ghost faced me");
						new MusicPlayer("song/scream.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20 < 0) {
							SinglePlayPage.showHarth.setText("0");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20));
						}
					} else if (SinglePlayPage.loc[i][j + 1].equals("green")) {
						SinglePlayPage.Fields[i][j + 1].setIcon(ghostrightgreen);
						SinglePlayPage.loc[i][j + 1] = "ghostgreen";
						if (SinglePlayPage.loc[i][j].equals("ghostgreen")) {
							SinglePlayPage.Fields[i][j].setIcon(bg1);
							SinglePlayPage.loc[i][j] = "green";
						} else if (SinglePlayPage.loc[i][j].equals("ghostbrown")) {
							SinglePlayPage.Fields[i][j].setIcon(bg2);
							SinglePlayPage.loc[i][j] = "brown";
						}
					} else if (SinglePlayPage.loc[i][j + 1].equals("brown")) {
						SinglePlayPage.Fields[i][j + 1].setIcon(ghostrightbrown);
						SinglePlayPage.loc[i][j + 1] = "ghostbrown";
						if (SinglePlayPage.loc[i][j].equals("ghostgreen")) {
							SinglePlayPage.Fields[i][j].setIcon(bg1);
							SinglePlayPage.loc[i][j] = "green";
						} else if (SinglePlayPage.loc[i][j].equals("ghostbrown")) {
							SinglePlayPage.Fields[i][j].setIcon(bg2);
							SinglePlayPage.loc[i][j] = "brown";
						}
					}
					j++;
					ighost.set(num, i);
					jghost.set(num, j);
				} else if (rand == 3) {
					if (SinglePlayPage.loc[i + 1][j].equals("wall") || SinglePlayPage.loc[i + 1][j].equals("block")
							|| SinglePlayPage.loc[i + 1][j].equals("unblock")
							|| SinglePlayPage.loc[i + 1][j].equals("ghostbrown")
							|| SinglePlayPage.loc[i + 1][j].equals("ghostgreen")
							|| SinglePlayPage.loc[i + 1][j].equals("bomb")
							|| SinglePlayPage.loc[i + 1][j].equals("blodgreen")
							|| SinglePlayPage.loc[i + 1][j].equals("blodbrown")
							|| SinglePlayPage.loc[i + 1][j].equals("ubombgreen")
							|| SinglePlayPage.loc[i + 1][j].equals("ubombbrown")
							|| SinglePlayPage.loc[i + 1][j].equals("pointgreen")
							|| SinglePlayPage.loc[i + 1][j].equals("pointbrown")
							|| SinglePlayPage.loc[i + 1][j].equals("dead")) {
						continue;
					} else if (SinglePlayPage.loc[i + 1][j].equals("BMgreen")) {
						i--;
						System.out.println("a ghost faced me");
						new MusicPlayer("song/scream.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20 < 0) {
							SinglePlayPage.showHarth.setText("0");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20));
						}
					} else if (SinglePlayPage.loc[i + 1][j].equals("BMbrown")) {
						i--;
						System.out.println("a ghost faced me");
						new MusicPlayer("song/scream.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20 < 0) {
							SinglePlayPage.showHarth.setText("0");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20));
						}
					} else if (SinglePlayPage.loc[i + 1][j].equals("green")) {
						SinglePlayPage.Fields[i + 1][j].setIcon(ghostfrontgreen);
						SinglePlayPage.loc[i + 1][j] = "ghostgreen";
						if (SinglePlayPage.loc[i][j].equals("ghostgreen")) {
							SinglePlayPage.Fields[i][j].setIcon(bg1);
							SinglePlayPage.loc[i][j] = "green";
						} else if (SinglePlayPage.loc[i][j].equals("ghostbrown")) {
							SinglePlayPage.Fields[i][j].setIcon(bg2);
							SinglePlayPage.loc[i][j] = "brown";
						}
					} else if (SinglePlayPage.loc[i + 1][j].equals("brown")) {
						SinglePlayPage.Fields[i + 1][j].setIcon(ghostfrontbrown);
						SinglePlayPage.loc[i + 1][j] = "ghostbrown";
						if (SinglePlayPage.loc[i][j].equals("ghostgreen")) {
							SinglePlayPage.Fields[i][j].setIcon(bg1);
							SinglePlayPage.loc[i][j] = "green";
						} else if (SinglePlayPage.loc[i][j].equals("ghostbrown")) {
							SinglePlayPage.Fields[i][j].setIcon(bg2);
							SinglePlayPage.loc[i][j] = "brown";
						}
					}
					i++;
					ighost.set(num, i);
					jghost.set(num, j);
				} else if (rand == 4) {
					if (SinglePlayPage.loc[i][j - 1].equals("wall") || SinglePlayPage.loc[i][j - 1].equals("block")
							|| SinglePlayPage.loc[i][j - 1].equals("unblock")
							|| SinglePlayPage.loc[i][j - 1].equals("ghostbrown")
							|| SinglePlayPage.loc[i][j - 1].equals("ghostgreen")
							|| SinglePlayPage.loc[i][j - 1].equals("bomb")
							|| SinglePlayPage.loc[i][j - 1].equals("blodgreen")
							|| SinglePlayPage.loc[i][j - 1].equals("blodbrown")
							|| SinglePlayPage.loc[i][j - 1].equals("ubombgreen")
							|| SinglePlayPage.loc[i][j - 1].equals("ubombbrown")
							|| SinglePlayPage.loc[i][j - 1].equals("pointgreen")
							|| SinglePlayPage.loc[i][j - 1].equals("pointbrown")
							|| SinglePlayPage.loc[i][j - 1].equals("dead")) {
						continue;
					} else if (SinglePlayPage.loc[i][j - 1].equals("BMgreen")) {
						j++;
						System.out.println("a ghost faced me");
						new MusicPlayer("song/scream.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20 < 0) {
							SinglePlayPage.showHarth.setText("0");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20));
						}
					} else if (SinglePlayPage.loc[i][j - 1].equals("BMbrown")) {
						j++;
						System.out.println("a ghost faced me");
						new MusicPlayer("song/scream.mp3", false).start();
						if (Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20 < 0) {
							SinglePlayPage.showHarth.setText("0");
						} else {
							SinglePlayPage.showHarth
									.setText(String.valueOf(Integer.parseInt(SinglePlayPage.showHarth.getText()) - 20));
						}
					} else if (SinglePlayPage.loc[i][j - 1].equals("green")) {
						SinglePlayPage.Fields[i][j - 1].setIcon(ghostleftgreen);
						SinglePlayPage.loc[i][j - 1] = "ghostgreen";
						if (SinglePlayPage.loc[i][j].equals("ghostgreen")) {
							SinglePlayPage.Fields[i][j].setIcon(bg1);
							SinglePlayPage.loc[i][j] = "green";
						} else if (SinglePlayPage.loc[i][j].equals("ghostbrown")) {
							SinglePlayPage.Fields[i][j].setIcon(bg2);
							SinglePlayPage.loc[i][j] = "brown";
						}
					} else if (SinglePlayPage.loc[i][j - 1].equals("brown")) {
						SinglePlayPage.Fields[i][j - 1].setIcon(ghostleftbrown);
						SinglePlayPage.loc[i][j - 1] = "ghostbrown";
						if (SinglePlayPage.loc[i][j].equals("ghostgreen")) {
							SinglePlayPage.Fields[i][j].setIcon(bg1);
							SinglePlayPage.loc[i][j] = "green";
						} else if (SinglePlayPage.loc[i][j].equals("ghostbrown")) {
							SinglePlayPage.Fields[i][j].setIcon(bg2);
							SinglePlayPage.loc[i][j] = "brown";
						}
					}
					j--;
					ighost.set(num, i);
					jghost.set(num, j);
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
