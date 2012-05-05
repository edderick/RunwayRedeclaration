package View;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

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

	boolean visible = true;

	//this value determines the amount of pixels the tag is from the end of the runway
	final int tagBorder = 10;

	//this value determines how much of the width of the panel the runway takes up.
	double ratio = 0.75;

	//this value determines how much of the width of the runway the runwayTag takes up.
	final double fontRatio = 0.5;

	final double tagRotate = 1;

	double r;
	int TORA;
	int TODA;
	int ASDA;
	int LDA;
	int TORAStart;
	int TODAStart;
	int ASDAStart;
	int LDAStart;
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
		
		setValues();
		setVisible(true);

	}

	public void setZoom(double ratio){
		this.ratio = ratio;
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
		
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				zoomSlider.setValue(zoomSlider.getValue() - (arg0.getWheelRotation() * 100));
			}
		});
		
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
		//		if(airport!=null){


		int pWidth = this.getWidth();
		int pHeight = this.getHeight();


		int width = (int) (ratio*pWidth);
		r=width/(double)runwayLength;

		//calculates the x and y values to position the runway on the view
		xRunway = (int) (((1.0-ratio)/2) * pWidth);
		yRunway = pHeight/2;
		g2d.setColor(new Color(135,206,250));
		g2d.fillRect(0, 0, pWidth, yRunway);
		g2d.setColor(Color.GRAY);
		g2d.fillRect(xRunway,  yRunway, (int) (runwayLength*r), 2);

		//		}
	}

	public void obstacleCreation(Graphics2D g2d){
		//		if(obstacle!=null){
		g2d.setColor(Color.RED);
		g2d.fillRect((int) ((r*xObstacle)+xRunway), (int) (yRunway-(r*obstacleHeight)), (int)(r*obstacleLength), (int)(r*obstacleHeight));
		//		}
	}

	public void declaredRunwaysCreation(Graphics2D g2d){
		//		if(airport!=null){
		Color toraColor = new Color(255, 165, 0);
		Color todaColor = new Color(255, 0, 0);
		Color asdaColor = new Color(0, 0, 255);
		Color ldaColor = new Color(255, 0, 255);
		g2d.setColor(toraColor);
		g2d.fillRect(xRunway+TORAStart, yRunway+5, (int) (TORA*r), 3);
		g2d.setColor(todaColor);
		g2d.fillRect(xRunway+TODAStart, yRunway +10, (int) (TODA*r), 3);
		g2d.setColor(asdaColor);
		g2d.fillRect(xRunway+ASDAStart,  yRunway +15, (int) (ASDA*r), 3);
		g2d.setColor(ldaColor);
		g2d.fillRect(xRunway+LDAStart,  yRunway +20, (int) (LDA*r), 3);
		//		}
		//		g2d.setColor(Color.BLACK);
		//		g2d.drawString("TORA", (int) (xRunway+((TORA*r)/2)), (yRunway+g2d.getFontMetrics().getHeight()));
		//		g2d.drawString("TODA", (int) (xRunway+((TODA*r)/2)), (int) ((yRunway -((runwayHeight*r)/2)) +g2d.getFontMetrics().getHeight()));
		//		g2d.drawString("ASDA", (int) (xRunway+((ASDA*r)/2)), (int) ((yRunway +((runwayHeight*r)/2) +g2d.getFontMetrics().getHeight())));
		//		g2d.drawString("LDA", (int) (xRunway+((LDA*r)/2)), (int) ((yRunway + (2* ((runwayHeight*r)/2)) +g2d.getFontMetrics().getHeight())));
	}


	public void setValues(){
		this.runwayLength = 2000;
		this.TORA = 100;
		this.TODA = 100;
		this.ASDA = 100;
		this.LDA = 100;
		int distance = 400;
		this.threshold="08L";
		this.xObstacle=runwayLength-distance;
		this.obstacleLength = 100;
		this.obstacleHeight = 100;
		this.LDAStart=0;
		this.TORAStart=0;
		this.TODAStart=TORAStart+TORA;
		this.ASDAStart=TORAStart+TORA;

		//		if(airport!=null  && airport.getCurrentRunway() != null &&
		//				   airport.getCurrentPhysicalRunway() != null && 
		//				   airport.getCurrentPhysicalRunway().getObstacle() != null){
		//		this.runwayLength = (int) runway.getTORA(runway.DEFAULT);
		//		this.TORA = (int) runway.getTORA(runway.REDECLARED);
		//		this.TODA = (int) runway.getTODA(runway.REDECLARED);
		//		this.ASDA = (int) runway.getASDA(runway.REDECLARED);
		//		this.LDA = (int) runway.getLDA(runway.REDECLARED);
		//		this.leftTag=airport.getCurrentPhysicalRunway().getRunway(0).getName();
		//		if(obstacle!=null){
		//			int distance = (int) airport.getCurrentPhysicalRunway().getDistanceAwayFromThreshold();
		//			this.threshold=airport.getCurrentPhysicalRunway().closeTo().getName(); 
		//			obstacleLeft=threshold.equals(leftTag);
		//			if(obstacleLeft){this.xObstacle = distance;}else{this.xObstacle=runwayLength-distance;}
		//			this.obstacleLength =(int) obstacle.getLength();
		//			this.obstacleHeight = (int) obstacle.getHeight();
		//		}else{
		//			obstacleLeft=false;
		//		}
		//		if(obstacleLeft){this.LDAStart = runwayLength;}else{this.LDAStart=0;}
		//		if(obstacleLeft){this.TORAStart = runwayLength;}else{this.TORAStart=0;}
		//		if(obstacleLeft){this.TODAStart = runwayLength;}else{this.TODAStart=0;}
		//		if(obstacleLeft){this.ASDAStart = runwayLength;}else{this.ASDAStart=0;}
		//
		//		}

	}



	public void updateAirport(Airport airport) {
		this.airport=airport;
		if(airport!=null && airport.getCurrentRunway() != null &&
				airport.getCurrentPhysicalRunway() != null && 
				airport.getCurrentPhysicalRunway().getObstacle() != null)
		{
			runway=airport.getCurrentRunway();
			obstacle=airport.getCurrentPhysicalRunway().getObstacle();
		}

	}

	public void setOffset(int x, int y){
		this.xOffset=x;
		this.yOffset=y;
	}

}



