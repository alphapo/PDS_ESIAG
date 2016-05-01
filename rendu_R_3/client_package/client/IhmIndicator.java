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
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import client.tools.Communicator;
import service.clientService;

public class IhmIndicator extends JFrame{
	Communicator com;
	clientService cs;
	String user;
	JButton nbSimOk, nbLoanOk;
	JComboBox consumerBox;
	Hashtable htConsumers, htLoanType;
	JLabel nbSim, nbSimCrit, nbSimResConsumer, nbLoan, avgDurationLoan, avgAmountLoan, nbLoanCrit, avgDurationLoanCrit, avgAmountLoanCrit;
	JTextField date1, date2;
	JCheckBox box1;
	Color aColor;
	
	public Hashtable getHtConsumers() {
		return htConsumers;
	}


	public void setHtConsumers(Hashtable htConsumers) {
		this.htConsumers = htConsumers;
	}



	JPanel loanPanel;
	JPanel simulationPanel;
	
	//clientService lève des exceptions car utilisation de rmi
	public IhmIndicator(Communicator c, String login) throws MalformedURLException, RemoteException, NotBoundException{
		this.setTitle("Connecté en tant que : "+login);
		this.initialize();
		com=c;
		user=login;

	}
	
	
	public void initialize() throws RemoteException, MalformedURLException, NotBoundException{
		setVisible(true);
		setSize(600,600);
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

	    JCheckBox dateNbLoan = new JCheckBox("Date ");
	    JCheckBox dateAvgLoan = new JCheckBox("Date ");
	    JCheckBox dateAvgAmountLoan = new JCheckBox("Date ");

	    
	    JCheckBox consumerNbLoan = new JCheckBox("Client ");
	    JCheckBox consumerAvgLoan = new JCheckBox("Client ");
	    JCheckBox consumerAvgAmountLoan = new JCheckBox("Client");
	    
	    
	    dateNbLoan.setBackground(aColor);
	    dateAvgLoan.setBackground(aColor);
	    dateAvgAmountLoan.setBackground(aColor);
	    consumerNbLoan.setBackground(aColor);
	    consumerAvgLoan.setBackground(aColor);
	    consumerAvgAmountLoan.setBackground(aColor);
	    
	    
	    ItemListener it = new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				Object source = e.getItemSelectable();

				try {
					
				if ((source == dateNbLoan)){
					if (e.getStateChange() == ItemEvent.SELECTED){
						cs.saveDate(date1.getText(), date2.getText());
						nbLoanCrit.setText(Integer.toString(cs.receiveNbLoan(true)));
					}
					else
						nbLoanCrit.setText("Aucun crière sélectionné");
				}
				else if (source == dateAvgLoan) {
					if (e.getStateChange() == ItemEvent.SELECTED){
						cs.saveDate(date1.getText(), date2.getText());
						avgDurationLoanCrit.setText(Float.toString(cs.receiveAvgDurationLoan(true))+" année(s)");
					}
					else
						avgDurationLoanCrit.setText("Aucun crière sélectionné");

				} else if (source == dateAvgAmountLoan) {
					if (e.getStateChange() == ItemEvent.SELECTED){
						cs.saveDate(date1.getText(), date2.getText());
						avgAmountLoanCrit.setText(Float.toString(cs.receiveAvgAmountLoan(true))+" euros");
					}
					else
						avgAmountLoanCrit.setText("Aucun crière sélectionné");
			    }

//			    else if (source == box1) {
//			    }
				
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}	
	    };

	    dateNbLoan.addItemListener(it);
	    dateAvgLoan.addItemListener(it);
	    dateAvgAmountLoan.addItemListener(it);

		
//							ITEM/Datas SETTINGS
//--------------------------------------------------------------------------
		
	    
		JLabel title = new JLabel("----------------- PRET -----------------", SwingConstants.CENTER);

		nbLoan = new JLabel();	
		avgDurationLoan = new JLabel();
		avgAmountLoan= new JLabel();
		
		nbLoanCrit = new JLabel("Aucun crière sélectionné");	
		avgDurationLoanCrit = new JLabel("Aucun crière sélectionné");
		avgAmountLoanCrit= new JLabel("Aucun crière sélectionné");
		
