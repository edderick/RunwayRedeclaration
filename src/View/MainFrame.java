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
import java.awt.FlowLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;


public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTable OriginalParametersTable;
	private JTable RedeclaredParametersTable;
	private JTable ObstacleDetailsTable;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

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
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnAirport = new JMenu("New");
		mnAirport.setMnemonic('n');
		mnFile.add(mnAirport);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Airport");
		mnAirport.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Obstacle");
		mnAirport.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu = new JMenu("Open");
		mnFile.add(mnNewMenu);
		
		JMenuItem mntmAirport = new JMenuItem("Airport");
		mnNewMenu.add(mntmAirport);
		
		JMenuItem mntmObstacle = new JMenuItem("Obstacle");
		mnNewMenu.add(mntmObstacle);
		
		JMenu mnOpenRecent = new JMenu("Open Recent");
		mnFile.add(mnOpenRecent);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Airport");
		mnOpenRecent.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Obstacle");
		mnOpenRecent.add(mntmNewMenuItem_3);
		
		JMenu mnSave = new JMenu("Save");
		mnFile.add(mnSave);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Airport");
		mnSave.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Obstacle");
		mnSave.add(mntmNewMenuItem_5);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Runway");
		mnEdit.add(mntmNewMenuItem_6);
		
		JMenuItem mntmAirport_1 = new JMenuItem("Airport");
		mnEdit.add(mntmAirport_1);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Obstacle");
		mnEdit.add(mntmNewMenuItem_7);
		
		JSeparator separator = new JSeparator();
		mnEdit.add(separator);
		
		JMenuItem mntmSelectRunway = new JMenuItem("Select Runway");
		mnEdit.add(mntmSelectRunway);
		
		JMenuItem mntmPositionObstacle = new JMenuItem("Position Obstacle");
		mnEdit.add(mntmPositionObstacle);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenu mnTopPanel = new JMenu("Top Panel");
		mnView.add(mnTopPanel);
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("Top View");
		buttonGroup.add(rdbtnmntmNewRadioItem);
		mnTopPanel.add(rdbtnmntmNewRadioItem);
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem_1 = new JRadioButtonMenuItem("Side View");
		buttonGroup.add(rdbtnmntmNewRadioItem_1);
		mnTopPanel.add(rdbtnmntmNewRadioItem_1);
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem_2 = new JRadioButtonMenuItem("Calculations");
		buttonGroup.add(rdbtnmntmNewRadioItem_2);
		mnTopPanel.add(rdbtnmntmNewRadioItem_2);
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem_3 = new JRadioButtonMenuItem("None");
		buttonGroup.add(rdbtnmntmNewRadioItem_3);
		mnTopPanel.add(rdbtnmntmNewRadioItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("Bottom Panel");
		mnView.add(mnNewMenu_1);
		
		JRadioButtonMenuItem rdbtnmntmTopView = new JRadioButtonMenuItem("Top View");
		buttonGroup_1.add(rdbtnmntmTopView);
		mnNewMenu_1.add(rdbtnmntmTopView);
		
		JRadioButtonMenuItem rdbtnmntmSideView = new JRadioButtonMenuItem("Side View");
		buttonGroup_1.add(rdbtnmntmSideView);
		mnNewMenu_1.add(rdbtnmntmSideView);
		
		JRadioButtonMenuItem rdbtnmntmCalculations = new JRadioButtonMenuItem("Calculations");
		buttonGroup_1.add(rdbtnmntmCalculations);
		mnNewMenu_1.add(rdbtnmntmCalculations);
		
		JRadioButtonMenuItem rdbtnmntmNone = new JRadioButtonMenuItem("None");
		buttonGroup_1.add(rdbtnmntmNone);
		mnNewMenu_1.add(rdbtnmntmNone);
		
		JMenu mnEmail = new JMenu("Email");
		menuBar.add(mnEmail);
		
		JMenuItem mntmSendEmail = new JMenuItem("Send email");
		mnEmail.add(mntmSendEmail);
		
		JMenuItem mntmAddressBook = new JMenuItem("Address book");
		mnEmail.add(mntmAddressBook);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmShowHelp = new JMenuItem("Show help");
		mnHelp.add(mntmShowHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About ");
		mnHelp.add(mntmAbout);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[270px][grow]", "[grow]"));
		
		JPanel leftPanel = new JPanel();
		contentPane.add(leftPanel, "cell 0 0,alignx center,aligny top");
		leftPanel.setLayout(new MigLayout("", "[275px,grow]", "[][][][grow]"));
		
		JPanel leftTopPanel = new JPanel();
		leftTopPanel.setBorder(new TitledBorder(null, "Original Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		leftPanel.add(leftTopPanel, "cell 0 0,grow");
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
		leftTopPanel.add(OriginalParametersTable, BorderLayout.CENTER);
		
		JPanel leftMiddlePanel = new JPanel();
		leftMiddlePanel.setBorder(new TitledBorder(null, "Redeclared Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		leftPanel.add(leftMiddlePanel, "cell 0 1,grow");
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
		leftPanel.add(letBottomPanel, "cell 0 2,grow");
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
		contentPane.add(rightSplitPane, "cell 1 0,grow");
		rightSplitPane.setResizeWeight(0.5);
		rightSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
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
