package windowBuilderTest;

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
		setBounds(100, 100, 504, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*
		 * the labels
		 */

		Label title = new Label("Simulateur de pr\u00EAt ");
		title.setAlignment(Label.CENTER);
		title.setFont(new Font("Dialog", Font.BOLD, 16));
		title.setBounds(148, 10, 258, 22);
		contentPane.add(title);

		Label amount = new Label("Montant du pr\u00EAt");
		amount.setFont(new Font("Dialog", Font.BOLD, 12));
		amount.setAlignment(Label.CENTER);
		amount.setBounds(97, 64, 103, 22);
		contentPane.add(amount);

		Label duration = new Label("Dur\u00E9e du pr\u00EAt");
		duration.setFont(new Font("Dialog", Font.BOLD, 12));
		duration.setBounds(97, 129, 91, 22);
		contentPane.add(duration);

		Label loanType = new Label("Type de pr\u00EAt");
		loanType.setFont(new Font("Dialog", Font.BOLD, 12));
		loanType.setBounds(97, 196, 80, 22);
		contentPane.add(loanType);

		JComboBox comboBoxLoanType = new JComboBox();
		comboBoxLoanType.setBounds(212, 198, 119, 20);
		contentPane.add(comboBoxLoanType);

		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pds_ing1","root","");
			statement=connection.createStatement();
			result=statement.executeQuery("select * from agence");

			while( result.next()){
				comboBoxLoanType.addItem(result.getString("ville"));
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

		JCheckBox chckbxHorsAssurance = new JCheckBox("Hors assurance");
		chckbxHorsAssurance.setBounds(6, 391, 119, 23);
		contentPane.add(chckbxHorsAssurance);

		JCheckBox chckbxHorsFraisDe = new JCheckBox("Hors frais de dossier");
		chckbxHorsFraisDe.setBounds(6, 421, 119, 23);
		contentPane.add(chckbxHorsFraisDe);

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

		amountTextField.setBounds(212, 64, 119, 20);
		contentPane.add(amountTextField);

		System.out.println(amountTextField.getText());

		JFormattedTextField durationTextField = new JFormattedTextField();
		durationTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if (!(Character.isDigit(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)){
					evt.consume();
				}
			}
		});
		durationTextField.setBounds(212, 131, 119, 20);
		contentPane.add(durationTextField);




		JButton btnSimuler = new JButton("Simuler");
		btnSimuler.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSimuler.setBounds(296, 304, 89, 23);
		contentPane.add(btnSimuler);
		/*
		 * metho to simulate a loan with all arguments
		 */

		btnSimuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// methode pour envoyer le JSon au serveur...
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
