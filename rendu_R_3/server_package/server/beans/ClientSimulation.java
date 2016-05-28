package beans;

import java.sql.Date;

public class ClientSimulation {
	
	
	int id_user;
	String day;
	int status;
	double duration;
	double amount;
	double rate ; 
	int id_loanType;
	String id_name;
	
	
	
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public void setId_loanType(int id_loanType) {
		this.id_loanType = id_loanType;
	}
	public void setId_name(String id_name) {
		this.id_name = id_name;
	}
	public int getId_user() {
		return id_user;
	}
	public String getDay() {
		return day;
	}
	public int getStatus() {
		return status;
	}
	public double getDuration() {
		return duration;
	}
	public double getAmount() {
		return amount;
	}
	public double getRate() {
		return rate;
	}
	public int getId_loanType() {
		return id_loanType;
	}
	public String getId_name() {
		return id_name;
	}
	
}	
	
	
	