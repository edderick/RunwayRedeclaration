package View;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

import Model.Airport;
import Model.AirportObserver;
import Model.Obstacle;
import Model.Runway;


@SuppressWarnings("serial")
public class SideView extends JPanel implements AirportObserver, ViewPanel{

	Color toraColor = new Color(184, 134, 11);
	Color dtColor = new Color(184, 11, 134);
	Color todaColor = new Color(255, 0, 0);
	Color asdaColor = new Color(0, 0, 255);
	Color ldaColor = new Color(255, 0, 255);
	Color resaColor = new Color(84, 84, 84);
	
	
	
	boolean visible = true;

	//this value determines the amount of pixels the tag is from the end of the runway
	final int tagBorder = 10;

	//this value determines how much of the width of the panel the runway takes up.
	double ratio = 0.75;

	//this value determines how much of the width of the runway the runwayTag takes up.
	final double fontRatio = 0.5;

	final double tagRotate = 1;

	double meterToPixel;
	int TORA;
	int TODA;
	int ASDA;
	int LDA;
	int DT;
	int RESA;
	int ANGLE;
	int stopway;
	int TORAStart;
	int TODAStart;
	int ASDAStart;
	int LDAStart;
	int DTStart;
	int runwayLength;
	int declaredWidth;
	int obstacleLength;
	int obstacleHeight;
	Airport airport;
	Runway runway;
	Obstacle obstacle;
	String threshold;
	boolean obstacleLeft;
	String leftTag;

	//relative to panel
	int xRunway;
	int yRunway;

	//relative to runway
	int xObstacle;

	int xOffset = 0;
	int yOffset = 0;

	int mousex;
	int mousey;

	public SideView(Airport airport){
		super();
		setSize(300,200);
		this.setBackground(new Color(154, 205, 50));

		updateAirport(airport);

		setLayout(new MigLayout("", "[grow]", "[grow][]"));

		createSlider();
		createDraggingListeners();
		
		setDragCursor(this);
		
		setValues();
		setVisible(true);

	}

	public void setZoom(double ratio){
		this.ratio = ratio;
	}
	
	public int meterToPixel(int x){
		return (int) (meterToPixel*x);
	}
	
	public int pixelToMeter(int x){
		return (int) (x/meterToPixel);
	}	
	
	public void setVisible(boolean b){
		visible=b;
	}

	public void paint (Graphics g){
		super.paint(g);
		if(visible){
			g.translate(xOffset, yOffset);
			setValues();
			Graphics2D g2d = (Graphics2D)g;
			runwayCreation(g2d);

			obstacleCreation(g2d);
			declaredRunwaysCreation(g2d);
		}

	}

