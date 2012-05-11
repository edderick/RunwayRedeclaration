package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.Airport;
import Model.AirportObserver;
import Model.LoadXMLFile;
import Model.Obstacle;
import View.EditObstacleDialog;
import View.MainFrame;

public class OpenRecentObstacleListener implements ActionListener, AirportObserver{

	String filename;
	Airport airport;
	List<AirportObserver> airportObservers;
	
	public OpenRecentObstacleListener(String filename, Airport airport, List<AirportObserver> airportObserver){
		this.filename = systemIndependentPath(filename);
		
		this.airportObservers = airportObserver;
		this.airport = airport;
	}

	private String systemIndependentPath(String filename){
		String separator = File.separator;
		
		if(separator.equals("\\")){
			separator = "\\\\";
		} 
		
		return filename.replaceAll("/|\\\\", separator);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(airport.getPhysicalRunways().size() == 0){
			JOptionPane.showMessageDialog(null, "Airport does not contain any physical runways\r\nPlease add one by going to Edit > Airport", "", JOptionPane.ERROR_MESSAGE);
		} else if (airport.getCurrentPhysicalRunway() == null){
			JOptionPane.showMessageDialog(null, "Please select a physical runway", "", JOptionPane.ERROR_MESSAGE);
		} else {
			LoadXMLFile lf = new LoadXMLFile();
			try {
				
				if(airport.getCurrentPhysicalRunway().getObstacle() != null && airport.getCurrentPhysicalRunway().getObstacle().getSaved() == false){

					Object[] options = { "Yes", "No" };	
					JOptionPane pane = new JOptionPane("You have an unsaved obstacle, are you sure you wish to continue?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
					JDialog dialog = pane.createDialog(new JFrame(), "Unsaved Work");
					dialog.show();

					if(pane.getValue().equals("No")) return;

				}
				
				Obstacle o = lf.silentLoadObstacle(filename);
				airport.getCurrentPhysicalRunway().setObstacle(o);

				try {
					MainFrame.saveRecentFile(o, LocalPathTrimmer.trimLocalPath(lf.getFile()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				System.out.println("Obstacle Opened");
				notifyAirportObservers();

				Obstacle old = airport.getCurrentPhysicalRunway().getObstacle();
				Obstacle obstacle = airport.getCurrentPhysicalRunway().getObstacle();
				@SuppressWarnings("unused")
				EditObstacleDialog eod = new EditObstacleDialog(obstacle, old, airport, airportObservers);
				airport.getCurrentPhysicalRunway().setObstacle(obstacle);

				

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;
	}
	
	void notifyAirportObservers() {
		for(AirportObserver ao: airportObservers){
			ao.updateAirport(airport);
		}
	}
 
}
