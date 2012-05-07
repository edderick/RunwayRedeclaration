package Model;

import java.util.ArrayList;

/**
 * Represents an airport, contains runways
 * @author Oscar
 */
public class Airport implements Saveable{

	//List of physical runways
	private ArrayList<PhysicalRunway> runways;
	private String name;
	
	//True when the current runway has been saved
	private boolean saved = true;
	
	Runway currentRunway;
	PhysicalRunway currentPhysicalRunway;

	/**
	 * The default constructor for Airport class
	 * @param name The name of the airport
	 */
	public Airport(String name){
		runways = new ArrayList<PhysicalRunway>();
		this.name = name;
		
		if (!runways.isEmpty()) { 
			currentPhysicalRunway = runways.get(0);
		}
		
		if (currentPhysicalRunway != null){
			currentRunway = currentPhysicalRunway.getRunway(0);
		}
		
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
	 * Sets the current runway, the runway that is drawn on screen etc
	 * @param runway New current runway
	 */
	public void setCurrentRunway(Runway runway){
		currentRunway = runway;
	}
	
	/**
	 * @return The current runway calculations should be about
	 */
	public Runway getCurrentRunway(){
		return currentRunway;
	}
	
	
	/**
	 * Sets the current physical runway, the physical runway that is drawn on screen etc
	 * @param runway New current physical runway
	 */
	public void setCurrentPhysicalRunway(PhysicalRunway runway){
		currentPhysicalRunway = runway;
	}
	
	/**
	 * @return The current physical runway calculations should be about
	 */
	public PhysicalRunway getCurrentPhysicalRunway(){
		return currentPhysicalRunway;
	}
	
	/**
	 * @return List of runways
	 */
	public ArrayList<PhysicalRunway> getPhysicalRunways(){
		return runways;
	}

	/**
	 * Saves the airport to an xml file on disk
	 */
	public void saveToXML(){
		try {
			@SuppressWarnings("unused")
			SaveToXMLFile xmlFile = new SaveToXMLFile(this);
			saved = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Called whenever airport is modified
	 */
	public void setModified(){
		this.saved = false;
	}
	
	/**
	 * @return Whether or not the airport has been saved
	 */
	public boolean getSaved(){
		return this.saved;
	}

	@Override
	public String getType() {
		return "Airport";
	}

}
