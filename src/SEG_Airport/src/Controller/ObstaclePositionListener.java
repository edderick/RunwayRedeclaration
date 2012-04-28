package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Airport;
import Model.AirportObserver;
import Model.Obstacle;
import View.MainFrame;

public class ObstaclePositionListener implements ActionListener, AirportObserver{
	
	Airport airport;
	
	public ObstaclePositionListener(Airport airport){
		this.airport = airport;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;
		
	}

	
	
	
}
