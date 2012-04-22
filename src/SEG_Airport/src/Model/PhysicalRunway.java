package Model;

/**
 * Represents a physical lump of concrete that can be declared as a runway
 * Contains two declared runways and an optional obstacle
 * @author Oscar & Kelvin
 */
public class PhysicalRunway {

	private String id;
	private Runway[] runway;
	private Obstacle obstacle;

	//Meters are used for these measurements 
	private double distanceAwayFromThreshold, RESA, stopway, blastAllowance; 
																				
	private double angleOfSlope;
	private boolean closeToA;

	/**
	 * Default constructor for Physical Runway
	 * @param identifier The name of the physical runway
	 * @param runwayOne The first runway
	 * @param runwayTwo The second runway
	 */
	public PhysicalRunway(String identifier, Runway runwayOne, Runway runwayTwo) {
		id = identifier;
		runway = new Runway[2];
		runway[0] = runwayOne;
		runway[1] = runwayTwo;
	}

	/**
	 * @param index Which runway to return
	 * @return The runway
	 */
    public Runway getRunway(int index){
    	return runway[index];
    }

    /**
     * @return The runway's id
     */
	public String getId() {
		return id;
	}

	/**
	 * @param id The new id for the physical runway
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Places a new obstacle on the runway and performs the required calculations
	 * @param obstacle The obstacle to place
	 * @param distanceAwayFromThreshold The distance the obstacle is from the end of the runway
	 * @param closeToRunwayName Which end the distance is measured from
	 */
	public void placeNewObstacle(Obstacle obstacle,
			double distanceAwayFromThreshold, String closeToRunwayName) {
		reset();
		this.obstacle = obstacle;
		this.distanceAwayFromThreshold = distanceAwayFromThreshold;
		setCloserToWhichThreshold(closeToRunwayName);
		calculateParameters();
	}

	/**
	 * Specified which end the obstacle's distance is measured from
	 * @param closeToRunwayName The runway the obstacle is closest to
	 */
	private void setCloserToWhichThreshold(String closeToRunwayName) {
		closeToA = closeToRunwayName.equals(runway[0].getName());
		calculateParameters();
	}
	
