package features;

import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import features.Features;
import server.beans.Authentification;
import server.tools.ConnectionPool;


//This class tests different sql statements
public class FeaturesTest {
	private ConnectionPool conn;
	@Before
	public void setUp() throws Exception {
		conn = new ConnectionPool();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	//Check if the user exists or no in the dataBase
	public void testUserExist() {
		Authentification auth = new Authentification();
		auth.setLogin("usman");
		auth.setPassword("usman");
		assertEquals(true,Features.userExist(auth, conn.getConnectionFromPool()));
	}
	
	@Test
	//Check if the user exists or no in the dataBase
	public void TestUserExist() {
		Authentification auth = new Authentification();
		auth.setLogin("ceciestuntest");
		auth.setPassword("usman");
		assertEquals(false,Features.userExist(auth, conn.getConnectionFromPool()));
	}
	
//	@Test
//	//return the number of loan contracted
////	public void testNbLoanContracted() {
////
////		assertEquals(5,f.nbLoanContracted(conn.getConnectionFromPool()));
////	}
	
//	@Test
//	//return the number of loan contracted without date
//	public void testNbSimulation() {
//
//		assertEquals(12,Features.nbSimulation(conn.getConnectionFromPool(), false));
//	}
	
	
	@Test
	public void testGetClient() {
		//key is id_Consumer and value = name
		Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
		ht = Features.getClient(conn.getConnectionFromPool());
		//First consumer is butt
		assertEquals("butt",ht.get(1));
	}
	
//	//returns average number of loan with date
//	@Test
//	public void testAvgDurationLoan() {
//		assertEquals(7.5, Features.avgDurationLoan(conn.getConnectionFromPool(),false), 0.0f);
//
//	}
	
	//returns average number of loan without date
	@Test
	public void testAvgDurationLoanBis() {
		assertEquals(16, Features.avgDurationLoan(conn.getConnectionFromPool(),false, true), 0.0f);

	}
	
	//returns average number of loan without date
	@Test
	public void testNbInterest() {
		assertEquals(16, Features.nbInterest(conn.getConnectionFromPool(),false, false), 0.0f);

	}
	
	
//	
//	@Test
//	public void testAvgAmount() {
//		assertEquals(9.5, Features.avgAmount(conn.getConnectionFromPool()), 0.0f);
//	}
	


}
