package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.ArrayList;

import Model.Airport;
import Model.AirportObserver;
import Model.PhysicalRunway;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Airport;
import Model.PhysicalRunway;
import Model.Runway;

public class SelectRunwayListener implements ItemListener{
	//I am not sure how to select a runway. 
	//Right now I have put to do it using a runway object, 
	//but it could be done in a different way if that is more convenient
	
	Airport airport;

	List<AirportObserver> airportObservers;
	
	public SelectRunwayListener(Airport airport, List<AirportObserver> airportObservers){
		this.airport = airport;
		this.airportObservers = airportObservers;
	}
	
	void notifyAirportObservers() {
		for(AirportObserver ao: airportObservers){
			ao.updateAirport(airport);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Runway runway = (Runway) e.getItem();
		airport.setCurrentRunway(runway);
		notifyAirportObservers();
	}
	
}