		addItem(pLoan, title,0, 0,5,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		
//		addItem(pLoan, new JButton("Actualiser"),0, 5,2,1, gc, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE);
		
		gc.insets = new Insets(5,5,5,5);
		addItem(pLoan, new JLabel("Indicateur"),1, 0,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL );
		addItem(pLoan, new JLabel("Résultat (Total)"),1, 1,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pLoan, new JLabel("Critère", SwingConstants.CENTER),1, 2,2,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pLoan, new JLabel("Résultat par critère"),1, 4,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		
		
		addItem(pLoan, new JLabel("Nombre de pret : "),2,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pLoan, nbLoan,2,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pLoan, dateNbLoan,2,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pLoan, consumerNbLoan,2,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pLoan, nbLoanCrit,2,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		
		
		addItem(pLoan, new JLabel("Durée moyenne des prets : "),3,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pLoan, avgDurationLoan,3,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pLoan, dateAvgLoan,3,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pLoan, consumerAvgLoan,3,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pLoan, avgDurationLoanCrit,3,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		
		
		addItem(pLoan, new JLabel("Montant moyen des prets : "),4,0,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pLoan, avgAmountLoan,4,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pLoan, dateAvgAmountLoan,4,2,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pLoan, consumerAvgAmountLoan,4,3,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pLoan, avgAmountLoanCrit,4,4,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		
		//Currently values of Database
		try {
			nbLoan.setText(Integer.toString(cs.receiveNbLoan(false)));
			avgDurationLoan.setText(Float.toString(cs.receiveAvgDurationLoan(false))+" année(s)");
			avgAmountLoan.setText(Float.toString(cs.receiveAvgAmountLoan(false))+" euros");

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
						nbSimCrit.setText(Integer.toString(cs.receiveNbSimulation(true)));
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
		
		
		
//		addItem(pSim, nbSimOk,2,5,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);

//		addItem(pSim, nbSimResDate,2,6,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		
//		addItem(pSim, new JLabel("Par client : "),3,1,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);



//		addItem(pSim, returnConsumerBox(),3,2,2,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);

//		addItem(pSim, nbSimResConsumer,3,6,1,1,gc,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		
//		
//		nbSimOk.addActionListener(new ActionListener() {
//			int i=0;
//	        public void actionPerformed(ActionEvent e)
//	        {
//	        	if((invalidDataMessage(date1.getText())&& invalidDataMessage(date2.getText()))){
//	        		try {
//		        		
//						cs = new clientService();
//						i = cs.receiveSimulationDate(date1.getText(), date2.getText());
//						nbSimResDate.setText(Integer.toString(i));
//						
//					} catch (Exception e1) {
//						e1.printStackTrace();
//					}
//	        	}
//	        
//	            }
//		});
		
		try {
			nbSim.setText(Integer.toString(cs.receiveNbSimulation(false)));

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
		
		gc.insets = new Insets(5,5,5,5);
		addItem(pInfo, title,0, 0,5,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addItem(pInfo, new JLabel("Indiquer critère "),1, 0,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, new JLabel("Période : "),2, 0,1,1, gc, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL);
		addItem(pInfo, date1,2,1,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);

		addItem(pInfo, new JLabel("au"),2,2,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		addItem(pInfo, date2,2,3,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);

		addItem(pInfo, new JLabel(" "),2,4,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		
		addItem(pInfo, new JLabel("Client : "),3, 0,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, returnConsumerBox(),3, 1,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, new JLabel("Type de prêt : "),4, 0,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		addItem(pInfo, returnLoanTypeBox(),4, 1,1,1, gc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);

		return pInfo;
	}
	
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

//	        comboBox.addActionListener(new ActionListener(){
//	        	public void actionPerformed(ActionEvent e){
//	        		 Item item = (Item)comboBox.getSelectedItem();
//	        	     try {
//	        	    	 nbSimResConsumer.setText(Integer.toString((cs.receiveNbSimulationPerConsumer(item.getId()))));
//	        	     } catch (RemoteException | SQLException e1) {
//						e1.printStackTrace();
//					}
//	        	       
//	        	}
//	        });
	      
		return comboBox;
	}
	
	public boolean invalidDataMessage(String str){
		int res=0;
		
		if ((str.matches("\\d{4}-\\d{2}-\\d{2}")) && !(str.isEmpty())) {
		    return true;
		}
		else
			res = JOptionPane.showOptionDialog(null, "Format de date incorrect ! \n AAAA-MM-JJ", "Ok", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		
			return false;
		
	}

	
	//add and set item in ihm
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
	    
//	    Border Black;
//	    Black = BorderFactory.createLineBorder(Color.black);
//	    c.setBorder(Black);
	    p.add(c, gc);
	  }

	
	
	public static void main(String [] args){
		try {
			new IhmIndicator(new Communicator(), "Usman");
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
	}
	

	    public void actionPerformed(ActionEvent e)
	    {
	        JComboBox comboBox = (JComboBox)e.getSource();
	        Item item = (Item)comboBox.getSelectedItem();
	        System.out.println( item.getId() + " : " + item.getDescription() );
	    }
	    
	    
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



}
