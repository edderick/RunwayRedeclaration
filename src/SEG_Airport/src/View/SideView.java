//TODO: If this is the latest version then this needs a large refactor!
//This class should contain a runway and draw using that.

package View;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class SideView extends JPanel {
	
	
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
	boolean obstacle;
	
	//relative to panel
	int xRunway;
	int yRunway;
	
	//relative to runway
	int xObstacle;
			
	public SideView(){
		super();
		setSize(300,200);
		this.setBackground(Color.WHITE);
		setVisible(true);
			
	}
	
	public void paint (Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		runwayCreation(g2d);
		if(obstacle)obstacleCreation(g2d);
		declaredRunwaysCreation(g2d);
			
	}
	
	public void runwayCreation(Graphics2D g2d){
		g2d.setColor(Color.GRAY);
		
		int pWidth = this.getWidth();
		int pHeight = this.getHeight();
		
		
		int width = (int) (ratio*pWidth);
		r=width/(double)runwayLength;
				
				//calculates the x and y values to position the runway on the view
		xRunway = (int) (((1.0-ratio)/2) * pWidth);
		yRunway = pHeight/2;
		
		g2d.drawLine(xRunway,  yRunway, (int) (xRunway+(runwayLength*r)), yRunway);
		}
	
	public void obstacleCreation(Graphics2D g2d){
		g2d.setColor(Color.RED);
		g2d.fillRect((int) ((r*xObstacle)+xRunway), (int) (yRunway-(r*obstacleHeight)), (int)(r*obstacleLength), (int)(r*obstacleHeight));
		
	}
	
	public void declaredRunwaysCreation(Graphics2D g2d){
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
		
//		g2d.setColor(Color.BLACK);
//		g2d.drawString("TORA", (int) (xRunway+((TORA*r)/2)), (yRunway+g2d.getFontMetrics().getHeight()));
//		g2d.drawString("TODA", (int) (xRunway+((TODA*r)/2)), (int) ((yRunway -((runwayHeight*r)/2)) +g2d.getFontMetrics().getHeight()));
//		g2d.drawString("ASDA", (int) (xRunway+((ASDA*r)/2)), (int) ((yRunway +((runwayHeight*r)/2) +g2d.getFontMetrics().getHeight())));
//		g2d.drawString("LDA", (int) (xRunway+((LDA*r)/2)), (int) ((yRunway + (2* ((runwayHeight*r)/2)) +g2d.getFontMetrics().getHeight())));
		}
	
	public void setRunwayDimensions(int length){
		this.runwayLength = length;
	}
	
	public void setValues(int tora, int toraStart, int toda, int todaStart, int asda, int asdaStart, int lda, int ldaStart, boolean obstacle, int x, int length, int height){
		this.TORA = tora;
		this.TODA = toda;
		this.ASDA = asda;
		this.LDA = lda;
		this.obstacle = obstacle;
		this.xObstacle = x;
		this.obstacleLength = length;
		this.obstacleHeight = height;
		this.TORAStart = toraStart;
		this.TODAStart = todaStart;
		this.ASDAStart = asdaStart;
		this.LDAStart = ldaStart;
		
		
	}
}



