package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Model.Airport;
import Model.AirportObserver;
import View.EditAirportDialog;

public class EditAirportListener implements ActionListener, AirportObserver{

	Airport airport;
	List<AirportObserver> airportObservers;
	
	public EditAirportListener(Airport airport, List<AirportObserver> airportObservers){
		this.airport = airport;
		this.airportObservers = airportObservers;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Airport old = airport;
		System.out.println("Editing Airport: " + old.getName());
		@SuppressWarnings("unused")
		EditAirportDialog ead = new EditAirportDialog(airport, old, airportObservers);
		notifyAirportObservers();
	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;
	}
	
	void notifyAirportObservers(){
		for(AirportObserver ao: airportObservers){
			ao.updateAirport(airport);
		}
	}
}