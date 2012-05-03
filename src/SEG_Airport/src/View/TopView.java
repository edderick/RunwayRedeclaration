package View;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.PaintContext;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Model.Airport;
import Model.AirportObserver;
import Model.Obstacle;
import Model.Runway;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


@SuppressWarnings("serial")
public class TopView extends JPanel implements AirportObserver, ViewPanel{

	boolean visible = false;

	//this value determines the amount of pixels the tag is from the end of the runway
	int tagBorder = 10;

	//this value determines how much of the width of the panel the runway takes up.
	double ratio = 0.95;

	//TODO: Remove redundant fields
	double ratioIncrement = 0.05;

	//this value determines how much of the width of the runway the runwayTag takes up.
	final double fontRatio = 0.5;

	//final removed to prevent warning
	double tagRotate = 1;

	int xOffset = 0;
	int yOffset = 0;

	double r;
	int TORA;
	int TODA;
	int ASDA;
	int LDA;
	int TORAStart;
	int TODAStart;
	int ASDAStart;
	int LDAStart;
	int runwayWidth;
	int runwayHeight;
	int obstacleLength;
	int obstacleWidth;
	boolean obstacleLeft;
	String leftTag;
	String rightTag;
	Airport airport;
	Runway runway;
	Obstacle obstacle;
	String threshold;

	//relative to panel
	int xRunway;
	int yRunway;

	//relative to runway
	int xObstacle;
	int yObstacle;

	int mousex;
	int mousey;

