package view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;


public class EditAirportDialog extends JDialog {

	//private final JPanel contentPanel = new JPanel();	
	private JTextField AirportName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditAirportDialog dialog = new EditAirportDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditAirportDialog() {
		
		setResizable(false);
		setTitle("Edit airport");
		setBounds(100, 100, 352, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		AirportName = new JTextField();
		AirportName.setText("London Heathrow");
		AirportName.setBounds(20, 11, 151, 20);
		getContentPane().add(AirportName);
		AirportName.setColumns(10);
		
		final JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"09L/27R", "27L/09R"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(0);
		list.setBounds(20, 42, 141, 202);
		getContentPane().add(list);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditRunwayDialog edd = new EditRunwayDialog(list);
				edd.setVisible(true);
			}
		});
		btnEdit.setBounds(185, 76, 131, 23);
		getContentPane().add(btnEdit);
		
		JButton btnNewRunway = new JButton("New Runway");
		btnNewRunway.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditRunwayDialog edd = new EditRunwayDialog(list);
				edd.setVisible(true);
			}
		});
		btnNewRunway.setBounds(185, 42, 131, 23);
		getContentPane().add(btnNewRunway);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(185, 120, 131, 23);
		getContentPane().add(btnDelete);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnOk.setBounds(185, 178, 131, 23);
		getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(185, 212, 131, 23);
		getContentPane().add(btnCancel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(195, 110, 121, 11);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(195, 154, 121, 11);
		getContentPane().add(separator_1);
		
		setVisible(true);

	}

}
