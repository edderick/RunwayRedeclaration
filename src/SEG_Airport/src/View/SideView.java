//TODO: This class and it's bros need to have a common parent that is more specific that JPanel ;)

package View;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Model.Airport;
import Model.AirportObserver;
import Model.Obstacle;
import Model.Runway;


@SuppressWarnings("serial")
public class SideView extends JPanel implements AirportObserver{
	
	boolean visible = false;
	
	//this value determines the amount of pixels the tag is from the end of the runway
	final int tagBorder = 10;
	
	//this value determines how much of the width of the panel the runway takes up.
	final double ratio = 0.75;
	
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
	int declaredWidth = 10;
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
			
	public SideView(Airport airport){
		super();
		setSize(300,200);
		this.setBackground(new Color(154, 205, 50));
		setVisible(true);
		updateAirport(airport);
		setValues();
			
	}
	
	public void setVisible(boolean b){
		visible=b;
	}
	
	public void paint (Graphics g){
		super.paint(g);
		if(visible){
		setValues();
		Graphics2D g2d = (Graphics2D)g;
		runwayCreation(g2d);

			obstacleCreation(g2d);
		declaredRunwaysCreation(g2d);
		}
			
	}
	
	public void runwayCreation(Graphics2D g2d){
//		if(airport!=null){
		g2d.setColor(Color.GRAY);
		
		int pWidth = this.getWidth();
		int pHeight = this.getHeight();
		
		
		int width = (int) (ratio*pWidth);
		r=width/(double)runwayLength;
				
				//calculates the x and y values to position the runway on the view
		xRunway = (int) (((1.0-ratio)/2) * pWidth);
		yRunway = pHeight/2;
		
		g2d.drawLine(xRunway,  yRunway, (int) (xRunway+(runwayLength*r)), yRunway);
		g2d.setColor(Color.BLUE);
		g2d.fillRect(0, 0, pWidth, yRunway);
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
		Color toraColor = new Color(255, 0, 0, 125);
		Color todaColor = new Color(0, 255, 0, 125);
		Color asdaColor = new Color(0, 0, 255, 125);
		Color ldaColor = new Color(255, 0, 255, 125);
		g2d.setColor(toraColor);
		g2d.fillRect(xRunway+TORAStart, yRunway, (int) (TORA*r), (int) ((declaredWidth*r)/2));
		g2d.setColor(todaColor);
		g2d.fillRect(xRunway+TODAStart, (int) (yRunway +((declaredWidth*r)/2)), (int) (TODA*r), (int) ((declaredWidth*r)/2));
		g2d.setColor(asdaColor);
		g2d.fillRect(xRunway+ASDAStart, (int) (yRunway +(2*(declaredWidth*r)/2)), (int) (ASDA*r), (int) ((declaredWidth*r)/2));
		g2d.setColor(ldaColor);
		g2d.fillRect(xRunway+LDAStart, (int) (yRunway + (3* ((declaredWidth*r)/2))), (int) (LDA*r), (int) ((declaredWidth*r)/2));
//		}
//		g2d.setColor(Color.BLACK);
//		g2d.drawString("TORA", (int) (xRunway+((TORA*r)/2)), (yRunway+g2d.getFontMetrics().getHeight()));
//		g2d.drawString("TODA", (int) (xRunway+((TODA*r)/2)), (int) ((yRunway -((runwayHeight*r)/2)) +g2d.getFontMetrics().getHeight()));
//		g2d.drawString("ASDA", (int) (xRunway+((ASDA*r)/2)), (int) ((yRunway +((runwayHeight*r)/2) +g2d.getFontMetrics().getHeight())));
//		g2d.drawString("LDA", (int) (xRunway+((LDA*r)/2)), (int) ((yRunway + (2* ((runwayHeight*r)/2)) +g2d.getFontMetrics().getHeight())));
		}

	
	public void setValues(){
		this.runwayLength = 2000;
		this.TORA = 0;
		this.TODA = 0;
		this.ASDA = 0;
		this.LDA = 0;
			int distance = 400;
			this.threshold="08L";
			this.xObstacle=runwayLength-distance;
			this.obstacleLength = 20;
			this.obstacleHeight = 20;
		this.LDAStart=0;
		this.TORAStart=0;
		this.TODAStart=0;
		this.ASDAStart=0;
		
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
//		this.airport=airport;
//		if(airport!=null && airport.getCurrentRunway() != null &&
//				   airport.getCurrentPhysicalRunway() != null && 
//				   airport.getCurrentPhysicalRunway().getObstacle() != null)
//				{
//			runway=airport.getCurrentRunway();
//			obstacle=airport.getCurrentPhysicalRunway().getObstacle();
//		}
		
	}
}



