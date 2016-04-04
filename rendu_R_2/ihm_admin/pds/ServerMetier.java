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
		//Cr�ation d'un objet Statement
		PreparedStatement state = null;
		//L'objet ResultSet contient le r�sultat de la requ�te SQL
		//ResultSet result = null;
		ResultSet result = null;
		//requete � executer
		String req1 ="SELECT id_user FROM user where login=?  and password=?";
		boolean bool = false;
		try {
			conn = DriverManager.getConnection(this.database, this.dbuser, this.dbpassword);

			state = conn.prepareStatement(req1);
			state.setString(1,a);
			state.setString(2,b);
			//result = state.executeQuery("SELECT id_user FROM user where login=''  and password='Azerty123'");
			//On r�cup�re les MetaData
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

	
	public int selectID()
	{
		Connection conn = null;
		//Cr�ation d'un objet Statement
		PreparedStatement state = null;
		//L'objet ResultSet contient le r�sultat de la requ�te SQL
		//ResultSet result = null;
		ResultSet result = null;
		String req ="Select max(id_Consumer) as id from consumer";
		int id = 0;
		try {
			conn = DriverManager.getConnection(this.database, this.dbuser, this.dbpassword);

			state = conn.prepareStatement(req);
			result = state.executeQuery();
			while (result.next())
			{
				id = result.getInt("id")+1;
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
		return id;
	}
	
	public String selectIDName(int id)
	{
		Connection conn = null;
		//Cr�ation d'un objet Statement
		Statement state = null;
		//L'objet ResultSet contient le r�sultat de la requ�te SQL
		//ResultSet result = null;
		ResultSet result = null;
		String name = null;
		String req ="Select name from consumer where id_Consumer= \""+id+"\"";
		System.out.println(req);
		try {
			conn = DriverManager.getConnection(this.database, this.dbuser, this.dbpassword);

			state = conn.prepareStatement(req);
//			state.setInt(1,id);
			result = state.executeQuery(req);
			while (result.next())
			{
				name = result.getString(id);
			}
			System.out.println(name);
			System.out.println(id);

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
		return name;
	}
	
	public void selectClient(JComboBox comboBox)
	{
		Connection conn = null;
		//Cr�ation d'un objet Statement
		PreparedStatement state = null;
		//L'objet ResultSet contient le r�sultat de la requ�te SQL
		//ResultSet result = null;
		ResultSet result = null;
		//requete � executer
		String req1 ="SELECT id_consumer, name, firstname, id_agency FROM consumer ";
		try {
			conn = DriverManager.getConnection(this.database, this.dbuser, this.dbpassword);

			state = conn.prepareStatement(req1);
			result = state.executeQuery();
			comboBox.removeAllItems();
			//comboBox.addItem("");
			while (result.next())
			{
				//comboBox.addItem(result.getInt("id_consumer"));
				comboBox.addItem(result.getInt("id_consumer")+" "+ result.getString("name")+" "+ result.getString("firstname"));
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
		//Cr�ation d'un objet Statement
		PreparedStatement state = null;
		//L'objet ResultSet contient le r�sultat de la requ�te SQL
		//ResultSet result = null;
		ResultSet result = null;
		//requete � executer
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


	public void creerClient (String name, String firstName, String dateOfBirth, String phoneNumber, String address, String postalCOde, String city, String countryOfBirth, String nationality, int agency)
	{
		Connection conn = null;
		String req2 = null;
		//Cr�ation d'un objet Statement
		Statement state = null;
		int rs = 0;
		int id = new ServerMetier().selectID();
		try {
			conn = DriverManager.getConnection(this.database, this.dbuser, this.dbpassword);
			req2 ="INSERT INTO consumer Values ('"+id+"' ,'"+name+"','"+firstName+"','"+dateOfBirth+"','"+address+"','"+postalCOde+"','"+countryOfBirth+"','"+phoneNumber+"','"+city+"','"+agency+"','"+nationality+"')";
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
		//Cr�ation d'un objet Statement
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
		System.out.println(new ServerMetier().selectID());
		
		//new ServerMetier().essai();
		//new ServerMetier().creerClient("Dupond", "Bertrand", 111111111, 3, 1);
		//new ServerMetier().creerClient("Bernard", "Martin", 111111111, 1);
		//new ServerMetier().supprimerClient(7);
	}
}
