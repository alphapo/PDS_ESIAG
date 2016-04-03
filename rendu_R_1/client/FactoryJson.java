package client;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

public class ClientFactoryJson {
	public static String makeJSONauthentification(String login, String password){
		StringWriter sw = new StringWriter();
		JsonGenerator jsonGen = Json.createGenerator(sw);
		jsonGen.writeStartObject()
				.write("login",login)
				.write("password", password)
				.writeEnd()
				.close();
		String jsonContent = sw.toString();
		return jsonContent;
	}
}
