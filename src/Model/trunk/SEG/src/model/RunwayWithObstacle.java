package model;

public class RunwayWithObstacle {

	private Runway runway; // With left and right end?
	private Obstacle obstacle;
	private double distanceAwayFromThreshold, REZA, stopway, blastAllowance; // In meter
	private double angleOfSlope;

	// private boolean closeToLeft;

	private double TORAtoOb, TORAawayOb, ASDAtoOb, ASDAawayOb, TODAtoOb,
			TODAawayOb, LDAtoOb, LDAoverOb;

	public RunwayWithObstacle(Runway runway) {
		this.runway = runway;
		removeObstacleAndReset();
	}

	public void placeNewObstacle(Obstacle obstacle,
			double distanceAwayFromThreshold) {
		reset();
		this.obstacle = obstacle;
		this.distanceAwayFromThreshold = distanceAwayFromThreshold;

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
		TORAtoOb = 0;
		TORAawayOb = 0;
		ASDAtoOb = 0;
		ASDAawayOb = 0;
		TODAtoOb = 0;
		TODAawayOb = 0;
		LDAtoOb = 0;
		LDAoverOb = 0;
	}

	private void calculateParameters() {
		TORAtoOb = calTORAtoOb();
		TORAawayOb = calTORAawayOb();
		ASDAtoOb = calASDAtoOb();
		ASDAawayOb = calASDAawayOb();
		TODAtoOb = calTODAtoOb();
		TODAawayOb = calTODAawayOb();
		LDAtoOb = calLDAtoOb();
		LDAoverOb = calLDAoverOb();
	}

	public Runway getRunway() {
		return runway;
	}

	public Obstacle getObstacle() {
		return obstacle;
	}

	public double getDistanceAwayFromThreshold() {
		return distanceAwayFromThreshold;
	}

	public void setDistanceAwayFromThreshold(double distanceAwayFromThreshold) {
		this.distanceAwayFromThreshold = distanceAwayFromThreshold;
	}

	public double getREZA() {
		return REZA;
	}

	public void setREZA(double REZA) {
		this.REZA = REZA;
	}

	public double getStopway() {
		return stopway;
	}

	public void setStopway(double stopway) {
		this.stopway = stopway;
	}

	public double getBlastAllowance() {
		return blastAllowance;
	}

	public void setBlastAllowance(double blastAllowance) {
		this.blastAllowance = blastAllowance;
	}

	public double getAngleOfSlope() {
		return angleOfSlope;
	}

	public void setAngleOfSlope(double angleOfSlope) {
		this.angleOfSlope = angleOfSlope;
	}

	public double getTORAtoOb() {
		return TORAtoOb;
	}

	public double getTORAawayOb() {
		return TORAawayOb;
	}

	public double getASDAtoOb() {
		return ASDAtoOb;
	}

	public double getASDAawayOb() {
		return ASDAawayOb;
	}

	public double getTODAtoOb() {
		return TODAtoOb;
	}

	public double getTODAawayOb() {
		return TODAawayOb;
	}

	public double getLDAtoOb() {
		return LDAtoOb;
	}

	public double getLDAoverOb() {
		return LDAoverOb;
	}

	private double calLDAtoOb() {
		return runway.getLDA() - runway.getDisplacedThreshold()
				- distanceAwayFromThreshold - REZA - stopway;
	}

	private double calLDAoverOb() {
		return runway.getLDA() - distanceAwayFromThreshold
				- (obstacle.getHeight() * angleOfSlope) - stopway;
	}

	private double calTORAawayOb() {
		return runway.getTORA() - distanceAwayFromThreshold - blastAllowance
				- -runway.getDisplacedThreshold();
	}

	private double calTORAtoOb() {
		return runway.getTORA() - distanceAwayFromThreshold
				- (obstacle.getHeight() * angleOfSlope) - stopway
				- runway.getDisplacedThreshold();
	}

	private double calASDAawayOb() {
		return runway.getASDA() - distanceAwayFromThreshold - blastAllowance
				- -runway.getDisplacedThreshold();
	}

	private double calASDAtoOb() {
		return runway.getASDA() - distanceAwayFromThreshold
				- (obstacle.getHeight() * angleOfSlope) - stopway
				- runway.getDisplacedThreshold();

	}

	private double calTODAawayOb() {
		return runway.getTODA() - distanceAwayFromThreshold - blastAllowance
				- runway.getDisplacedThreshold();
	}

	private double calTODAtoOb() {
		return runway.getTODA() - distanceAwayFromThreshold
				- (obstacle.getHeight() * angleOfSlope) - stopway
				- runway.getDisplacedThreshold();
	}
}
