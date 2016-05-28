package client.tools;


import java.io.StringWriter;
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

		StringWriter sw = new StringWriter();
		JsonGenerator jsonGen = Json.createGenerator(sw);
		jsonGen.writeStartObject()
		.write("request","GET_DURATIONDATA")
		.writeEnd()
		.close();
		String jsonContent = sw.toString();
		return jsonContent;
	}

}
