package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;


public class EditObstacleDialog extends JDialog {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtEdward;

	/**
	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EdObstacleFrame frame = new EdObstacleFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public EditObstacleDialog() {
		setResizable(false);
		setTitle("Edit Obstacle");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 241, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[100px:n,grow]", "[15px][24px,grow][][6.00][]"));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 0 1,grow");
		
		JLabel lblName = new JLabel("Name");
		panel_1.add(lblName);
		
		txtEdward = new JTextField();
		txtEdward.setPreferredSize(new Dimension(10, 20));
		txtEdward.setText("Edward");
		panel_1.add(txtEdward);
		txtEdward.setColumns(15);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 2,grow");
		panel.setLayout(new MigLayout("", "[37px][37px,grow]", "[15px][][][]"));
		
		JLabel lblAsda = new JLabel("Type");
		panel.add(lblAsda, "cell 0 0,alignx trailing,aligny top");
		
		textField = new JTextField();
		panel.add(textField, "flowx,cell 1 0,growx");
		textField.setColumns(10);
		
		JLabel lblToda = new JLabel("Height");
		panel.add(lblToda, "cell 0 1,alignx trailing,aligny top");
		
		textField_1 = new JTextField();
		lblToda.setLabelFor(textField_1);
		panel.add(textField_1, "flowx,cell 1 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblToda_1 = new JLabel("Distance");
		panel.add(lblToda_1, "cell 0 2,alignx trailing");
		
		textField_2 = new JTextField();
		lblToda_1.setLabelFor(textField_2);
		panel.add(textField_2, "flowx,cell 1 2,growx");
		textField_2.setColumns(10);
		
		JLabel lblLda = new JLabel("Angle");
		panel.add(lblLda, "cell 0 3,alignx trailing");
		
		textField_3 = new JTextField();
		lblLda.setLabelFor(textField_3);
		panel.add(textField_3, "flowx,cell 1 3,growx");
		textField_3.setColumns(10);
		
		JLabel lblM = new JLabel("m");
		panel.add(lblM, "cell 1 0");
		
		JLabel lblNewLabel_1 = new JLabel("m");
		panel.add(lblNewLabel_1, "cell 1 1");
		
		JLabel lblNewLabel_2 = new JLabel("m");
		panel.add(lblNewLabel_2, "cell 1 2");
		
		JLabel lblM_1 = new JLabel("m");
		panel.add(lblM_1, "cell 1 3");
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, "cell 0 4,grow");
		panel_2.setLayout(new MigLayout("", "[grow][][]", "[grow][]"));
		
		JButton btnNewButton = new JButton("Apply");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		panel_2.add(btnNewButton, "cell 1 0");
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel_2.add(btnNewButton_1, "cell 2 0");
		setVisible(true);
	}

}
