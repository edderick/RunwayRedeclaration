package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: Are you sure you wish to exit
		System.exit(0);
	}

}
