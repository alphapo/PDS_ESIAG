import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientManage implements Runnable{
//	private Client client;
	private Socket socketClient;
	private InputStream	is;
	private InputStreamReader isr;
	private BufferedReader br;
	private OutputStream os;
	private PrintWriter pw;
	private boolean connecte;
	private String login;
	private String mdp;
	

	public void setConnecte(boolean connecte) {
		this.connecte = connecte;
	}

	public ClientManage(Socket socketClient) throws IOException {
		this.socketClient = socketClient;
		is = socketClient.getInputStream();
	    isr = new InputStreamReader(is);
	    br = new BufferedReader(isr);
	    os = socketClient.getOutputStream();
		pw = new PrintWriter(os, true);
      	br = new BufferedReader(isr);

	}
	
	public void run(){
		System.out.println("avant");
		Identification idft = new Identification(this);
		System.out.println("après");
		while(this.connecte==false){
			try {
				if(idft.getPass()==false && this.connecte==false){
					System.out.println("");
				}	
				else{
					this.login = idft.getIdentifiant();
					this.send(login);
					this.reception();
					this.setConnecte(true);
					}
				
			} catch (IOException e) {
				System.out.println("Attente trop longue !");
				System.out.println("Erreur lors de l'envoie du message vers le serveur !");
				e.printStackTrace();
			}

		}
		
		System.out.println("2 eme étape !");
		
	}
	
	
	//Méthode permettant d'envoyer des requetes au serveur
	public void send(String login) throws IOException
	{

      	pw.println(login);
	}


	public void reception() throws IOException{
		String s = br.readLine();
		if(s.equals("0"))
			System.out.println("Réponse du server : Désolé, mais cet identifiant n'existe pas dans la BD !");
		else
			System.out.println("Bienvenue "+this.login+", Vous êtes maintenant connecté !");
		
	}
	

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
