package client.tools;

import org.json.JSONException;
import org.json.JSONObject;


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
	
	
	public static int resultQueryAccessSimulation(String jsonContent){
		JSONObject jObject ;
		int nb = 0;;
		try {
			jObject = new JSONObject(jsonContent);
			nb = jObject.getInt("result");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Retrun a number
		return nb;
	}
}
