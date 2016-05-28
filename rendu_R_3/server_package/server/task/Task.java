package server.task;

import java.sql.Connection;

import features.Features;
import server.beans.Authentification;
import server.beans.Client;
import server.beans.ClientSimulation;
import server.beans.Duration;
import server.tools.ServerFactoryJson;
import server.tools.ServerParserJson;

public class Task {
	static String result = null;

	public static String authentificationLaunched(String jsonContent, Connection connection){
		// we recover json data
		Authentification auth = ServerParserJson.getAthentification(jsonContent);
		if (Features.userExist(auth, connection)){
			result = ServerFactoryJson.connectionAccepted();
		}else{
			result = ServerFactoryJson.connectionDenied();
		}
		return result;
	}

	public static String addClientLaunched(String jsonContent, Connection connection) {
		// TODO Auto-generated method stub
		Client client = ServerParserJson.getClient(jsonContent);
		Features.addClient(client, connection);
		return ServerFactoryJson.connectionAccepted();
	}


	public static String simulationLaunched(String jsonContent, Connection connection) {
		// TODO Auto-generated method stub

		ClientSimulation clientSimulation= ServerParserJson.getClientSimulationData(jsonContent);
		if (Features.addSimulation(clientSimulation, connection))
			return ServerFactoryJson.simulationStarted(); 
		else 
			return ServerFactoryJson.simulationDenied();
	}
	
	public static String getDurationDataLaunched(String jsonContent,Connection connection){
		String request= ServerParserJson.getRequest(jsonContent);
		Duration duration = new Duration();
		duration = Features.getDataDuration(connection);
		return ServerFactoryJson.dataDuration(duration);
	}




}
