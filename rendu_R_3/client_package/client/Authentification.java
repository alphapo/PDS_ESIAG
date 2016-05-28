package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

<<<<<<< HEAD
import tools.ClientFactoryJson;
import tools.Communicator;
=======
import client.beans.ManageUc;
import client.tools.ClientFactoryJson;
import client.tools.Communicator;
>>>>>>> 7a6cb1ff9973eb2df612a2cd72a4103ee7d47dc0


public class Authentification extends JFrame implements ActionListener
{
	//initialisation des variables à communiquer au server
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
	//variable servant à derminer si on peut ouvrir socket client
	private boolean bool = true;


	public Authentification()
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
		{
			Communicator communicator = new Communicator();

			communicator.sendData(ClientFactoryJson.makeJSONauthentification(identifiant, password));
			if(communicator.receiveData().equals("OK") ) {

<<<<<<< HEAD
				//				int res = JOptionPane.showOptionDialog(null, "Vous etes connecté", "Test", JOptionPane.DEFAULT_OPTION,
				//						JOptionPane.INFORMATION_MESSAGE, null, null, null);
				//				System.out.println(res);
				//				if(res ==0){


				new client.InterClient();
			}
			//			}


			else{
=======
				int res = JOptionPane.showOptionDialog(null, "Vous etes connecté", "Test", JOptionPane.DEFAULT_OPTION,
				        JOptionPane.INFORMATION_MESSAGE, null, null, null);
				System.out.println(res);

				if(res ==0){
					//After the connetion, the user can choose an operation (indicator, simulation...)
					//Here, we have ihmIndicator
					try {
//						new ManageUc(communicator);
//						new Ihm(communicator);
//						new IhmIndicator(communicator, identifiant);
						new ManageUc(communicator, identifiant);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					this.dispose();
				}
				
	
			}else
>>>>>>> 7a6cb1ff9973eb2df612a2cd72a4103ee7d47dc0
				JOptionPane.showMessageDialog(null, "Vos identifiants ne sont pas corrects");
			}	
		}
	}
}
