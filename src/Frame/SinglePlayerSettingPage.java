package Frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Code.MusicPlayer;
import Player.PlayerQuery;

public class SinglePlayerSettingPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4728334679860280082L;
	private JPanel contentPane;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static MusicPlayer mp;
	private JLabel username;
	PlayerQuery pq = new PlayerQuery();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SinglePlayerSettingPage frame = new SinglePlayerSettingPage();
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
	public SinglePlayerSettingPage() {
		mp = new MusicPlayer("song/SettingPages.mp3", true);
		mp.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon iconLogo = new ImageIcon(
				new ImageIcon("img/SettingPage.png").getImage().getScaledInstance((int)screenSize.getWidth(), (int)screenSize.getHeight() , Image.SCALE_SMOOTH));
		contentPane.setLayout(null);
		setUndecorated(true);
		
		JLabel back = new JLabel();
		back.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				MainPage mpa = new MainPage();
				mpa.setVisible(true);
				dispose();
				mp.stop();
			}
		});
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setBounds((int) (screenSize.getWidth()*0.5/100), (int) (screenSize.getHeight()*1/100), 205, 80);
		back.setBorder(null);
		contentPane.add(back);
		
		JLabel profilePic = new JLabel();
		profilePic.setBounds(20, 110, 97, 97);
		contentPane.add(profilePic);
		ImageIcon pic = new ImageIcon(new ImageIcon(pq.players().get(MainPage.currentPlayerId - 1).getPic()).getImage()
				.getScaledInstance(97, 97, Image.SCALE_SMOOTH));
		profilePic.setIcon(pic);
		
		username = new JLabel("");
		username.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		username.setForeground(new Color(255, 204, 0));
		username.setBounds(20, 205, 126, 28);
		contentPane.add(username);
		username.setText(pq.players().get(MainPage.currentPlayerId - 1).getUserName());
		
		JLabel mode = new JLabel("select your game mode");
		mode.setHorizontalAlignment(SwingConstants.CENTER);
		mode.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		mode.setForeground(new Color(255, 204, 0));
		mode.setBounds((int) (screenSize.getWidth()*44.5/100), 115, 385, 28);
		contentPane.add(mode);
		
		JLabel eazy = new JLabel();
		eazy.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				mp.stop();
				GameLoadPage.single = true;
				GameLoadPage.counter = 0;
				GameLoadPage glp = new GameLoadPage();
				glp.logo2.setText("Eazy");
				glp.logo1.setText("Single Player");
				glp.setVisible(true);
				dispose();
			}
		});
		eazy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		eazy.setBounds((int) (screenSize.getWidth()*41.5/100), (int) (screenSize.getHeight()*40/100), 135, 110);
		eazy.setBorder(null);
		contentPane.add(eazy);
		
		JLabel eazyMode = new JLabel("eazy");
		eazyMode.setHorizontalAlignment(SwingConstants.CENTER);
		eazyMode.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		eazyMode.setForeground(new Color(255, 204, 0));
		eazyMode.setBounds((int) (screenSize.getWidth()*42/100), (int) (screenSize.getHeight()*55/100), 135, 28);
		contentPane.add(eazyMode);
		
		JLabel normal = new JLabel();
		normal.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				mp.stop();
				GameLoadPage.single = true;
				GameLoadPage glp = new GameLoadPage();
				glp.logo2.setText("Normal");
				glp.logo1.setText("Single Player");
				glp.setVisible(true);
				dispose();
			}
		});
		normal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		normal.setBounds((int) (screenSize.getWidth()*52.5/100), (int) (screenSize.getHeight()*40/100), 135, 110);
		contentPane.add(normal);
		
		JLabel normalMode = new JLabel("normal");
		normalMode.setHorizontalAlignment(SwingConstants.CENTER);
		normalMode.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		normalMode.setForeground(new Color(255, 204, 0));
		normalMode.setBounds((int) (screenSize.getWidth()*53/100), (int) (screenSize.getHeight()*55/100), 135, 28);
		contentPane.add(normalMode);
		
		JLabel hard = new JLabel();
		hard.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MusicPlayer("song/click.mp3", false).start();
				mp.stop();
				GameLoadPage.single = true;
				GameLoadPage glp = new GameLoadPage();
				glp.logo1.setText("Single Player");
				glp.logo2.setText("Hard");
				glp.setVisible(true);
				dispose();
			}
		});
		hard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hard.setBounds((int) (screenSize.getWidth()*63.5/100), (int) (screenSize.getHeight()*40/100), 135, 110);
		hard.setBorder(null);
		contentPane.add(hard);
		
		JLabel hardMode = new JLabel("hard");
		hardMode.setHorizontalAlignment(SwingConstants.CENTER);
		hardMode.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		hardMode.setForeground(new Color(255, 204, 0));
		hardMode.setBounds((int) (screenSize.getWidth()*64/100), (int) (screenSize.getHeight()*55/100), 135, 28);
		contentPane.add(hardMode);
		
		JLabel backGround = new JLabel("");
		backGround.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
		contentPane.add(backGround);
		backGround.setIcon(iconLogo);
	}

}
