package client;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import client.tools.Communicator;
import service.ClientService;

public class IhmIndicator extends JFrame{
	Communicator com;
	ClientService cs;
	String user, agency;
	JButton nbSimOk, nbLoanOk;
	JComboBox consumerBox, loanTypeBox, genderBox, loanTypeNbLoanBox;
	Hashtable htConsumers, htLoanType;
	int loanType, genderId;
	//JLabel for result without criteria
	JLabel nbSim, avgAge, nbUser, nbCons, nbSimResConsumer, nbLoan, avgDurationLoan, avgAmountLoan, interest, maxRate, minRate, avgRate;
	//JLabel for result with criteria
	JLabel nbSimCrit, nbLoanCrit, avgDurationLoanCrit, avgAmountLoanCrit, interestCrit, maxRateCrit, minRateCrit, avgRateCrit, nbUserCrit, avgAgeCrit  ,nbConsCrit;
	JTextField date1, date2;
	JCheckBox box1;
	Color aColor;

	public Hashtable<Integer, String> getHtConsumers() {
		return htConsumers;
	}


	public void setHtConsumers(Hashtable<Integer, String> htConsumers) {
		this.htConsumers = htConsumers;
	}



	JPanel loanPanel;
	JPanel simulationPanel;
	
	//clientService lève des exceptions car utilisation de rmi
	public IhmIndicator(Communicator c, String login) throws MalformedURLException, RemoteException, NotBoundException{
		user="JACQUIER";
		try {
			cs = new ClientService();
			cs.saveAgency(user);
			agency=cs.getAgency();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		this.initialize(cs);
		this.setTitle("Connecté en tant que : "+login+" - Agence : "+agency );

//		try {
////			UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		com=c;

		date1.setText("2016-04-06");
		date2.setText("2016-06-06");

	}


	public void initialize(ClientService cs) throws RemoteException, MalformedURLException, NotBoundException{
		setVisible(true);
		setSize(800,600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		this.getContentPane().add(initializeInfoPanel(cs), BorderLayout.SOUTH);	
		this.getContentPane().add(initializeLoanPanel(cs), BorderLayout.NORTH);
		this.getContentPane().add(initializeSimulationPanel(cs), BorderLayout.CENTER);	
		updateLabelData();

		validate();

	}


//--------------------------------------------------------------------------
//								LOAN PANEL
//--------------------------------------------------------------------------


	public JPanel initializeLoanPanel(ClientService cs){
		JPanel pLoan = new JPanel(new GridBagLayout());
		pLoan.setPreferredSize(new Dimension(500,200));
		Color aColor = Color.decode("#99FFCC");
		pLoan.setBackground(aColor);
		GridBagConstraints gc = new GridBagConstraints();
	
//							JCheckBox SETTINGS
//--------------------------------------------------------------------------

	    JCheckBox dateNbLoanBox = new JCheckBox();
	    JCheckBox dateAvgLoanBox = new JCheckBox();
	    JCheckBox dateAvgAmountLoanBox = new JCheckBox();
	    JCheckBox dateInterestBox = new JCheckBox();
	    JCheckBox dateMaxRateBox = new JCheckBox();
	    JCheckBox dateMinRateBox = new JCheckBox();
	    JCheckBox dateAvgRateBox = new JCheckBox();

	    JCheckBox loanTypeNbLoanBox = new JCheckBox();
	    JCheckBox loanTypeAvgLoanBox = new JCheckBox();
	    JCheckBox loanTypeAvgAmountLoanBox = new JCheckBox();
	    JCheckBox loanTypeInterestBox = new JCheckBox();
	    JCheckBox loanTypeMaxRateBox = new JCheckBox();
	    JCheckBox loanTypeMinRateBox = new JCheckBox();
	    JCheckBox loanTypeAvgRateBox = new JCheckBox();



	    loanTypeAvgLoanBox.setSelected(true);
	    loanTypeAvgAmountLoanBox.setSelected(true);
	    loanTypeInterestBox.setSelected(true);
	    loanTypeMaxRateBox.setSelected(true);
	    loanTypeMinRateBox.setSelected(true);
	    loanTypeAvgRateBox.setSelected(true);
	    
	    loanTypeAvgLoanBox.setEnabled(false);
	    loanTypeAvgAmountLoanBox.setEnabled(false);
	    loanTypeInterestBox.setEnabled(false);
	    loanTypeMaxRateBox.setEnabled(false);
	    loanTypeMinRateBox.setEnabled(false);
	    loanTypeAvgRateBox.setEnabled(false);

	    dateNbLoanBox.setBackground(aColor);
	    dateAvgLoanBox.setBackground(aColor);
	    dateAvgAmountLoanBox.setBackground(aColor);
	    dateInterestBox.setBackground(aColor);
	    
	     loanTypeNbLoanBox.setBackground(aColor);
	     loanTypeAvgLoanBox.setBackground(aColor);
	     loanTypeAvgAmountLoanBox.setBackground(aColor);
	     loanTypeInterestBox.setBackground(aColor);
	     loanTypeMaxRateBox.setBackground(aColor);
	     loanTypeMinRateBox.setBackground(aColor);
	     loanTypeAvgRateBox.setBackground(aColor);
	     dateMaxRateBox.setBackground(aColor);
		 dateMinRateBox.setBackground(aColor);
		 dateAvgRateBox.setBackground(aColor);
//	    consumerNbLoanBox.setBackground(aColor);
//	    consumerAvgLoanBox.setBackground(aColor);
//	    consumerAvgAmountLoanBox.setBackground(aColor);
//	    consumerInterestBox.setBackground(aColor);



	    ItemListener it = new ItemListener(){
	    	public void itemStateChanged(ItemEvent e) {
	    		Object source = e.getItemSelectable();				
	    		try {
	    			//Date and loanTypeId will send by RMI methods
	    			//Then, some features can use these datas with RMI methods
	    			cs.saveDate(date1.getText(), date2.getText());
	    			cs.saveLoanTypeId(getIdLoanType(loanTypeBox));//					nbLoanCrit.setText(Integer.toString(cs.receiveNbLoan(e.getStateChange()==ItemEvent.SELECTED)));							

	    			//	    			avgDurationLoanCrit.setText(Float.toString(cs.receiveAvgDurationLoan(e.getStateChange()==ItemEvent.SELECTED, true))+" année(s)");

	    			if ((source == dateNbLoanBox && validDateMessage())){
	    				nbLoanCrit.setText(Integer.toString(cs.receiveNbLoan(e.getStateChange() == ItemEvent.SELECTED, loanTypeNbLoanBox.isSelected())));
	    			}
	    			else if ((source == loanTypeNbLoanBox && validDateMessage())){
	    				nbLoanCrit.setText(Integer.toString(cs.receiveNbLoan(dateNbLoanBox.isSelected(), e.getStateChange() == ItemEvent.SELECTED)));
	    			}
	    			else if (source == dateAvgLoanBox && validDateMessage()) {
	    				avgDurationLoanCrit.setText(Float.toString(cs.receiveAvgDurationLoan(e.getStateChange() == ItemEvent.SELECTED, true))+" année(s)");
	    			}
	    			else if (source == dateAvgAmountLoanBox && validDateMessage()) {
	    				avgAmountLoanCrit.setText(Float.toString(cs.receiveAvgAmountLoan(e.getStateChange() == ItemEvent.SELECTED, true))+" euros");
	    			}
	    			else if (source == dateInterestBox && validDateMessage()) {
	    				interestCrit.setText(Float.toString(cs.receiveNbInterest(e.getStateChange() == ItemEvent.SELECTED, true))+" euros");
	    			}
	    			else if (source == loanTypeInterestBox && validDateMessage()) {
	    				interestCrit.setText(Float.toString(cs.receiveNbInterest(dateInterestBox.isSelected(), true))+" euros");
	    			}
	    			else if (source == dateMaxRateBox && validDateMessage()) {
	    				maxRateCrit.setText(Float.toString(cs.receiveMaxRate(dateMaxRateBox.isSelected(), true))+" %");
	    			}
	    			else if (source == dateMinRateBox && validDateMessage()) {
	    				minRateCrit.setText(Float.toString(cs.receiveMinRate(dateMinRateBox.isSelected(), true))+" %");
	    			}
	    			else if (source == dateAvgRateBox && validDateMessage()) {
	    				avgRateCrit.setText(Float.toString(cs.receiveAvgRate(dateAvgRateBox.isSelected(), true))+" %");
	    			}
	    			
	
//			    else if (source == LoanTypeNbLoanBox) {
//			    	if(e.getStateChange() == ItemEvent.SELECTED){
//						nbLoanCrit.setText(Integer.toString(cs.receiveNbLoan(true)));
//			    	}
//			    	else
//			    		System.out.println("non coché !");
	    			//			    	
	    			//			    }

	    		} catch (Exception e1) {
	    			e1.printStackTrace();
	    		}
	    	}	
	    };

	    dateNbLoanBox.addItemListener(it);
	    dateAvgLoanBox.addItemListener(it);
	    dateAvgAmountLoanBox.addItemListener(it);
	    dateInterestBox.addItemListener(it);
	    dateMaxRateBox.addItemListener(it);
	    dateMinRateBox.addItemListener(it);
	    dateAvgRateBox.addItemListener(it);
	    

	    loanTypeNbLoanBox.addItemListener(it);
	    //	    loanTypeAvgLoanBox.addItemListener(it);
	    //	    loanTypeAvgAmountLoanBox.addItemListener(it);
	    loanTypeInterestBox.addItemListener(it);


	    //							ITEM/Datas SETTINGS
	    //--------------------------------------------------------------------------


	    JLabel title = new JLabel("----------------- PRET -----------------", SwingConstants.CENTER);

	    nbLoan = new JLabel();	
	    avgDurationLoan = new JLabel();
	    avgAmountLoan= new JLabel();
	    interest = new JLabel();
	    maxRate = new JLabel();
	    minRate = new JLabel();
	    avgRate = new JLabel();


	    nbLoanCrit = new JLabel("Aucun crière sélectionné");	
	    avgDurationLoanCrit = new JLabel();
	    avgAmountLoanCrit= new JLabel();
	    interestCrit= new JLabel();
	    maxRateCrit = new JLabel();
	    minRateCrit = new JLabel();
	    avgRateCrit = new JLabel();
	    
	    
	    addItem(pLoan, title,0, 0,5,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

	    //		addItem(pLoan, new JButton("Actualiser"),0, 5,2,1, gc, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE);

	    gc.insets = new Insets(5,5,5,5);
	    addItem(pLoan, new JLabel("Indicateur"),1, 0,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL );
	    addItem(pLoan, new JLabel("Résultat (Total)"),1, 1,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, new JLabel("Par date", SwingConstants.CENTER),1, 2,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
//	    addItem(pLoan, new JLabel("Par client", SwingConstants.CENTER),1, 3,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, new JLabel("Par type de prêt", SwingConstants.CENTER),1,3,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);	    
//	    addItem(pLoan, new JLabel("Par sexe", SwingConstants.CENTER),1,5,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);	    
	    addItem(pLoan, new JLabel("Résultat par critère"),1, 4,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);


	    addItem(pLoan, new JLabel("Nombre de pret : "),2,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, nbLoan,2,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, dateNbLoanBox,2,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, loanTypeNbLoanBox,2,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, nbLoanCrit,2,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);


	    addItem(pLoan, new JLabel("Durée moyenne des prets : "),3,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, new JLabel("-"),3,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, dateAvgLoanBox,3,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, loanTypeAvgLoanBox,3,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, avgDurationLoanCrit,3,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);


	    addItem(pLoan, new JLabel("Montant moyen des prets : "),4,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, new JLabel("-"),4,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, dateAvgAmountLoanBox,4,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, loanTypeAvgAmountLoanBox,4,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, avgAmountLoanCrit,4,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

	    addItem(pLoan, new JLabel("Intérêts precues par la banque : "),5,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, new JLabel("-"),5,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, dateInterestBox,5,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, loanTypeInterestBox,5,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, interestCrit,5,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

	    
	    addItem(pLoan, new JLabel("Taux max: "),6,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, new JLabel("-"),6,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, dateMaxRateBox,6,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, loanTypeMaxRateBox,6,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, maxRateCrit,6,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

	    
	    addItem(pLoan, new JLabel("Taux min : "),7,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, new JLabel("-"),7,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, dateMinRateBox,7,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, loanTypeMinRateBox,7,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, minRateCrit,7,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

	    
	    addItem(pLoan, new JLabel("Taux moyens : "),8,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, new JLabel("-"),8,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, dateAvgRateBox,8,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, loanTypeAvgRateBox,8,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, avgRateCrit,8,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

	    
	    
	    
	    
	    
	    
	    

		//Currently values of Database
		try {
			nbLoan.setText(Integer.toString(cs.receiveNbLoan(false, false)));
//			avgAmountLoan.setText(Float.toString(cs.receiveAvgAmountLoan(false, false)));
//			avgDurationLoan.setText(Float.toString(cs.receiveAvgDurationLoan(false, false)));
//			avgAmountLoan.setText(Float.toString(cs.receiveAvgAmountLoan(false, false)));
//			interest.setText(Float.toString(cs.receiveNbInterest(false, false)));

			//			avgDurationLoan.setText(Float.toString(cs.receiveAvgDurationLoan(false, getIdLoanType(loanTypeBox)))+" année(s)");
//			avgAmountLoan.setText(Float.toString(cs.receiveAvgAmountLoan(false, true))+" euros");
//			interest.setText(Float.toString(cs.receiveNbInterest(false, true))+" euros");
		} catch (Exception e) {
			e.printStackTrace();
		}

		CheckboxGroup cbg = new CheckboxGroup();
		add(new Checkbox("one", cbg, true));


		return pLoan;
	}

//--------------------------------------------------------------------------
//							SIMULATION PANEL
//--------------------------------------------------------------------------



	public JPanel initializeSimulationPanel(ClientService cs){
		JPanel pSim = new JPanel(new GridBagLayout());
		pSim.setPreferredSize(new Dimension(500,200));
		aColor = Color.decode("#99FFCC");
		pSim.setBackground(aColor);
		GridBagConstraints gc = new GridBagConstraints();



		//							JCheckBox SETTINGS
		//--------------------------------------------------------------------------

		
		
		JCheckBox dateNbSimBox = new JCheckBox();
		JCheckBox genderConsBox = new JCheckBox();
		JCheckBox genderUserBox = new JCheckBox();
	    JCheckBox loanTypeNbSimBox = new JCheckBox();
	    JCheckBox genderAvgNbAgeBox = new JCheckBox();

	    
	    genderAvgNbAgeBox.setBackground(aColor);
	    dateNbSimBox.setBackground(aColor);
	    genderConsBox.setBackground(aColor);
	    genderUserBox.setBackground(aColor);
	    loanTypeNbSimBox.setBackground(aColor);


		dateNbSimBox.setBackground(aColor);
//		consumerNbSim.setBackground(aColor);

		//	    JCheckBox dateAvgLoan = new JCheckBox("Date ");
		//	    JCheckBox dateAvgAmountLoan = new JCheckBox("Date ");


		ItemListener it = new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				Object source = e.getItemSelectable();

				try {

					if ((source == dateNbSimBox)){
						if (e.getStateChange() == ItemEvent.SELECTED){
							cs.saveDate(date1.getText(), date2.getText());
							nbSimCrit.setText(Integer.toString(cs.receiveNbSimulation(false, false)));
						}
						else
							nbSimCrit.setText("Aucun crière sélectionné");
					}
					else if ((source == genderConsBox)){
						if (e.getStateChange() == ItemEvent.SELECTED){
							cs.saveGenderId(getGenderId(genderBox));
							nbConsCrit.setText(Integer.toString(cs.receiveNbConsumer(e.getStateChange() == ItemEvent.SELECTED, genderConsBox.isSelected())));
						}
						else
							nbConsCrit.setText("Aucun crière sélectionné");
					}
					
					
					else if ((source == genderUserBox)){
						if (e.getStateChange() == ItemEvent.SELECTED){
							cs.saveGenderId(getGenderId(genderBox));
							nbUserCrit.setText(Integer.toString(cs.receiveNbUsers(e.getStateChange() == ItemEvent.SELECTED, genderUserBox.isSelected())));
						}
						else
							nbUserCrit.setText("Aucun crière sélectionné");

					}
					
					else if ((source == loanTypeNbSimBox)){
						if (e.getStateChange() == ItemEvent.SELECTED){
							cs.saveDate(date1.getText(), date2.getText());
			    			cs.saveLoanTypeId(getIdLoanType(loanTypeBox));
							nbSimCrit.setText(Integer.toString(cs.receiveNbSimulation(loanTypeNbSimBox.isSelected(),e.getStateChange() == ItemEvent.SELECTED)));
						}
						else
							nbSimCrit.setText("Aucun crière sélectionné");

					}
					
					else if ((source == genderAvgNbAgeBox)){
						if (e.getStateChange() == ItemEvent.SELECTED){
							cs.saveGenderId(getGenderId(genderBox));
							avgAgeCrit.setText(Float.toString(cs.receiveAvgAgeConsumer(genderAvgNbAgeBox.isSelected(),e.getStateChange() == ItemEvent.SELECTED)));
						}
						else
							avgAgeCrit.setText("Aucun crière sélectionné");

					}
//	    			else if ((source == loanTypeNbLoanBox && validDateMessage())){
//	    				nbLoanCrit.setText(Integer.toString(cs.receiveNbLoan(dateNbLoanBox.isSelected(), e.getStateChange() == ItemEvent.SELECTED)));
//	    			}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}	
		};

	    dateNbSimBox.addItemListener(it);
	    genderConsBox.addItemListener(it);
	    genderUserBox.addItemListener(it);
	    loanTypeNbSimBox.addItemListener(it);
	    genderAvgNbAgeBox.addItemListener(it);

	    
	    
	    //							ITEM/Datas SETTINGS
	    //--------------------------------------------------------------------------


	    JLabel title = new JLabel("----------------- SIMULATION -----------------");

	    nbSimOk = new JButton("Ok");
	    consumerBox = new JComboBox();
	    nbSim = new JLabel();
	    nbCons = new JLabel();
	    nbUser= new JLabel();
	    avgAge= new JLabel();
	    
	    nbSimCrit = new JLabel("Aucun critère sélectionné");
	    nbUserCrit = new JLabel("Aucun critère sélectionné");
	    nbConsCrit = new JLabel("Aucun critère sélectionné");
	    avgAgeCrit = new JLabel("Aucun critère sélectionné");


	    nbSimResConsumer = new JLabel();

	    //		timeBox = new JComboBox();
	    //		timeBox = returnPeriodOfTimeBox();


	    addItem(pSim, title,0, 2,5,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

	    gc.insets = new Insets(5,5,5,5);
	    addItem(pSim, new JLabel("Indicateur"),1, 0,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL );
	    addItem(pSim, new JLabel("Résultat (Total)"),1, 1,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, new JLabel("Par Date", SwingConstants.CENTER),1, 2,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, new JLabel("Par type de prêt", SwingConstants.CENTER),1, 3,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, new JLabel("Par genre", SwingConstants.CENTER),1, 4,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

	    addItem(pSim, new JLabel("Résultat par critère"),1, 5,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);


	    addItem(pSim, new JLabel("Nombre de simulation : "),2,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
	    addItem(pSim, nbSim,2,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, dateNbSimBox,2,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, loanTypeNbSimBox,2,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, new JLabel("-"),2,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, nbSimCrit,2,5,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

	    
	    addItem(pSim, new JLabel("Nombre d'utilisateurs : "),3,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
	    addItem(pSim, nbUser,3,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, new JLabel("-"),3,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, new JLabel("-"),3,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, genderUserBox,3,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, nbUserCrit,3,5,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

	    
	    addItem(pSim, new JLabel("Nombre de clients : "),4,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
	    addItem(pSim, nbCons,4,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, new JLabel("-"),4,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, new JLabel("-"),4,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, genderConsBox,4,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, nbConsCrit,4,5,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    
	    addItem(pSim, new JLabel("Age moyen des clients : "),5,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
	    addItem(pSim, avgAge,5,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, new JLabel("-"),5,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, new JLabel("-"),5,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, genderAvgNbAgeBox,5,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, avgAgeCrit,5,5,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

	    try {
	    	nbSim.setText(Integer.toString(cs.receiveNbSimulation(false, false)));
	    	nbUser.setText(Integer.toString(cs.receiveNbUsers(false, false)));
	    	nbCons.setText(Integer.toString(cs.receiveNbConsumer(false, false)));
	    	avgAge.setText(Float.toString(cs.receiveAvgAgeConsumer(false, false)));


	    } catch (Exception e) {
	    	e.printStackTrace();
	    }


	    return pSim;
	}

	public JPanel initializeInfoPanel(ClientService cs){
		JPanel pInfo = new JPanel(new GridBagLayout());
		pInfo.setPreferredSize(new Dimension(500,200));
		aColor = Color.decode("#99CCFF");
		pInfo.setBackground(aColor);
		GridBagConstraints gc = new GridBagConstraints();


		JLabel title = new JLabel("*********************** Informations complémentaires ***********************", SwingConstants.CENTER);
		date1 = new JTextField();
		date2 = new JTextField();

		date1.setPreferredSize(new Dimension(80,20));
		date2.setPreferredSize(new Dimension(80,20));

		loanTypeBox = new JComboBox();
		loanTypeBox = returnLoanTypeBox(cs);

			
		genderBox = new JComboBox();
		genderBox = returnGenderBox();

		gc.insets = new Insets(5,5,5,5);
		addItem(pInfo, title,0, 0,5,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pInfo, new JLabel("Indiquer critère "),1, 0,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, new JLabel("Période : "),2, 0,1,1, gc, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL);
		addItem(pInfo, date1,2,1,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, new JLabel("au"),2,2,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pInfo, date2,2,3,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, new JLabel("Client : "),3, 0,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, returnConsumerBox(cs),3, 1,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, new JLabel("Type de prêt : "),4, 0,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, loanTypeBox,4, 1,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, new JLabel("Re-cocher le(s) critère(s) pour prendre en compte le type de pret."),4, 2,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, new JLabel("Genre : "),5, 0,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, genderBox,5, 1,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		
		return pInfo;
	}

	
	//Handle Consumer Box
	//It contains key, value structure
	//subClass were implemented to manage this box
	public JComboBox returnConsumerBox(ClientService cs){

			try {
				htConsumers = cs.receiveUsersHashtable();
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}


		JComboBox comboBox = new JComboBox();

		Iterator<Map.Entry>  it;
		Map.Entry            entry;

		it = htConsumers.entrySet().iterator();
		while (it.hasNext()) {
			entry = it.next();
			comboBox.addItem(new Item(Integer.parseInt(entry.getKey().toString()), entry.getValue().toString()));
		}

		Item selected_item = (Item) comboBox.getSelectedItem();

		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Item item = (Item)comboBox.getSelectedItem();
				try {
					nbSimResConsumer.setText(Integer.toString((cs.receiveNbSimulationPerConsumer(item.getId()))));
				} catch (RemoteException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		return comboBox;
	}

	
	public JComboBox returnLoanTypeBox(ClientService cs){

		try {
			htLoanType = cs.receiveLoanTypeHashtable();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		JComboBox comboBox = new JComboBox();

		Iterator<Map.Entry>  it;
		Map.Entry            entry;

		it = htLoanType.entrySet().iterator();
		while (it.hasNext()) {
			entry = it.next();
			comboBox.addItem(new Item(Integer.parseInt(entry.getKey().toString()), entry.getValue().toString()));
		}

		Item selected_item = (Item) comboBox.getSelectedItem();

			        comboBox.addActionListener(new ActionListener(){
			        	public void actionPerformed(ActionEvent e){
			        		 Item item = (Item)comboBox.getSelectedItem();
			        	     try {
			        	    	 System.out.println(item.getId());
			        	    	 updateLabelData();
			        	     } catch (Exception e1) {
								e1.printStackTrace();
							}
			        	       
			        	}
			        });

		return comboBox;
	}
	
	public JComboBox returnGenderBox(){

		genderBox = new JComboBox();

		Iterator<Map.Entry>  it;
		Map.Entry            entry;
		
		Hashtable gender = new Hashtable();
		gender.put(0, "Femme");
		gender.put(1, "Homme");
		
		
		it = gender.entrySet().iterator();
		while (it.hasNext()) {
			entry = it.next();
			genderBox.addItem(new Item(Integer.parseInt(entry.getKey().toString()), entry.getValue().toString()));
		}

		Item selected_item = (Item) genderBox.getSelectedItem();

		genderBox.addActionListener(new ActionListener(){
			        	public void actionPerformed(ActionEvent e){
			        		 Item item = (Item)genderBox.getSelectedItem();
			        	     try {
//			        	    	 nbSimResConsumer.setText(Integer.toString((cs.receiveNbSimulationPerConsumer(item.getId()))));
			        	    	 System.out.println(item.getId());
			        	    	 updateLabelData();
			        	     } catch (Exception e1) {
								e1.printStackTrace();
							}
			        	       
			        	}
			        });

		return genderBox;
	}

	public int getIdLoanType(JComboBox j){
		Item selected_item = (Item) j.getSelectedItem();
		Item item = (Item)j.getSelectedItem();
		loanType = item.getId();
		return this.loanType;
	}
	
	public int getGenderId(JComboBox j){
		Item selected_item = (Item) j.getSelectedItem();
		Item item = (Item)j.getSelectedItem();
		genderId = item.getId();
		return this.genderId;
	}

	public void updateLabelData(){
	
		try {
			cs.saveDate(date1.getText(), date2.getText());
			cs.saveLoanTypeId(getIdLoanType(loanTypeBox));//
			cs.saveGenderId(getGenderId(genderBox));//

			
			
			avgDurationLoanCrit.setText(Float.toString(cs.receiveAvgDurationLoan(false, true))+" année(s)");
			avgAmountLoanCrit.setText(Float.toString(cs.receiveAvgAmountLoan(false, true))+" euros");
			interestCrit.setText(Float.toString(cs.receiveNbInterest(false, true))+"euros");
			maxRateCrit.setText(Float.toString(cs.receiveMaxRate(false, true))+" %");
			minRateCrit.setText(Float.toString(cs.receiveMinRate(false, true))+" %");
			avgRateCrit.setText(Float.toString(cs.receiveAvgRate(false, true))+" %");

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public boolean validDateMessage(){

		if ((((date1.getText().matches("\\d{4}-\\d{2}-\\d{2}")) && !(date1.getText().isEmpty()))) && (((date2.getText().matches("\\d{4}-\\d{2}-\\d{2}")) && !(date2.getText().isEmpty())))) {
			return true;
		}

		else if(date1.getText().isEmpty() || date2.getText().isEmpty()){
			int res = JOptionPane.showOptionDialog(null, "Champ date vide ! \n AAAA-MM-JJ", "Ok", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
			return false;
		}
		else{
			int res2 = JOptionPane.showOptionDialog(null, "Format de date incorrect ! \n AAAA-MM-JJ", "Erreur date", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

			return false;
		}
	}

	
	//add and set item in ihm with a GridBagLayout
	public void addItem(JPanel p, JComponent c, int x, int y,int gridwidth, int gridheight, GridBagConstraints
			gc, int align, int fill) {
		//		  Random rnd = new Random();

		gc.gridx = y;
		gc.gridy = x;
		gc.fill = fill;
		gc.gridwidth = gridwidth;
		gc.gridheight = gridheight;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = align;
		//	    gc.insets = new Insets(2,0,2,0);

			    Border Black;
			    Black = BorderFactory.createLineBorder(Color.black);
//			    c.setBorder(Black);
		p.add(c, gc);
	}

	public void actionPerformed(ActionEvent e)
	{
		JComboBox consumerBox = (JComboBox)e.getSource();
		Item item = (Item)consumerBox.getSelectedItem();
		System.out.println( item.getId() + " : " + item.getDescription() );
	}

	//This class sets key, value datas in Consumer's JComboBox
	class Item
	{
		private int id;
		private String description;

		public Item(int id, String description)
		{
			this.id = id;
			this.description = description;
		}

		public int getId()
		{
			return id;
		}

		public String getDescription()
		{
			return description;
		}

	        public String toString()
	        {
	        	return description;
	        }
	    }

//	    //This class manages the list in comboBox (focus, getid, get description...)
//	    class ItemRenderer extends BasicComboBoxRenderer
//	    {
//	    	public Component getListCellRendererComponent(
//	    			JList list, Object value, int index,
//	    			boolean isSelected, boolean cellHasFocus)
//	    	{
//	    		super.getListCellRendererComponent(list, value, index,
//	    				isSelected, cellHasFocus);
//
//	    		if (value != null)
//	    		{
//	    			Item item = (Item)value;
//	    			setText( item.getDescription().toUpperCase() );
//	    		}
//
//	    		if (index == -1)
//	    		{
//	    			Item item = (Item)value;
//	    			setText( "" + item.getId() );
//	    		}
//
//
//	    		return this;
//	    	}
//	    }


//	    public static void main(String [] args){
//	    	try {
//	    		new IhmIndicator(new Communicator(), "Usman");
//	    	} catch (Exception e) {
//
//	    		e.printStackTrace();
//	    	}
//	    }

}
