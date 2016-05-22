package client.beans;

import java.util.ArrayList;

public class LoanRepaymentsPlan {

	private double remaining;
	private double monthly ;
	private double amortizedCapital;
	private double interest;
	private double rate;
	private double totalCapital = 0;
	private double totalInterest = 0;
	private double totalMonthly = 0;
	private double insurance;
	private double duration;
	private double capital;
	private ReimbursementType reimbursementType;
	private Frequency frequency;
	
	private ArrayList<TempoData> data = new ArrayList<TempoData>();

	
	public LoanRepaymentsPlan(double amount, double rate, double duration, double insurance, int reimbursement_Type, Frequency frequency) {
		// TODO Auto-generated constructor stub
		this.capital = amount;
		this.insurance = insurance;
		this.duration = duration;
		this.frequency = frequency;
		this.reimbursementType = intToReimbursementType(reimbursement_Type);
		remaining = amount;
		this.rate = rate;
	}

	public void fillArray(){
		
		double localDuration = duration;
		double localRate = rate;
		switch(frequency){
		case Yearly:	localDuration = duration/12;
						break;
		case Monthly:	localRate = (rate/12);
						break;
		}
		
		for(int currentPeriode = 1; currentPeriode<=localDuration; currentPeriode++){
			
			/***************** choose the good method of calculate *****************************/
			switch(reimbursementType){
				case In_Fine :	methodIn_Fine(currentPeriode, localRate);
								break;
				case Constant_Amortization :	methodConstantAmortization(currentPeriode, localRate);
												break;
				case Constant_Annuity:	methodConstant_Annuity(currentPeriode, localRate);
										break;
			}
			
			data.add(new TempoData(Integer.toString(currentPeriode), monthly, amortizedCapital, interest , this.insurance, remaining));

			totalCapital = totalCapital + amortizedCapital;
			totalInterest = totalInterest + interest;
			totalMonthly = totalMonthly + monthly;
			
		}
		double totalInsurance = insurance*localDuration;
		data.add(new TempoData("Total", keepNoneNumbersAfterComma(totalMonthly), keepNoneNumbersAfterComma(totalCapital), keepNoneNumbersAfterComma(totalInterest), totalInsurance, 0 ));
	}
	
	
	
	public ArrayList<TempoData> getData() {
		return data;
	}

	/************ Reimbursement method ****************/

	private void methodIn_Fine(int currentPeriode, double rate){
		interest = keep2numbersAfterComma(capital*rate);
		amortizedCapital = (duration == currentPeriode)? capital : 0;
		monthly = keep2numbersAfterComma(amortizedCapital + interest + insurance);
		remaining =(duration == currentPeriode)? 0: capital;
	}
	private void methodConstantAmortization(int currentPeriode, double rate){
		interest = keep2numbersAfterComma(remaining*rate);
		amortizedCapital = keep2numbersAfterComma(capital/duration);
		monthly = keep2numbersAfterComma(amortizedCapital + interest + insurance);
		remaining =keep2numbersAfterComma(remaining-amortizedCapital);
	}
	
	private void methodConstant_Annuity(int currentPeriode, double rate){
		interest = keep2numbersAfterComma(remaining*rate);
		amortizedCapital = keep2numbersAfterComma(remaining*rate/(Math.pow((1 + rate),duration-(currentPeriode-1)) - 1));
		monthly = keep2numbersAfterComma(amortizedCapital + interest + insurance);
		remaining = keep2numbersAfterComma(remaining-amortizedCapital);
	}


	private ReimbursementType intToReimbursementType(int type){
		switch(type){
		
		case 1 :	return ReimbursementType.In_Fine;
		
		case 2 :	return ReimbursementType.Constant_Amortization;
		
		case 3 : 	return ReimbursementType.Constant_Annuity;
		
		default: 	return null;
		}
	}

	/*
	 *  Getters 
	 */
	
	public double getRemaining() {
		return remaining;
	}

	public double getMonthly() {
		return monthly;
	}

	public double getAmortizedCapital() {
		return amortizedCapital;
	}

	public double getInterest() {
		return interest;
	}

	public double getRate() {
		return rate;
	}

	public double getTotalCapital() {
		return totalCapital;
	}

	public double getTotalInterest() {
		return totalInterest;
	}

	public double getTotalMonthly() {
		return totalMonthly;
	}

	public double getInsurance() {
		return insurance;
	}

	public double getDuration() {
		return duration;
	}

	public double getCapital() {
		return capital;
	}

	public ReimbursementType getReimbursementType() {
		return reimbursementType;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setData(ArrayList<TempoData> data) {
		this.data = data;
	}
	
	/*
	 * some utilities
	 */
	private double keep2numbersAfterComma(double number){
		//round up number
		number = Math.round(number * Math.pow(10,1)) / Math.pow(10,1);
		//keep 2 numbers after comma
		return (double)((int)(number*100))/100;
	}
	private double keepNoneNumbersAfterComma(double number){
		//round up number
		return Math.round(number * Math.pow(10,0)) / Math.pow(10,0);
	}
}
