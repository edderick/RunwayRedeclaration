package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.SendEmailDialog;

public class ShowEmailDialogListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new SendEmailDialog();
	}

}
