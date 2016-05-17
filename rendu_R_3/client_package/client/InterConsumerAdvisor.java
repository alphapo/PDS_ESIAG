package windowBuilderTest;

import java.awt.*;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream.GetField;
import java.awt.event.ActionEvent;

public class InterConsumerAdvisor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterConsumerAdvisor frame = new InterConsumerAdvisor();
					frame.setVisible(true);
			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterConsumerAdvisor() {
		setTitle("Interface Conseiller");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSimulate = new JButton("Simuler");
		/*
		 * Methode to simulate a loan
		 */
		btnSimulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Insert in the database
			}
		});
		btnSimulate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSimulate.setBounds(258, 303, 89, 31);
		contentPane.add(btnSimulate);
		
		TextField textFieldAmount = new TextField();
		textFieldAmount.setBounds(218, 44, 89, 22);
		contentPane.add(textFieldAmount);
		/*
		 * test print the amount in the console
		 */
		
		Label montantPret = new Label("Montant");
		montantPret.setFont(new Font("Dialog", Font.BOLD, 12));
		montantPret.setAlignment(Label.CENTER);
		montantPret.setBounds(122, 44, 62, 22);
		contentPane.add(montantPret);
		
		Label HeadLabel = new Label("Simulateur de Pr\u00EAt");
		HeadLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		HeadLabel.setAlignment(Label.CENTER);
		HeadLabel.setBounds(83, 10, 327, 22);
		contentPane.add(HeadLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(218, 84, 89, 20);
		contentPane.add(comboBox);
		
		Label label = new Label("Dur\u00E9e du pr\u00EAt");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		label.setBounds(106, 82, 89, 22);
		contentPane.add(label);
		
		Label label_1 = new Label("Type de pr\u00EAt");
		label_1.setAlignment(Label.CENTER);
		label_1.setFont(new Font("Dialog", Font.BOLD, 12));
		label_1.setBounds(106, 136, 78, 22);
		contentPane.add(label_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(218, 136, 89, 20);
		contentPane.add(comboBox_1);
		
		JButton btncalculateRate = new JButton("calculer Taux");
		/*
		 * Method to calculate the variable rate for a specificied client
		 */
		btncalculateRate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new "UC taux variable"
			}
		});
		btncalculateRate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btncalculateRate.setBounds(296, 246, 114, 23);
		contentPane.add(btncalculateRate);
		
		Label label_2 = new Label("Mensualit\u00E9s");
		label_2.setAlignment(Label.CENTER);
		label_2.setFont(new Font("Dialog", Font.BOLD, 12));
		label_2.setBounds(106, 194, 78, 22);
		contentPane.add(label_2);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Frais de dossier 20,00 euros ");
		chckbxNewCheckBox.setSelected(true);
		chckbxNewCheckBox.setBounds(6, 366, 203, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxAssuranceDu = new JCheckBox("Assurance 20% du montant du pr\u00EAt");
		chckbxAssuranceDu.setSelected(true);
		chckbxAssuranceDu.setBounds(6, 340, 203, 23);
		contentPane.add(chckbxAssuranceDu);
		
		TextField textField = new TextField();
		textField.setBounds(218, 194, 89, 22);
		contentPane.add(textField);
	}
}
