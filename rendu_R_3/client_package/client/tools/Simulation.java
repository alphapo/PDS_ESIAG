package test2;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class Simulation extends AbstractTableModel {
	private final List<TempoData> data = new ArrayList<TempoData>();

	private final String[] entetes = {"Numéro", "Mensualité", "Capital Amorti","Interets","Assurance","Capital restant dû"};

	/*
	 * 
	 * Method of reimbursement: constant annual installment
	 * 
	 * 
	 */
	public Simulation(double amount, double rate, double duration, double insurance) {
		
		double remaining = amount;
		double monthly ;
		double capital;
		double interest;
		double rateByMonth = (rate/12);
		double totalCapital = 0;
		double totalInterest = 0;
		double totalMonthly = 0;
		
		for(int i = 1; i<=duration; i++){
			interest = keep2numbersAfterComma(remaining*rateByMonth);
			capital = keep2numbersAfterComma(remaining*rateByMonth/(Math.pow((1 + rateByMonth),duration-(i-1)) - 1));
			monthly = keep2numbersAfterComma(capital + interest + insurance);
			remaining = keep2numbersAfterComma(remaining-capital);
			
			data.add(new TempoData(Integer.toString(i), monthly, capital, interest , insurance, remaining));
			
			totalCapital = totalCapital + capital;
			totalInterest = totalInterest + interest;
			totalMonthly = totalMonthly + monthly;
		}
		double totalInsurance = insurance*duration;
		data.add(new TempoData("Total", keepNoneNumbersAfterComma(totalMonthly), keepNoneNumbersAfterComma(totalCapital), keepNoneNumbersAfterComma(totalInterest), totalInsurance, 0 ));
	}

	public int getRowCount() {
		return data.size();
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		 switch(columnIndex){
         case 0:
             return data.get(rowIndex).getNumber();
         case 1:
             return data.get(rowIndex).getMonthly();
         case 2:
             return data.get(rowIndex).getCapitalAmorti();
         case 3:
             return data.get(rowIndex).getInterest();
         case 4:
             return data.get(rowIndex).getInsurance();
         case 5:
             return data.get(rowIndex).getRemaining();
         default:
             return null; 
     }
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