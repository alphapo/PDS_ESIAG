package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Hashtable;

public interface InformationsIndicators extends Remote{

	public String getInformation() throws RemoteException;

	public Hashtable<Integer, String> getHashCustomers() throws RemoteException;
	
	public Hashtable<Integer, String> getHashLoanType() throws RemoteException;

	public int nbSimulationPerDate(String dateInf, String dateSup) throws RemoteException;

	public int nbSimulation(boolean date, boolean loanTypeId) throws RemoteException;

	public int nbSimulationPerConsumer(int idConsumer) throws RemoteException;

	public float avgDurationLoan(boolean date,boolean loanTypeId) throws RemoteException;

	public float avgAmountLoan(boolean date, boolean loanTypeId) throws RemoteException;

	public int nbLoan(boolean date, boolean loanTypeId) throws RemoteException;

	public float nbInterest(boolean date, boolean loanTypeId) throws RemoteException;
	
	public void saveDate(String dateInf, String dateSup) throws RemoteException;
	
	public void saveLoanTypeId(int id) throws RemoteException;


}