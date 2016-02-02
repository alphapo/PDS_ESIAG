

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

public class Client {

	public static void main(String[] args) throws ClassNotFoundException {
		int port = 1212;
		// TODO Auto-generated method stub
		Socket socket;
	    //InputStream lit octet par octet
	    InputStream is;
	    //OutputStream envoie octect par octet
	    OutputStream os;
	    
	    BufferedReader br;
	    InputStreamReader isr;
	    PrintWriter pw;
	    
	    
	    
	    
//		BufferedReader in;
//		String msg = null;
		try {
			socket = new Socket("localhost", port);
		    is = socket.getInputStream();
		    isr = new InputStreamReader(is);
		    br = new BufferedReader(isr);
		    os=socket.getOutputStream();
		    pw = new PrintWriter(os,true);
		    
		    
		    while(true){
//		    	
//		    	  ObjectOutputStream outObject = new ObjectOutputStream(socket.getOutputStream());
//		    	  outObject.flush();
//		   
//		          ObjectInputStream inObject = new ObjectInputStream(socket.getInputStream());
		   
//		          System.out.println("Client a cree les flux");
		          
		          Scanner clavier = new Scanner(System.in);
					
					System.out.println("Entrez votre message");
					String message = clavier.nextLine();
					System.out.println("Donner votre mdp");
					String mdp = clavier.nextLine();
		   
		         
		   
		       
		   
////		          Object objetRecu = inObject.readObject();
//		          ArrayList list = new ArrayList();
//		          list.add(nom);
//		          list.add(mdp);
////		          list.add(socket);
		          
		          
//		          System.out.println("Client: donnees emises...");
//		          outObject.writeObject(list);
//		          outObject.flush();
		          
		          
//		          System.out.println("Client recoit: " + Arrays.toString(tableauRecu));
		   
//		          in.close();
//		          out.close();
//		          socket.close();
		    	
				
//				list.add(nom);
//				list.add(mdp);
				pw.println(message);
				String instr=br.readLine();
				System.out.println("Réponse du server : "+instr);
				
//				System.out.println("Quitter ?");
				
		    }
			//			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//			msg = in.readLine();
//			System.out.println("Server:"+ msg);
//			
			
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
