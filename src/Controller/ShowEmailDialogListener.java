package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.AddressBook;
import Model.Airport;
import Model.AirportObserver;
import View.SendEmailDialog;

public class ShowEmailDialogListener implements ActionListener, AirportObserver{

	AddressBook addressBook;
	Airport airport;

	public ShowEmailDialogListener(AddressBook addressBook, Airport airport){
		this.addressBook = addressBook;
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
			new SendEmailDialog(addressBook, airport);
		}
	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;
	}

}
