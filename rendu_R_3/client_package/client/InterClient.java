package client;


import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.xml.parsers.FactoryConfigurationError;

import beans.ClientSimulation;
import beans.Duration;
import tools.ClientFactoryJson;
import tools.Communicator;

import java.beans.PropertyChangeListener;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.beans.PropertyChangeEvent;

import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import java.awt.Label;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;

import java.util.Date;

public class InterClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection connection;
	Statement statement;
	ResultSet result;
	Date day = new Date(); 
	Communicator communicator = new Communicator();
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
		loanType.setBounds(146, 210, 80, 22);
		contentPane.add(loanType);

		JComboBox comboBoxLoanType = new JComboBox();
		comboBoxLoanType.setBounds(306, 212, 119, 20);
		contentPane.add(comboBoxLoanType);
		try{
			Class.forName("com.mysql.jdbc.Driver");
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
		JButton simulation = new JButton("Simuler");
		simulation.setFont(new Font("Tahoma", Font.BOLD, 12));
		simulation.setBounds(442, 302, 89, 39);
		contentPane.add(simulation);

		Label frenquency = new Label("Fr\u00E9quence de remboursement");
		frenquency.setFont(new Font("Dialog", Font.BOLD, 12));
		frenquency.setAlignment(Label.CENTER);
		frenquency.setBounds(105, 251, 178, 22);
		contentPane.add(frenquency);

		JComboBox frequencyCombobox = new JComboBox();
		frequencyCombobox.setModel(new DefaultComboBoxModel(new String[] {"Mensuel", "Annuel"}));
		frequencyCombobox.setBounds(306, 253, 119, 20);
		contentPane.add(frequencyCombobox);


		JComboBox durationComboBox = new JComboBox();
		durationComboBox.setModel(new DefaultComboBoxModel(new String[] {""}));
		durationComboBox.setBounds(306, 115, 119, 20);
		contentPane.add(durationComboBox);
		HashMap<Integer,Integer> mapDurationMonth= new HashMap<>();
		try{
			statement=connection.createStatement();
			result=statement.executeQuery("select * from duration");
			while( result.next()){
				mapDurationMonth.put(result.getInt("duration"),result.getInt("month"));
				durationComboBox.addItem(result.getInt("duration"));
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

		Label nbreAnnee = new Label("ann\u00E9e(s)");
		nbreAnnee.setFont(new Font("Dialog", Font.BOLD, 12));
		nbreAnnee.setBounds(433, 115, 62, 22);
		contentPane.add(nbreAnnee);

		Label label = new Label("\u00E2ge");
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		label.setAlignment(Label.CENTER);
		label.setBounds(117, 153, 178, 22);
		contentPane.add(label);

		Label label_1 = new Label("ans");
		label_1.setFont(new Font("Dialog", Font.BOLD, 12));
		label_1.setBounds(429, 153, 62, 22);
		contentPane.add(label_1);

		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(306, 151, 119, 20);
		contentPane.add(formattedTextField);

		HashMap<String, Double> mapLoanTypeInsurance= new HashMap<>();
		HashMap<String, Double> mapLoanTypeRate= new HashMap<>();
		HashMap<String, Integer> mapLoanTypeName= new HashMap<>();
		try{
			statement=connection.createStatement();
			result=statement.executeQuery("select * from loanType ,insurance where insurance.id_insurance=loanType.id_insurance");
			while( result.next()){
				mapLoanTypeName.put(result.getString("loanType.name"),result.getInt("loanType.id_loanType"));
				mapLoanTypeInsurance.put(result.getString("loanType.name"),result.getDouble("insurance.id_insurance"));
				mapLoanTypeRate.put(result.getString("loanType.name"),result.getDouble("loanType.rate"));
			}


		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, e);
			System.out.println(e);
		}

<<<<<<< HEAD
		/*
		 * methode sert à afficher une hashmap
		 */
=======
		afficherMap(mapLoanTypeRate);
>>>>>>> 7a6cb1ff9973eb2df612a2cd72a4103ee7d47dc0

		simulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (amountTextField.getText().isEmpty()|| label.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Veuillez renseigner les champs permettant de vous identifier");
				}
				double amount = Double.parseDouble(amountTextField.getText());
				String loanType = (String)comboBoxLoanType.getSelectedItem();
				int id_loanType= mapLoanTypeName.get(loanType).intValue();
				double insurance=mapLoanTypeInsurance.get(loanType).doubleValue();
				double rate=mapLoanTypeRate.get(loanType).doubleValue();
				int durationYear =(int)durationComboBox.getSelectedItem() ;
				double durationMonth= (double)mapDurationMonth.get(durationYear).intValue();
				String day= aujourdhui();


				/*
				 * le remboursement par défaut pour un client est de type trois.
				 */
				System.out.println("---------------interface pret -------------------");
				System.out.println(durationMonth);
				System.out.println(amount);
				System.out.println(rate);
				System.out.println("testInsertion");
				//				System.out.println(insurance);
				System.out.println(day);

				int status = 2;


				ClientSimulation clientSimulation = new ClientSimulation();
				clientSimulation.setId_user(11);
				clientSimulation.setAmount(amount);
				clientSimulation.setDay(day);
				clientSimulation.setRate(rate);
				clientSimulation.setStatus(status);
				clientSimulation.setDuration(durationMonth);
				clientSimulation.setId_name("testInsertion");
				clientSimulation.setId_loanType(id_loanType);
				
				communicator.sendData(ClientFactoryJson.makeJSONsimulationForClient(clientSimulation));
				if(communicator.receiveData().equals("OK")){
					JOptionPane.showMessageDialog(null, "Votre simulation a été enregistrée");
					System.out.println("Insertion OK");
				}
				
				else
					System.out.println("Insertion KO");

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
	/*
	 * get back the date.
	 */
	public String aujourdhui() {
		return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}


}



