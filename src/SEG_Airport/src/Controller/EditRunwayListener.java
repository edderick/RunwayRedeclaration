package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;

import Model.Airport;
import Model.AirportObserver;
import View.EditRunwayDialog;
import View.MainFrame;

public class EditRunwayListener implements ActionListener, AirportObserver{

	Airport airport;
	List<AirportObserver> airportObservers;
	
	public EditRunwayListener(Airport airport, List<AirportObserver> airportObservers ){
		this.airport = airport;
		this.airportObservers = airportObservers;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(airport.getPhysicalRunways().size() == 0){
			JOptionPane.showMessageDialog(null, "Airport does not contain any physical runways\r\nPlease add one by going to Edit > Airport", "", JOptionPane.ERROR_MESSAGE);
			return;
		}
		@SuppressWarnings("unused")
		@SuppressWarnings({ "unused", "rawtypes" })
		EditRunwayDialog erd = new EditRunwayDialog(airport, new JList(), false, airportObservers);
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
ActionEvent e) {
@SuppressWarnings("unused")
EditRunwayDialog erd = new EditRunwayDialog(airport, new JList(), false);
}
}
*/