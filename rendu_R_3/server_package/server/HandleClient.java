package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.util.Hashtable;

import features.Features;
import server.beans.Authentification;
import server.task.Task;
import server.tools.ConnectionPool;
import server.tools.ServerParserJson;


public class HandleClient extends Thread {
	private static String date1, date2;
	private static int loanTypeId, genderId;
	private static String user;
	private PrintWriter out;
	private BufferedReader in;
	final private Socket clientSocket ;
	private ConnectionPool connectionPool;
	private static Connection connection;
	private boolean stop = false;

	public HandleClient(final Socket clientSocket, ConnectionPool pool) throws IOException {
		this.clientSocket = clientSocket;
		connection = pool.getConnectionFromPool();
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		connectionPool = pool;
	}

	public void run() {
		System.out.println("client "+clientSocket.getInetAddress()+" tries to connect");
		String jsonContent; 
		String result = null;
		try{
			while(!stop){
				jsonContent = this.getContentJson(in);
				switch(getRequest(jsonContent)){
				case "AUTH":  result = Task.authentificationLaunched(jsonContent, connection);
				break;
				case "ADD_CLIENT": result = Task.addClientLaunched(jsonContent, connection);
				break;
				case "LOAN":  // Task for loan ...
					break;

				case "EXIT":  // Task when client exit...
					break;
				}

				out.println(result);
			}
		}catch(IOException e){
			finish();
		}
	}
	
	private String getContentJson(final BufferedReader in) throws IOException{
		return  in.readLine(); 
	}
	private String getRequest(String jsonContent){
		return ServerParserJson.getRequest(jsonContent);
	}
	public synchronized void finish() {
		if (!stop) {
			stop = true;
			try {
				System.out.println("client "+clientSocket.getInetAddress()+" is disconnected");
				clientSocket.close();
				connectionPool.returnConnectionToPool(connection);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	
	//---------------------------------------------------------
	//					Indicators methods
	//---------------------------------------------------------
	
	public static void setAgency(String user) {
		HandleClient.user = user;
		HandleClient.setServerLogin();
		System.out.println("Le login est : ---->"+getLoginUser());
	}
	
	public static String getDate1() {
		return date1;
	}

	public static void setDate1(String date1) {
		HandleClient.date1 = date1;
	}
	
	public static String getDate2() {
		return date2;
	}

	public static void setDate2(String date2) {
		HandleClient.date2 = date2;
	}
	
	public static int getLoanTypeId() {
		return loanTypeId;
	}

	public static void setLoanTypeId(int loanTypeId) {
		HandleClient.loanTypeId = loanTypeId;
	}
	
	public static int getGenderId() {
		return genderId;
	}

	public static void setGenderId(int genderId) {
		HandleClient.genderId= genderId;
	}
	
	public static Hashtable<Integer, String> hashConsumer(){
		return Features.getClient(connection);
	}
	
	public static Hashtable<Integer, String> hashLoanType(){
		return Features.getLoanType(connection);
	}
	
	public static int nbSimulationPerDate(String dateInf,String dateSup){
		return Features.nbSimulationPerDate(connection ,dateInf, dateSup);
	}
	
	public static int nbSimulationPerConsumer(int idConsumer){
		return Features.nbSimulationPerConsumer(connection ,idConsumer);
	}
	
	public static int nbSimulation(boolean date, boolean loanTypeId){
		return Features.nbSimulation(connection, date, loanTypeId);
	}
	
	public static int nbConsumer(boolean date, boolean gender){
		return Features.nbConsumer(connection, date, gender);
	}
	
	public static int nbUser(boolean date, boolean gender){
		return Features.nbUser(connection, date, gender);
	}
	
	public static int nbLoan(boolean date, boolean loanTypeId){
		return Features.nbLoan(connection, date, loanTypeId);
	}
	
	public static float nbInterest(boolean date, boolean loanTypeId){
		return Features.nbInterest(connection, date, loanTypeId);
	}
	
	public static float maxRate(boolean date, boolean loanTypeId){
		return Features.maxRate(connection, date, loanTypeId);
	}
	
	public static float minRate(boolean date, boolean loanTypeId){
		return Features.minRate(connection, date, loanTypeId);
	}
	
	public static float avgRate(boolean date, boolean loanTypeId){
		return Features.avgRate(connection, date, loanTypeId);
	}
	
	public static float avgAgeConsumer(boolean date, boolean loanTypeId){
		return Features.avgAgeConsumer(connection, loanTypeId);
	}
	
	public static float avgDurationLoan(boolean date, boolean loanTypeId){
		return Features.avgDurationLoan(connection, date, loanTypeId);
	}
	
	public static float avgAmountLoan(boolean date, boolean loanTypeId){
		return Features.avgAmountLoan(connection, date, loanTypeId);
	}
	
	public static String getDate(){
//		date1 = "2016/04/01";
		date1 = HandleClient.getDate1();
		date2 = HandleClient.getDate2();
		return "simulationDate >= \""+date1+"\" and simulationDate <= \""+date2+"\"";
	}
	
	public static int getLoanType(){
		int id = HandleClient.getLoanTypeId();
		return id;
	}
	
	public static String getGender(){
		int id = HandleClient.getGenderId();
		String gender;
		if(id==0)
			gender="F";
		else
			gender="M";
		System.out.println(gender);
		return gender;
	}
	
	public static void setServerLogin(){
		Features.setIdAgency(connection, HandleClient.user);
	}

	public static String getLoginUser(){
		return HandleClient.user;
	}

	public static String getAgency(){
		return Features.getAgency(connection);
	}
	
	public static void sendRate(double rate, int idLoanType){
		System.out.println(rate+" et "+idLoanType);
		Features.sendRate(connection, rate, idLoanType);

	}
}
