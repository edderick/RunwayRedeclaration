package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		new SendEmailDialog(addressBook, airport);
	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;
	}

}
