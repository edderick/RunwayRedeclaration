package Model;

/**
 * Represents an obstacle that can be placed on a runway
 * @author Edward
 */
public class Obstacle {

	private String name;
	private String sizeType; // Small ; Medium ; Large
	private double height, width, length;

	/**
	 * Default constructor for an obstacle
	 * @param name Name of obstacle
	 * @param sizeType The size category that it fits in
	 * @param height Height of object
	 * @param width Width of object
	 * @param length Length of object
	 */
	public Obstacle(String name, String sizeType, double height, double width, double length) {
		this.name = name;
		this.sizeType = sizeType;
		this.height = height;
		this.width = width;
		this.length = length;
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
	 * @return The size type of the obstacle
	 */
	public String getSizeType() {
		return sizeType;
	}

	/**
	 * @param sizeType The new size type of the obstacle
	 */
	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
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
