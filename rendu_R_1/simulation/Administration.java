package pds;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Administration extends JFrame implements ActionListener
{

	private JPanel panel = new JPanel();
	//initialisation des labels
	private JLabel label = new JLabel("Nom");
	private JLabel label1 = new JLabel("Prenom");
	private JLabel label2 = new JLabel("num�ro de compte");
	private JLabel label3 = new JLabel("Agence");
	private JLabel label4 = new JLabel("Employe");
	
	
	private JLabel label20 = new JLabel("Nom");
	private JLabel label21 = new JLabel("Prenom");
	private JLabel label22 = new JLabel("num�ro de compte");
	private JLabel label23 = new JLabel("Agence");
	private JLabel label24 = new JLabel("Employe");
	private JLabel label25 = new JLabel("Client");
	

	//initialisation des champs texte
	private JTextField name = new JTextField();
	private JTextField firstName = new JTextField();
	private JTextField employee = new JTextField();
	private JTextField idAccount = new JTextField();
	private JComboBox agency = new JComboBox();
	
	private JTextField name2 = new JTextField();
	private JTextField firstName2 = new JTextField();
	private JTextField employee2 = new JTextField();
	private JTextField idAccount2 = new JTextField();
	private JComboBox agency2 = new JComboBox();
	
	JComboBox consumers2 = new JComboBox();
	
	
	// initialisation des boutons de creation, modification, supression
	private JButton submit1 = new JButton("Cr�er client");
	private JButton submit2 = new JButton("Modifier client");
	private JButton submit3 = new JButton("Supprimer client");


	public Administration ()
	{
		this.setTitle("Interface administration");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);

		panel.setBackground(Color.GRAY);
		panel.setLayout(new BorderLayout());


		//onglet1 - Cr�ation d'un client ***************************************************************
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(6,1));
		
		////on indique au bouton que la fentre va ecouter ses actions
		submit1.addActionListener(this);
		
		//initialisation des panels qui vont composer le GridLayout
		JPanel lvl1 = new JPanel();
		lvl1.add(label);
		lvl1.add(name);
		name.setPreferredSize(new Dimension(150, 30));

		JPanel lvl2 = new JPanel();
		lvl2.add(label1);
		lvl2.add(firstName);
		firstName.setPreferredSize(new Dimension(150, 30));

		JPanel lvl3 = new JPanel();
		lvl3.add(label2);
		lvl3.add(idAccount);
		idAccount.setPreferredSize(new Dimension(150, 30));


		JPanel lvl4 = new JPanel();
		lvl4.add(label3);
		agency.setSelectedIndex(-1);
		agency.setPreferredSize(new Dimension(150, 30));
		new ServerMetier().selectAgency(agency);
		lvl4.add(agency);

		JPanel lvl5= new JPanel();
		lvl5.add(label4);
		String[] choix = { "","oui", "non"};
		JComboBox employee = new JComboBox(choix);
		employee.setSelectedIndex(0);
		employee.setPreferredSize(new Dimension(150, 30));
		lvl5.add(employee);

		JPanel lvl6= new JPanel();
		lvl6.add(submit1);
		panel2.add(lvl1);
		panel2.add(lvl2);
		panel2.add(lvl3);
		panel2.add(lvl4);
		panel2.add(lvl5);
		panel2.add(lvl6);

		//onglet2 - Modification d'un client ***************************************************************************S
		JPanel lvl27 = new JPanel();
		JComboBox consumers = new JComboBox();
		employee.setSelectedIndex(0);
		employee.setPreferredSize(new Dimension(150, 30));
		new ServerMetier().selectClient(consumers);
		
		consumers.addActionListener (new ActionListener () 
		{
		    public void actionPerformed(ActionEvent e) {
		        
		    }
		});
		lvl27.add(label25);
		lvl27.add(consumers);
		
		JPanel lvl21 = new JPanel();
		lvl21.add(label20);
		lvl21.add(name2);
		name2.setPreferredSize(new Dimension(150, 30));

		JPanel lvl22 = new JPanel();
		lvl22.add(label21);
		lvl22.add(firstName2);
		firstName2.setPreferredSize(new Dimension(150, 30));

		JPanel lvl23 = new JPanel();
		lvl23.add(label22);
		lvl23.add(idAccount2);
		idAccount2.setPreferredSize(new Dimension(150, 30));


		JPanel lvl24 = new JPanel();
		lvl24.add(label23);
		agency.setSelectedIndex(-1);
		agency.setPreferredSize(new Dimension(150, 30));
		new ServerMetier().selectAgency(agency2);
		lvl24.add(agency2);

		JPanel lvl25= new JPanel();
		lvl25.add(label24);
		String[] choix2 = { "","oui", "non"};
		JComboBox employee2 = new JComboBox(choix);
		employee2.setSelectedIndex(0);
		employee2.setPreferredSize(new Dimension(150, 30));
		lvl25.add(employee2);
		
		JPanel lvl26= new JPanel();
		lvl26.add(submit2);
		
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(7,1));
		panel3.add(lvl27);
		panel3.add(lvl21);
		panel3.add(lvl22);
		panel3.add(lvl23);
		panel3.add(lvl24);
		panel3.add(lvl25);
		panel3.add(lvl26);
		

		//onglets3 - Supression d'un client**************************************************************************
		JPanel panel4 = new JPanel();
		
		consumers2.setSelectedIndex(-1);
		consumers2.setPreferredSize(new Dimension(220, 30));
		submit3.addActionListener(this);
		new ServerMetier().selectClient(consumers2);
		
		consumers2.addFocusListener(new FocusListener()
		{
			public void focusLost(FocusEvent e)
			{
				
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				new ServerMetier().selectClient(consumers2);
			}
		});
		
		panel4.add(consumers2);
		panel4.add(submit3);

		//**********************************************************************************
		onglets.add("Ajouter un client", panel2);
		onglets.add("Modifier un client", panel3);
		onglets.add("Supprimer un client", panel4);

		panel.add(onglets);

		this.setContentPane(panel);
		this.setVisible(true);
	}

	public static void main (String [] args)
	{
		new Administration();
		
	}

	public void actionPerformed(ActionEvent e)
	{
		
		Object source = e.getSource();
		 
		if(source == submit1)
		{
			new ServerMetier().creerClient(name.getText(), firstName.getText(), Integer.parseInt(idAccount.getText()), agency.getSelectedIndex());
		} 
		else if(source == submit3)
		{
			//new ServerMetier().supprimerClient(consumers2.getSelectedIndex());
			String[] decoupe = consumers2.getSelectedItem().toString().split(" ");
			new ServerMetier().supprimerClient(Integer.parseInt(decoupe[0]));
		}
	}
}


