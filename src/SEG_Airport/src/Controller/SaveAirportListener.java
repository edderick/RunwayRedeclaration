package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.SaveToXMLFile;
import View.MainFrame;

public class SaveAirportListener implements ActionListener{

	MainFrame mf;
	
	public SaveAirportListener(MainFrame mf) {
		this.mf = mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			new SaveToXMLFile(mf.getAirport());
			System.out.println("Saved Airport: " + mf.getAirport().getName());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
