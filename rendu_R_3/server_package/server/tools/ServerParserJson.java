package server.tools;

import javax.json.Json;

import org.json.JSONException;
import org.json.JSONObject;

import client.beans.ClientSimulation;
import server.beans.Authentification;
import server.beans.Client;
import server.beans.Simulation;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}
	
	// method to recover data entered in the interface
	
	static public ClientSimulation getClientSimulationData(String jsonContent){
		ClientSimulation clientSimulation = new ClientSimulation();
		JSONObject jObject = null;
		try {
			jObject = new JSONObject(jsonContent);
			/*
			 * à Corriger une fois les methodes implémentés.
			clientSimulation.setUser(jObject.getString("id_user"));
			ClientSimulation.setSimulationDate(jObject.getString("simulationDate"));
			ClientSimulation.setStatus(jObject.getString("status"));
			*/
			
			clientSimulation.setDuration(jObject.getInt("status"));
			clientSimulation.setAmount(jObject.getInt("amount"));
			
			/*
			clientSimulation.setInterest(jObject.getInt("amount"));
			clientSimulation.setLoanType(jObject.getInt("interest"));
			*/
			clientSimulation.setFreqency(jObject.getString("frequency"));
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientSimulation;
	}
	
}
