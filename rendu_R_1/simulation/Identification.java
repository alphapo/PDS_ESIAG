package pds;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Identification extends JFrame implements ActionListener
{
	//initialisation des variables � communiquer au server
	private String identifiant = null;
	private String password = null;
	//private char[] password = null;
	
	private JPanel panel = new JPanel();
	//initialisation label des champs texte
	private JLabel labelLogin= new JLabel("Identifiant");;
	private JLabel labelMdp = new JLabel("Mot de passe");
	//initialisation champs texte
	private JTextField login = new JTextField();
	private JPasswordField mdp = new JPasswordField();
	//bouton
	private JButton submit = new JButton("envoyer");
	//variable servant � derminer si on peut ouvrir socket client
	private boolean bool = true;

	
	public Identification()
	{
	    this.setTitle("Identification");
	    this.setSize(400, 150);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    
	    panel.setBackground(Color.white);
	    panel.setLayout(new BorderLayout());
	    
	     //initialisation des champs textes
	    login.setPreferredSize(new Dimension(150, 30));
	    login.setForeground(Color.BLUE);
	    mdp.setPreferredSize(new Dimension(150, 30));
	    
	    //on indique au bouton que la fentre va ecouter ses actions
	    submit.addActionListener(this);
	    
	    //cr�ation de diff�rent layout pour positionner les champs et bouton
	    //1ere ligne
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(3,2));

		//initialisation des panels qui vont composer le GridLayout
		JPanel lvl1 = new JPanel();
		lvl1.add(labelLogin);
		lvl1.add(login);

		JPanel lvl2 = new JPanel();
		lvl2.add(labelMdp);
		lvl2.add(mdp);

		JPanel lvl3= new JPanel();
		lvl3.add(submit);
		
		panel2.add(lvl1);
		panel2.add(lvl2);
		panel2.add(lvl3);
	    
	    panel.add(panel2);
	    
	    this.setContentPane(panel);
	    this.setVisible(true);
	}
	
	
	public static void main(String[] args)
	{
		new Identification();
	}


	public void actionPerformed(ActionEvent e) 
	{
		identifiant = login.getText();
		password = String.valueOf(mdp.getPassword());

				Client clt = new Client();
				
				//si l'un des champ d'identification est vide affichage d'un message d'alerte
				if (identifiant.isEmpty()|| password.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Veuillez renseigner les champs permettant de vous identifier");
				}
				else 
				{
					clt.connection();
					clt.message(identifiant, password);
				}

			

	}
	
}
