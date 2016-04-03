package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;

import connection_pool.ConnectionPool;


public class HandleClient extends Thread {
	PrintWriter out = null;
	BufferedReader in = null;
	
	public HandleClient(Socket clientSocket, ConnectionPool pool) throws IOException {
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	public void run() {
		String jsonContent = null;
		Authentification auth = null;
		boolean exist=false;
		do{
			try {
				jsonContent = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			auth = ServerParserJson.getAthentification(jsonContent);
			exist = Features.userExist(auth);
			if(!exist){
				out.println("ERROR");
				out.println(ServerFactoryJson.connectionDenied());
			}
		}while(!exist);
		try {
			String command = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//     	
//    	try {
//    		while (!stop) {
//    			switch (chi.getCommand()) {
//    			
//    			case Input.DELETE:  cho.sendEmptyLine();
//    								cho.deleteRessource(chi.getFilename(), chi.getUsername());
//    								cho.sendOk();
//    								cho.sendEmptyLine();
//    								break;
//    			
//    			case Input.GET:		cho.sendEmptyLine();
//    								cho.sendFileContent(chi.getFilename(), chi.getUsername());
//    								cho.sendEmptyLine();
//    								cho.sendOk();
//    								cho.sendEmptyLine();
//    								break;
//    			
//    			case Input.PUT:		cho.sendEmptyLine();
//    								cho.writeFile( chi.getFilename(), chi.getUsername(), chi.getFileContent() );
//    								cho.sendEmptyLine();
//    								break;
//    								
//    			case Input.PROPFIND:cho.sendEmptyLine();
//									cho.propFile(chi.getFilename() , chi.getUsername());
//									cho.sendEmptyLine();
//									break;
	}	
}
