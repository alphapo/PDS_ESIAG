package test2;

public class TempoData {
	int number;
	double amount;
	double rate;
	double duration;
	double monthly;
	double interest;
	double insurance;
	double remaining;
	
	public TempoData(int number, double amount, double rate, double duration, int insurance, double monthly, double remaining) {
		this.number = number;
		this.amount = amount;
		this.rate = rate;
		this.duration = duration;
		this.monthly = monthly; 
		this.insurance = insurance;
		interest = (int)(amount*rate)/duration;
		this.remaining = remaining;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getMonthly() {
		return monthly;
	}

	public void setMonthly(int monthly) {
		this.monthly = monthly;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(int interest) {
		this.interest = interest;
	}

	public double getInsurance() {
		return insurance;
	}

	public void setInsurance(int insurance) {
		this.insurance = insurance;
	}

	public double getRemaining() {
		// TODO Auto-generated method stub
		return  remaining;
	}
	
}
