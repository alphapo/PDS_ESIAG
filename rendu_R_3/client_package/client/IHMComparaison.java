package client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class IHMComparaison extends JFrame{

	private JPanel panel = new JPanel();

	public IHMComparaison()
	{
		try 
		{

			UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e)
		{

		}
		
		panel.setBackground(Color.white);
		
		setTitle("Comparaison de simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setBounds(100, 100, 600, 400);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_contentPane);

		JLabel lblSimu1 = new JLabel("S\u00E9lectionnez la simulation 1");
		GridBagConstraints gbc_lblSimu1 = new GridBagConstraints();
		gbc_lblSimu1.insets = new Insets(30, 30, 30, 30);
		gbc_lblSimu1.gridx = 4;
		gbc_lblSimu1.gridy = 3;
		panel.add(lblSimu1, gbc_lblSimu1);

		JComboBox comboBoxSimu1 = new JComboBox();
		GridBagConstraints gbc_comboBoxSimu1 = new GridBagConstraints();
		gbc_comboBoxSimu1.insets = new Insets(30, 30, 30, 30);
		gbc_comboBoxSimu1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSimu1.gridx = 6;
		gbc_comboBoxSimu1.gridy = 3;
		panel.add(comboBoxSimu1, gbc_comboBoxSimu1);

		JLabel lblSimu2 = new JLabel("S\u00E9lectionnez la simulation 2");
		GridBagConstraints gbc_lblSimu2 = new GridBagConstraints();
		gbc_lblSimu2.insets = new Insets(30, 30, 30, 30);
		gbc_lblSimu2.gridx = 4;
		gbc_lblSimu2.gridy = 5;
		panel.add(lblSimu2, gbc_lblSimu2);

		JComboBox comboBoxSimu2 = new JComboBox();
		GridBagConstraints gbc_comboBoxSimu2 = new GridBagConstraints();
		gbc_comboBoxSimu2.insets = new Insets(30, 30, 30, 30);
		gbc_comboBoxSimu2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSimu2.gridx = 6;
		gbc_comboBoxSimu2.gridy = 5;
		panel.add(comboBoxSimu2, gbc_comboBoxSimu2);

		JLabel lblSimu3 = new JLabel("S\u00E9lectionnez la simulation 3");
		GridBagConstraints gbc_lblSimu3 = new GridBagConstraints();
		gbc_lblSimu3.insets = new Insets(30, 30, 30, 30);
		gbc_lblSimu3.gridx = 4;
		gbc_lblSimu3.gridy = 7;
		panel.add(lblSimu3, gbc_lblSimu3);

		JComboBox comboBoxSimu3 = new JComboBox();
		GridBagConstraints gbc_comboBoxSimu3 = new GridBagConstraints();
		gbc_comboBoxSimu3.insets = new Insets(30, 30, 30, 30);
		gbc_comboBoxSimu3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSimu3.gridx = 6;
		gbc_comboBoxSimu3.gridy = 7;
		panel.add(comboBoxSimu3, gbc_comboBoxSimu3);

		JButton btnCompar = new JButton("Comparez");
		btnCompar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnCompar = new GridBagConstraints();
		gbc_btnCompar.insets = new Insets(15, 15, 15, 15);
		gbc_btnCompar.gridx = 6;
		gbc_btnCompar.gridy = 9;
		panel.add(btnCompar, gbc_btnCompar);
		
		setContentPane(panel);
		setVisible(true);
	}
}
