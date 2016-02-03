import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientManage {
//	private Client client;
	private Socket socketClient;
	private InputStream	is;
	private InputStreamReader isr;
	private BufferedReader br;
	private OutputStream os;
	private PrintWriter pw;
	

	public ClientManage(Socket socketClient) throws IOException {
		this.socketClient = socketClient;
		is = socketClient.getInputStream();
	    isr = new InputStreamReader(is);
	    br = new BufferedReader(isr);
	    os = socketClient.getOutputStream();
		pw = new PrintWriter(os, true);
      	br = new BufferedReader(isr);

	}
	
	//Ces deux méthodes vont être implementer dans une autre classe ClientManage où ces méthodes seront appelées.
	public void send(String login) throws IOException
	{

      	pw.println(login);
	}


	public void reception() throws IOException{
		String s = br.readLine();
		System.out.println("Réponse du server : "+s);
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
