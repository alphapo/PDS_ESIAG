package features;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import server.HandleClient;
import server.beans.Authentification;
import server.beans.Client;

public class Features {
	static String dateInf;
	static String dateSup;
	
//	public static String saveDate( String dateInf, String dateSup , Connection connection) {
//		HandleClient.
//		
//		
//		try {
//			Statement state = connection.createStatement();
//			// Mettre a jour le nom de la table et les 
//			state.executeUpdate("INSERT INTO ....... ");
//		}catch ( SQLException ex){
//			ex.printStackTrace();
//		}
//	}
	
	public static String getDateSup() {
		return dateSup;
	}
	public static void setDateSup(String dateSup) {
		Features.dateSup = dateSup;
	}
	public static String getDateInf() {
		return dateInf;
	}
	public static void setDateInf(String dateInf) {
		Features.dateInf = dateInf;
	}
	
	
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
	
	public static Hashtable<Integer, String> getClient(Connection connection) {
	    Hashtable<Integer, String> htConsumer = new Hashtable<Integer, String>();

		try {
			Statement state = connection.createStatement();

			ResultSet rs = state.executeQuery("SELECT * from consumer");
			while(rs.next()){
				htConsumer.put(rs.getInt("id_Consumer"), rs.getString("name"));
			}

		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return htConsumer;
	}
	
	public static Hashtable<Integer, String> getLoanType(Connection connection) {
	    Hashtable<Integer, String> htLoanType = new Hashtable<Integer, String>();

		try {
			Statement state = connection.createStatement();

			ResultSet rs = state.executeQuery("SELECT * from loanType");
			while(rs.next()){
				htLoanType.put(rs.getInt("id_loanType"), rs.getString("name"));
			}

		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return htLoanType;
	}

	
	//Return the number of simulation
	public static int nbSimulation(Connection connection, boolean date){
		int nb=0;
		ResultSet rs;
		String s = HandleClient.getDate("", "");
		String sql;
		try {
			Statement state = connection.createStatement();
			if(date==true){
				sql = "Select * from simulation "+s;
			}
			else
				sql = "Select * from simulation";

			rs = state.executeQuery(sql);

			while(rs.next()){
				nb+=1;
			}
		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
	}
	
	//Returns the number of simulation group by consumer
	public static int nbSimulationPerConsumer(int id_user, Connection connection){
		int nb=0;
		
		try {
			Statement state = connection.createStatement();
			String sql = "SELECT * from simulation where id_user = \""+id_user+"\"";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				nb+=1;
			}

		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
	}
	
	
	//Returns the number of simulation group by date
	public static int nbSimulationPerDate(Connection connection, String dateInf, String dateSup){
		int nb=0;
		String sql = " Select * from Simulation where simulationDate >= \""+dateInf+"\" and simulationDate <= \""+dateSup+"\"";
		Statement state;
		try {
			state = connection.createStatement();
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				nb+=1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		 
		return nb;
	}
	
	
	//Returns the number of simulation group by consumer
	public static int nbSimulationPerConsumer(Connection connection, int idConsumer){
		int nb=0;
		String sql = " Select * from Simulation s, User u where u.id_user=s.id_user and id_Consumer=\""+idConsumer+"\"";
		Statement state;
		try {
			state = connection.createStatement();
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				nb+=1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		 
		return nb;
	}
	
	
	//Return the number of loan contracted 
	public static int nbLoan(Connection connection, boolean date){
		int nb=0;
		String s = HandleClient.getDate("", "");
		ResultSet rs;
		String sql = null;
		try {
			Statement state = connection.createStatement();

			if(date==true){
				sql = "Select * from loan l, simulation s "+s+" and l.id_simulation=s.id_simulation";
			}
			else
				sql = "Select * from loan natural join simulation";
			
			rs = state.executeQuery(sql);
			
			while(rs.next()){
				nb+=1;
			}
		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
		
	}
	
	//Returns the average duration of loans
	public static float avgDurationLoan(Connection connection, boolean date){
		float nb=0;
		String s = HandleClient.getDate("", "");
		ResultSet rs;
		String sql = null;
		try {
			Statement state = connection.createStatement();
			
			if(date==true)
				sql = "SELECT AVG(duration) FROM simulation "+s;
			else
				sql = "SELECT AVG(duration) FROM simulation";
			
			rs = state.executeQuery(sql);
			rs.next();
			nb = rs.getFloat(1);

		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
	}
	
	
	//Returns the average amount of loans
	public static float avgAmountLoan(Connection connection, boolean date){
		float nb=0;
		String s = HandleClient.getDate("", "");
		ResultSet rs;
		String sql;
		try {
			Statement state = connection.createStatement();

			if(date==true)
				sql = "SELECT AVG(amount) FROM simulation "+s;
			else
				sql = "SELECT AVG(amount) FROM simulation";
			
			
			rs = state.executeQuery(sql);
			rs.next();
			nb = rs.getFloat(1);

		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
