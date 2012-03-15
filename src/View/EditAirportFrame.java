import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class EditAirportFrame {

	private JFrame frmEditAirport;
	private JTextField AirportName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditAirportFrame window = new EditAirportFrame();
					window.frmEditAirport.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditAirportFrame() {
		frmEditAirport = new JFrame();
		frmEditAirport.setResizable(false);
		frmEditAirport.setTitle("Edit airport");
		frmEditAirport.setBounds(100, 100, 352, 300);
		frmEditAirport.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEditAirport.getContentPane().setLayout(null);
		
		AirportName = new JTextField();
		AirportName.setText("London Heathrow");
		AirportName.setBounds(20, 11, 151, 20);
		frmEditAirport.getContentPane().add(AirportName);
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
		frmEditAirport.getContentPane().add(list);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditRunwayFrame edr = new EditRunwayFrame(list);
				edr.setVisible(true);
			}
		});
		btnEdit.setBounds(185, 76, 131, 23);
		frmEditAirport.getContentPane().add(btnEdit);
		
		JButton btnNewRunway = new JButton("New Runway");
		btnNewRunway.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditRunwayFrame edr = new EditRunwayFrame(list);
				edr.setVisible(true);
			}
		});
		btnNewRunway.setBounds(185, 42, 131, 23);
		frmEditAirport.getContentPane().add(btnNewRunway);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(185, 120, 131, 23);
		frmEditAirport.getContentPane().add(btnDelete);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEditAirport.setVisible(false);
			}
		});
		btnOk.setBounds(185, 178, 131, 23);
		frmEditAirport.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEditAirport.setVisible(false);
			}
		});
		btnCancel.setBounds(185, 212, 131, 23);
		frmEditAirport.getContentPane().add(btnCancel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(195, 110, 121, 11);
		frmEditAirport.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(195, 154, 121, 11);
		frmEditAirport.getContentPane().add(separator_1);
		
		frmEditAirport.setVisible(true);
	}
}
