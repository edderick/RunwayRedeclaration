package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Model.Airport;
import Model.AirportObserver;

public class RemoveObstacleListener implements ActionListener, AirportObserver{

	Airport airport;
	List<AirportObserver> airportObservers;

	public RemoveObstacleListener(Airport airport, List<AirportObserver> airportObservers){
		this.airport = airport;
		this.airportObservers = airportObservers;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(airport.getCurrentPhysicalRunway() != null && airport.getCurrentPhysicalRunway().getObstacle() != null){
			airport.getCurrentPhysicalRunway().removeObstacleAndReset();
		}
		notifyAirportObservers();
	}

	void notifyAirportObservers(){
		for(AirportObserver ao: airportObservers){
			ao.updateAirport(airport);
		}
	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;
	}	
}
