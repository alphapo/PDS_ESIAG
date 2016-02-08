package pds;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import javax.swing.JComboBox;

public class ServerMetier {

	//public static void main(String[] args) {      
	private String DriverName;
	private String database;
	private String dbuser;
	private String dbpassword;

	public ServerMetier()
	{
		Properties prop = new Properties();
		InputStream input = null;


		try {

			String filename = "config.properties";
			input = ServerMetier.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return;
			}
			// load a properties file
			prop.load(input);

			DriverName = prop.getProperty("DriverName");
			database = prop.getProperty("database");
			dbuser = prop.getProperty("dbuser");
			dbpassword = prop.getProperty("dbpassword");

			Class.forName(DriverName);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				input.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public boolean verifID (String a, String b)
	{
		Connection conn = null;
		//Création d'un objet Statement
		PreparedStatement state = null;
		//L'objet ResultSet contient le résultat de la requête SQL
		//ResultSet result = null;
		ResultSet result = null;
		//requete à executer
		String req1 ="SELECT id_user FROM user where login=?  and password=?";
		boolean bool = false;
		try {
			conn = DriverManager.getConnection(this.database, this.dbuser, this.dbpassword);

			state = conn.prepareStatement(req1);
			state.setString(1,a);
			state.setString(2,b);
			//result = state.executeQuery("SELECT id_user FROM user where login=''  and password='Azerty123'");
			//On récupère les MetaData
			result = state.executeQuery();
			if (result.next())
			{
				/*
					while (result.next())
					{
						int resultat = result.getInt("id_user");
						System.out.println(resultat);
					}
				 */
				bool = true;
			}
			else 
			{
				//System.out.println("aucun resultat");
				bool = false;
			}


		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		finally
		{
			try{if(result!=null){result.close();}}catch(Exception e){} 
			try{if(state!=null){state.close();}}catch(Exception e){} 
			try{if(conn!=null){conn.close();}}catch(Exception e){}
		}

		if(bool)	{System.out.println("ok");return true;}
		else { System.out.println("ko");return false;}
	}


	public void selectClient(JComboBox comboBox)
	{
		Connection conn = null;
		//Création d'un objet Statement
		PreparedStatement state = null;
		//L'objet ResultSet contient le résultat de la requête SQL
		//ResultSet result = null;
		ResultSet result = null;
		//requete à executer
		String req1 ="SELECT id_consumer, firstname, lastname, id_account, id_agency FROM consumer ";
		try {
			conn = DriverManager.getConnection(this.database, this.dbuser, this.dbpassword);

			state = conn.prepareStatement(req1);
			result = state.executeQuery();
			comboBox.removeAllItems();
			//comboBox.addItem("");
			while (result.next())
			{
				//comboBox.addItem(result.getInt("id_consumer"));
				comboBox.addItem(result.getInt("id_consumer")+" "+ result.getString("firstname")+" "+ result.getString("lastname")+" "+result.getInt("id_account"));
			}

		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		finally
		{
			try{if(result!=null){result.close();}}catch(Exception e){} 
			try{if(state!=null){state.close();}}catch(Exception e){} 
			try{if(conn!=null){conn.close();}}catch(Exception e){}
		}
	}

	public void selectAgency(JComboBox comboBox)
	{
		Connection conn = null;
		//Création d'un objet Statement
		PreparedStatement state = null;
		//L'objet ResultSet contient le résultat de la requête SQL
		//ResultSet result = null;
		ResultSet result = null;
		//requete à executer
		String req2 ="SELECT id_agency, city, country From agency ";
		try {
			conn = DriverManager.getConnection(this.database, this.dbuser, this.dbpassword);
			state = conn.prepareStatement(req2);
			result = state.executeQuery();
			comboBox.addItem("");
			while (result.next())
			{
				comboBox.addItem(result.getString("city"));
			}

		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		finally
		{
			try{if(result!=null){result.close();}}catch(Exception e){} 
			try{if(state!=null){state.close();}}catch(Exception e){} 
			try{if(conn!=null){conn.close();}}catch(Exception e){}
		}
	}


	public void creerClient (String firstName, String name, int idAccount, int agency, int employee)
	{
		Connection conn = null;
		String req2 = null;
		//Création d'un objet Statement
		Statement state = null;
		int rs = 0;
		try {
			conn = DriverManager.getConnection(this.database, this.dbuser, this.dbpassword);
			req2 ="INSERT INTO consumer Values (NULL,'"+name+"','"+firstName+"','"+idAccount+"','"+agency+"','"+employee+"')";
			state = conn.prepareStatement(req2);
			rs = state.executeUpdate(req2);
			System.out.println("ok");

		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		finally
		{
			try{if(state!=null){state.close();}}catch(Exception e){} 
			try{if(conn!=null){conn.close();}}catch(Exception e){}
		}
	}


	public void supprimerClient(int var1)
	{
		Connection conn = null;
		String req2 = null;
		//Création d'un objet Statement
		Statement state = null;
		int rs = 0;
		
		try {
			conn = DriverManager.getConnection(this.database, this.dbuser, this.dbpassword);
			String SQL = "DELETE FROM consumer WHERE id_consumer ='"+var1+"'";
			state = conn.prepareStatement(SQL);
			rs = state.executeUpdate(SQL);
			System.out.println("ok");
		

		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		finally
		{
			try{if(state!=null){state.close();}}catch(Exception e){} 
			try{if(conn!=null){conn.close();}}catch(Exception e){}
		}
	}

	public static void main (String [] args)
	{
		String a = "Ldurand";
		String b = "Azerty123";
		new ServerMetier().verifID(a, b);
		new ServerMetier().creerClient("Dupond", "Bertrand", 111111111, 3, 1);
	}
}
