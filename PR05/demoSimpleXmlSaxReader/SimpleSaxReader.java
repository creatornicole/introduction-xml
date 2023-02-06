package demoSimpleXmlSaxReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SimpleSaxReader {
	
	XMLReader reader = null;
	String xmlFile = null;
	
	/**
	 * Konstruktur zur Initialisierung des SAX-Parsers
	 * 
	 * @param xmlFile zu verarbeitendes XML-File
	 */
	public SimpleSaxReader(String xmlFile) {
		String driverName = "org.apache.xerces.jaxp.SAXParserFactoryImpl";
		
		try {
			//SAX-Parser ueber Factory-Methode erzeugen
			//SAXParserFactory nutzt Xerces-Implementierung ueber driverName
			SAXParserFactory factory = SAXParserFactory.newInstance(driverName, null); 
			//Spezifiziert, dass durch Code erzeugte Parser XML-Namensraeume unterstuetzen wird
			factory.setNamespaceAware(true);
			//Eigentlichen Parser ueber erstelltes factory-Objekt erstellen
			SAXParser parser = factory.newSAXParser();
			
			//reader SAX-XML-Reader zuweisen
			this.reader = parser.getXMLReader();
			
		} catch(SAXException | ParserConfigurationException ex) {
			System.out.println(ex.getMessage());
		}
		
		this.xmlFile = xmlFile;
		setFeatures();
	}
	
	/**
	 * SAX-Parser Eigenschaften setzen
	 */
	private void setFeatures() {
		try {
			//Validierung einschalten
			this.reader.setFeature("http://xml.org/sax/features/validation", true);
			//Gegen Schema validieren
			this.reader.setFeature("http://apache.org/xml/features/validation/schema", true);
		} catch(Exception ex) {
			ex.getMessage();
		}
	}
	
	/**
	 * Startet Parsen und die Event-Erzeugung
	 */
	public void run() {
		try {
			this.reader.parse(this.xmlFile);
			System.out.println("SAX-Parsen erfolgreich");
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Registrierung ContentHandler
	 * 
	 * @param myContentHandler Uebergabe ContentHandler fuer XML-Reader
	 */
	public void setContentHandler(ContentHandler myContentHandler) {
		this.reader.setContentHandler(myContentHandler);
	}
	
	/**
	 * Registrierung ErrorHandler
	 * 
	 * @param myErrorHandler Uebergabe ErrorHandler fuer XML-Reader
	 */
	public void setErrorHandler(ErrorHandler myErrorHandler) {
		this.reader.setErrorHandler(myErrorHandler);
	}

}
