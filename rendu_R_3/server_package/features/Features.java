package features;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.beans.Authentification;
import server.beans.Client;

public class Features {
	public static boolean userExist(Authentification authentification, Connection connection) {
		int linesNumber = 0;
		try {
			Statement state = connection.createStatement();
			String sql = "SELECT * FROM user where login = \""+authentification.getLogin()+"\" and password = \""+authentification.getPassword()+"\"";
			ResultSet result = state.executeQuery(sql);
			//we place on last line
			result.last(); 
			//we recover line number
			linesNumber = result.getRow(); 
		}catch ( SQLException ex){
			ex.printStackTrace();
		}
		if(linesNumber == 1)
			return true;
		else
			return false;
	}
	public static void addClient(Client client, Connection connection) {
		try {
			Statement state = connection.createStatement();
			// Mettre a jour le nom de la table et les 
			state.executeUpdate("INSERT INTO ....... ");
		}catch ( SQLException ex){
			ex.printStackTrace();
		}
	}
}
