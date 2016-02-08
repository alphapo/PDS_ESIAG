package server;

import org.json.JSONException;
import org.json.JSONObject;

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
}
