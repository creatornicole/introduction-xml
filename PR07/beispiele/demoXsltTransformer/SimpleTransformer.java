package demoXsltTransformer;

import java.io.File;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

//Xalan-Implementierung
import org.apache.xalan.xsltc.trax.TransformerFactoryImpl;

/**
 * Grundfunktionalitaeten eines einfachen XSLT-Transformers
 */

public class SimpleTransformer {
	
	
	public void transform(String path, String inputFile, String outputFile, String stylesheetFile) {
		try {
			//Transformer ueber TransformerFactory erzeugen
			TransformerFactory transformerFactory = new TransformerFactoryImpl(); //fuer Xalan-Implementierung
			
			//Stylesheet-Datei laden, indem davon Objekt erzeugt wird
			Source stylesheet = new StreamSource(new File(path+stylesheetFile));
			//Stylesheet ueber Transformer-Objekt benutzen
			//Transformer-Objekt erzeugen und Stylesheet in Java-Klasse kompilieren
			//Transformer-Objekt kann durch Kompilation mehrfach angewendet werden
			//transformiert zudem schneller, als wenn eine Stylesheet-Datei immer wieder neu interpretiert werden muss
			Transformer transformer = transformerFactory.newTransformer(stylesheet);
			
			//Ausgabeformat angeben (hier: HTML)
			transformer.setOutputProperty(OutputKeys.METHOD, "html");
			
			//Eingabe-Datei anlegen, indem Objekt davon erzeugt wird
			Source inputDoc = new StreamSource(new File(path+inputFile));
			//Ausgabe-Datei anlegen, indem Objekt davon erzeugt wird
			Result outputDoc = new StreamResult(new File(path+outputFile));
			
			//Durchfuehren der eigentlichen Transformation
			transformer.transform(inputDoc, outputDoc);
			System.out.println("Transformation erfolgreich.");
			
		} catch (TransformerException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
