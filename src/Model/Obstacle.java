package model;

public class Obstacle {

	private String name;
	private String type; // Aircraft ; Vehicle ; etc...
	private String sizeType; // Small ; Medium ; Large
	private double height;

	public Obstacle(String name, String type, String sizeType, double height) {
		this.name = name;
		this.type = type;
		this.sizeType = sizeType;
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSizeType() {
		return sizeType;
	}

	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

}
