
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client{

	
public static void main(String[] args)
{
		boolean connecte = false;
		int port = 1212;
		try {
			Socket socketClient = new Socket("localhost", port);
			ClientManage cm = new ClientManage(socketClient);
			//Cr�ation d'un thread Client g�r� par ClientManage
			Thread tClient = new Thread(cm);
			tClient.start();
//		    }
//			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		finally
		{
		     System.out.println("Connexion ferm�e");
	    }
		

}



}
