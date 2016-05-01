package client.tools;

import java.awt.BorderLayout;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BuilderRestitution extends JFrame{
	
	
	public BuilderRestitution(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// amount: 10000 ; 
		//rate: 1.5 ; 
		//duration: 4 ; 
		//insurance: 200
		JTable tableau = new JTable(new Simulation(10000, 1.5, 4, 200));
		
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
 
        pack();
		
	}
	
	//static void drawGraph(JPanel container, Collection informations){}
	 public static void main(String[] args) {
	        new BuilderRestitution().setVisible(true);
	    }
}