package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Airport;
import Model.AirportObserver;
import Model.Obstacle;
import View.EditObstacleDialog;
import View.MainFrame;


public class NewObstacleListener implements ActionListener, AirportObserver{

	Airport airport;
	
	public NewObstacleListener(Airport airport){
		this.airport = airport;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Obstacle old = airport.getCurrentPhysicalRunway().getObstacle();
		Obstacle obstacle = new Obstacle(null, null, 0, 0, 0);
		System.out.println("Editing Obstacle: " + old.getName());
		@SuppressWarnings("unused")
		EditObstacleDialog eod = new EditObstacleDialog(obstacle, old);
		airport.getCurrentPhysicalRunway().setObstacle(obstacle);
		
	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;		
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