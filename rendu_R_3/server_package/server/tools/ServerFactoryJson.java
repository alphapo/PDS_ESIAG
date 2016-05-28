package tools;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

import beans.Duration;

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

	public static String simulationDenied(){
		StringWriter sw = new StringWriter();
		JsonGenerator jsonGen = Json.createGenerator(sw);
		jsonGen.writeStartObject()
		.write("request","SIMULATION")
		.write("result","KO")
		.writeEnd()
		.close();
		String jsonContent = sw.toString();
		return jsonContent;

	}

	
	public static String dataDuration(Duration duration){
		StringWriter sw = new StringWriter();
		JsonGenerator jsonGen = Json.createGenerator(sw);
		jsonGen.writeStartObject()
		.write("request","dataDuration")
		.write("year",duration.getMonth(0))
		.write("month",duration.getMonth(0))
		.writeEnd()
		.close();
		String jsonContent = sw.toString();
		return jsonContent;

	}
	

	

}
