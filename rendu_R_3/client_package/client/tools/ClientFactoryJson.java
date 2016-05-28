package tools;

import java.io.BufferedReader;
import java.io.StringWriter;
/*
 *
 */
import java.util.*;
import javax.json.Json;
import javax.json.stream.JsonGenerator;

import beans.Client;
import beans.ClientSimulation;
import beans.Duration;
import client.DurationInterface;


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
	public static String makeJSONsimulationForClient(ClientSimulation clientSimulation){
		
		
		System.out.println("id_user:"+ clientSimulation.getId_user());
		System.out.println("day:"+ clientSimulation.getDay());
		System.out.println("status:"+ clientSimulation.getStatus());
		System.out.println("duration: "+clientSimulation.getDuration());
		System.out.println("amount: "+ clientSimulation.getAmount());
		System.out.println("rate: "+clientSimulation.getRate());
		System.out.println("id_loanType: "+ clientSimulation.getId_loanType());
		System.out.println("id_name: "+ clientSimulation.getId_name());
		
		
		
		StringWriter sw = new StringWriter();
		JsonGenerator jsonGen = Json.createGenerator(sw);
		jsonGen.writeStartObject()
				.write("request","ADD_CLIENT_SIMULATION")
				.write("id_user",clientSimulation.getId_user())
				.write("day", clientSimulation.getDay())
				.write("status",clientSimulation.getStatus())
				.write("duration",clientSimulation.getDuration())
				.write("amount",clientSimulation.getAmount())
				.write("rate",clientSimulation.getRate())
				.write("id_loanType",clientSimulation.getId_loanType())
				.write("id_name",clientSimulation.getId_name())
				.writeEnd()
				.close();
		String jsonContent = sw.toString();
		return jsonContent;
	}
	
	public static String makeJSONgetDurationData(){
<<<<<<< HEAD
		
			StringWriter sw = new StringWriter();
			JsonGenerator jsonGen = Json.createGenerator(sw);
			jsonGen.writeStartObject()
					.write("request","GET_DURATIONDATA")
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
				
				.writeEnd()
				.close();
		String jsonContent = sw.toString();
		return jsonContent;
	}
=======
		
			StringWriter sw = new StringWriter();
			JsonGenerator jsonGen = Json.createGenerator(sw);
			jsonGen.writeStartObject()
					.write("request","GET_DURATIONDATA")
					.writeEnd()
					.close();
			String jsonContent = sw.toString();
			return jsonContent;
		}
		
	
>>>>>>> 7a6cb1ff9973eb2df612a2cd72a4103ee7d47dc0
}
