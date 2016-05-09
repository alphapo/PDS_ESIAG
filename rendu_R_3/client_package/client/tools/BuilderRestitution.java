package test2;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BuilderRestitution extends JFrame{


	public BuilderRestitution(){

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// amount: 100 000 ; 
		//rate by year: 3,6% ; 
		//duration: 180 months ; 
		//insurance: 30 euros by month
		JTable tableau = new JTable(new Simulation(100000, 0.036, 180, 30));

		getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

		pack();

	}

	public static void main(String[] args) {
		new BuilderRestitution().setVisible(true);
	}
}
