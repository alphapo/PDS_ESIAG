package windowBuilderTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterClient extends JFrame {

	private JPanel contentPane;

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
		
		Label label = new Label("Simulateur de pr\u00EAt ");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 16));
		label.setBounds(148, 10, 258, 22);
		contentPane.add(label);
		
		Label label_1 = new Label("Montant du pr\u00EAt");
		label_1.setFont(new Font("Dialog", Font.BOLD, 12));
		label_1.setAlignment(Label.CENTER);
		label_1.setBounds(97, 64, 103, 22);
		contentPane.add(label_1);
		
		TextField textField = new TextField();
		textField.setBounds(212, 129, 119, 22);
		contentPane.add(textField);
		
		Label label_2 = new Label("Dur\u00E9e du pr\u00EAt");
		label_2.setFont(new Font("Dialog", Font.BOLD, 12));
		label_2.setBounds(97, 129, 91, 22);
		contentPane.add(label_2);
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(212, 64, 119, 22);
		contentPane.add(textField_1);
		
		Label label_3 = new Label("Type de pr\u00EAt");
		label_3.setFont(new Font("Dialog", Font.BOLD, 12));
		label_3.setBounds(97, 196, 80, 22);
		contentPane.add(label_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(212, 198, 119, 20);
		contentPane.add(comboBox);
		
		JCheckBox chckbxHorsAssurance = new JCheckBox("Hors assurance");
		chckbxHorsAssurance.setBounds(6, 391, 119, 23);
		contentPane.add(chckbxHorsAssurance);
		
		JCheckBox chckbxHorsFraisDe = new JCheckBox("Hors frais de dossier");
		chckbxHorsFraisDe.setBounds(6, 421, 119, 23);
		contentPane.add(chckbxHorsFraisDe);
		
		JButton btnSimuler = new JButton("Simuler");
		/*
		 * metho to simulate a loan with all arguments
		 */
		
		btnSimuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// add  the update of data in the data base
			}
		});
		btnSimuler.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSimuler.setBounds(296, 304, 89, 23);
		contentPane.add(btnSimuler);
	}

}
