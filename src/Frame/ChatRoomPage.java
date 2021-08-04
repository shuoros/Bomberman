package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Code.MusicPlayer;
import Player.PlayerQuery;

public class ChatRoomPage extends JFrame {

	private JPanel contentPane;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JLabel username;
	private JLabel lbl;
	private JLabel level;
	private JLabel lbl1;
	PlayerQuery pq = new PlayerQuery();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatRoomPage frame = new ChatRoomPage();
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
	public ChatRoomPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setUndecorated(true);
		ImageIcon iconLogo = new ImageIcon(
				new ImageIcon("img/PEPCRPage.png").getImage().getScaledInstance((int)screenSize.getWidth(), (int)screenSize.getHeight() , Image.SCALE_SMOOTH));
		contentPane.setLayout(null);
		
		JLabel profilePic = new JLabel();
		profilePic.setBounds(20, 27, 97, 97);
		contentPane.add(profilePic);
		ImageIcon pic = new ImageIcon(new ImageIcon(pq.players().get(MainPage.currentPlayerId - 1).getPic()).getImage()
				.getScaledInstance(97, 97, Image.SCALE_SMOOTH));
		profilePic.setIcon(pic);
		
		username = new JLabel("");
		username.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		username.setForeground(new Color(255, 204, 0));
		username.setBounds(20, 156, 126, 28);
		contentPane.add(username);
		username.setText(pq.players().get(MainPage.currentPlayerId - 1).getUserName());
		
		JRadioButton onlineOffline = new JRadioButton("Offline");
		onlineOffline.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		onlineOffline.setOpaque(false);
		onlineOffline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(MainPage.online) {
					onlineOffline.setForeground(new Color(255, 0, 0));
					onlineOffline.setText("Offline");
					MainPage.online = false;
				}else {
					onlineOffline.setForeground(new Color(0, 255, 0));
					onlineOffline.setText("Online");
					MainPage.online = true;
				}
			}
		});
		onlineOffline.setForeground(new Color(255, 0, 0));
		onlineOffline.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		onlineOffline.setBounds(14, 137, 103, 21);
		contentPane.add(onlineOffline);
		
		level = new JLabel("");
		level.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		level.setForeground(new Color(255, 204, 0));
		level.setBounds(72, 182, 51, 28);
		contentPane.add(level);
		level.setText(String.valueOf(pq.players().get(MainPage.currentPlayerId - 1).getLevel()));
		
		lbl = new JLabel("Level :");
		lbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		lbl.setForeground(new Color(255, 204, 0));
		lbl.setBounds(20, 182, 51, 28);
		contentPane.add(lbl);
		
		lbl1 = new JLabel("BomberMan ChatRoom");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		lbl1.setForeground(new Color(255, 204, 0));
		lbl1.setBounds(633, 48, 500, 28);
		contentPane.add(lbl1);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBackground(new Color(50, 50, 50));
		progressBar.setForeground(new Color(255, 204, 0));
		progressBar.setBounds(0, 220, 220, 30);
		progressBar.setStringPainted(true);
		contentPane.add(progressBar);
		progressBar.setValue((int) (pq.players().get(MainPage.currentPlayerId - 1).getXp() * 100)
				/ (pq.players().get(MainPage.currentPlayerId - 1).getLevel() * 1000));
		
		JButton mainPage = new JButton("main page");
		mainPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				MainPage mp = new MainPage();
				mp.setVisible(true);
				dispose();
			}
		});
		mainPage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainPage.setBackground(new Color(50, 50, 50));
		mainPage.setForeground(new Color(255, 204, 0));
		mainPage.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		mainPage.setBounds(0, 269, 220, 31);
		contentPane.add(mainPage);
		
		JButton profile = new JButton("profile");
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				ProfilePage pp = new ProfilePage();
				pp.setVisible(true);
				dispose();
			}
		});
		profile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		profile.setBackground(new Color(50, 50, 50));
		profile.setForeground(new Color(255, 204, 0));
		profile.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		profile.setBounds(0, 310, 220, 31);
		contentPane.add(profile);
		
		JButton chat = new JButton("chat room");
		chat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
			}
		});
		chat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chat.setBackground(new Color(51, 153, 255));
		chat.setForeground(new Color(255, 204, 0));
		chat.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		chat.setBounds(0, 351, 220, 31);
		contentPane.add(chat);
		
		JButton logout = new JButton("log out");
		logout.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				LoginPage lp = new LoginPage();
				lp.setVisible(true);
				lp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				MainPage.mp.stop();
				dispose();
			}
		});
		logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logout.setBackground(new Color(50, 50, 50));
		logout.setForeground(new Color(255, 204, 0));
		logout.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		logout.setBounds(0, 391, 220, 31);
		contentPane.add(logout);
		
		JButton exit = new JButton("exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				System.exit(0);
			}
		});
		exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit.setBackground(new Color(50, 50, 50));
		exit.setForeground(new Color(255, 204, 0));
		exit.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		exit.setBounds(0, 431, 220, 31);
		contentPane.add(exit);
		
		JLabel backGround = new JLabel("");
		backGround.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
		contentPane.add(backGround);
		backGround.setIcon(iconLogo);
	}
}
