package demoXPathTester;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class DemoXPathTester {

	public static void main(String[] args) {
		
		System.out.println("Demo XPathTester");
		
		String path = "D:\\Stuff\\Studium\\HSMW\\IF21wS1-B\\3_datenrepraesentation\\praktika\\PR08\\";
		String expression="";
		
		//bibio.xml
		String xmlFile = "biblio.xml";
		expression="/biblio/buch[@buch_id='_00002']/*";
		
		//XML-Reader erzeugen, der mit XPath-Technologie umgehen kann
		SAXReader reader = new SAXReader(); //Hinweis: nach Standard-Einstellung ist Validieren ausgeschaltet
		
		//Einlesen des XML-Dokumentes
		Document doc;
		try {
			doc = reader.read(path+xmlFile); //einlesen XML-Dokument
			
			//erzeugen einer XPath-Node-Liste
			List<Node> nodes = doc.selectNodes(expression);
			
			//Knoten, die expression entsprechen, auswerten
			for(int k=0; k < nodes.size(); k++) {
				Object obj = nodes.get(k);
				
				if(obj instanceof Node) { //wenn Objekt Menge von XPath-Knoten dann nodeInfo ausgeben
					Node node = (Node) obj; //Objekt zu Node casten
					String nodeInfo = Integer.toString(k+1) + "."
							+ getNodeTypeAsString(node) 
							+ " <" + node.getName() + "> Wert [" + node.getStringValue() + "]";
					System.out.println(nodeInfo);
				}
				//wenn nicht Node, so treffen andere Typen zu, da XPath Knotentypen nur Node, Number, String, Boolean sein koennen
				else if(obj instanceof Number) {
					System.out.println("Number: " + obj.toString());
				} else if(obj instanceof String) {
					System.out.println("String: " + obj.toString());
				} else if(obj instanceof Boolean) {
					System.out.println("Boolean: " + obj.toString());
				}
			}
		} catch (DocumentException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Gibt Knotentyp gemaess XPath zurueck
	 * 
	 * @param node
	 * @return
	 */
	private static String getNodeTypeAsString(Node node) {
		String typeString = "";
		
		switch(node.getNodeType()) {
		case Node.DOCUMENT_NODE:
			typeString = "Dokumentknoten";
			break;
		case Node.ELEMENT_NODE:
			typeString = "Elementknoten";
			break;
		case Node.ATTRIBUTE_NODE:
			typeString = "Attributknoten";
			break;
		case Node.TEXT_NODE:
			typeString = "Textknoten";
			break;
		case Node.PROCESSING_INSTRUCTION_NODE:
			typeString = "Arbeitsanweisung";
			break;
		case Node.NAMESPACE_NODE:
			typeString = "Namensraumknoten";
			break;
		case Node.COMMENT_NODE:
			typeString = "Kommentarknoten";
			break;
		default:
			typeString = "(unbekannt)";
			break;
		}
		
		return typeString;
	}

}
