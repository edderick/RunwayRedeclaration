package model;

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

//Creates XML document with an Airport's details
public class XMLFile {

	Document document;
	File file;

	public XMLFile() {

	}

	public XMLFile(Airport a) {
		createXMLFile();
		createAirportDOMTree(a);
		fileSaving();

		// loadDocument();
	}

	private void createXMLFile() {

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
			System.out
					.println("Error while trying to instantiate DocumentBuilder "
							+ pce);
			System.exit(1);
		}
	}

	private void createAirportDOMTree(Airport airport) {

		int no = airport.runways().size();
		// String root = "Airport"; // a.getName(); //<- replaced by the FINAL
		// value in Airport Class

		Element rootElement = document
				.createElement("Airport"/* airport.getName() */);
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

	private void fileSaving() {
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
			StreamResult result = new StreamResult(file/*
														 * new StringWriter() /
														 * System.out
														 */);
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

	private void loadXMLFile() {
		JFileChooser fc = new JFileChooser();
		class MyFilter extends javax.swing.filechooser.FileFilter {
			public boolean accept(File file) {
				String filename = file.getName();
				return filename.endsWith(".xml");
			}

			public String getDescription() {
				return "*.xml";
			}
		}
		fc.addChoosableFileFilter(new MyFilter());
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();

			// get the factory
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			try {
				// Using factory get an instance of document builder
				DocumentBuilder db = dbf.newDocumentBuilder();

				// parse using builder to get DOM representation of the XML file
				// System.out.println(file.getName());
				document = db.parse(file.getAbsoluteFile());

			} catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (SAXException se) {
				se.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		} else {
			System.out.println("Open command cancelled by user.");
		}
	}

	public Airport loadAirport() {
		loadXMLFile();
		
		// get the root element
		Element docEle = document.getDocumentElement();
		Airport airport = null;
		try {
			airport = new Airport(getStringValue(docEle, "AirportName"));
			// get a nodelist of elements
			NodeList nl = docEle.getElementsByTagName("Runway");
			if (nl != null && nl.getLength() > 0) {
				for (int i = 0; i < nl.getLength(); i++) {

					// get the runway element
					Element el = (Element) nl.item(i);

					// get the runway object
					Runway r = getRunway(el);

					// add it to list
					airport.addRunway(r);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return airport;
	}

	private Runway getRunway(Element empEl) {

		// for each <Runway> element get text or int values
		String name = getStringValue(empEl, "Name");
		int tora = getIntValue(empEl, "TORA");
		int asda = getIntValue(empEl, "ASDA");
		int toda = getIntValue(empEl, "TODA");
		int lda = getIntValue(empEl, "LDA");

		// Create a new runway with the value read from the xml nodes
		Runway r = new Runway(name, tora, asda, toda, lda);
		
		return r;
	}

	private String getStringValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}
		return textVal;
	}

	private int getIntValue(Element ele, String tagName) {
		return Integer.parseInt(getStringValue(ele, tagName));
	}
}
