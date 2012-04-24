package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Airport;
import View.EditAirportDialog;
import View.MainFrame;

public class NewAirportListener implements ActionListener{

	MainFrame mf;
	
	public NewAirportListener(MainFrame mf){
		this.mf = mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Airport old = mf.getAirport();
		Airport airport = new Airport("");
		System.out.println("Creating new runway");
		@SuppressWarnings("unused")
		EditAirportDialog ead = new EditAirportDialog(airport, old);
		mf.setAirport(airport);
	}

}

/*
new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		Airport old = airport;
		airport = new Airport("");
		@SuppressWarnings("unused")
		EditAirportDialog ead = new EditAirportDialog(airport, old);
	}
}
*/