package determinationtaux;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;



public class Ihm implements ActionListener{

	private JFrame frmRateDetermination;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel; 
	private JRadioButton rdbtnYes, rdbtnNo, rdbtnGood, rdbtnBad;
	private JLabel lblNewLabel_1;
	private JComboBox comboBox;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ihm window = new Ihm();
					window.frmRateDetermination.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ihm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRateDetermination = new JFrame();
		frmRateDetermination.getContentPane().setBackground(Color.WHITE);
		frmRateDetermination.setTitle("D\u00E9termination taux d'int\u00E9r\u00EAt");
		frmRateDetermination.setBounds(100, 100, 549, 515);
		frmRateDetermination.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmRateDetermination.getContentPane().setLayout(gridBagLayout);
		
		lblNewLabel_1 = new JLabel("Type de pr\u00EAt :");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 1;
		frmRateDetermination.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		
		comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
			comboBox.addItem("");
		    comboBox.addItem("Conso");
		    comboBox.addItem("Immo");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 1;
		frmRateDetermination.getContentPane().add(comboBox, gbc_comboBox);
		
		JLabel lblDureDuPrt = new JLabel("Dur\u00E9e du pr\u00EAt :");
		GridBagConstraints gbc_lblDureDuPrt = new GridBagConstraints();
		gbc_lblDureDuPrt.anchor = GridBagConstraints.EAST;
		gbc_lblDureDuPrt.insets = new Insets(0, 0, 5, 5);
		gbc_lblDureDuPrt.gridx = 4;
		gbc_lblDureDuPrt.gridy = 3;
		frmRateDetermination.getContentPane().add(lblDureDuPrt, gbc_lblDureDuPrt);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 3;
		frmRateDetermination.getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblge = new JLabel("\u00C2ge : ");
		GridBagConstraints gbc_lblge = new GridBagConstraints();
		gbc_lblge.anchor = GridBagConstraints.EAST;
		gbc_lblge.insets = new Insets(0, 0, 5, 5);
		gbc_lblge.gridx = 4;
		gbc_lblge.gridy = 5;
		frmRateDetermination.getContentPane().add(lblge, gbc_lblge);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 5;
		gbc_textField_2.gridy = 5;
		frmRateDetermination.getContentPane().add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblEtatDeSant = new JLabel("Etat de sant\u00E9 : ");
		GridBagConstraints gbc_lblEtatDeSant = new GridBagConstraints();
		gbc_lblEtatDeSant.anchor = GridBagConstraints.EAST;
		gbc_lblEtatDeSant.insets = new Insets(0, 0, 5, 5);
		gbc_lblEtatDeSant.gridx = 4;
		gbc_lblEtatDeSant.gridy = 7;
		frmRateDetermination.getContentPane().add(lblEtatDeSant, gbc_lblEtatDeSant);
		
		rdbtnGood = new JRadioButton("Bon");
		rdbtnGood.setBackground(Color.WHITE);
		GridBagConstraints gbc_rdbtnGood = new GridBagConstraints();
		gbc_rdbtnGood.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnGood.gridx = 5;
		gbc_rdbtnGood.gridy = 7;
		frmRateDetermination.getContentPane().add(rdbtnGood, gbc_rdbtnGood);
		
		rdbtnBad = new JRadioButton("Mauvais");
		rdbtnBad.setBackground(Color.WHITE);
		GridBagConstraints gbc_rdbtnBad = new GridBagConstraints();
		gbc_rdbtnBad.anchor = GridBagConstraints.WEST;
		gbc_rdbtnBad.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnBad.gridx = 6;
		gbc_rdbtnBad.gridy = 7;
		frmRateDetermination.getContentPane().add(rdbtnBad, gbc_rdbtnBad);
		
		JLabel lblOneormore = new JLabel("Un/plusieurs pr\u00EAt(s) en cours? : ");
		GridBagConstraints gbc_lblOneormore = new GridBagConstraints();
		gbc_lblOneormore.anchor = GridBagConstraints.EAST;
		gbc_lblOneormore.insets = new Insets(0, 0, 5, 5);
		gbc_lblOneormore.gridx = 4;
		gbc_lblOneormore.gridy = 9;
		frmRateDetermination.getContentPane().add(lblOneormore, gbc_lblOneormore);
		
		rdbtnYes = new JRadioButton("Oui");
		rdbtnYes.setBackground(Color.WHITE);
		GridBagConstraints gbc_rdbtnYes = new GridBagConstraints();
		gbc_rdbtnYes.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnYes.gridx = 5;
		gbc_rdbtnYes.gridy = 9;
		frmRateDetermination.getContentPane().add(rdbtnYes, gbc_rdbtnYes);
		
		rdbtnNo = new JRadioButton("Non");
		rdbtnNo.setBackground(Color.WHITE);
		GridBagConstraints gbc_rdbtnNo = new GridBagConstraints();
		gbc_rdbtnNo.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNo.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNo.gridx = 6;
		gbc_rdbtnNo.gridy = 9;
		frmRateDetermination.getContentPane().add(rdbtnNo, gbc_rdbtnNo);
		 
		 JButton btnCalculerLeTaux = new JButton("Calculer le taux");
		 btnCalculerLeTaux.setBackground(Color.WHITE);
		 GridBagConstraints gbc_btnCalculerLeTaux = new GridBagConstraints();
		 gbc_btnCalculerLeTaux.gridwidth = 2;
		 gbc_btnCalculerLeTaux.insets = new Insets(0, 0, 5, 5);
		 gbc_btnCalculerLeTaux.gridx = 5;
		 gbc_btnCalculerLeTaux.gridy = 11;
		 frmRateDetermination.getContentPane().add(btnCalculerLeTaux, gbc_btnCalculerLeTaux);
		 
		 btnCalculerLeTaux.addActionListener(this);
		     
		     JLabel lblResultat = new JLabel("Le nouveau taux est :");
		     GridBagConstraints gbc_lblResultat = new GridBagConstraints();
		     gbc_lblResultat.anchor = GridBagConstraints.EAST;
		     gbc_lblResultat.insets = new Insets(0, 0, 5, 5);
		     gbc_lblResultat.gridx = 4;
		     gbc_lblResultat.gridy = 13;
		     frmRateDetermination.getContentPane().add(lblResultat, gbc_lblResultat);
		     
		      lblNewLabel = new JLabel("  ");
		      GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		      gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		      gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		      gbc_lblNewLabel.gridx = 5;
		      gbc_lblNewLabel.gridy = 13;
		      frmRateDetermination.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
//		 rdbtnYes, rdbtnNo, rdbtnAlrPaid, rdbtnGood, rdbtnBad;
		
			
		
		
		
		
		int loanType=0;
	//	double calcrate;
		if (comboBox.getSelectedItem().toString().equals("Conso"))
			loanType = 3;
		else if (comboBox.getSelectedItem().toString().equals("Immo"))
			//System.out.println(comboBox.getSelectedItem().toString().equals("Immo"));
			loanType = 2;
		
			
		boolean health, loanhist;
		if(rdbtnGood.isSelected())
			health = false;
		else
			health = true;
		
		if(rdbtnYes.isSelected())
			loanhist = false;
		else
			loanhist = true;
	
	
	
		
		RateDetermination d1 =new RateDetermination(Integer.parseInt(textField_1.getText()), Integer.parseInt(textField_2.getText()), health, loanhist, loanType);
		
		double total = d1.changeRate();
		lblNewLabel.setText(String.valueOf(total));
		
	
	}

}
	