	public TopView(Airport airport){
		super();

		this.setBackground(new Color(154, 205, 50));
		updateAirport(airport);
		setValues();
		
		setLayout(new MigLayout("", "[grow]", "[grow][]"));
		
		createSlider();
		createDraggingListeners();
		setVisible(true);
		
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

	public void setVisible(boolean b){
		visible=b;
	}

	public void createSlider(){
		JSlider zoomSlider = new JSlider();
		zoomSlider.setBackground(new Color(154, 205, 50));
		zoomSlider.setValue(1000);
		zoomSlider.setMinimum(1000);
		zoomSlider.setMaximum(6000);
		zoomSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider slider = ((JSlider)e.getSource());
				setZoom(slider.getValue() / 1000);
				repaint();
				updateUI();
			}
		});
		add(zoomSlider, "cell 0 1,alignx right,aligny bottom");
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
		if(airport!=null && runway!=null){

			int pWidth = this.getWidth();
			int pHeight = this.getHeight();


			int width = (int) (ratio*pWidth);
			r=width/(double)runwayWidth;

			int height = (int) (runwayHeight * r);

			if(2*height>pHeight){
				height = (int) (pHeight/2);
				r=height/(double)runwayHeight;
				width=(int) (runwayWidth*r);
			}



			//calculates the x and y values to position the runway on the view
			xRunway = (int) (((1.0-ratio)/2) *pWidth);
			yRunway = (pHeight - height)/2;

			g2d.setColor(Color.BLUE);
			int nPoints = 12;
			int xPoints[] = new int[nPoints];
			int yPoints[] = new int[nPoints];
			xPoints[0]=(int) (xRunway-(60*r));
			yPoints[0]= (int) (yRunway+(((runwayHeight/2) -75)*r));
			xPoints[1]= xPoints[0];
			yPoints[1]= (int) (yRunway+(((runwayHeight/2) +75)*r));
			xPoints[2]=(int) (xPoints[1]+(210*r));
			yPoints[2]= yPoints[1];
			xPoints[3]=(int) (xRunway+(300*r));
			yPoints[3]= (int) (yRunway+(((runwayHeight/2) +105)*r));
			xPoints[4]=(int) (xRunway+((runwayWidth-300)*r));
			yPoints[4]= yPoints[3];
			xPoints[5]=(int) (xRunway+((runwayWidth-150)*r));
			yPoints[5]= yPoints[1];
			xPoints[6]=(int) (xRunway+((runwayWidth+60)*r));
			yPoints[6]= yPoints[1];
			xPoints[7]=xPoints[6];
			yPoints[7]= yPoints[0];
			xPoints[8]=xPoints[5];
			yPoints[8]= yPoints[0];
			xPoints[9]=xPoints[4];
			yPoints[9]=  (int) (yRunway-((105-(runwayHeight/2))*r));
			xPoints[10]=xPoints[3];
			yPoints[10]= yPoints[9];
			xPoints[11]=xPoints[2];
			yPoints[11]= yPoints[0];
			g2d.setColor(new Color(34, 139, 34));
			g2d.fillRect(xPoints[0], (int) (yRunway - ((150-(runwayHeight/2))*r)), (int) ((runwayWidth+120)*r),(int) (300*r));

			g2d.setColor(new Color(0, 100, 0));

			g2d.fillPolygon(xPoints, yPoints, nPoints);

			g2d.setColor(Color.GRAY);
			g2d.fillRect(xRunway, yRunway, width, height);

			g2d.setColor(Color.WHITE);
			int dashesY = (int) (yRunway+((runwayHeight/2)*r));
			int dashesLength = 35;
			int dashesWidth = 1;
			int gaps = 25;
			for(int i = dashesLength*4; i<runwayWidth-(dashesLength*5); i=i+gaps+dashesLength){
				g2d.drawRect((int) (xRunway+i*r), dashesY, (int) (dashesLength*r), (int) (dashesWidth*r));
			}
			tagBorder=(int) (dashesLength*3*r);

			//27R 08L tags

			for(int i=0; i<1000; i++){
				Font f = new Font("tag", 1, 1000-i);
				g2d.setFont(f);
				if(g2d.getFontMetrics().stringWidth(leftTag)<=(fontRatio*height))break;
			}



			if(tagRotate == 1){
				g2d.rotate(0.5 * Math.PI,  xRunway+tagBorder, yRunway+(height/4));
				g2d.drawString(leftTag, xRunway+ tagBorder, yRunway+(height/4));
				g2d.rotate(-0.5 * Math.PI,  xRunway + tagBorder, yRunway+(height/4));
				g2d.rotate(-0.5 * Math.PI,  xRunway + width - tagBorder, yRunway+(3*(height/4)));
				g2d.drawString(rightTag, xRunway+ width - tagBorder, yRunway+(3*(height/4)));
				g2d.rotate(0.5 * Math.PI,  xRunway + width - tagBorder, yRunway+(3*(height/4)));
			}else{
				int offset = g2d.getFontMetrics().getHeight();
				g2d.rotate(-0.5 * Math.PI,  xRunway+tagBorder+offset, yRunway+(3*(height/4)));
				g2d.drawString(leftTag, xRunway+ tagBorder+offset, yRunway+(3*(height/4)));
				g2d.rotate(0.5 * Math.PI,  xRunway + tagBorder+offset, yRunway+(3*(height/4)));
				g2d.rotate(0.5 * Math.PI,  xRunway + width - tagBorder - offset, yRunway+(height/4));
				g2d.drawString(rightTag, xRunway+ width - tagBorder - offset, yRunway+(height/4));
				g2d.rotate(-0.5 * Math.PI,  xRunway + width - tagBorder - offset, yRunway+(height/4));
			}

			dashesWidth=(runwayHeight/20);
			if (dashesWidth == 0) dashesWidth = 1;
			gaps=dashesWidth;
			int dashesX = (int) (xRunway + ((dashesLength/2)*r));
			dashesLength=dashesLength*2;

			for(int i = gaps; i<runwayHeight-(gaps*2); i=i+gaps+dashesWidth){
				g2d.fillRect(dashesX, (int)(yRunway+i*r), (int) (dashesLength*r), (int) (dashesWidth*r));
			}

			dashesX = (int) (xRunway + (runwayWidth*r)  - ((dashesLength/4)*r) - (dashesLength*r));
			for(int i = gaps; i<runwayHeight-(gaps*2); i=i+gaps+dashesWidth){
				g2d.fillRect(dashesX, (int)(yRunway+i*r), (int) (dashesLength*r), (int) (dashesWidth*r));
			}

		}



	}

	public void obstacleCreation(Graphics2D g2d){
		if(obstacle!=null){
			g2d.setColor(Color.RED);
			g2d.fillRect((int) ((r*xObstacle)+xRunway), (int)((r*yObstacle)+yRunway), (int)(r*obstacleLength), (int)(r*obstacleWidth));
		}
	}

	public void declaredRunwaysCreation(Graphics2D g2d){
		if(airport!=null && runway!=null){
			//		Color toraColor = new Color(0, 255, 0, 70);
			//		Color todaColor = new Color(255, 0, 0, 70);
			//		Color asdaColor = new Color(0, 0, 255, 70);
			//		Color ldaColor = new Color(255, 0, 255, 70);
			Color toraColor = new Color(255, 165, 0);
			Color todaColor = new Color(255, 0, 0);
			Color asdaColor = new Color(0, 0, 255);
			Color ldaColor = new Color(255, 0, 255);

			//			g2d.setColor(toraColor);
			//			g2d.fillRect(xRunway+TORAStart, (int) (yRunway+offset), (int) (TORA*r), (int) ((3*(runwayHeight*r)/8)-(r*runwayHeight *0.05)));
			//			g2d.setColor(todaColor);
			//			g2d.fillRect(xRunway+TODAStart, (int) (yRunway -((3*(runwayHeight*r)/8))+offset), (int) (TODA*r), (int) ((3*(runwayHeight*r)/8)-(r*runwayHeight *0.05)));
			//			g2d.setColor(asdaColor);
			//			g2d.fillRect(xRunway+ASDAStart, (int) (yRunway +((3*(runwayHeight*r)/8))+offset), (int) (ASDA*r), (int) ((3*(runwayHeight*r)/8)-(r*runwayHeight *0.05)));
			//			g2d.setColor(ldaColor);
			//			g2d.fillRect(xRunway+LDAStart, (int) (yRunway + (2* ((3*(runwayHeight*r)/8)))+offset), (int) (LDA*r), (int) ((3*(runwayHeight*r)/8)-(r*runwayHeight *0.05)));
			int yInc = (int) ((runwayHeight/2)*r);
			g2d.setFont(new Font("key", 1, 15));
			int textDistance = g2d.getFontMetrics().getHeight();
			int width = 3;
			g2d.setColor(toraColor);
			g2d.fillRect(xRunway+TORAStart, (int) (yRunway-yInc), (int) (TORA*r), width);
			g2d.drawString("TORA", 10, this.getHeight()- 20-(3*textDistance));
			g2d.setColor(todaColor);
			g2d.fillRect(xRunway+TODAStart, (int) (yRunway -yInc*2), (int) (TODA*r), width);
			g2d.drawString("TODA", 10, this.getHeight()- 20-(2*textDistance) );
			g2d.setColor(asdaColor);
			g2d.fillRect(xRunway+ASDAStart, (int) (yRunway -yInc*3), (int) (ASDA*r), width);
			g2d.drawString("ASDA",10, this.getHeight()- 20- textDistance);
			g2d.setColor(ldaColor);
			g2d.fillRect(xRunway+LDAStart, (int) (yRunway + yInc + (runwayHeight*r)), (int) (LDA*r), width);
			g2d.drawString("LDA", 10, this.getHeight()- 20);

			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("scale", 1, 8));
			int scaleWidth=runwayWidth/10;
			g2d.fillRect(10, (int) this.getHeight()-10, (int) (scaleWidth*r), 2);	
			g2d.fillRect(10, (int) this.getHeight()-15, (int) 2, 5);
			g2d.fillRect((int) (8+(scaleWidth*r)), (int) this.getHeight()-15, (int) 2, 5);
			g2d.fillRect((int) (8+(scaleWidth/2*r)), (int) this.getHeight()-12, (int) 2, 2);
			g2d.drawString(Integer.toString(scaleWidth)+"m", (int) ((scaleWidth*r)), (int) this.getHeight()-15);
			g2d.drawString(Integer.toString(scaleWidth/2)+"m", (int) ((scaleWidth/2*r)), (int) this.getHeight()-12);
			g2d.drawString("0m", 8, (int) this.getHeight()-15);
		}
	}


	public void setValues(){
		//		this.runwayWidth = 2000;
		//		this.runwayHeight = runwayWidth/20;
		//		this.leftTag="08L";
		//		this.rightTag="27R";
		//		this.TORA = 0;
		//		this.TODA = 0;
		//		this.ASDA = 0;
		//		this.LDA = 0;
		//			int distance = 400;
		//			this.threshold="08L";
		//			obstacleLeft=threshold.equals(leftTag);
		//			if(obstacleLeft){this.xObstacle = distance;}else{this.xObstacle=runwayWidth-distance;}
		//			this.yObstacle = runwayHeight/2;
		//			this.obstacleLength = 20;
		//			this.obstacleWidth = 20;
		//		if(obstacleLeft){this.LDAStart = (int) (runwayWidth*r);}else{this.LDAStart=0;}
		//		if(obstacleLeft){this.TORAStart = (int) (runwayWidth*r);}else{this.TORAStart=0;}
		//		if(obstacleLeft){this.TODAStart = (int) (runwayWidth*r);}else{this.TODAStart=0;}
		//		if(obstacleLeft){this.ASDAStart = (int) (runwayWidth*r);}else{this.ASDAStart=0;}


		if(airport!=null && runway!=null){
			this.runwayWidth = (int) runway.getTORA(runway.DEFAULT);
			this.runwayHeight = 60;

			this.TORA = (int) runway.getTORA(runway.REDECLARED);
			this.TODA = (int) runway.getTODA(runway.REDECLARED);
			this.ASDA = (int) runway.getASDA(runway.REDECLARED);
			this.LDA = (int) runway.getLDA(runway.REDECLARED);
			this.leftTag= airport.getCurrentPhysicalRunway().getRunway(0).getName();
			this.rightTag = airport.getCurrentPhysicalRunway().getRunway(1).getName();

			if(obstacle!=null){
				int distance = (int) airport.getCurrentPhysicalRunway().getDistanceAwayFromThreshold();
				this.threshold=airport.getCurrentPhysicalRunway().closeTo().getName(); 
				if(threshold.equals(airport.getCurrentPhysicalRunway().getRunway(0).getName())){this.leftTag=airport.getCurrentPhysicalRunway().getRunway(1).getName();}else{this.leftTag=airport.getCurrentPhysicalRunway().getRunway(0).getName();}
				this.rightTag=threshold;
				this.xObstacle=runwayWidth-distance;
				this.yObstacle = runwayHeight/2; //there will be a getter for this!!! :D
				this.obstacleLength =(int) obstacle.getLength();
				this.obstacleWidth = (int) obstacle.getWidth();
				this.LDAStart=(int) (xRunway-(obstacleLength*airport.getCurrentPhysicalRunway().getAngleOfSlope()));
				this.TORAStart=0;
				this.TODAStart=TORAStart+TORA;
				this.ASDAStart=TORAStart+TORA;
			}	
		}
	}

	public void updateAirport(Airport airport) {
		this.airport=airport;
		if(airport!=null &&
				airport.getCurrentPhysicalRunway() != null)
		{
			runway=airport.getCurrentRunway();
			obstacle=airport.getCurrentPhysicalRunway().getObstacle();
			setValues();
			repaint();
		}

	}


	//TODO: Remove redundant methods
	public void zoomIn(){
		ratio=ratio+ratioIncrement;
		repaint();
	}

	//TODO: Remove redundant methods
	public void zoomOut(){
		ratio=ratio-ratioIncrement;
		repaint();
	}

	public void setZoom(double ratio){
		this.ratio = ratio;
	}

	//TODO: Remove redundant methods
	public void resetZoom(){
		this.ratioIncrement=0.95;
	}


	public void setOffset(int x, int y){
		this.xOffset=x;
		this.yOffset=y;
	}
}



