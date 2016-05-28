package server;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;
import tools.ConnectionPool;


public class Main {
	public static void main(String[] args) {

		int server_port;
		ServerSocket serverSocket;
		Socket clientSocket; 
		final ConnectionPool connectionPool = new ConnectionPool();
		
		Properties prop = new Properties();
		InputStream input = null;

		String filename = "config.properties";
		input = Main.class.getClassLoader().getResourceAsStream(filename);
		if (input == null) {
			System.out.println("Sorry, unable to find " + filename);
		}
		// load a properties file
		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		server_port = Integer.parseInt(prop.getProperty("port_listen"));
		
		try {
			serverSocket = new ServerSocket(server_port);
			System.out.println("Server running ....");
			
			
			while(true){
				clientSocket = serverSocket.accept();
				new HandleClient(clientSocket, connectionPool).start();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
