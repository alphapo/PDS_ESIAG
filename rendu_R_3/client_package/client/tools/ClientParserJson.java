package client.tools;

import java.util.Hashtable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import client.beans.ClientSimulation;
import client.beans.Duration;


public class ClientParserJson {
	public static String resultQueryAccess(String jsonContent){
		JSONObject jObject ;
		String result = null;
		try {
			jObject = new JSONObject(jsonContent);
			result = jObject.getString("result");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return KO or OK
		return result;
	}
	
		
}
