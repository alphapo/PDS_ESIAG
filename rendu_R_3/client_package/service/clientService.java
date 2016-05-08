package service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Hashtable;


public class clientService {
	static InformationsIndicators stub;
	
	public clientService() throws MalformedURLException, RemoteException, NotBoundException{
		stub= (InformationsIndicators)Naming.lookup("rmi://localhost:1099/IndicatorsRMI");
	}
	
	public Hashtable<Integer, String> receiveCustomersHashtable() throws RemoteException{
    	Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
    	ht = stub.getHashCustomers();		
    	return ht;
	}
	
	public Hashtable<Integer, String> receiveLoanTypeHashtable() throws RemoteException{
    	Hashtable<Integer, String> htLoan = new Hashtable<Integer, String>();
    	htLoan = stub.getHashLoanType();		
    	return htLoan;
	}
	
	public int receiveNbSimulation(boolean date, boolean loanTypeId) throws RemoteException, SQLException{	
    	return stub.nbSimulation(date, loanTypeId);
	}
	
	public int receiveSimulationDate(String dateInf, String dateSup) throws RemoteException, SQLException{	
    	return stub.nbSimulationPerDate(dateInf, dateSup);
	}
	
	public int receiveNbSimulationPerConsumer(int idConsumer) throws RemoteException, SQLException{	
    	return stub.nbSimulationPerConsumer(idConsumer);
	}
	
	public float receiveAvgDurationLoan(boolean date, boolean loanTypeId) throws RemoteException, SQLException{	
    	return stub.avgDurationLoan(date, loanTypeId);
	}
	
	public float receiveAvgAmountLoan(boolean date, boolean loanTypeId) throws RemoteException, SQLException{	
    	return stub.avgAmountLoan(date, loanTypeId);
	}
	
	public int receiveNbLoan(boolean date, boolean loanTypeId ) throws RemoteException, SQLException{	
    	return stub.nbLoan(date, loanTypeId);
	}
	
	public float receiveNbInterest(boolean date, boolean loanTypeId ) throws RemoteException, SQLException{	
    	return stub.nbInterest(date, loanTypeId);
	}

	public void saveDate(String dateInf, String dateSup) throws RemoteException {
		stub.saveDate( dateInf,  dateSup);
	}
	public void saveLoanTypeId(int id) throws RemoteException {
		stub.saveLoanTypeId(id);
	}
	

}