package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.Airport;
import Model.AirportObserver;
import Model.Obstacle;
import Model.PhysicalRunway;
import View.EditObstacleDialog;
import View.MainFrame;


public class NewObstacleListener implements ActionListener, AirportObserver{

	Airport airport;
	
	public NewObstacleListener(Airport airport){
		this.airport = airport;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(airport.getRunways().size() == 0){
			JOptionPane.showMessageDialog(null, "Airport does not contain any physical runways", "", JOptionPane.ERROR_MESSAGE);
		} else {
			if(airport.getCurrentPhysicalRunway() == null){
				// we shall pick a runway
				String physicalRunwayNames = "";
				for(PhysicalRunway p : airport.getRunways())
					physicalRunwayNames += p.getId() + " ";
				
				airport.setCurrentPhysicalRunway(airport.getRunways().get(airport.getRunways().indexOf(new PhysicalRunway(	(String) JOptionPane.showInputDialog(null,"Choose one", "Input", JOptionPane.INFORMATION_MESSAGE, null,	physicalRunwayNames.split(" "), physicalRunwayNames.split(" ")[0]), null, null))));
			}
			
			Obstacle old = airport.getCurrentPhysicalRunway().getObstacle();
			Obstacle obstacle = new Obstacle(null, null, 0, 0, 0);
			@SuppressWarnings("unused")
			EditObstacleDialog eod = new EditObstacleDialog(obstacle, old);
			airport.getCurrentPhysicalRunway().setObstacle(obstacle);
			
		}
			
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