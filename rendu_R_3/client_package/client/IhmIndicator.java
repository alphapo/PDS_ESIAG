package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.tools.ClientFactoryJson;
import client.tools.Communicator;

public class IhmIndicator extends JFrame{
	Communicator com;
	String user;
	JLabel nbLoan;
	JLabel nbSimulation;
	JButton nbSimOk;
	JButton nbLoanOk;
	
	JPanel loanPanel;
	JPanel simulationPanel;
	
	public IhmIndicator(Communicator c, String login){
		this.setTitle("Connecté en tant que : "+login);
		this.initialize();
		com=c;
		user=login;
	}
	

//	
//	public void sendIndicator() throws IOException{
//		int nb = 100;
//
////		com.sendData(ClientFactoryJson.makeJSONgetSimulation(1));
////		 nb = com.receiveDataSimulation();
////		 
////		System.out.println(nb);
//		System.out.println("Oui");
//	}
//	
	
	
	public void initialize(){
		setVisible(true);
		setSize(500,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		this.getContentPane().add(initializeLoanPanel(), BorderLayout.NORTH);
		this.getContentPane().add(initializeSimulationPanel(), BorderLayout.SOUTH);	
		
		validate();

		}

	public JPanel initializeLoanPanel(){
		JPanel pLoan = new JPanel(new GridBagLayout());
		pLoan.setPreferredSize(new Dimension(500,200));
		pLoan.setBackground(Color.white);
	    GridBagConstraints gc = new GridBagConstraints();
	    
		JLabel title = new JLabel("----------------- PRET -----------------");
		
		nbLoanOk = new JButton("Nombre de pret(s)");
		nbLoan = new JLabel("Résultat");		
		
		addItem(pLoan, title,0, 0, gc, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL);
		addItem(pLoan, nbLoanOk,1,0,gc,GridBagConstraints.WEST, GridBagConstraints.NONE);
		addItem(pLoan, nbLoan,1,1,gc,GridBagConstraints.WEST, GridBagConstraints.NONE);
		
			
		nbLoanOk.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	        {
			 try {
	        	int nb=0;
	            //Execute when button is pressed
	        	com.sendData(ClientFactoryJson.makeJSONgetSimulation(1));
				nb = com.receiveDataSimulation();
				nbLoan.setText(Integer.toString(nb));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	            }
		});
		
		
		return pLoan;
	}
	


	
	public JPanel initializeSimulationPanel(){
		JPanel pSim = new JPanel(new GridBagLayout());
		pSim.setPreferredSize(new Dimension(500,200));
		pSim.setBackground(Color.yellow);
	    GridBagConstraints gc = new GridBagConstraints();

		
		JLabel title = new JLabel("----------------- SIMULATION -----------------");
		nbSimOk = new JButton("Nombre de simulation(s)");
		nbSimulation = new JLabel("Résultat");
		nbSimulation.setBackground(Color.white);
		
		addItem(pSim, title,0, 0, gc, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);

		addItem(pSim, nbSimOk,1,0,gc,GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		addItem(pSim, nbSimulation,1,1,gc,GridBagConstraints.WEST, GridBagConstraints.NONE);


		
		nbSimOk.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	        {
			 try {
	        	int nb=0;
	            //Execute when button is pressed
	        	com.sendData(ClientFactoryJson.makeJSONgetSimulation(2));
				nb = com.receiveDataSimulation();
				nbSimulation.setText(Integer.toString(nb));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	            }
		});
		
		return pSim;
	}
	

	//add and set item in ihm
	public void addItem(JPanel p, JComponent c, int x, int y, GridBagConstraints
 gc, int align, int fill) {
	    gc.gridx = y;
	    gc.gridy = x;
	    gc.fill = fill;
	    gc.weightx = 1;
	    gc.weighty = 1;
	    gc.anchor = align;
	    p.add(c, gc);
	  }

	
	
	public static void main(String [] args) throws IOException{
		new IhmIndicator(new Communicator(), "Usman");
		
		
	}

}
