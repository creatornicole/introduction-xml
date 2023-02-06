package demoSimpleXmlSaxReader;

public class ProgramMain {

	public static void main(String[] args) {
		
		System.out.println("Demo SAX-Reader");
		
		String path = "D:\\Stuff\\Studium\\HSMW\\IF21wS1-B\\3_datenrepraesentation\\praktika\\PR05\\";
		String xmlFile = "biblio_schema.xml";		
		
		//ErrorHandler mit invalidier XML-Datei testen
		//String xmlFile = "biblio_schema_invalid.xml";
		
		//SAX-Reader instanziieren und initialisieren
		SimpleSaxReader mySaxReader = new SimpleSaxReader(path + xmlFile);
		
		//Eigenen ContentHandler registrieren
		mySaxReader.setContentHandler(new MyContentHandler());
		//Eigenen ErrorHandler registrieren
		mySaxReader.setErrorHandler(new MyErrorHandler());
		
		//Parsen starten und SAX-Events erzeugen
		mySaxReader.run();

	}

}
