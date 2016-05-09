package test2;

public class TempoData {
	String number;
	double monthly;
	double interest;
	double insurance;
	double remaining;
	double capitalAmorti;
	
	public TempoData(String number, double monthly, double capitalAmorti, double interest, double insurance, double remaining ) {
		this.number = number;
		this.monthly = monthly; 
		this.insurance = insurance;
		this.interest = interest;
		this.remaining = remaining;
		this.capitalAmorti = capitalAmorti;
	}

	public String getNumber() {
		return number;
	}

	public double getMonthly() {
		return monthly;
	}

	public double getInterest() {
		return interest;
	}

	public double getInsurance() {
		return insurance;
	}

	public double getRemaining() {
		return remaining;
	}

	public double getCapitalAmorti() {
		return capitalAmorti;
	}

	
	
}
