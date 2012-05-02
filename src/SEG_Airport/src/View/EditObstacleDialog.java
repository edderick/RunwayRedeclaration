package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.SelectRunwayListener;
import Model.*;
import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class EditObstacleDialog extends JDialog {

	private JPanel contentPane;
	private JTextField TF_HEIGHT;
	private JTextField TF_ThresholdDistance;
	private JTextField TF_CentereLineDistance;
	private JTextField TF_NAME;
	
	public EditObstacleDialog(Obstacle obstacle, Obstacle old, Airport airport) {
		
		setResizable(false);
		setTitle("Edit Obstacle");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 353, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[100px:n,grow]", "[15px][24px,grow][][6.00][]"));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 0 1,grow");
		
		JLabel lblName = new JLabel("Name");
		panel_1.add(lblName);
		
		TF_NAME = new JTextField();
		TF_NAME.setPreferredSize(new Dimension(10, 20));
		panel_1.add(TF_NAME);
		TF_NAME.setColumns(15);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 2,grow");
		panel.setLayout(new MigLayout("", "[37px][37px,grow]", "[15px][][][]"));
		
		JLabel lblCloserTo = new JLabel("Closer To");
		panel.add(lblCloserTo, "cell 0 0,alignx trailing");
		
		JLabel lblHeight = new JLabel("Height");
		panel.add(lblHeight, "cell 0 1,alignx trailing,aligny top");
		
		TF_HEIGHT = new JTextField();
		lblHeight.setLabelFor(TF_HEIGHT);
		panel.add(TF_HEIGHT, "flowx,cell 1 1,growx");
		TF_HEIGHT.setColumns(10);
		
		JLabel lblThresholdDistance = new JLabel("Dist. From Threshold");
		panel.add(lblThresholdDistance, "cell 0 2,alignx trailing");
		
		TF_ThresholdDistance = new JTextField();
		lblThresholdDistance.setLabelFor(TF_ThresholdDistance);
		panel.add(TF_ThresholdDistance, "flowx,cell 1 2,growx");
		TF_ThresholdDistance.setColumns(10);
		
		JLabel lblCentreLineDistance = new JLabel("Dist. From Centre Line");
		panel.add(lblCentreLineDistance, "cell 0 3,alignx trailing");
		
		TF_CentereLineDistance = new JTextField();
		lblCentreLineDistance.setLabelFor(TF_CentereLineDistance);
		panel.add(TF_CentereLineDistance, "flowx,cell 1 3,growx");
		TF_CentereLineDistance.setColumns(10);
		
		JLabel lblmetres_1 = new JLabel("m");
		panel.add(lblmetres_1, "cell 1 1");
		
		JLabel lblmetres_2 = new JLabel("m");
		panel.add(lblmetres_2, "cell 1 2");
		
		JLabel lblmetres_3 = new JLabel("m");
		panel.add(lblmetres_3, "cell 1 3");
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, "cell 0 4,grow");
		panel_2.setLayout(new MigLayout("", "[grow][][]", "[grow][]"));
		
		JButton btnNewButton = new JButton("Apply");
		panel_2.add(btnNewButton, "cell 1 0");
	
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new EODcancelListener(obstacle, old, this));
		panel_2.add(btnNewButton_1, "cell 2 0");
		
		// Add text to the textfields from the obstacle object
		TF_HEIGHT.setText(Double.toString(obstacle.getHeight()));
		TF_CentereLineDistance.setText(Double.toString(airport.getCurrentPhysicalRunway().getDistanceAwayFromCenterLine()));
		TF_ThresholdDistance.setText(Double.toString(airport.getCurrentPhysicalRunway().getDistanceAwayFromThreshold()));
		
		JComboBox comboBoxCloserTo = new JComboBox();
		panel.add(comboBoxCloserTo, "flowx,cell 1 0,alignx right,grow");
		TF_NAME.setText(obstacle.getName());

		
		if ((airport.getCurrentPhysicalRunway() != null) && 
				(airport.getCurrentPhysicalRunway().getRunway(0) != comboBoxCloserTo.getItemAt(0))){
			comboBoxCloserTo.removeAllItems();
			PhysicalRunway r = airport.getCurrentPhysicalRunway();
			comboBoxCloserTo.addItem(r.getRunway(0));
			comboBoxCloserTo.addItem(r.getRunway(1));
		}
		
		
		
		btnNewButton.addActionListener(new EODapplyListener(obstacle, airport, this, TF_HEIGHT, TF_ThresholdDistance, TF_CentereLineDistance, TF_NAME, comboBoxCloserTo));
		
		JPanel panel_Spacer = new JPanel();
		panel.add(panel_Spacer, "cell 1 0");
		
		setVisible(true);
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

		eod.dispose();
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
