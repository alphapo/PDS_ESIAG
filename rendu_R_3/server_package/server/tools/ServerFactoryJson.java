package server.tools;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

//Send JSon to client


public class ServerFactoryJson {
	public static String connectionDenied(){
		StringWriter sw = new StringWriter();
		JsonGenerator jsonGen = Json.createGenerator(sw);
		jsonGen.writeStartObject()
				.write("request","AUTH")
				.write("result","KO")
				.writeEnd()
				.close();
		String jsonContent = sw.toString();
		return jsonContent;
	}
	public static String connectionAccepted(){
		StringWriter sw = new StringWriter();
		JsonGenerator jsonGen = Json.createGenerator(sw);
		jsonGen.writeStartObject()
				.write("request","AUTH")
				.write("result","OK")
				.writeEnd()
				.close();
		String jsonContent = sw.toString();
		return jsonContent;
	}
	

	
}
