package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import Code.MusicPlayer;

public class GameLoadPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3321372981558660832L;
	private JPanel contentPane;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public JLabel logo2;
	public JLabel logo1;
	public static MusicPlayer mp;
	static int counter = 0;
	public static Boolean single;
	public static Boolean multy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameLoadPage frame = new GameLoadPage();
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
	public GameLoadPage() {
		mp = new MusicPlayer("song/Ready...GO!.mp3", true);
		mp.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		
		logo1 = new JLabel();
		logo1.setForeground(Color.WHITE);
		logo1.setFont(new Font("Trebuchet MS", Font.BOLD, 90));
		logo1.setHorizontalAlignment(SwingConstants.CENTER);
		logo1.setBounds((screenSize.width/2) - (708/2), (screenSize.height/2) - (235/2) - 100, 708, 235);
		contentPane.add(logo1);

		logo2 = new JLabel();
		logo2.setForeground(Color.WHITE);
		logo2.setFont(new Font("Trebuchet MS", Font.BOLD, 50));
		logo2.setHorizontalAlignment(SwingConstants.CENTER);
		logo2.setBounds((screenSize.width/2) - (768/2), (screenSize.height/2) - (128/2) + 100, 768, 128);
		contentPane.add(logo2);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBorderPainted(false);
		progressBar.setForeground(Color.WHITE);
		progressBar.setBackground(Color.BLACK);
		progressBar.setBounds(0, (int) screenSize.getHeight() - 30, (int) screenSize.getWidth(), 30);
		contentPane.add(progressBar);
		Timer timer = new Timer(30, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter++;
				progressBar.setValue(counter);
				if(counter == 101) {
					if(single) {
						SinglePlayPage.type = logo2.getText();
						SinglePlayPage spp = new SinglePlayPage();
						spp.setVisible(true);
					}else if(multy) {
						
					}
					dispose();
				}
			}
		});
		timer.start();
	}
}
