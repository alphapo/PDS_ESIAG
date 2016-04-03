package pds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	private Socket clientSocket;
	private BufferedReader in;
	private String msg;
	private PrintWriter out;
	private String login;
	private String mdp;

	public Client()
	{
		Socket clientSocket = null;
		BufferedReader in = null;
		String msg = null;
		PrintWriter out = null;
	}
		
		
	public void connection ()
	{
		try {
			this.clientSocket = new Socket("127.0.0.1", 12346);
			this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			this.msg = in.readLine();
			System.out.println("Server:"+ msg);
			//this.out = new PrintWriter(clientSocket.getOutputStream(), true);
			//this.out.println(this.login);
			//this.out.println(this.mdp);

			
		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		finally
		{
			try
			{
				in.close();
				clientSocket.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void message(String var1, String var2)
	{
		login = var1;
		mdp = var2;
		try
		{
			this.out = new PrintWriter(clientSocket.getOutputStream(), true);
			this.out.println(login);
			this.out.println(mdp);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}
	
	


}
