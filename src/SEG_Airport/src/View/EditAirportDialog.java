package View;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import Model.*;
import javax.swing.JLabel;


public class EditAirportDialog extends JDialog {

	//private final JPanel contentPanel = new JPanel();	
	private JTextField AirportName;
	private Airport airport;
	private Airport airport_backup;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			EditAirportDialog dialog = new EditAirportDialog(airport);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public EditAirportDialog(Airport apt) {
		airport = apt;
		airport_backup = apt;
		ArrayList<String> physicalRunwayNames = new ArrayList<String>();
		for(PhysicalRunway p : airport.runways()){
			physicalRunwayNames.add(p.getId());
		}
		
		setResizable(false);
		setTitle("Edit airport");
		setBounds(100, 100, 352, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		AirportName = new JTextField();
		AirportName.setText(airport.getName());
		AirportName.setBounds(59, 11, 151, 20);
		getContentPane().add(AirportName);
		AirportName.setColumns(10);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel pr = new DefaultListModel();
		for(int i = 0; i < physicalRunwayNames.size(); i++){
			pr.addElement(physicalRunwayNames.get(i));
		}
		list.setModel(pr);
		list.setSelectedIndex(0);
		list.setBounds(20, 42, 141, 202);
		getContentPane().add(list);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new editListener(airport,list,false));
		btnEdit.setBounds(185, 76, 131, 23);
		getContentPane().add(btnEdit);
		
		JButton btnNewRunway = new JButton("New Runway");
		btnNewRunway.addActionListener(new editListener(airport,list,true));
		btnNewRunway.setBounds(185, 42, 131, 23);
		getContentPane().add(btnNewRunway);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(185, 120, 131, 23);
		getContentPane().add(btnDelete);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new okListener(this, AirportName, airport));
		btnOk.setBounds(185, 178, 131, 23);
		getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				airport = airport_backup;
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
		
		JLabel lblNameOfAirport = new JLabel("Name:");
		lblNameOfAirport.setBounds(20, 14, 46, 14);
		getContentPane().add(lblNameOfAirport);
		
		setVisible(true);

	}
}

class okListener implements ActionListener{
	JDialog jd;
	JTextField jt;
	Airport a;
	public void actionPerformed(ActionEvent e) {
		a.setName(jt.getText());
		jd.setVisible(false);
	}
	public okListener(JDialog jd, JTextField jt, Airport a) {
		this.jd = jd; this.jt = jt; this.a = a;
	}	
}
class editListener implements ActionListener{
	Airport a;
	JList physicalRunwayJList;
	boolean newRunway;
	public void actionPerformed(ActionEvent e) {
		EditRunwayDialog erd = new EditRunwayDialog(a,physicalRunwayJList,newRunway);		
	}
	public editListener(Airport a, JList physicalRunwayJList, boolean newRunway) {
		this.a = a;
		this.physicalRunwayJList = physicalRunwayJList;
		this.newRunway = newRunway;
	}	
}
//class editListener implements ActionListener{
//	Airport a;
//	public void actionPerformed(ActionEvent e) {
//		
//		
//	}
//	public okListener(Airport a) {
//		this.a = a;
//	}	
//}