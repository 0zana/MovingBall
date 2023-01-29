package kolos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

public class clientPilka {
	public static void main(String[] args) {
		try {
			Socket sock = new Socket("localhost", 12345);
			System.out.println("Po³¹czono! 12345");
			boolean gh;

			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			PrintWriter out = new PrintWriter(sock.getOutputStream(), true);

			String s, serverResponse;
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			while ((serverResponse = in.readLine()) != null) {
				gh = false;
				System.out.println("Server: " + serverResponse);
				AppF f = new AppF();
				f.setVisible(true);
				while (!gh) {
					if (f.animArea.isStart) {
						s = sdf.format(System.currentTimeMillis());
						System.out.println("Client: "+s);
						out.println(s);
						out.flush();
						gh=true;
					}
				}
				f.setVisible(false);
			}
			System.out.println("CLIENT: Ending server connection. Closing client streams and socket.");
			out.close();
			in.close();
			sock.close();
			System.exit(0);
		} catch (

		UnknownHostException e) {
			System.err.println("CLIENT: Trying to connect to unknown host: " + e);
			System.exit(1);
		} catch (Exception e) {
			System.err.println("CLIENT: Exception:  " + e);
			System.exit(1);
		}

	}
}
