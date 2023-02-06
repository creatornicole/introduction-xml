package demoXmlDomWriter;

import org.apache.xerces.dom.DOMImplementationImpl; 
import org.w3c.dom.Attr; 
import org.w3c.dom.Comment; 
import org.w3c.dom.DOMConfiguration; 
import org.w3c.dom.Document; 
import org.w3c.dom.DocumentType; 
import org.w3c.dom.Element; 
import org.w3c.dom.Text; 
import org.w3c.dom.ls.DOMImplementationLS; 
import org.w3c.dom.ls.LSSerializer;

public class DemoXmlDomWriter {

	public static void main(String[] args) {
		
		DemoXmlDomWriter domWriter = new DemoXmlDomWriter();
		
		String path = "D:\\Stuff\\Studium\\HSMW\\IF21wS1-B\\3_datenrepraesentation\\praktika\\PR04\\";
		String xmlOutputFile ="buecher.xml";
		
		//DOM erstellen
		Document doc = domWriter.createDom();
		
		//in XML-Datei schreiben
		domWriter.writeDom(path + xmlOutputFile, doc);
		
		System.out.println("Demo-DOM wurde erstellt und in eine XML-Datei geschrieben.");

	}
	
	/**
	 * XML-Datei aus DOM-Objekt schreiben/erzeugen
	 * 
	 * @param xmlFile Datei in die XML geschrieben werden soll
	 * @param doc DOM aus dem gelesen werden soll
	 */
	public void writeDom(String xmlFile, Document doc) {
		//Instanziierung Xerces-DOM-Implementierung
		DOMImplementationLS domImpl = new DOMImplementationImpl(); //LS-Feature-Objekte
		LSSerializer serializer = domImpl.createLSSerializer();
		DOMConfiguration config = serializer.getDomConfig();
		
		//pro Ebene vier Leerzeichen zum Einruecken erzeugen
		config.setParameter("format-pretty-print", true); //"pretty-print" erzeugt fuer Anwender einfach lesbare Darstellung
		//Zeilende formatieren
		serializer.setNewLine("\r\n");
		
		//in XML-Datei schreiben
		serializer.writeToURI(doc, "File:" + xmlFile);
	}
	
	/**
	 * DOM-Objekt erstellen (hier fuer reine Testzwecke)
	 * 
	 * @return DOM-Objekt
	 */
	public Document createDom() {
		Document doc = null;
		
		//Instanzierung der Xerces-DOM-Implementierung
		DOMImplementationImpl domImpl = new DOMImplementationImpl();
		//Erzeugen des DOM-Dokumentes inklusive Wurzel-Knoten
		doc = domImpl.createDocument(null, "buecher", null);
		//DTD vereinbaren
		DocumentType docType = domImpl.createDocumentType("buecher", null, "buecher.dtd");
		
		doc.appendChild(docType);
		
		//Knoten des ersten Levels nach der Wurzel erzeugen
		Element elemBuch = doc.createElement("buch");
		
		//Attribute hinzufuegen
		elemBuch.setAttribute("isbn", "978-0-59600-764-5");
		elemBuch.setAttribute("buch_id", "_00001");
		
		//Kindknoten erzeugen
		Element elemTitel = doc.createElement("titel"); //Element erzeugen
		Text textNodeTitel = doc.createTextNode("XML in a Nutshell"); //Textinhalt fuer zuvor erzeugtes Element
		elemTitel.appendChild(textNodeTitel); //Text dem Element zuweisen
		
		Element elemAutoren = doc.createElement("autoren"); //Element erzeugen
		Text textNodeAutor = doc.createTextNode("Harold, E.R., Means, W.S."); //Textinhalt fuer erzeugtes Element erzeugen
		elemAutoren.appendChild(textNodeAutor); //Text dem Element zuweisen
		
		Element elemVerlag = doc.createElement("verlag"); //Element erzeugen
		Text textNodeVerlag = doc.createTextNode("O'Reilly Press Köln"); //Textinhalt fuer erzeugtes Element erzeugen
		elemVerlag.appendChild(textNodeVerlag); //Text dem Element zuweisen
		
		//Kommentarzeile erzeugen
		//wird vor den restlichen Child-Elementen erscheinen aufgrund Festlegung der Anordnung (siehe Befehle zur Kindknoten-Zuweisung
		Comment comment = doc.createComment("XML mit DOM-Writer erzeugt am 06.02.2023");
		
		//Kindknoten zu elemBuch hinzufuegen
		//Reihenfolge hier bestimmt Reihenfolge in der XML-Datei
		elemBuch.appendChild(comment);
		elemBuch.appendChild(elemTitel);
		elemBuch.appendChild(elemAutoren);
		elemBuch.appendChild(elemVerlag);
		
		//Knoten elemBuch in das Dokument einfuegen
		doc.getDocumentElement().appendChild(elemBuch);
		
		return doc;
	}

}
