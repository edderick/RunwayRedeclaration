import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;


public class EditAirportFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditAirportFrame frame = new EditAirportFrame();
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
	public EditAirportFrame() {
		setTitle("Edit Airport");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 222, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][]", "[][][grow][][260px]"));
		
		JLabel lblNewLabel = new JLabel("Name");
		contentPane.add(lblNewLabel, "flowx,cell 0 1 2 1");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 0 1");
		textField.setColumns(10);
		
		JList list = new JList();
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"09L", "27R", "09R", "27L"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		contentPane.add(list, "cell 0 3 2 1,grow");
		
		btnNewButton = new JButton("Apply");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.add(btnNewButton, "flowx,cell 1 4");
		
		btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.add(btnNewButton_1, "cell 1 4");
	}

}
