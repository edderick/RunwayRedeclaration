package model;

public class RunwayWithObstacle {

	private Runway runway;
	private Obstacle obstacle;
	private double distanceAwayFromThreshold; // In meter

	public RunwayWithObstacle(Runway runway, Obstacle obstacle,
			double distanceAwayFromThreshold) {
		super();
		this.runway = runway;
		this.obstacle = obstacle;
		this.distanceAwayFromThreshold = distanceAwayFromThreshold;
	}

	public Runway getRunway() {
		return runway;
	}

	public void setRunway(Runway runway) {
		this.runway = runway;
	}

	public Obstacle getObstacle() {
		return obstacle;
	}

	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}

	public double getDistanceAwayFromThreshold() {
		return distanceAwayFromThreshold;
	}

	public void setDistanceAwayFromThreshold(double distanceAwayFromThreshold) {
		this.distanceAwayFromThreshold = distanceAwayFromThreshold;
	}

}
