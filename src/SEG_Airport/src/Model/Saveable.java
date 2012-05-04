package Model;

public interface Saveable {

	public void saveToXML();
	/**
	 * Called whenever airport is modified
	 */
	public void setModified();
	
	/**
	 * @return Whether or not the airport has been saved
	 */
	public boolean getSaved();
	
}
