package client.tools;

import java.util.ArrayList;

public class LoanRepaymentPlan {
	
	private ArrayList<TempoData> data = new ArrayList<TempoData>();
	
	public LoanRepaymentPlan(double amount, double rate, double duration, double insurance) {
		// TODO Auto-generated constructor stub
		
		double remaining = amount;
		double monthly ;
		double amortizedCapital;
		double interest;
		double rateByMonth = (rate/12);
		double totalCapital = 0;
		double totalInterest = 0;
		double totalMonthly = 0;
		
		for(int i = 1; i<=duration; i++){
			interest = keep2numbersAfterComma(remaining*rateByMonth);
			/*****************      Formula to find amortizedCapital        *******************************/
			amortizedCapital = keep2numbersAfterComma(remaining*rateByMonth/(Math.pow((1 + rateByMonth),duration-(i-1)) - 1));
			monthly = keep2numbersAfterComma(amortizedCapital + interest + insurance);
			remaining = keep2numbersAfterComma(remaining-amortizedCapital);
			
			data.add(new TempoData(Integer.toString(i), monthly, amortizedCapital, interest , insurance, remaining));
			
			totalCapital = totalCapital + amortizedCapital;
			totalInterest = totalInterest + interest;
			totalMonthly = totalMonthly + monthly;
		}
		double totalInsurance = insurance*duration;
		data.add(new TempoData("Total", keepNoneNumbersAfterComma(totalMonthly), keepNoneNumbersAfterComma(totalCapital), keepNoneNumbersAfterComma(totalInterest), totalInsurance, 0 ));
	}
	
	public ArrayList<TempoData> getData() {
		return data;
	}
	
	
	private static double keep2numbersAfterComma(double number){
		//round up number
		number = Math.round(number * Math.pow(10,1)) / Math.pow(10,1);
		//keep 2 numbers after comma
		return (double)((int)(number*100))/100;
	}
	private static double keepNoneNumbersAfterComma(double number){
		//round up number
		return Math.round(number * Math.pow(10,0)) / Math.pow(10,0);
	}
}
