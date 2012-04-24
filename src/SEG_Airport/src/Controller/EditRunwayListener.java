package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import View.EditRunwayDialog;
import View.MainFrame;

public class EditRunwayListener implements ActionListener{

	MainFrame mf;
	
	public EditRunwayListener(MainFrame mf){
		this.mf = mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		EditRunwayDialog erd = new EditRunwayDialog(mf.getAirport(), new JList(), false);
	}

}

/*
ActionEvent e) {
@SuppressWarnings("unused")
EditRunwayDialog erd = new EditRunwayDialog(airport, new JList(), false);
}
}
*/