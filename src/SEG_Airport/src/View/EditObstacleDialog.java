package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Model.*;
import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class EditObstacleDialog extends JDialog {

	private JPanel contentPane;
	private JTextField TF_HEIGHT;
	private JTextField TF_WIDTH;
	private JTextField TF_LENGTH;
	private JTextField TF_NAME;
	
	public EditObstacleDialog(Obstacle obstacle, Obstacle old) {
		
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
		
		JLabel lblToda = new JLabel("Height");
		panel.add(lblToda, "cell 0 1,alignx trailing,aligny top");
		
		TF_HEIGHT = new JTextField();
		lblToda.setLabelFor(TF_HEIGHT);
		panel.add(TF_HEIGHT, "flowx,cell 1 1,growx");
		TF_HEIGHT.setColumns(10);
		
		JLabel lblToda_1 = new JLabel("Dist. From Threshold");
		panel.add(lblToda_1, "cell 0 2,alignx trailing");
		
		TF_WIDTH = new JTextField();
		lblToda_1.setLabelFor(TF_WIDTH);
		panel.add(TF_WIDTH, "flowx,cell 1 2,growx");
		TF_WIDTH.setColumns(10);
		
		JLabel lblLda = new JLabel("Dist. From Centre Line");
		panel.add(lblLda, "cell 0 3,alignx trailing");
		
		TF_LENGTH = new JTextField();
		lblLda.setLabelFor(TF_LENGTH);
		panel.add(TF_LENGTH, "flowx,cell 1 3,growx");
		TF_LENGTH.setColumns(10);
		
		JLabel lblM = new JLabel("    ");
		panel.add(lblM, "flowx,cell 1 0");
		
		JLabel lblNewLabel_1 = new JLabel("m");
		panel.add(lblNewLabel_1, "cell 1 1");
		
		JLabel lblNewLabel_2 = new JLabel("m");
		panel.add(lblNewLabel_2, "cell 1 2");
		
		JLabel label = new JLabel("m");
		panel.add(label, "cell 1 3");
		
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
		TF_LENGTH.setText(Double.toString(obstacle.getLength()));
		TF_WIDTH.setText(Double.toString(obstacle.getWidth()));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Runway 1", "Runway 2"}));
		panel.add(comboBox, "cell 1 0,alignx right");
		TF_NAME.setText(obstacle.getName());

		
		setVisible(true);
	}
}

class EODapplyListener implements ActionListener{
	Obstacle obstacle;
	EditObstacleDialog eod;
	JTextField height, length, width, name;
	JComboBox size;
	
	public void actionPerformed(ActionEvent e) {
		obstacle.setHeight(doubleParser.parse(height.getText()));
		obstacle.setLength(doubleParser.parse(length.getText()));
		obstacle.setWidth(doubleParser.parse(width.getText()));
		obstacle.setName(name.getText());

		eod.setVisible(false);
	}

	public EODapplyListener(Obstacle obstacle, EditObstacleDialog eod,
			JTextField height, JTextField length, JTextField width,
			JTextField name, JComboBox size) {
		super();
		this.obstacle = obstacle;
		this.eod = eod;
		this.height = height;
		this.length = length;
		this.width = width;
		this.name = name;
		this.size = size;
	}
}

class EODcancelListener implements ActionListener{
	Obstacle obstacle, revertTo;
	EditObstacleDialog eod;
	
	public void actionPerformed(ActionEvent e){
//		obstacle = revertTo;
		eod.setVisible(false);
	}

	public EODcancelListener(Obstacle obstacle, Obstacle revertTo, EditObstacleDialog eod) {
		super();
		this.obstacle = obstacle;
		this.revertTo = revertTo;
		this.eod = eod;
	}
	
	
}
