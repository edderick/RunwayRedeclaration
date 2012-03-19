package view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;


public class EditRunwayDialog extends JDialog {

//	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtl;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField txtr;
	private JList listOfPhysicalRunways;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			EditRunwayDialog dialog = new EditRunwayDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public EditRunwayDialog(JList listOfRunways) {
		this.listOfPhysicalRunways = listOfRunways;
		
		setResizable(false);
		setTitle("Edit Runway");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 501, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 50, 212, 113);
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[37px][37px,grow]", "[15px][][][]"));
		
		JLabel lblAsda = new JLabel("ASDA");
		panel.add(lblAsda, "cell 0 0,alignx trailing,aligny top");
		
		textField = new JTextField();
		panel.add(textField, "flowx,cell 1 0,growx");
		textField.setColumns(10);
		
		JLabel lblToda = new JLabel("TORA");
		panel.add(lblToda, "cell 0 1,alignx trailing,aligny top");
		
		textField_1 = new JTextField();
		lblToda.setLabelFor(textField_1);
		panel.add(textField_1, "flowx,cell 1 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblToda_1 = new JLabel("TODA");
		panel.add(lblToda_1, "cell 0 2,alignx trailing");
		
		textField_2 = new JTextField();
		lblToda_1.setLabelFor(textField_2);
		panel.add(textField_2, "flowx,cell 1 2,growx");
		textField_2.setColumns(10);
		
		JLabel lblLda = new JLabel("LDA");
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 12, 212, 34);
		contentPane.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[68.00,grow][129.00,grow]", "[24px]"));
		
		JLabel lblNewLabel = new JLabel("Runway");
		panel_1.add(lblNewLabel, "cell 0 0,alignx center,aligny center");
		
		txtl = new JTextField();
		txtl.setText("09L");
		panel_1.add(txtl, "cell 1 0");
		txtl.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 174, 463, 45);
		contentPane.add(panel_2);
		
		JButton btnNewButton = new JButton("Apply");
		btnNewButton.setBounds(328, 11, 59, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listOfPhysicalRunways != null){
					final String[] vals = new String[listOfPhysicalRunways.getModel().getSize() + 1];
					for(int i = 0; i < listOfPhysicalRunways.getModel().getSize(); i++){
						vals[i] = (String) listOfPhysicalRunways.getModel().getElementAt(i);
					}
					vals[vals.length-1] = "newRunway";
					
					listOfPhysicalRunways.setModel( new AbstractListModel() {
						String[] values = vals;
						public int getSize() {
							return values.length;
						}
						public Object getElementAt(int index) {
							return values[index];
						}
							
					});
				}
				
				setVisible(false);			
			}			
		});
		panel_2.setLayout(null);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(391, 11, 65, 23);
		panel_2.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(246, 50, 212, 113);
		contentPane.add(panel_3);
		panel_3.setLayout(new MigLayout("", "[][grow]", "[][][][]"));
		
		JLabel label = new JLabel("ASDA");
		panel_3.add(label, "cell 0 0,alignx trailing");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		panel_3.add(textField_4, "flowx,cell 1 0,growx");
		
		JLabel label_1 = new JLabel("m");
		panel_3.add(label_1, "cell 1 0");
		
		JLabel label_2 = new JLabel("TORA");
		panel_3.add(label_2, "cell 0 1,alignx trailing");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		panel_3.add(textField_5, "flowx,cell 1 1,growx");
		
		JLabel label_4 = new JLabel("TODA");
		panel_3.add(label_4, "cell 0 2,alignx trailing");
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		panel_3.add(textField_6, "flowx,cell 1 2,growx");
		
		JLabel label_3 = new JLabel("LDA");
		panel_3.add(label_3, "cell 0 3,alignx trailing");
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		panel_3.add(textField_7, "flowx,cell 1 3,growx");
		
		JLabel label_5 = new JLabel("m");
		panel_3.add(label_5, "cell 1 1");
		
		JLabel label_6 = new JLabel("m");
		panel_3.add(label_6, "cell 1 2");
		
		JLabel label_7 = new JLabel("m");
		panel_3.add(label_7, "cell 1 3");
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(246, 12, 212, 34);
		contentPane.add(panel_4);
		panel_4.setLayout(new MigLayout("", "[74.00][grow]", "[]"));
		
		JLabel label_8 = new JLabel("Runway");
		panel_4.add(label_8, "cell 0 0,alignx center");
		
		txtr = new JTextField();
		txtr.setText("27R");
		txtr.setColumns(10);
		panel_4.add(txtr, "cell 1 0");
		setVisible(true);

	}

}
