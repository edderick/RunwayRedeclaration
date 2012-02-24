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
	
	public void saveToXML(){
		
		try {
			SaveToXMLFile f = new SaveToXMLFile(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
