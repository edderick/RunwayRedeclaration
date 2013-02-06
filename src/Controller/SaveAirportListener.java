package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import Model.Airport;
import Model.AirportObserver;
import View.MainFrame;

public class SaveAirportListener implements ActionListener, AirportObserver{

	Airport airport;
	List<AirportObserver> airportObservers;
	
	public SaveAirportListener(Airport airport, List<AirportObserver> airportObservers) {
		this.airport = airport;
		this.airportObservers = airportObservers;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			File f = airport.saveToXML();
			System.out.println("Saved Airport: " + airport.getName());
						
			MainFrame.saveRecentFile(airport, LocalPathTrimmer.trimLocalPath(f));
			notifyAirportObservers();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		
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
