package Model;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Represents an airport, contains runways
 * @author Oscar
 *
 */
public class Airport {

	//List of physical runways
	private ArrayList<PhysicalRunway> runways;
	private String name;

	/**
	 * The default constructor for Airport class
	 * @param name The name of the airport
	 */
	public Airport(String name){
		runways = new ArrayList<PhysicalRunway>();
		this.name = name;
	}

	/**
	 * @return The name of the airport 
	 */
	public String getName(){
		return name;
	}

	/**
	 * @param name The new name for the airport
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Adds a runway to the list
	 * @param runway The Physical Runway to add to the list
	 */
	public void addPhysicalRunway(PhysicalRunway runway){
		runways.add(runway);
	}

	/**
	 * Removes a runway from  the list 
	 * @param id The id of the runway to remove
	 */
	public void removePhysicalRunway(String id){
		for(PhysicalRunway r : runways){
			if(r.getId().equals(id)){
				runways.remove(r);
			}
		}
	}

	/**
	 * @return List of runways
	 */
	public ArrayList<PhysicalRunway> runways(){
		return runways;
	}

	/**
	 * Saves the airport to an xml file on disk
	 */
	public void saveToXML(){
		try {
			SaveToXMLFile f = new SaveToXMLFile(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
