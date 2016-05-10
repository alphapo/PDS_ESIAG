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
		 * --------------- Parameters ----------------------------
		 *  amount: 10 000 ; 
		 *  rate by year: 3,6% ;
		 *  duration: 10 months ; 
		 *  insurance: 30 euros by month
		 *  
		 *  
		 */
		
		LoanRepaymentPlan loanRepaymentPlan = new LoanRepaymentPlan(10000, 0.036, 10, 30);
		
		
		JTable arrayFrame = new JTable(new ShowArrayRestitution(loanRepaymentPlan.getData()));

		jframe.getContentPane().add(new JScrollPane(arrayFrame), BorderLayout.CENTER);
		jframe.pack();
		jframe.setVisible(true);
		
		
		new ShowGraphicLine(loanRepaymentPlan.getData(), "Graphique en ligne" ,"Restitution graphique du tableau d'amortissement"); 
		
		new ShowGraphicBar3D(loanRepaymentPlan.getData(), "Graphique en bande" ,"Restitution graphique du tableau d'amortissement");
	}

}
