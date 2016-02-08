package pds;

import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static String tabInfos[] = new String[2];
	private static int cpt = 0;
	//public Server()

	public static void main (String [] args)
	{

		int port = 12346;
		ServerSocket serverSocket = null;
		Socket clientSocket = null; 
		BufferedReader in = null;
		PrintWriter out = null;
		String msg = null;


		try {
			System.out.println("Demarrage du server....");
			serverSocket = new ServerSocket(port);
			clientSocket = serverSocket.accept();
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			
			
			out.println("vous etes connecté");
 
		         while((msg = in.readLine())!=null)
		         {
		             tabInfos[cpt] = msg;
		             System.out.println(tabInfos[cpt]);
		             cpt++;
		             
		         }
		         if (new ServerMetier().verifID(tabInfos[0], tabInfos[1]))
		         {
		        	 new Administration();
		         }
		         else
		         {
		        	 System.out.println("identifiants incorrect");
		         }


		} catch (IOException e) {

			e.printStackTrace();

		}
		finally
	    {
	    	try
	    	{
				clientSocket.close();
				//serverSocket.close();
	    		out.close();
	    		in.close();	  
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    	}
	    }


	}

}
