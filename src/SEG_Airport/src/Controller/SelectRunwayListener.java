package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Airport;
import Model.PhysicalRunway;
import Model.Runway;

public class SelectRunwayListener implements ActionListener{

	//I am not sure how to select a runway. 
	//Right now I have put to do it using a runway object, 
	//but it could be done in a different way if that is more convenient
	
	JComboBox jc;
	JTable original, redeclared;
	Airport airport;

	public SelectRunwayListener(Airport airport, JComboBox jc, JTable original, JTable redeclared) {
		super();
		this.jc = jc;
		this.original = original;
		this.redeclared = redeclared;
		this.airport = airport;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("airport name: " + airport.getName());
		ArrayList<Runway> runways = new ArrayList<Runway>();
		for(PhysicalRunway p : airport.getRunways()){
			runways.add(p.getRunway(0));
			runways.add(p.getRunway(1));
		}
		System.out.println("size: " + runways.size() + " " + airport.getRunways().size());
//		original.setModel(new DefaultTableModel(
//				new Object[][] {
//						{"TORA(R)", runways.get(jc.getSelectedIndex()).getTORA(Runway.DEFAULT)},
//						{"TODA(R)", runways.get(jc.getSelectedIndex()).getTODA(Runway.DEFAULT)},
//						{"ASDA(R)", runways.get(jc.getSelectedIndex()).getASDA(Runway.DEFAULT)},
//						{"LDA(R)", runways.get(jc.getSelectedIndex()).getLDA(Runway.DEFAULT)},
//						{"DT", runways.get(jc.getSelectedIndex()).getDisplacedThreshold(Runway.DEFAULT)}
//				},
//				new String[] {
//						"New column", "New column"
//				}
//				));
//		redeclared.setModel(new DefaultTableModel(
//				new Object[][] {
//						{"TORA(R)", runways.get(jc.getSelectedIndex()).getTORA(Runway.REDECLARED)},
//						{"TODA(R)", runways.get(jc.getSelectedIndex()).getTODA(Runway.REDECLARED)},
//						{"ASDA(R)", runways.get(jc.getSelectedIndex()).getASDA(Runway.REDECLARED)},
//						{"LDA(R)", runways.get(jc.getSelectedIndex()).getLDA(Runway.REDECLARED)},
//						{"DT", runways.get(jc.getSelectedIndex()).getDisplacedThreshold(Runway.REDECLARED)}
//				},
//				new String[] {
//						"New column", "New column"
//				}				
//				));
	}

}
