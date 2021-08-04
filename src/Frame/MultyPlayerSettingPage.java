package Frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Client.Client;
import Code.MusicPlayer;
import Player.Player;
import Player.PlayerQuery;

public class MultyPlayerSettingPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -219982044710523709L;
	private JPanel contentPane;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static MusicPlayer mp;
	private JLabel username;
	private JButton hosts[][];
	private static Socket socket = MainPage.socket;
	PlayerQuery pq = new PlayerQuery();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultyPlayerSettingPage frame = new MultyPlayerSettingPage();
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
	public MultyPlayerSettingPage() {
		Client client = new Client(socket);
		client.start();
		mp = new MusicPlayer("song/SettingPages.mp3", true);
		mp.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon iconLogo = new ImageIcon(new ImageIcon("img/SettingPage2.png").getImage()
				.getScaledInstance((int) screenSize.getWidth(), (int) screenSize.getHeight(), Image.SCALE_SMOOTH));
		contentPane.setLayout(null);
		setUndecorated(true);

		JLabel back = new JLabel();
		back.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MainPage mpa = new MainPage();
				mpa.setVisible(true);
				dispose();
				mp.stop();
			}
		});
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setBounds(8, 14, 205, 80);
		back.setBorder(null);
		contentPane.add(back);

		JLabel profilePic = new JLabel();
		profilePic.setBounds(20, 100, 97, 97);
		contentPane.add(profilePic);
		ImageIcon pic = new ImageIcon(new ImageIcon(pq.players().get(MainPage.currentPlayerId - 1).getPic()).getImage()
				.getScaledInstance(97, 97, Image.SCALE_SMOOTH));
		profilePic.setIcon(pic);

		username = new JLabel("");
		username.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		username.setForeground(new Color(255, 204, 0));
		username.setBounds(20, 200, 126, 28);
		contentPane.add(username);
		username.setText(pq.players().get(MainPage.currentPlayerId - 1).getUserName());

		JLabel mode = new JLabel("host a game");
		mode.setHorizontalAlignment(SwingConstants.CENTER);
		mode.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		mode.setForeground(new Color(255, 204, 0));
		mode.setBounds((int) (screenSize.getWidth()*44.3/100), 115, 385, 28);
		contentPane.add(mode);

		JLabel hmp = new JLabel("how many players ?");
		hmp.setHorizontalAlignment(SwingConstants.CENTER);
		hmp.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		hmp.setForeground(new Color(255, 204, 0));
		hmp.setBounds((int) (screenSize.getWidth()*41/100), 370, 385, 28);
		contentPane.add(hmp);

		JLabel warning = new JLabel("(maximum four players)");
		warning.setHorizontalAlignment(SwingConstants.CENTER);
		warning.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		warning.setForeground(new Color(255, 204, 0));
		warning.setBounds((int) (screenSize.getWidth()*41/100), 390, 385, 28);
		contentPane.add(warning);

		JTextField hmpf = new JTextField("");
		hmpf.setBackground(new Color(50, 50, 50));
		hmpf.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		hmpf.setForeground(new Color(255, 204, 0));
		hmpf.setBounds((int) (screenSize.getWidth()*63/100), (int) (screenSize.getHeight()*42/100), 30, 28);
		hmpf.setCaretColor(Color.orange);
		hmpf.setCaretPosition(0);
		contentPane.add(hmpf);
		hmpf.setColumns(10);

		JButton start = new JButton("create game");
		start.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				mp.stop();
				client.sendToServer("im-host-newgame-" + hmpf.getText() + "-"
						+ pq.players().get(MainPage.currentPlayerId - 1).getUserName());
				MultyPlayPage.isHost = true;
				MultyPlayPage.witchNumber = 1;
				MultyPlayPage.numOfPlayers = Integer.parseInt(hmpf.getText());
				MultyPlayPage mpp = new MultyPlayPage();
				//mpp.msg.setText("wait for other palyers to connect");
				mpp.setVisible(true);
				dispose();
			}
		});
		start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		start.setForeground(new Color(50, 205, 50));
		start.setBounds((int) (screenSize.getWidth()*53/100), (int) (screenSize.getHeight()*73.5/100), 127, 35);
		start.setBackground(new Color(50, 50, 50));
		start.setBorder(null);
		contentPane.add(start);

		JLabel mode1 = new JLabel("*join a game*");
		mode1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				client.sendToServer("sendme-hosts");
				String line = client.giveFromServer();
				System.out.println(line);
				String[] host = line.split("-");
				for (int i = 0; i < host.length; i++) {
					hosts[i][0].setText(String.valueOf(i + 1) + " - " + host[i]);
				}
				for (int i = host.length; i < 6; i++) {
					hosts[i][0].setText("No Host");
				}

			}
		});
		mode1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mode1.setHorizontalAlignment(SwingConstants.CENTER);
		mode1.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		mode1.setForeground(new Color(255, 204, 0));
		mode1.setBounds(0, (int) (screenSize.getHeight()*30/100), (int) (screenSize.getWidth()*14.25/100), 30);
		contentPane.add(mode1);

		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(50, 50, 50));
		panel1.setBounds(0, (int) (screenSize.getHeight()*35/100), (int) (screenSize.getWidth()*14.25/100), (int) screenSize.getHeight() - (int) (screenSize.getHeight()*35/100));
		contentPane.add(panel1);
		panel1.setLayout(new GridLayout(6, 1, 0, 0));

		hosts = new JButton[6][1];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 1; j++) {
				hosts[i][j] = new JButton();
				hosts[i][j].addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent event) {
						new MusicPlayer("song/click.mp3", false).start();
						String[] name = ((JButton) event.getSource()).getText().split(" - ");
						if (((JButton) event.getSource()).getText().equals("No host")) {
							JOptionPane.showMessageDialog(null, "Threr Is No host Here");
						} else {
							for (Player p : pq.players()) {
								if (p.getUserName().equals(name[1])) {
									client.sendToServer(
											"im-join-" + pq.players().get(MainPage.currentPlayerId - 1).getUserName()
													+ "-" + String.valueOf(Integer.parseInt(name[0]) - 1));
									String[] message = client.giveFromServer().split("-");
									if (message[0].equals("you cant join")) {
										JOptionPane.showMessageDialog(null, "game is full");
									} else if (message[0].equals("you joined")) {
										MultyPlayPage mpp = new MultyPlayPage();
										MultyPlayPage.witchNumber = Integer.parseInt(message[1]);
										String[] names = message[2].split("\\|");
										for (int k = 0; k < names.length; k++) {
											if (k == 0) {
												mpp.b1name.setText(names[k]);
												mpp.b1point.setText("0");
											} else if (k == 1) {
												mpp.b2name.setText(names[k]);
												mpp.b2point.setText("0");
											} else if (k == 2) {
												mpp.b3name.setText(names[k]);
												mpp.b3point.setText("0");
											}
										}
										if (Integer.parseInt(message[1]) == 2) {
											mpp.b2name.setText(
													pq.players().get(MainPage.currentPlayerId - 1).getUserName());
											mpp.b2point.setText("0");
										} else if (Integer.parseInt(message[1]) == 3) {
											mpp.b3name.setText(
													pq.players().get(MainPage.currentPlayerId - 1).getUserName());
											mpp.b3point.setText("0");
										} else if (Integer.parseInt(message[1]) == 4) {
											mpp.b4name.setText(
													pq.players().get(MainPage.currentPlayerId - 1).getUserName());
											mpp.b4point.setText("0");
										}
										//mpp.msg.setText("wait for other palyers to connect");
										mpp.setVisible(true);
										dispose();
										mp.stop();
									}
								}
							}
						}
					}
				});
				hosts[i][j].setText("No host");
				hosts[i][j].setBackground(new Color(50, 50, 50));
				hosts[i][j].setForeground(new Color(255, 204, 0));
				hosts[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				panel1.add(hosts[i][j]);
			}
			JLabel backGround = new JLabel("");
			backGround.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
			contentPane.add(backGround);
			backGround.setIcon(iconLogo);
		}
	}
}
