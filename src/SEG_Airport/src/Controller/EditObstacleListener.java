package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Airport;
import Model.AirportObserver;
import Model.Obstacle;
import Model.PhysicalRunway;
import View.EditAirportDialog;
import View.EditObstacleDialog;
import View.MainFrame;

public class EditObstacleListener implements ActionListener, AirportObserver{

	Airport airport;
	List<AirportObserver> airportObservers;

	public EditObstacleListener(Airport airport, List<AirportObserver> airportObservers){
		this.airport = airport;
		this.airportObservers = airportObservers;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(airport.getPhysicalRunways().size() == 0){
			JOptionPane.showMessageDialog(null, "Airport does not contain any physical runways\r\nPlease add one by going to Edit > Airport", "", JOptionPane.ERROR_MESSAGE);
		} else if (airport.getCurrentPhysicalRunway() == null){
			JOptionPane.showMessageDialog(null, "Please select a physical runway", "", JOptionPane.ERROR_MESSAGE);
		} else if (airport.getCurrentPhysicalRunway().getObstacle() == null){
			Obstacle old = new Obstacle("", "", 0, 0, 0);
			Obstacle obstacle = new Obstacle("", "", 0, 0, 0);
			EditObstacleDialog eod = new EditObstacleDialog(obstacle, old);
			airport.getCurrentPhysicalRunway().setObstacle(obstacle);			
		}
		else {

			Obstacle old = airport.getCurrentPhysicalRunway().getObstacle();
			Obstacle obstacle = airport.getCurrentPhysicalRunway().getObstacle();
			EditObstacleDialog eod = new EditObstacleDialog(obstacle, old);
			airport.getCurrentPhysicalRunway().setObstacle(obstacle);			
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
new ActionListener() {
public void actionPerformed(ActionEvent arg0) {
	Obstacle old = obstacle;
	@SuppressWarnings("unused")
	EditObstacleDialog ead = new EditObstacleDialog(obstacle, old);
}
}
 */
