import java.net.*;
import java.io.*;

//il est obligatoire d'impl�menter la classe Runnable
class ThreadWork implements Runnable
{
  private Thread tWork;
  private Socket ThreadSocket; 
  private PrintWriter out; 
  private BufferedReader br; 
  private ServerMT2 mainServer; 
  private int numClient=0; // contiendra le num�ro de client g�r� par ce thread

 public ThreadWork(Socket s, ServerMT2 mainServer)
  {
	  this.mainServer=mainServer;
	  this.ThreadSocket = s;
	  try
	    {
	      // fabrication des flux 
	      out = new PrintWriter(ThreadSocket.getOutputStream());
	      br = new BufferedReader(new InputStreamReader(ThreadSocket.getInputStream()));
	      numClient = mainServer.getNbClients();
	    }
	    catch (IOException e){ }

	  tWork = new Thread(this); // instanciation du thread
	  tWork.start(); // demarrage du thread, la fonction run() est ici lanc�e
	    mainServer.addClient(tWork);
	  }
  
    
  public void run()
  {
    String message = "TESTESTES"; // d�claration de la variable qui recevra les messages du client
    // on indique dans la console la connection d'un nouveau client
    System.out.println("Un nouveau client s'est connecte, no "+numClient);


    try{
    char charCur[] = new char[10];
    
      while(br.read(charCur, 0, 1)!=-1) // attente en boucle des messages provenant du client (bloquant sur _in.read())
      {
    	  mainServer.send(message,this.ThreadSocket);
    }
    	out.println(message);
        System.out.println("R�ponse du server : ");
        System.out.println("Bienvenue, vous etes maintenant connect� ! ");
    	
    	//Gestion des messages entre clients et server 
    	
    }
    catch (Exception e){ }
    finally // finally se produira le plus souvent lors de la deconnexion du client
    {
      try
      {
      	// on indique � la console la deconnexion du client
        System.out.println("Le client no "+numClient+" s'est deconnecte");
        mainServer.delClient(tWork); // on supprime le client de la liste
        ThreadSocket.close(); // fermeture du socket si il ne l'a pas d�j� �t� (� cause de l'exception lev�e plus haut)
        System.out.println("Liste des clients connect�s : ");
        mainServer.listeClients();
      }
      catch (IOException e){ }
    }
  }
}