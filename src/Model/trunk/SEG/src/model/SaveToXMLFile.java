package model;

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

//This class, when instantiated and passed an airport; takes the airports runways and their
//values and creates an XML file with them. It provides a JFileChooser window to select where
//to save the file and how to name it.
public class SaveToXMLFile {

	File file;
	
	public SaveToXMLFile(Airport a) throws Exception {

		int no = a.runways().size();
		String root = "Airport";//a.getName();
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		Element rootElement = document.createElement(root);
		document.appendChild(rootElement);
		
		//First element, the airport's name
		Element airportName = document.createElement("AirportName"/* element */);
		airportName.appendChild(document.createTextNode(a.getName()));
		rootElement.appendChild(airportName);
		
		for (int i = 0; i < no; i++) {
			Runway r = (Runway) a.runways().get(i);

			//Creating runway element and appending to root element
			Element em = document.createElement("Runway");

			//Creating each of the runway's elements and appending to the runway element
			Element name = document.createElement("Name");
			name.appendChild(document.createTextNode(r.getName()));
			em.appendChild(name);

			Element tora = document.createElement("TORA");
			String to = Integer.toString(r.getTORA());
			tora.appendChild(document.createTextNode(to));
			em.appendChild(tora);

			Element asda = document.createElement("ASDA");
			String as = Integer.toString(r.getASDA());
			asda.appendChild(document.createTextNode(as));
			em.appendChild(asda);

			Element toda = document.createElement("TODA");
			String tod = Integer.toString(r.getTODA());
			toda.appendChild(document.createTextNode(tod));
			em.appendChild(toda);

			Element lda = document.createElement("LDA");
			String ld = Integer.toString(r.getLDA());
			lda.appendChild(document.createTextNode(ld));
			em.appendChild(lda);
			
			Element displacedThreshold = document.createElement("DisplacedThreshold");
			String dt = Integer.toString(r.getDisplacedThreshold());
			displacedThreshold.appendChild(document.createTextNode(dt));
			em.appendChild(displacedThreshold);

			rootElement.appendChild(em);
		}
		
		//Creating JFileChooser object and storing its return value
		JFileChooser fc = new JFileChooser();
		//XMLFileFilter ff = new XMLFileFilter();
		//fc.setFileFilter(ff);
		//fc.setFileHidingEnabled(false);//show hidden files
		int returnVal = fc.showSaveDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
    		Transformer transformer = transformerFactory.newTransformer();
    		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    		
    		StreamResult result = new StreamResult(file);
    		file.createNewFile();
    		DOMSource source = new DOMSource(document);
    		transformer.transform(source, result);
        } else {
            System.out.println("Save command cancelled by user.");
        }

		
		

	}
}
