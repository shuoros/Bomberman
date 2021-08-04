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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Code.MusicPlayer;
import Code.PicBrowse;
import DataBase.Connect;
import Player.Player;
import Player.PlayerQuery;

public class EditProfilePage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7936520425656527173L;
	private JPanel contentPane;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JLabel username;
	private JLabel lbl;
	private JLabel level;
	private JLabel lbl1;
	PlayerQuery pq = new PlayerQuery();
	String imagePth = null;
	boolean pass;
	boolean passError = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProfilePage frame = new EditProfilePage();
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
	public EditProfilePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setUndecorated(true);
		ImageIcon iconLogo = new ImageIcon(new ImageIcon("img/PEPCRPage.png").getImage()
				.getScaledInstance((int) screenSize.getWidth(), (int) screenSize.getHeight(), Image.SCALE_SMOOTH));
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
				if (MainPage.online) {
					onlineOffline.setForeground(new Color(255, 0, 0));
					onlineOffline.setText("Offline");
					MainPage.online = false;
				} else {
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

		lbl1 = new JLabel("Edit Profile");
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
				ProfilePage pp = new ProfilePage();
				pp.setVisible(true);
				dispose();
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

		JLabel profPic = new JLabel();
		profPic.setBounds(1150, 164, 200, 200);
		contentPane.add(profPic);
		ImageIcon picture = new ImageIcon(new ImageIcon(pq.players().get(MainPage.currentPlayerId - 1).getPic())
				.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
		profPic.setIcon(picture);

		JButton brwose = new JButton("brwose");
		brwose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				PicBrowse picInserter = new PicBrowse();
				imagePth = picInserter.browseImage(profPic);
			}
		});
		brwose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		brwose.setBackground(new Color(50, 50, 50));
		brwose.setForeground(new Color(255, 204, 0));
		brwose.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		brwose.setBounds(1204, 385, 100, 20);
		contentPane.add(brwose);

		JLabel logoUsername = new JLabel("nickname : ");
		logoUsername.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		logoUsername.setForeground(new Color(255, 204, 0));
		logoUsername.setBounds(500, 220, 126, 28);
		contentPane.add(logoUsername);

		JTextField showUsername = new JTextField("");
		showUsername.setBackground(new Color(50, 50, 50));
		showUsername.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		showUsername.setForeground(new Color(255, 204, 0));
		showUsername.setBounds(600, 220, 156, 28);
		contentPane.add(showUsername);
		showUsername.setColumns(10);
		showUsername.setText(pq.players().get(MainPage.currentPlayerId - 1).getUserName());

		JLabel logoEmail = new JLabel("email : ");
		logoEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		logoEmail.setForeground(new Color(255, 204, 0));
		logoEmail.setBounds(500, 320, 126, 28);
		contentPane.add(logoEmail);

		JTextField showEmail = new JTextField("");
		showEmail.setBackground(new Color(50, 50, 50));
		showEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		showEmail.setForeground(new Color(255, 204, 0));
		showEmail.setBounds(600, 320, 256, 28);
		contentPane.add(showEmail);
		showEmail.setColumns(10);
		showEmail.setText(pq.players().get(MainPage.currentPlayerId - 1).getEmail());

		JLabel oldpas = new JLabel("old password : ");
		oldpas.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		oldpas.setForeground(new Color(255, 204, 0));
		oldpas.setBounds(500, 420, 126, 28);
		contentPane.add(oldpas);

		JLabel warning = new JLabel("(if you do not want to change your password, leave these two fields blank)");
		warning.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		warning.setForeground(new Color(255, 204, 0));
		warning.setBounds(770, 420, 500, 28);
		contentPane.add(warning);

		JTextField showOldPass = new JTextField("");
		showOldPass.setBackground(new Color(50, 50, 50));
		showOldPass.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		showOldPass.setForeground(new Color(255, 204, 0));
		showOldPass.setBounds(600, 420, 156, 28);
		contentPane.add(showOldPass);
		showOldPass.setColumns(10);

		JLabel newpas = new JLabel("new password : ");
		newpas.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		newpas.setForeground(new Color(255, 204, 0));
		newpas.setBounds(500, 520, 126, 28);
		contentPane.add(newpas);

		JTextField showNewPass = new JTextField("");
		showNewPass.setBackground(new Color(50, 50, 50));
		showNewPass.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		showNewPass.setForeground(new Color(255, 204, 0));
		showNewPass.setBounds(600, 520, 156, 28);
		contentPane.add(showNewPass);
		showNewPass.setColumns(10);

		JButton edit = new JButton("save");
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				if (showNewPass.getText().isEmpty()) {
					pass = false;
				} else if (!showNewPass.getText().isEmpty()) {
					Connection con = Connect.getConnection();
					PreparedStatement ps;
					ResultSet rs;
					String origPass = null;
					try {
						ps = con.prepareStatement("SELECT * FROM `players` WHERE `id`= ?");
						ps.setString(1, String.valueOf(MainPage.currentPlayerId));
						rs = ps.executeQuery();
						if (rs.next()) {
							origPass = rs.getString("password");
							System.out.println(origPass);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if (origPass.equals(showOldPass.getText())) {
						pass = true;
					} else {
						JOptionPane.showMessageDialog(null, "Old Password Doesn't Right !");
					}
				}
				if (!passError) {
					/**
					 * If The User Want To Update The Data And Image
					 */
					if (imagePth != null) {
						byte[] img = null;
						try {
							Path pth = Paths.get(imagePth);
							img = Files.readAllBytes(pth);
							if (pass) {
								Player player = new Player(MainPage.currentPlayerId, showUsername.getText(),
										showNewPass.getText(), showEmail.getText(), img, 0.0, 0, 0.0);
								pq.updatePlayer(player, true, true, true);
							} else {
								Player player = new Player(MainPage.currentPlayerId, showUsername.getText(), null,
										showEmail.getText(), img, 0.0, 0, 0.0);
								pq.updatePlayer(player, true, false, true);
							}
						} catch (FileNotFoundException e) {
							Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, e);
							e.printStackTrace();
						} catch (IOException e) {
							Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, e);
							e.printStackTrace();
						}
					} else {
						if (pass) {
							Player player = new Player(MainPage.currentPlayerId, showUsername.getText(),
									showNewPass.getText(), showEmail.getText(), null, 0.0, 0, 0.0);
							pq.updatePlayer(player, false, true, true);
						} else {
							Player player = new Player(MainPage.currentPlayerId, showUsername.getText(), null,
									showEmail.getText(), null, 0.0, 0, 0.0);
							pq.updatePlayer(player, false, false, true);
						}
					}
				}
				ProfilePage pp = new ProfilePage();
				pp.setVisible(true);
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
