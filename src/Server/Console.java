package Server;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextField;

public class Console {

	private JFrame frame;
	private JTextPane console;
	private JTextField input;
	public StyledDocument document;
	public ArrayList<String> recentlyUsed = new ArrayList<String>();
	public int recentUsedId = 0;
	public int recentUsedMax = 10;
	public boolean loop = false;
	public int loopTimes = 1;
	public int loopTimesTemp = 1;
	public boolean trace = false;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Console window = new Console();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void doCommand(String command) {
		final String[] commands = command.split(" ");
		for (int i = 0; i < loopTimes; i++) {
			try {
				if (commands[0].equalsIgnoreCase("clear")) {
					clear();
				} else if (commands[0].equalsIgnoreCase("loop")) {
					loopTimesTemp = Integer.parseInt(commands[1]);
					loop = true;
					println("The Next Statement Will Loop For" + loopTimesTemp + " Times!", trace,
							new Color(155, 155, 255));
				} else if (commands[0].equalsIgnoreCase("start")) {
					if(commands[1].equalsIgnoreCase("server")) {
						new Server();
						println("server runing", trace, new Color(255, 155, 155));
					}
				} else if (commands[0].equalsIgnoreCase("trace")) {
					if (commands[1].equalsIgnoreCase("true")) {
						trace = true;
						println("Tracing Enabled!", trace, new Color(255, 155, 155));
					} else if (commands[1].equalsIgnoreCase("false")) {
						trace = false;
						println("Tracing Desabled", trace, new Color(255, 155, 155));
					}
				} else if (commands[0].equalsIgnoreCase("get")) {
					if (commands[1].equalsIgnoreCase("ip")) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								try {
									println("Local IP : " + InetAddress.getLocalHost().getHostAddress(), trace,
											new Color(255, 155, 155));
								} catch (Exception e) {
									println("Error => " + e.getMessage(), trace, new Color(255, 155, 155));
								}
								try {
									String url = "http://vallentinsource.com/globalip.php";
									println("Connecting To Proxy", trace, new Color(155, 255, 155));
									try {
										Thread.sleep(100);
									} catch (Exception e) {
										e.printStackTrace();
									}
									BufferedReader br = new BufferedReader(
											new InputStreamReader(new URL(url).openStream()));
									println("Retrieving Global IP Address", trace, new Color(155, 255, 155));
									String receive = br.readLine();
									println("Global IP : " + receive, trace, new Color(155, 255, 155));
								} catch (Exception e) {
									println("Error => " + e.getMessage(), trace, new Color(255, 155, 155));
								}
								scrollBottom();
							}
						}).start();
					}

				} else {
					println("Command Dosen't Exsit", trace, new Color(236, 236, 236));
				}
			} catch (Exception e) {
				println("Error => " + e.getMessage(), trace, new Color(255, 155, 155));
			}
		}
		if (loop) {
			loopTimes = loopTimesTemp;
			loopTimesTemp = 1;
		} else {
			loopTimes = 1;
			loopTimesTemp = 1;
		}
	}

	public void scrollTop() {
		console.setCaretPosition(0);
	}

	public void scrollBottom() {
		console.setCaretPosition(console.getDocument().getLength());
	}

	public void print(String command, boolean trace) {
		print(command, trace, new Color(236, 236, 236));
	}

	public void print(String command, boolean trace, Color color) {
		Style style = console.addStyle("style", null);
		StyleConstants.setForeground(style, color);
		if (trace) {
			Throwable t = new Throwable();
			StackTraceElement[] elements = t.getStackTrace();
			String caller = elements[0].getClassName();
			command = caller + "=>" + command;
		}
		try {
			document.insertString(document.getLength(), command, style);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void println(String command, boolean trace) {
		println(command, trace, new Color(236, 236, 236));
	}

	public void println(String command, boolean trace, Color color) {
		print(command + "\n", trace, color);
	}

	public void clear() {
		try {
			document.remove(0, document.getLength());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Create the application.
	 */
	public Console() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Bomber Man Server");
		frame.setResizable(false);
		frame.setBounds(200, 200, 680, 480);
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 664, 406);
		scrollPane.setBackground(new Color(50, 50, 50));
		scrollPane.setOpaque(true);
		frame.getContentPane().add(scrollPane);
		
		console = new JTextPane();
		scrollPane.setViewportView(console);
		scrollPane.getViewport().setBackground(new Color(50, 50, 50));
		console.setBackground(new Color(50, 50, 50));
		console.setOpaque(true);
		console.setEditable(false);
		console.setForeground(new Color(236, 236, 236));
		console.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		console.setBorder(null);
		document = console.getStyledDocument();
		
		
		input = new JTextField();
		input.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = input.getText();
				if (text.length() > 1) {
					println(text, false);
					recentlyUsed.add(text);
					recentUsedId = 0;
					doCommand(text);
					scrollBottom();
					input.selectAll();
				}
			}
		});
		input.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					if (recentUsedId < recentUsedMax - 1 && recentUsedId < recentlyUsed.size() - 1) {
						recentUsedId++;
					}
					input.setText(recentlyUsed.get(recentlyUsed.size() - 1 - recentUsedId));
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (recentUsedId > 0) {
						recentUsedId--;
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		input.setOpaque(false);
		input.setBounds(0, 407, 664, 34);
		input.setCaretColor(Color.orange);
		input.setForeground(new Color(236, 236, 236));
		input.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		frame.getContentPane().add(input);
		input.setColumns(10);
	}
}
