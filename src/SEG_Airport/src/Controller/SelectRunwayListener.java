package Controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import Model.Airport;
import Model.AirportObserver;
import Model.Runway;

public class SelectRunwayListener implements ItemListener, AirportObserver{
	//I am not sure how to select a runway. 
	//Right now I have put to do it using a runway object, 
	//but it could be done in a different way if that is more convenient

	Airport airport;
	List<AirportObserver> airportObservers;

	public SelectRunwayListener(Airport airport, List<AirportObserver> airportObservers){
		this.airport = airport;
		this.airportObservers = airportObservers;
	}

	void notifyAirportObservers(){
		for(AirportObserver ao: airportObservers){
			ao.updateAirport(airport);
		}		
	}	

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getItem() instanceof Runway 
				&& airport.getCurrentPhysicalRunway() != null 
				&& (airport.getCurrentPhysicalRunway().getRunway(0) == e.getItem()
				  ||airport.getCurrentPhysicalRunway().getRunway(1) == e.getItem()) ){
			Runway runway = (Runway) e.getItem();
			airport.setCurrentRunway(runway);
			notifyAirportObservers();
		}
	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport; 		
	}

}
