package client.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;


public class Communicator {
	
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	
	public Communicator() {
		Properties prop = new Properties();
		InputStream input = null;
		
		String server_address;
		int server_port;

		String filename = "client_config.properties";
		input = Communicator.class.getClassLoader().getResourceAsStream(filename);
		if (input == null) {
			System.out.println("Sorry, unable to find " + filename);
		}
		// load a properties file
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server_address = prop.getProperty("server_address");
		server_port = Integer.parseInt(prop.getProperty("server_port"));
		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			clientSocket = new Socket(server_address, server_port);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void sendData(String data){		
		out.println(data);
	}
	public String receiveData(){
		try {
			return ClientParserJson.resultQueryAccess(in.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
//	public int receiveDataSimulation() throws IOException{
//		try {
//			return ClientParserJson.resultQueryAccessSimulation(in.readLine());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return 0;
//		
//	}
	
	
	
//	
//	public Hashtable receiveDataConsumer() throws IOException{
//		try {
//			return ClientParserJson.resultQueryAccessConsumer(in.readLine());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//		
//	}
}
