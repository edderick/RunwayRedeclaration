package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import Model.Airport;
import Model.AirportObserver;
import View.MainFrame;

public class SaveObstacleListener implements ActionListener, AirportObserver{

	Airport airport;

	public SaveObstacleListener(Airport airport) {
		this.airport = airport;
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if((airport.getCurrentPhysicalRunway() != null) && (airport.getCurrentPhysicalRunway().getObstacle() != null)){
			try {
				File f = airport.getCurrentPhysicalRunway().getObstacle().saveToXML();
				System.out.println("Saved Obstacle: " + airport.getCurrentPhysicalRunway().getObstacle().getName());
				MainFrame.saveRecentFile(airport.getCurrentPhysicalRunway().getObstacle(), f.getAbsolutePath());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else{
			JOptionPane.showMessageDialog(null, "There is no obstacle to save.", "", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;
	}

}
