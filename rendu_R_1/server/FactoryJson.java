package server;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

public class ServerFactoryJson {
	static String connectionDenied(){
		StringWriter sw = new StringWriter();
		JsonGenerator jsonGen = Json.createGenerator(sw);
		jsonGen.writeStartObject()
				.write("Message","Login or password isn't correct")
				.writeEnd()
				.close();
		String jsonContent = sw.toString();
		return jsonContent;
	}
}
