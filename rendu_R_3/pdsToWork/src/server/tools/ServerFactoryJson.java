package server.tools;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

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
	
	//Json send by the server to the client 
	public static String simulationStarted(){
		StringWriter sw = new StringWriter();
		JsonGenerator jsonGen = Json.createGenerator(sw);
		jsonGen.writeStartObject()
				.write("request","SIMULATION")
				.write("result","OK")
				.writeEnd()
				.close();
		String jsonContent = sw.toString();
		return jsonContent;
		 
	}
}
