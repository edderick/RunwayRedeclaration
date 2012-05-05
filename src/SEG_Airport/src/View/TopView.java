package View;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.PaintContext;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JComponent;
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

	//this value determines how much of the width of the runway the runwayTag takes up.
	final double fontRatio = 0.5;

	int xOffset = 0;
	int yOffset = 0;

	Color toraColor = new Color(255, 165, 0);
	Color todaColor = new Color(255, 0, 0);
	Color asdaColor = new Color(0, 0, 255);
	Color ldaColor = new Color(255, 0, 255);

	double meterToPixel;
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
	int spaceForScale;

	//relative to panel
	int xRunway;
	int yRunway;

	//relative to runway
	int xObstacle;
	int yObstacle;

	int mousex;
	int mousey;
	
	int runwayStripWidthFromCentreLine=150;
	int runwayToEdgeOfCnGArea = 60;
	int CnGAreaWidthFromCentreLine = 75;
	int CnGWiderAreaWidthFromCentreLine=105;
	int widerAreaPointOne = 150;
	int widerAreaPointTwo = 300;
	

	public TopView(Airport airport){
		super();
		this.setBackground(new Color(154, 205, 50));
		setLayout(new MigLayout("", "[grow]", "[grow][]"));
		
		updateAirport(airport);
		setValues();

		setDragCursor(this);
		createSlider();
		createDraggingListeners();
		setVisible(true);

	}

	public void paint (Graphics g){
		super.paint(g);
		if(visible){
			setValues();
			Graphics2D g2d = (Graphics2D)g;
			
			g.translate(xOffset, yOffset);
			runwayCreation(g2d);
			obstacleCreation(g2d);
			declaredRunwaysCreation(g2d);
			drawDirection(g2d);
			
			g.translate(-xOffset, -yOffset);
			paintComponents(g);
			drawKey(g2d);
			drawScale(g2d);
		}
	}

	public void drawDirection(Graphics2D g2d){
		if(airport.getCurrentRunway()!=null){
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine(xRunway+meterToPixel(runwayWidth/4), yRunway-meterToPixel(runwayStripWidthFromCentreLine*2), xRunway+meterToPixel(3*runwayWidth/4), yRunway-meterToPixel(runwayStripWidthFromCentreLine*2));

		if(airport.getCurrentRunway().getName().equals(leftTag)){
			g2d.drawLine(xRunway+meterToPixel(3*runwayWidth/4), yRunway-meterToPixel(runwayStripWidthFromCentreLine*2), xRunway+meterToPixel(11*runwayWidth/16), yRunway-meterToPixel(runwayStripWidthFromCentreLine*2)+meterToPixel(runwayStripWidthFromCentreLine/4));
			g2d.drawLine(xRunway+meterToPixel(3*runwayWidth/4), yRunway-meterToPixel(runwayStripWidthFromCentreLine*2), xRunway+meterToPixel(11*runwayWidth/16), yRunway-meterToPixel(runwayStripWidthFromCentreLine*2)-meterToPixel(runwayStripWidthFromCentreLine/4));
		}else{
			g2d.drawLine(xRunway+meterToPixel(runwayWidth/4), yRunway-meterToPixel(runwayStripWidthFromCentreLine*2), xRunway+meterToPixel(5*runwayWidth/16), yRunway-meterToPixel(runwayStripWidthFromCentreLine*2)+meterToPixel(runwayStripWidthFromCentreLine/4));
			g2d.drawLine(xRunway+meterToPixel(runwayWidth/4),yRunway-meterToPixel(runwayStripWidthFromCentreLine*2), xRunway+meterToPixel(5*runwayWidth/16), yRunway-meterToPixel(runwayStripWidthFromCentreLine*2)-meterToPixel(runwayStripWidthFromCentreLine/4));
		}
		}
	}
	
	
	public void setVisible(boolean b){
		visible=b;
	}

	public void createSlider(){
		JSlider zoomSlider = new JSlider();
		zoomSlider.setBackground(null);
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
		zoomSlider.setCursor(Cursor.getDefaultCursor());
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

	public int meterToPixel(int x){
		return (int) (meterToPixel*x);
	}
	
	public void runwayCreation(Graphics2D g2d){
		if(airport!=null && runway!=null){

			int pWidth = this.getWidth();
			int pHeight = this.getHeight();

			int width = (int) (ratio*pWidth);
			meterToPixel=width/(double)runwayWidth;

			int height = meterToPixel(runwayHeight);

			//calculates the x and y values to position the runway on the view
			xRunway = (int) (((1.0-ratio)/2) * pWidth);
			yRunway = (pHeight - height)/2;

			
			drawRunwayArea(g2d);

			g2d.setColor(Color.GRAY);
			g2d.fillRect(xRunway, yRunway, width, height);

			drawRunwayMarkings(g2d);
			
			
			
			for(int i=0; i<1000; i++){
				Font f = new Font("tag", 1, 1000-i);
				g2d.setFont(f);
				if(g2d.getFontMetrics().stringWidth(leftTag)<=(fontRatio*height))break;
			}			
			g2d.rotate(0.5 * Math.PI,  xRunway+tagBorder, yRunway+(height/4));
			g2d.drawString(leftTag, xRunway+ tagBorder, yRunway+(height/4));
			g2d.rotate(-0.5 * Math.PI,  xRunway + tagBorder, yRunway+(height/4));
			g2d.rotate(-0.5 * Math.PI,  xRunway + width - tagBorder, yRunway+(3*(height/4)));
			g2d.drawString(rightTag, xRunway+ width - tagBorder, yRunway+(3*(height/4)));
			g2d.rotate(0.5 * Math.PI,  xRunway + width - tagBorder, yRunway+(3*(height/4)));
			
			

		}

	}

	public void drawRunwayArea(Graphics2D g2d){

		int nPoints = 12;
		int xPoints[] = new int[nPoints];
		int yPoints[] = new int[nPoints];
		
		xPoints[0]=(int) (xRunway-(60*meterToPixel));
		yPoints[0]= (int) (yRunway+(((runwayHeight/2) -75)*meterToPixel));
		xPoints[1]= xPoints[0];
		yPoints[1]= (int) (yRunway+(((runwayHeight/2) +75)*meterToPixel));
		xPoints[2]=(int) (xPoints[1]+(210*meterToPixel));
		yPoints[2]= yPoints[1];
		xPoints[3]=(int) (xRunway+(300*meterToPixel));
		yPoints[3]= (int) (yRunway+(((runwayHeight/2) +105)*meterToPixel));
		xPoints[4]=(int) (xRunway+((runwayWidth-300)*meterToPixel));
		yPoints[4]= yPoints[3];
		xPoints[5]=(int) (xRunway+((runwayWidth-150)*meterToPixel));
		yPoints[5]= yPoints[1];
		xPoints[6]=(int) (xRunway+((runwayWidth+60)*meterToPixel));
		yPoints[6]= yPoints[1];
		xPoints[7]=xPoints[6];
		yPoints[7]= yPoints[0];
		xPoints[8]=xPoints[5];
		yPoints[8]= yPoints[0];
		xPoints[9]=xPoints[4];
		yPoints[9]=  (int) (yRunway-((105-(runwayHeight/2))*meterToPixel));
		xPoints[10]=xPoints[3];
		yPoints[10]= yPoints[9];
		xPoints[11]=xPoints[2];
		yPoints[11]= yPoints[0];
		g2d.setColor(new Color(34, 139, 34));
		g2d.fillRect(xPoints[0], (int) (yRunway - ((150-(runwayHeight/2))*meterToPixel)), (int) ((runwayWidth+120)*meterToPixel),(int) (300*meterToPixel));

		g2d.setColor(new Color(0, 100, 0));

		g2d.fillPolygon(xPoints, yPoints, nPoints);
	}
	
	public void drawRunwayMarkings(Graphics2D g2d){
		g2d.setColor(Color.WHITE);
		int dashesY = (int) (yRunway+meterToPixel(runwayHeight/2));
		int dashesLength = 35;
		int dashesWidth = 1;
		int gaps = 25;
		int ratioOfDashesToThresholdMarker = 4;
		for(int i = dashesLength*ratioOfDashesToThresholdMarker; i<runwayWidth-(dashesLength*ratioOfDashesToThresholdMarker+1); i=i+gaps+dashesLength){
			g2d.drawRect(xRunway+meterToPixel(i), dashesY, meterToPixel(dashesLength), meterToPixel(dashesWidth));
		}
		
		int ratioOfDashesToTagSize = 3;
		
		tagBorder=(int) meterToPixel(dashesLength*ratioOfDashesToTagSize);
			
		int ratioOfThresholdDashesWidthToRunwayHeight = 20;
		int ratioOfDashesToThresholdDashes = 2;
		
		dashesWidth=(runwayHeight/ratioOfThresholdDashesWidthToRunwayHeight);
		if (dashesWidth == 0) dashesWidth = 1;
		gaps=dashesWidth;
		int dashesX = xRunway + meterToPixel(dashesLength/2);
		dashesLength=dashesLength*ratioOfDashesToThresholdDashes;

		for(int i = gaps; i<runwayHeight-(gaps*2); i=i+gaps+dashesWidth){
			g2d.fillRect(dashesX, meterToPixel(i)+yRunway, meterToPixel(dashesLength), meterToPixel(dashesWidth));
		}

		dashesX = xRunway + meterToPixel(runwayWidth)  - meterToPixel(dashesLength/ratioOfDashesToThresholdMarker) - meterToPixel(dashesLength);
		for(int i = gaps; i<runwayHeight-(gaps*2); i=i+gaps+dashesWidth){
			g2d.fillRect(dashesX, meterToPixel(i)+yRunway, meterToPixel(dashesLength), meterToPixel(dashesWidth));
		}
	}
	
	public void obstacleCreation(Graphics2D g2d){
		if(obstacle!=null){
			g2d.setColor(Color.RED);
			g2d.fillRect(meterToPixel(xObstacle)+xRunway, meterToPixel(yObstacle)+yRunway, meterToPixel(obstacleLength), meterToPixel(obstacleWidth));
		}
	}

	
	public void drawKey(Graphics2D g2d){
		g2d.setFont(new Font("key", 1, 15));
		int textDistance = g2d.getFontMetrics().getHeight();
		spaceForScale = 30;
		int spaceFromLeftEdge = 10;
		g2d.setColor(toraColor);
		g2d.drawString("TORA", spaceFromLeftEdge, this.getHeight()- spaceForScale-textDistance);
		g2d.setColor(todaColor);
		g2d.drawString("TODA", spaceFromLeftEdge, this.getHeight()- spaceForScale-(2*textDistance) );
		g2d.setColor(asdaColor);
		g2d.drawString("ASDA",spaceFromLeftEdge, this.getHeight()- spaceForScale- (3*textDistance));
		g2d.setColor(ldaColor);
		g2d.drawString("LDA", spaceFromLeftEdge, this.getHeight()- spaceForScale);
	}

	
	public void drawScale(Graphics2D g2d){
		if(airport!=null && runway!=null){
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("scale", 1, 8));
			int scaleWidth=runwayWidth/10;
			g2d.fillRect(10, (int) this.getHeight()-10, (int) (scaleWidth*meterToPixel), 2);	
			g2d.fillRect(10, (int) this.getHeight()-15, (int) 2, 5);
			g2d.fillRect((int) (8+(scaleWidth*meterToPixel)), (int) this.getHeight()-15, (int) 2, 5);
			g2d.fillRect((int) (8+(scaleWidth/2*meterToPixel)), (int) this.getHeight()-12, (int) 2, 2);
			g2d.drawString(Integer.toString(scaleWidth)+"m", (int) ((scaleWidth*meterToPixel)), (int) this.getHeight()-15);
			g2d.drawString(Integer.toString(scaleWidth/2)+"m", (int) ((scaleWidth/2*meterToPixel)), (int) this.getHeight()-12);
			g2d.drawString("0m", 8, (int) this.getHeight()-15);
		}
	}

	public void declaredRunwaysCreation(Graphics2D g2d){
		if(airport!=null && runway!=null){
			int yInc = meterToPixel(runwayHeight/2);
			g2d.setFont(new Font("key", 1, 15));
			int width = 3;
			g2d.setColor(toraColor);
			g2d.fillRect(xRunway+TORAStart,  (yRunway-yInc),  meterToPixel(TORA), width);
			g2d.setColor(todaColor);
			g2d.fillRect(xRunway+TODAStart, (yRunway -yInc*2),  meterToPixel(TODA), width);
			g2d.setColor(asdaColor);
			g2d.fillRect(xRunway+ASDAStart, (yRunway -yInc*3),  meterToPixel(ASDA), width);
			g2d.setColor(ldaColor);
			g2d.fillRect(xRunway+LDAStart, (yRunway + yInc + meterToPixel(runwayHeight)),  meterToPixel(LDA), width);
		}
	}


	public void setValues(){
		if(airport!=null && runway!=null){
			this.runwayWidth = (int) runway.getTORA(Runway.DEFAULT);
			this.runwayHeight = 60;

			this.TORA = (int) runway.getTORA(Runway.REDECLARED);
			this.TODA = (int) runway.getTODA(Runway.REDECLARED);
			this.ASDA = (int) runway.getASDA(Runway.REDECLARED);
			this.LDA = (int) runway.getLDA(Runway.REDECLARED);
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
				this.LDAStart=0;
				this.TORAStart=0;
				this.TODAStart=(int) (TORAStart+((TORA-TODA)*meterToPixel));
				this.ASDAStart=(int) (TORAStart+((TORA-ASDA)*meterToPixel));
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
			setOffset(0, 0);
			setValues();
			repaint();
		}

	}

	public void setZoom(double ratio){
		this.ratio = ratio;
	}

	public void setOffset(int x, int y){
		int a = meterToPixel(runwayWidth);
		int b = meterToPixel(runwayHeight);
		if(x>a){
			x=a;
		}
		if(y>b){
			y=b;
		}
		if(x<-a){
			x=-a;
		}
		if(y<-b){
			y=-b;
		}
		this.xOffset=x;
		this.yOffset=y;
	}
	
	public void reset(){
		setZoom(0.95);
		setOffset(0, 0);
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



