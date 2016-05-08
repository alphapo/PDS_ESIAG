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
  
	public Hashtable<Integer, String> getHashCustomers(){
		return HandleClient.hashCustomers();
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
	
	//Save the date to use it in Features
	public void saveDate(String d1, String d2){
		HandleClient.setDate1(d1);
		HandleClient.setDate2(d2);
	}
	
	//Save the LoanTypeId to use it in Features	
	public void saveLoanTypeId(int id){
		HandleClient.setLoanTypeId(id);
	}
}