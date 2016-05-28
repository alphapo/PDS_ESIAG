package client.beans;

import javax.swing.table.AbstractTableModel;

public class ShowArrayRestitution extends AbstractTableModel {
	
	private  LoanRepaymentsPlan loanRepaymentsPlan;

	private final String[] entetes = {"Numéro", "Mensualité", "Capital Amorti","Intérêts","Assurance","Capital dû"};

	/*
	 * 
	 * Method of reimbursement: constant annual installment
	 * 
	 * 
	 */
	public ShowArrayRestitution(final LoanRepaymentsPlan loanRepaymentPlan) {
		this.loanRepaymentsPlan = loanRepaymentPlan;
	}

	public int getRowCount() {
		return loanRepaymentsPlan.getData().size();
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
             return loanRepaymentsPlan.getData().get(rowIndex).getNumberOfDueDate();
         case 1:
             return loanRepaymentsPlan.getData().get(rowIndex).getMonthly();
         case 2:
             return loanRepaymentsPlan.getData().get(rowIndex).getAmortizedCapital();
         case 3:
             return loanRepaymentsPlan.getData().get(rowIndex).getInterest();
         case 4:
             return loanRepaymentsPlan.getData().get(rowIndex).getInsurance();
         case 5:
             return loanRepaymentsPlan.getData().get(rowIndex).getRemaining();
         default:
             return null; 
     }
	}
}