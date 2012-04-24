package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

import Controller.*;
import Model.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTable OriginalParametersTable;
	private JTable RedeclaredParametersTable;
	private JTable ObstacleDetailsTable;
	private JLabel lblCurrentAirport;
	private JLabel lblCurrentRunway;
	private final ButtonGroup topPanelButtonGroup = new ButtonGroup();
	private final ButtonGroup bottomPanelButtonGroup = new ButtonGroup();
	
	private Airport airport;
	private Obstacle obstacle;

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

	public MainFrame() {
		airport = new Airport("");
		obstacle = new Obstacle("", "", 0, 0, 0);
		setTitle("SEG Group 9 - Runway Redeclaration System");
		
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
		setBounds(100, 100, 798, 524);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('f');
		menuBar.add(mnFile);
		
		JMenu mnAirport = new JMenu("New");
		mnAirport.setMnemonic('n');
		mnFile.add(mnAirport);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Airport");
		mntmNewMenuItem.setMnemonic('a');
		mntmNewMenuItem.addActionListener(new NewAirportListener());
		mnAirport.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Obstacle");
		mntmNewMenuItem_1.setMnemonic('o');
		mntmNewMenuItem_1.addActionListener(new NewObstacleListener());
		mnAirport.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu = new JMenu("Open");
		mnNewMenu.setMnemonic('o');
		mnFile.add(mnNewMenu);
		
		JMenuItem mntmAirport = new JMenuItem("Airport");
		mntmAirport.setMnemonic('a');
		mntmAirport.addActionListener(new OpenAirportListener());
		mnNewMenu.add(mntmAirport);
		
		JMenuItem mntmObstacle = new JMenuItem("Obstacle");
		mntmObstacle.setMnemonic('o');
		mntmObstacle.addActionListener(new OpenObstacleListener());
		mnNewMenu.add(mntmObstacle);
		
		//TODO: Create a way to list recent opening and persistently store them
		
		JMenu mnOpenRecent = new JMenu("Open Recent");
		mnOpenRecent.setMnemonic('r');
		mnFile.add(mnOpenRecent);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Airport");
		mntmNewMenuItem_2.setMnemonic('a');
		mnOpenRecent.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Obstacle");
		mntmNewMenuItem_3.setMnemonic('o');
		mnOpenRecent.add(mntmNewMenuItem_3);
		
		JMenu mnSave = new JMenu("Save");
		mnSave.setMnemonic('s');
		mnFile.add(mnSave);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Airport");
		mntmNewMenuItem_4.setMnemonic('a');
		mntmNewMenuItem_4.addActionListener(new SaveAirportListener());
		mnSave.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Obstacle");
		mntmNewMenuItem_5.setMnemonic('o');
		mntmNewMenuItem_5.addActionListener(new SaveObstacleListener());
		mnSave.add(mntmNewMenuItem_5);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setMnemonic('x');
		mntmExit.addActionListener(new ExitListener());
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setMnemonic('e');
		menuBar.add(mnEdit);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Runway");
		mntmNewMenuItem_6.setMnemonic('r');
		mntmNewMenuItem_6.addActionListener(new EditRunwayListener());
		mnEdit.add(mntmNewMenuItem_6);
		
		JMenuItem mntmAirport_1 = new JMenuItem("Airport");
		mntmAirport_1.setMnemonic('a');
		mntmAirport_1.addActionListener(new  EditAirportListener(this));
		mnEdit.add(mntmAirport_1);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Obstacle");
		mntmNewMenuItem_7.setMnemonic('o');
		mntmNewMenuItem_7.addActionListener(new EditObstacleListener());
		mnEdit.add(mntmNewMenuItem_7);
		
		JSeparator separator = new JSeparator();
		mnEdit.add(separator);
		
		JMenu mnSelectRunway = new JMenu("Select Runway");
		mnSelectRunway.setMnemonic('s');
		mnEdit.add(mnSelectRunway);
		
		//TODO: Replace these place holders with some generated stuff
		
		JRadioButtonMenuItem rdbtnmntmlr = new JRadioButtonMenuItem("09L/27R");
		topPanelButtonGroup.add(rdbtnmntmlr);
		rdbtnmntmlr.setSelected(true);
		mnSelectRunway.add(rdbtnmntmlr);
		
		JRadioButtonMenuItem rdbtnmntmrl = new JRadioButtonMenuItem("09R/27L");
		topPanelButtonGroup.add(rdbtnmntmrl);
		mnSelectRunway.add(rdbtnmntmrl);
		
		//End place holders
		
		JMenuItem mntmPositionObstacle = new JMenuItem("Position Obstacle");
		mntmPositionObstacle.setMnemonic('p');
		mntmPositionObstacle.addActionListener(new ObstaclePositionListener(obstacle));
		mnEdit.add(mntmPositionObstacle);
		
		JMenu mnView = new JMenu("View");
		mnView.setMnemonic('v');
		menuBar.add(mnView);
		
		//TODO: Fill in the appropriate Listeners here ======================================================================================
		JMenu mnTopPanel = new JMenu("Top Panel");
		mnTopPanel.setMnemonic('t');
		mnView.add(mnTopPanel);
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("Top View");
		topPanelButtonGroup.add(rdbtnmntmNewRadioItem);
		mnTopPanel.add(rdbtnmntmNewRadioItem);
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem_1 = new JRadioButtonMenuItem("Side View");
		topPanelButtonGroup.add(rdbtnmntmNewRadioItem_1);
		mnTopPanel.add(rdbtnmntmNewRadioItem_1);
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem_2 = new JRadioButtonMenuItem("Calculations");
		topPanelButtonGroup.add(rdbtnmntmNewRadioItem_2);
		mnTopPanel.add(rdbtnmntmNewRadioItem_2);
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem_3 = new JRadioButtonMenuItem("None");
		topPanelButtonGroup.add(rdbtnmntmNewRadioItem_3);
		mnTopPanel.add(rdbtnmntmNewRadioItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("Bottom Panel");
		mnNewMenu_1.setMnemonic('b');
		mnView.add(mnNewMenu_1);
		
		JRadioButtonMenuItem rdbtnmntmTopView = new JRadioButtonMenuItem("Top View");
		bottomPanelButtonGroup.add(rdbtnmntmTopView);
		mnNewMenu_1.add(rdbtnmntmTopView);
		
		JRadioButtonMenuItem rdbtnmntmSideView = new JRadioButtonMenuItem("Side View");
		bottomPanelButtonGroup.add(rdbtnmntmSideView);
		mnNewMenu_1.add(rdbtnmntmSideView);
		
		JRadioButtonMenuItem rdbtnmntmCalculations = new JRadioButtonMenuItem("Calculations");
		bottomPanelButtonGroup.add(rdbtnmntmCalculations);
		mnNewMenu_1.add(rdbtnmntmCalculations);
		
		JRadioButtonMenuItem rdbtnmntmNone = new JRadioButtonMenuItem("None");
		bottomPanelButtonGroup.add(rdbtnmntmNone);
		mnNewMenu_1.add(rdbtnmntmNone);
		
		//TODO: All the way down to here ======================================================================================
		
		JMenu mnEmail = new JMenu("Email");
		mnEmail.setMnemonic('m');
		menuBar.add(mnEmail);
		
		JMenuItem mntmSendEmail = new JMenuItem("Send email");
		mntmSendEmail.setMnemonic('s');
		mntmSendEmail.addActionListener(new SendEmailListener());
		mnEmail.add(mntmSendEmail);
		
		JMenuItem mntmAddressBook = new JMenuItem("Address book");
		mntmAddressBook.setMnemonic('a');
		mntmAddressBook.addActionListener(new ShowAddressBookListener());
		mnEmail.add(mntmAddressBook);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setMnemonic('h');
		menuBar.add(mnHelp);
		
		JMenuItem mntmShowHelp = new JMenuItem("Show help");
		mntmShowHelp.setMnemonic('s');
		mnHelp.add(mntmShowHelp);
		mntmShowHelp.addActionListener(new ShowHelpListener());
		
		JMenuItem mntmAbout = new JMenuItem("About ");
		mntmAbout.setMnemonic('a');
		mnHelp.add(mntmAbout);
		mntmAbout.addActionListener(new ShowAboutListener());
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[270px][grow]", "[grow]"));
		
		JPanel leftPanel = new JPanel();
		contentPane.add(leftPanel, "cell 0 0,alignx center,aligny top");
		leftPanel.setLayout(new MigLayout("", "[275px,grow]", "[grow][][][][grow]"));
		
		JPanel panel = new JPanel();
		leftPanel.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[77px]", "[14px][]"));
		
		lblCurrentAirport = new JLabel("Current Airport:");
		lblCurrentAirport.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblCurrentAirport, "cell 0 0,alignx left,aligny top");
		
		lblCurrentRunway = new JLabel("Current Runway:");
		panel.add(lblCurrentRunway, "cell 0 1");
		
		JPanel leftTopPanel = new JPanel();
		leftTopPanel.setBorder(new TitledBorder(null, "Original Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		leftPanel.add(leftTopPanel, "cell 0 1,grow");
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
		leftPanel.add(leftMiddlePanel, "cell 0 2,grow");
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
		leftPanel.add(letBottomPanel, "cell 0 3,grow");
		letBottomPanel.setLayout(new BorderLayout(0, 0));
		
		ObstacleDetailsTable = new JTable();
		ObstacleDetailsTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"Type", "747"},
				{"Height", "48m"},
				{"Distance from Threshold", "73m"},
				{"Engine Blast Allowance", "10m"},
				{"Angle of Slope", "1:50"},
			},
			new String[] {
				"Property", "Value"
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

	public void setAirport(Airport airport){
		this.airport = airport;
	}
	
	public Airport getAirport(){
		return airport;
	}
}
