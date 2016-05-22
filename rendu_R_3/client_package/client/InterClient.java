

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import client.beans.Frequency;
import client.beans.LoanRepaymentPlan;
import client.beans.ShowArrayRestitution;

import java.beans.PropertyChangeListener;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.beans.PropertyChangeEvent;

import java.text.*;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;

public class InterClient extends JFrame {

	private JPanel contentPane;
	Connection connection;
	Statement statement;
	ResultSet result;

	/**
	 * Create the frame.
	 */
	public InterClient() {

		setTitle("Interface Client ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*
		 * the labels
		 */

		Label title = new Label("Simulateur de pr\u00EAt ");
		title.setAlignment(Label.CENTER);
		title.setFont(new Font("Harlow Solid Italic", Font.BOLD, 22));
		title.setBounds(177, 10, 370, 35);
		contentPane.add(title);

		Label amount = new Label("Montant du pr\u00EAt");
		amount.setFont(new Font("Dialog", Font.BOLD, 12));
		amount.setAlignment(Label.CENTER);
		amount.setBounds(146, 64, 103, 22);
		contentPane.add(amount);

		Label duration = new Label("Dur\u00E9e du pr\u00EAt");
		duration.setFont(new Font("Dialog", Font.BOLD, 12));
		duration.setBounds(158, 115, 91, 22);
		contentPane.add(duration);

		Label loanType = new Label("Type de pr\u00EAt");
		loanType.setFont(new Font("Dialog", Font.BOLD, 12));
		loanType.setBounds(158, 182, 80, 22);
		contentPane.add(loanType);

		JComboBox comboBoxLoanType = new JComboBox();
		comboBoxLoanType.setBounds(306, 182, 119, 20);
		contentPane.add(comboBoxLoanType);
		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/test_pds","root","");
			statement=connection.createStatement();
			result=statement.executeQuery("select * from loantype");

			while( result.next()){
				comboBoxLoanType.addItem(result.getString("name"));
			}


		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, e);
			System.out.println(e);
		}
	
		JFormattedTextField amountTextField = new JFormattedTextField();
		amountTextField.addKeyListener(new KeyAdapter() {
			@Override
			/*
			 * Obliged the client to enter only integer
			 * 
			 */
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if (!(Character.isDigit(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)){
					evt.consume();
				}
			}
		});

		amountTextField.setBounds(306, 66, 119, 20);
		contentPane.add(amountTextField);

		JButton btnSimuler = new JButton("Simuler");
		btnSimuler.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSimuler.setBounds(442, 302, 89, 39);
		contentPane.add(btnSimuler);

		Label frenquency = new Label("Fr\u00E9quence de remboursement");
		frenquency.setFont(new Font("Dialog", Font.BOLD, 12));
		frenquency.setAlignment(Label.CENTER);
		frenquency.setBounds(106, 230, 178, 22);
		contentPane.add(frenquency);

		JComboBox frequencyCombobox = new JComboBox();
		frequencyCombobox.setModel(new DefaultComboBoxModel(new String[] {"Mensuel", "Annuel"}));
		frequencyCombobox.setBounds(306, 232, 119, 20);
		contentPane.add(frequencyCombobox);


		JComboBox durationComboBox = new JComboBox();
		durationComboBox.setModel(new DefaultComboBoxModel());
		durationComboBox.setBounds(306, 115, 119, 20);
		contentPane.add(durationComboBox);

		try{
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/test_pds","root","");
			statement=connection.createStatement();
			result=statement.executeQuery("select * from duration");
			
			while( result.next()){
				
				durationComboBox.addItem(result.getString("duration"));
			}

		}

		catch (Exception e){
			JOptionPane.showMessageDialog(null, e);
			System.out.println(e);
		}

		JButton recap = new JButton("Recapitulatif");
		recap.setFont(new Font("Tahoma", Font.BOLD, 12));
		recap.setBounds(50, 417, 127, 23);
		contentPane.add(recap);

		JButton print = new JButton("t\u00E9l\u00E9charger");
		print.setFont(new Font("Tahoma", Font.BOLD, 12));
		print.setBounds(241, 417, 111, 23);
		contentPane.add(print);

		JButton diagramLines = new JButton("Graphique 2D");
		diagramLines.setFont(new Font("Tahoma", Font.BOLD, 12));
		diagramLines.setBounds(429, 417, 145, 23);
		contentPane.add(diagramLines);

		JButton button = new JButton("Imprimer");
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBounds(665, 417, 111, 23);
		contentPane.add(button);

		HashMap<String, Double> mapLoanTypeInsurance= new HashMap<>();
		HashMap<String, Double> mapLoanTypeRate= new HashMap<>();
		try{
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/test_pds","root","");
			statement=connection.createStatement();
			result=statement.executeQuery("select * from loanType ,insurance where insurance.id_insurance=loanType.id_insurance");
			while( result.next()){
				mapLoanTypeInsurance.put(result.getString("loanType.name"),result.getDouble("insurance.id_insurance"));
				mapLoanTypeRate.put(result.getString("loanType.name"),result.getDouble("loanType.rate"));
			}


		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, e);
			System.out.println(e);
		}

		afficherMap(mapLoanTypeRate);
		//System.out.println(mapLoanTypeInsurance.get("Prêt voiture").doubleValue());	




		btnSimuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame jframe = new JFrame();
				jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				double amount = Double.parseDouble(amountTextField.getText());
				int duration=0;
				if (durationComboBox.getSelectedIndex()==0){
					duration =1;
				}
				else if (durationComboBox.getSelectedIndex()==1){
					duration =5;
				}
				String loanType = (String)comboBoxLoanType.getSelectedItem();
				double insurance=mapLoanTypeInsurance.get(loanType).doubleValue();
				double rate=mapLoanTypeRate.get(loanType).doubleValue();
				/*
				 * le remboursement par défaut pour un client est de type trois.
				 */
				int reimbursementType = 3;
				Frequency frequency = Frequency.Monthly;

				LoanRepaymentPlan loanRepaymentPlan=new LoanRepaymentPlan(amount, rate, duration, insurance, reimbursementType, frequency);
				loanRepaymentPlan.fillArray();
				JTable arrayFrame = new JTable(new ShowArrayRestitution(loanRepaymentPlan.getData()));

				jframe.getContentPane().add(new JScrollPane(arrayFrame), BorderLayout.CENTER);

				jframe.pack();
				jframe.setVisible(true);


			}
		});

	}

	/**  
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterClient frame = new InterClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void afficherMap(HashMap<String, Double> map){
		for(Entry<String, Double> entry : map.entrySet()) {
			String cle = entry.getKey();
			Double valeur = entry.getValue();
			System.out.println("cle==>"+cle);
			System.out.println("valeur==>"+valeur);

		}
	}

}
