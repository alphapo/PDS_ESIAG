import java.net.*;
import java.io.*;

//il est obligatoire d'impl�menter la classe Runnable
class ThreadWork implements Runnable
{
private Thread tWork;
private Socket ThreadSocket; 
private InputStream	is;
private InputStreamReader isr;
private BufferedReader br;
private OutputStream os;
private PrintWriter pw;
private ServerMT2 mainServer; 
private int numClient=0; // contiendra le num�ro de client g�r� par ce thread

public ThreadWork(Socket s, ServerMT2 mainServer)
  {
	  this.mainServer=mainServer;
	  this.ThreadSocket = s;
	  try
	    {
	      // fabrication des flux 
		  	pw = new PrintWriter(ThreadSocket.getOutputStream(), true);
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
    System.out.println("Un nouveau client s'est connecte, no de son Thread : "+this.tWork.getName());


    try{
			//Pr�paration des flux dentr�es/sorties
			is = ThreadSocket.getInputStream();
		    isr = new InputStreamReader(is);
		    br = new BufferedReader(isr);
		    os = ThreadSocket.getOutputStream();

    	
//    char charCur[] = new char[10];
//    
////      while(br.read(charCur, 0, 1)!=-1) // attente en boucle des messages provenant du client (bloquant sur _in.read())
//      {
//    	  mainServer.send(message,this.ThreadSocket);
//    	  
//
////		    String conf = "Bienvenue, vous �tes le client n� "+nbClients;
////		    pw.println(conf);
//		    System.out.println("Le client n� "+nbClients+" vient de se connecter");
//		    System.out.println("IP du client : "+socket.getRemoteSocketAddress());
//    	  
//    	  
//    }
        
    while(true){
    		String in = br.readLine();
		    String conf = "Bienvenue "+in+", vous �tes le client n� "+mainServer.getNbClients();;
	    	pw.println(conf);
        }
    	
    	//Gestion des messages entre clients et server 
    	
    }
    catch (IOException e){ }
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
  

public void send(String message, Socket s) throws IOException
  {
	
	      	// ecriture du texte pass� en param�tre (et concat�nation d'une string de fin de chaine si besoin)
	        pw.print(message);
        
  }
}