package Frame;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import DataBase.Connect;

public class LoadPage {

	private JFrame frame;
	static int counter = 0;
	static boolean closed = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadPage window = new LoadPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoadPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 400, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("");
		label.setBounds(0, 0, 400, 431);
		frame.getContentPane().add(label);
		ImageIcon logo = new ImageIcon(new ImageIcon("img/University_of_Guilan_Logo.jpeg").getImage()
				.getScaledInstance(400, 431, Image.SCALE_SMOOTH));
		label.setIcon(logo);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 100, 0));
		progressBar.setBounds(0, 492, 400, 16);
		frame.getContentPane().add(progressBar);
		// progressBar.setStringPainted(true);
		String createTableSQL = "CREATE TABLE IF NOT EXISTS players (" +
                                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                                    "username VARCHAR(50) NOT NULL," +
                                    "password VARCHAR(50) NOT NULL," +
                                    "email VARCHAR(50) NOT NULL," +
                                    "pic blob," +
                                    "highscore DOUBLE," +
                                    "level INTEGER," +
                                    "xp DOUBLE" +
                                    ")";
		Connection con = Connect.getConnection();
		try {
			con.createStatement().executeUpdate(createTableSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Timer timer = new Timer(25, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter++;
				progressBar.setValue(counter);
				if (counter == 101) {
					IntroPage ip = new IntroPage();
					ip.setVisible(true);
					ip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.dispose();
					closed = true;
				}
			}
		});
		timer.start();
		if (closed) {
			timer.stop();
		}
	}
}
