package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenRecentAirportListener implements ActionListener{
	
	String filename;
	
	public OpenRecentAirportListener(String filename){
		this.filename = filename;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Opening: " + filename);		
	}
 
}
