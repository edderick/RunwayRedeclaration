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

import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class EditObstacleDialog extends JDialog {

	private JPanel contentPane;
	private JTextField tfHeight;
	private JTextField tfThresholdDistance;
	private JTextField tfCentreLineDistance;
	private JTextField tfName;

	private List<AirportObserver> airportObservers;
	private Airport airport;

	public EditObstacleDialog(Obstacle obstacle, Obstacle old, Airport airport, List<AirportObserver> airportObservers) {

		this.airportObservers = airportObservers; 
		this.airport = airport;

		setResizable(false);
		setTitle("Edit Obstacle");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 355, 250);
		
		contentPane = new JPanel();
		contentPane.setLayout(new MigLayout("", "[100px:n,grow]", "[15px][24px,grow][][6.00][]"));
		setContentPane(contentPane);

		JPanel namePanel = new JPanel();
		contentPane.add(namePanel, "cell 0 1,grow");

		JLabel lblName = new JLabel("Name");
		namePanel.add(lblName);

		tfName = new JTextField();
		tfName.setPreferredSize(new Dimension(10, 20));
		namePanel.add(tfName);

		JPanel fieldsPanel = new JPanel();
		contentPane.add(fieldsPanel, "cell 0 2,grow");
		fieldsPanel.setLayout(new MigLayout("", "[37px][37px,grow]", "[15px][][][]"));

		JLabel lblCloserTo = new JLabel("Closer To");
		fieldsPanel.add(lblCloserTo, "cell 0 0,alignx trailing");

		JLabel lblHeight = new JLabel("Height");
		fieldsPanel.add(lblHeight, "cell 0 1,alignx trailing,aligny top");

		tfHeight = new JTextField();
		lblHeight.setLabelFor(tfHeight);
		fieldsPanel.add(tfHeight, "flowx,cell 1 1,growx");
		tfHeight.setColumns(10);

		JLabel lblThresholdDistance = new JLabel("Dist. From Threshold");
		fieldsPanel.add(lblThresholdDistance, "cell 0 2,alignx trailing");

		tfThresholdDistance = new JTextField();
		lblThresholdDistance.setLabelFor(tfThresholdDistance);
		fieldsPanel.add(tfThresholdDistance, "flowx,cell 1 2,growx");
		tfThresholdDistance.setColumns(10);

		JLabel lblCentreLineDistance = new JLabel("Dist. From Centre Line");
		fieldsPanel.add(lblCentreLineDistance, "cell 0 3,alignx trailing");

		tfCentreLineDistance = new JTextField();
		lblCentreLineDistance.setLabelFor(tfCentreLineDistance);
		fieldsPanel.add(tfCentreLineDistance, "flowx,cell 1 3,growx");
		tfCentreLineDistance.setColumns(10);

		JLabel lblmetres_1 = new JLabel("m");
		fieldsPanel.add(lblmetres_1, "cell 1 1");

		JLabel lblmetres_2 = new JLabel("m");
		fieldsPanel.add(lblmetres_2, "cell 1 2");

		JLabel lblmetres_3 = new JLabel("m");
		fieldsPanel.add(lblmetres_3, "cell 1 3");

		JPanel buttonsPanel = new JPanel();
		contentPane.add(buttonsPanel, "cell 0 4,grow");
		buttonsPanel.setLayout(new MigLayout("", "[grow][][]", "[grow][]"));

		JButton btnApply = new JButton("Apply");
		buttonsPanel.add(btnApply, "cell 1 0");


		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new EODcancelListener(obstacle, old, this));
		buttonsPanel.add(btnCancel, "cell 2 0");

		// Add text to the textfields from the obstacle object
		tfHeight.setText(Double.toString(obstacle.getHeight()));
		tfCentreLineDistance.setText(Double.toString(airport.getCurrentPhysicalRunway().getDistanceAwayFromCenterLine()));
		tfThresholdDistance.setText(Double.toString(airport.getCurrentPhysicalRunway().getDistanceAwayFromThreshold()));

		JComboBox comboBoxCloserTo = new JComboBox();
		fieldsPanel.add(comboBoxCloserTo, "flowx,cell 1 0,alignx right,grow");
		tfName.setText(obstacle.getName());


		if ((airport.getCurrentPhysicalRunway() != null) && 
				(airport.getCurrentPhysicalRunway().getRunway(0) != comboBoxCloserTo.getItemAt(0))){
			comboBoxCloserTo.removeAllItems();
			PhysicalRunway r = airport.getCurrentPhysicalRunway();
			comboBoxCloserTo.addItem(r.getRunway(0));
			comboBoxCloserTo.addItem(r.getRunway(1));
		}



		btnApply.addActionListener(new EODapplyListener(obstacle, airport, this, tfHeight, tfThresholdDistance, tfCentreLineDistance, tfName, comboBoxCloserTo));

		JPanel panel_Spacer = new JPanel();
		fieldsPanel.add(panel_Spacer, "cell 1 0");

		setVisible(true);
	}


	void notifyAirportObservers(){
		for(AirportObserver ao: airportObservers){
			ao.updateAirport(airport);
		}
	}
	
	class EODapplyListener implements ActionListener{
		Obstacle obstacle;
		Airport airport;
		EditObstacleDialog eod;
		JTextField height, distanceToThreshold, distanceToCentreLine, name;
		JComboBox closeTo;

		public void actionPerformed(ActionEvent e) {
			obstacle.setHeight(doubleParser.parse(height.getText()));
			airport.getCurrentPhysicalRunway().setDistanceAwayFromThreshold(doubleParser.parse(distanceToThreshold.getText()));
			airport.getCurrentPhysicalRunway().setDistanceAwayFromCenterLine(doubleParser.parse(distanceToCentreLine.getText()));
			airport.getCurrentPhysicalRunway().setCloserToWhichThreshold(airport.getCurrentRunway().getName());
			obstacle.setName(name.getText());
			
			notifyAirportObservers();
		}

		public EODapplyListener(Obstacle obstacle, Airport airport, EditObstacleDialog eod,
				JTextField height, JTextField distanceToThreshold, JTextField distanceToCentreLine,
				JTextField name, JComboBox closeTo) {
			super();
			this.obstacle = obstacle;
			this.eod = eod;
			this.height = height;
			this.distanceToThreshold = distanceToThreshold;
			this.distanceToCentreLine = distanceToCentreLine;
			this.name = name;
			this.closeTo = closeTo;
			this.airport = airport;
		}
	}

	class EODcancelListener implements ActionListener{
		Obstacle obstacle, revertTo;
		EditObstacleDialog eod;

		public void actionPerformed(ActionEvent e){
			//		obstacle = revertTo;
			eod.dispose();
		}

		public EODcancelListener(Obstacle obstacle, Obstacle revertTo, EditObstacleDialog eod) {
			super();
			this.obstacle = obstacle;
			this.revertTo = revertTo;
			this.eod = eod;
		}


	}
}
