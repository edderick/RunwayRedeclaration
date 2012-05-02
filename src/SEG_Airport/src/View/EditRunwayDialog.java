package View;

import java.util.ArrayList;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Model.Airport;
import Model.AirportObserver;
import Model.PhysicalRunway;
import Model.Runway;

import net.miginfocom.swing.MigLayout;



public class EditRunwayDialog extends JDialog implements AirportObserver{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfLeftASDA;
	private JTextField tfLeftTORA;
	private JTextField tfLeftTODA;
	private JTextField tfLeftLDA;
	private JTextField tfLeftName;
	private JTextField tfRightASDA;
	private JTextField tfRightTORA;
	private JTextField tfRightTODA;
	private JTextField tfRightLDA;
	private JTextField tfRightName;
	@SuppressWarnings("unused")
	private Airport airport;
	@SuppressWarnings("unused")
	private JList physicalRunwayJList;
	private JTextField tfLeftDisplacementThreshold;
	private JTextField tfRightDisplacedThreshold;

	private List<AirportObserver> airportObservers;
	
	//I don't really see any benifit to passing this JList in over passing in a physicalRunway...
	
	public EditRunwayDialog(Airport airport, JList physicalRunwayJList, boolean newRunway, List<AirportObserver> airportObservers) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.airport = airport;
		this.physicalRunwayJList = physicalRunwayJList;
		
		this.airportObservers = airportObservers;
		
		setResizable(false);
		setTitle("Edit Runway");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 490, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel leftFeildsPanel = new JPanel();
		leftFeildsPanel.setBounds(12, 50, 212, 140);
		contentPane.add(leftFeildsPanel);
		leftFeildsPanel.setLayout(new MigLayout("", "[37px][37px,grow]", "[15px][][][][]"));
		
		JLabel lblLeftASDA = new JLabel("ASDA");
		lblLeftASDA.setToolTipText("Accelerate Stop Distance Available");
		leftFeildsPanel.add(lblLeftASDA, "cell 0 0,alignx trailing,aligny top");
		
		tfLeftASDA = new JTextField();
		leftFeildsPanel.add(tfLeftASDA, "cell 1 0,growx");
		tfLeftASDA.setColumns(10);
		
		JLabel lblLeftTORA = new JLabel("TORA");
		lblLeftTORA.setToolTipText("Take-Off Run Available ");
		leftFeildsPanel.add(lblLeftTORA, "cell 0 1,alignx trailing,aligny top");
		
		tfLeftTORA = new JTextField();
		lblLeftTORA.setLabelFor(tfLeftTORA);
		leftFeildsPanel.add(tfLeftTORA, "flowx,cell 1 1,growx");
		tfLeftTORA.setColumns(10);
		
		JLabel lblLeftTODA = new JLabel("TODA");
		lblLeftTODA.setToolTipText("Take-Off Distance Available");
		leftFeildsPanel.add(lblLeftTODA, "cell 0 2,alignx trailing");
		
		tfLeftTODA = new JTextField();
		lblLeftTODA.setLabelFor(tfLeftTODA);
		leftFeildsPanel.add(tfLeftTODA, "flowx,cell 1 2,growx");
		tfLeftTODA.setColumns(10);
		
		JLabel lbLeftLDA = new JLabel("LDA");
		lbLeftLDA.setToolTipText("Landing Distance Available");
		leftFeildsPanel.add(lbLeftLDA, "cell 0 3,alignx trailing");
		
		tfLeftLDA = new JTextField();
		lbLeftLDA.setLabelFor(tfLeftLDA);
		leftFeildsPanel.add(tfLeftLDA, "flowx,cell 1 3,growx");
		tfLeftLDA.setColumns(10);
		
		JLabel lblm1 = new JLabel("m");
		leftFeildsPanel.add(lblm1, "cell 1 0");
		
		JLabel lblm2 = new JLabel("m");
		leftFeildsPanel.add(lblm2, "cell 1 1");
		
		JLabel lblm4 = new JLabel("m");
		leftFeildsPanel.add(lblm4, "cell 1 2");
		
		JLabel lblm3 = new JLabel("m");
		leftFeildsPanel.add(lblm3, "cell 1 3");
		
		JLabel lbLeftDisplacementThreshold = new JLabel("DT");
		lbLeftDisplacementThreshold.setToolTipText("Displacement Threshold");
		leftFeildsPanel.add(lbLeftDisplacementThreshold, "cell 0 4,alignx trailing");
		
