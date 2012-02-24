package model;

public class RunwayWithObstacle {

	private Runway runway;
	private Obstacle obstacle;
	private double distanceAwayFromThreshold; // In meter
	private double angleOfSlope;

	public RunwayWithObstacle(Runway runway, Obstacle obstacle,
			double distanceAwayFromThreshold) {
		this.runway = runway;
		this.obstacle = obstacle;
		this.distanceAwayFromThreshold = distanceAwayFromThreshold;
		this.angleOfSlope = 50;
	}

	public RunwayWithObstacle(Runway runway, Obstacle obstacle,
			double distanceAwayFromThreshold, double angleOfSlope) {
		this.runway = runway;
		this.obstacle = obstacle;
		this.distanceAwayFromThreshold = distanceAwayFromThreshold;
		this.angleOfSlope = angleOfSlope;
	}

	public Runway getRunway() {
		return runway;
	}

	public Obstacle getObstacle() {
		return obstacle;
	}

	public double getAngleOfSlope() {
		return angleOfSlope;
	}

	public void setAngleOfSlope(double angleOfSlope) {
		this.angleOfSlope = angleOfSlope;
	}

	public double getDistanceAwayFromThreshold() {
		return distanceAwayFromThreshold;
	}

	public void setDistanceAwayFromThreshold(double distanceAwayFromThreshold) {
		this.distanceAwayFromThreshold = distanceAwayFromThreshold;
	}
	
	public double getNewTORA(){
		return 0;
	}
	
	public double getNewASDA(){
		return 0;
	}
	
	public double getNewTODA(){
		return 0;
	}
	
	public double getNewLDA(){
		return 0;
	}

	public void removeObstacle() {
		obstacle = null;
		distanceAwayFromThreshold = 0;
		angleOfSlope = 50;
	}

	public void placeNewObstacle(Obstacle obstacle,
			double distanceAwayFromThreshold) {
		this.obstacle = obstacle;
		this.distanceAwayFromThreshold = distanceAwayFromThreshold;
	}

	public void placeNewObstacle(Obstacle obstacle,
			double distanceAwayFromThreshold, double angleOfSlope) {
		this.obstacle = obstacle;
		this.distanceAwayFromThreshold = distanceAwayFromThreshold;
		this.angleOfSlope = angleOfSlope;
	}

}
