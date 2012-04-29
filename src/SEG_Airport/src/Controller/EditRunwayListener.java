package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import Model.Airport;
import Model.AirportObserver;
import View.EditRunwayDialog;
import View.MainFrame;

public class EditRunwayListener implements ActionListener, AirportObserver{

	Airport airport;
	
	public EditRunwayListener(Airport airport){
		this.airport = airport;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings({ "unused", "rawtypes" })
		EditRunwayDialog erd = new EditRunwayDialog(airport, new JList(), false);
	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;
	}

}

/*
ActionEvent e) {
@SuppressWarnings("unused")
EditRunwayDialog erd = new EditRunwayDialog(airport, new JList(), false);
}
}
*/