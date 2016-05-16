package client.beans;

public class TempoData {
	String numberOfDueDate;
	double monthly;
	double interest;
	double insurance;
	double remaining;
	double amortizedCapital;
	
	public TempoData(String numberOfDueDate, double monthly, double amortizedCapital, double interest, double insurance, double remaining ) {
		this.numberOfDueDate = numberOfDueDate;
		this.monthly = monthly; 
		this.insurance = insurance;
		this.interest = interest;
		this.remaining = remaining;
		this.amortizedCapital = amortizedCapital;
	}

	public String getNumberOfDueDate() {
		return numberOfDueDate;
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

	public double getAmortizedCapital() {
		return amortizedCapital;
	}

	
	
}
