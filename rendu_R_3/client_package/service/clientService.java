package service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Hashtable;


public class ClientService {
	static InformationsIndicators stub;
	
	public ClientService() throws MalformedURLException, RemoteException, NotBoundException{
		stub= (InformationsIndicators)Naming.lookup("rmi://localhost:1099/IndicatorsRMI");
	}
	
	public Hashtable<Integer, String> receiveUsersHashtable() throws RemoteException{
    	Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
    	ht = stub.getHashConsumer();		
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
	
	public int receiveNbUsers(boolean date, boolean gender) throws RemoteException, SQLException{	
    	return stub.nbUsers(date, gender);
	}
	
	public int receiveNbConsumer(boolean date, boolean gender) throws RemoteException, SQLException{	
    	return stub.nbConsumer(date, gender);
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
	
	public float receiveMaxRate(boolean date, boolean loanTypeId ) throws RemoteException, SQLException{	
    	return stub.maxRate(date, loanTypeId);
	}
	
	public float receiveMinRate(boolean date, boolean loanTypeId ) throws RemoteException, SQLException{	
    	return stub.minRate(date, loanTypeId);
	}
	
	public float receiveAvgRate(boolean date, boolean loanTypeId ) throws RemoteException, SQLException{	
    	return stub.avgRate(date, loanTypeId);
	}
	
	public float receiveAvgAgeConsumer(boolean date, boolean loanTypeId ) throws RemoteException, SQLException{	
    	return stub.avgAgeConsumer(date, loanTypeId);
	}

	public void saveDate(String dateInf, String dateSup) throws RemoteException {
		stub.saveDate( dateInf,  dateSup);
	}
	public void saveLoanTypeId(int id) throws RemoteException {
		stub.saveLoanTypeId(id);
	}
	
	public void saveGenderId(int id) throws RemoteException {
		stub.saveGenderId(id);
	}
	
	public void saveAgency(String a) throws RemoteException{
		stub.saveAgency(a);
	}
	public String getAgency() throws RemoteException{
		return stub.getAgency();
	}
	
	public void receiveSendRate(double rate, int idLoanType) throws RemoteException{
		stub.sendRate(rate, idLoanType);
	}

	

}