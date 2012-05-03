package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

import Model.Airport;
import Model.AirportObserver;
import Model.Obstacle;
import Model.PhysicalRunway;

@SuppressWarnings("serial")
public class AdvancedParametersDialog extends JDialog {

	@SuppressWarnings("unused")
	private Obstacle obstacle, obstacle_backup;
	private JTextField tfRESA;
	private JTextField tfStopway;
	private JTextField tfBlastAllowance;
	private JTextField tfAngleOfSlope;
	private JTextField tfRunwayStripWidth;
	private JTextField tfClearAndGradedWidth;
	private JTextField tfObstacleWidth;
	private JTextField tfObstacleLength;
	
	private Airport airport;
	private PhysicalRunway physicalRunway;
	private List<AirportObserver> airportObservers;
	
	public AdvancedParametersDialog(Airport airport, List<AirportObserver> airportObservers) {
		setResizable(false);
		setTitle("Advanced Parameters");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 310, 330);
		
		this.airport = airport;
		physicalRunway = airport.getCurrentPhysicalRunway();
		this.airportObservers = airportObservers;
		
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[100px:n,grow]", "[15px,grow][]"));
		
		JPanel fieldsPanel = new JPanel();
		contentPane.add(fieldsPanel, "cell 0 0,grow");
		fieldsPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][grow][][]"));
		
		
		JLabel lblRESA = new JLabel("RESA");
		fieldsPanel.add(lblRESA, "cell 0 0,alignx trailing");
		
		tfRESA = new JTextField();
		fieldsPanel.add(tfRESA, "flowx,cell 1 0,growx");

		
		JLabel labelm0 = new JLabel("m");
		fieldsPanel.add(labelm0, "cell 1 0");
		
		
		JLabel lblStopway = new JLabel("Stopway");
		fieldsPanel.add(lblStopway, "cell 0 1,alignx trailing");
		
		tfStopway = new JTextField();
		fieldsPanel.add(tfStopway, "flowx,cell 1 1,growx");

		
		JLabel labelm1 = new JLabel("m");
		fieldsPanel.add(labelm1, "cell 1 1");
		
		
		JLabel lblBlastAllowance = new JLabel("Blast allowance");
		fieldsPanel.add(lblBlastAllowance, "cell 0 2,alignx trailing");
		
		tfBlastAllowance = new JTextField();
		fieldsPanel.add(tfBlastAllowance, "flowx,cell 1 2,grow");

		
		JLabel labelm2 = new JLabel("m");
		fieldsPanel.add(labelm2, "cell 1 2");
		
		
		JLabel lblAngleOfSlope = new JLabel("Angle of slope");
		fieldsPanel.add(lblAngleOfSlope, "cell 0 3,alignx trailing");
		
		tfAngleOfSlope = new JTextField();
		fieldsPanel.add(tfAngleOfSlope, "flowx,cell 1 3,growx");

		
		JLabel labelm3 = new JLabel("m");
		fieldsPanel.add(labelm3, "cell 1 3");
		
		
		JLabel labelRunwayStripWidth = new JLabel("Runway Strip Width");
		fieldsPanel.add(labelRunwayStripWidth, "cell 0 4,alignx trailing");
		
		tfRunwayStripWidth = new JTextField();
		fieldsPanel.add(tfRunwayStripWidth, "flowx,cell 1 4,growx");
	
		
		JLabel labelm4 = new JLabel("m");
		fieldsPanel.add(labelm4, "cell 1 4");
		
		
		JLabel lblClearAndGradedWidth = new JLabel("Clear and Graded Width");
		fieldsPanel.add(lblClearAndGradedWidth, "cell 0 5,alignx trailing");
		
		tfClearAndGradedWidth = new JTextField();
		fieldsPanel.add(tfClearAndGradedWidth, "flowx,cell 1 5,growx");

		
		JLabel labelm5 = new JLabel("m");
		fieldsPanel.add(labelm5, "cell 1 5");
		
		
		JLabel lblNewLabel = new JLabel("Obstacle Width");
		fieldsPanel.add(lblNewLabel, "cell 0 7,alignx trailing");
		
		tfObstacleWidth = new JTextField();
		fieldsPanel.add(tfObstacleWidth, "flowx,cell 1 7,growx,aligny top");
		

		JLabel labelm6 = new JLabel("m");
		fieldsPanel.add(labelm6, "cell 1 7");
		
		
		JLabel lblObstacleLength = new JLabel("Obstacle Length");
		fieldsPanel.add(lblObstacleLength, "cell 0 8,alignx trailing");
		
		tfObstacleLength = new JTextField();
		fieldsPanel.add(tfObstacleLength, "flowx,cell 1 8,growx");
		

		JLabel labelm7 = new JLabel("m");
		fieldsPanel.add(labelm7, "cell 1 8");
		
		
		JPanel buttonsPanel = new JPanel();
		contentPane.add(buttonsPanel, "cell 0 1,grow");
		buttonsPanel.setLayout(new MigLayout("", "[][][grow][][]", "[grow][]"));
		
		JButton btnDefault = new JButton("Default");
		buttonsPanel.add(btnDefault, "cell 0 1");
		btnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				physicalRunway.resetAngleOfSlope();
				physicalRunway.resetBlastAllowance();
				physicalRunway.resetClearedAndGradedWidth();
				physicalRunway.resetRESA();
				physicalRunway.resetRunwayStripWidth();
				physicalRunway.resetStopway();
				
				if(physicalRunway.getObstacle() != null) physicalRunway.getObstacle().resetSize();
				
				notifyAirportObservers();
				setFormValues();
			}
		});
		
		
		JButton btnApply = new JButton("Apply");
		buttonsPanel.add(btnApply, "cell 3 1");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				physicalRunway.setAngleOfSlope(Double.parseDouble(tfAngleOfSlope.getText()));
				physicalRunway.setBlastAllowance(Double.parseDouble(tfBlastAllowance.getText()));
				physicalRunway.setClearedAndGradedWidth(Double.parseDouble(tfClearAndGradedWidth.getText()));
				physicalRunway.setRESA(Double.parseDouble(tfRESA.getText()));
				physicalRunway.setRunwayStripWidth(Double.parseDouble(tfRunwayStripWidth.getText()));
				physicalRunway.setStopway(Double.parseDouble(tfStopway.getText()));
				
				if(physicalRunway.getObstacle() != null){ 
					physicalRunway.getObstacle().setWidth(Double.parseDouble(tfObstacleWidth.getText()));
					physicalRunway.getObstacle().setLength(Double.parseDouble(tfObstacleLength.getText()));
				}
				
				notifyAirportObservers();
			}
		});

		
		JButton btnClose = new JButton("Close");
		buttonsPanel.add(btnClose, "cell 4 1");		
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setFormValues();
		setVisible(true);
	}
	
	void notifyAirportObservers(){
		for(AirportObserver ao: airportObservers){
			ao.updateAirport(airport);
		}
	}
	
	void setFormValues(){
		tfRESA.setText(String.valueOf(physicalRunway.getRESA()));
		tfStopway.setText(String.valueOf(physicalRunway.getStopway()));
		tfAngleOfSlope.setText(String.valueOf(physicalRunway.getAngleOfSlope()));
		tfBlastAllowance.setText(String.valueOf(physicalRunway.getBlastAllowance()));
		tfRunwayStripWidth.setText(String.valueOf(physicalRunway.getRunwayStripWidth()));
		tfClearAndGradedWidth.setText(String.valueOf(physicalRunway.getClearedAndGradedWidth()));
		
		Obstacle obstacle = physicalRunway.getObstacle();
		
		if(obstacle != null){
				tfObstacleWidth.setText(String.valueOf(obstacle.getWidth()));
				tfObstacleLength.setText(String.valueOf(obstacle.getLength()));
		}
		else {
			tfObstacleWidth.setText("");
			tfObstacleLength.setText("");
		}
			
	}
	
}