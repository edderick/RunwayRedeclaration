package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Airport;
import Model.AirportObserver;
import Model.SaveToXMLFile;
import View.MainFrame;

public class SaveAirportListener implements ActionListener, AirportObserver{

	Airport airport;
	
	public SaveAirportListener(Airport airport) {
		this.airport = airport;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			airport.saveToXML();
			System.out.println("Saved Airport: " + airport.getName());
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
