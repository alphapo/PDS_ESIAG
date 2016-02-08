package pds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		int port = 12345;
	    ServerSocket serverSocket = null;
	    Socket clientSocket; 
	    BufferedReader in;
	    PrintWriter out;
	    
	    
	    try {
	    	System.out.println("Demarrage du server....");
			serverSocket = new ServerSocket(port);
			
		    clientSocket = serverSocket.accept();
		    
		    
		    
		    System.out.println("un client connecté");
		    out = new PrintWriter(clientSocket.getOutputStream(), true);
		    out.println("vous etes connecté");
			
		    clientSocket.close();
			serverSocket.close();

		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	    
	    
	    
	    
	}

}
