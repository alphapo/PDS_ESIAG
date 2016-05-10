package client.tools;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ShowArrayRestitution extends AbstractTableModel {
	private  ArrayList<TempoData> data = new ArrayList<TempoData>();

	private final String[] entetes = {"Numéro", "Mensualité", "Capital Amorti","Interets","Assurance","Capital restant dû"};

	/*
	 * 
	 * Method of reimbursement: constant annual installment
	 * 
	 * 
	 */
	public ShowArrayRestitution(ArrayList<TempoData> data) {
		this.data = data;
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
             return data.get(rowIndex).getNumberOfDueDate();
         case 1:
             return data.get(rowIndex).getMonthly();
         case 2:
             return data.get(rowIndex).getAmortizedCapital();
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
}