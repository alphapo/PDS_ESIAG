package features;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import java.sql.PreparedStatement;

import server.HandleClient;
import server.beans.Authentification;
import server.beans.Client;
import server.beans.*;
import server.beans.ClientSimulation;
import server.beans.Duration;

public class Features {
	static String dateInf;
	static String dateSup;
	static String agency;
	static int id_agency;
	

	
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

			ResultSet rs = state.executeQuery("SELECT * from user NATURAL join consumer");
			while(rs.next()){
				htConsumer.put(rs.getInt("id_user"), rs.getString("name"));
			}

		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return htConsumer;
	}
	
	//Returns the average duration of loans group by loantype
	public static String getAgency(Connection connection){
		String s = HandleClient.getLoginUser();
		ResultSet rs;
		String sql;
		String agency ="";
		try {

			Statement state = connection.createStatement();
			sql = "SELECT city FROM agency where id_agency=\""+Features.id_agency+"\"";
			rs = state.executeQuery(sql);
			rs.next();
			agency = rs.getString(1);
		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return agency;
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
		String s = HandleClient.getDate();
		int id = HandleClient.getLoanType();

		ResultSet rs;
		String sql = null;
		try {
			Statement state = connection.createStatement();
			//Each condition answers each ckeckBox selection
			if(date==true && loanTypeId==false){
				sql = "Select count(*) from simulation s, user u, consumer c where s.id_user=u.id_user and u.id_consumer=c.id_consumer and "+s+" and id_agency=\""+Features.id_agency+"\"";
			}
			else if(date ==true && loanTypeId==true ){
				sql = "Select count(*) from simulation s, user u, consumer c where s.id_user=u.id_user and u.id_consumer=c.id_consumer and "+s+" and id_loanType=\""+id+"\" and id_agency=\""+Features.id_agency+"\"";
			}
			else if(date ==false && loanTypeId==true ){
				sql = "Select count(*) from simulation s, user u, consumer c where s.id_user=u.id_user and u.id_consumer=c.id_consumer and id_agency=\""+Features.id_agency+"\" and s.id_loanType=\""+id+"\"";
			}
			else
				sql = "Select count(*) from simulation s, user u, consumer c where s.id_user=u.id_user and u.id_consumer=c.id_consumer and id_Agency=\""+Features.id_agency+"\"";


			System.out.println(sql);
			
			rs = state.executeQuery(sql);
			
			while(rs.next()){
				nb=rs.getInt(1);
			}
		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
	}
	
	
	//Return the number of simulation
	//Return the number of simulation
	public static int nbConsumer(Connection connection, boolean date, boolean gender){
		int nb=0;
		ResultSet rs;
		String sql = null;
		try {
			Statement state = connection.createStatement();
			
			if(gender==true)
				sql = "Select count(*) from consumer where id_Agency=\""+Features.id_agency+"\" and gender=\""+HandleClient.getGender()+"\"";
			else
				sql = "Select count(*) from consumer where id_Agency=\""+Features.id_agency+"\"";
			
			System.out.println(sql);
			rs = state.executeQuery(sql);
			
			while(rs.next()){
				nb=rs.getInt(1);
			}
		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
	}
	
	//Return the number of simulation
	public static int nbUser(Connection connection, boolean date, boolean gender){
		int nb=0;
		String s = HandleClient.getDate();
		int id = HandleClient.getLoanType();

		ResultSet rs;
		String sql = null;
		try {
			Statement state = connection.createStatement();
			if(gender==true){
				sql = "Select count(*) from user u, consumer c where u.id_consumer=c.id_consumer and id_Agency=\""+Features.id_agency+"\" and gender=\""+HandleClient.getGender()+"\"";
			}
			else
				sql = "Select count(*) from user u, consumer c where u.id_consumer=c.id_consumer and id_Agency=\""+Features.id_agency+"\"";
			
			System.out.println(sql);
			
			rs = state.executeQuery(sql);
			
			while(rs.next()){
				nb=rs.getInt(1);
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
			String sql = "SELECT * from simulation natural join user natural join consumer where id_user = \""+id_user+"\" and id_agency=\""+Features.id_agency+"\"";

			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				nb+=1;
			}

		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
	}
	
	//Returns the number of simulation group by consumer
	public static void setIdAgency(Connection connection, String login){
		int id=0;
		
		try {
			Statement state = connection.createStatement();
			String sql = "SELECT id_agency FROM user u, consumer c WHERE u.login=\""+HandleClient.getLoginUser()+"\" and u.id_Consumer=c.id_Consumer";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				id=rs.getInt(1);
			}

		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		Features.id_agency = id;
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
				sql = "Select count(*) from loan l, simulation s, user u, consumer c where l.id_simulation=s.id_simulation and s.id_user=u.id_user and u.id_consumer=c.id_consumer and "+s+" and id_agency=\""+Features.id_agency+"\"";
			}
			else if(date ==true && loanTypeId==true ){
				sql = "Select count(*) from loan l, simulation s, user u, consumer c where l.id_simulation=s.id_simulation and s.id_user=u.id_user and u.id_consumer=c.id_consumer and "+s+" and id_loanType=\""+id+"\" and id_agency=\""+Features.id_agency+"\"";
			}
			else if(date ==false && loanTypeId==true ){
				sql = "Select count(*) from loan l, simulation s, user u, consumer c where l.id_simulation=s.id_simulation and s.id_user=u.id_user and u.id_consumer=c.id_consumer and id_agency=\""+Features.id_agency+"\" and s.id_loanType=\""+id+"\"";
			}
			else
				sql = "Select count(*) from loan l, simulation s, user u, consumer c where l.id_simulation=s.id_simulation and s.id_user=u.id_user and u.id_consumer=c.id_consumer and id_Agency=\""+Features.id_agency+"\"";


			System.out.println(sql);
			
			rs = state.executeQuery(sql);
			
			while(rs.next()){
				nb=rs.getInt(1);
			}
		}catch ( SQLException ex){
			ex.printStackTrace();
			System.out.println("Erreur requete !");
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
				sql = "Select AVG(duration) from simulation s, user u, consumer c where s.id_user=u.id_user and u.id_consumer=c.id_consumer and "+s+" and id_loanType=\""+id+"\" and id_agency=\""+Features.id_agency+"\"";
			else
				sql = "Select AVG(duration) from simulation s, user u, consumer c where s.id_user=u.id_user and u.id_consumer=c.id_consumer and id_loanType=\""+id+"\" and id_agency=\""+Features.id_agency+"\"";
			rs = state.executeQuery(sql);
			rs.next();
			nb = rs.getFloat(1);
			
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
				sql = "Select AVG(amount) from simulation s, user u, consumer c where s.id_user=u.id_user and u.id_consumer=c.id_consumer and "+s+" and id_loanType=\""+id+"\" and id_agency=\""+Features.id_agency+"\"";
			else
				sql = "Select AVG(amount) from simulation s, user u, consumer c where s.id_user=u.id_user and u.id_consumer=c.id_consumer and id_loanType=\""+id+"\" and id_agency=\""+Features.id_agency+"\"";
			
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
		double rate, rateByMonth, interest, remaining, capital;
		double totalInterest;
		float percent, finalInterest;
		ResultSet rs;
		String sql;
		try {
			Statement state = connection.createStatement();

			if(date==true)
				sql = "Select * from simulation s, user u, consumer c where s.id_user=u.id_user and u.id_consumer=c.id_consumer and "+s+" and id_loanType=\""+id+"\" and id_agency=\""+Features.id_agency+"\"";
			else
				sql = "Select * from simulation s, user u, consumer c where s.id_user=u.id_user and u.id_consumer=c.id_consumer and id_loanType=\""+id+"\" and id_agency=\""+Features.id_agency+"\"";

			rs = state.executeQuery(sql);

			while(rs.next()){
				totalInterest = 0;
				duration = (rs.getInt("duration"))*12;
				rate = rs.getFloat("interestRate")*(0.01f);
				remaining = ((double)rs.getInt("amount"));
				rateByMonth = (rate/12);

				for(int i = 1; i<=duration; i++){
					interest = round2Numbers(remaining*rateByMonth);
					capital = round2Numbers(remaining*rateByMonth/(Math.pow((1 + rateByMonth),duration-(i-1)) - 1));
					remaining = round2Numbers(remaining-capital);

					totalInterest = totalInterest + interest;
				}
				nb = (float)(nb+totalInterest);
			}

		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
	
	}
	
	//Returns some interest
	public static float maxRate(Connection connection, boolean date, boolean loanTypeId){
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
				sql = "Select max(interestRate) from simulation s, user u, consumer c where s.id_user=u.id_user and u.id_consumer=c.id_consumer and "+s+" and id_loanType=\""+id+"\" and id_agency=\""+Features.id_agency+"\"";
			else
				sql = "Select max(interestRate) from simulation s, user u, consumer c where s.id_user=u.id_user and u.id_consumer=c.id_consumer and id_loanType=\""+id+"\" and id_agency=\""+Features.id_agency+"\"";
			
			rs = state.executeQuery(sql);
			rs.next();
			nb = rs.getFloat(1);
			
		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
	}

	
	public static float minRate(Connection connection, boolean date, boolean loanTypeId){
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
				sql = "Select MIN(interestRate) from simulation s, user u, consumer c where s.id_user=u.id_user and u.id_consumer=c.id_consumer and "+s+" and id_loanType=\""+id+"\" and id_agency=\""+Features.id_agency+"\"";
			else
				sql = "Select MIN(interestRate) from simulation s, user u, consumer c where s.id_user=u.id_user and u.id_consumer=c.id_consumer and id_loanType=\""+id+"\" and id_agency=\""+Features.id_agency+"\"";
			
			rs = state.executeQuery(sql);
			rs.next();
			nb = rs.getFloat(1);
			
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
	public static float avgRate(Connection connection, boolean date, boolean loanTypeId){
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
				sql = "Select AVG(interestRate) from simulation s, user u, consumer c where s.id_user=u.id_user and u.id_consumer=c.id_consumer and "+s+" and id_loanType=\""+id+"\" and id_agency=\""+Features.id_agency+"\"";
			else
				sql = "Select AVG(interestRate) from simulation s, user u, consumer c where s.id_user=u.id_user and u.id_consumer=c.id_consumer and id_loanType=\""+id+"\" and id_agency=\""+Features.id_agency+"\"";
			
			rs = state.executeQuery(sql);
			rs.next();
			nb = rs.getFloat(1);
			System.out.println("Rate max  : "+nb);
			
		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		return nb;
	}
	
	private static float round2Numbers(double number){
		//round up number
		number = Math.round(number * Math.pow(100,1)) / Math.pow(100,1);
		//keep 2 numbers after comma
		return (float)((int)(number*100))/100;
	}
	
	
	//Returns the average duration of loans group by loantype
	public static void sendRate(Connection connection, double rate, int idLoanType){
		int rs;
		String sql = null;
		try {
			Statement state = connection.createStatement();
			
			 sql = "UPDATE loantype SET rate=\""+rate+"\" where id_loanType=\""+idLoanType+"\"";

			rs = state.executeUpdate(sql);
			
			System.out.println("Mise a jour bdd taux Ok");

		}catch ( SQLException ex){
			ex.printStackTrace();
		}
	}
	
	
	//Returns the average duration of loans group by loantype
	public static float avgAgeConsumer(Connection connection, boolean gender){
		ResultSet rs;
		String sql;
		ArrayList<String> dateOfBirth = new ArrayList();
		try {
			Statement state = connection.createStatement();
			
			if(gender=true)
				sql = "Select * from Consumer where gender=\""+HandleClient.getGender()+"\"";
			else
				sql = "Select * from Consumer";

			rs = state.executeQuery(sql);
			
			while(rs.next()){
				dateOfBirth.add(rs.getString("dateOfBirth"));
			}
			System.out.println("Age moyen : "+Features.calculateAge(dateOfBirth));
		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		
		return Features.calculateAge(dateOfBirth);

	}
	
	
	public static float calculateAge(ArrayList<String> list){
		int it=0;
		int ageTemp=0;
		Date d;
		float avgAge = 0;
		DateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<Integer> ageList = new ArrayList();
		while(it<list.size()){
			Date birthDateInit;
			try {
				birthDateInit = (Date)format.parse(list.get(it));

			Calendar birthDate = Calendar.getInstance();
			Calendar CurrentDate=Calendar.getInstance();
			
			birthDate.setTime(birthDateInit);
			
			CurrentDate.setTime(new Date());
			ageTemp= CurrentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
			ageList.add(ageTemp);
			it++;
			
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
			for(int i = 0 ; i<ageList.size() ; i++){
				avgAge=avgAge+ageList.get(i);
			}
			System.out.println(avgAge);
			return (avgAge/ageList.size());	
	}
	
		public static JComboBox selectLoantypeBox(Connection conn)
	{
		JComboBox c = new JComboBox();
		//Création d'un objet Statement
		PreparedStatement state = null;
		//L'objet ResultSet contient le résultat de la requête SQL
		//ResultSet result = null;
		ResultSet result = null;
		//requete à executer
		String req1 ="SELECT name, id_loantype FROM loantype";
		try {
			state = conn.prepareStatement(req1);
			result = state.executeQuery();
			c.removeAllItems();
			c.addItem("");
			while (result.next())
			{
				//comboBox.addItem(result.getInt("id_consumer"));
				c.addItem(result.getString("name"));
			}

		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return c;
	}
	
	public static JComboBox selectConsumerBox(Connection conn)
	{
		JComboBox c = new JComboBox();
		//Création d'un objet Statement
		PreparedStatement state = null;
		//L'objet ResultSet contient le résultat de la requête SQL
		//ResultSet result = null;
		ResultSet result = null;
		//requete à executer
		String req1 ="SELECT id_COnsumer, name, firstname, dateOfBirth FROM Consumer";
		
		HashMap<Integer, String> hmap = new HashMap<Integer, String>();
		try {
			state = conn.prepareStatement(req1);
			result = state.executeQuery();
			c.removeAllItems();
			//c.addItem("");
			while (result.next())
			{
				//comboBox.addItem(result.getInt("id_consumer"));
				c.addItem(result.getString("name") +" "+ result.getString("firstname")+" "+ result.getString("dateOfBirth"));
				
			}

		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return c;
	}
	
	public static HashMap selectConsumerBox2(Connection conn)
	{
		//Création d'un objet Statement
		PreparedStatement state = null;
		//L'objet ResultSet contient le résultat de la requête SQL
		//ResultSet result = null;
		ResultSet result = null;
		//requete à executer
		String req1 ="SELECT id_COnsumer, name, firstname, dateOfBirth FROM Consumer";
		String concat = null;
		HashMap<Integer, String> hmap = new HashMap<Integer, String>();
		try {
			state = conn.prepareStatement(req1);
			result = state.executeQuery();
			while (result.next())
			{
				//comboBox.addItem(result.getInt("id_consumer"));
				//c.addItem(result.getString("name") +" "+ result.getString("firstname")+" "+ result.getString("dateOfBirth"));
				concat = result.getString("name") +" "+ result.getString("firstname")+" "+ result.getString("dateOfBirth");
				hmap.put(result.getInt("id_consumer"),concat);
			}

		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return hmap;
	}
	
	public static DefaultTableModel  selectSimulation(Connection conn, int id_user, int id_loantype)
	{
		DefaultTableModel  t = null ;
		//Création d'un objet Statement
		PreparedStatement state = null;
		//L'objet ResultSet contient le résultat de la requête SQL
		//ResultSet result = null;
		ResultSet result = null;
		//requete à executer
//		String req1 ="SELECT id_simulation, duration, amount, interestRate, name FROM Simulation WHERE id_user = 1";
		String req1 ="SELECT id_simulation, duration, amount, interestRate, name FROM Simulation WHERE id_user = ? and id_loanType = ?";

		//creation des objets
		String[] columnNames = {"SimulationNumber", "name", "duration", "amount", "interestRate"};
		int i=0, j =0;
		int number;
		String name ;
		int duration;
		int amount;
		double interest;
		Object[][] data = new Object[10][5];
		System.out.println(id_user +" "+  id_loantype);
		try {
			state = conn.prepareStatement(req1);
			state.setInt(1, id_user);
			state.setInt(2, id_loantype);
			result = state.executeQuery();

			while (result.next())
			{
//				System.out.println("hello");
				number = result.getInt("id_simulation");
//				System.out.println(number);
				name = result.getString("name");
//				System.out.println(name);
				duration = result.getInt("duration");
//				System.out.println(duration);
				amount = result.getInt("amount");
//				System.out.println(amount);
				interest = result.getInt("interestRate");
//				System.out.println(interest);
				
				data[j][i] = number;
				data[j][i+1] = name;
				data[j][i+2] = duration;
				data[j][i+3] = amount;
				data[j][i+4] = interest;
				j++;
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println("Requete sql !");
		
		return t = new DefaultTableModel (data, columnNames);
	}
	
}
