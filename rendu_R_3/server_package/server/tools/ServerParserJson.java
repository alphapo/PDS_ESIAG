package server.tools;

import javax.json.Json;

import org.json.JSONException;
import org.json.JSONObject;

import server.beans.ClientSimulation;
import server.beans.Duration;
import server.beans.Authentification;
import server.beans.Client;
//import beans.Simulation;

public class ServerParserJson {
	static public Authentification getAthentification(String jsonContent){
		
		JSONObject jObject = null;
		Authentification authentification = new Authentification();
		String log = null;
		String pwd = null;
		try {
			jObject = new JSONObject(jsonContent);
			log = jObject.getString("login");
			pwd = jObject.getString("password");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		authentification.setLogin(log);
		authentification.setPassword(pwd);
		return authentification;
	}
	static public  String getRequest(String jsonContent){
		JSONObject jObject = null;
		String request = null;
		try {
			jObject = new JSONObject(jsonContent);
			request = jObject.getString("request");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return request;
	}
	static public  Client getClient(String jsonContent){
		Client client = new Client();
		JSONObject jObject = null;
		try {
			jObject = new JSONObject(jsonContent);
			client.setName(jObject.getString("name"));
			client.setFirstName(jObject.getString("firstName"));
			client.setAddress(jObject.getString("address"));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	// Read Json sent by a client after a simualtion 
	
	static public ClientSimulation getClientSimulationData(String jsonContent){
		ClientSimulation clientSimulation = new ClientSimulation();
		JSONObject jObject = null;
		try {
			jObject = new JSONObject(jsonContent);
		
			 
			clientSimulation.setId_user(jObject.getInt("id_user"));
			clientSimulation.setDay(jObject.getString("day"));
			clientSimulation.setStatus(jObject.getInt("status"));
			clientSimulation.setDuration(jObject.getDouble("duration"));
			clientSimulation.setAmount(jObject.getDouble("amount"));
			clientSimulation.setRate(jObject.getDouble("rate"));
			clientSimulation.setId_loanType(jObject.getInt("id_loanType"));
			clientSimulation.setId_name(jObject.getString("id_name"));
			
			System.out.println("id_user:"+ clientSimulation.getId_user());
			System.out.println("day:"+ clientSimulation.getDay());
			System.out.println("status:"+ clientSimulation.getStatus());
			System.out.println("duration:"+clientSimulation.getDuration());
			System.out.println("amount:"+ clientSimulation.getAmount());
			System.out.println("rate: "+clientSimulation.getRate());
			System.out.println("id_loanType"+ clientSimulation.getId_loanType());
			System.out.println("id_name"+ clientSimulation.getId_name());
			
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return clientSimulation;
	}
	
	
	
}
