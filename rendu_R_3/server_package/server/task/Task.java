package server.task;

import java.sql.Connection;
import java.util.ArrayList;

import features.Features;
import server.beans.Authentification;
import server.beans.Client;
import server.tools.ServerFactoryJson;
import server.tools.ServerParserJson;

public class Task {
	static String result = null;
	static int nb=0;
	
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
	
//	public static ArrayList getSimulationData(Connection connection){
//		return Features.getSimulationData(connection);
//		
//	}
//	
	public static String getStats(String jsonContent, Connection connection){
		int indicator = ServerParserJson.getIndicator(jsonContent);
		
		//Indicator indicates the type of indicator
		if(indicator == 1){
			result = ServerFactoryJson.simulationDataAccepted(Features.nbLoanContracted(connection));
			System.out.println("Cest 1");
		}
		else if(indicator == 2){
			result = ServerFactoryJson.simulationDataAccepted(Features.nbSimulation(connection));
			System.out.println("Cest 2");
		}
		
		
		return result;
	}
}
