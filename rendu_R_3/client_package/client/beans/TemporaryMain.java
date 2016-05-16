package client.beans;

import java.awt.BorderLayout;
import java.awt.print.PrinterException;
import java.text.MessageFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.JTextField;

public class TemporaryMain {


	public static void main(String[] args){
		JFrame jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		 *  --------------- Parameters ----------------------------
		 *  amount: 10 000 ; 
		 *  rate by year: 5% ;
		 *  duration: 10 months ; 
		 *  insurance: 30 euros by month
		 *  
		 *  
		 */
		double amount = 10000;
		double rateByYear = 0.05;
		double duration = 10;
		double insuranceByMonth = 30;
		Frequency frequency = Frequency.Monthly;
		int reimbursementType = 3;
		/*	
		 *  1 - for in fine repayments
		 *  2 - for constant amortization 
		 *  3 - for constant annuity
		 */  
		
		
		
		/******************** calculate loan repayments plan *************************/
		LoanRepaymentPlan loanRepaymentPlan = new LoanRepaymentPlan(amount, rateByYear, duration, insuranceByMonth, reimbursementType, frequency);
		loanRepaymentPlan.fillArray();
		
		/******************** print graphic loan repayments plan *********************/
		JTable arrayFrame = new JTable(new ShowArrayRestitution(loanRepaymentPlan.getData()));
		
		jframe.getContentPane().add(new JScrollPane(arrayFrame), BorderLayout.CENTER);

		jframe.pack();
		jframe.setVisible(true);
		
		/******************** print graphic line *********************/
		new ShowGraphicLine(loanRepaymentPlan.getData(), "Graphique" ,"Restitution graphique de la simulation"); 
		
		/******************** print graphic bar *********************/
		new ShowGraphicBar3D(loanRepaymentPlan.getData(), "Graphique" ,"Restitution graphique de la simulation");
		
		/******************** Download PDF version  *********************/
		Download.pdfVersion(loanRepaymentPlan.getData(), amount, rateByYear, duration, insuranceByMonth);
		
		/******************** print loan repayments plan with physical printer *********************/		
		try {
			arrayFrame.print(PrintMode.NORMAL, new MessageFormat("Tableau d''amortissement"), new MessageFormat("Page {0}"));
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
