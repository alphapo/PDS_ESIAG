

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Client{
	
public static void main(String[] args)
{
		int port = 1212;
		try {
			Socket socketClient = new Socket("localhost", port);
			ClientManage cm = new ClientManage(socketClient);

//			//InputStream lit octet par octet
//			InputStream is = socketClient.getInputStream();
//			InputStreamReader isr = new InputStreamReader(is);
//			//OutputStream envoie octect par octet
//		    OutputStream os=socketClient.getOutputStream();
//		    PrintWriter	pw = new PrintWriter(os, true);
//		    BufferedReader br = new BufferedReader(isr);
		    

		    
		    while(true){
		    		String message;
		    		String reponseServer;
//		    	  ObjectOutputStream outObject = new ObjectOutputStream(socket.getOutputStream());
//		    	  outObject.flush();
//		   
//		          ObjectInputStream inObject = new ObjectInputStream(socket.getInputStream());
		   
//		          System.out.println("Client a cree les flux");
		          
		            Scanner clavier = new Scanner(System.in);
					
					System.out.println("Entrez votre message");
					message = clavier.nextLine();
//					pw.println(message);
					cm.send(message);
		   
		       
		   
////		          Object objetRecu = inObject.readObject();
//		         	  ArrayList list = new ArrayList();
//		        	  list.add(nom);
//		        	  list.add(mdp);
////		          list.add(socket);
		          
		          
//		         	 System.out.println("Client: donnees emises...");
//		         	 outObject.writeObject(list);
//		         	 outObject.flush();
		          
		          
//		         	 System.out.println("Client recoit: " + Arrays.toString(tableauRecu));
					
//		         	 in.close();
//		         	 out.close();
//		         	 socket.close();
					
				
//					list.add(nom);
//					list.add(mdp);
//					reponseServer = br.readLine();
//					System.out.println("Réponse du server : "+reponseServer);
					cm.reception();
			
		    }
			
			} 
		    catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

}



}
