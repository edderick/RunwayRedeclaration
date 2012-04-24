package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Airport;
import View.EditAirportDialog;
import View.MainFrame;

public class EditAirportListener implements ActionListener{

	MainFrame mf;
	
	public EditAirportListener(MainFrame mf){
		this.mf = mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Airport old = mf.getAirport();
		Airport airport = new Airport(old.getName());
		System.out.println("Editing Airport: " + old.getName());
		@SuppressWarnings("unused")
		EditAirportDialog ead = new EditAirportDialog(airport, old);
		mf.setAirport(airport);
	}

}
/*(
new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("airport you are editing: " + airport.getName());
		Airport old = airport;
		@SuppressWarnings("unused")
		EditAirportDialog ead = new EditAirportDialog(airport, old);
	}
}
*/