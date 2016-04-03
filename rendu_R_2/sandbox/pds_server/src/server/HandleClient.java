package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;


import features.Features;
import server.beans.Authentification;
import server.task.Task;
import server.tools.ConnectionPool;
import server.tools.ServerParserJson;


public class HandleClient extends Thread {
	private PrintWriter out;
	private BufferedReader in;
	final private Socket clientSocket ;
	private ConnectionPool connectionPool;
	private Connection connection;
	private boolean stop = false;

	public HandleClient(final Socket clientSocket, ConnectionPool pool) throws IOException {
		this.clientSocket = clientSocket;
		connection = pool.getConnectionFromPool();
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		connectionPool = pool;
	}

	public void run() {
		System.out.println("client "+clientSocket.getInetAddress()+" tries to connect");
		String jsonContent; 
		String result = null;

		try{
			while(!stop){
				jsonContent = this.getContentJson(in);
				switch(getRequest(jsonContent)){
				case "AUTH":  result = Task.authentificationLaunched(jsonContent, connection);
							  break;
				case "ADD_CLIENT": result = Task.addClientLaunched(jsonContent, connection);
							       break;
				case "LOAN":  // Task for loan ...
					          break;

				case "EXIT":  // Task when client exit...
					          break;
				}
				out.println(result);
			}
		}catch(IOException e){
			finish();
		}
	}

	private String getContentJson(final BufferedReader in) throws IOException{
		return  in.readLine(); 
	}
	private String getRequest(String jsonContent){
		return ServerParserJson.getRequest(jsonContent);
	}
	public synchronized void finish() {
		if (!stop) {
			stop = true;
			try {
				System.out.println("client "+clientSocket.getInetAddress()+" is disconnected");
				clientSocket.close();
				connectionPool.returnConnectionToPool(connection);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