	public void createSlider(){
		final JSlider zoomSlider = new JSlider();
		zoomSlider.setMinorTickSpacing(1);
		zoomSlider.setBackground(new Color(154, 205, 50));
		zoomSlider.setValue(950);
		zoomSlider.setMinimum(950);
		zoomSlider.setMaximum(6000);
		zoomSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider slider = ((JSlider)e.getSource());
				setZoom((double)slider.getValue() / (double)1000);
				repaint();
				updateUI();
			}
		});
		add(zoomSlider, "cell 0 1,alignx right,aligny bottom");
		
		zoomSlider.setCursor(Cursor.getDefaultCursor());
		
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				zoomSlider.setValue(zoomSlider.getValue() - (arg0.getWheelRotation() * 100));
			}
		});
		
		zoomSlider.setPaintLabels(false);
		
	}

	public void createDraggingListeners(){
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				mousex = arg0.getX();
				mousey = arg0.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int deltaMouseX = arg0.getX() - mousex;
				int deltaMouseY = arg0.getY() - mousey;

				mousex = arg0.getX();
				mousey = arg0.getY();

				setOffset(xOffset + deltaMouseX, yOffset + deltaMouseY);
				repaint();
				updateUI();
			}
		});
	}

	public void runwayCreation(Graphics2D g2d){
		if(airport!=null&& runway!=null){


			int pWidth = this.getWidth();
			int pHeight = this.getHeight();
	
	
			int width = (int) (ratio*pWidth);
			meterToPixel=width/(double)runwayLength;
	
			//calculates the x and y values to position the runway on the view
			xRunway = (int) (((1.0-ratio)/2) * pWidth);
			yRunway = pHeight/2;
			g2d.setColor(new Color(135,206,250));
			g2d.fillRect(0, 0, pWidth, yRunway);
			g2d.setColor(Color.GRAY);
			g2d.fillRect(xRunway,  yRunway, meterToPixel(runwayLength), 5);

		}
	}

	public void obstacleCreation(Graphics2D g2d){
	if(obstacle!=null){
		g2d.setColor(Color.RED);
		g2d.fillRect(meterToPixel(xObstacle)+xRunway, yRunway-meterToPixel(obstacleHeight), meterToPixel(obstacleLength), meterToPixel(obstacleHeight));
	}
	}

	public void declaredRunwaysCreation(Graphics2D g2d){
		if(airport!=null){
			g2d.setColor(toraColor);
			g2d.fillRect(xRunway+TORAStart, yRunway+5, meterToPixel(TORA), 3);
			g2d.setColor(todaColor);
			g2d.fillRect(xRunway+TODAStart, yRunway +10, meterToPixel(TODA), 3);
			g2d.setColor(asdaColor);
			g2d.fillRect(xRunway+ASDAStart,  yRunway +15, meterToPixel(ASDA), 3);
			g2d.setColor(ldaColor);
			g2d.fillRect(xRunway+LDAStart,  yRunway +20, meterToPixel(LDA), 3);
			g2d.setColor(dtColor);
			g2d.fillRect(xRunway+DTStart,  yRunway +25, meterToPixel(DT), 3);
		}
	}

	public void setValues(){
		if(airport!=null && runway!=null && airport.getCurrentPhysicalRunway() != null){
		this.runwayLength = (int) runway.getTORA(Runway.DEFAULT);
	
		this.TORA = (int) runway.getTORA(Runway.REDECLARED);
		this.TODA = (int) runway.getTODA(Runway.REDECLARED);
		this.ASDA = (int) runway.getASDA(Runway.REDECLARED);
		this.LDA = (int) runway.getLDA(Runway.REDECLARED);
		this.DT = (int) runway.getDisplacedThreshold(runway.DEFAULT);

		this.RESA = (int) airport.getCurrentPhysicalRunway().getRESA();
		this.stopway = (int) airport.getCurrentPhysicalRunway().getStopway();
		this.leftTag= airport.getCurrentPhysicalRunway().getRunway(0).getName();
				
		int pWidth = this.getWidth();
		int pHeight = this.getHeight();

		int length = (int) (ratio*pWidth);
		meterToPixel=length/(double)runwayLength;

		

		//calculates the x and y values to position the runway on the view
		xRunway = (int) (((1.0-ratio)/2) * pWidth);
		yRunway = (pHeight - 5)/2;
		
		
		
		if(obstacle!=null){
			int distance = (int) airport.getCurrentPhysicalRunway().getDistanceAwayFromThreshold();
			this.threshold=airport.getCurrentPhysicalRunway().closeTo().getName(); 
			if(threshold.equals(airport.getCurrentPhysicalRunway().getRunway(0).getName())){this.leftTag=airport.getCurrentPhysicalRunway().getRunway(1).getName();}else{this.leftTag=airport.getCurrentPhysicalRunway().getRunway(0).getName();}
			this.xObstacle=runwayLength-distance;
			this.obstacleLength =(int) obstacle.getLength();
			this.obstacleHeight = (int) obstacle.getHeight();
			this.ANGLE=(int) (obstacle.getHeight()*airport.getCurrentPhysicalRunway().getAngleOfSlope());
			
			
		}
		int leftDT;
		int rightDT;
		if(leftTag.equals(airport.getCurrentPhysicalRunway().getRunway(1).getName())){
			leftDT =(int) airport.getCurrentPhysicalRunway().getRunway(1).getDisplacedThreshold(0);
			rightDT =(int) airport.getCurrentPhysicalRunway().getRunway(0).getDisplacedThreshold(0);
		}else{
			leftDT =(int) airport.getCurrentPhysicalRunway().getRunway(0).getDisplacedThreshold(0);
			rightDT =(int) airport.getCurrentPhysicalRunway().getRunway(1).getDisplacedThreshold(0);
		}
		
		if(leftDT>0){
			DTStart=0;
			LDAStart=DTStart+meterToPixel(DT);
		}else{
			DTStart=0;
			LDAStart=0;
		}
	
		this.TORAStart=0;
		this.TODAStart=(int) (TORAStart+meterToPixel(TORA-TODA));
		this.ASDAStart=(int) (TORAStart+meterToPixel(TORA-ASDA));
		
		}
	}

	public void updateAirport(Airport airport) {
		this.airport=airport;
		if(airport!=null &&
				airport.getCurrentPhysicalRunway() != null)
		{
			runway=airport.getCurrentRunway();
			obstacle=airport.getCurrentPhysicalRunway().getObstacle();
			setOffset(0, 0);
			setValues();
			repaint();
		}
		
		

	}

	public void setOffset(int x, int y){
		this.xOffset=x;
		this.yOffset=y;
	}

	public void setDragCursor(final JComponent component){
		Cursor openHandCursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("data/opengrab.png"), new Point(16,16), "closed");
		component.setCursor(openHandCursor);	

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				Cursor closedHandCursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("data/closedgrab.png"), new Point(16,16), "closed");
				component.setCursor(closedHandCursor);	

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				Cursor openHandCursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("data/opengrab.png"), new Point(16,16), "closed");
				component.setCursor(openHandCursor);	
			}
		});
	}
	
}



