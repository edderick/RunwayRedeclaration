package model;

public class PhysicalRunway {

	private String id;
	private Runway a, b;
	private Obstacle obstacle;

	private double distanceAwayFromThreshold, REZA, stopway, blastAllowance; // In
																				// meter
	private double angleOfSlope;
	private boolean closeToA;

	public PhysicalRunway(String identifier, Runway one, Runway two) {
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

	private void setCloserToWhichThreshold(String closeToRunwayName) {
		if (closeToRunwayName.compareTo(a.getName()) == 0) {
			closeToA = true;
		} else {
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

	public void defaultValues() {
		reset();
		calculateParameters();
	}

	private void calculateParameters() {
		Runway closeTo = closeToA ? a : b;
		Runway awayFrom = closeToA ? b : a;

		calTORAtoOb(closeTo, awayFrom);
		calTORAawayOb(closeTo);
		calASDAtoOb(closeTo, awayFrom);
		calASDAawayOb(closeTo);
		calTODAtoOb(closeTo, awayFrom);
		calTODAawayOb(closeTo);
		calLDAtoOb(closeTo, awayFrom);
		calLDAoverOb(closeTo);
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

	public String toCalculation(String runwayName) {
		String result = "";
		Runway closeTo = closeToA ? a : b;
		Runway awayFrom = closeToA ? b : a;

		if (runwayName.compareTo(closeTo.getName()) == 0) {
			result += "New LDA : " + closeTo.getLDA(0) + " - "
					+ distanceAwayFromThreshold + " - (" + obstacle.getHeight()
					+ " * " + angleOfSlope + ") - " + stopway + ")\n";
			result += "New TORA : " + closeTo.getTORA(0) + " - "
					+ distanceAwayFromThreshold + " - " + blastAllowance
					+ " - " + closeTo.getDisplacedThreshold(1) + "\n";
			result += "New ASDA : " + closeTo.getASDA(0) + " - "
					+ distanceAwayFromThreshold + " - " + blastAllowance
					+ " - " + closeTo.getDisplacedThreshold(1) + "\n";
			result += "New TODA : " + closeTo.getTODA(0) + " - "
					+ distanceAwayFromThreshold + " - " + blastAllowance
					+ " - " + closeTo.getDisplacedThreshold(1) + "\n";

		} else {
			result += "New LDA : " + awayFrom.getLDA(0) + " - "
					+ closeTo.getDisplacedThreshold(1) + " - "
					+ distanceAwayFromThreshold + " - " + REZA + " - "
					+ stopway + "\n";
			result += "New TORA : " + awayFrom.getTORA(0) + " - "
					+ distanceAwayFromThreshold + " - (" + obstacle.getHeight()
					+ " * " + angleOfSlope + ") - " + stopway + " - "
					+ closeTo.getDisplacedThreshold(1) + "\n";
			result += "New ASDA : " + awayFrom.getASDA(0) + " - "
					+ distanceAwayFromThreshold + " - (" + obstacle.getHeight()
					+ " * " + angleOfSlope + ") - " + stopway + " - "
					+ closeTo.getDisplacedThreshold(1) + "\n";
			result += "New TODA : " + awayFrom.getTODA(0) + " - "
					+ distanceAwayFromThreshold + " - (" + obstacle.getHeight()
					+ " * " + angleOfSlope + ") - " + stopway + " - "
					+ closeTo.getDisplacedThreshold(1) + "\n";
		}

		return result;
	}

	private void calLDAtoOb(Runway closeTo, Runway awayFrom) {
		awayFrom.setLDA(1,
				awayFrom.getLDA(0) - closeTo.getDisplacedThreshold(1)
						- distanceAwayFromThreshold - REZA - stopway);
	}

	private void calLDAoverOb(Runway closeTo) {
		closeTo.setLDA(1, closeTo.getLDA(0) - distanceAwayFromThreshold
				- (obstacle.getHeight() * angleOfSlope) - stopway);
	}

	private void calTORAawayOb(Runway closeTo) {
		closeTo.setTORA(1, closeTo.getTORA(0) - distanceAwayFromThreshold
				- blastAllowance - closeTo.getDisplacedThreshold(1));
	}

	private void calTORAtoOb(Runway closeTo, Runway awayFrom) {
		awayFrom.setTORA(
				1,
				awayFrom.getTORA(0) - distanceAwayFromThreshold
						- (obstacle.getHeight() * angleOfSlope) - stopway
						- closeTo.getDisplacedThreshold(1));
	}

	private void calASDAawayOb(Runway closeTo) {
		closeTo.setASDA(1, closeTo.getASDA(0) - distanceAwayFromThreshold
				- blastAllowance - closeTo.getDisplacedThreshold(1));
	}

	private void calASDAtoOb(Runway closeTo, Runway awayFrom) {
		awayFrom.setASDA(
				1,
				awayFrom.getASDA(0) - distanceAwayFromThreshold
						- (obstacle.getHeight() * angleOfSlope) - stopway
						- closeTo.getDisplacedThreshold(1));
	}

	private void calTODAawayOb(Runway closeTo) {
		closeTo.setTODA(1, closeTo.getTODA(0) - distanceAwayFromThreshold
				- blastAllowance - closeTo.getDisplacedThreshold(1));
	}

	private void calTODAtoOb(Runway closeTo, Runway awayFrom) {
		awayFrom.setTODA(
				1,
				awayFrom.getTODA(0) - distanceAwayFromThreshold
						- (obstacle.getHeight() * angleOfSlope) - stopway
						- closeTo.getDisplacedThreshold(1));
	}

}
