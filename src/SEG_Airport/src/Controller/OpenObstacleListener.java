package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import Model.Airport;
import Model.AirportObserver;
import Model.LoadXMLFile;
import Model.Obstacle;
import View.MainFrame;

public class OpenObstacleListener implements ActionListener, AirportObserver{

	Airport airport;
	List<AirportObserver> airportObservers;
	
	public OpenObstacleListener(Airport airport, List<AirportObserver> airportObserver){
		this.airportObservers = airportObserver;
		this.airport = airport;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {

		LoadXMLFile lf = new LoadXMLFile();
		try {
			Obstacle o = lf.loadObstacle();
			airport.getCurrentPhysicalRunway().setObstacle(o);
			
			try {
				MainFrame.saveRecentFile(o, lf.getFilename());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("Obstacle Opened");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;
	}
 
}
