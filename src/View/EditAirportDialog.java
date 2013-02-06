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
import net.miginfocom.swing.MigLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;


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
		getContentPane().setLayout(new MigLayout("", "[][grow][100px:n]", "[][][][grow][][grow][][grow][][grow][][]"));

		JLabel lblNameOfAirport = new JLabel("Name:");
		getContentPane().add(lblNameOfAirport, "cell 0 0,alignx left,aligny center");

		tfAirportName = new JTextField();
		tfAirportName.setText(airport.getName());
		getContentPane().add(tfAirportName, "cell 1 0,growx,aligny top");
		DefaultListModel physicalRunwayListModel = new DefaultListModel();

		for(int i = 0; i < physicalRunwayNames.size(); i++){
			physicalRunwayListModel.addElement(physicalRunwayNames.get(i));
		}

		JButton btnNewRunway = new JButton("New Runway");
		btnNewRunway.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("data/AddIcon.png")));

		JList listOfRunways = new JList();
		listOfRunways.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listOfRunways.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		listOfRunways.setModel(physicalRunwayListModel);
		listOfRunways.setSelectedIndex(0);
		getContentPane().add(listOfRunways, "cell 0 1 2 11,grow");
		btnNewRunway.addActionListener(new EditListener(listOfRunways, true));
		
		JButton btnEdit = new JButton("Edit");
		getContentPane().add(btnEdit, "cell 2 2,grow");
		btnEdit.addActionListener(new EditListener(listOfRunways, false));
		getContentPane().add(btnNewRunway, "cell 2 1,grow");


		JSeparator separator = new JSeparator();
		getContentPane().add(separator, "cell 2 4,grow");

		JButton btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("data/DeleteIcon.png")));
		getContentPane().add(btnDelete, "cell 2 6,grow");


		btnDelete.addActionListener(new DeleteListener(listOfRunways));
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new OkListener());

		JSeparator separator_1 = new JSeparator();
		getContentPane().add(separator_1, "cell 2 8,grow");
		getContentPane().add(btnOk, "cell 2 10,grow");

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		getContentPane().add(btnCancel, "cell 2 11,growx");

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