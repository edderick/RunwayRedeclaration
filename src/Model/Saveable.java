package Model;

import java.io.File;

/**
 * This Interface should be implemented by any model object that
 * can be saved to an xmlfile
 * @author Edward
 */
public interface Saveable {
	
	/**
	 * Called whenever object is modified
	 */
	public void setModified();
	
	/**
	 * @return Whether or not the object has been saved
	 */
	public boolean getSaved();
	
	/**
	 * Saves the object to an xml file on disk
	 * @return 
	 */
	public File saveToXML();
	
	/**
	 * @return The type of model enitity
	 */
	public String getType();
}
