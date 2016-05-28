package client.beans;

public class TemporaryMain {

	public static void main(String[] args) {
		/*
		 *  --------------- Parameters ----------------------------
		 *  amount: 10 000 ; 
		 *  rate by year: 5% ;
		 *  duration: 10 months ; 
		 *  insurance: 30 euro by month
		 *  
		 */
		double amount = 10000;
		double rateByYear = 0.05;
		double duration = 10;
		double insuranceByMonth = 30;
		Frequency frequency = Frequency.Monthly;
		int reimbursementType = 3;
		
		new Pipe(amount, rateByYear, duration, insuranceByMonth, frequency, reimbursementType);
		
	}

}
