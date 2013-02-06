package Model;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Filters files shown on JFileChooser window
 * @author Oscar
 */
public class XMLFileFilter extends FileFilter {
	
	/**
	 * @return Whether the given file is accepted by this filter.
	 */
	public boolean accept(File file) {
		return file.getAbsolutePath().endsWith(".xml");
	}

	/**
	 * The description of this filter: XML file. 
	 */
	public String getDescription() {
		return "XML file";
	}

}
