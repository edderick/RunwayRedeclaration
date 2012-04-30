package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import Model.*;

import javax.swing.JLabel;


@SuppressWarnings("serial")
public class EditAirportDialog extends JDialog {

	private JTextField AirportName;

	public EditAirportDialog(Airport airport, Airport old, List<AirportObserver> airportObservers) {
		System.out.println("old size: " + old.getPhysicalRunways().size());
		ArrayList<String> physicalRunwayNames = new ArrayList<String>();
		
		for(PhysicalRunway p : airport.getPhysicalRunways()){
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
		btnEdit.addActionListener(new editListener(airport,list,false,airportObservers));
		btnEdit.setBounds(185, 76, 131, 23);
		getContentPane().add(btnEdit);

		JButton btnNewRunway = new JButton("New Runway");
		btnNewRunway.addActionListener(new editListener(airport,list,true,airportObservers));
		btnNewRunway.setBounds(185, 42, 131, 23);
		getContentPane().add(btnNewRunway);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new EADdeleteListener(list, airport));
		btnDelete.setBounds(185, 143, 131, 23);
		getContentPane().add(btnDelete);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new okListener(this, AirportName, airport, airportObservers));
		btnOk.setBounds(185, 221, 131, 23);
		getContentPane().add(btnOk);

		JSeparator separator = new JSeparator();
		separator.setBounds(195, 121, 121, 11);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(195, 183, 121, 11);
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
	List<AirportObserver> airportObservers; 
	public void actionPerformed(ActionEvent e) {
		a.setName(jt.getText());
		System.out.println(a.getPhysicalRunways().size());
		jd.setVisible(false);
		notifyAirportObservers();
	}
	public okListener(JDialog jd, JTextField jt, Airport a, List<AirportObserver> airportObservers) {
		this.jd = jd; this.jt = jt; this.a = a; this.airportObservers = airportObservers;
	}	
	
	void notifyAirportObservers(){
		for(AirportObserver ao: airportObservers){
			ao.updateAirport(a);
		}
	}
	
}

class editListener implements ActionListener{
	Airport a;
	JList physicalRunwayJList;
	boolean newRunway;
	List<AirportObserver> airportObservers;
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		EditRunwayDialog erd = new EditRunwayDialog(a,physicalRunwayJList,newRunway, airportObservers);	
	}
	public editListener(Airport a, JList physicalRunwayJList, boolean newRunway, List<AirportObserver> airportObservers) {
		this.a = a;
		this.physicalRunwayJList = physicalRunwayJList;
		this.newRunway = newRunway;
		this.airportObservers = airportObservers;
	}	
}

class EADcancelListener implements ActionListener{
	Airport airport, old;
	EditAirportDialog ead;

	public void actionPerformed(ActionEvent e) {
		ead.setVisible(false);
	}

	public EADcancelListener(Airport airport, Airport old, EditAirportDialog ead) {
		super();
		this.airport = airport;
		this.old = old;
		this.ead = ead;
	}

}

class EADdeleteListener implements ActionListener{
	JList physicalRunwayJList;
	Airport a;
	public void actionPerformed(ActionEvent arg0) {
		int index = physicalRunwayJList.getSelectedIndex();
		a.getPhysicalRunways().remove(index);
		ArrayList<String> physicalRunwayNames = new ArrayList<String>();
		for(PhysicalRunway p : a.getPhysicalRunways()){
			physicalRunwayNames.add(p.getId());
		}
		DefaultListModel pr = new DefaultListModel();
		for(int i = 0; i < physicalRunwayNames.size(); i++){
			pr.addElement(physicalRunwayNames.get(i));
		}
		physicalRunwayJList.setModel(pr);
		physicalRunwayJList.setSelectedIndex(0);
	}
	public EADdeleteListener(JList physicalRunwayJList, Airport a) {
		super();
		this.physicalRunwayJList = physicalRunwayJList;
		this.a = a;
	}

}