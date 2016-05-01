package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Hashtable;

public interface InformationsIndicators extends Remote{

	public String getInformation() throws RemoteException;
	public Hashtable<Integer, String> getHashCustomers() throws RemoteException;
	public Hashtable<Integer, String> getHashLoanType() throws RemoteException;
	public int nbSimulationPerConsumer(int idConsumer) throws RemoteException;
	public int nbSimulation(boolean date) throws RemoteException;
	public float avgDurationLoan(boolean date) throws RemoteException;
	public float avgAmountLoan(boolean date) throws RemoteException;
	public int nbLoan(boolean date) throws RemoteException;
	public void saveDate(String dateInf, String dateSup) throws RemoteException;
}
