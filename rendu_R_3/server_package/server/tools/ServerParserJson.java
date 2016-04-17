package server.tools;

import org.json.JSONException;
import org.json.JSONObject;

import server.beans.Authentification;
import server.beans.Client;


//Read JSon
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
	
	static public int getIndicator(String jsonContent){
		int nb = 0;
		JSONObject jObject = null;
		try {
			jObject = new JSONObject(jsonContent);
			nb = jObject.getInt("Indicator");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nb;
	}
}
