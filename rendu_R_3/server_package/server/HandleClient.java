package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.util.Hashtable;

import beans.Authentification;
import features.Features;
import task.Task;
import tools.ConnectionPool;
import tools.ServerParserJson;


public class HandleClient extends Thread {
	private static String date1, date2;
	private static int loanTypeId;
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
				case "ADD_CLIENT_SIMULATION":  result = Task.simulationLaunched(jsonContent, connection);
				break;
				case "GET_DURATIONDATA":  result = Task.getDurationDataLaunched(jsonContent, connection);
				break;
				}

				out.println(result);
			}
		}catch(IOException e){
			finish();
		}
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

	public static Hashtable<Integer, String> hashCustomers(){
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

	public static int nbLoan(boolean date, boolean loanTypeId){
		return Features.nbLoan(connection, date, loanTypeId);
	}

	public static float nbInterest(boolean date, boolean loanTypeId){
		return Features.nbInterest(connection, date, loanTypeId);
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
}
