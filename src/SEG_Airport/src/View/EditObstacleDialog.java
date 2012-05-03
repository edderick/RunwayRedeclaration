package View;

import java.util.List;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Airport;
import Model.AirportObserver;
import Model.Obstacle;
import Model.PhysicalRunway;
import Model.Runway;

import net.miginfocom.swing.MigLayout;



public class EditObstacleDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfHeight;
	private JTextField tfThresholdDistance;
	private JTextField tfCentreLineDistance;
	private JTextField tfName;
	JComboBox comboBoxCloserTo;
	
	private List<AirportObserver> airportObservers;
	private Airport airport;
	private Obstacle obstacle;

	public EditObstacleDialog(Obstacle obstacle, Obstacle old, Airport airport, List<AirportObserver> airportObservers) {

		this.airportObservers = airportObservers; 
		this.airport = airport;
		this.obstacle = obstacle;

		setResizable(false);
		setTitle("Edit Obstacle");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 355, 250);
		
		contentPane = new JPanel();
		contentPane.setLayout(new MigLayout("", "[100px:n,grow]", "[][6.00][grow][]"));
		setContentPane(contentPane);

		JPanel fieldsPanel = new JPanel();
		contentPane.add(fieldsPanel, "cell 0 0,grow");
		fieldsPanel.setLayout(new MigLayout("", "[37px][37px,grow]", "[][15px][][][]"));
		
				JLabel lblName = new JLabel("Name");
				fieldsPanel.add(lblName, "cell 0 0,alignx right");
		
				tfName = new JTextField();
				fieldsPanel.add(tfName, "flowx,cell 1 0,growx");
				tfName.setColumns(10);
				tfName.setPreferredSize(new Dimension(10, 20));
				tfName.setText(obstacle.getName());

		JLabel lblCloserTo = new JLabel("Closer To");
		fieldsPanel.add(lblCloserTo, "cell 0 1,alignx trailing");

		JLabel lblHeight = new JLabel("Height");
		fieldsPanel.add(lblHeight, "cell 0 2,alignx trailing,aligny top");

		tfHeight = new JTextField();
		lblHeight.setLabelFor(tfHeight);
		fieldsPanel.add(tfHeight, "flowx,cell 1 2,growx");
		tfHeight.setColumns(10);

		JLabel lblThresholdDistance = new JLabel("Dist. From Threshold");
		fieldsPanel.add(lblThresholdDistance, "cell 0 3,alignx trailing");

		tfThresholdDistance = new JTextField();
		lblThresholdDistance.setLabelFor(tfThresholdDistance);
		fieldsPanel.add(tfThresholdDistance, "flowx,cell 1 3,growx");
		tfThresholdDistance.setColumns(10);

		JLabel lblCentreLineDistance = new JLabel("Dist. From Centre Line");
		fieldsPanel.add(lblCentreLineDistance, "cell 0 4,alignx trailing");

		tfCentreLineDistance = new JTextField();
		lblCentreLineDistance.setLabelFor(tfCentreLineDistance);
		fieldsPanel.add(tfCentreLineDistance, "flowx,cell 1 4,growx");
		tfCentreLineDistance.setColumns(10);

		JLabel lblmetres_1 = new JLabel("m");
		fieldsPanel.add(lblmetres_1, "cell 1 2");

		JLabel lblmetres_2 = new JLabel("m");
		fieldsPanel.add(lblmetres_2, "cell 1 3");

		JLabel lblmetres_3 = new JLabel("m");
		fieldsPanel.add(lblmetres_3, "cell 1 4");

		JPanel buttonsPanel = new JPanel();
		contentPane.add(buttonsPanel, "cell 0 3,grow");
		buttonsPanel.setLayout(new MigLayout("", "[grow][][]", "[grow][]"));
		
				JButton btnApply = new JButton("Apply");
				buttonsPanel.add(btnApply, "cell 1 1");
				
						JButton btnCancel = new JButton("Cancel");
						btnCancel.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								dispose();
							}
						});
						buttonsPanel.add(btnCancel, "cell 2 1");
				
						btnApply.addActionListener(new ApplyListener());

		// Add text to the textfields from the obstacle object
		tfHeight.setText(Double.toString(obstacle.getHeight()));
		tfCentreLineDistance.setText(Double.toString(airport.getCurrentPhysicalRunway().getDistanceAwayFromCenterLine()));
		tfThresholdDistance.setText(Double.toString(airport.getCurrentPhysicalRunway().getDistanceAwayFromThreshold()));

		comboBoxCloserTo = new JComboBox();
		fieldsPanel.add(comboBoxCloserTo, "flowx,cell 1 1,alignx right,grow");

		//Ensure current physical runway is set and the runway has changed
		if ((airport.getCurrentPhysicalRunway() != null) && 
			(airport.getCurrentPhysicalRunway().getRunway(0) != comboBoxCloserTo.getItemAt(0))){
			comboBoxCloserTo.removeAllItems();
			PhysicalRunway r = airport.getCurrentPhysicalRunway();
			comboBoxCloserTo.addItem(r.getRunway(0));
			comboBoxCloserTo.addItem(r.getRunway(1));
			comboBoxCloserTo.setSelectedItem(airport.getCurrentPhysicalRunway().closeTo());
		}

		JPanel panel_Spacer = new JPanel();
		fieldsPanel.add(panel_Spacer, "cell 1 1");
		
		JPanel panel_Spacer2 = new JPanel();
		fieldsPanel.add(panel_Spacer2, "cell 1 0");

		setVisible(true);
	}


	void notifyAirportObservers(){
		for(AirportObserver ao: airportObservers){
			ao.updateAirport(airport);
		}
	}
	
	class ApplyListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			obstacle.setHeight(DoubleParser.parse(tfHeight.getText()));
			airport.getCurrentPhysicalRunway().setDistanceAwayFromThreshold(DoubleParser.parse(tfThresholdDistance.getText()));
			airport.getCurrentPhysicalRunway().setDistanceAwayFromCenterLine(DoubleParser.parse(tfCentreLineDistance.getText()));
			airport.getCurrentPhysicalRunway().setCloserToWhichThreshold(((Runway)comboBoxCloserTo.getSelectedItem()).getName());
			obstacle.setName(tfName.getText());
			
			notifyAirportObservers();
			dispose();
		}
	}
}
