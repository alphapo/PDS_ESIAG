package client.tools;

import java.io.BufferedReader;
import java.io.StringWriter;
/*
 *
 */
import java.util.*;
import javax.json.Json;
import javax.json.stream.JsonGenerator;

import client.beans.Client;
import client.beans.ClientSimulation;


public class ClientFactoryJson {
	public static String makeJSONauthentification(String login, String password){
		StringWriter sw = new StringWriter();
		JsonGenerator jsonGen = Json.createGenerator(sw);
		jsonGen.writeStartObject()
				.write("request","AUTH")
				.write("login",login)
				.write("password", password)
				.writeEnd()
				.close();
		String jsonContent = sw.toString();
		return jsonContent;
	}
	
	public static String makeJSONaddClient(Client client){
		StringWriter sw = new StringWriter();
		JsonGenerator jsonGen = Json.createGenerator(sw);
		jsonGen.writeStartObject()
				.write("request","ADD_CLIENT")
				.write("name", client.getName())
				.write("firstName", client.getFirstName())
				.writeEnd()
				.close();
		String jsonContent = sw.toString();
		return jsonContent;
	}
	
	/*
	 * this Json inserts data in the database
	 */
	public static String makeJSONsimulationForClient(ClientSimulation simulationData){
		
	// Cette methode n'est pas terminée, il va falloir créer le methode 
		Date date = new Date();
		StringWriter sw = new StringWriter();
		JsonGenerator jsonGen = Json.createGenerator(sw);
		jsonGen.writeStartObject()
				.write("request","ADD_CLIENT_SIMULATION")
				/*
				.write("id_user", getUser())
				.write("simulationDate", getDate())
				.write("status", getStatus())
				*/
				.write("duration",simulationData.getDuration())
				.write("amount",simulationData.getAmount())
				/*.write("interest",getInterest())
				.write("duration",getIdLoanType())
				*/
				.writeEnd()
				.close();
		String jsonContent = sw.toString();
		return jsonContent;
	}
	
	/*
	 * Json from ClientAdvisor 
	 */
	public static String makeJSONsimulationForClientAdvisor(ClientSimulation simulationData){
		
	// Cette methode n'est pas terminée, il va falloir créer le methode. 
	/*
	 * ce Json prend plus de paramètres que celui du client
	 */
		Date date = new Date();
		StringWriter sw = new StringWriter();
		JsonGenerator jsonGen = Json.createGenerator(sw);
		jsonGen.writeStartObject()
				.write("request","ADD_ADVISOR_SIMULATION")
				/*
				.write("id_user", getUser())
				.write("simulationDate", getDate())
				.write("status", getStatus())
				.write("freqency", setFreqency()
				*/
				.write("duration",simulationData.getDuration())
				.write("amount",simulationData.getAmount())
				/*.write("interest",getInterest())
				.write("duration",getIdLoanType())
				
				*/
				.write("frequency",simulationData.getFreqency())
				
				
				.writeEnd()
				.close();
		String jsonContent = sw.toString();
		return jsonContent;
	}
}
