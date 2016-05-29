package features;

import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import features.Features;
import server.HandleClient;
import server.beans.Authentification;
import server.tools.ConnectionPool;
import service.InformationImpl;

/***************** Tests Features functions and statement *******************
All tests based on agencyID = 142 and idLoanType=2 and begindate = 2016-04-01 endDate=date = 2016-06-01
All tests based on a default "Jeu de données" file
*/


//This class tests different sql statements
public class FeaturesTest {
	private ConnectionPool conn;
	private InformationImpl infoImpl;
	@Before
	public void setUp() throws Exception {
		conn = new ConnectionPool();
		infoImpl = new InformationImpl();
		infoImpl.saveDate("2016-04-01", "2016-06-01");
		Features.id_agency = 142;
		infoImpl.saveLoanTypeId(2);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	//Check if the user exists or no in the dataBase
	public void testUserExist() {
		Authentification auth = new Authentification();
		auth.setLogin("JACQUIER");
		auth.setPassword("Laurent");
		assertEquals(true,Features.userExist(auth, conn.getConnectionFromPool()));
	}

	
	@Test
	//return the number of simulation with date
	public void testNbSimulation() {

		assertEquals(5,Features.nbSimulation(conn.getConnectionFromPool(), true,true));
	}
	
	@Test
	//return the number of simulation with date
	public void testNbUsers() {
		assertEquals(10,Features.nbUser(conn.getConnectionFromPool(), true,false));
	}
	
	@Test
	//return the number of simulation with gender 
	public void testNbUsersBis() {
		HandleClient.setGenderId(0);
		assertEquals(1,Features.nbUser(conn.getConnectionFromPool(), true,true));
	}
	
	@Test
	//return the number of simulation with gender
	public void testNbConsumer() {
		HandleClient.setGenderId(0);
		assertEquals(68,Features.nbConsumer(conn.getConnectionFromPool(), true,true));
	}
	
	@Test
	//return the number of simulation without date
	public void testNbSimulationBis() {

		assertEquals(11,Features.nbSimulation(conn.getConnectionFromPool(), false,true));
	}
	
	
	@Test
	public void testGetClient() {
		//key is id_Consumer and value = name
		Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
		ht = Features.getClient(conn.getConnectionFromPool());
		//First consumer is butt
		assertEquals("PIROUX",ht.get(1));
	}
	
	//returns average duration of loan with date
	@Test
	public void testAvgDurationLoan() {
		assertEquals(7.599999904632568, Features.avgDurationLoan(conn.getConnectionFromPool(),true,false), 0.0f);

	}
	
	//returns average duration of loan without date
	@Test
	public void testAvgDurationLoanBis() {
		assertEquals(10, Features.avgDurationLoan(conn.getConnectionFromPool(),false, true), 0.0f);

	}
	//returns nb interest with date
	@Test
	public void testNbInterest() {
		assertEquals(136555.875, Features.nbInterest(conn.getConnectionFromPool(),true, true), 0.0f);
	}
	
	//returns nb interest without date
	@Test
	public void testNbInterestBis() {
		assertEquals(406621.25, Features.nbInterest(conn.getConnectionFromPool(),false, true), 0.0f);
	}
	
	
	//returns the maximum rate group by loanType with
	@Test
	public void testMaxRate() {
		assertEquals(2.5, Features.maxRate(conn.getConnectionFromPool(),true, true), 0.00f);

	}
	
	//returns the minimum rate group by loanType with date
	@Test
	public void testMinRate() {
		assertEquals(1.222000002861023, Features.minRate(conn.getConnectionFromPool(),true, true), 0.00f);

	}
	
	//returns the average rate group by loanType with date
	@Test
	public void testAvgRate() {
		assertEquals(1.8444000482559204, Features.avgRate(conn.getConnectionFromPool(),true, true), 0.00f);

	}
	
	//returns the maximum rate group by loanType without date
	@Test
	public void testMaxRateBis() {
		assertEquals(3.0, Features.maxRate(conn.getConnectionFromPool(),false, true), 0.00f);
		Features.maxRate(conn.getConnectionFromPool(), false, true);

	}
//	
	//returns the agency with user parameter
	@Test
	public void testGetAgency(){
		assertEquals("Brissay-Choigny", Features.getAgency(conn.getConnectionFromPool()));

	}
	
		
	//returns the average Age consumer without gender
	@Test
	public void avgAgeConsumer(){
		assertEquals(58, Features.avgAgeConsumer(conn.getConnectionFromPool(), false),0.00f);

	}
	
	//returns the average Age consumer without gender with gender "Homme"

	@Test
	public void avgAgeConsumerBis(){
		HandleClient.setGenderId(0);
		assertEquals(58.32352828979492, Features.avgAgeConsumer(conn.getConnectionFromPool(), true),0.00f);

	}
	
	//returns the average Age consumer without gender with gender "Femme"

	@Test
	public void avgAgeConsumerBis2(){
		HandleClient.setGenderId(1);
		assertEquals(61.090362548828125, Features.avgAgeConsumer(conn.getConnectionFromPool(), true),0.00f);

	}
	
//	
//	
//	@Test
//	public void testAvgAmount() {
//		assertEquals(9.5, Features.avgAmount(conn.getConnectionFromPool()), 0.0f);
//	}
	

}
