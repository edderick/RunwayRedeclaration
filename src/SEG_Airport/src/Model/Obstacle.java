package Model;

/**
 * Represents an obstacle that can be placed on a runway
 * @author Kelvin
 */
public class Obstacle {

	private String name;
	private double height, width, length;

	/**
	 * Default constructor for an obstacle
	 * @param name Name of obstacle
	 * @param height Height of object
	 */
	public Obstacle(String name, double height) {
		this.name = name;
		this.height = height;
		this.width = 300;
	    this.length = 1000;
	}

	/**
	 * @return The obstacles name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The obstacles new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return The height of the obstacle
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height The new height of the obstacle
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * @return The width of the obstacle
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @param width The new width of the obstacle
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * @return The length of the obstacle
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @param length The new height of the obstacle
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * Saves the obstacle to an xml file on disk
	 */
	public void saveToXML(){

		try {
			@SuppressWarnings("unused")
			SaveToXMLFile xmlFile = new SaveToXMLFile(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
