package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Game.Field;

public class Server {

	private static final int port = 8585;
	private static ArrayList<String> hosts = new ArrayList<>();
	private static ArrayList<ArrayList<PrintWriter>> games = new ArrayList<>();
	private static ArrayList<ArrayList<String>> players = new ArrayList<>();
	private static ArrayList<Integer> howManyPlayers = new ArrayList<>();
	private static ArrayList<Field> field = new ArrayList<>();
	private static ArrayList<String[][]> loc = new ArrayList<>();

	public static void main(String[] args) {
		runServer();
	}

	private static void runServer() {
		System.out.println("server is running :D");
		ServerSocket listener = null;
		try {
			listener = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			/**
			 * waiting for clients to connect
			 */
			while (true) {
				new Handler(listener.accept()).start();
				System.out.println("a client connected");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				listener.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static class Handler extends Thread {

		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		private boolean isHost;
		private String name;
		private String allHost;

		public Handler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				// set reader and writer
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				while (true) {
					String line = null;
					try {
						line = in.readLine();
					} catch (IOException e) {
						System.out.println(e.toString());
					}
					String[] message = line.split("-");
					/**
					 * darkhast karbar baray host shodan va ezafe shodan name u be list hosts va ijad yek bazi be name u
					 */
					if (message[0].equals("im")) {
						if (message[1].equals("host")) {
							if (message[2].equals("newgame")) {
								isHost = true;
								name = message[4];
								howManyPlayers.add(Integer.parseInt(message[3]));
								hosts.add(name);
								field.add(new Field("online"));
								loc.add(new String[20][20]);
								loc.set(loc.size() - 1, field.get(field.size() - 1).generateOnlineField());
								games.add(new ArrayList<PrintWriter>());
								games.get(games.size() - 1).add(out);
								players.add(new ArrayList<String>());
								players.get(games.size() - 1).add(name);
								System.out.println("game created by " + name + " with " + message[3] + " players");
							}
						} 
						/**
						 * darkhast karbar baraye join shodan ba ezafe shodan name u be liste hosti ke entekhab karde
						 */
						else if (message[1].equals("join")) {
							isHost = false;
							name = message[2];
							if (howManyPlayers.get(Integer.parseInt(message[3])) > games
									.get(Integer.parseInt(message[3])).size()) {
								String hostN = hosts.get(Integer.parseInt(message[3]));
								for (PrintWriter writer : games.get(Integer.parseInt(message[3]))) {
									writer.println(
											"new-" + String.valueOf(games.get(Integer.parseInt(message[3])).size() + 1)
													+ "-" + name);
								}
								String names = null;
								for (int i = 0; i < players.get(Integer.parseInt(message[3])).size(); i++) {
									if (i == 0) {
										names = players.get(Integer.parseInt(message[3])).get(i) + "|";
									} else {
										names += players.get(Integer.parseInt(message[3])).get(i) + "|";
									}
								}
								games.get(Integer.parseInt(message[3])).add(out);
								players.get(Integer.parseInt(message[3])).add(name);
								out.println("you joined-"
										+ String.valueOf(games.get(Integer.parseInt(message[3])).size()) + "-" + names);
								System.out.println(name + " joined " + hostN);
							} else {
								out.println("you cant join");
								System.out.println(name + " cant join");
							}
						}
					}
					/**
					 * darkhast karbar baraye ersal name hastha be u ba entekhab yeke az anha tavasot u
					 */
					else if (message[0].equals("sendme")) {
						if (message[1].equals("hosts")) {
							System.out.println("someone requested for hosts name");
							for (int i = 0; i < hosts.size(); i++) {
								if (i == 0) {
									allHost = hosts.get(i) + "-";
								} else {
									allHost += hosts.get(i) + "-";
								}
							}
							System.out.println(allHost);
							out.println(allHost);
							allHost = null;
						}
					} 
					/**
					 * darkhast shoru bazi tavasot host va ersal field az qabl sakhte shode baray bazikonan on host
					 */
					else if (message[0].equals("startgame")) {
						for (int i = 0; i < hosts.size(); i++) {
							if (hosts.get(i).equals(message[1])) {
								if (howManyPlayers.get(i) == 2) {
									loc.get(i)[1][1] = "bombermanwhitefront";
									loc.get(i)[1][18] = "bombermanblackfront";
								} else if (howManyPlayers.get(i) == 3) {
									loc.get(i)[1][1] = "bombermanwhitefront";
									loc.get(i)[1][18] = "bombermanblackfront";
									loc.get(i)[18][1] = "bombermanbluefront";
								} else if (howManyPlayers.get(i) == 4) {
									loc.get(i)[1][1] = "bombermanwhitefront";
									loc.get(i)[1][18] = "bombermanblackfront";
									loc.get(i)[18][1] = "bombermanbluefront";
									loc.get(i)[18][18] = "bombermanredfront";
								}
								String fieldg = "";
								for (int j = 0; j < 20; j++) {
									for (int k = 0; k < 20; k++) {
										fieldg += loc.get(i)[j][k] + "|";
									}
								}
								for (PrintWriter writer : games.get(i)) {
									writer.println("generatefield-" + fieldg);
								}
							}
						}
					} 
					/**
					 * ersal dastur update yani ke taqiiri dar safhe bazi ijad shode
					 */
					else if (message[0].equals("update")) {
						// TODO.inaro doros kon ke kodum bazie man pishfarz ba host 0 gozashtam
						for (PrintWriter writer : games.get(0)) {
							if (writer.equals(out)) {
								continue;
							} else {
								writer.println(message[0] + "-" + message[1] + "-" + message[2] + "-" + message[3]);
							}
						}
					} 
					/**
					 * ersal dastur item yani item jadadi be map ezaf shode
					 */
					else if (message[0].equals("item")) {
						for (PrintWriter writer : games.get(0)) {
							writer.println("update-" + message[1] + "-" + message[2] + "-" + message[3]);
						}
					}
					/**
					 * ersal dastur bomb yaani bombi da zamin kashte shode
					 */
					else if(message[0].equals("bomb")) {
						for (PrintWriter writer : games.get(0)) {
							if (writer.equals(out)) {
								continue;
							} else {
								writer.println("update-bomb-" + message[1] + "-" + message[2] + "-" + message[3]);
							}
						}
					}
					/**
					 * ersal dastur bm yaani yeki az bomber man ha jabe ja shode
					 */
					else if(message[0].equals("bm")) {
						for (PrintWriter writer : games.get(0)) {
							if (writer.equals(out)) {
								continue;
							} else {
								writer.println("update-" + message[1] + "-" + message[2] + "-" + message[3] + "-" + message[4] + "-" + message[5]);
							}
						}
					}
					/**
					 * ersal dastur point yaani yeki az bazikonan emtyazash ezafe shode
					 */
					else if(message[0].equals("point")) {
						for (PrintWriter writer : games.get(0)) {
							if (writer.equals(out)) {
								continue;
							} else {
								writer.println("update-pointt-" + message[1] + "-" + message[2]);
							}
						}
					}
					/**
					 * ersal dastur end be maanai payan bazi
					 */
					else if(message[0].equals("end")) {
						for (PrintWriter writer : games.get(0)) {
							writer.println("end");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("a client lefted");
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}
	}
}
