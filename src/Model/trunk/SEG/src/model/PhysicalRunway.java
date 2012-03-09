package model;

public class PhysicalRunway {

	private String id;
	private Runway a, b;
	private Obstacle obstacle;
	
	private double distanceAwayFromThreshold, REZA, stopway, blastAllowance; // In meter
	private double angleOfSlope;
	private boolean closeToA;
	
	public PhysicalRunway(String identifier, Runway one, Runway two){	
		id = identifier;
		a = one;
		b = two;
	}

	public Runway getA() {
		return a;
	}

	public void setA(Runway a) {
		this.a = a;
	}

	public Runway getB() {
		return b;
	}

	public void setB(Runway b) {
		this.b = b;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void placeNewObstacle(Obstacle obstacle,
			double distanceAwayFromThreshold, String closeToRunwayName) {
		reset();
		this.obstacle = obstacle;
		this.distanceAwayFromThreshold = distanceAwayFromThreshold;
		setCloserToWhichThreshold(closeToRunwayName);
		calculateParameters();
	}
	
	private void setCloserToWhichThreshold (String closeToRunwayName){
		if(closeToRunwayName.compareTo(a.getName()) == 0){
			closeToA = true;
		}else{
			closeToA = false;
		}
		calculateParameters();
	}

	public void removeObstacleAndReset() {
		obstacle = null;
		distanceAwayFromThreshold = 0;
		reset();
	}

	private void reset() {
		angleOfSlope = 50;
		REZA = 240;
		stopway = 60;
		blastAllowance = 300;
	}
	
	public void defaultValues(){
		reset();
		calculateParameters();
	}

	private void calculateParameters() {
		calTORAtoOb();
		calTORAawayOb();
		calASDAtoOb();
		calASDAawayOb();
		calTODAtoOb();
		calTODAawayOb();
		calLDAtoOb();
		calLDAoverOb();
	}
	
	public double getDistanceAwayFromThreshold() {
		return distanceAwayFromThreshold;
	}

	public void setDistanceAwayFromThreshold(double distanceAwayFromThreshold) {
		this.distanceAwayFromThreshold = distanceAwayFromThreshold;
		calculateParameters();
	}

	public double getREZA() {
		return REZA;
	}

	public void setREZA(double REZA) {
		this.REZA = REZA;
		calculateParameters();
	}

	public double getStopway() {
		return stopway;
	}

	public void setStopway(double stopway) {
		this.stopway = stopway;
		calculateParameters();
	}

	public double getBlastAllowance() {
		return blastAllowance;
	}

	public void setBlastAllowance(double blastAllowance) {
		this.blastAllowance = blastAllowance;
		calculateParameters();
	}

	public double getAngleOfSlope() {
		return angleOfSlope;
	}

	public void setAngleOfSlope(double angleOfSlope) {
		this.angleOfSlope = angleOfSlope;
		calculateParameters();
	}
	
	private void calLDAtoOb() {
		Runway closeTo = closeToA? a : b;
		Runway awayTo = closeToA? b : a;
		awayTo.setLDA(1, awayTo.getLDA(0) - closeTo.getDisplacedThreshold(1)
				- distanceAwayFromThreshold - REZA - stopway) ;
	}

	private void calLDAoverOb() {
		Runway closeTo = closeToA? a : b;
		closeTo.setLDA(1, closeTo.getLDA(0) - distanceAwayFromThreshold
				- (obstacle.getHeight() * angleOfSlope) - stopway);
	}

	private void calTORAawayOb() {
		Runway closeTo = closeToA? a : b;
		closeTo.setTORA(1, closeTo.getTORA(0) - distanceAwayFromThreshold - blastAllowance
				- closeTo.getDisplacedThreshold(1));
	}

	private void calTORAtoOb() {
		Runway closeTo = closeToA? a : b;
		Runway awayTo = closeToA? b : a;
		awayTo.setTORA(1, awayTo.getTORA(0) - distanceAwayFromThreshold
				- (obstacle.getHeight() * angleOfSlope) - stopway
				- closeTo.getDisplacedThreshold(1));
	}

	private void calASDAawayOb() {
		Runway closeTo = closeToA? a : b;
		closeTo.setASDA(1, closeTo.getASDA(0) - distanceAwayFromThreshold - blastAllowance
				- closeTo.getDisplacedThreshold(1));
	}

	private void calASDAtoOb() {
		Runway closeTo = closeToA? a : b;
		Runway awayTo = closeToA? b : a;
		awayTo.setASDA(1, awayTo.getASDA(0) - distanceAwayFromThreshold
				- (obstacle.getHeight() * angleOfSlope) - stopway
				- closeTo.getDisplacedThreshold(1));
	}

	private void calTODAawayOb() {
		Runway closeTo = closeToA? a : b;
		closeTo.setTODA(1, closeTo.getTODA(0) - distanceAwayFromThreshold - blastAllowance
				- closeTo.getDisplacedThreshold(1));
	}

	private void calTODAtoOb() {
		Runway closeTo = closeToA? a : b;
		Runway awayTo = closeToA? b : a;
		awayTo.setTODA(1, awayTo.getTODA(0) - distanceAwayFromThreshold
				- (obstacle.getHeight() * angleOfSlope) - stopway
				- closeTo.getDisplacedThreshold(1));
	}
	
}
