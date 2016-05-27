package service;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

import server.HandleClient;

public class InformationImpl extends UnicastRemoteObject implements InformationsIndicators{
	private static final long serialVersionUID = 2674880711467464646L;


	public InformationImpl() throws RemoteException {
		super();
	}

  public String getInformation() {
    System.out.println("Invocation de la méthode getInformation()");
    return "bonjour";
  }
  
	public Hashtable<Integer, String> getHashConsumer(){
		return HandleClient.hashConsumer();
	}
	
	public Hashtable<Integer, String> getHashLoanType(){
		return HandleClient.hashLoanType();
	}
	
	public int nbSimulation(boolean date, boolean loanTypeId){
		return HandleClient.nbSimulation(date, loanTypeId);
	}
	
	public int nbSimulationPerDate(String dateInf, String dateSup){
		return HandleClient.nbSimulationPerDate(dateInf,dateSup);
	}
	
	public int nbSimulationPerConsumer(int idConsumer){
		return HandleClient.nbSimulationPerConsumer(idConsumer);
	}
	
	public float avgDurationLoan(boolean date, boolean loanTypeId){
		return HandleClient.avgDurationLoan(date, loanTypeId);
	}
	
	public int nbLoan(boolean date, boolean loanTypeId){
		return HandleClient.nbLoan(date, loanTypeId);
	}
	
	public float avgAmountLoan(boolean date, boolean loanTypeId){
		return HandleClient.avgAmountLoan(date, loanTypeId);
	}
	
	public float nbInterest(boolean date, boolean loanTypeId){
		return HandleClient.nbInterest(date, loanTypeId);
	}
	
	public float maxRate(boolean date, boolean loanTypeId){
		return HandleClient.maxRate(date, loanTypeId);
	}

	public float minRate(boolean date, boolean loanTypeId){
		return HandleClient.minRate(date, loanTypeId);
	}
	
	public float avgRate(boolean date, boolean loanTypeId){
		return HandleClient.avgRate(date, loanTypeId);
	}	
	
	public float avgAgeConsumer(boolean date, boolean loanTypeId){
		return HandleClient.avgAgeConsumer(date, loanTypeId);
	}	
	
	//Save the date to use it in Features
	public void saveDate(String d1, String d2){
		HandleClient.setDate1(d1);
		HandleClient.setDate2(d2);
	}
	
	//Save the LoanTypeId to use it in Features	
	public void saveLoanTypeId(int id){
		HandleClient.setLoanTypeId(id);
	}
	
	//Save gender to use it in Features	
	public void saveGenderId(int id){
		HandleClient.setGenderId(id);
	}
	
	public void saveAgency(String user){
		HandleClient.setAgency(user);
	}
	
	public String getAgency(){
		return HandleClient.getAgency();
	}
	public void sendRate(double rate, int idLoanType){
		HandleClient.sendRate(rate, idLoanType);
	}

	public int nbUsers(boolean date, boolean gender) throws RemoteException {
		return HandleClient.nbUser(date, gender);
	}

	public int nbConsumer(boolean date, boolean gender) throws RemoteException {		
		return HandleClient.nbConsumer(date, gender);
	}


}