		tfLeftDisplacementThreshold = new JTextField();
		tfLeftDisplacementThreshold.setColumns(10);
		leftFeildsPanel.add(tfLeftDisplacementThreshold, "flowx,cell 1 4,growx");
		
		JLabel lblm5 = new JLabel("m");
		leftFeildsPanel.add(lblm5, "cell 1 4");
		
		JPanel leftNamePanel = new JPanel();
		leftNamePanel.setBounds(12, 12, 212, 34);
		contentPane.add(leftNamePanel);
		leftNamePanel.setLayout(new MigLayout("", "[68.00,grow][129.00,grow]", "[24px]"));
		
		JLabel lblLeftRunway = new JLabel("Runway");
		leftNamePanel.add(lblLeftRunway, "cell 0 0,alignx center,aligny center");
		
		tfLeftName = new JTextField();	
		leftNamePanel.add(tfLeftName, "flowx,cell 1 0,growx");
		tfLeftName.setColumns(10);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(12, 209, 448, 45);
		contentPane.add(buttonsPanel);
		
		
		JPanel rightFeildsPanel = new JPanel();
		rightFeildsPanel.setBounds(246, 50, 212, 140);
		contentPane.add(rightFeildsPanel);
		rightFeildsPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
		
		JLabel lblRightASDA = new JLabel("ASDA");
		lblRightASDA.setToolTipText("Accelerate Stop Distance Available");
		rightFeildsPanel.add(lblRightASDA, "cell 0 0,alignx trailing");
		
		tfRightASDA = new JTextField();
		tfRightASDA.setColumns(10);
		rightFeildsPanel.add(tfRightASDA, "flowx,cell 1 0,growx");
		
		JLabel label_m6 = new JLabel("m");
		rightFeildsPanel.add(label_m6, "cell 1 0");
		
		JLabel lblRightTORA = new JLabel("TORA");
		lblRightTORA.setToolTipText("Take-Off Run Available ");
		rightFeildsPanel.add(lblRightTORA, "cell 0 1,alignx trailing");
		
		tfRightTORA = new JTextField();
		tfRightTORA.setColumns(10);
		rightFeildsPanel.add(tfRightTORA, "flowx,cell 1 1,growx");
		
		JLabel lblRightTODA = new JLabel("TODA");
		lblRightTODA.setToolTipText("Take-Off Distance Available");
		rightFeildsPanel.add(lblRightTODA, "cell 0 2,alignx trailing");
		
		tfRightTODA = new JTextField();
		tfRightTODA.setColumns(10);
		rightFeildsPanel.add(tfRightTODA, "flowx,cell 1 2,growx");
		
		JLabel lblRightLDA = new JLabel("LDA");
		lblRightLDA.setToolTipText("Landing Distance Available");
		rightFeildsPanel.add(lblRightLDA, "cell 0 3,alignx trailing");
		
		tfRightLDA = new JTextField();
		tfRightLDA.setColumns(10);
		rightFeildsPanel.add(tfRightLDA, "flowx,cell 1 3,growx");
		
		JLabel labelm7 = new JLabel("m");
		rightFeildsPanel.add(labelm7, "cell 1 1");
		
		JLabel labelm8 = new JLabel("m");
		rightFeildsPanel.add(labelm8, "cell 1 2");
		
		JLabel labelm9 = new JLabel("m");
		rightFeildsPanel.add(labelm9, "cell 1 3");
		
		JLabel lblRightDisplacedThreshold = new JLabel("DT");
		lblRightDisplacedThreshold.setToolTipText("Displacement Threshold");
		rightFeildsPanel.add(lblRightDisplacedThreshold, "cell 0 4,alignx trailing");
		
		tfRightDisplacedThreshold = new JTextField();
		tfRightDisplacedThreshold.setColumns(10);
		rightFeildsPanel.add(tfRightDisplacedThreshold, "flowx,cell 1 4,growx");
		
		JLabel labelm10 = new JLabel("m");
		rightFeildsPanel.add(labelm10, "cell 1 4");
		
		JPanel rightNamePanel = new JPanel();
		rightNamePanel.setBounds(246, 12, 212, 34);
		contentPane.add(rightNamePanel);
		rightNamePanel.setLayout(new MigLayout("", "[74.00][grow]", "[]"));
		
