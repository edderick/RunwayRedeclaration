package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.Airport;
import Model.AirportObserver;
import Model.Print;

public class PrintCalculationsListener implements ActionListener, AirportObserver{

	Airport airport;
	Print print = new Print();
	
	public PrintCalculationsListener(Airport airport){
		this.airport = airport;
	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(airport.getCurrentPhysicalRunway() == null){
			JOptionPane.showMessageDialog(null, "No physical Runway selected", "", JOptionPane.ERROR_MESSAGE);
		}
		else if (airport.getCurrentPhysicalRunway().getObstacle() == null){
			JOptionPane.showMessageDialog(null, "Runway does not contain an Obstacle", "", JOptionPane.ERROR_MESSAGE);
		}
		else if (airport.getCurrentRunway() == null){
			JOptionPane.showMessageDialog(null, "No Runway Selected", "", JOptionPane.ERROR_MESSAGE);
		}
		else {
			print.print(airport.getCurrentPhysicalRunway().toCalculation(airport.getCurrentRunway().getName()));
		}
	}
}
