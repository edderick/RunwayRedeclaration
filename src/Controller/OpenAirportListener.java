package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.Airport;
import Model.AirportObserver;
import Model.LoadXMLFile;
import Model.PhysicalRunway;
import View.MainFrame;

public class OpenAirportListener implements ActionListener, AirportObserver{

	Airport airport;
	List<AirportObserver> airportObservers;

	public OpenAirportListener(Airport airport, List<AirportObserver> aiportObserver){
		this.airportObservers = aiportObserver;
		this.airport = airport;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LoadXMLFile lf = new LoadXMLFile();
		Airport ap;

		try {
			if(airport.getSaved() == false){

				Object[] options = { "Yes", "No" };	
				JOptionPane pane = new JOptionPane("You have an unsaved airport, are you sure you wish to continue?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
				JDialog dialog = pane.createDialog(new JFrame(), "Unsaved Work");
				dialog.show();

				if(pane.getValue().equals("No")) return;

			}
			
			try {
				ap = lf.loadAirport();				
			} catch (Exception e2) {
				ap = null;
			}

			if(ap != null){
				airport = ap;
				System.out.println("This is the airport opened:: " + ap.getName());
				//iterate over the runways in the loaded airport and print all values
				for (PhysicalRunway r : ap.getPhysicalRunways()) { // this will show all the physical runways
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
				
			}
			
			if(airport.getPhysicalRunways().size() != 0) { 
				airport.setCurrentPhysicalRunway(airport.getPhysicalRunways().get(0));
				airport.setCurrentRunway(airport.getCurrentPhysicalRunway().getRunway(0));
			}
			
			
			if(ap != null){
				try {
					MainFrame.saveRecentFile(ap, LocalPathTrimmer.trimLocalPath(lf.getFile()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				notifyAirportObservers();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}


	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;

	}
	
	void notifyAirportObservers() {
		for(AirportObserver ao: airportObservers){
			ao.updateAirport(this.airport);
		}
	}

}

