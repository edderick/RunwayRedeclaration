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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.TabExpander;

import Model.AddressBook;
import Model.Contact;

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

	private AddressBook addressBook; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SendEmailDialog dialog = new SendEmailDialog(new AddressBook());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	class MyTableModel extends AbstractTableModel {
		private String[] columnNames = {"Send","First name", "Last name" ,"Email"};
		private List<Boolean> send = new ArrayList<Boolean>();
		private List<String> firstName = new ArrayList<String>();
		private List<String> lastName = new ArrayList<String>();
		private List<String> email = new ArrayList<String>();
		
		public void addContact(Contact contact){
			send.add(false);
			firstName.add(contact.getFirstName());
			lastName.add(contact.getLastName());
			email.add(contact.getEmail());
			
		}
		
		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return send.size();
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			switch(col){
			case 0 : return send.get(row);
			case 1 : return firstName.get(row);
			case 2 : return lastName.get(row);
			case 3 : return email.get(row);
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
			switch(col){
			case 0 : send.set(row, (Boolean)value); break;
			case 1 : firstName.set(row, (String)value); break;
			case 2 : lastName.set(row, (String)value); break;
			case 3 : email.set(row, (String) value); break;
			}
			fireTableCellUpdated(row, col);
			if(col == 0 && (Boolean) getValueAt(row, 0)){ System.out.println("Email will be sent to: "+this.getValueAt(row, col+2)); }
			
		}

	}



	/**
	 * Create the dialog.
	 */
	public SendEmailDialog(AddressBook addressBook) {
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
	
		scrollPane.setViewportView(table);
		
		for(Contact c : addressBook.getContacts()){
			tableModel.addContact(c);
		}
		
		tableModel.addContact(new Contact("edd", "se", "lol"));
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 1 0,grow");
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{65, 0};
		gbl_panel_1.rowHeights = new int[]{23, 0, 23, 1, 23, 1, 23, 23, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);

		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
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
				setVisible(false);
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

	AddressBook addressBook;
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new SendEmailDialog(addressBook);
	}

}
