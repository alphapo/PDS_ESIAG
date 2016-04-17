package features;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import features.Features;
import server.beans.Authentification;
import server.tools.ConnectionPool;


//This class tests different sql statements
public class FeaturesTest {
	private Features f;
	private ConnectionPool conn;
	@Before
	public void setUp() throws Exception {
		f = new Features();
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
		assertEquals(true,f.userExist(auth, conn.getConnectionFromPool()));
	}
	
	@Test
	//Check if the user exists or no in the dataBase
	public void TestUserExist() {
		Authentification auth = new Authentification();
		auth.setLogin("ceciestuntest");
		auth.setPassword("usman");
		assertEquals(false,f.userExist(auth, conn.getConnectionFromPool()));
	}
	
	@Test
	//return the number of loan contracted
	public void testNbLoanContracted() {

		assertEquals(5,f.nbLoanContracted(conn.getConnectionFromPool()));
	}
	
	@Test
	//return the number of loan contracted
	public void testNbSimulation() {

		assertEquals(3,f.nbSimulation(conn.getConnectionFromPool()));
	}
	


}
