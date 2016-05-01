package client.tools;

import java.io.BufferedReader;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

import client.beans.Client;

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
	
//	public static String makeJSONgetSimulation(){
//		StringWriter sw = new StringWriter();
//		JsonGenerator jsonGen = Json.createGenerator(sw);
//		jsonGen.writeStartObject()
//				.write("request","SIMULATION")
//				.writeEnd()
//				.close();
//		String jsonContent = sw.toString();
//		return jsonContent;
//	}
	
//	public static String makeJSONgetSimulation(int indicator){
//		StringWriter sw = new StringWriter();
//		JsonGenerator jsonGen = Json.createGenerator(sw);
//		jsonGen.writeStartObject()
//				.write("request","SIMULATION")
//				.write("Indicator", indicator)
//				.writeEnd()
//				.close();
//		String jsonContent = sw.toString();
//		return jsonContent;
//	}
}
