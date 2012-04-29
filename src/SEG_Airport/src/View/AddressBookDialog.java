package View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import Model.AddressBook;
import Model.Contact;

import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class AddressBookDialog extends JDialog {

	@SuppressWarnings("unused")
	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private JPanel panel_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnDelete;
	private JButton btnCancel;
	private JSeparator separator;
	private JSeparator separator_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddressBookDialog dialog = new AddressBookDialog(new AddressBook());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddressBookDialog(AddressBook addressBook) {
		setTitle("Address Book");
		setBounds(100, 100, 481, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][79px]", "[grow]"));

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0,grow");

		table = new JTable();

		table.setModel(new MyTableModel(addressBook));

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		scrollPane.setViewportView(table);

		panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 1 0,grow");

		btnNewButton = new JButton("New");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contact contact = new Contact("","","");
				EditEmailDialog eed = new EditEmailDialog(contact);
				((MyTableModel)table.getModel()).getAddressBook().addContact(contact);
				table.updateUI();
			}
		});
		panel_1.setLayout(new MigLayout("", "[65px]", "[23px][23px][1px][23px][1px][grow][]"));
		panel_1.add(btnNewButton, "cell 0 0,growx,aligny top");

		btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1){
					EditEmailDialog eed = new EditEmailDialog(((MyTableModel)table.getModel()).getAddressBook().getContacts().get(table.getSelectedRow()));
					eed.setVisible(true);
					table.updateUI();
				}
			}
		});
		panel_1.add(btnNewButton_1, "cell 0 1,growx,aligny top");

		separator = new JSeparator();
		panel_1.add(separator, "cell 0 2,grow");

		btnDelete = new JButton("Delete");
		panel_1.add(btnDelete, "cell 0 3,growx,aligny top");
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1){
					String email = ((MyTableModel)table.getModel()).getAddressBook().getContacts().get(table.getSelectedRow()).getEmail();
					((MyTableModel)table.getModel()).getAddressBook().removeContactByEmail(email);
					table.updateUI();
				}
			}
		});

		separator_1 = new JSeparator();
		panel_1.add(separator_1, "cell 0 4,grow");

		//		btnApply = new JButton("Apply");
		//		btnApply.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				setVisible(false);
		//			}
		//		});

		//		GridBagConstraints gbc_btnApply = new GridBagConstraints();
		//		gbc_btnApply.anchor = GridBagConstraints.NORTH;
		//		gbc_btnApply.fill = GridBagConstraints.HORIZONTAL;
		//		gbc_btnApply.insets = new Insets(0, 0, 5, 0);
		//		gbc_btnApply.gridx = 0;
		//		gbc_btnApply.gridy = 5;
		//		panel_1.add(btnApply, gbc_btnApply);

		btnCancel = new JButton("Close");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_1.add(btnCancel, "cell 0 6,grow");

		setVisible(true);
	}

}

class MyTableModel extends AbstractTableModel {
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

	/*
	 * JTable uses this method to determine the default renderer/
	 * editor for each cell.  If we didn't implement this method,
	 * then the last column would contain text ("true"/"false"),
	 * rather than a check box.
	 */
	@SuppressWarnings("unchecked")
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's
	 * editable.
	 */
	public boolean isCellEditable(int row, int col) {
		//Note that the data/cell address is constant,
		//no matter where the cell appears onscreen.
		if (col > 0) {
			return false;
		} else {
			return true;
		}
	}
	/*
	 * Don't need to implement this method unless your table's
	 * data can change.
	 */
	public void setValueAt(Object value, int row, int col) {
	}

	public AddressBook getAddressBook(){
		return addressBook;
	}

}
