package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Code.MusicPlayer;

public class IntroPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3781345512227881327L;
	private JPanel contentPane;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntroPage frame = new IntroPage();
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
	public IntroPage() {
		new MusicPlayer("song/Intro.mp3", false).start();
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				LoginPage lp = new LoginPage();
				lp.setVisible(true);
				lp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		t.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);

		JLabel logo1 = new JLabel();
		logo1.setBounds((screenSize.width/2) - (708/2), (screenSize.height/2) - (588/2) - 100, 708, 588);
		contentPane.add(logo1);

		ImageIcon iconLogo1 = new ImageIcon(
				new ImageIcon("img/LoginBomberMan.png").getImage().getScaledInstance(708, 588, Image.SCALE_SMOOTH));
		logo1.setIcon(iconLogo1);

		JLabel logo2 = new JLabel();
		logo2.setBounds((screenSize.width/2) - (768/2), (screenSize.height/2) + (588/2) - 50, 768, 128);
		contentPane.add(logo2);

		ImageIcon iconLogo2 = new ImageIcon(
				new ImageIcon("img/Bomberman_Title.png").getImage().getScaledInstance(768, 128, Image.SCALE_SMOOTH));
		logo2.setIcon(iconLogo2);
	}
}
