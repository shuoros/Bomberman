package Code;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javazoom.jl.player.Player;

public class MusicPlayer extends Thread {

	private String pth;
	private boolean repeat;

	public MusicPlayer(String pth, boolean repeat) {
		this.pth = pth;
		this.repeat = repeat;
	}

	public void run() {
		File file = new File(pth);
		FileInputStream fis;
		BufferedInputStream bis = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Player player = new Player(bis);
			if(repeat) {
				while(true) {
					player.play();
				}
			}else {
				player.play();
			}
		} catch (Exception e) {
		}
	}
}
