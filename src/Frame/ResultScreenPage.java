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

public class ResultScreenPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7066356653452520476L;
	private JPanel contentPane;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static MusicPlayer mp;
	private JLabel username;
	public static JLabel mod;
	public static JLabel showTime;
	public static JLabel time;
	public static JLabel showPoint;
	public static JLabel win;
	PlayerQuery pq = new PlayerQuery();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultScreenPage frame = new ResultScreenPage();
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
	public ResultScreenPage() {
		mp = new MusicPlayer("song/ResultScreen.mp3", true);
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
		back.setBounds(8, 14, 205, 80);
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
		
		JLabel mode = new JLabel("Game Result");
		mode.setHorizontalAlignment(SwingConstants.CENTER);
		mode.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		mode.setForeground(new Color(255, 204, 0));
		mode.setBounds((int) (screenSize.getWidth()*44/100), 115, 385, 28);
		contentPane.add(mode);
		
		JLabel logo = new JLabel();
		logo.setBounds((int) (screenSize.getWidth()*52/100), 190, 150, 150);
		contentPane.add(logo);
		ImageIcon logoi = new ImageIcon(new ImageIcon("img/LoginBomberMan.png").getImage()
				.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
		logo.setIcon(logoi);
		
		mod = new JLabel("eazy mode");
		mod.setBounds((int) (screenSize.getWidth()*52/100), 350, 150, 50);
		mod.setHorizontalAlignment(SwingConstants.CENTER);
		mod.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		mod.setForeground(new Color(255, 204, 0));
		contentPane.add(mod);
		
		time = new JLabel("🕒");
		time.setForeground(Color.WHITE);
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setFont(time.getFont().deriveFont(22f));
		time.setBounds((int) (screenSize.getWidth()*48.7/100), 450, 150, 50);
		contentPane.add(time);

		showTime = new JLabel("00 : 00");
		showTime.setForeground(Color.WHITE);
		showTime.setHorizontalAlignment(SwingConstants.CENTER);
		showTime.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		showTime.setBounds((int) (screenSize.getWidth()*55.3/100), 450, 150, 50);
		contentPane.add(showTime);

		JLabel pointlbl = new JLabel("💎");
		pointlbl.setForeground(Color.blue);
		pointlbl.setHorizontalAlignment(SwingConstants.CENTER);
		pointlbl.setFont(pointlbl.getFont().deriveFont(22f));
		pointlbl.setBounds((int) (screenSize.getWidth()*48.7/100), 500, 150, 50);
		contentPane.add(pointlbl);

		showPoint = new JLabel("0");
		showPoint.setForeground(Color.WHITE);
		showPoint.setHorizontalAlignment(SwingConstants.CENTER);
		showPoint.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		showPoint.setBounds((int) (screenSize.getWidth()*55.3/100), 500, 150, 50);
		contentPane.add(showPoint);
		
		win = new JLabel("you win :D");
		win.setBounds((int) (screenSize.getWidth()*45.05/100), 550, 350, 70);
		win.setHorizontalAlignment(SwingConstants.CENTER);
		win.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		win.setForeground(new Color(255, 204, 0));
		contentPane.add(win);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(50, 50, 50));
		panel.setBounds((int) (screenSize.getWidth()*39.1/100), (int) (screenSize.getHeight()*17.5/100), (int) (screenSize.getWidth()*36.2/100), (int) (screenSize.getHeight()*71/100));
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel backGround = new JLabel("");
		backGround.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
		contentPane.add(backGround);
		backGround.setIcon(iconLogo);
	}
}
