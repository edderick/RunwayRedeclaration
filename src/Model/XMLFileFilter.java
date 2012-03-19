package model;

import java.io.File;

import javax.swing.filechooser.FileFilter;

//Filters files shown on JFileChooser window
public class XMLFileFilter extends FileFilter {

	public boolean accept(File file) {
		
		return file.getAbsolutePath().endsWith(".xml");
	}

	
	public String getDescription() {
		
		return "XML file";
	}

}
