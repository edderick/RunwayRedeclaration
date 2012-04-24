package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Airport;
import Model.LoadXMLFile;
import Model.PhysicalRunway;
import View.MainFrame;

public class OpenAirportListener implements ActionListener{

	MainFrame mf;
	
	public OpenAirportListener(MainFrame mf){
		this.mf = mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		LoadXMLFile lf = new LoadXMLFile();
		Airport ap = new Airport("");
		
		try {
			
			ap = lf.loadAirport();

		
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
		mf.setAirport(ap);
	}
	
		
	}

}



/*
public void actionPerformed(ActionEvent arg0) {
/*
 *  Open airport code goes here - need to reset airport if the user decides to cancel
		
LoadXMLFile lf = new LoadXMLFile();
Airport ap = airport;
try {
	airport = lf.loadFile();
	System.out.println("This is the airport opened: " + airport.getName());
	//iterate over the runways in the loaded airport and print all values
	for (PhysicalRunway r : airport.runways()) { // this will show all the physical runways
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
	lblCurrentAirport.setText("Current Airport: " + airport.getName());
} catch (Exception e) {}
if (airport == null) {
	airport = ap;
}
}
}
*/