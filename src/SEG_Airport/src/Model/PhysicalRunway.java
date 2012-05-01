package Model;

/**
 * Represents a physical lump of concrete that can be declared as a runway
 * Contains two declared runways and an optional obstacle
 * 
 * @author Oscar & Kelvin
 */
public class PhysicalRunway {

	public static int DEFAULT = 0;
	public static int REDECLARED = 1;

	private String id;
	private Runway[] runway;
	private Obstacle obstacle;
	private double runwayStripWidth, clearedAndGradedWidth;

	// Meters are used for these measurements
	private double distanceAwayFromThreshold, distanceAwayFromCenterLine;
	private double[] RESA, stopway, blastAllowance, angleOfSlope;

	private boolean closeToA;

	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((PhysicalRunway) obj).id);
	}

	/**
	 * Default constructor for Physical Runway
	 * 
	 * @param identifier
	 *            The name of the physical runway
	 * @param width
	 *            The width of the physical runway
	 * @param runwayOne
	 *            The first runway
	 * @param runwayTwo
	 *            The second runway
	 */
	public PhysicalRunway(String identifier, Runway runwayOne, Runway runwayTwo) {
		id = identifier;
		this.clearedAndGradedWidth = 75;
		this.runwayStripWidth = 150;
		runway = new Runway[2];
		runway[0] = runwayOne;
		runway[1] = runwayTwo;

		this.RESA = new double[2];
		this.RESA[DEFAULT] = 240;
		this.RESA[REDECLARED] = 240;

		this.stopway = new double[2];
		this.stopway[DEFAULT] = 60;
		this.stopway[REDECLARED] = 60;

		this.blastAllowance = new double[2];
		this.blastAllowance[DEFAULT] = 300;
		this.blastAllowance[REDECLARED] = 300;

		this.angleOfSlope = new double[2];
		this.angleOfSlope[DEFAULT] = 50;
		this.angleOfSlope[REDECLARED] = 50;
	}

	/**
	 * @param index
	 *            Which runway to return
	 * @return The runway
	 */
	public Runway getRunway(int index) {
		return runway[index];
	}

	/**
	 * @return The runway's id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            The new id for the physical runway
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return The width of the runway, cleared and graded area
	 */
	public double getClearedAndGradedWidth() {
		return clearedAndGradedWidth;
	}

	/**
	 * @param width
	 *            The new width of the runway, cleared and graded area and
	 *            runway strip
	 */
	public void setClearedAndGradedWidth(double clearedAndGradedWidth) {
		this.clearedAndGradedWidth = clearedAndGradedWidth;
	}

	/**
	 * @return The width of the runway, cleared and graded area and runway strip
	 */
	public double getRunwayStripWidth() {
		return runwayStripWidth;
	}

	/**
	 * @param width
	 *            The new width of the runway, cleared and graded area and
	 *            runway strip
	 */
	public void setRunwayStripWidth(double runwayStripWidth) {
		this.runwayStripWidth = runwayStripWidth;
	}

	/**
	 * @param option
	 *            Runway.DEFAULT or Runway.REDECLARED
	 * @return Value of RESA
	 */
	public double getRESA(int option) {
		return this.RESA[option];
	}

	/**
	 * @param option
	 *            Runway.DEFAULT or Runway.REDECLARED
	 * @param aSDA
	 *            New value of RESA
	 */
	public void setRESA(int option, double RESA) {
		this.RESA[option] = RESA;
	}

	/**
	 * @param option
	 *            Runway.DEFAULT or Runway.REDECLARED
	 * @return Value of stopway
	 */
	public double getStopway(int option) {
		return this.stopway[option];
	}

	/**
	 * @param option
	 *            Runway.DEFAULT or Runway.REDECLARED
	 * @param aSDA
	 *            New value of stopway
	 */
	public void setStopway(int option, double stopway) {
		this.stopway[option] = stopway;
	}

	/**
	 * @param option
	 *            Runway.DEFAULT or Runway.REDECLARED
	 * @return Value of RESA
	 */
	public double getBlastAllowance(int option) {
		return this.blastAllowance[option];
	}

	/**
	 * @param option
	 *            Runway.DEFAULT or Runway.REDECLARED
	 * @param aSDA
	 *            New value of RESA
	 */
	public void setBlastAllowance(int option, double blastAllowance) {
		this.blastAllowance[option] = blastAllowance;
	}

	/**
	 * @param option
	 *            Runway.DEFAULT or Runway.REDECLARED
	 * @return Value of angle of slope
	 */
	public double getAngleOfSlope(int option) {
		return this.angleOfSlope[option];
	}

	/**
	 * @param option
	 *            Runway.DEFAULT or Runway.REDECLARED
	 * @param angleOfSlope
	 *            New value for angle of slope
	 */
	public void setAngleOfSlope(int option, double angleOfSlope) {
		this.angleOfSlope[option] = angleOfSlope;
	}

	/**
	 * Places a new obstacle on the runway and performs the required
	 * calculations
	 * 
	 * @param obstacle
	 *            The obstacle to place
	 * @param distanceAwayFromThreshold
	 *            The distance the obstacle is from the end of the runway
	 * @param closeToRunwayName
	 *            Which end the distance is measured from
	 */
	public void placeNewObstacle(Obstacle obstacle,
			double distanceAwayFromThreshold,
			double distanceAwayFromCenterLine, String closeToRunwayName) {
		reset();
		this.obstacle = obstacle;
		this.distanceAwayFromThreshold = distanceAwayFromThreshold;
		this.distanceAwayFromCenterLine = distanceAwayFromCenterLine;
		setCloserToWhichThreshold(closeToRunwayName);
		calculateParameters();
	}

	public Obstacle getObstacle() {
		return obstacle;
	}

	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}

	/**
	 * @return The distance between the obstacle and the center line of the
	 *         physical runway
	 */
	public double getDistanceAwayFromCenterLine() {
		return distanceAwayFromCenterLine;
	}

	/**
	 * @param id
	 *            The new distance between the obstacle and the center line of
	 *            the physical runway
	 */
	public void setDistanceAwayFromCenterLine(double distanceAwayFromCenterLine) {
		this.distanceAwayFromCenterLine = distanceAwayFromCenterLine;
	}

	/**
	 * Specified which end the obstacle's distance is measured from
	 * 
	 * @param closeToRunwayName
	 *            The runway the obstacle is closest to
	 */
	private void setCloserToWhichThreshold(String closeToRunwayName) {
		closeToA = closeToRunwayName.equals(runway[0].getName());
		calculateParameters();
	}

	/**
	 * @return The runway with the threshold the object is measured from
	 */
	public Runway closeTo() {
		return closeToA ? runway[0] : runway[1];
	}

	/**
	 * Removed the obstacle from the runway and reset values
	 */
	public void removeObstacleAndReset() {
		obstacle = null;
		distanceAwayFromThreshold = 0;
		reset();
	}

	/**
	 * Resets the runway numbers to their defaults
	 */
	private void reset() {
		setAngleOfSlope(REDECLARED, angleOfSlope[DEFAULT]);
		setRESA(REDECLARED, RESA[DEFAULT]);
		setStopway(REDECLARED, stopway[DEFAULT]);
		setBlastAllowance(REDECLARED, blastAllowance[DEFAULT]);
	}

	/**
	 * Reset the runway parameters to the defaults
	 */
	public void defaultValues() {
		reset();
		calculateParameters();
	}

	/**
	 * Calculated the redclared runway parameter
	 */
	public void calculateParameters() {
		Runway closeTo = closeToA ? runway[0] : runway[1];
		Runway awayFrom = closeToA ? runway[1] : runway[0];

		calculateTORATowardsObstacle(closeTo, awayFrom);
		calculateTORAAwayFromObstacle(closeTo);
		calculateASDATowardsObstacle(closeTo, awayFrom);
		calculateASDAAwayFromObstacle(closeTo);
		calculateTODATowardsObstacle(closeTo, awayFrom);
		calculateTODAAwayFromObstacle(closeTo);
		calculateLDATowardsObstacle(closeTo, awayFrom);
		calculateLDAOverObstacle(closeTo);
	}

	/**
	 * @return The distance that the obstalce is from the threshold
	 */
	public double getDistanceAwayFromThreshold() {
		return distanceAwayFromThreshold;
	}

	/**
	 * @param distanceAwayFromThreshold
	 *            The distance that the obstacle is from the threshold
	 */
	public void setDistanceAwayFromThreshold(double distanceAwayFromThreshold) {
		this.distanceAwayFromThreshold = distanceAwayFromThreshold;
		calculateParameters();
	}

	/**
	 * Produces the calculations as a human readable text
	 * 
	 * @param runwayName
	 *            name of runway to perform calculation for
	 * @return Text of calculations
	 */
	public String toCalculation(String runwayName) {
		StringBuilder result = new StringBuilder();
		Runway closeTo = closeToA ? runway[0] : runway[1];
		Runway awayFrom = closeToA ? runway[1] : runway[0];

		if (runwayName.equals(closeTo.getName())) {
			result.append("New TORA : " + closeTo.getTORA(Runway.DEFAULT)
					+ " - " + distanceAwayFromThreshold + " - "
					+ blastAllowance + " - "
					+ closeTo.getDisplacedThreshold(Runway.REDECLARED) + " = "
					+ closeTo.getTORA(Runway.REDECLARED) + "\n");
			result.append("New ASDA : " + closeTo.getASDA(Runway.DEFAULT)
					+ " - " + distanceAwayFromThreshold + " - "
					+ blastAllowance + " - "
					+ closeTo.getDisplacedThreshold(Runway.REDECLARED) + " = "
					+ closeTo.getASDA(Runway.REDECLARED) + "\n");
			result.append("New TODA : " + closeTo.getTODA(Runway.DEFAULT)
					+ " - " + distanceAwayFromThreshold + " - "
					+ blastAllowance + " - "
					+ closeTo.getDisplacedThreshold(Runway.REDECLARED) + " = "
					+ closeTo.getTODA(Runway.REDECLARED) + "\n");
			result.append("New LDA : " + closeTo.getLDA(Runway.DEFAULT) + " - "
					+ distanceAwayFromThreshold + " - (" + obstacle.getHeight()
					+ " * " + angleOfSlope[REDECLARED] + ") - " + stopway
					+ ") = " + closeTo.getLDA(Runway.REDECLARED) + "\n");

		} else {
			result.append("New TORA : " + awayFrom.getTORA(Runway.DEFAULT)
					+ " - " + distanceAwayFromThreshold + " - ("
					+ obstacle.getHeight() + " * " + angleOfSlope[REDECLARED]
					+ ") - " + stopway + " - "
					+ closeTo.getDisplacedThreshold(Runway.REDECLARED) + " = "
					+ awayFrom.getTORA(Runway.REDECLARED) + "\n");
			result.append("New ASDA : " + awayFrom.getASDA(Runway.DEFAULT)
					+ " - " + distanceAwayFromThreshold + " - ("
					+ obstacle.getHeight() + " * " + angleOfSlope[REDECLARED]
					+ ") - " + stopway + " - "
					+ closeTo.getDisplacedThreshold(Runway.REDECLARED) + " = "
					+ awayFrom.getASDA(Runway.REDECLARED) + "\n");
			result.append("New TODA : " + awayFrom.getTODA(Runway.DEFAULT)
					+ " - " + distanceAwayFromThreshold + " - ("
					+ obstacle.getHeight() + " * " + angleOfSlope[REDECLARED]
					+ ") - " + stopway + " - "
					+ closeTo.getDisplacedThreshold(Runway.REDECLARED) + " = "
					+ awayFrom.getTODA(Runway.REDECLARED) + "\n");
			result.append("New LDA : " + awayFrom.getLDA(Runway.DEFAULT)
					+ " - " + closeTo.getDisplacedThreshold(Runway.REDECLARED)
					+ " - " + distanceAwayFromThreshold + " - " + RESA + " - "
					+ stopway + " = " + awayFrom.getLDA(Runway.REDECLARED)
					+ "\n");
		}
		return result.toString();
	}

	/**
	 * Calculate the LDA for landing towards the obstacle
	 * 
	 * @param closeTo
	 *            The runway with threshold closer to the obstacle
	 * @param awayFrom
	 *            The runway at the other end on the same physical runway
	 */
	private void calculateLDATowardsObstacle(Runway closeTo, Runway awayFrom) {
		if (obstacle == null
				|| (distanceAwayFromCenterLine >= 0 ? distanceAwayFromCenterLine > runwayStripWidth
						: -(distanceAwayFromCenterLine) > runwayStripWidth)) {
			awayFrom.setLDA(1, awayFrom.getLDA(Runway.DEFAULT));
		} else {
			awayFrom.setLDA(
					1,
					awayFrom.getLDA(Runway.DEFAULT)
							- closeTo.getDisplacedThreshold(Runway.REDECLARED)
							- distanceAwayFromThreshold - RESA[REDECLARED]
							- stopway[REDECLARED]);
		}
	}

	/**
	 * Calculate the LDA for landing over the obstacle
	 * 
	 * @param closeTo
	 *            The runway with threshold closer to the obstacle
	 */
	private void calculateLDAOverObstacle(Runway closeTo) {
		if (obstacle == null
				|| (distanceAwayFromCenterLine >= 0 ? distanceAwayFromCenterLine > runwayStripWidth
						: -(distanceAwayFromCenterLine) > runwayStripWidth)) {
			closeTo.setLDA(1, closeTo.getLDA(Runway.DEFAULT));
		} else {
			closeTo.setLDA(1, closeTo.getLDA(Runway.DEFAULT)
					- distanceAwayFromThreshold
					- (obstacle.getHeight() * angleOfSlope[REDECLARED])
					- stopway[REDECLARED]);
		}
	}

	/**
	 * Calculate the TORA away from the obstacle
	 * 
	 * @param closeTo
	 *            The runway with threshold closer to the obstacle
	 */
	private void calculateTORAAwayFromObstacle(Runway closeTo) {
		if (obstacle == null
				|| (distanceAwayFromCenterLine >= 0 ? distanceAwayFromCenterLine > runwayStripWidth
						: -(distanceAwayFromCenterLine) > runwayStripWidth)) {
			closeTo.setTORA(1, closeTo.getTORA(Runway.DEFAULT));
		} else {
			closeTo.setTORA(1, closeTo.getTORA(Runway.DEFAULT)
					- distanceAwayFromThreshold - blastAllowance[REDECLARED]
					- closeTo.getDisplacedThreshold(Runway.REDECLARED));
		}
	}

	/**
	 * Calculate the TORA towards the obstacle
	 * 
	 * @param closeTo
	 *            The runway with threshold closer to the obstacle
	 * @param awayFrom
	 *            The runway at the other end on the same physical runway
	 */
	private void calculateTORATowardsObstacle(Runway closeTo, Runway awayFrom) {
		if (obstacle == null
				|| (distanceAwayFromCenterLine >= 0 ? distanceAwayFromCenterLine > runwayStripWidth
						: -(distanceAwayFromCenterLine) > runwayStripWidth)) {
			awayFrom.setTORA(1, awayFrom.getTORA(Runway.DEFAULT));
		} else {
			awayFrom.setTORA(
					1,
					awayFrom.getTORA(Runway.DEFAULT)
							- distanceAwayFromThreshold
							- (obstacle.getHeight() * angleOfSlope[REDECLARED])
							- stopway[REDECLARED]
							- closeTo.getDisplacedThreshold(Runway.REDECLARED));
		}
	}

	/**
	 * Calculate the ASDA away from the obstacle
	 * 
	 * @param closeTo
	 *            The runway with threshold closer to the obstacle
	 */
	private void calculateASDAAwayFromObstacle(Runway closeTo) {
		if (obstacle == null
				|| (distanceAwayFromCenterLine >= 0 ? distanceAwayFromCenterLine > runwayStripWidth
						: -(distanceAwayFromCenterLine) > runwayStripWidth)) {
			closeTo.setASDA(1, closeTo.getASDA(Runway.DEFAULT));
		} else {
			closeTo.setASDA(1, closeTo.getASDA(Runway.DEFAULT)
					- distanceAwayFromThreshold - blastAllowance[REDECLARED]
					- closeTo.getDisplacedThreshold(Runway.REDECLARED));
		}
	}

	/**
	 * Calculate the ASDA towards the obstacle
	 * 
	 * @param closeTo
	 *            The runway with threshold closer to the obstacle
	 * @param awayFrom
	 *            The runway at the other end on the same physical runway
	 */
	private void calculateASDATowardsObstacle(Runway closeTo, Runway awayFrom) {
		if (obstacle == null
				|| (distanceAwayFromCenterLine >= 0 ? distanceAwayFromCenterLine > runwayStripWidth
						: -(distanceAwayFromCenterLine) > runwayStripWidth)) {
			awayFrom.setASDA(1, awayFrom.getASDA(Runway.DEFAULT));
		} else {
			awayFrom.setASDA(
					1,
					awayFrom.getASDA(Runway.DEFAULT)
							- distanceAwayFromThreshold
							- (obstacle.getHeight() * angleOfSlope[REDECLARED])
							- stopway[REDECLARED]
							- closeTo.getDisplacedThreshold(Runway.REDECLARED));
		}
	}

	/**
	 * Calculate the TODA away from the obstacle
	 * 
	 * @param closeTo
	 *            The runway with threshold closer to the obstacle
	 */
	private void calculateTODAAwayFromObstacle(Runway closeTo) {
		if (obstacle == null
				|| (distanceAwayFromCenterLine >= 0 ? distanceAwayFromCenterLine > runwayStripWidth
						: -(distanceAwayFromCenterLine) > runwayStripWidth)) {
			closeTo.setTODA(1, closeTo.getTODA(Runway.DEFAULT));
		} else {
			closeTo.setTODA(1, closeTo.getTODA(Runway.DEFAULT)
					- distanceAwayFromThreshold - blastAllowance[REDECLARED]
					- closeTo.getDisplacedThreshold(Runway.REDECLARED));
		}
	}

	/**
	 * Calculate the TODA towards the obstacle
	 * 
	 * @param closeTo
	 *            The runway with threshold closer to the obstacle
	 * @param awayFrom
	 *            The runway at the other end on the same physical runway
	 */
	private void calculateTODATowardsObstacle(Runway closeTo, Runway awayFrom) {
		if (obstacle == null
				|| (distanceAwayFromCenterLine >= 0 ? distanceAwayFromCenterLine > runwayStripWidth
						: -(distanceAwayFromCenterLine) > runwayStripWidth)) {
			awayFrom.setTODA(1, awayFrom.getTODA(Runway.DEFAULT));
		} else {
			awayFrom.setTODA(
					1,
					awayFrom.getTODA(Runway.DEFAULT)
							- distanceAwayFromThreshold
							- (obstacle.getHeight() * angleOfSlope[REDECLARED])
							- stopway[REDECLARED]
							- closeTo.getDisplacedThreshold(Runway.REDECLARED));
		}
	}

}
