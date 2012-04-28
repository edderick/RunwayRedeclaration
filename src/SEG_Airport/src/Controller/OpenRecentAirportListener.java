package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import Model.Airport;
import Model.AirportObserver;
import Model.LoadXMLFile;
import Model.PhysicalRunway;
import View.MainFrame;

public class OpenRecentAirportListener implements ActionListener, AirportObserver{
	
	String filename;
	Airport airport;
	List<AirportObserver> airportObservers;
	
	public OpenRecentAirportListener(String filename, Airport airport, List<AirportObserver> airportObservers){
		this.filename = filename;
		this.airport = airport;
		this.airportObservers = airportObservers;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Opening: " + filename);		
		LoadXMLFile lf = new LoadXMLFile();
		Airport ap = new Airport("");

		try {

			ap = lf.silentLoadAirport(filename);


			System.out.println("This is the airport opened: " + ap.getName());
			//iterate over the runways in the loaded airport and print all values
			for (PhysicalRunway r : ap.runways()) { // this will show all the physical runways
				System.out.println(((PhysicalRunway) r).getId() 
						+" "+ r.getRunway(0).getName() 
						+" "+ r.getRunway(0).getTORA(1)
						+" "+ r.getRunway(0).getASDA(1)
						+" "+ r.getRunway(0).getTODA(1)
						+" "+ r.getRunway(0).getLDA(1)

						+" "+ r.getRunway(1).getName()
						+" "+ r.getRunway(1).getTORA(1)
						+" "+ r.getRunway(1).getASDA(1)
						+" "+ r.getRunway(1).getTODA(1)
						+" "+ r.getRunway(1).getLDA(1)

						);
			}

		} catch (Exception ex) {}
		if (ap != null) {
			this.airport = ap;
			notifyAirportObservers();
			try {
				MainFrame.saveRecentFile(ap, lf.getFilename());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;
	}
	
	void notifyAirportObservers(){
		for(AirportObserver ao: airportObservers){
			ao.updateAirport(airport);
		}
	}

 
}
