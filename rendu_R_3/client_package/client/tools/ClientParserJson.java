package client.tools;

import java.util.Hashtable;

import org.json.JSONArray;
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
	
	
//	public static int resultQueryAccessSimulation(String jsonContent){
//		JSONObject jObject ;
//		int nb = 0;;
//		try {
//			jObject = new JSONObject(jsonContent);
//			nb = jObject.getInt("result");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// Retrun a number
//		return nb;
//	}
//	
//	public static Hashtable resultQueryAccessConsumer(String jsonContent){
//		JSONObject jObject ;
//		JSONObject ht = new JSONObject();
//		try {
//			jObject = new JSONObject(jsonContent);
//			ht = jObject.getJSONObject("result");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// Retrun a number
//		Hashtable ht1 = new Hashtable();
//		ht1.put(1, ht);
//		return ht1;
//	}
}
