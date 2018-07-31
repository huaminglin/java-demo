<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html><body><textarea readonly="true" rows="40" cols="200">
            <xsl:copy-of select="/School/Student"/>
        </textarea></body></html>
    </xsl:template>
</xsl:stylesheet>
