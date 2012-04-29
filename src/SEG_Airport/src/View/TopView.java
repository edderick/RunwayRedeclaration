//This class and it's bro need to have a common parent that is more specific that JPanel ;)

package View;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Model.Airport;
import Model.AirportObserver;
import Model.Obstacle;
import Model.Runway;


@SuppressWarnings("serial")
public class TopView extends JPanel implements AirportObserver{
	
	
	//this value determines the amount of pixels the tag is from the end of the runway
	final int tagBorder = 10;
	
	//this value determines how much of the width of the panel the runway takes up.
	double ratio = 0.75;
	
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
	
		
	public TopView(Airport airport){
		super();
		setSize(300,200);
		this.setBackground(Color.WHITE);
		setVisible(true);
		updateAirport(airport);
		setValues();		
	}
	
	public void paint (Graphics g){
		super.paint(g);
		g.translate(xOffset, yOffset);
		setValues();
		Graphics2D g2d = (Graphics2D)g;
		
		runwayCreation(g2d);
		obstacleCreation(g2d);
		declaredRunwaysCreation(g2d);
			
	}
	
	public void runwayCreation(Graphics2D g2d){
//		if(airport!=null){
		
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
		g2d.setColor(Color.ORANGE);
		g2d.fillRect(xPoints[0], (int) (yRunway - ((150-(runwayHeight/2))*r)), (int) ((runwayWidth+120)*r),(int) (300*r));
		
		g2d.setColor(Color.BLUE);
		
		g2d.fillPolygon(xPoints, yPoints, nPoints);
		
		g2d.setColor(Color.GRAY);
		g2d.fillRect(xRunway, yRunway, width, height);
		
		//27R 08L tags
		g2d.setColor(Color.BLACK);
		int i;
		for(i=0; i<1000; i++){
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
		
//		}
		
		
		
	}
	
	public void obstacleCreation(Graphics2D g2d){
//		if(obstacle!=null){
			g2d.setColor(Color.RED);
			g2d.fillRect((int) ((r*xObstacle)+xRunway), (int)((r*yObstacle)+yRunway), (int)(r*obstacleLength), (int)(r*obstacleWidth));
//		}
	}
	
	public void declaredRunwaysCreation(Graphics2D g2d){
//		if(airport!=null){
		Color toraColor = new Color(255, 0, 0, 125);
		Color todaColor = new Color(0, 255, 0, 125);
		Color asdaColor = new Color(0, 0, 255, 125);
		Color ldaColor = new Color(255, 0, 255, 125);
		if(obstacleLeft){
			g2d.setColor(toraColor);
			g2d.fillRect(xRunway+TORAStart-(int) (TORA*r), yRunway, (int) (TORA*r), (int) (((runwayHeight*r)/2)-(r*runwayHeight *0.05)));
			g2d.setColor(todaColor);
			g2d.fillRect(xRunway+TODAStart-(int) (TODA*r), (int) (yRunway -((runwayHeight*r)/2)), (int) (TODA*r), (int) (((runwayHeight*r)/2)-(r*runwayHeight *0.05)));
			g2d.setColor(asdaColor);
			g2d.fillRect(xRunway+ASDAStart-(int) (ASDA*r), (int) (yRunway +((runwayHeight*r)/2)), (int) (ASDA*r), (int) (((runwayHeight*r)/2)-(r*runwayHeight *0.05)));
			g2d.setColor(ldaColor);
			g2d.fillRect(xRunway+LDAStart-(int) (LDA*r), (int) (yRunway + (2* ((runwayHeight*r)/2))), (int) (LDA*r), (int) (((runwayHeight*r)/2)-(r*runwayHeight *0.05)));
			
		}else{	
			g2d.setColor(toraColor);
			g2d.fillRect(xRunway+TORAStart, yRunway, (int) (TORA*r), (int) (((runwayHeight*r)/2)-(r*runwayHeight *0.05)));
			g2d.setColor(todaColor);
			g2d.fillRect(xRunway+TODAStart, (int) (yRunway -((runwayHeight*r)/2)), (int) (TODA*r), (int) (((runwayHeight*r)/2)-(r*runwayHeight *0.05)));
			g2d.setColor(asdaColor);
			g2d.fillRect(xRunway+ASDAStart, (int) (yRunway +((runwayHeight*r)/2)), (int) (ASDA*r), (int) (((runwayHeight*r)/2)-(r*runwayHeight *0.05)));
			g2d.setColor(ldaColor);
			g2d.fillRect(xRunway+LDAStart, (int) (yRunway + (2* ((runwayHeight*r)/2))), (int) (LDA*r), (int) (((runwayHeight*r)/2)-(r*runwayHeight *0.05)));
		}
		
		g2d.setColor(Color.BLACK);
		g2d.drawString("TORA", (int) (xRunway+((TORA*r)/2)), (yRunway+g2d.getFontMetrics().getHeight()));
		g2d.drawString("TODA", (int) (xRunway+((TODA*r)/2)), (int) ((yRunway -((runwayHeight*r)/2)) +g2d.getFontMetrics().getHeight()));
		g2d.drawString("ASDA", (int) (xRunway+((ASDA*r)/2)), (int) ((yRunway +((runwayHeight*r)/2) +g2d.getFontMetrics().getHeight())));
		g2d.drawString("LDA", (int) (xRunway+((LDA*r)/2)), (int) ((yRunway + (2* ((runwayHeight*r)/2)) +g2d.getFontMetrics().getHeight())));
//		}
	}
	
	
	public void setValues(){
		this.runwayWidth = 2000;
		this.runwayHeight = runwayWidth/20;
		this.leftTag="08L";
		this.rightTag="27R";
		this.TORA = 1500;
		this.TODA = 1000;
		this.ASDA = 500;
		this.LDA = 1500;
			int distance = 400;
			this.threshold="08L";
			obstacleLeft=threshold.equals(leftTag);
			if(obstacleLeft){this.xObstacle = distance;}else{this.xObstacle=runwayWidth-distance;}
			this.yObstacle = runwayHeight/2;
			this.obstacleLength = 20;
			this.obstacleWidth = 20;
		if(obstacleLeft){this.LDAStart = (int) (runwayWidth*r);}else{this.LDAStart=0;}
		if(obstacleLeft){this.TORAStart = (int) (runwayWidth*r);}else{this.TORAStart=0;}
		if(obstacleLeft){this.TODAStart = (int) (runwayWidth*r);}else{this.TODAStart=0;}
		if(obstacleLeft){this.ASDAStart = (int) (runwayWidth*r);}else{this.ASDAStart=0;}
		
		
//		if(airport!=null){
//		this.runwayWidth = (int) runway.getTORA(runway.DEFAULT);
//		this.runwayHeight = runwayWidth/20;
//		
//		this.TORA = (int) runway.getTORA(runway.REDECLARED);
//		this.TODA = (int) runway.getTODA(runway.REDECLARED);
//		this.ASDA = (int) runway.getASDA(runway.REDECLARED);
//		this.LDA = (int) runway.getLDA(runway.REDECLARED);
//		
//		if(obstacle!=null){
//			int distance = (int) airport.getCurrentPhysicalRunway().getDistanceAwayFromThreshold();
//			this.threshold=airport.getCurrentPhysicalRunway().closeTo().getName(); 
//			if(threshold.equals(airport.getCurrentPhysicalRunway().getRunway(0).getName())){this.leftTag=airport.getCurrentPhysicalRunway().getRunway(1).getName();}else{this.leftTag=airport.getCurrentPhysicalRunway().getRunway(0).getName();}
//			this.rightTag=threshold;
//			this.xObstacle=runwayWidth-distance;
//			this.yObstacle = runwayHeight/2;
//			this.obstacleLength =(int) obstacle.getLength();
//			this.obstacleWidth = (int) obstacle.getWidth();
//			this.LDAStart=(int) (xRunway-(obstacleLength*airport.getCurrentPhysicalRunway().getAngleOfSlope()));
//			this.TORAStart=0;
//			this.TODAStart=TORAStart+TORA;
//			this.ASDAStart=TORAStart+TORA;
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
	
	
	
	public void zoomIn(){
		ratio=ratio+ratioIncrement;
	}
	
	public void zoomOut(){
		ratio=ratio-ratioIncrement;
	}
	
	public void setZoom(double ratioIncrement){
		this.ratioIncrement=ratioIncrement;
	}
	
	public void setOffset(int x, int y){
		this.xOffset=x;
		this.yOffset=y;
	}
}



