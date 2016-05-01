package server.beans;

public class Simulation {

	int  amount;
	int duration;
	String loanType;
	
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	
	public int getAmount() {
		return amount;
	}
	public int getDuration() {
		return duration;
	}
	public String getLoanType() {
		return loanType;
	}
}
