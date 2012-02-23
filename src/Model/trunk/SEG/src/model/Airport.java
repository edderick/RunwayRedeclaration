package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Airport {
	
	//List of runways
	private ArrayList<Runway> runways;
	private String name;
	
	//Constructor
	public Airport(String n){
		runways = new ArrayList<Runway>();
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
	//Method to add runway to the list
	public void addRunway(Runway r){
		runways.add(r);
	}
	
	//Method to remove runway from the list
	public void removeRunway(String n){
		
		for (Iterator i = runways.iterator(); i.hasNext(); ) {
		    Object element = i.next();

		    if (((Runway) element).getName() == n) {
		        i.remove();
		    }
		}
	}
	
	//Method that returns the whole list of runways
	public ArrayList runways(){
		return runways;
	}
	
	public String toString(){
		String s = "Airport name: " + name +"\n";
		
		for(int i=0; i < runways.size(); i++){
			s += runways.get(i) + "\n";
		}
		
		return s;
	}
	
	public void saveToXML(){
		try {
			XMLFile f = new XMLFile(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
