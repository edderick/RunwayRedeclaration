package View;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import Model.AddressBook;
import Model.Airport;
import Model.Contact;
import Model.Email;

import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class SendEmailDialog extends JDialog {

	@SuppressWarnings("unused")
	private final JPanel contentPanel = new JPanel();

	private JPanel contentPane;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnSend;
	private JButton btnCancel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SendEmailDialog dialog = new SendEmailDialog(new AddressBook(), new Airport(""));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	class MyTableModel extends AbstractTableModel {
		private String[] columnNames = {"Send","First name", "Last name" ,"Email"};
		private List<Contact> contacts = new ArrayList<Contact>();
		private List<Contact> selected = new ArrayList<Contact>();

		public void addContact(Contact contact){
			contacts.add(contact);
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return contacts.size();
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			switch(col){
			case 0 : return selected.contains(contacts.get(row));
			case 1 : return contacts.get(row).getFirstName();
			case 2 : return contacts.get(row).getLastName();
			case 3 : return contacts.get(row).getEmail();
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
			if (col == 0){ 
				Contact contact = contacts.get(row);
				if(selected.contains(contact)) selected.remove(contact);
				else selected.add(contact);			
			}

			fireTableCellUpdated(row, col);
		}

		public List<Contact> getSelected(){
			return selected;
		}

	}



	/**
	 * Create the dialog.
	 */
	public SendEmailDialog(AddressBook addressBook, Airport airport) {
		setTitle("Address Book");
		setBounds(100, 100, 481, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0,grow");

		@SuppressWarnings("unused")
		JCheckBox jc1 = new JCheckBox();
		table = new JTable(){
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth(Math.max(rendererWidth +
						getIntercellSpacing().width,
						tableColumn.getPreferredWidth()));
				return  component;
			}
		};
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ON); // turns this table into a 'spring' table		

		MyTableModel tableModel = new MyTableModel();

		table.setModel(tableModel);
		table.setRowSelectionAllowed(false);

		scrollPane.setViewportView(table);

		for(Contact c : addressBook.getContacts()){
			tableModel.addContact(c);
		}

		tableModel.addContact(new Contact("edd", "se", "ejfs1g10@soton.ac.uk"));

		panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 1 0,grow");
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{65, 0};
		gbl_panel_1.rowHeights = new int[]{23, 0, 23, 1, 23, 1, 23, 23, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);

		btnSend = new JButton("Send");
		btnSend.addActionListener(new sendButtonListener(tableModel.getSelected(), airport));
		GridBagConstraints gbc_btnApply = new GridBagConstraints();
		gbc_btnApply.anchor = GridBagConstraints.NORTH;
		gbc_btnApply.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnApply.insets = new Insets(0, 0, 5, 0);
		gbc_btnApply.gridx = 0;
		gbc_btnApply.gridy = 0;
		panel_1.add(btnSend, gbc_btnApply);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancel.fill = GridBagConstraints.BOTH;
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 6;
		panel_1.add(btnCancel, gbc_btnCancel);
		setVisible(true);
	}

}

class sendButtonListener implements ActionListener{

	List<Contact> contacts; 
	Airport airport;
	String subject, body;

	public sendButtonListener(List<Contact> contacts, Airport airport){
		this.contacts = contacts;
		this.airport = airport;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(airport.getCurrentRunway() != null && airport.getCurrentPhysicalRunway() != null){
			subject = "Calculations for " + airport.getCurrentRunway().getName() +" at " +airport.getName();
			body = airport.getCurrentPhysicalRunway().toCalculation(airport.getCurrentRunway().getName());
		}
		else {
			JOptionPane.showMessageDialog(null, "No current obstacle!");
			return;
		}
		
		new Thread(){
			public void run(){
				for (Contact c : contacts){
					System.out.println(c.getEmail());
				}
				if(contacts.size() != 0){
					Email email = new Email();
					email.addRecipients(contacts);
					email.setSubject(subject);
					email.setBody(body);
					email.send();
				}
			}
		}.start();
	}

}
