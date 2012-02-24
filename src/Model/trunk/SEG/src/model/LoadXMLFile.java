package model;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class LoadXMLFile {

	File fXmlFile;
	Airport arpt = null;

	public LoadXMLFile() {
		// this.loadFile();
	}

	public Airport loadFile() throws Exception {

		JFileChooser fc = new JFileChooser();
		XMLFileFilter ff = new XMLFileFilter();
		fc.setFileFilter(ff);
		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fXmlFile = fc.getSelectedFile();

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			String root = doc.getDocumentElement().getNodeName();
			// can add an if statement here to make sure its the right kind of file

			// Creating an Airport object using the name from the xml file
			NodeList airportName = doc.getElementsByTagName("AirportName");
			Node n = airportName.item(0);
			Element e = (Element) n;
			String an = e.getTextContent();
			arpt = new Airport(an);
			// System.out.println(arpt.getName());

			NodeList nList = doc.getElementsByTagName("Runway");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String name = getTagValue("Name", eElement);
					int tora = Integer.parseInt(getTagValue("TORA", eElement));
					int asda = Integer.parseInt(getTagValue("ASDA", eElement));
					int toda = Integer.parseInt(getTagValue("TODA", eElement));
					int lda = Integer.parseInt(getTagValue("LDA", eElement));

					Runway r = new Runway(name, tora, asda, toda, lda);
					arpt.addRunway(r);
				}
			}
		} else {
			System.out.println("Open command cancelled by user.");
		}

		return arpt;
	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

}
