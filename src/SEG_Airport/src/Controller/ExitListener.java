package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import Model.Airport;
import Model.AirportObserver;
import Model.Obstacle;
import Model.Saveable;
import View.ExitDialog;

public class ExitListener extends WindowAdapter implements ActionListener, AirportObserver{

	Airport airport;
	Obstacle obstacle;
	
	public ExitListener(Airport airport){
		this.airport = airport;
		if(airport.getCurrentPhysicalRunway() != null && airport.getCurrentPhysicalRunway().getObstacle() != null){
		this.obstacle = airport.getCurrentPhysicalRunway().getObstacle();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		handleClose();
	}

	@Override 
	public void windowClosing(WindowEvent e){
		handleClose();
	}
	
	private void handleClose(){
		if(airport.getSaved() && (obstacle == null || obstacle.getSaved())){
			System.exit(0);
		}
		else{
			
			List<Saveable> toSave = new ArrayList<Saveable>();
			
			if(!airport.getSaved()){
				toSave.add(airport);
				System.out.println("Gotta save Airport!");
			}
			
			if(obstacle != null && !obstacle.getSaved()){
				toSave.add(obstacle);
				System.out.println("Gotta save Obstacle!");
			}
			
			new ExitDialog(toSave);
			
		}
	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;
		if(airport.getCurrentPhysicalRunway() != null ){
			obstacle = airport.getCurrentPhysicalRunway().getObstacle();
		} else {
			obstacle = null;
		}
	}
	
}
