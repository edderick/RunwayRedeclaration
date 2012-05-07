package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

import Model.Contact;

/**
 * A dialog to edit a contacts details
 * @author Edward Seabrook & Brian Yu
 */
public class EditContactDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfEmail;
	
	private Contact contactCopy;
	private JTable tblContactsCopy;
	
	public EditContactDialog(Contact contact, JTable tblContacts) {
		setTitle("Edit Contact");
		setBounds(100, 100, 360, 170);

		this.contactCopy = contact;
		this.tblContactsCopy = tblContacts;
		
		contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[57.00][307.00,grow]", "[][][][]"));

		JLabel lblFirstName = new JLabel("First Name");
		contentPanel.add(lblFirstName, "cell 0 0,alignx trailing");

		tfFirstName = new JTextField();
		contentPanel.add(tfFirstName, "cell 1 0,growx");
		tfFirstName.setText(contact.getFirstName());

		JLabel lblLastName = new JLabel("Last Name");
		contentPanel.add(lblLastName, "cell 0 1,alignx trailing");

		tfLastName = new JTextField();
		contentPanel.add(tfLastName, "cell 1 1,growx");
		tfLastName.setText(contact.getLastName());
		
		JLabel lblEmail = new JLabel("Email");
		contentPanel.add(lblEmail, "cell 0 2,alignx trailing");

		tfEmail = new JTextField();
		contentPanel.add(tfEmail, "cell 1 2,growx");
		tfEmail.setText(contact.getEmail());

		JButton btnOk = new JButton("Ok");
		contentPanel.add(btnOk, "cell 1 3,alignx right");
		btnOk.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				contactCopy.setFirstName(tfFirstName.getText());
				contactCopy.setLastName(tfLastName.getText());
				contactCopy.setEmail(tfEmail.getText());
				tblContactsCopy.updateUI();
				((AddressBookTableModel)tblContactsCopy.getModel()).getAddressBook().saveToXML();
				dispose();
			}
		});

		getRootPane().setDefaultButton(null);
		setAlwaysOnTop(true);
		setVisible(true);
	}
}

