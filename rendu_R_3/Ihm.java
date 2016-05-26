package client;


import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JTextField;

import client.beans.ManageUc;
import client.tools.Communicator;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;

import service.ClientService;

public class Ihm implements ActionListener{

	private JFrame frmDterminationTauxDintrt;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel; 
	private JRadioButton rdbtnOui, rdbtnNon, rdbtnBon, rdbtnMauvais;
	private JLabel lblNewLabel_1;
	private JComboBox comboBox;
	ClientService cs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ihm window = new Ihm(new Communicator());
					window.frmDterminationTauxDintrt.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ihm(Communicator c ) throws MalformedURLException, RemoteException, NotBoundException{
		try {
			cs = new ClientService();
		} catch (Exception e) {
			e.printStackTrace();
		}
		initialize(cs);
		this.frmDterminationTauxDintrt.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ClientService cs) {
		frmDterminationTauxDintrt = new JFrame();
		frmDterminationTauxDintrt.getContentPane().setBackground(Color.WHITE);
		frmDterminationTauxDintrt.setTitle("D\u00E9termination taux d'int\u00E9r\u00EAt");
		frmDterminationTauxDintrt.setBounds(100, 100, 551, 397);
		frmDterminationTauxDintrt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmDterminationTauxDintrt.getContentPane().setLayout(gridBagLayout);
		
		lblNewLabel_1 = new JLabel("Type de pr\u00EAt :");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 1;
		frmDterminationTauxDintrt.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		
		comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
			comboBox.addItem("");
		    comboBox.addItem("Perso");
		    comboBox.addItem("Immo");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 1;
		frmDterminationTauxDintrt.getContentPane().add(comboBox, gbc_comboBox);
		
		JLabel lblDureDuPrt = new JLabel("Dur\u00E9e du pr\u00EAt :");
		GridBagConstraints gbc_lblDureDuPrt = new GridBagConstraints();
		gbc_lblDureDuPrt.anchor = GridBagConstraints.EAST;
		gbc_lblDureDuPrt.insets = new Insets(0, 0, 5, 5);
		gbc_lblDureDuPrt.gridx = 4;
		gbc_lblDureDuPrt.gridy = 3;
		frmDterminationTauxDintrt.getContentPane().add(lblDureDuPrt, gbc_lblDureDuPrt);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 3;
		frmDterminationTauxDintrt.getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblge = new JLabel("\u00C2ge : ");
		GridBagConstraints gbc_lblge = new GridBagConstraints();
		gbc_lblge.anchor = GridBagConstraints.EAST;
		gbc_lblge.insets = new Insets(0, 0, 5, 5);
		gbc_lblge.gridx = 4;
		gbc_lblge.gridy = 5;
		frmDterminationTauxDintrt.getContentPane().add(lblge, gbc_lblge);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 5;
		gbc_textField_2.gridy = 5;
		frmDterminationTauxDintrt.getContentPane().add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblEtatDeSant = new JLabel("Etat de sant\u00E9 : ");
		GridBagConstraints gbc_lblEtatDeSant = new GridBagConstraints();
		gbc_lblEtatDeSant.anchor = GridBagConstraints.EAST;
		gbc_lblEtatDeSant.insets = new Insets(0, 0, 5, 5);
		gbc_lblEtatDeSant.gridx = 4;
		gbc_lblEtatDeSant.gridy = 7;
		frmDterminationTauxDintrt.getContentPane().add(lblEtatDeSant, gbc_lblEtatDeSant);
		
		rdbtnBon = new JRadioButton("Bon");
		rdbtnBon.setBackground(Color.WHITE);
		GridBagConstraints gbc_rdbtnBon = new GridBagConstraints();
		gbc_rdbtnBon.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnBon.gridx = 5;
		gbc_rdbtnBon.gridy = 7;
		frmDterminationTauxDintrt.getContentPane().add(rdbtnBon, gbc_rdbtnBon);
		
		rdbtnMauvais = new JRadioButton("Mauvais");
		rdbtnMauvais.setBackground(Color.WHITE);
		GridBagConstraints gbc_rdbtnMauvais = new GridBagConstraints();
		gbc_rdbtnMauvais.anchor = GridBagConstraints.WEST;
		gbc_rdbtnMauvais.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMauvais.gridx = 6;
		gbc_rdbtnMauvais.gridy = 7;
		frmDterminationTauxDintrt.getContentPane().add(rdbtnMauvais, gbc_rdbtnMauvais);
		
		JLabel lblDjEuUnplusieurs = new JLabel("Un/plusieurs pr\u00EAt(s) en cours? : ");
		GridBagConstraints gbc_lblDjEuUnplusieurs = new GridBagConstraints();
		gbc_lblDjEuUnplusieurs.insets = new Insets(0, 0, 5, 5);
		gbc_lblDjEuUnplusieurs.gridx = 4;
		gbc_lblDjEuUnplusieurs.gridy = 9;
		frmDterminationTauxDintrt.getContentPane().add(lblDjEuUnplusieurs, gbc_lblDjEuUnplusieurs);
		
		rdbtnOui = new JRadioButton("Oui");
		rdbtnOui.setBackground(Color.WHITE);
		GridBagConstraints gbc_rdbtnOui = new GridBagConstraints();
		gbc_rdbtnOui.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnOui.gridx = 5;
		gbc_rdbtnOui.gridy = 9;
		frmDterminationTauxDintrt.getContentPane().add(rdbtnOui, gbc_rdbtnOui);
		
		rdbtnNon = new JRadioButton("Non");
		rdbtnNon.setBackground(Color.WHITE);
		GridBagConstraints gbc_rdbtnNon = new GridBagConstraints();
		gbc_rdbtnNon.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNon.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNon.gridx = 6;
		gbc_rdbtnNon.gridy = 9;
		frmDterminationTauxDintrt.getContentPane().add(rdbtnNon, gbc_rdbtnNon);
		
		JButton btnCalculerLeTaux = new JButton("Calculer le taux");
		btnCalculerLeTaux.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnCalculerLeTaux = new GridBagConstraints();
		gbc_btnCalculerLeTaux.gridwidth = 2;
		gbc_btnCalculerLeTaux.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalculerLeTaux.gridx = 5;
		gbc_btnCalculerLeTaux.gridy = 11;
		frmDterminationTauxDintrt.getContentPane().add(btnCalculerLeTaux, gbc_btnCalculerLeTaux);
		
		JLabel lblResultat = new JLabel("Resultat: ");
		GridBagConstraints gbc_lblResultat = new GridBagConstraints();
		gbc_lblResultat.insets = new Insets(0, 0, 0, 5);
		gbc_lblResultat.gridx = 4;
		gbc_lblResultat.gridy = 12;
		frmDterminationTauxDintrt.getContentPane().add(lblResultat, gbc_lblResultat);
		
		 lblNewLabel = new JLabel("  ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 12;
		frmDterminationTauxDintrt.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		btnCalculerLeTaux.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
//		 rdbtnOui, rdbtnNon, rdbtnDjaRgls, rdbtnBon, rdbtnMauvais;
		int typePret=0;
	//	double calcrate;
		if (comboBox.getSelectedItem().toString().equals("Perso"))
			typePret = 1;
		else if (comboBox.getSelectedItem().toString().equals("Immo"))
			//System.out.println(comboBox.getSelectedItem().toString().equals("Immo"));
			typePret = 2;
		
			
		
		
		boolean health, loanhist;
		if(rdbtnBon.isSelected())
			health = false;
		else
			health = true;
		
		if(rdbtnOui.isSelected())
			loanhist = false;
		else
			loanhist = true;
	
	
	
		
		RateDetermination d1 =new RateDetermination(Integer.parseInt(textField_1.getText()), Integer.parseInt(textField_2.getText()), health, loanhist, typePret);
		
		double total = d1.changeRate();
		lblNewLabel.setText(String.valueOf(total));
		
		try {
			System.out.println("rate :"+total+"-----"+typePret);
			cs.receiveSendRate(total, typePret);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		new RateDetermination(Integer.parseInt(textField_1.getText()), Integer.parseInt(textField_2.getText()), health, loanhist, typePret).insertnewrate(new RateDetermination(Integer.parseInt(textField_1.getText()), Integer.parseInt(textField_2.getText()), health, loanhist, typePret).changeRate());

	}

}
	

