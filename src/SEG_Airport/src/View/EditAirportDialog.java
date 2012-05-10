package View;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;

import Model.Airport;
import Model.AirportObserver;
import Model.PhysicalRunway;


public class EditAirportDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField tfAirportName;

	private Airport airport;
	private List<AirportObserver> airportObservers;
	
	public EditAirportDialog(Airport airport, Airport old, List<AirportObserver> airportObservers) {

		this.airport = airport;
		this.airportObservers = airportObservers;
		
		ArrayList<String> physicalRunwayNames = new ArrayList<String>();

		for(PhysicalRunway p : airport.getPhysicalRunways()){
			physicalRunwayNames.add(p.getId());
		}

		setResizable(true);
		setTitle("Edit airport");
		setBounds(100, 100, 352, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		tfAirportName = new JTextField();
		tfAirportName.setText(airport.getName());
		tfAirportName.setBounds(60, 10, 130, 20);
		getContentPane().add(tfAirportName);

		JList listOfRunways = new JList();
		listOfRunways.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel physicalRunwayListModel = new DefaultListModel();

		for(int i = 0; i < physicalRunwayNames.size(); i++){
			physicalRunwayListModel.addElement(physicalRunwayNames.get(i));
		}

		listOfRunways.setModel(physicalRunwayListModel);
		listOfRunways.setSelectedIndex(0);
		listOfRunways.setBounds(20, 45, 140, 200);
		getContentPane().add(listOfRunways);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new EditListener(listOfRunways, false));
		btnEdit.setBounds(185, 75, 130, 25);
		getContentPane().add(btnEdit);

		JButton btnNewRunway = new JButton("New Runway");
		btnNewRunway.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("data/AddIcon.png")));
		btnNewRunway.addActionListener(new EditListener(listOfRunways, true));
		btnNewRunway.setBounds(185, 45, 130, 25);
		getContentPane().add(btnNewRunway);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("data/DeleteIcon.png")));
		btnDelete.addActionListener(new DeleteListener(listOfRunways));
		btnDelete.setBounds(185, 145, 130, 25);
		getContentPane().add(btnDelete);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new OkListener());
		btnOk.setBounds(185, 220, 130, 25);
		getContentPane().add(btnOk);

		JSeparator separator = new JSeparator();
		separator.setBounds(195, 120, 120, 10);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(195, 184, 120, 10);
		getContentPane().add(separator_1);

		JLabel lblNameOfAirport = new JLabel("Name:");
		lblNameOfAirport.setBounds(20, 14, 46, 14);
		getContentPane().add(lblNameOfAirport);

		setAlwaysOnTop(true);
		setVisible(true);

	}

	void notifyAirportObservers(){
		for(AirportObserver ao: airportObservers){
			ao.updateAirport(airport);
		}
	}

	
	class OkListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			airport.setName(tfAirportName.getText());
			System.out.println(airport.getPhysicalRunways().size());
			airport.setModified();
			notifyAirportObservers();
			dispose();
		}
	}

	
	class EditListener implements ActionListener{
		JList physicalRunwayJList;
		boolean newRunway;
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			EditRunwayDialog erd = new EditRunwayDialog(airport,physicalRunwayJList,newRunway, airportObservers);	
		}
		public EditListener(JList physicalRunwayJList, boolean newRunway) {
			this.physicalRunwayJList = physicalRunwayJList;
			this.newRunway = newRunway;
		}	
	}

	
	class DeleteListener implements ActionListener{
		JList physicalRunwayJList;
		public void actionPerformed(ActionEvent arg0) {
			int index = physicalRunwayJList.getSelectedIndex();
			
			airport.getPhysicalRunways().remove(index);
			
			ArrayList<String> physicalRunwayNames = new ArrayList<String>();
			for(PhysicalRunway p : airport.getPhysicalRunways()){
				physicalRunwayNames.add(p.getId());
			}
			
			DefaultListModel pr = new DefaultListModel();
			for(int i = 0; i < physicalRunwayNames.size(); i++){
				pr.addElement(physicalRunwayNames.get(i));
			}
			
			physicalRunwayJList.setModel(pr);
			physicalRunwayJList.setSelectedIndex(0);
		}
		public DeleteListener(JList physicalRunwayJList) {
			this.physicalRunwayJList = physicalRunwayJList;
		}
	}
}