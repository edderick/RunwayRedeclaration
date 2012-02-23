package model;

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

//Creates XML document with an Airport's details
public class XMLFile {

	Document document;
	File file;
	Airport airport;

	public XMLFile(Airport a){

		this.airport = a;
		//int no = a.runways().size();
		//String root = "Airport"; // a.getName(); //<- replaced by the FINAL value in Airport Class
		
		createDocument();

		createAirportDOMTree();
		
		fileSaving();

	}

	private void createDocument() {
		
		/*
		 * DocumentBuilderFactory documentBuilderFactory =
		 * DocumentBuilderFactory.newInstance(); DocumentBuilder documentBuilder
		 * = documentBuilderFactory.newDocumentBuilder(); Document document =
		 * documentBuilder.newDocument();
		 */

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// get an instance of builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// create an instance of DOM
			document = db.newDocument();

		} catch (ParserConfigurationException pce) {
			// dump it
			System.out.println("Error while trying to instantiate DocumentBuilder " + pce);
			System.exit(1);
		}
	}

	private void createAirportDOMTree(){
		
		int no = airport.runways().size();
		
		Element rootElement = document.createElement("Airport"/*airport.getName()*/);
		document.appendChild(rootElement);
		
		// First element, the airport's name
		Element airportName = document.createElement("AirportName"/* element */);
		airportName.appendChild(document.createTextNode(airport.getName()));
		rootElement.appendChild(airportName);

		for (int i = 0; i < no; i++) {
			Runway r = (Runway) airport.runways().get(i);

			Element em = document.createElement("Runway"/* element */);

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

			rootElement.appendChild(em);
		}
	}
	
	private void fileSaving(){
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			// This is where a real application would open the file.
			// log.append("Opening: " + file.getName() + "." + newline);
		} else {
			System.out.println("Open command cancelled by user.");
		}
		// File file = new File("xmlTest.xml");
		// File file = fc.getSelectedFile();
		// FileWriter fw = new FileWriter(file);
		// fw.write(contents);

		/*
		 * JFileChooser filesave = new JFileChooser(); FileFilter filter = new
		 * FileNameExtensionFilter("Text file", "txt");
		 * filesave.addChoosableFileFilter(filter); int ret =
		 * filesave.showSaveDialog(null);
		 */

		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			StreamResult result = new StreamResult(file/* new StringWriter() / System.out*/);
			                                       // initialize w/ file object to save to file
			file.createNewFile();
			DOMSource source = new DOMSource(document);
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String xmlString = result.getWriter().toString();
		// System.out.println(xmlString);

		// new-->
		/*
		 * Transformer transformer =
		 * TransformerFactory.newInstance().newTransformer();
		 * transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		 */
	}
}
