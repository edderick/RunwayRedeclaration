import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EditObstacleFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditObstacleFrame frame = new EditObstacleFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditObstacleFrame() {
		setTitle("Edit Obstacle");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 241, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[100px:n,grow]", "[15px][24px,grow][grow]"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 1,grow");
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
		contentPane.add(panel_2, "cell 0 2,grow");
		panel_2.setLayout(new MigLayout("", "[grow][][]", "[grow][]"));
		
		JButton btnNewButton = new JButton("Apply");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_2.add(btnNewButton, "cell 1 1");
		
		JButton btnNewButton_1 = new JButton("Cancel");
		panel_2.add(btnNewButton_1, "cell 2 1");
		setVisible(true);
	}

}
