import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
	private JTable OriginalParametersTable;
	private JTable RedeclaredParametersTable;
	private JTable ObstacleDetailsTable;

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
		
		try {
		    UIManager.setLookAndFeel(
		        UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException ex) {
		  System.out.println("Unable to load native look and feel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 524);
		
		JMenuBar menuBar = new Menubar();
		setJMenuBar(menuBar);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane mainSplitPane = new JSplitPane();
		mainSplitPane.setResizeWeight(0.25);
		contentPane.add(mainSplitPane, BorderLayout.CENTER);
		
		JPanel leftPanel = new JPanel();
		mainSplitPane.setLeftComponent(leftPanel);
		leftPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JPanel leftTopPanel = new JPanel();
		leftTopPanel.setBorder(new TitledBorder(null, "Original Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		leftPanel.add(leftTopPanel, "flowy,cell 0 0,grow");
		leftTopPanel.setLayout(new BorderLayout(0, 0));
		
		OriginalParametersTable = new JTable();
		OriginalParametersTable.setModel(new DefaultTableModel(
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
		leftTopPanel.add(OriginalParametersTable);
		
		JPanel leftMiddlePanel = new JPanel();
		leftMiddlePanel.setBorder(new TitledBorder(null, "Redeclared Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		leftPanel.add(leftMiddlePanel, "cell 0 0,grow");
		leftMiddlePanel.setLayout(new BorderLayout(0, 0));
		
		RedeclaredParametersTable = new JTable();
		RedeclaredParametersTable.setModel(new DefaultTableModel(
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
		leftMiddlePanel.add(RedeclaredParametersTable, BorderLayout.CENTER);
		
		JPanel letBottomPanel = new JPanel();
		letBottomPanel.setBorder(new TitledBorder(null, "Obstacle Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		leftPanel.add(letBottomPanel, "flowy,cell 0 0,grow");
		letBottomPanel.setLayout(new BorderLayout(0, 0));
		
		ObstacleDetailsTable = new JTable();
		ObstacleDetailsTable.setModel(new DefaultTableModel(
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
		letBottomPanel.add(ObstacleDetailsTable);
		
		JSplitPane rightSplitPane = new JSplitPane();
		rightSplitPane.setResizeWeight(0.5);
		rightSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		mainSplitPane.setRightComponent(rightSplitPane);
		
		TopView topView = new TopView();
		topView.setRunwayDimensions(100, 20, "08L", "27R");
		topView.setValues(80, 5, 40, 0, 73, 2, 15, 50, true, 76, 3, 9, 4);
		rightSplitPane.setLeftComponent(topView);
		
		SideView sideView = new SideView();
		sideView.setRunwayDimensions(80);
		sideView.setValues(80, 5, 40, 0, 73, 2, 15, 50, true, 76, 3, 9);
		rightSplitPane.setRightComponent(sideView);
		
		
		
		
	
		
		
	}

}
