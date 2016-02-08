import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class Identification extends JFrame implements ActionListener
{
	//initialisation des variables à communiquer au server
	private String identifiant;
	private String password;
	private ClientManage cm;
	private boolean pass;
	//private char[] password = null;
	
	public boolean getPass() {
		return this.pass;
	}


	public void setPass(boolean pass) {
		this.pass = pass;
	}



	private JPanel panel = new JPanel();
	//initialisation label des champs texte
	private JLabel labelLogin= new JLabel("Identifiant");;
	private JLabel labelMdp = new JLabel("Mot de passe");
	//initialisation champs texte
	private JTextField login = new JTextField();
	private JPasswordField mdp = new JPasswordField();
	//bouton
	private JButton submit = new JButton("envoyer");

	
	public Identification(ClientManage cm)
	{	this.cm=cm;
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
	    
	    //création de différent layout pour positionner les champs et bouton
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
	
	
	public String getIdentifiant() {
		return identifiant;
	}


	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void actionPerformed(ActionEvent e) 
	{
		identifiant = login.getText();
		password = String.valueOf(mdp.getPassword());
		
		//si l'un des champ d'identification est vide affichage d'un message d'alerte
		if (identifiant.isEmpty()|| password.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Veuillez renseigner les champs permettant de vous identifier");
		}
		else 
			{	//On stocke les données pour que le clienrt les récupère afin de les envoyer au serveur.
				this.setPassword(password);
				this.setIdentifiant(identifiant);
//				System.out.println(this.getPassword());
//				new Client(identifiant, password);
				this.setPass(true);
				
			}
	}
	

	
}