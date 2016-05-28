package client.beans;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import client.Ihm;
import client.IhmIndicator;
import client.tools.Communicator;


public class ManageUc extends JFrame implements ActionListener{
	private JButton indicator, calculateRate, compare; 
	Communicator com;
	String log;

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Communicator getCom() {
		return com;
	}

	public void setCom(Communicator com) {
		this.com = com;
	}

	public ManageUc(Communicator c, String login) {
		com=c;
		log=login;
		this.setTitle("ADMINISTRATION");
		this.setSize(400, 200);
		this.setVisible(true);
		this.getContentPane().setLayout(new FlowLayout());
		this.initializeButton();
		this.setResizable(false);
		this.validate();
	}
	
	public void initializeButton(){
		indicator = new JButton("Statistiques");
		calculateRate = new JButton("calculateRate");
		compare = new JButton("compare");

		indicator.addActionListener(this);
		calculateRate.addActionListener(this);
//		JLabel title = new JLabel("ADMINISTRATION");
	    Border Black;
	    Black = BorderFactory.createLineBorder(Color.black);

	    JTabbedPane tabbedPane = new JTabbedPane();

	    JPanel buttonRow = new JPanel();
	    // Use default FlowLayout.
	    buttonRow.add(createButtonRow(true));
	    tabbedPane.addTab("Sélectionner la tâche à exécuter", buttonRow);

	    JPanel labelAndComponent = new JPanel();
	    // Use default FlowLayout.
	    labelAndComponent.add(createLabelAndComponent(false));
	    labelAndComponent.add(createLabelAndComponent(true));

	    // Add tabbedPane to this panel.
	    add(tabbedPane, BorderLayout.CENTER);
	
	}

//	public static void main(String[] args) {
//		new ManageUc(new Communicator(), "test");
//	}

	public void actionPerformed(ActionEvent e) {
		try {
		if(e.getSource().equals(indicator)){
				new IhmIndicator(this.getCom(), this.getLog());
		}
		else if(e.getSource().equals(calculateRate)){
			new Ihm(this.getCom());
			System.out.println("Calcul taux");
		}
		else if(e.getSource().equals(compare)){

			System.out.println("Comparaison");
		}
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
	}
	
	 protected JPanel createButtonRow(boolean changeAlignment) {
//			indicator = new JButton("Statistiques");
//			calculateRate = new JButton("calculateRate");
//			compare = new JButton("compare");

//		    indicator = new JButton("");
		    indicator.setVerticalTextPosition(AbstractButton.BOTTOM);
		    indicator.setHorizontalTextPosition(AbstractButton.CENTER);

//			calculateRate = new JButton("calculateRate");
		    calculateRate.setVerticalTextPosition(AbstractButton.BOTTOM);
		    calculateRate.setHorizontalTextPosition(AbstractButton.CENTER);
		    
//			compare = new JButton("compare");
		    compare.setVerticalTextPosition(AbstractButton.BOTTOM);
		    compare.setHorizontalTextPosition(AbstractButton.CENTER);

		    String title;
		      title = "Sélectionner ";
		      indicator.setAlignmentY(BOTTOM_ALIGNMENT);
		      calculateRate.setAlignmentY(BOTTOM_ALIGNMENT);
		      compare.setAlignmentY(BOTTOM_ALIGNMENT);


		    JPanel pane = new JPanel();
		    pane.setBorder(BorderFactory.createTitledBorder(title));
		    pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
		    pane.add(indicator);
		    pane.add(calculateRate);
		    pane.add(compare);

		    return pane;
		  }
	
	  protected JPanel createLabelAndComponent(boolean doItRight) {
		    JPanel pane = new JPanel();

		    JComponent component = new JPanel();
		    Dimension size = new Dimension(150, 100);
		    component.setMaximumSize(size);
		    component.setPreferredSize(size);
		    component.setMinimumSize(size);
		    TitledBorder border = new TitledBorder(new LineBorder(Color.black), "A JPanel",
		        TitledBorder.CENTER, TitledBorder.BELOW_TOP);
		    border.setTitleColor(Color.black);
		    component.setBorder(border);

		    JLabel label = new JLabel("This is a JLabel");
		    String title;
		    if (doItRight) {
		      title = "Matched";
		      label.setAlignmentX(CENTER_ALIGNMENT);
		    } else {
		      title = "Mismatched";
		    }

		    pane.setBorder(BorderFactory.createTitledBorder(title));
		    pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		    pane.add(label);
		    pane.add(component);
		    return pane;
		  }

}
