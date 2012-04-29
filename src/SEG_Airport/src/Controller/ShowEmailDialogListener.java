package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.AddressBook;
import View.SendEmailDialog;

public class ShowEmailDialogListener implements ActionListener{

	AddressBook addressBook;
	
	public ShowEmailDialogListener(AddressBook addressBook){
		this.addressBook = addressBook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new SendEmailDialog(addressBook);
	}

}
