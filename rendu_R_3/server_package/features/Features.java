package features;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import server.beans.Authentification;
import server.beans.Client;

public class Features {
	public static boolean userExist(Authentification authentification, Connection connection) {
		int linesNumber = 0;
		try {
			Statement state = connection.createStatement();
			String sql = "SELECT * FROM user where login = \""+authentification.getLogin()+"\" and password = \""+authentification.getPassword()+"\"";
			ResultSet result = state.executeQuery(sql);
			//we place on last line
			result.last(); 
			//we recover line number
			linesNumber = result.getRow(); 
		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		if(linesNumber == 1)
			return true;
		else
			return false;
	}
	public static void addClient(Client client, Connection connection) {
		try {
			Statement state = connection.createStatement();
			// Mettre a jour le nom de la table et les 
			state.executeUpdate("INSERT INTO ....... ");
		}catch ( SQLException ex){
			ex.printStackTrace();
		}
	}

	
	//Return the number of loan contracted 
	public static int nbLoanContracted(Connection connection){
		int nb=0;
		
		try {
			Statement state = connection.createStatement();
			ResultSet rs = state.executeQuery("Select * from loan");
			while(rs.next()){
				nb+=1;
			}
			System.out.println("Pret server "+nb);

		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
		
	}
	
	
	//Return the number of simulation
	public static int nbSimulation(Connection connection){
		int nb=0;
		
		try {
			Statement state = connection.createStatement();
			ResultSet rs = state.executeQuery("Select * from simulation");
			while(rs.next()){
				nb+=1;
			}
			System.out.println(nb);

		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
	}
	
	//Return the number of simulation
	public static int nbSimulationPerConsumer(int id_user, Connection connection){
		int nb=0;
		
		try {
			Statement state = connection.createStatement();
			String sql = "SELECT * from simulation where id_user = \""+id_user+"\"";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				nb+=1;
			}
			System.out.println(nb);

		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
