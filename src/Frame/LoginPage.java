package Frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Code.MusicPlayer;
import DataBase.Connect;
import Player.Player;
import Player.PlayerQuery;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JTextField nickname;
	private JTextField email;
	private JTextField password;
	private JButton login;
	private JTextField nicknameOrEmail;
	private JTextField logPassword;
	private JLabel lblNewLabel;
	private JLabel lblSoroushShemshadi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean isUsernameExist(String un) {
		boolean uExist = false;
		Connection con = Connect.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = con.prepareStatement("SELECT * FROM `players` WHERE `username` = ?");
			ps.setString(1, nickname.getText());
			rs = ps.executeQuery();
			if (rs.next()) {
				uExist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uExist;
	}

	public boolean isEmailExist(String un) {
		boolean uExist = false;
		Connection con = Connect.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = con.prepareStatement("SELECT * FROM `players` WHERE `email` = ?");
			ps.setString(1, nickname.getText());
			rs = ps.executeQuery();
			if (rs.next()) {
				uExist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uExist;
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Game Of Bombs");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		ImageIcon iconLogo = new ImageIcon(new ImageIcon("img/LoginPage.png").getImage()
				.getScaledInstance((int) screenSize.getWidth(), (int) screenSize.getHeight(), Image.SCALE_SMOOTH));

		JButton register = new JButton("Register");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				if (nickname.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Enter Your nickname");
				} else if (email.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Enter Your email");
				} else if (password.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Enter Your password");
				} else if (isUsernameExist(nickname.getText())) {
					JOptionPane.showMessageDialog(null, "This nickname Already Tacken Before");
				} else if (isEmailExist(email.getText())) {
					JOptionPane.showMessageDialog(null, "This email Already Tacken Before");
				} else {
					Connection con = Connect.getConnection();
					PreparedStatement ps;
					ResultSet rs;
					LoginPage lip = new LoginPage();
					byte[] img = null;
					try {
						Path pth = Paths.get("img/defaultuser.png");
						img = Files.readAllBytes(pth);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					Player player = new Player(null, nickname.getText(), password.getText(), email.getText(), img, 0.0,
							1, 0.0);
					PlayerQuery pq = new PlayerQuery();
					pq.addPlayer(player);
					nickname.setText("nickname");
					email.setText("email");
					password.setText("password");
				}
			}
		});
		register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		register.setForeground(new Color(255, 204, 0));
		register.setBounds((int) (screenSize.getWidth()*31.7/100), (int) (screenSize.getHeight()*66.4/100), 135, 33);
		register.setBackground(new Color(50, 50, 50));
		register.setBorder(null);
		contentPane.add(register);

		nickname = new JTextField();
		nickname.setForeground(new Color(210, 215, 211));
		nickname.setOpaque(false);
		nickname.setBorder(null);
		nickname.setBounds((int) (screenSize.getWidth()*28.7/100), (int) (screenSize.getHeight()*46.4/100), 240, 35);
		nickname.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		contentPane.add(nickname);
		nickname.setColumns(10);
		nickname.setText("nickname");
		nickname.setCaretColor(Color.orange);
		nickname.setCaretPosition(0);
		nickname.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				nickname.setText("");
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if (nickname.getText().isEmpty()) {
					nickname.setText("nickname");
					nickname.setCaretPosition(0);
				}
			}
		});

		email = new JTextField();
		email.setText("email");
		email.setOpaque(false);
		email.setForeground(new Color(210, 215, 211));
		email.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		email.setColumns(10);
		email.setBorder(null);
		email.setBounds((int) (screenSize.getWidth()*28.7/100), (int) (screenSize.getHeight()*51.4/100), 240, 35);
		contentPane.add(email);
		email.setCaretColor(Color.orange);
		email.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				email.setText("");
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if (email.getText().isEmpty()) {
					email.setText("email");
					email.setCaretPosition(0);
				}
			}
		});

		password = new JTextField();
		password.setText("password");
		password.setOpaque(false);
		password.setForeground(new Color(210, 215, 211));
		password.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		password.setColumns(10);
		password.setBorder(null);
		password.setBounds((int) (screenSize.getWidth()*28.7/100), (int) (screenSize.getHeight()*56.4/100), 240, 35);
		contentPane.add(password);
		password.setCaretColor(Color.orange);
		password.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				password.setText("");
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if (password.getText().isEmpty()) {
					password.setText("password");
					password.setCaretPosition(0);
				}
			}
		});

		JCheckBox chckbxNewCheckBox = new JCheckBox("Remember me");
		chckbxNewCheckBox.setForeground(new Color(210, 215, 211));
		chckbxNewCheckBox.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		chckbxNewCheckBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxNewCheckBox.setBounds((int) (screenSize.getWidth()*55/100), (int) (screenSize.getHeight()*58.8/100), 135, 21);
		chckbxNewCheckBox.setOpaque(false);
		contentPane.add(chckbxNewCheckBox);
		
		login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				if (nicknameOrEmail.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Enter Your nickname/email");
				} else if (logPassword.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Enter Your password");
				} else {
					if(chckbxNewCheckBox.isSelected()) {
						PrintWriter writer;
						try {
							writer = new PrintWriter("doc/login.txt", "UTF-8");
							writer.print(nicknameOrEmail.getText() + "-" + logPassword.getText());
							writer.close();
						} catch (FileNotFoundException | UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}else {
						PrintWriter writer;
						try {
							writer = new PrintWriter("doc/login.txt", "UTF-8");
							writer.print("");
							writer.close();
						} catch (FileNotFoundException | UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
					Connection con = Connect.getConnection();
					PreparedStatement ps;
					ResultSet rs;
					try {
						ps = con.prepareStatement("SELECT * FROM `players` WHERE `username` = ? AND `password` = ?");
						ps.setString(1, nicknameOrEmail.getText());
						ps.setString(2, logPassword.getText());
						rs = ps.executeQuery();
						if (rs.next()) {
							JOptionPane.showMessageDialog(null, "You Logged In");
							MainPage.currentPlayerId = rs.getInt("id");
							MainPage.musicIsPlayed = false;
							MainPage mp = new MainPage();
							mp.setVisible(true);
							mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							mp.setLocationRelativeTo(null);
							dispose();
						} else {
							ps = con.prepareStatement("SELECT * FROM `players` WHERE `email` = ? AND `password` = ?");
							ps.setString(1, nicknameOrEmail.getText());
							ps.setString(2, logPassword.getText());
							rs = ps.executeQuery();
							if (rs.next()) {
								JOptionPane.showMessageDialog(null, "You Logged In");
								MainPage.currentPlayerId = rs.getInt("id");
								MainPage mp = new MainPage();
								mp.setVisible(true);
								mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								mp.setLocationRelativeTo(null);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "Login Error");
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		login.setForeground(new Color(255, 204, 0));
		login.setBorder(null);
		login.setBackground(new Color(50, 50, 50));
		login.setBounds((int) (screenSize.getWidth()*57.4/100), (int) (screenSize.getHeight()*62.25/100), 133, 33);
		contentPane.add(login);

		nicknameOrEmail = new JTextField();
		nicknameOrEmail.setText("nickname or email");
		nicknameOrEmail.setOpaque(false);
		nicknameOrEmail.setForeground(new Color(210, 215, 211));
		nicknameOrEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		nicknameOrEmail.setColumns(10);
		nicknameOrEmail.setBorder(null);
		nicknameOrEmail.setBounds((int) (screenSize.getWidth()*55/100), (int) (screenSize.getHeight()*48.55/100), 240, 35);
		contentPane.add(nicknameOrEmail);
		nicknameOrEmail.setCaretColor(Color.orange);
		nicknameOrEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				nicknameOrEmail.setText("");
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if (nicknameOrEmail.getText().isEmpty()) {
					nicknameOrEmail.setText("nickname or email");
					nicknameOrEmail.setCaretPosition(0);
				}
			}
		});

		logPassword = new JTextField();
		logPassword.setText("password");
		logPassword.setOpaque(false);
		logPassword.setForeground(new Color(210, 215, 211));
		logPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		logPassword.setColumns(10);
		logPassword.setBorder(null);
		logPassword.setBounds((int) (screenSize.getWidth()*55/100), (int) (screenSize.getHeight()*53.8/100), 240, 35);
		contentPane.add(logPassword);
		logPassword.setCaretColor(Color.orange);
		logPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				logPassword.setText("");
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if (logPassword.getText().isEmpty()) {
					logPassword.setText("password");
					logPassword.setCaretPosition(0);
				}
			}
		});

		String line = null;
		FileReader fileReader;
		try {
			fileReader = new FileReader("doc/login.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			line = bufferedReader.readLine();
			bufferedReader.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(line != null) {
			chckbxNewCheckBox.setSelected(true);
			String[] log = line.split("-");
			nicknameOrEmail.setText(log[0]);
			logPassword.setText(log[1]);
		}
		
		lblNewLabel = new JLabel("designed by");
		lblNewLabel.setForeground(new Color(210, 215, 211));
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(654, 735, 77, 33);
		contentPane.add(lblNewLabel);

		lblSoroushShemshadi = new JLabel("Soroush Shemshadi");
		lblSoroushShemshadi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				try {
					Desktop.getDesktop().browse(new URL("http://www.soroush.pro").toURI());
				} catch (Exception e) {
				}
			}
		});
		lblSoroushShemshadi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSoroushShemshadi.setForeground(new Color(0, 153, 255));
		lblSoroushShemshadi.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblSoroushShemshadi.setBounds(722, 735, 115, 33);
		contentPane.add(lblSoroushShemshadi);

		JLabel exit = new JLabel("Exit");
		exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				System.exit(0);
			}
		});
		exit.setForeground(new Color(220, 20, 60));
		exit.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		exit.setHorizontalAlignment(SwingConstants.CENTER);
		exit.setBounds(10, 10, 67, 33);
		contentPane.add(exit);

		JLabel backGround = new JLabel("");
		backGround.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
		contentPane.add(backGround);
		backGround.setIcon(iconLogo);
	}
}
