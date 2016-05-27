package client;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Statement;

public class RateDetermination {

	int duration = 0;    // Here we must return the loan term
	int age = 0;      // Here we must return to the age of the borrower
	boolean health;	  // Here it must be said if the borrower is in good health or not
	boolean loanhist;  // Here it must be said if the borrower already had a loan or not
	double calcrate ;
	//double Fixedrate = 2;
	//double Conso;
	//double Immo;
	double newRate = 0;
	int typePret=0;
	
	public RateDetermination(int duration, int age, boolean health, boolean loanhist, int typePret) {
		this.duration = duration;
		this.age = age;
		this.health = health;
		this.loanhist = loanhist;
		this.typePret=typePret;
		//Fixedrate = fixedrate;
	//	Conso = conso;
	//	Immo = immo;
		
		
	}
	
	public  double changeRate(){
		
		//double calcrate = this.Fixedrate;
		//double calcrate = 0;
		
		//if (calcrate == Conso){
		//	calcrate = 3;
		//	System.out.println("1");
	//	}
		//else {
	//		calcrate = 2; 
		//	System.out.println("2");
		//}
		
		double calcrate=typePret;
		if (duration <= 10 && duration >= 2){
			calcrate -=0.025;
			System.out.println("3");
		}
		else if (duration <= 30 && duration >= 21){
			calcrate += 0.025; 
			System.out.println("4");
		}
		
		if (age <= 25 && age >= 16){
			calcrate +=0.025;
			System.out.println("5");
		}
		else if (age <= 60 && age >= 41){
			calcrate -= 0.025;
			System.out.println("6");
		}
		if (health == true){
			calcrate += 0.1; 
			System.out.println("7");
		}
		else if (health == false){
			calcrate -= 0.1; 
			System.out.println("8");
		}
		if (loanhist == true) {
			calcrate -= 0.05;
			System.out.println("9");
		}
		else if (loanhist == false){
			calcrate += 0.05;
			System.out.println("10");
		}
		
		return calcrate; // This is the new rate
		//if	
	}
		
//	public static void main(String[] args) {
	//	RateDetermination d1 =new RateDetermination(5, 55, false, true, 2);
		//System.out.println(d1.changeRate());
	//}
		
//	public static void insertnewrate (Double calcrate) {
//		//information d'accès a la BDD
//		String url = "jdbc:mysql://localhost/test_pds";
//		String login = "admin";
//		String passwd = "";
//		Connection cn =null;
//		Statement st =null;
//		try {
//			// Etape 1 : Chargement du driver
//			Class.forName("com.mysql.jdbc.Driver");
//			// Etape 2 : Récupération de la connexion
//			cn = DriverManager.getConnection(url, login, passwd);
//			// Etape 3 : Création d'un statement 
//			st = cn.createStatement();
//			String sql = "UPDATE `loantype` (`rate`) VALUES ('"+ calcrate + "')";
//			
//			// Etape 4 : exécution requête
//			st.executeUpdate(sql);
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				// Etaoe 5 : liberer ressources de la mémoire
//					cn.close();
//					st.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	
//	}
	
}
	

