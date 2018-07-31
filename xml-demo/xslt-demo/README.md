## Use xml-maven-plugin to execute XSLT
xslt-demo/target/generated-resources/xml/xslt/input.xml

## Use Java API to execute XSLT
mvn exec:java

After use StreamSource insteadof DOMSource, Java XSLT works
javax.xml.transform.stream.StreamSource
javax.xml.transform.dom.DOMSource
DOMSource can't be used as stylesheet source, but it can be used as input source.

## xalan dependency
xalan is optional. There is a default xalan in JDK
com.sun.org.apache.xalan.internal.xsltc.trax.TransformerImpl
org.apache.xalan.transformer.TransformerImpl
