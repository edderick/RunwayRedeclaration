package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Obstacle;
import View.EditObstacleDialog;
import View.MainFrame;


public class NewObstacleListener implements ActionListener{

	MainFrame mf;
	
	public NewObstacleListener(MainFrame mf){
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
public void actionPerformed(ActionEvent e) {
Obstacle old = obstacle;
obstacle = new Obstacle("", "", 0, 0, 0);
@SuppressWarnings("unused")
EditObstacleDialog ead = new EditObstacleDialog(obstacle, old);
}
}
*/