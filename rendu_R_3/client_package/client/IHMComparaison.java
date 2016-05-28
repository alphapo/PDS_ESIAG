package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import client.tools.Communicator;

import service.clientService;

public class IhmComparaison extends JFrame implements ActionListener{

	private JPanel panel = new JPanel();
	JComboBox simuType = new JComboBox();
	JComboBox consumerComboBox = new JComboBox();
	JButton btnOk;
	JTable simulation;
	
	HashMap<Integer, String> hmap;
	Object consumer;
	Set set;
	Iterator iterator;
	Map.Entry mentry;
	
	DefaultTableModel model;

	public IhmComparaison(Communicator c)
	{
		try 
		{

			UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e)
		{

		}

		panel.setBackground(Color.white);

		setTitle("Compare simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setBounds(100, 100, 600, 500);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_contentPane);

		//		JComboBox simuType = new JComboBox();
		GridBagConstraints gbc_simuType = new GridBagConstraints();
		gbc_simuType.insets = new Insets(30, 30, 30, 30);
		gbc_simuType.fill = GridBagConstraints.HORIZONTAL;
		gbc_simuType.gridx = 4;
		gbc_simuType.gridy = 2;

		//		new ServerMetier().selectLoantype(simuType);
		try {
			clientService cs = new clientService();
			simuType = cs.selectLoantypeBox();
		} catch (Exception e1) {
			e1.printStackTrace();
		}


		panel.add(simuType, gbc_simuType);



		//		JComboBox consumerComboBox = new JComboBox();
		GridBagConstraints gbc_consumer = new GridBagConstraints();
		consumerComboBox.setPreferredSize(new Dimension(200, 25));
		//		gbc_simuType.insets = new Insets(30, 30, 30, 30);
		gbc_consumer.insets = new Insets(0, 0, 5, 0);
		//		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_consumer.gridx = 6;
		gbc_consumer.gridy = 2;
//		try {
//			clientService cs2 = new clientService();
//			consumerComboBox = cs2.selectConsumerBox();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		
		
		hmap = new HashMap<Integer, String>();
		
		try {
		clientService cs3 = new clientService();
		hmap = cs3.selectConsumerBox2();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		consumer = null;
		set = hmap.entrySet();
		iterator = set.iterator();
        consumerComboBox.addItem("");
	      while(iterator.hasNext()) {
	          mentry = (Map.Entry)iterator.next();
	          consumer =  mentry.getValue();
//	          System.out.println(consumer);
	          consumerComboBox.addItem(consumer);
	       }
		
		panel.add(consumerComboBox, gbc_consumer);
		
		btnOk = new JButton("Ok");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.insets = new Insets(0, 0, 5, 0);
		gbc_btnOk.gridx = 8;
		gbc_btnOk.gridy = 2;
		btnOk.addActionListener(this);
		panel.add(btnOk, gbc_btnOk);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridheight = 4;
		gbc_panel_1.gridwidth = 8;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 4;

		String[] columnNames = {"Simulation Number","Name","Duration","Number","interest Rate"};
		Object[][] data = {{"", "","", "", ""},{"", "","", "", ""},{"", "","", "", ""},{"", "","", "", ""},{"", "","", "", ""},{"", "","", "", ""},{"", "","", "", ""},{"", "","", "", ""},{"", "","", "", ""},{"", "","", "", ""}};
		
		 model = new DefaultTableModel(data, columnNames);
		simulation = new JTable(model);
		
				simulation = new JTable(data, columnNames);
				TableColumn column = null;
				for (int i = 0; i < 5; i++)
				{
				    column = simulation.getColumnModel().getColumn(i);
				    column.setPreferredWidth(115);
				}
		JTableHeader header = simulation.getTableHeader();

		
//				try {
//					clientService cs3 = new clientService();
//					simulation = cs3.selectSimulation(simuType.getSelectedIndex(), hmap.get(consumerComboBox.getSelectedIndex()));
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		panel3.add(header, BorderLayout.NORTH);
		panel3.add(simulation, BorderLayout.CENTER);
		panel_1.add(panel3, simulation);
		panel.add(panel_1, gbc_panel_1);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.LAST_LINE_END;
		gbc_panel_2.gridx = 8;
		gbc_panel_2.gridy = 8;
		JButton btnCompar = new JButton("Comparez");
		panel_2.setBackground(Color.white);
		panel_2.add(btnCompar);
		panel.add(panel_2, gbc_panel_2);
		
	
		setContentPane(panel);
		setVisible(true);



	}

	public void actionPerformed(ActionEvent e) 
	{

		Object source = e.getSource();
		if(source == btnOk)
		{
			{
				Iterator iterator2 = set.iterator();
				
				while(iterator2.hasNext())
				{
//					mentry = (Map.Entry)iterator2.next();
//					System.out.println(mentry.getValue());
//					System.out.println(consumerComboBox.getSelectedItem());
					if (mentry.getValue().equals(consumerComboBox.getSelectedItem())) 
					{
//						System.out.println("ok");
//						System.out.println(mentry.getKey());
//						System.out.println(simuType.getSelectedIndex());
						
						try {
						clientService cs3 = new clientService();
						simulation.setModel(cs3.selectSimulation(simuType.getSelectedIndex(), (int) mentry.getKey()));
						TableColumn column = null;
						for (int i = 0; i < 5; i++)
						{
						    column = simulation.getColumnModel().getColumn(i);
						    column.setPreferredWidth(115);
						}
		                model.fireTableDataChanged();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
						
						break;
						
					}
				} 
			}


		}
	}


}