	/**
	 * @return The runway with the threshold the object is measured from
	 */
	public Runway closeTo(){
		return closeToA? runway[0] : runway[1];
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
		angleOfSlope = 50;
		RESA = 240;
		stopway = 60;
		blastAllowance = 300;
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
	private void calculateParameters() {
		Runway closeTo = closeToA ? runway[0] : runway[1];
		Runway awayFrom = closeToA ? runway[1] : runway[0];

		calTORAtoOb(closeTo, awayFrom);
		calTORAawayOb(closeTo);
		calASDAtoOb(closeTo, awayFrom);
		calASDAawayOb(closeTo);
		calTODAtoOb(closeTo, awayFrom);
		calTODAawayOb(closeTo);
		calLDAtoOb(closeTo, awayFrom);
		calLDAoverOb(closeTo);
	}

	/**
	 * @return The distance that the obstalce is from the threshold
	 */
	public double getDistanceAwayFromThreshold() {
		return distanceAwayFromThreshold;
	}

	/**
	 * @param distanceAwayFromThreshold The distance that the obstacle is from the threshold
	 */
	public void setDistanceAwayFromThreshold(double distanceAwayFromThreshold) {
		this.distanceAwayFromThreshold = distanceAwayFromThreshold;
		calculateParameters();
	}

	/**
	 * @return The runway end safety area in metres 
	 */
	public double getRESA() {
		return RESA;
	}

	/**
	 * @param RESA The new value of the runway end safety area in metres
	 */
	public void setRESA(double RESA) {
		this.RESA = RESA;
		calculateParameters();
	}

	/**
	 * @return The length of the stopway in metres
	 */
	public double getStopway() {
		return stopway;
	}

	/**
	 * @param stopway The new length of the stopway in metres
	 */
	public void setStopway(double stopway) {
		this.stopway = stopway;
		calculateParameters();
	}

	/**
	 * @return The blast allowance in metres
	 */
	public double getBlastAllowance() {
		return blastAllowance;
	}
	
	/**
	 * @param blastAllowance The new blast allowance in metres
	 */
	public void setBlastAllowance(double blastAllowance) {
		this.blastAllowance = blastAllowance;
		calculateParameters();
	}

	/** 
	 * @return The angle of Slope required
	 */
	public double getAngleOfSlope() {
		return angleOfSlope;
	}

	/**
	 * @param angleOfSlope The new angle of slope required
	 */
	public void setAngleOfSlope(double angleOfSlope) {
		this.angleOfSlope = angleOfSlope;
		calculateParameters();
	}

	/**
	 * Produces the calculations as a human readable text
	 * @param runwayName name of runway to perform calculation for
	 * @return Text of calculations
	 */
	public String toCalculation(String runwayName) {
		StringBuilder result = new StringBuilder();
		Runway closeTo = closeToA ? runway[0] : runway[1];
		Runway awayFrom = closeToA ? runway[1] : runway[0];

		if (runwayName.equals(closeTo.getName())) {
			result.append("New TORA : " + closeTo.getTORA(0) + " - "
					+ distanceAwayFromThreshold + " - " + blastAllowance
					+ " - " + closeTo.getDisplacedThreshold(1) + " = "
					+ closeTo.getTORA(1) + "\n");
			result.append("New ASDA : " + closeTo.getASDA(0) + " - "
					+ distanceAwayFromThreshold + " - " + blastAllowance
					+ " - " + closeTo.getDisplacedThreshold(1) + " = "
					+ closeTo.getASDA(1) + "\n");
			result.append("New TODA : " + closeTo.getTODA(0) + " - "
					+ distanceAwayFromThreshold + " - " + blastAllowance
					+ " - " + closeTo.getDisplacedThreshold(1) + " = "
					+ closeTo.getTODA(1) + "\n");
			result.append("New LDA : " + closeTo.getLDA(0) + " - "
					+ distanceAwayFromThreshold + " - (" + obstacle.getHeight()
					+ " * " + angleOfSlope + ") - " + stopway + ") = "
					+ closeTo.getLDA(1) + "\n");

		} else {
			result.append("New TORA : " + awayFrom.getTORA(0) + " - "
					+ distanceAwayFromThreshold + " - (" + obstacle.getHeight()
					+ " * " + angleOfSlope + ") - " + stopway + " - "
					+ closeTo.getDisplacedThreshold(1) + " = "
					+ awayFrom.getTORA(1) + "\n");
			result.append("New ASDA : " + awayFrom.getASDA(0) + " - "
					+ distanceAwayFromThreshold + " - (" + obstacle.getHeight()
					+ " * " + angleOfSlope + ") - " + stopway + " - "
					+ closeTo.getDisplacedThreshold(1) + " = "
					+ awayFrom.getASDA(1) + "\n");
			result.append("New TODA : " + awayFrom.getTODA(0) + " - "
					+ distanceAwayFromThreshold + " - (" + obstacle.getHeight()
					+ " * " + angleOfSlope + ") - " + stopway + " - "
					+ closeTo.getDisplacedThreshold(1) + " = "
					+ awayFrom.getTODA(1) + "\n");
			result.append("New LDA : " + awayFrom.getLDA(0) + " - "
					+ closeTo.getDisplacedThreshold(1) + " - "
					+ distanceAwayFromThreshold + " - " + RESA + " - "
					+ stopway + " = " + awayFrom.getLDA(1) + "\n");
		}
		return result.toString();
	}

	//TODO: Javadoc these methods, and maybe give them clearer names
	
	
	private void calLDAtoOb(Runway closeTo, Runway awayFrom) {
		awayFrom.setLDA(1,
				awayFrom.getLDA(0) - closeTo.getDisplacedThreshold(1)
						- distanceAwayFromThreshold - RESA - stopway);
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
