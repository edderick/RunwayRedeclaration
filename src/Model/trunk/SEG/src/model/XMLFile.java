package model;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

//Creates XML document with an Airport's details
public class XMLFile {
	
	public XMLFile(Airport a) throws Exception {

		int no = a.runways().size();
		String root = a.getName();
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		Element rootElement = document.createElement(root);
		document.appendChild(rootElement);
		for (int i = 0; i < no; i++) {
			Runway r = (Runway) a.runways().get(i);
			
			Element em = document.createElement("Runway"/*element*/);
			
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
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(System.out);
		transformer.transform(source, result);
	}
}
	
	
	

