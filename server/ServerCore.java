package server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;

import connection_pool.ConnectionPool;

public class ServerCore {
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		int port = 12345;
		ServerSocket serverSocket = null;
		Socket clientSocket; 
		final ConnectionPool connectionPool = new ConnectionPool();
		
		try {
			System.out.println("Server running ....");
			serverSocket = new ServerSocket(port);

			while(true){
				clientSocket = serverSocket.accept();

				System.out.println("un client "+clientSocket.getInetAddress()+" connecté");

				new HandleClient(clientSocket, connectionPool).start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
