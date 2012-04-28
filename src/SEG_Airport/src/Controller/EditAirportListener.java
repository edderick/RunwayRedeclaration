package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Model.Airport;
import Model.AirportObserver;
import View.EditAirportDialog;
import View.MainFrame;

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
		EditAirportDialog ead = new EditAirportDialog(airport, old);
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


/*(
new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("airport you are editing: " + airport.getName());
		Airport old = airport;
		@SuppressWarnings("unused")
		EditAirportDialog ead = new EditAirportDialog(airport, old);
	}
}
*/