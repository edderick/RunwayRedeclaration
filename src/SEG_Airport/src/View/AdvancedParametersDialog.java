package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

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

	public AdvancedParametersDialog(PhysicalRunway physicalRunway) {
		setResizable(false);
		setTitle("Advanced Parameters");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 310, 326);
		
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[100px:n,grow]", "[15px,grow][]"));
		
		JPanel fieldsPanel = new JPanel();
		contentPane.add(fieldsPanel, "cell 0 0,grow");
		fieldsPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][grow]"));
		
		
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
		tfRunwayStripWidth.setColumns(10);
		
		JLabel labelm4 = new JLabel("m");
		fieldsPanel.add(labelm4, "cell 1 4");
		
		JLabel lblClearAndGradedWidth = new JLabel("Clear and Graded Width");
		fieldsPanel.add(lblClearAndGradedWidth, "cell 0 5,alignx trailing");
		
		tfClearAndGradedWidth = new JTextField();
		fieldsPanel.add(tfClearAndGradedWidth, "flowx,cell 1 5,growx");
		tfClearAndGradedWidth.setColumns(10);
		
		JLabel labelm5 = new JLabel("m");
		fieldsPanel.add(labelm5, "cell 1 5");
		
		JLabel lblNewLabel = new JLabel("Obstacle Width");
		fieldsPanel.add(lblNewLabel, "cell 0 7,alignx trailing");
		
		tfObstacleWidth = new JTextField();
		fieldsPanel.add(tfObstacleWidth, "flowx,cell 1 7,growx,aligny top");
		tfObstacleWidth.setColumns(10);
		
		JLabel lblObstacleLength = new JLabel("Obstacle Length");
		fieldsPanel.add(lblObstacleLength, "cell 0 8,alignx trailing");
		
		tfObstacleLength = new JTextField();
		fieldsPanel.add(tfObstacleLength, "flowx,cell 1 8,growx");
		tfObstacleLength.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("m");
		fieldsPanel.add(lblNewLabel_1, "cell 1 7");
		
		JLabel lblM = new JLabel("m");
		fieldsPanel.add(lblM, "cell 1 8");
		
		
		JPanel buttonsPanel = new JPanel();
		contentPane.add(buttonsPanel, "cell 0 1,grow");
		buttonsPanel.setLayout(new MigLayout("", "[][][grow][][]", "[grow][]"));
		
		JButton btnDefault = new JButton("Default");
		buttonsPanel.add(btnDefault, "cell 0 1");
		
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO: Implement this
				setVisible(false);
			}
		});
		buttonsPanel.add(btnApply, "cell 3 1");
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonsPanel.add(btnClose, "cell 4 1");		
		
		setVisible(true);
	}
}