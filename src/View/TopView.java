import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class TopView extends JPanel {
	
	
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
	int runwayWidth;
	int runwayHeight;
	int obstacleLength;
	int obstacleWidth;
	String leftTag;
	String rightTag;
	boolean obstacle;
	
	//relative to panel
	int xRunway;
	int yRunway;
	
	//relative to runway
	int xObstacle;
	int yObstacle;
	
	
	
		
	public TopView(){
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
		r=width/(double)runwayWidth;
				
		int height = (int) (runwayHeight * r);
		
			
		//calculates the x and y values to position the runway on the view
		xRunway = (int) (((1.0-ratio)/2) *pWidth);
		yRunway = (pHeight - height)/2;
		
		g2d.fillRect(xRunway, yRunway, width, height);
		
		//27R 08L tags
		g2d.setColor(Color.BLACK);
		int i;
		for(i=0; i<1000; i++){
			Font f = new Font("tag", 1, 1000-i);
			g2d.setFont(f);
			if(g2d.getFontMetrics().stringWidth(leftTag)<=(fontRatio*height))break;
		}
		
		if(tagRotate==1){
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
		
		
	}
	
	public void obstacleCreation(Graphics2D g2d){
		g2d.setColor(Color.RED);
		int pWidth = this.getWidth();
		g2d.fillRect((int) ((r*xObstacle)+xRunway), (int)((r*yObstacle)+yRunway), (int)(r*obstacleLength), (int)(r*obstacleWidth));
		
	}
	
	public void declaredRunwaysCreation(Graphics2D g2d){
		Color toraColor = new Color(255, 0, 0, 125);
		Color todaColor = new Color(0, 255, 0, 125);
		Color asdaColor = new Color(0, 0, 255, 125);
		Color ldaColor = new Color(255, 0, 255, 125);
		g2d.setColor(toraColor);
		g2d.fillRect(xRunway+TORAStart, yRunway, (int) (TORA*r), (int) (((runwayHeight*r)/2)-10));
		g2d.setColor(todaColor);
		g2d.fillRect(xRunway+TODAStart, (int) (yRunway -((runwayHeight*r)/2)), (int) (TODA*r), (int) (((runwayHeight*r)/2)-10));
		g2d.setColor(asdaColor);
		g2d.fillRect(xRunway+ASDAStart, (int) (yRunway +((runwayHeight*r)/2)), (int) (ASDA*r), (int) (((runwayHeight*r)/2)-10));
		g2d.setColor(ldaColor);
		g2d.fillRect(xRunway+LDAStart, (int) (yRunway + (2* ((runwayHeight*r)/2))), (int) (LDA*r), (int) (((runwayHeight*r)/2)-10));
	}
	
	public void setRunwayDimensions(int width, int height, String left, String right){
		this.runwayWidth = width;
		this.runwayHeight = height;
		this.leftTag=left;
		this.rightTag=right;
	}
	
	public void setValues(int tora, int toraStart, int toda, int todaStart, int asda, int asdaStart, int lda, int ldaStart, boolean obstacle, int x, int y, int length, int width){
		this.TORA = tora;
		this.TODA = toda;
		this.ASDA = asda;
		this.LDA = lda;
		this.obstacle = obstacle;
		this.xObstacle = x;
		this.yObstacle = y;
		this.obstacleLength = length;
		this.obstacleWidth = width;
		this.TORAStart = toraStart;
		this.TODAStart = todaStart;
		this.ASDAStart = asdaStart;
		this.LDAStart = ldaStart;
		
		
	}
}



