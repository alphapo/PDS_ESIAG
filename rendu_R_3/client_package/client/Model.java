package client;

import beans.Client;
import tools.ClientFactoryJson;
import tools.ClientParserJson;
import tools.Communicator;

public class Model {
	
	private final static Communicator communicator = new Communicator();
	
	// return OK if user exists else KO
	public static String checkUserExist(String login, String password){
		
		String jsonContent = ClientFactoryJson.makeJSONauthentification(login, password);
		communicator.sendData(jsonContent);
		return ClientParserJson.resultQueryAccess(communicator.receiveData()); 
	
	}
	
	public static String addClient(Client client){
		return null;
	}
	
	public static String launchSimulation(){
		return null;
	}
}
