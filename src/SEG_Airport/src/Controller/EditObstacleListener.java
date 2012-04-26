package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Airport;
import Model.Obstacle;
import View.EditAirportDialog;
import View.EditObstacleDialog;
import View.MainFrame;

public class EditObstacleListener implements ActionListener{

	MainFrame mf;
	
	public EditObstacleListener(MainFrame mf){
		this.mf = mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Obstacle old = mf.getAirport().getCurrentPhysicalRunway().getObstacle();
		Obstacle obstacle = new Obstacle(null, null, 0, 0, 0);
		System.out.println("Editing Obstacle: " + old.getName());
		@SuppressWarnings("unused")
		EditObstacleDialog eod = new EditObstacleDialog(obstacle, old);
		mf.getAirport().getCurrentPhysicalRunway().setObstacle(obstacle);
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