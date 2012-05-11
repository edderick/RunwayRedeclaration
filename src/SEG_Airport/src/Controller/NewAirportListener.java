package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.Airport;
import Model.AirportObserver;
import View.EditAirportDialog;

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
		System.out.println("Creating new Airport");
		
		if(airport.getSaved() == false){

			Object[] options = { "Yes", "No" };	
			JOptionPane pane = new JOptionPane("You have an unsaved airport, are you sure you wish to continue?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
			JDialog dialog = pane.createDialog(new JFrame(), "Unsaved Work");
			dialog.show();

			if(pane.getValue().equals("No")) return;

		}
		
		
		@SuppressWarnings("unused")
		EditAirportDialog ead = new EditAirportDialog(new Airport(""), airport, airportObservers);
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