package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Model.Airport;
import Model.AirportObserver;
import View.EditAirportDialog;
import View.MainFrame;

public class NewAirportListener implements ActionListener, AirportObserver{

	Airport airport;
	List<AirportObserver> airportObservers;
	
	public NewAirportListener(Airport airport, List<AirportObserver> airportObservers){
		this.airport = airport;
		this.airportObservers = airportObservers;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Airport old = airport;
		System.out.println("Creating new runway");
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

/*
new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		Airport old = airport;
		airport = new Airport("");
		@SuppressWarnings("unused")
		EditAirportDialog ead = new EditAirportDialog(airport, old);
	}
}
*/