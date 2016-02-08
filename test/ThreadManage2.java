import java.io.*;

//Gestion des commandes dans la console
class ThreadManage2 implements Runnable
{
  ServerMT2 mainServer; 
  BufferedReader in;
  String commandeTapee=""; // contiendra la commande tap�e
  Thread tWork; // contiendra le thread

public ThreadManage2(ServerMT2 mainServer)
  {
	this.mainServer=mainServer; 
    in = new BufferedReader(new InputStreamReader(System.in));
    tWork = new Thread(this); // instanciation du thread
    tWork.start(); // demarrage du thread, la fonction run() est ici lanc�e
  }

  //Methode : attend les commandes dans la console et ex�cute l'action demand�e **
  public void run()
  {
    try
    {
      // si aucune commande n'est tap�e, on ne fait rien (bloquant sur in.readLine())
      while ((commandeTapee=in.readLine())!=null)
      {
        if (commandeTapee.equals("quit")) // commande "quit" detect�e ...
          System.exit(0); // ... on ferme alors le serveur
        else if(commandeTapee.equals("total")) // commande "total" detect�e ...
        {
          // ... on affiche le nombre de clients actuellement connect�s
          System.out.println("Nombre de clients connectes : "+mainServer.getNbClients());
          System.out.println("--------");
        }
        else
        {
          // si la commande n'est ni "total", ni "quit", on informe l'utilisateur et on lui donne une aide
          System.out.println("Cette commande n'est pas supportee");
          System.out.println("Quitter : \"quit\"");
          System.out.println("Nombre de connectes : \"total\"");
          System.out.println("--------");
        }
        System.out.flush(); // on affiche tout ce qui est en attente dans le flux
      }
    }
    catch (IOException e) {}
  }
}