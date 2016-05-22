package client.beans;

public class ClientSimulation {
	double amount; 
	int duration; 
	String loanType;
	String freqency;
	

	public double getAmount() {
		return amount;
	}
	public int getDuration() {
		return duration;
	}
	public String getLoan_type() {
		return loanType;
	}
	public String getFreqency() {
		return freqency;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public void setFreqency(String freqency) {
		this.freqency = freqency;
	}
	
}
