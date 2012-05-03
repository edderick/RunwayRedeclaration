package Model;

/**
 * Represents an obstacle that can be placed on a runway
 * @author Kelvin
 */
public class Obstacle implements Saveable{

	private String name;
	private double height, width, length;
	private final double DEFAULT_WIDTH = 100;
	private final double DEFAULT_LENGTH = 100;
	
	//True when obstacle has been saved
	private boolean saved = true;
	
	/**
	 * Default constructor for an obstacle
	 * @param name Name of obstacle
	 * @param height Height of object
	 */
	public Obstacle(String name, double height) {
		this.name = (name == null? "Temp Obstacle" : name);
		this.height = (height < 0? 25 : height);
		this.width = DEFAULT_WIDTH;
	    this.length = DEFAULT_LENGTH;
	}

	/**
	 * resets the width and length to their default values
	 */
	public void resetSize(){
		width = DEFAULT_WIDTH;
		length = DEFAULT_LENGTH;
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
		this.name = (name == null? this.name : name);
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
		this.height = (height < 0? this.height : height);
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
		this.width = (width < 0? this.width : width);
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
		this.length = (length < 0? this.length : length);
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
		saved = true;
	}
	
	/**
	 * Called whenever airport is modified
	 */
	public void setModified(){
		this.saved = false;
	}
	
	/**
	 * @return Whether or not the airport has been saved
	 */
	public boolean getSaved(){
		return this.saved;
	}


}
