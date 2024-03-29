package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.Airport;
import Model.AirportObserver;
import Model.Obstacle;
import View.EditObstacleDialog;


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
		} else if (airport.getCurrentPhysicalRunway() == null){
			JOptionPane.showMessageDialog(null, "Please select a physical runway", "", JOptionPane.ERROR_MESSAGE);
		} else{

			if(airport.getCurrentPhysicalRunway().getObstacle() != null && airport.getCurrentPhysicalRunway().getObstacle().getSaved() == false){

				Object[] options = { "Yes", "No" };	
				JOptionPane pane = new JOptionPane("You have an unsaved obstacle, are you sure you wish to continue?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
				JDialog dialog = pane.createDialog(new JFrame(), "Unsaved Work");
				dialog.show();

				if(pane.getValue().equals("No")) return;

			}

			@SuppressWarnings("unused")
			EditObstacleDialog eod = new EditObstacleDialog(new Obstacle("", 0), new Obstacle("", 0), airport, airportObservers);
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