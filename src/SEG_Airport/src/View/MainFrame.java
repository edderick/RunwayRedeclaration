package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements AirportObserver{

	private static final int MAX_HISTORY = 10;
	private static final int HISTORY_TO_SHOW = 5;
	
	private JPanel contentPane;
	private JSplitPane rightSplitPane;
	private JTable OriginalParametersTable;
	private JTable RedeclaredParametersTable;
	private JTable ObstacleDetailsTable;
	private JLabel lblAirportName;
	private JComboBox currentRunwayCombo;
	private final ButtonGroup topPanelButtonGroup = new ButtonGroup();
	private final ButtonGroup bottomPanelButtonGroup = new ButtonGroup();

	private Airport airport;
	private AddressBook addressBook;

	private static List<String> recentAirports;
	private static List<String> recentObstacles;

	private List<AirportObserver> airportObservers;
	
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
		
		//TODO: Move these closer to where they are needed and maybe have two sets
		TopView topView = new TopView(airport);
		SideView sideView = new SideView(airport);
		CalculationsView calcView = new CalculationsView(airport);
		
		//TODO: Load addressbook on application start;
		addressBook = new AddressBook();
		
		rightSplitPane = new JSplitPane();
		
		airport = new Airport("");
		airportObservers = new ArrayList<AirportObserver>();
		airportObservers.add(this);

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
		NewAirportListener nal = new NewAirportListener(airport, airportObservers);
		mntmNewMenuItem.addActionListener(nal);
		airportObservers.add(nal);
		mnAirport.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Obstacle");
		mntmNewMenuItem_1.setMnemonic('o');
		NewObstacleListener nol = new NewObstacleListener(airport);
		mntmNewMenuItem_1.addActionListener(nol);
		airportObservers.add(nol);
		mnAirport.add(mntmNewMenuItem_1);

		JMenu mnNewMenu = new JMenu("Open");
		mnNewMenu.setMnemonic('o');
		mnFile.add(mnNewMenu);

		JMenuItem mntmAirport = new JMenuItem("Airport");
		mntmAirport.setMnemonic('a');
		OpenAirportListener oal = new OpenAirportListener(airport, airportObservers);
		mntmAirport.addActionListener(oal);
		airportObservers.add(oal);
		mnNewMenu.add(mntmAirport);

		JMenuItem mntmObstacle = new JMenuItem("Obstacle");
		mntmObstacle.setMnemonic('o');
		OpenObstacleListener ool = new OpenObstacleListener(airport, airportObservers);
		mntmObstacle.addActionListener(ool);
		airportObservers.add(ool);
		mnNewMenu.add(mntmObstacle);

		//TODO: Create a way to list recent opening and persistently store them

		JMenu mnOpenRecent = new JMenu("Open Recent");
		mnOpenRecent.setMnemonic('r');
		mnFile.add(mnOpenRecent);

		//Reads in persistent recent files
		try {
			this.loadRecentFiles(HISTORY_TO_SHOW);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JMenu mnOpenRecentAirports = new JMenu("Airport");
		mnOpenRecentAirports.setMnemonic('a');
		mnOpenRecent.add(mnOpenRecentAirports);
		
		//Populate list
		for(String s : recentAirports){
			JMenuItem mntmRecentAirport = new JMenuItem(s);
			mnOpenRecentAirports.add(mntmRecentAirport);
			OpenRecentAirportListener oral = new OpenRecentAirportListener(s, airport, airportObservers);
			mntmRecentAirport.addActionListener(oral);
			airportObservers.add(oral);
		}
		
		JMenu mnOpenRecentObstacles = new JMenu("Obstacle");
		mnOpenRecentObstacles.setMnemonic('o');
		mnOpenRecent.add(mnOpenRecentObstacles);

		//Populate list
		for(String s : recentObstacles){
			JMenuItem mntmRecentObstacle = new JMenuItem(s);
			mnOpenRecentObstacles.add(mntmRecentObstacle);
			OpenRecentObstacleListener orol = new OpenRecentObstacleListener(s, null, airportObservers);
			mntmRecentObstacle.addActionListener(orol);
			airportObservers.add(orol);
		}
		
		JMenu mnSave = new JMenu("Save");
		mnSave.setMnemonic('s');
		mnFile.add(mnSave);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Airport");
		mntmNewMenuItem_4.setMnemonic('a');
		SaveAirportListener sal = new SaveAirportListener(airport);
		mntmNewMenuItem_4.addActionListener(sal);
		airportObservers.add(sal);
		mnSave.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Obstacle");
		mntmNewMenuItem_5.setMnemonic('o');
		SaveObstacleListener sol = new SaveObstacleListener(airport);
		mntmNewMenuItem_5.addActionListener(sol);
		airportObservers.add(sol);
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
		EditRunwayListener erl = new EditRunwayListener(airport);
		mntmNewMenuItem_6.addActionListener(erl);
		mnEdit.add(mntmNewMenuItem_6);

		JMenuItem mntmAirport_1 = new JMenuItem("Airport");
		mntmAirport_1.setMnemonic('a');
		EditAirportListener eal = new EditAirportListener(airport, airportObservers);
		mntmAirport_1.addActionListener(eal);
		mnEdit.add(mntmAirport_1);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Obstacle");
		mntmNewMenuItem_7.setMnemonic('o');
		EditObstacleListener eol = new EditObstacleListener(airport);
		mntmNewMenuItem_7.addActionListener(eol);
		airportObservers.add(eol);
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
		ObstaclePositionListener opl = new ObstaclePositionListener(airport);
		mntmPositionObstacle.addActionListener(opl);
		mnEdit.add(mntmPositionObstacle);

		JMenu mnView = new JMenu("View");
		mnView.setMnemonic('v');
		menuBar.add(mnView);

		JMenu mnTopPanel = new JMenu("Top Panel");
		mnTopPanel.setMnemonic('t');
		mnView.add(mnTopPanel);
		
		JRadioButtonMenuItem rdbtnmntmTopPanelTopView = new JRadioButtonMenuItem("Top View");
		topPanelButtonGroup.add(rdbtnmntmTopPanelTopView);
		mnTopPanel.add(rdbtnmntmTopPanelTopView);
		rdbtnmntmTopPanelTopView.addActionListener(new SelectViewListener(rightSplitPane, topView, true));

		JRadioButtonMenuItem rdbtnmntmTopPanelSideView = new JRadioButtonMenuItem("Side View");
		topPanelButtonGroup.add(rdbtnmntmTopPanelSideView);
		mnTopPanel.add(rdbtnmntmTopPanelSideView);
		rdbtnmntmTopPanelSideView.addActionListener(new SelectViewListener(rightSplitPane, sideView, true));

		JRadioButtonMenuItem rdbtnmntmTopPanelCalculations = new JRadioButtonMenuItem("Calculations");
		topPanelButtonGroup.add(rdbtnmntmTopPanelCalculations);
		mnTopPanel.add(rdbtnmntmTopPanelCalculations);
		rdbtnmntmTopPanelCalculations.addActionListener(new SelectViewListener(rightSplitPane, calcView, true));

		JRadioButtonMenuItem rdbtnmntmTopPanelNone = new JRadioButtonMenuItem("None");
		topPanelButtonGroup.add(rdbtnmntmTopPanelNone);
		mnTopPanel.add(rdbtnmntmTopPanelNone);
		rdbtnmntmTopPanelNone.addActionListener(new SelectViewListener(rightSplitPane, null, true));
		
		rdbtnmntmTopPanelTopView.setSelected(true);
		
		JMenu mnNewMenu_1 = new JMenu("Bottom Panel");
		mnNewMenu_1.setMnemonic('b');
		mnView.add(mnNewMenu_1);

		JRadioButtonMenuItem rdbtnmntmBottomPanelTopView = new JRadioButtonMenuItem("Top View");
		bottomPanelButtonGroup.add(rdbtnmntmBottomPanelTopView);
		mnNewMenu_1.add(rdbtnmntmBottomPanelTopView);
		rdbtnmntmBottomPanelTopView.addActionListener(new SelectViewListener(rightSplitPane, topView , false));

		JRadioButtonMenuItem rdbtnmntmBottomPanelSideView = new JRadioButtonMenuItem("Side View");
		rdbtnmntmBottomPanelSideView.setSelected(true);
		bottomPanelButtonGroup.add(rdbtnmntmBottomPanelSideView);
		mnNewMenu_1.add(rdbtnmntmBottomPanelSideView);
		rdbtnmntmBottomPanelSideView.addActionListener(new SelectViewListener(rightSplitPane, sideView, false));

		JRadioButtonMenuItem rdbtnmntmBottomPanelCalculations = new JRadioButtonMenuItem("Calculations");
		bottomPanelButtonGroup.add(rdbtnmntmBottomPanelCalculations);
		mnNewMenu_1.add(rdbtnmntmBottomPanelCalculations);
		rdbtnmntmBottomPanelCalculations.addActionListener(new SelectViewListener(rightSplitPane, calcView, false));

		JRadioButtonMenuItem rdbtnmntmBottomPanelNone = new JRadioButtonMenuItem("None");
		bottomPanelButtonGroup.add(rdbtnmntmBottomPanelNone);
		mnNewMenu_1.add(rdbtnmntmBottomPanelNone);
		rdbtnmntmBottomPanelNone.addActionListener(new SelectViewListener(rightSplitPane, null, false));

		JMenu mnEmail = new JMenu("Email");
		mnEmail.setMnemonic('m');
		menuBar.add(mnEmail);

		JMenuItem mntmSendEmail = new JMenuItem("Send email");
		mntmSendEmail.setMnemonic('s');
		mntmSendEmail.addActionListener(new ShowEmailDialogListener(addressBook));
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
		panel.setLayout(new MigLayout("", "[77px][grow]", "[14px][]"));

		JLabel lblCurrentAirport = new JLabel("Current Airport:");
		lblCurrentAirport.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblCurrentAirport, "cell 0 0,alignx left,aligny top");
		
		lblAirportName = new JLabel("None");
		panel.add(lblAirportName, "cell 1 0");

		JLabel lblCurrentRunway = new JLabel("Current Runway:");
		panel.add(lblCurrentRunway, "cell 0 1,alignx trailing");
		
		currentRunwayCombo = new JComboBox();
		panel.add(currentRunwayCombo, "cell 1 1,growx");

		JPanel leftTopPanel = new JPanel();
		leftTopPanel.setBorder(new TitledBorder(null, "Original Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		leftPanel.add(leftTopPanel, "cell 0 1,grow");
		leftTopPanel.setLayout(new BorderLayout(0, 0));

		OriginalParametersTable = new JTable();
		OriginalParametersTable.setEnabled(false);
		OriginalParametersTable.setRowSelectionAllowed(false);
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
		RedeclaredParametersTable.setEnabled(false);
		RedeclaredParametersTable.setRowSelectionAllowed(false);
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
		ObstacleDetailsTable.setEnabled(false);
		ObstacleDetailsTable.setRowSelectionAllowed(false);
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

		contentPane.add(rightSplitPane, "cell 1 0,grow");
		rightSplitPane.setResizeWeight(0.5);
		rightSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

		//TODO: Uncomment this once it's fixed
		//topView.setRunwayDimensions(100, 20, "08L", "27R");
		//topView.setValues();
		rightSplitPane.setLeftComponent(topView);

		//TODO: Uncomment this once it's fixed
//		sideView.setRunwayDimensions(80);
//		sideView.setValues(80, 5, 40, 0, 73, 2, 15, 50, true, 76, 3, 9);
		rightSplitPane.setRightComponent(sideView);

		airportObservers.add(topView);
		airportObservers.add(sideView);
		airportObservers.add(calcView);
		
		try {
			this.loadRecentFiles(5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void loadRecentFiles(int numberToRead) throws IOException{
		recentAirports = new ArrayList<String>();

		FileInputStream fis = new FileInputStream("data/recentAirports");
		InputStreamReader in = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(in);

		for(int i = 0; i < numberToRead; i++){
			String line = br.readLine();
			if (line != null){
				recentAirports.add(line);		
			}
			else break;
		}


		recentObstacles = new ArrayList<String>();

		fis = new FileInputStream("data/recentObstacles");
		in = new InputStreamReader(fis);
		br = new BufferedReader(in);

		for(int i = 0; i < numberToRead; i++){
			String line = br.readLine();
			if (line != null){
				recentObstacles.add(line);
			}
			else break;
		}

	}

	public static void saveRecentFile(Airport airport, String filename) throws IOException{
		if(recentAirports.contains(filename)){
			recentAirports.remove(filename);
			recentAirports.add(0, filename);
		}else{
			recentAirports.add(0, filename);
			if(recentAirports.size() > MAX_HISTORY) recentAirports.remove(MAX_HISTORY + 1);
		}
		
		FileWriter fw = new FileWriter("data/recentAirports");
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (String string: recentAirports){
			bw.write(string + "\n");
		}
		bw.flush();
	}

	public static void saveRecentFile(Obstacle obstacle, String filename) throws IOException{
		if(recentObstacles.contains(filename)){
			recentObstacles.remove(filename);
			recentObstacles.add(0, filename);
		}else{
			recentObstacles.add(0, filename);
			if(recentObstacles.size() > MAX_HISTORY) recentObstacles.remove(MAX_HISTORY + 1);
		}
		
		FileWriter fw = new FileWriter("data/recentObstacles");
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (String string: recentObstacles){
			bw.write(string + "\n");
		}
		bw.flush();
	}

	public Airport getAirport(){
		return airport;
	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;
		lblAirportName.setText(airport.getName());
		currentRunwayCombo.removeAllItems();
		for (PhysicalRunway r : airport.getRunways()){
			currentRunwayCombo.addItem(r.getRunway(0));
			currentRunwayCombo.addItem(r.getRunway(1));
		}
	}
	
	

}
