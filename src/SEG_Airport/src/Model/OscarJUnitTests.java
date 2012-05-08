package Model;

import java.util.Random;

import junit.framework.TestCase;

/**
 * This class contains test cases in JUnit format for
 * the implementation of the Model. 
 * @author Oscar
 */

public class OscarJUnitTests extends TestCase{

	protected String[] names = {"airport", "b", "c", "d", "e", "f", "g"};
	protected double[] tora;
	protected double[] asda;
	protected double[] toda;
	protected double[] lda;
	protected double[] dt;
	protected double[] awayFromThreshold;
	protected double[] awayFromCenter;
	//Airport airport;
	
	
	protected void setUp(){
		Random r = new Random();
		tora = new double[5]; 
		asda = new double[5];
		toda = new double[5];
		lda = new double[5];
		dt = new double[5];
		awayFromThreshold = new double[2];
		awayFromCenter = new double[2];
		
		for (int i=0; i<5; i++){ 
			tora[i]=r.nextDouble()*1000;
			asda[i]=r.nextDouble()*1000;
			toda[i]=r.nextDouble()*1000;
			lda[i]=r.nextDouble()*1000;
			dt[i]=r.nextDouble()*1000;
		}
		
		for (int i=0; i<2; i++){
			awayFromThreshold[i] = r.nextDouble()*10;
			awayFromCenter[i] = r.nextDouble()*10;
		}
		
	}
	
	public void testInstantiateAirport(){
		this.setUp();
		
		Airport airport = new Airport(names[0]);
		Runway r0 = new Runway(names[1], tora[0], asda[0], toda[0], lda[0], dt[0]);
		Runway r1 = new Runway(names[2], tora[1], asda[1], toda[1], lda[1], dt[1]);
		Runway r2 = new Runway(names[3], tora[2], asda[2], toda[2], lda[2], dt[2]);
		Runway r3 = new Runway(names[4], tora[3], asda[3], toda[3], lda[3], dt[3]);
		
		Runway[] runways = {r0, r1, r2, r3};
		
		PhysicalRunway one = new PhysicalRunway(names[5], r0, r1);
		one.setDistanceAwayFromThreshold(awayFromThreshold[0]);
		one.setDistanceAwayFromCenterLine(awayFromCenter[0]);
		
		PhysicalRunway two = new PhysicalRunway(names[6], r2, r3);
		two.setDistanceAwayFromThreshold(awayFromThreshold[1]);
		two.setDistanceAwayFromCenterLine(awayFromCenter[1]);
		
		PhysicalRunway[] phyRunways = {one, two};
				
		airport.addPhysicalRunway(one);
		airport.addPhysicalRunway(two);
		
		assertEquals(airport.getName(), names[0]);
		for (int i=0; i<4; i++){
			Runway temp = runways[i];
			assertEquals(temp.getName(), names[i+1]);
			assertEquals(temp.getTORA(0), tora[i]);
			assertEquals(temp.getASDA(0), asda[i]);
			assertEquals(temp.getTODA(0), toda[i]);
			assertEquals(temp.getLDA(0), lda[i]);
			assertEquals(temp.getDisplacedThreshold(0), dt[i]);
		}
		
		for (int i=0; i<2; i++){
			PhysicalRunway temp = phyRunways[i];
			assertEquals(temp.getId(), names[i+4]);
			assertEquals(temp.getDistanceAwayFromThreshold(), awayFromThreshold[i]);
			assertEquals(temp.getDistanceAwayFromCenterLine(), awayFromCenter[i]);
		}
		
	}
	
	public void testAiportName(){
		this.setUp();
		Airport airport = new Airport(names[0]);
		
		assertEquals(airport.getName(), names[0]);
	}
	
	public void testRunwayName(){
		this.setUp();
		Runway r = new Runway(names[1], tora[0], asda[0], toda[0], lda[0], dt[0]);
		
		assertEquals(r.getName(), names[1]);
	}
	
	public void testRunwayTora(){
		this.setUp();
		Runway r = new Runway(names[1], tora[0], asda[0], toda[0], lda[0], dt[0]);
		
		assertEquals(r.getTORA(0), tora[0]);
	}
	
	public void testRunwayAsda(){
		this.setUp();
		Runway r = new Runway(names[1], tora[0], asda[0], toda[0], lda[0], dt[0]);
		
		assertEquals(r.getASDA(0), asda[0]);
	}
	
	public void testRunwayToda(){
		this.setUp();
		Runway r = new Runway(names[1], tora[0], asda[0], toda[0], lda[0], dt[0]);
		
		assertEquals(r.getTODA(0), toda[0]);
	}
	
	public void testRunwayLda(){
		this.setUp();
		Runway r = new Runway(names[1], tora[0], asda[0], toda[0], lda[0], dt[0]);
		
		assertEquals(r.getLDA(0), lda[0]);
	}
	
	public void testRunwayDt(){
		this.setUp();
		Runway r = new Runway(names[1], tora[0], asda[0], toda[0], lda[0], dt[0]);
		
		assertEquals(r.getDisplacedThreshold(0), dt[0]);
	}
	
	public void testPhysicalRunwayDistanceFromThreshold(){
		this.setUp();
		Runway r = new Runway(names[1], tora[0], asda[0], toda[0], lda[0], dt[0]);
		Runway r1 = new Runway(names[2], tora[1], asda[1], toda[1], lda[1], dt[1]);
		
		PhysicalRunway one = new PhysicalRunway(names[5], r, r1);
		one.setDistanceAwayFromThreshold(awayFromThreshold[0]);
		one.setDistanceAwayFromCenterLine(awayFromCenter[0]);
		
		assertEquals(one.getDistanceAwayFromThreshold(), awayFromThreshold[0]);
	}
	
	public void testPhysicalRunwayDistanceFromCenter(){
		this.setUp();
		Runway r = new Runway(names[1], tora[0], asda[0], toda[0], lda[0], dt[0]);
		Runway r1 = new Runway(names[2], tora[1], asda[1], toda[1], lda[1], dt[1]);
		
		PhysicalRunway one = new PhysicalRunway(names[5], r, r1);
		one.setDistanceAwayFromThreshold(awayFromThreshold[0]);
		one.setDistanceAwayFromCenterLine(awayFromCenter[0]);
		
		assertEquals(one.getDistanceAwayFromCenterLine(), awayFromCenter[0]);
	}
	
}
