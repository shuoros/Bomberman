package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * safhe client baraye ertebat barqar kardan client ha ba server har client
 * socketesho mide be in class va in class harfasho be server mige ya harafyy ke
 * server besh mizane ro migire
 * 
 * @author Soroush
 *
 */
public class Client extends Thread {
	private static BufferedReader in;
	private static PrintWriter out;
	private static Socket socket;

	public Client(Socket s) {
		this.socket = s;
	}

	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * in method ye string migire va uno be server mide
	 * 
	 * @param message
	 */
	public void sendToServer(String message) {
		out.println(message);
	}

	/**
	 * in method harfaye servero migire
	 * @return
	 */
	public String giveFromServer() {
		String message = null;
		try {
			message = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
}