		JLabel lblRightRunway = new JLabel("Runway");
		rightNamePanel.add(lblRightRunway, "cell 0 0,alignx center");
		
		tfRightName = new JTextField();
		tfRightName.setColumns(10);
		rightNamePanel.add(tfRightName, "flowx,cell 1 0,growx");
		
		JButton btnApply = new JButton("Apply");
		btnApply.setBounds(266, 11, 80, 23);
		btnApply.addActionListener(new ERDokListener(airport, tfLeftASDA, tfLeftTORA, tfLeftTODA, tfLeftLDA, tfLeftDisplacementThreshold, tfRightASDA, tfRightTORA, tfRightTODA, tfRightLDA, tfRightDisplacedThreshold, tfRightName, tfLeftName, physicalRunwayJList, this, newRunway, airportObservers));
		
		JPanel panel_Spacer2 = new JPanel();
		rightNamePanel.add(panel_Spacer2, "cell 1 0");
		
		JPanel panel_Spacer = new JPanel();
		leftNamePanel.add(panel_Spacer, "cell 1 0");
		buttonsPanel.setLayout(null);
		buttonsPanel.add(btnApply);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(356, 11, 80, 23);
		buttonsPanel.add(btnCancel);
		
		if(airport.getPhysicalRunways().size() > 0 & !newRunway){
			int index = physicalRunwayJList.getSelectedIndex();
			if(index == -1) index = airport.getPhysicalRunways().indexOf(airport.getCurrentPhysicalRunway());
			
			tfLeftName.setText(airport.getPhysicalRunways().get(index).getRunway(0).getName());
			tfLeftASDA.setText(Double.toString(airport.getPhysicalRunways().get(index).getRunway(0).getASDA(0)));
			tfLeftTORA.setText(Double.toString(airport.getPhysicalRunways().get(index).getRunway(0).getTORA(0)));
			tfLeftTODA.setText(Double.toString(airport.getPhysicalRunways().get(index).getRunway(0).getTODA(0)));
			tfLeftLDA.setText(Double.toString(airport.getPhysicalRunways().get(index).getRunway(0).getLDA(0)));
			tfLeftDisplacementThreshold.setText(Double.toString(airport.getPhysicalRunways().get(index).getRunway(0).getDisplacedThreshold(0)));
			
			tfRightName.setText(airport.getPhysicalRunways().get(index).getRunway(1).getName());
			tfRightASDA.setText(Double.toString(airport.getPhysicalRunways().get(index).getRunway(1).getASDA(0)));
			tfRightTORA.setText(Double.toString(airport.getPhysicalRunways().get(index).getRunway(1).getTORA(0)));
			tfRightTODA.setText(Double.toString(airport.getPhysicalRunways().get(index).getRunway(1).getTODA(0)));
			tfRightLDA.setText(Double.toString(airport.getPhysicalRunways().get(index).getRunway(1).getLDA(0)));
			tfRightDisplacedThreshold.setText(Double.toString(airport.getPhysicalRunways().get(index).getRunway(1).getDisplacedThreshold(0)));
		}		
		
		setVisible(true);

	}

	@Override
	public void updateAirport(Airport airport) {
		this.airport = airport;
		
	}
}

class ERDokListener implements ActionListener{
	Airport airport; 
	JTextField LASDA; JTextField LTORA; JTextField LTODA; JTextField LLDA; JTextField LDT;
	JTextField RASDA; JTextField RTORA; JTextField RTODA; JTextField RLDA; JTextField RDT;
	JTextField RNAME; JTextField LNAME; 
	JList physicalRunwayJList;
	JDialog jd;
	boolean newRunway;
	List<AirportObserver> airportObserver;
	
