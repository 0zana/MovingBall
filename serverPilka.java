package kolos;



import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class serverPilka {
	public static void main(String[] args) {
		while(true) {
			ServerSocket servSock = null;
			int k=0;
			try {
				servSock = new ServerSocket(12345);
			}catch (IOException e) {
				System.err.println("Nie mo¿na pod³czyæ");
				System.exit(1);
			}
			System.out.println("Nas³uchiwanie na port 12345! ");
			Socket clSock =null;
			
			try {
				clSock = servSock.accept();
				servSock.close();
			}catch (IOException e) {
				System.err.println("Nie zaakceptowano portu, " +e);
				System.exit(1);
			}
			System.out.println("Zaakecpowano port 12345!");
			
			try {
				System.out.println("Zaczynamy zabawê!");
				BufferedReader in = new BufferedReader(new InputStreamReader(clSock.getInputStream()));
				PrintWriter out = new PrintWriter(clSock.getOutputStream(), true);
				String inputLine;
				
				out.println("Zaczynamy");
				out.flush();
				
				while((inputLine = in.readLine()) != null) {
					if(inputLine != null){
						k++;
					}
					out.println("Noted");
					out.flush();
					if(k==9) {
						break;
					}
				}
				out.close();
				clSock.close();
				System.out.println("Serwer: Zakoñczono dzia³anie i koñczenie po³¹czenia");
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}

