import java.net.*;
import java.io.*;

//il est obligatoire d'implémenter la classe Runnable
class ThreadWork implements Runnable
{
private Thread tWork;
private boolean connecte=false;
private Socket ThreadSocket; 
private InputStream	is;
private InputStreamReader isr;
private BufferedReader br;
private OutputStream os;
private PrintWriter pw;
private ServerMT2 mainServer; 
private int numClient=0; // contiendra le numéro de client géré par ce thread

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
	  		tWork.start(); // demarrage du thread, la fonction run() est ici lancée
	  		mainServer.addClient(tWork);
	  }
  
    
public void run()
  {
    String message = "TESTESTES"; // déclaration de la variable qui recevra les messages du client
    // on indique dans la console la connection d'un nouveau client
    System.out.println("Un nouveau client s'est connecte, no de son Thread : "+this.tWork.getName());


    try{
			//Préparation des flux dentrées/sorties
			is = ThreadSocket.getInputStream();
		    isr = new InputStreamReader(is);
		    br = new BufferedReader(isr);
		    os = ThreadSocket.getOutputStream();


		    while(true){
		    	//Vérification base de donnee
		    		String in = br.readLine();
		    		String pseudo = "usman";
		    		System.out.println("Tentative de connexion de "+pseudo);
		    		if(in.equals(pseudo)){
//		    			 String conf = "Bienvene "+in+", vous êtes le client n° "+mainServer.getNbClients();
		    			 pw.println("1");
		    			 this.connecte=true;
		    		}
		    		else{
//					    String conf = "Désolé, mais cet identifiant n'existe pas dans la BD !";
				    	pw.println("0");
				    	this.connecte=false;
		    		}

		        }
    	
    	//Gestion des messages entre clients et server 
    	
    }
    catch (IOException e){ }
    catch (Exception e){ }
  
    finally // finally se produira le plus souvent lors de la deconnexion du client
    {
      try
      {
	      	// on indique à la console la deconnexion du client
	        System.out.println("Le client no "+numClient+" s'est deconnecte");
	        mainServer.delClient(tWork); // on supprime le client de la liste
	        ThreadSocket.close(); // fermeture du socket si il ne l'a pas déjà été (à cause de l'exception levée plus haut)
	        System.out.println("Liste des clients connectés : ");
	        mainServer.listeClients();
      }
      catch (IOException e){ }
    }
  }
  

public void send(String message, Socket s) throws IOException
  {
	
	      	// ecriture du texte passé en paramètre (et concaténation d'une string de fin de chaine si besoin)
	        pw.print(message);
        
  }
}