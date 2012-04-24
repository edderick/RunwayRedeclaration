
//=================================================

//Sorry about this ugly comment block!

//Do we need to cast the nodes to elements?

//Would the line: 
// String obstacleName = doc.getElementsByTagName("Obstacle_Name").item(0).getTextContent();
// do the same thing?

// Also, I am personally (I admit this is a matter of opinion), 
// not a fan of shortened variable names e.g "len" vs. "length"

//Finally - See last method, it needs a javadoc, and I don't know what it is doing :p

//==================================================


package Model;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

/**
 * Class to load an object from an XML file
 * @author Oscar
 */
public class LoadXMLFile {

	File fXmlFile;
	Airport arpt = null;
	Obstacle obst = null;
	ArrayList<Runway> rways;
	int index = 0; //used to iterate over runways

	/**
	 * TODO: this
	 */
	public LoadXMLFile() {
		// this.loadFile();
	}

	/**
	 * @return Obstacle that was contained in XML
	 * @throws Exception Exceptions to do with reading files and parsing XML
	 */
	public Obstacle loadObstacle() throws Exception{

		JFileChooser fc = new JFileChooser();
		XMLFileFilter ff = new XMLFileFilter();
		fc.setFileFilter(ff);
		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fXmlFile = fc.getSelectedFile();

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			//String root = doc.getDocumentElement().getNodeName();
			// can add an if statement here to make sure its the right kind of file

			// Creating an Obstacle object using the name from the xml file
			NodeList airportName = doc.getElementsByTagName("Obstacle_Name");
			Node n = airportName.item(0);
			Element e = (Element) n;
			String on = e.getTextContent();//Obstacle name (string)

			NodeList sizeType = doc.getElementsByTagName("Size_Type");
			Node st = sizeType.item(0);
			Element e1 = (Element) st;
			String type = e1.getTextContent();//Obstacle type (string)

			NodeList height = doc.getElementsByTagName("Height");
			Node ht = height.item(0);
			Element e2 = (Element) ht;
			Double hei = Double.parseDouble(e2.getTextContent());

			NodeList width = doc.getElementsByTagName("Width");
			Node wt = width.item(0);
			Element e3 = (Element) wt;
			Double wid = Double.parseDouble(e3.getTextContent());

			NodeList length = doc.getElementsByTagName("Length");
			Node lt = length.item(0);
			Element e4 = (Element) lt;
			Double len = Double.parseDouble(e4.getTextContent());


			obst = new Obstacle(on, type, hei, wid, len);
			// System.out.println(arpt.getName());

		} else {
			System.out.println("Open command cancelled by user.");
		}

		return obst;
	}

	/**
	 * @return Airport that was contained in XML
	 * @throws Exception Exceptions to do with reading files and parsing XML
	 */
	public Airport loadAirport() throws Exception {

		rways = new ArrayList<Runway>();

		JFileChooser fc = new JFileChooser();
		XMLFileFilter ff = new XMLFileFilter();
		fc.setFileFilter(ff);
		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fXmlFile = fc.getSelectedFile();

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			//String root = doc.getDocumentElement().getNodeName(); This line seems unneeded
			// can add an if statement here to make sure its the right kind of file

			// Creating an Airport object using the name from the xml file
			NodeList airportName = doc.getElementsByTagName("Airport_Name");
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

					String name = getTagValue("RunwayName", eElement);
					double tora = Double.parseDouble(getTagValue("TORA", eElement));//Integer.parseInt(getTagValue("TORA", eElement));
					double asda = Double.parseDouble(getTagValue("ASDA", eElement));
					double toda = Double.parseDouble(getTagValue("TODA", eElement));
					double lda = Double.parseDouble(getTagValue("LDA", eElement));
					double dt = Double.parseDouble(getTagValue("DisplacedThreshold", eElement));

					Runway r = new Runway(name, tora, asda, toda, lda, dt);
					rways.add(r);//arpt.addRunway(r);
				}
			}

			NodeList physicalRun = doc.getElementsByTagName("PhysicalRunway");
			for (int temp = 0; temp < physicalRun.getLength(); temp++){
				Node nNode = physicalRun.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String name = getTagValue("Name", eElement);
					PhysicalRunway pr = new PhysicalRunway(name, rways.get(index), rways.get(index+1));
					arpt.addPhysicalRunway(pr);
					index = index + 2;
				}


			}

		} else {
			//			System.out.println("Open command cancelled by user.");
		}

		return arpt;
	}

	/**
	 * TODO: Javadoc me!
	 */
	private static String getTagValue(String sTag, Element eElement) {

		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();

	}
	
	public String getFilename(){
		return fXmlFile.getPath();
	}

}
