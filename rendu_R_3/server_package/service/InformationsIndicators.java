package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public interface InformationsIndicators extends Remote{

	public String getInformation() throws RemoteException;

	public Hashtable<Integer, String> getHashConsumer() throws RemoteException;
	
	public Hashtable<Integer, String> getHashLoanType() throws RemoteException;

	public int nbSimulationPerDate(String dateInf, String dateSup) throws RemoteException;

	public int nbSimulation(boolean date, boolean loanTypeId) throws RemoteException;
	
	public int nbUsers(boolean date, boolean gender) throws RemoteException;

	public int nbConsumer(boolean date, boolean gender) throws RemoteException;
	
	public int nbSimulationPerConsumer(int idConsumer) throws RemoteException;

	public float avgDurationLoan(boolean date,boolean loanTypeId) throws RemoteException;

	public float avgAmountLoan(boolean date, boolean loanTypeId) throws RemoteException;

	public int nbLoan(boolean date, boolean loanTypeId) throws RemoteException;

	public float nbInterest(boolean date, boolean loanTypeId) throws RemoteException;
	
	public float maxRate(boolean date, boolean loanTypeId) throws RemoteException;

	public float minRate(boolean date, boolean loanTypeId) throws RemoteException;

	public float avgRate(boolean date, boolean loanTypeId) throws RemoteException;
	
	public float avgAgeConsumer(boolean date, boolean loanTypeId) throws RemoteException;

	public void saveDate(String dateInf, String dateSup) throws RemoteException;
	
	public void saveLoanTypeId(int id) throws RemoteException;

	public void saveGenderId(int id) throws RemoteException;

	public void saveAgency(String a) throws RemoteException;
	
	public String getAgency() throws RemoteException;
	
	public void sendRate(double rate, int idLoanType) throws RemoteException;
	
		public JComboBox selectLoantypeBox() throws RemoteException;
	
	public JComboBox selectConsumerBox() throws RemoteException;
	
	public HashMap selectConsumerBox2() throws RemoteException;
	
	public DefaultTableModel  selectSimulation(int id_user, int id_loantype) throws RemoteException;
}
