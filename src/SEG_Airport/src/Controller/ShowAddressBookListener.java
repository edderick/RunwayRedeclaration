package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.AddressBookDialog;

public class ShowAddressBookListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		AddressBookDialog abd = new AddressBookDialog();
		
	}

}

/*
new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddressBookDialog abd = new AddressBookDialog();
				abd.setVisible(true);
			}
		}
*/