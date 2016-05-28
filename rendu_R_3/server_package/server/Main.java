package server;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;
import server.tools.ConnectionPool;
import service.InformationImpl;


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

			System.out.println("Mise en place du port registre");

			LocateRegistry.createRegistry(1099);

			InformationImpl informationImpl = new InformationImpl();

			String url = "rmi://localhost:1099/IndicatorsRMI";
			System.out.println("Enregistrement de l'objet avec l'url : " + url);
			
			//On enregistre l'objet informationImpl auprès du registry
			Naming.rebind(url, informationImpl);

			System.out.println("Server running ....");
			serverSocket = new ServerSocket(server_port);
			
			while(true){
				clientSocket = serverSocket.accept();
				new HandleClient(clientSocket, connectionPool).start();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
