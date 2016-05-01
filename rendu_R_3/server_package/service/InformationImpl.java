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
	
	public int nbSimulation(boolean date){
		return HandleClient.nbSimulation(date);
	}
	
	public int nbSimulationPerDate(String dateInf, String dateSup){
		return HandleClient.nbSimulationPerDate(dateInf,dateSup);
	}
	
	public int nbSimulationPerConsumer(int idConsumer){
		return HandleClient.nbSimulationPerConsumer(idConsumer);
	}
	
	public float avgDurationLoan(boolean date){
		return HandleClient.avgDurationLoan(date);
	}
	
	public int nbLoan(boolean date){
		return HandleClient.nbLoan(date);
	}
	
	public float avgAmountLoan(boolean date){
		return HandleClient.avgAmountLoan(date);
	}
	
	public void saveDate(String d1, String d2){
//		HandleClient.getDate(d1, d2);
		HandleClient.setDate1(d1);
		HandleClient.setDate2(d2);
	}
}