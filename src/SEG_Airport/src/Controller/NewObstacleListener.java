package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Airport;
import Model.AirportObserver;
import Model.Obstacle;
import Model.PhysicalRunway;
import View.EditObstacleDialog;
import View.MainFrame;


public class NewObstacleListener implements ActionListener, AirportObserver{

	Airport airport;
	List<AirportObserver> airportObservers;
	
	public NewObstacleListener(Airport airport, List<AirportObserver> airportObservers){
		this.airport = airport;
		this.airportObservers = airportObservers;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(airport.getPhysicalRunways().size() == 0){
			JOptionPane.showMessageDialog(null, "Airport does not contain any physical runways\r\nPlease add one by going to Edit > Airport", "", JOptionPane.ERROR_MESSAGE);
		} else {
			if(airport.getCurrentPhysicalRunway() == null){
				// we shall pick a runway
				String physicalRunwayNames = "";
				for(PhysicalRunway p : airport.getPhysicalRunways())
					physicalRunwayNames += p.getId() + " ";
				
				airport.setCurrentPhysicalRunway(airport.getPhysicalRunways().get(
						airport.getPhysicalRunways().indexOf(new PhysicalRunway( 
								(String) JOptionPane.showInputDialog(null,"Choose a physical for this obstacle", 
										"", JOptionPane.INFORMATION_MESSAGE, null,	
										physicalRunwayNames.split(" "), physicalRunwayNames.split(" ")[0]), null, null))));
			}
			
			Obstacle old = airport.getCurrentPhysicalRunway().getObstacle();
			Obstacle obstacle = new Obstacle(null, null, 0, 0, 0);
			@SuppressWarnings("unused")
			EditObstacleDialog eod = new EditObstacleDialog(obstacle, old);
			airport.getCurrentPhysicalRunway().setObstacle(obstacle);			
			notifyAirportObservers();
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

/*
public void actionPerformed(ActionEvent e) {
Obstacle old = obstacle;
obstacle = new Obstacle("", "", 0, 0, 0);
@SuppressWarnings("unused")
EditObstacleDialog ead = new EditObstacleDialog(obstacle, old);
}
}
*/