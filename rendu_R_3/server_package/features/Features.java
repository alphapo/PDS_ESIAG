package features;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import beans.Authentification;
import beans.Client;
import beans.ClientSimulation;
import beans.Duration;
import server.HandleClient;

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
	
//	public static void addSimulation(Simulation simulation, Connection connection){
//		try {
//			
//			Statement state = connection.createStatement();
//			//Data insertion in the simulation table
//			state.executeQuery("INSERT INTO simulation values()");
//			
//		} catch (SQLException ex){
//			ex.printStackTrace();
//		}
//	}
	
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
	public static int nbSimulation(Connection connection, boolean date, boolean loanTypeId){
		int nb=0;
		ResultSet rs;
		String s = HandleClient.getDate();
		int id = HandleClient.getLoanType();

		String sql;
		try {
			Statement state = connection.createStatement();
			
			//Each condition answers each ckeckBox selection
			if(date==true && loanTypeId==false){
				sql = "Select * from simulation where "+s;
			}
			else if(date ==true && loanTypeId==true ){
				sql = "Select * from simulation where "+s+" and id_loanType=\""+id+"\"";
			}
			else if(date ==false && loanTypeId==true ){
				sql = "Select * from simulation where id_loanType=\""+id+"\"";
			}
			else
				sql = "Select * from loan";

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
	public static int nbLoan(Connection connection, boolean date, boolean loanTypeId){
		int nb=0;
		String s = HandleClient.getDate();
		int id = HandleClient.getLoanType();

		ResultSet rs;
		String sql = null;
		try {
			Statement state = connection.createStatement();
			//Each condition answers each ckeckBox selection
			if(date==true && loanTypeId==false){
				sql = "Select * from loan l, simulation s where "+s+" and l.id_simulation=s.id_simulation";
			}
			else if(date ==true && loanTypeId==true ){
				sql = "Select * from loan l, simulation s where "+s+" and l.id_simulation=s.id_simulation and s.id_loanType=\""+id+"\"";
			}
			else if(date ==false && loanTypeId==true ){
				sql = "Select * from loan l, simulation s where l.id_simulation=s.id_simulation and s.id_loanType=\""+id+"\"";
			}
			else
				sql = "Select * from loan natural join simulation";
			
			System.out.println("Date : "+date+" - loanTypeId : "+loanTypeId);
			System.out.println(sql);
			
			rs = state.executeQuery(sql);
			
			while(rs.next()){
				nb+=1;
			}
		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
		
	}
	
	//Returns the average duration of loans group by loantype
	public static float avgDurationLoan(Connection connection, boolean date, boolean loanTypeId){
		float nb=0;
		String s = HandleClient.getDate();
		int id = HandleClient.getLoanType();
		ResultSet rs;
		String sql = null;
		try {
			Statement state = connection.createStatement();
			
			if(date==true)
				sql = "SELECT AVG(duration) FROM simulation where "+s+" and id_loanType=\""+id+"\"";
			else
				sql = "SELECT AVG(duration) FROM simulation where id_loanType=\""+id+"\"";
			
			rs = state.executeQuery(sql);
			rs.next();
			nb = rs.getFloat(1);
			
			System.out.println("Affichae durée moyenne pret par type de pret : "+nb);

		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
	}
	
	
	//Returns the average amount of loans group by loantype
	public static float avgAmountLoan(Connection connection, boolean date, boolean loanTypeId){
		float nb=0;
		String s = HandleClient.getDate();
		int id = HandleClient.getLoanType();
		ResultSet rs;
		String sql;
		try {
			Statement state = connection.createStatement();

			if(date==true)
				sql = "SELECT AVG(amount) FROM simulation where "+s+" and id_loanType=\""+id+"\"";
			else
				sql = "SELECT AVG(amount) FROM simulation where id_loanType=\""+id+"\"";
			
			
			System.out.println("Affichae montant moyen pret par type de pret : "+nb);

			
			rs = state.executeQuery(sql);
			rs.next();
			nb = rs.getFloat(1);

		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
	}
	
	//Returns some interest
	public static float nbInterest(Connection connection, boolean date, boolean loanTypeId){
		float nb=0;
		String s = HandleClient.getDate();
		int id = HandleClient.getLoanType();
		int duration;
		float percent, interest, finalInterest;
		ResultSet rs;
		String sql;
		try {
			Statement state = connection.createStatement();

			if(date==true)
				sql = "SELECT * FROM simulation where "+s+" and id_loanType=\""+id+"\"";
			else
				sql = "SELECT * FROM simulation where id_loanType=\""+id+"\"";
		
			rs = state.executeQuery(sql);
			
			while(rs.next()){
				duration = rs.getInt("duration");
				percent = rs.getFloat("interestRate")*(0.01f);
				interest = percent*((float)rs.getInt("amount"));
				finalInterest = (float)(interest * duration);
				nb=nb+finalInterest;
			}
			
		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
	}

	
	
	public static boolean addSimulation(ClientSimulation clientSimulation, Connection connection){
		boolean status = true;
		try {
			
			Statement state = connection.createStatement();
			//Data insertion in the simulation table
			String sql = "INSERT INTO SIMULATION VALUES (DEFAULT, "+clientSimulation.getId_user()+" ,'"+clientSimulation.getDay()+"' ,"+clientSimulation.getStatus()+", "+clientSimulation.getDuration()+" , "+clientSimulation.getAmount()+" ,"+clientSimulation.getRate()+" , "+clientSimulation.getId_loanType()+" ,'"+clientSimulation.getId_name()+"' )";
			System.out.println(sql);
			state.executeUpdate(sql);
			
			
		} catch (SQLException ex){
			status = false;
			ex.printStackTrace();
		}
		return status;
	}
	public static Duration getDataDuration(Connection connection){
		Duration duration = new Duration();
		ResultSet result = null;
		try {
			
			Statement state = connection.createStatement();
			//Data insertion in the simulation table
			String sql = "SELECT * FROM DURATION";
			System.out.println(sql);
			result = state.executeQuery(sql);
			while(result.next()){
				duration.add(result.getDouble("duration"), result.getDouble("month"));
			}
		} catch (SQLException ex){
			ex.printStackTrace();
		}
		return duration;
	}
	
	
	
	
	
	
	
	
}
