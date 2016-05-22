

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

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

		System.out.println(amountTextField.getText());




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
		
		JButton recap = new JButton("Recapitulatif");
		recap.setFont(new Font("Tahoma", Font.BOLD, 12));
		recap.setBounds(50, 417, 127, 23);
		contentPane.add(recap);
		
		JButton print = new JButton("Imprimer");
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
		try{
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/test_pds","root","");
			statement=connection.createStatement();
			result=statement.executeQuery("select * from duration");
			
			while( result.next()){

				durationComboBox.addItem(result.getInt("duration")+" "+"ans");
			}


		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, e);
			System.out.println(e);
		}
		finally{

			try{
				statement.close();
				result.close();
				connection.close();

			}catch(Exception e ){
				JOptionPane.showMessageDialog(null, "Erreur");

			}
		}
		/*
		 * metho to simulate a loan with all arguments
		 */

		btnSimuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				System.out.println(comboBoxLoanType.getSelectedItem().toString());
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
}
   
