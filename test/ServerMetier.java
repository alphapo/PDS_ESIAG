package pds;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Properties;

public class ServerMetier {

	public static void main(String[] args) {      

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

			String DriverName = prop.getProperty("DriverName");
			String database = prop.getProperty("database");
			String dbuser = prop.getProperty("dbuser");
			String dbpassword = prop.getProperty("dbpassword");

			Class.forName(DriverName);
			System.out.println("Driver O.K.");

			Connection conn = DriverManager.getConnection(database, dbuser, dbpassword);
			System.out.println("Connexion effective !");       

			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT * FROM agency");
			//On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();

			System.out.println("\n**********************************");
			//On affiche le nom des colonnes
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

			System.out.println("\n**********************************");

			while(result.next()){         
				for(int i = 1; i <= resultMeta.getColumnCount(); i++)
					System.out.print("\t" + result.getObject(i).toString() + "\t |");

				System.out.println("\n---------------------------------");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}      
	}
}
