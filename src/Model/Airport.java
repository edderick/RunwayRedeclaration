package model;

import java.util.ArrayList;
import java.util.Iterator;


//Airport
public class Airport {
	
	//List of physical runways
	private ArrayList<PhysicalRunway> runways;
	private String name;
	
	//Constructor
	public Airport(String n){
		runways = new ArrayList<PhysicalRunway>();
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	//Method to add runway to the list
	public void addPhysicalRunway(PhysicalRunway r){
		runways.add(r);
	}
	
	//Method to remove runway from the list
	public void removePhysicalRunway(String id){
		
		for (Iterator i = runways.iterator(); i.hasNext(); ) {
		    Object element = i.next();

		    if (((PhysicalRunway) element).getId() == id) {
		        i.remove();
		    }
		}
	}
	
	//Method that returns the whole list of runways
	public ArrayList<PhysicalRunway> runways(){
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
