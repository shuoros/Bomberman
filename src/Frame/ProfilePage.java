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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Code.MusicPlayer;
import Player.PlayerQuery;

public class ProfilePage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3269583406955786574L;
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
					ProfilePage frame = new ProfilePage();
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
	public ProfilePage() {
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
		
		lbl1 = new JLabel("Profile");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		lbl1.setForeground(new Color(255, 204, 0));
		lbl1.setBounds(633, 48, 500, 28);
		contentPane.add(lbl1);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBackground(new Color(50, 50, 50));
		progressBar.setForeground(new Color(255, 204, 0));
		progressBar.setBounds(0, (int) (screenSize.getHeight()*24.4/100), (int) (screenSize.getWidth()*14.4/100), (int) (screenSize.getHeight()*5/100));
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
		mainPage.setBounds(0, (int) (screenSize.getHeight()*30/100), (int) (screenSize.getWidth()*14.4/100), (int) (screenSize.getHeight()*4/100));
		contentPane.add(mainPage);
		
		JButton profile = new JButton("profile");
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
			}
		});
		profile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		profile.setBackground(new Color(51, 153, 255));
		profile.setForeground(new Color(255, 204, 0));
		profile.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		profile.setBounds(0, (int) (screenSize.getHeight()*36/100), (int) (screenSize.getWidth()*14.4/100), (int) (screenSize.getHeight()*4/100));
		contentPane.add(profile);
		
		JButton chat = new JButton("chat room");
		chat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				if(MainPage.online) {
					ChatRoomPage crp = new ChatRoomPage();
					crp.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "your not online");
				}
			}
		});
		chat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chat.setBackground(new Color(50, 50, 50));
		chat.setForeground(new Color(255, 204, 0));
		chat.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		chat.setBounds(0, (int) (screenSize.getHeight()*42/100), (int) (screenSize.getWidth()*14.4/100), (int) (screenSize.getHeight()*4/100));
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
		logout.setBounds(0, (int) (screenSize.getHeight()*48/100), (int) (screenSize.getWidth()*14.4/100), (int) (screenSize.getHeight()*4/100));
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
		exit.setBounds(0, (int) (screenSize.getHeight()*54/100), (int) (screenSize.getWidth()*14.4/100), (int) (screenSize.getHeight()*4/100));
		contentPane.add(exit);
		
		JLabel logoPic = new JLabel();
		logoPic.setBounds(1150, 164, 200, 200);
		contentPane.add(logoPic);
		ImageIcon picture = new ImageIcon(new ImageIcon(pq.players().get(MainPage.currentPlayerId - 1).getPic()).getImage()
				.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
		logoPic.setIcon(picture);
		
		JLabel logoUsername = new JLabel("nickname : ");
		logoUsername.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		logoUsername.setForeground(new Color(255, 204, 0));
		logoUsername.setBounds(500, 220, 126, 28);
		contentPane.add(logoUsername);
		
		JLabel showUsername = new JLabel("");
		showUsername.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		showUsername.setForeground(new Color(255, 204, 0));
		showUsername.setBounds(600, 220, 156, 28);
		contentPane.add(showUsername);
		showUsername.setText(pq.players().get(MainPage.currentPlayerId - 1).getUserName());
		
		JLabel logoEmail = new JLabel("email : ");
		logoEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		logoEmail.setForeground(new Color(255, 204, 0));
		logoEmail.setBounds(500, 320, 126, 28);
		contentPane.add(logoEmail);
		
		JLabel showEmail = new JLabel("");
		showEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		showEmail.setForeground(new Color(255, 204, 0));
		showEmail.setBounds(600, 320, 256, 28);
		contentPane.add(showEmail);
		showEmail.setText(pq.players().get(MainPage.currentPlayerId - 1).getEmail());

		JLabel logoScore = new JLabel("high score : ");
		logoScore.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		logoScore.setForeground(new Color(255, 204, 0));
		logoScore.setBounds(500, 420, 126, 28);
		contentPane.add(logoScore);
		
		JLabel showScore = new JLabel("");
		showScore.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		showScore.setForeground(new Color(255, 204, 0));
		showScore.setBounds(600, 420, 256, 28);
		contentPane.add(showScore);
		showScore.setText(String.valueOf(pq.players().get(MainPage.currentPlayerId - 1).getHighScore()));
		
		JButton edit = new JButton("edit profile");
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				EditProfilePage epp = new EditProfilePage();
				epp.setVisible(true);
				dispose();
			}
		});
		edit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		edit.setBackground(new Color(50, 50, 50));
		edit.setForeground(new Color(255, 204, 0));
		edit.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		edit.setBounds((int) (screenSize.getWidth()*82.25/100), (int) (screenSize.getHeight()*63.77/100), (int) (screenSize.getWidth()*10.13888888888889/100), (int) (screenSize.getHeight()*4.6/100));
		contentPane.add(edit);
		
		JLabel backGround = new JLabel("");
		backGround.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
		contentPane.add(backGround);
		backGround.setIcon(iconLogo);
	}
}
