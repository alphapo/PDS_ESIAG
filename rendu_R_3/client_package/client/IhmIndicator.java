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
import service.clientService;

public class IhmIndicator extends JFrame{
	Communicator com;
	clientService cs;
	String user;
	JButton nbSimOk, nbLoanOk;
	JComboBox consumerBox, loanTypeBox;
	Hashtable htConsumers, htLoanType;
	int loanType;
	JLabel nbSim, nbSimCrit, nbSimResConsumer, nbLoan, avgDurationLoan, avgAmountLoan, interest, nbLoanCrit, avgDurationLoanCrit, avgAmountLoanCrit, interestCrit;
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
		this.setTitle("Connecté en tant que : "+login);
		this.initialize();
		try {
			UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		com=c;
		user=login;
		date1.setText("2016-04-06");
		date2.setText("2016-06-06");

	}


	public void initialize() throws RemoteException, MalformedURLException, NotBoundException{
		setVisible(true);
		setSize(800,600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		this.getContentPane().add(initializeLoanPanel(), BorderLayout.NORTH);
		this.getContentPane().add(initializeSimulationPanel(), BorderLayout.CENTER);	
		this.getContentPane().add(initializeInfoPanel(), BorderLayout.SOUTH);	


		validate();

	}


//--------------------------------------------------------------------------
//								LOAN PANEL
//--------------------------------------------------------------------------


	public JPanel initializeLoanPanel(){
		JPanel pLoan = new JPanel(new GridBagLayout());
		pLoan.setPreferredSize(new Dimension(500,200));
		Color aColor = Color.decode("#99FFCC");
		pLoan.setBackground(aColor);
		GridBagConstraints gc = new GridBagConstraints();
		try {
			cs = new clientService();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
//							JCheckBox SETTINGS
//--------------------------------------------------------------------------

	    JCheckBox dateNbLoanBox = new JCheckBox("Date ");
	    JCheckBox dateAvgLoanBox = new JCheckBox("Date ");
	    JCheckBox dateAvgAmountLoanBox = new JCheckBox("Date ");
	    JCheckBox dateInterestBox = new JCheckBox("Date ");

	    
	    JCheckBox consumerNbLoanBox = new JCheckBox("Client ");
	    JCheckBox consumerAvgLoanBox = new JCheckBox("Client ");
	    JCheckBox consumerAvgAmountLoanBox = new JCheckBox("Client");
	    JCheckBox consumerInterestBox = new JCheckBox("Client");

	    
	    JCheckBox loanTypeNbLoanBox = new JCheckBox("Type de pret ");
	    JCheckBox loanTypeAvgLoanBox = new JCheckBox("Type de pret ");
	    JCheckBox loanTypeAvgAmountLoanBox = new JCheckBox("Type de pret");
	    JCheckBox loanTypeInterestBox = new JCheckBox("Type de pret");


	    loanTypeAvgLoanBox.setSelected(true);
	    loanTypeAvgAmountLoanBox.setSelected(true);
	    loanTypeInterestBox.setSelected(true);
	    
	    loanTypeAvgLoanBox.setEnabled(false);
	    loanTypeAvgAmountLoanBox.setEnabled(false);
	    loanTypeInterestBox.setEnabled(false);

	    dateNbLoanBox.setBackground(aColor);
	    dateAvgLoanBox.setBackground(aColor);
	    dateAvgAmountLoanBox.setBackground(aColor);
	    dateInterestBox.setBackground(aColor);
	    consumerNbLoanBox.setBackground(aColor);
	    consumerAvgLoanBox.setBackground(aColor);
	    consumerAvgAmountLoanBox.setBackground(aColor);
	    consumerInterestBox.setBackground(aColor);



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


	    nbLoanCrit = new JLabel("Aucun crière sélectionné");	
	    avgDurationLoanCrit = new JLabel();
	    avgAmountLoanCrit= new JLabel();
	    interestCrit= new JLabel();

	    addItem(pLoan, title,0, 0,5,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

	    //		addItem(pLoan, new JButton("Actualiser"),0, 5,2,1, gc, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE);

	    gc.insets = new Insets(5,5,5,5);
	    addItem(pLoan, new JLabel("Indicateur"),1, 0,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL );
	    addItem(pLoan, new JLabel("Résultat (Total)"),1, 1,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, new JLabel("Critère", SwingConstants.CENTER),1, 2,3,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, new JLabel("Résultat par critère"),1, 5,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);


	    addItem(pLoan, new JLabel("Nombre de pret : "),2,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, nbLoan,2,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, dateNbLoanBox,2,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, consumerNbLoanBox,2,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, loanTypeNbLoanBox,2,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, nbLoanCrit,2,5,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);


	    addItem(pLoan, new JLabel("Durée moyenne des prets : "),3,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, avgDurationLoan,3,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, dateAvgLoanBox,3,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, consumerAvgLoanBox,3,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, loanTypeAvgLoanBox,3,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, avgDurationLoanCrit,3,5,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);


	    addItem(pLoan, new JLabel("Montant moyen des prets : "),4,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, avgAmountLoan,4,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, dateAvgAmountLoanBox,4,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, consumerAvgAmountLoanBox,4,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, loanTypeAvgAmountLoanBox,4,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, avgAmountLoanCrit,4,5,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

	    addItem(pLoan, new JLabel("Intérêts precues par la banque : "),5,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, interest,5,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, dateInterestBox,5,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, consumerInterestBox,5,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, loanTypeInterestBox,5,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pLoan, interestCrit,5,5,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);


		//Currently values of Database
		try {
			nbLoan.setText(Integer.toString(cs.receiveNbLoan(false, false)));
			//			avgDurationLoan.setText(Float.toString(cs.receiveAvgDurationLoan(false, getIdLoanType(loanTypeBox)))+" année(s)");
			avgAmountLoan.setText(Float.toString(cs.receiveAvgAmountLoan(false, true))+" euros");
			interest.setText(Float.toString(cs.receiveNbInterest(false, true))+" euros");

			avgDurationLoanCrit.setText(Float.toString(cs.receiveAvgDurationLoan(false, true))+" annee(s)");
			avgAmountLoanCrit.setText(Float.toString(cs.receiveAvgAmountLoan(false, true))+" euros");
			interestCrit.setText(Float.toString(cs.receiveNbInterest(false, true))+" euros");


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



	public JPanel initializeSimulationPanel(){
		JPanel pSim = new JPanel(new GridBagLayout());
		pSim.setPreferredSize(new Dimension(500,200));
		aColor = Color.decode("#99FFCC");
		pSim.setBackground(aColor);
		GridBagConstraints gc = new GridBagConstraints();



		//							JCheckBox SETTINGS
		//--------------------------------------------------------------------------

		JCheckBox dateNbSim = new JCheckBox("Date ");
		JCheckBox consumerNbSim = new JCheckBox("Client ");

		dateNbSim.setBackground(aColor);
		consumerNbSim.setBackground(aColor);

		//	    JCheckBox dateAvgLoan = new JCheckBox("Date ");
		//	    JCheckBox dateAvgAmountLoan = new JCheckBox("Date ");


		ItemListener it = new ItemListener(){
	    	public void itemStateChanged(ItemEvent e) {
	    		Object source = e.getItemSelectable();

	    		try {

	    			if ((source == dateNbSim)){
	    				if (e.getStateChange() == ItemEvent.SELECTED){
	    					cs.saveDate(date1.getText(), date2.getText());
	    					nbSimCrit.setText(Integer.toString(cs.receiveNbSimulation(false, false)));
	    				}
	    				else
	    					nbSimCrit.setText("Aucun crière sélectionné");
	    			}

	    		} catch (Exception e1) {
	    			e1.printStackTrace();
	    		}
	    	}	
	    };

	    dateNbSim.addItemListener(it);


	    //							ITEM/Datas SETTINGS
	    //--------------------------------------------------------------------------


	    JLabel title = new JLabel("----------------- SIMULATION -----------------", SwingConstants.CENTER);

	    nbSimOk = new JButton("Ok");
	    consumerBox = new JComboBox();
	    nbSim = new JLabel();
	    nbSimCrit = new JLabel("Aucun critère sélectionné");

	    nbSimResConsumer = new JLabel();

	    //		timeBox = new JComboBox();
	    //		timeBox = returnPeriodOfTimeBox();


	    addItem(pSim, title,0, 0,5,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

	    gc.insets = new Insets(5,5,5,5);
	    addItem(pSim, new JLabel("Indicateur"),1, 0,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL );
	    addItem(pSim, new JLabel("Résultat (Total)"),1, 1,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, new JLabel("Critère", SwingConstants.CENTER),1, 2,2,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, new JLabel("Résultat par critère"),1, 4,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);


	    addItem(pSim, new JLabel("Nombre de simulation : "),2,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
	    addItem(pSim, nbSim,2,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, dateNbSim,2,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, consumerNbSim,2,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
	    addItem(pSim, nbSimCrit,2,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);



	    try {
	    	nbSim.setText(Integer.toString(cs.receiveNbSimulation(false, false)));

	    } catch (Exception e) {
	    	e.printStackTrace();
	    }


	    return pSim;
	}

	public JPanel initializeInfoPanel(){
		JPanel pInfo = new JPanel(new GridBagLayout());
		pInfo.setPreferredSize(new Dimension(500,200));
		aColor = Color.decode("#99CCFF");
		pInfo.setBackground(aColor);
		GridBagConstraints gc = new GridBagConstraints();


		JLabel title = new JLabel("----------------- Informations complémentaires -----------------", SwingConstants.CENTER);
		date1 = new JTextField();
		date2 = new JTextField();

		date1.setPreferredSize(new Dimension(80,20));
		date2.setPreferredSize(new Dimension(80,20));

		loanTypeBox = new JComboBox();
		loanTypeBox = returnLoanTypeBox();


		gc.insets = new Insets(5,5,5,5);
		addItem(pInfo, title,0, 0,5,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pInfo, new JLabel("Indiquer critère "),1, 0,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, new JLabel("Période : "),2, 0,1,1, gc, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL);
		addItem(pInfo, date1,2,1,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, new JLabel("au"),2,2,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pInfo, date2,2,3,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, new JLabel("Client : "),3, 0,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, returnConsumerBox(),3, 1,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, new JLabel("Type de prêt : "),4, 0,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, loanTypeBox,4, 1,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, new JLabel("Re-cocher le(s) critère(s) pour prendre en compte le type de pret."),4, 2,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		
		return pInfo;
	}

	
	//Handle Consumer Box
	//It contains key, value structure
	//subClass were implemented to manage this box
	public JComboBox returnConsumerBox(){

		try {
			cs = new clientService();
			htConsumers = cs.receiveCustomersHashtable();
		} catch (Exception e2) {
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

	
	public JComboBox returnLoanTypeBox(){

		try {
			cs = new clientService();
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
//			        	    	 nbSimResConsumer.setText(Integer.toString((cs.receiveNbSimulationPerConsumer(item.getId()))));
			        	    	 System.out.println(item.getId());
			        	    	 updateLabelData();
			        	     } catch (Exception e1) {
								e1.printStackTrace();
							}
			        	       
			        	}
			        });

		return comboBox;
	}

	public int getIdLoanType(JComboBox j){
		Item selected_item = (Item) j.getSelectedItem();
		//        j.addActionListener(new ActionListener(){
		//        	public void actionPerformed(ActionEvent e){
		//        		 Item item = (Item)j.getSelectedItem();
		//        	     try {
		//        	    	 loanType = item.getId();
		//        	    	 System.out.println(loanType);
		//        	     } catch (Exception e1) {
		//					e1.printStackTrace();
		//				}
		//        	       
		//        	}
		//        });
		Item item = (Item)j.getSelectedItem();
		loanType = item.getId();
		return this.loanType;
	}
	
	public void updateLabelData(){
	
		try {
			cs.saveDate(date1.getText(), date2.getText());
			cs.saveLoanTypeId(getIdLoanType(loanTypeBox));//
			
			avgDurationLoanCrit.setText(Float.toString(cs.receiveAvgDurationLoan(false, true)));
			avgAmountLoanCrit.setText(Float.toString(cs.receiveAvgAmountLoan(false, true)));
			interestCrit.setText(Float.toString(cs.receiveNbInterest(false, true)));
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
			int res2 = JOptionPane.showOptionDialog(null, "Format de date incorrect ! \n AAAA-MM-JJ", "Ok", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

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

//			    Border Black;
//			    Black = BorderFactory.createLineBorder(Color.black);
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

	    //This class manages the list in comboBox (focus, getid, get description...)
	    class ItemRenderer extends BasicComboBoxRenderer
	    {
	    	public Component getListCellRendererComponent(
	    			JList list, Object value, int index,
	    			boolean isSelected, boolean cellHasFocus)
	    	{
	    		super.getListCellRendererComponent(list, value, index,
	    				isSelected, cellHasFocus);

	    		if (value != null)
	    		{
	    			Item item = (Item)value;
	    			setText( item.getDescription().toUpperCase() );
	    		}

	    		if (index == -1)
	    		{
	    			Item item = (Item)value;
	    			setText( "" + item.getId() );
	    		}


	    		return this;
	    	}
	    }


	    public static void main(String [] args){
	    	try {
	    		new IhmIndicator(new Communicator(), "Usman");
	    	} catch (Exception e) {

	    		e.printStackTrace();
	    	}


	    }

}
