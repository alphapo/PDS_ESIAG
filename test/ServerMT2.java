import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMT2
{
private ArrayList<Thread> threadArray = new ArrayList<Thread>(); // contiendra tous les flux de sortie vers les clients
private int nbClients=0; // nombre total de clients connectés
private int taille;

  //Methode : la première méthode exécutée, elle attend les connections **
public static void main(String args[])
{
	   ServerMT2 mainServer = new ServerMT2(); // instance de la classe principale

	   try
	    {
		    new ThreadManage2(mainServer); // lance le thread de gestion de la console
		    int port = 1212;//port à déterminer
		    ServerSocket ss = new ServerSocket(port); // ouverture d'un socket serveur sur port
		    printWelcome(port);
		    
		    while (true){ // attente en boucle de connexion (bloquant sur ss.accept)
			    if(mainServer.dispo()){//Si true, alors il y a des connexions disponibles
			    	ThreadWork t = new ThreadWork(ss.accept(), mainServer); //Un client se connecte
			   }
			    else{
				  	ss.accept();
				  	InputStream	is = ss.accept().getInputStream();
				    InputStreamReader isr = new InputStreamReader(is);
				    BufferedReader br = new BufferedReader(isr);
				    OutputStream os = ss.accept().getOutputStream();
				    PrintWriter pw = new PrintWriter(os, true);
			//		    System.out.println("Connexions pleines ! Veuillez réessayer plus tard !");
			  		System.out.println("Connexion refusée");
			//	  		mainServer.send("Connexions pleines ! Veuillez réessayer plus tard !", ss.accept());
			  		ss.accept().close();
		      }
	    	  
	      }
	    }
	    catch (Exception e) { }
}
 
  //Methode : affiche le message d'accueil **
static private void printWelcome(Integer port)
{
	   System.out.println("--------");
	   System.out.println("Server : Version 1.0");
	   System.out.println("Derniere version : 02/02/2016");
	   System.out.println("--------");
	   System.out.println("Demarre sur le port : "+port.toString());
       System.out.println("--------");
       System.out.println("Quitter : tapez \"quit\"");
       System.out.println("Nombre de personnes connectes : tapez \"total\"");
       System.out.println("--------");
}



  //Methode : détruit le client/thread, c'est à dire la connexion
public void delClient(Thread t)
{
	   nbClients--; // un client en moins
       threadArray.remove(t); //On retire le thread de l'arraylist
 
}
  
  //Méthode permettant de définir la limite des connesions
public boolean dispo()
{
	   if(threadArray.size()>4)
	     return false;
       else
	     return true;
   
}
 
 
  //Methode : ajoute un nouveau client/thread dans la liste **
public void addClient(Thread t)
{
       nbClients++; // un client en plus 
       threadArray.add(t); // on ajoute le nouveau threa à la liste de thread
}
  
  //Methode affichant la liste de connexions
public void listeClients()
{	  System.out.println("Nombre de clients connectés : "+this.getNbClients());
	  if(!threadArray.isEmpty()){
     	 for(int i=0;i<threadArray.size(); i++){
		        	System.out.println("----> "+threadArray.get(i).getName());
//		        	connec.removeAll(connec);
		        }
     }
     else
     	System.out.println("Aucune connexion prise");

}

  //Methode : retourne le nombre de clients connectés **
public int getNbClients()
{
      return nbClients; // retourne le nombre de clients connectés
}

}