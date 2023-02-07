@echo off

rem So funktioniert das Aufrufen von Xalan-Java in einer Batch-Datei
rem -classpath ..\..\xalan-j_2_7_2\xalan.jar: Verweis auf die jar-Datei
rem org.apache.xalan.xslt.Process: Verweis auf die Java-Klasse
rem -xsltc: Aufruf des XSLT-Compilers
java -classpath ..\..\xalan-j_2_7_2\xalan.jar org.apache.xalan.xslt.Process -xsltc -IN messberichte.xml -XSL messberichte.xsl -OUT messberichte.html -html
pause
