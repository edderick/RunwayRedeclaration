package Model;

import java.io.*;

import javax.swing.JFileChooser;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

/**This class, when instantiated and passed an airport; takes the airports runways and their
 * values and creates an XML file with them. It provides a JFileChooser window to select where
 * to save the file and how to name it. It also works with obstacles.
 * @author Oscar
 */
public class SaveToXMLFile {

	private File file;
	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document document;
	private Element rootElement;


	/**
	 * Constructor for Airport
	 * @param airport The airport to save
	 * @throws Exception Relating to writing files or generating xml
	 * TODO: Throw only relevant exceptions. 
	 */
	public SaveToXMLFile(Airport airport) throws Exception {
		String root = "Airport";
		this.createDocBuilderFactory(root);

		//First element, the airport's name
		Element airportName = document.createElement("Airport_Name");
		airportName.appendChild(document.createTextNode(airport.getName()));
		rootElement.appendChild(airportName);

		this.addNodesAndElements(airport);

		//Creating JFileChooser object and storing its return value
		this.createFChooserAndStore();
	}

	/**
	 * Constructor for obstacle
	 * @param obstacle The obstacle to save
	 * @throws Exception Relating to reading files or generating xml
	 */
	public SaveToXMLFile(Obstacle obstacle) throws Exception{

		String root = "Obstacle";
		this.createDocBuilderFactory(root);

		//First element, the obstacle's name
		Element obstacleName = document.createElement("Obstacle_Name");
		obstacleName.appendChild(document.createTextNode(obstacle.getName()));
		rootElement.appendChild(obstacleName);

		this.addNodesAndElementsObstacle(obstacle);

		//Creating JFileChooser object and storing its return value
		this.createFChooserAndStore();
	}

	/**
	 * Creates DocumentBuilderFactory using string for root element
	 * @param root The root element
	 * @throws ParserConfigurationException 
	 */
	public void createDocBuilderFactory(String root) throws ParserConfigurationException{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.newDocument();
		rootElement = document.createElement(root);
		document.appendChild(rootElement);
	}


	/**
	 * Adds the nodes and elements to the xml
	 * @param airport The airport to be saved
	 */
	public void addNodesAndElements(Airport airport) {

		//int numberOfRunways = airport.runways().size(); //number of physical runways

		for (PhysicalRunway runway: airport.runways()) { // looping through all physical runways


			Element physicalRunway = document.createElement("PhysicalRunway");
			String nam = runway.getId();
			// physicalRunway.appendChild(document.createTextNode(nam));

			Element prName = document.createElement("Name");
			prName.appendChild(document.createTextNode(nam));
			physicalRunway.appendChild(prName);

			for (int i = 0; i < 2; i++) { // looping through each actual runway (2)
				Runway r = runway.getRunway(i);// getting a runway

				// Creating runway element and appending to root element
				Element em = document.createElement("Runway");

				// Creating each of the runway's elements and appending to the runway element
				Element name = document.createElement("RunwayName");
				name.appendChild(document.createTextNode(r.getName()));
				em.appendChild(name);

				Element tora = document.createElement("TORA");
				String to = Double.toString(r.getTORA(1));// getting the tora value that can be modified
				tora.appendChild(document.createTextNode(to));
				em.appendChild(tora);

				Element asda = document.createElement("ASDA");
				String as = Double.toString(r.getASDA(1));
				asda.appendChild(document.createTextNode(as));
				em.appendChild(asda);

				Element toda = document.createElement("TODA");
				String tod = Double.toString(r.getTODA(1));
				toda.appendChild(document.createTextNode(tod));
				em.appendChild(toda);

				Element lda = document.createElement("LDA");
				String ld = Double.toString(r.getLDA(1));
				lda.appendChild(document.createTextNode(ld));
				em.appendChild(lda);

				Element displacedThreshold = document
						.createElement("DisplacedThreshold");
				String dt = Double.toString(r.getDisplacedThreshold(1));
				displacedThreshold.appendChild(document.createTextNode(dt));
				em.appendChild(displacedThreshold);

				physicalRunway.appendChild(em);
				// rootElement.appendChild(em);
			}

			rootElement.appendChild(physicalRunway);
		}

	}

	/**
	 * Adds the nodes and elements to the xml
	 * @param obstacle The obstacle to be saved
	 */
	public void addNodesAndElementsObstacle(Obstacle obstacle) {

		Element size_Type = document.createElement("Size_Type");
		String st = obstacle.getSizeType();
		size_Type.appendChild(document.createTextNode(st));
		rootElement.appendChild(size_Type);

		Element height = document.createElement("Height");
		String h = Double.toString(obstacle.getHeight());
		height.appendChild(document.createTextNode(h));
		rootElement.appendChild(height);

		Element width = document.createElement("Width");
		String w = Double.toString(obstacle.getWidth());
		width.appendChild(document.createTextNode(w));
		rootElement.appendChild(width);

		Element length = document.createElement("Length");
		String l = Double.toString(obstacle.getLength());
		length.appendChild(document.createTextNode(l));
		rootElement.appendChild(length);

	}


	/**
	 * Creates File chooser and saves XML to given file
	 * @throws IOException
	 * @throws TransformerException
	 */
	public void createFChooserAndStore() throws IOException, TransformerException {

		JFileChooser fc = new JFileChooser();

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
