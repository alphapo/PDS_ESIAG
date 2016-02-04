package pds;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Simulation extends JFrame
{

	private JPanel panel = new JPanel();
	//initialisation des labels
	private JLabel label = new JLabel("Type de prêt");
	private JLabel label1 = new JLabel("Montant du prêt");
	private JLabel label2 = new JLabel("Durée du prêt");
	private JLabel label3 = new JLabel("Taux fixe");
	private JLabel label4 = new JLabel("Taux d'assurance");
	
	//initialisation des champs texte
	private JTextField montant = new JTextField();
	private JTextField duree = new JTextField();
	private JTextField tauxFixe = new JTextField();
	private JTextField tauxAssur = new JTextField();
	


	public Simulation ()
	{
		this.setTitle("Simulation prêt");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		panel.setBackground(Color.GRAY);
		panel.setLayout(new BorderLayout());

		String[] petStrings = { "crédit-bail", "Immobilier", "Consommation"};

		JComboBox typePret = new JComboBox(petStrings);
		typePret.setSelectedIndex(0);
		typePret.setPreferredSize(new Dimension(150, 30));

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(5,1));

		//initialisation des panels qui vont composer le GridLayout
		JPanel lvl1 = new JPanel();
		lvl1.add(label);
		lvl1.add(typePret);

		JPanel lvl2 = new JPanel();
		lvl2.add(label1);
		lvl2.add(montant);
		montant.setPreferredSize(new Dimension(150, 30));

		JPanel lvl3 = new JPanel();
		lvl3.add(label2);
		lvl3.add(duree);
		duree.setPreferredSize(new Dimension(150, 30));

		JPanel lvl4 = new JPanel();
		lvl4.add(label3);
		lvl4.add(tauxFixe);
		tauxFixe.setPreferredSize(new Dimension(150, 30));

		JPanel lvl5= new JPanel();
		lvl5.add(label4);
		lvl5.add(tauxAssur);
		tauxAssur.setPreferredSize(new Dimension(150, 30));
		
		panel2.add(lvl1);
		panel2.add(lvl2);
		panel2.add(lvl3);
		panel2.add(lvl4);
		panel2.add(lvl5);
		
		panel.add(panel2);

		this.setContentPane(panel);
		this.setVisible(true);
	}

	public static void main (String [] args)
	{
		new Simulation();
	}
}
