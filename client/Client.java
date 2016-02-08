package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket clientSocket;
		BufferedReader in;
		PrintWriter out;
		String msg = null;
		try {
			clientSocket = new Socket("127.0.0.1", 12345);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			msg = in.readLine();
			System.out.println("Server:"+ msg);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
