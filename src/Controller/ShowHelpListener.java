package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.HelpDialog;

public class ShowHelpListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new HelpDialog();
	}

}
