package ServerMetier;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pds.Server;
import pds.ServerMetier;

public class ServerMetierTest {
	private Server s;
	private ServerMetier sm;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		s = new Server();
		sm = new ServerMetier();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
//	user usman already exists
	public void testVerifID() {
		System.out.println("---------------------Test verifID--------------------");

		assertEquals(true, sm.verifID("usman", "usman"));
	}
	@Test
	//create a consumer and check insert
	public void testCreerClient(){
		System.out.println("---------------------Test creerClient--------------------");
		System.out.println("ID ancien client " +(sm.selectID()-1));
		sm.creerClient("un gros chat", "", "1989-02-03", "0903029022", "Rue republique", "75102", "Paris", "France", "Australien", 2);
		System.out.println("ID nouveau client " +(sm.selectID()-1));
	}
	
	@Test
	//Check if the consumer has correctly been deleted
	public void testSupprimerClient(){
		System.out.println("---------------------Test supprimerClient--------------------");
		sm.creerClient("un gros chat", "", "1989-02-03", "0903029022", "Rue republique", "75102", "Paris", "France", "Australien", 2);
		System.out.println("ID du client qui vient d être crée " +(sm.selectID()-1));
		sm.supprimerClient(sm.selectID()-1);
		System.out.println("ID dernier client "+(sm.selectID()-1));
	}
	
	


}
