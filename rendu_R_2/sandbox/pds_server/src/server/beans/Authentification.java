package server.beans;


public class Authentification {
	String login;
	String password;
	
	public Authentification() {
		// TODO Auto-generated constructor stub
	}
	
	public Authentification(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
