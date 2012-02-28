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
	
	
	int TORA;
	int TODA;
	int ASDA;
	int LDA;
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
			
	}
	
	public void runwayCreation(Graphics2D g2d){
		g2d.setColor(Color.GRAY);
		
		int pWidth = this.getWidth();
		int pHeight = this.getHeight();
		
		
		int width = (int) (ratio*pWidth);
		int height = (width * runwayHeight)/runwayWidth;
		
			
		//calculates the x and y values to position the runway on the view
		xRunway = (int) (((1.0-ratio)/2) *pWidth);
		yRunway = (pHeight - height)/2;
		
		g2d.fillRect(xRunway, yRunway, width, height);
		
		//27R 08L tags
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("tag", 1, (int) (ratio*(pWidth*15)/225)));
		g2d.rotate(0.5 * Math.PI,  xRunway+tagBorder, yRunway+(height/4));
		g2d.drawString(leftTag, xRunway+ tagBorder, yRunway+(height/4));
		g2d.rotate(-0.5 * Math.PI,  xRunway + tagBorder, yRunway+(height/4));
		g2d.rotate(-0.5 * Math.PI,  xRunway + width - tagBorder, yRunway+(3*(height/4)));
		g2d.drawString(rightTag, xRunway+ width - tagBorder, yRunway+(3*(height/4)));
		g2d.rotate(0.5 * Math.PI,  xRunway + width - tagBorder, yRunway+(3*(height/4)));
		
		
		
	}
	
	public void obstacleCreation(Graphics2D g2d){
		g2d.setColor(Color.RED);
		int pWidth = this.getWidth();
		int r = (int) ((ratio*pWidth)/runwayWidth);
			
		g2d.fillRect((r*xObstacle)+xRunway, (r*yObstacle)+yRunway, r*obstacleLength, r*obstacleWidth);
		
	}
	
	public void setRunwayDimensions(int width, int height, String left, String right){
		this.runwayWidth = width;
		this.runwayHeight = height;
		this.leftTag=left;
		this.rightTag=right;
	}
	
	public void setValues(int tora, int toda, int asda, int lda, boolean obstacle, int x, int y, int length, int width){
		this.TORA = tora;
		this.TODA = toda;
		this.ASDA = asda;
		this.LDA = lda;
		this.obstacle = obstacle;
		this.xObstacle = x;
		this.yObstacle = y;
		this.obstacleLength = length;
		this.obstacleWidth = width;
		
		
	}
}



