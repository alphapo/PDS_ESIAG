package client.tools;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TemporaryMain {


	public static void main(String[] args){
		JFrame jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		 *  --------------- Parameters ----------------------------
		 *  amount: 10 000 ; 
		 *  rate by year: 3,6% ;
		 *  duration: 10 months ; 
		 *  insurance: 30 euros by month
		 *  
		 *  
		 */
		
		LoanRepaymentPlan loanRepaymentPlan = new LoanRepaymentPlan(10000, 0.005, 10, 50);
		
		Printer.load(loanRepaymentPlan.getData(), 10000, 0.005, 10, 50);
		
		JTable arrayFrame = new JTable(new ShowArrayRestitution(loanRepaymentPlan.getData()));

		jframe.getContentPane().add(new JScrollPane(arrayFrame), BorderLayout.CENTER);
		jframe.pack();
		jframe.setVisible(true);
		
		
		
		new ShowGraphicLine(loanRepaymentPlan.getData(), "Graphique" ,"Restitution graphique de la simulation"); 
		
		new ShowGraphicBar3D(loanRepaymentPlan.getData(), "Graphique" ,"Restitution graphique de la simulation");
	}

}
