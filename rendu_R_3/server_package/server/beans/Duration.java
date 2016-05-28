package server.beans;


import java.util.ArrayList;
import java.util.HashMap;

//import javax.json.JsonValue;

public class Duration {
	HashMap<Double,Double> mapDurationMonth= new HashMap<>();
	
	public Duration() {
	}
	public void add(double year, double month){
		mapDurationMonth.put(year, month);
	}
	public ArrayList<Double>  getAllYears(){
		ArrayList<Double> listYear = new ArrayList<Double>();
		for ( double key : mapDurationMonth.keySet() ) {
		    listYear.add(key);
		}
		return listYear;
	}
	public double getMonth(double year){
		return mapDurationMonth.get(year).doubleValue();
	}
	public int size(){
		return mapDurationMonth.size();
	}
	
	
}
