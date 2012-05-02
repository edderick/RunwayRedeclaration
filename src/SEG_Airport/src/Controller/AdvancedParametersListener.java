package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Airport;
import Model.AirportObserver;
import View.AdvancedParametersDialog;

public class AdvancedParametersListener implements ActionListener, AirportObserver{
	
	Airport airport;
	List<AirportObserver> airportObservers;
	
	public AdvancedParametersListener(Airport airport, List<AirportObserver> airportObservers){
		this.airport = airport;
		this.airportObservers = airportObservers;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(airport.getCurrentPhysicalRunway() != null){
			new AdvancedParametersDialog(airport, airportObservers);
		}
		else JOptionPane.showMessageDialog(null, "Airport does not contain any physical runways\r\nPlease add one by going to Edit > Airport", "", JOptionPane.ERROR_MESSAGE);
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
