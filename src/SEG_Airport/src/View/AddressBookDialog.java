package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import net.miginfocom.swing.MigLayout;

import Model.AddressBook;
import Model.Contact;

/**
 * Creates a dialog to alter the address book for the email system
 * @author Edward Seabrook
 */
public class AddressBookDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	//private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private JPanel menuPane;
	private JButton btnNew;
	private JButton btnEdit;
	private JScrollPane scrollPane;
	private JTable tblContacts;
	private JButton btnDelete;
	private JButton btnClose;
	private JSeparator separator;

	public AddressBookDialog(AddressBook addressBook) {
		setTitle("Address Book");
		setBounds(100, 100, 481, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[grow][79px]", "[grow]"));
		setContentPane(contentPane);

		tblContacts = new JTable();
		tblContacts.setModel(new MyTableModel(addressBook));
		tblContacts.getColumnModel().getColumn(0).setResizable(false);
		tblContacts.getColumnModel().getColumn(0).setPreferredWidth(50);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0,grow");
		scrollPane.setViewportView(tblContacts);

		menuPane = new JPanel();
		menuPane.setLayout(new MigLayout("", "[65px]", "[23px][23px][1px][23px][1px][grow][]"));
		contentPane.add(menuPane, "cell 1 0,grow");

		btnNew = new JButton("New");
		menuPane.add(btnNew, "cell 0 0,growx,aligny top");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contact contact = new Contact("","","");
				@SuppressWarnings("unused")
				EditContactDialog eed = new EditContactDialog(contact, tblContacts);
				((MyTableModel)tblContacts.getModel()).getAddressBook().addContact(contact);
				tblContacts.updateUI();
			}
		});

		btnEdit = new JButton("Edit");
		menuPane.add(btnEdit, "cell 0 1,growx,aligny top");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblContacts.getSelectedRow() != -1){
					@SuppressWarnings("unused")
					EditContactDialog eed = new EditContactDialog(((MyTableModel)tblContacts.getModel()).getAddressBook().getContacts().get(tblContacts.getSelectedRow()), tblContacts);
					tblContacts.updateUI();
				}
			}
		});

		separator = new JSeparator();
		menuPane.add(separator, "cell 0 2,grow");

		btnDelete = new JButton("Delete");
		menuPane.add(btnDelete, "cell 0 3,growx,aligny top");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblContacts.getSelectedRow() != -1){
					String email = ((MyTableModel)tblContacts.getModel()).getAddressBook().getContacts().get(tblContacts.getSelectedRow()).getEmail();
					((MyTableModel)tblContacts.getModel()).getAddressBook().removeContactByEmail(email);
					tblContacts.updateUI();
				}
			}
		});

		btnClose = new JButton("Close");
		menuPane.add(btnClose, "cell 0 6,grow");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setVisible(true);
	}
}

/**
 * Allows the table to access the AddressBook
 * @author Edward Seabrook
 */
class MyTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	AddressBook addressBook;
	private String[] columnNames = {"First name", "Last name" ,"Email"};

	public MyTableModel(AddressBook addressBook){
		this.addressBook = addressBook;
	}

	public void addContact(Contact contact){
		addressBook.addContact(contact);
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return addressBook.getContacts().size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		switch(col){
		case 0 : return addressBook.getContacts().get(row).getFirstName();
		case 1 : return addressBook.getContacts().get(row).getLastName();
		case 2 : return addressBook.getContacts().get(row).getEmail();
		default : return null;
		}
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public void setValueAt(Object value, int row, int col) {
	}

	public AddressBook getAddressBook(){
		return addressBook;
	}
}
