package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public EditObstacleListener(Airport airport){
		this.airport = airport;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		if (airport.getCurrentPhysicalRunway() == null)
//		{ 
//			JOptionPane.showMessageDialog(null, "No current runway!");
//		}
//		else if(airport.getCurrentPhysicalRunway().getObstacle() == null){
//			//TODO: Maybe make this bring up a dialog to create an obstacle
//			JOptionPane.showMessageDialog(null, "No current obstacle!");
//		}
//		else{
//			Obstacle old = airport.getCurrentPhysicalRunway().getObstacle();
//			Obstacle obstacle = new Obstacle(null, null, 0, 0, 0);
//			System.out.println("Editing Obstacle: " + old.getName());
//			@SuppressWarnings("unused")
//			EditObstacleDialog eod = new EditObstacleDialog(obstacle, old);
//			airport.getCurrentPhysicalRunway().setObstacle(obstacle);
//		}
		if(airport.getRunways().size() == 0){
			JOptionPane.showMessageDialog(null, "Airport does not contain any physical runways\r\nPlease add one by going to Edit > Airport", "", JOptionPane.ERROR_MESSAGE);
		} else {
			//if(airport.getCurrentPhysicalRunway() == null){
				// we shall pick a runway
				String physicalRunwayNames = "";
				for(PhysicalRunway p : airport.getRunways())
					physicalRunwayNames += p.getId() + " ";
				
				airport.setCurrentPhysicalRunway(airport.getRunways().get(
						airport.getRunways().indexOf(new PhysicalRunway( 
								(String) JOptionPane.showInputDialog(null,"Choose a physical for this obstacle", 
										"", JOptionPane.INFORMATION_MESSAGE, null,	
										physicalRunwayNames.split(" "), physicalRunwayNames.split(" ")[0]), null, null))));
			//}
			
			Obstacle old = airport.getCurrentPhysicalRunway().getObstacle();
			Obstacle obstacle = airport.getCurrentPhysicalRunway().getObstacle();
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
new ActionListener() {
public void actionPerformed(ActionEvent arg0) {
	Obstacle old = obstacle;
	@SuppressWarnings("unused")
	EditObstacleDialog ead = new EditObstacleDialog(obstacle, old);
}
}
 */