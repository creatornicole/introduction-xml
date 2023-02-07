package demoXsltTransformer;

/**
 * Demonstriert das Einbinden eines XSLT-Transformers
 * mittels Xalan in eine Java-Anwendung
 */

public class ProgramMain {

	public static void main(String[] args) {
		//Speichern der benoetigten Parameter fuer Aufruf XSLT-Transformer in Variablen
		String path = "D:\\Stuff\\Studium\\HSMW\\IF21wS1-B\\3_datenrepraesentation\\praktika\\PR07\\beispiele\\biblio\\";
		String inputFile = "biblio.xml";
		String outputFile = "biblio.html";
		String styleSheetFile = "biblio.xsl";
		
		System.out.println("Demo XSLT-Transformer");
		
		//Aufruf XSLT-Transformer
		SimpleTransformer sT = new SimpleTransformer();
		sT.transform(path, inputFile, outputFile, styleSheetFile);
	}

}