	public void actionPerformed(ActionEvent e) {
		
		if(airport.getPhysicalRunways().size() > 0 & !newRunway){ // get the physical runway and change the values
			int index = physicalRunwayJList.getSelectedIndex();
			if(index == -1) index = airport.getPhysicalRunways().indexOf(airport.getCurrentPhysicalRunway());
			// set the values to what's in the JTextFields
			airport.getPhysicalRunways().get(index).getRunway(0).setName(LNAME.getText());
			airport.getPhysicalRunways().get(index).getRunway(0).setASDA(0, doubleParser.parse(LASDA.getText()));
			airport.getPhysicalRunways().get(index).getRunway(0).setTORA(0, doubleParser.parse(LTORA.getText()));
			airport.getPhysicalRunways().get(index).getRunway(0).setTODA(0, doubleParser.parse(LTODA.getText()));
			airport.getPhysicalRunways().get(index).getRunway(0).setLDA(0, doubleParser.parse(LLDA.getText()));
			airport.getPhysicalRunways().get(index).getRunway(0).setDisplacedThreshold(0, doubleParser.parse(LDT.getText()));
			
			airport.getPhysicalRunways().get(index).getRunway(1).setName(RNAME.getText());
			airport.getPhysicalRunways().get(index).getRunway(1).setASDA(0, doubleParser.parse(RASDA.getText()));
			airport.getPhysicalRunways().get(index).getRunway(1).setTORA(0, doubleParser.parse(RTORA.getText()));
			airport.getPhysicalRunways().get(index).getRunway(1).setTODA(0, doubleParser.parse(RTODA.getText()));
			airport.getPhysicalRunways().get(index).getRunway(1).setLDA(0, doubleParser.parse(RLDA.getText()));
			airport.getPhysicalRunways().get(index).getRunway(1).setDisplacedThreshold(0, doubleParser.parse(RDT.getText()));
	
			airport.getPhysicalRunways().get(index).calculateParameters();
			
			airport.getPhysicalRunways().get(index).setId(LNAME.getText() + "/" + RNAME.getText());
			
		} else { // add a new physical runway and assign the values
			// THIS LOOKS TRUELY INFANTILE!!!
			// YOU LOOK TRUELY INFANTILE!!! >.<
			airport.addPhysicalRunway(
					new PhysicalRunway(LNAME.getText() + "/" +  RNAME.getText(), 
						new Runway(LNAME.getText(), doubleParser.parse(LTORA.getText()), doubleParser.parse(LASDA.getText()), doubleParser.parse(LTODA.getText()), doubleParser.parse(LLDA.getText()), doubleParser.parse(LDT.getText())),	
						new Runway(RNAME.getText(), doubleParser.parse(RTORA.getText()), doubleParser.parse(RASDA.getText()), doubleParser.parse(RTODA.getText()), doubleParser.parse(RLDA.getText()), doubleParser.parse(RDT.getText())))); 
			if(airport.getPhysicalRunways().size() == 1){
				airport.setCurrentPhysicalRunway(airport.getPhysicalRunways().get(0));
				airport.setCurrentRunway(airport.getCurrentPhysicalRunway().getRunway(0));
			}
		}
		
		ArrayList<String> physicalRunwayNames = new ArrayList<String>();
		for(PhysicalRunway p : airport.getPhysicalRunways()){
			physicalRunwayNames.add(p.getId());
		}
		DefaultListModel pr = new DefaultListModel();
		for(int i = 0; i < physicalRunwayNames.size(); i++){
			pr.addElement(physicalRunwayNames.get(i));
		}
		physicalRunwayJList.setModel(pr);
		physicalRunwayJList.setSelectedIndex(0);
		jd.dispose();
		
		
		
		notifyAirportObservers();
		
	}

	
	
	public ERDokListener(Airport airport, JTextField lASDA, JTextField lTORA,
			JTextField lTODA, JTextField lLDA, JTextField lDT,
			JTextField rASDA, JTextField rTORA, JTextField rTODA,
			JTextField rLDA, JTextField rDT, JTextField rNAME,
			JTextField lNAME, JList physicalRunwayJList,
			JDialog jd, boolean newRunway, List<AirportObserver> airportObserver) {
		super();
		this.airport = airport;
		LASDA = lASDA;
		LTORA = lTORA;
		LTODA = lTODA;
		LLDA = lLDA;
		LDT = lDT;
		RASDA = rASDA;
		RTORA = rTORA;
		RTODA = rTODA;
		RLDA = rLDA;
		RDT = rDT;
		RNAME = rNAME;
		LNAME = lNAME;
		this.physicalRunwayJList = physicalRunwayJList;
		this.jd = jd;
		this.newRunway = newRunway;
		this.airportObserver = airportObserver;
	}



	void notifyAirportObservers(){
		for(AirportObserver ao: airportObserver){
			ao.updateAirport(airport);
		}
	}
	
}

class doubleParser{
	static double parse(String s){
		double d = 0;
		try{
			d = Double.parseDouble(s);
		} catch (Exception e) {System.out.println(e);}
		return d;
	}
}
