package test2;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class Simulation extends AbstractTableModel {
	private final List<TempoData> data = new ArrayList<TempoData>();

	private final String[] entetes = {"Numéro", "Mensualité","Taux d'interet","Assurance","Capital restant dû"};

	public Simulation(double amount, double rate, double duration, int insurance) {
		
		double amountTTC = amount + amount*rate + insurance;
		System.out.println(amountTTC);
		double monthly= amountTTC/duration;
		double amountRemaining =  amountTTC-monthly;
		
		for(int i = 1; i<=duration; i++){
			amountRemaining = amountRemaining-monthly;
			data.add(new TempoData(i, amount, rate, duration, insurance, monthly, amountRemaining));
		}
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
             return data.get(rowIndex).getRate();
         case 3:
             return data.get(rowIndex).getInsurance();
         case 4:
             return data.get(rowIndex).getRemaining();
         default:
             return null; 
     }
	}

}