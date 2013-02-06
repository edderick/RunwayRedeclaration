package View;

import javax.swing.JTextArea;

import Model.Airport;
import Model.AirportObserver;


@SuppressWarnings("serial")
public class CalculationsView extends JTextArea implements AirportObserver, ViewPanel{

	Airport airport; 
	
	public CalculationsView(Airport airport){
		updateAirport(airport);
		setAlignmentY(TOP_ALIGNMENT);
	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;	
		if ((airport != null) && (airport.getCurrentPhysicalRunway() != null) && (airport.getCurrentRunway() != null)){
			if(airport.getCurrentPhysicalRunway().getObstacle() == null) {setText("No obstacle has been placed!"); return;}
			setText(airport.getCurrentPhysicalRunway().toCalculation(airport.getCurrentRunway().getName()));
		}
		else {
			setText("No runway currently selected!");
		}
	}
	
	public void setVisible(boolean visible){
		//DO NOTHING
	}
	
}
