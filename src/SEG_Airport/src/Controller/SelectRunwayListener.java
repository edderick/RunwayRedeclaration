package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Runway;

public class SelectRunwayListener implements ActionListener{

	//I am not sure how to select a runway. 
	//Right now I have put to do it using a runway object, 
	//but it could be done in a different way if that is more convenient
	
	Runway runway;
	
	SelectRunwayListener(Runway runway){
		this.runway = runway;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
