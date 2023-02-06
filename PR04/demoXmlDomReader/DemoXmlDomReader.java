package demoXmlDomReader;

import org.apache.xerces.parsers.DOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DemoXmlDomReader {

	public static void main(String[] args) {
		
		System.out.println("Demo XML-DOM lesen");
		
		DemoXmlDomReader dom = new DemoXmlDomReader();
		String path="D:\\Stuff\\Studium\\HSMW\\IF21wS1-B\\3_datenrepraesentation\\praktika\\PR04\\";
		String xmlFile="biblio_dtd.xml";
		
		//DOM-Objekt (no Validation, DTD)
		Document doc = dom.readDom(path, xmlFile, false, false); 
		
		//Root-Element
		Node rootNode = doc.getDocumentElement();
		
		//Ausgabe DOM-Baumstruktur auf Konsole
		dom.showDom(rootNode, 1);
	}
	
	
	/**
	 * Initialisierung und Konfiguration DOM-Parser,
	 * Parsen der XML-Datei,
	 * Rueckgabe DOM-Objekt
	 * 
	 * @param path Pfad zu XML-File
	 * @param xmlFile Name XML-File
	 * @param doValidation Ein-Ausschalten Validierung
	 * @param isSchema Umschalten DTD oder Schema
	 * @return Dokument-Objekt der geparsten XML-Datei
	 */
	public Document readDom(String path, String xmlFile, boolean doValidation, boolean isSchema) {
		//DOM-Parser Instanziierung
		DOMParser domParser = new DOMParser();
		try {
			if(!isSchema) { //DTD
				//ignoriert Format-Leerzeichen
				//Feature nur mit DTD moeglich
				domParser.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", false);
			}
			
			if(doValidation) {
				//Validieren ein- oder ausschalten
				domParser.setFeature("http://apache.org/xml/features/validation/schema", true);
				
				if(isSchema) { //kein Feature fuer DTD, Schema-Validierung
					domParser.setFeature("http://apache.org/xml/features/validation/schema", true);
				}
			}
			
			//Dokument parsen und einlesen in DOM
			domParser.parse(path + xmlFile); //domParser-Instanz enthält das XML-Dokument als DOM
			System.out.println("DOM-parsing erfolgreich");
			
			//Dokument und root-Knoten holen
			Node rootElement = domParser.getDocument().getDocumentElement();
			System.out.println("Das Wurzelement heisst: " + rootElement.getNodeName());
			
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return domParser.getDocument();
	}
	
	/**
	 * Rekursive Anzeige der DOM-Baumstruktur in der Konsole
	 * 
	 * @param node 
	 * @param level
	 */
	public void showDom(Node node, int level) {
		
		//Anzeige aller Attribute des aktuellen Knotens
		NamedNodeMap attrMap = node.getAttributes();
		if(attrMap != null) {
			for(int k=0; k < attrMap.getLength(); k++) {
				Node attrNode = attrMap.item(k);
				String attrInfo = "(Knoten-)Attribut-Name [" + attrNode.getNodeName() + "] Knoten-Typ [" 
						+ attrNode.getNodeType() + "] Wert [" + attrNode.getNodeValue() + "]";
				//Einruecken zur Formatierung
				for(int l=0; l < level; l++) {
					System.out.print("   ");
				}
				System.out.println(attrInfo);
			}
		}
		
		//Anzeige aller Kindknoten des aktuellen Knotens
		NodeList nodeList = node.getChildNodes();
		for(int k=0; k < nodeList.getLength(); k++) {
			Node nodeChild = nodeList.item(k);
			String nodeInfo = "Knoten-Name [" + nodeChild.getNodeName()
				+ "] Knoten-Typ [" + nodeChild.getNodeType()
				+ "] Wert [" + nodeChild.getNodeValue() + "]";
			//Einruecken zur Formatierung
			for(int l=0; l < level; l++) {
				System.out.print("   ");
			}
			System.out.print(Integer.toString(level) + "." + Integer.toString(k+1) + nodeInfo);
			
			//Anzeige Text-Content, wenn Knotentyp Textknoten
			if(nodeChild.getNodeType() == Node.TEXT_NODE) {
				//Einruecken zum Formatieren
				for(int l=0; l < level; l++) {
					System.out.print("   ");
				}
				System.out.println(Integer.toString(level) + "." + Integer.toString(k+1) 
						+ " Text-Inhalt: [" + nodeChild.getTextContent() + "]");
			}
			
			//rekursives Aufrufen ermoeglicht rekursives Absteigen in DOM-Baumstruktur
			showDom(nodeChild, level+1);
		}
		
	}

}
