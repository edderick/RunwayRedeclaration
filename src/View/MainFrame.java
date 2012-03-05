import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 524);
		
		JMenuBar menuBar = new Menubar();
		setJMenuBar(menuBar);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.25);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Original Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_1, "flowy,cell 0 0,grow");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"TORA", "3884m"},
				{"TODA", "3962m"},
				{"ASDA", "3884m"},
				{"LDA", "3884m"},
			},
			new String[] {
				"New column", "New column"
			}
		));
		panel_1.add(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Redeclared Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_2, "cell 0 0,grow");
		panel_2.setLayout(new BorderLayout(0, 0));
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"TORA(R)", "3884m"},
				{"TODA(R)", "3962m"},
				{"ASDA(R)", "3884m"},
				{"LDA(R)", "3884m"},
			},
			new String[] {
				"New column", "New column"
			}
		));
		panel_2.add(table_1, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Obstacle Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_3, "flowy,cell 0 0,grow");
		panel_3.setLayout(new BorderLayout(0, 0));
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{"Height", "48m"},
				{"Distance from Threshold", "73m"},
				{"Engine Blast Allowance", "10m"},
				{"Angle of Slope", "1:50"},
			},
			new String[] {
				"New column", "New column"
			}
		));
		panel_3.add(table_2);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.5);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);
		
		TopView tv = new TopView();
		tv.setRunwayDimensions(100, 20, "08L", "27R");
		tv.setValues(80, 5, 40, 0, 73, 2, 15, 50, true, 76, 3, 9, 4);
		splitPane_1.setLeftComponent(tv);
		
		TopView topView = new TopView();
		topView.setRunwayDimensions(100, 20, "08L", "27R");
		topView.setValues(80, 5, 40, 0, 73, 2, 15, 50, true, 76, 3, 9, 4);
		splitPane_1.setRightComponent(topView);
		
		
		
		
	
		
		
	}

}
