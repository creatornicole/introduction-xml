<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- Ausgabe als HTML -->
	<xsl:output method="html"/>
	
	<xsl:template match="/">
		<html>
			<head>
				<titel>Links für das Modul Datenrepräsentation</titel>
			</head>
			<body>
				<h1>Links für das Modul Datenrepräsentation</h1>
				<xsl:call-template name="tableHeader"/>
			</body>	
		</html>
	</xsl:template>
	
	<xsl:template name="tableHeader">
		<table border="1">
			<tr> <!-- Table Header -->
				<th>Position</th>
				<th>Titel</th>
				<th>Link</th>
				<th>Beschreibung</th>
			</tr>
			<!-- Mit Informationslinks fuellen -->
			<xsl:call-template name="tableBody"/>	
		</table>
	</xsl:template>
	
	<xsl:template name="tableBody">
		<xsl:for-each select="/links/link[modul='DTA']">
		<tr>
			<td><xsl:value-of select="position()"/></td>
			<td><xsl:value-of select="titel"/></td>
			<td>
				<a>
					<xsl:attribute name="href"> <!-- Attribut zu Element hinzufuegen -->
						<xsl:value-of select="ref"/> <!-- Attribut einen Wert hinzufuegen -->
					</xsl:attribute>
					<xsl:value-of select="ref"/>
				</a>
			</td>
			<td><xsl:value-of select="description"/></td>
		</tr>
		</xsl:for-each>
	</xsl:template>
	

</xsl:stylesheet>