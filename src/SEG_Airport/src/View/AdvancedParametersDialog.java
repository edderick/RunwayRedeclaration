package View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Model.Obstacle;
import Model.PhysicalRunway;
import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class AdvancedParametersDialog extends JDialog {

	private JPanel contentPane;
	@SuppressWarnings("unused")
	private Obstacle obstacle, obstacle_backup;
	private boolean newObstacle;
	private JTextField TF_RESA;
	private JTextField TF_STOPWAY;
	private JTextField TF_BLASTALLOWANCE;
	private JTextField TF_AOS;

	public AdvancedParametersDialog(PhysicalRunway physicalRunway) {
		this.obstacle = obstacle;
		this.obstacle_backup = obstacle;
		
		if(newObstacle) obstacle = new Obstacle("", 0);
		
		setResizable(false);
		setTitle("Advanced Parameters");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 309, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[100px:n,grow]", "[15px,grow][][][24px,grow][][6.00][]"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][]"));
		
		JLabel lblL = new JLabel("RESA");
		panel.add(lblL, "cell 0 1,alignx trailing");
		
		TF_RESA = new JTextField();
		panel.add(TF_RESA, "flowx,cell 1 1,growx");
		TF_RESA.setColumns(10);
		
		JLabel label = new JLabel("m");
		panel.add(label, "cell 1 1");
		
		JLabel lblStopway = new JLabel("Stopway");
		panel.add(lblStopway, "cell 0 2,alignx trailing");
		
		TF_STOPWAY = new JTextField();
		TF_STOPWAY.setColumns(10);
		panel.add(TF_STOPWAY, "flowx,cell 1 2,growx");
		
		JLabel label_2 = new JLabel("m");
		panel.add(label_2, "cell 1 2");
		
		JLabel lblBlastAllowance = new JLabel("Blast allowance");
		panel.add(lblBlastAllowance, "cell 0 3,alignx trailing");
		
		TF_BLASTALLOWANCE = new JTextField();
		TF_BLASTALLOWANCE.setColumns(10);
		panel.add(TF_BLASTALLOWANCE, "flowx,cell 1 3,grow");
		
		JLabel label_4 = new JLabel("m");
		panel.add(label_4, "cell 1 3");
		
		JLabel lblAngleOfSlope = new JLabel("Angle of slope");
		panel.add(lblAngleOfSlope, "cell 0 4,alignx trailing");
		
		TF_AOS = new JTextField();
		TF_AOS.setColumns(10);
		panel.add(TF_AOS, "flowx,cell 1 4,growx");
		
		JLabel label_6 = new JLabel("m");
		panel.add(label_6, "cell 1 4");
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, "cell 0 1,grow");
		panel_2.setLayout(new MigLayout("", "[][][grow][][]", "[grow][]"));
		
		JButton btnNewButton = new JButton("Apply");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		
		JButton btnDefault = new JButton("Default");
		panel_2.add(btnDefault, "cell 0 0");
		
		JLabel label_1 = new JLabel("                 ");
		panel_2.add(label_1, "cell 1 0");
		panel_2.add(btnNewButton, "cell 3 0");
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel_2.add(btnNewButton_1, "cell 4 0");		
		
		setVisible(true);
	}

}
