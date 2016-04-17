package server;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Properties;

import features.Features;
import server.tools.ConnectionPool;


public class Main {
	public static void main(String[] args) {

		// TODO Auto-generated method stub
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server_port = Integer.parseInt(prop.getProperty("port_listen"));
		
		try {
//			ArrayList list = Features.getSimulationData(connectionPool.getConnectionFromPool());
//			System.out.println("Nombre prêt contractés : "+ Features.nbLoanContracted(connectionPool.getConnectionFromPool()));
//			System.out.println("Nombre de simulation par client : "+ Features.nbSimulationPerConsumer(1,connectionPool.getConnectionFromPool()));
			System.out.println("Server running ....");
			serverSocket = new ServerSocket(server_port);
			

			while(true){
				clientSocket = serverSocket.accept();
				new HandleClient(clientSocket, connectionPool).start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
