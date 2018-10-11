package huaminglin.demo.jaxb;

import javax.xml.bind.JAXBContext;
import com.example.store.Note;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerFactory;
import java.io.StringWriter;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import javax.xml.transform.Transformer;
import java.io.InputStream;

public class JaxbDemo {
    private static Document createXmlDocument() throws ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbFactory.newDocumentBuilder();
        Document doc = builder.newDocument();
        return doc;
    }

    private static Marshaller createJaxbMarshaller(Class... classesToBeBound) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(classesToBeBound);
        Marshaller m = jc.createMarshaller();
        return m;
    }

    private static String getXmlNodeAsString(Node node) throws TransformerConfigurationException, TransformerException {
        StringWriter writer = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(node), new StreamResult(writer));
        return writer.toString();
    }

    private static Unmarshaller createJaxbUnmarshaller(Class... classesToBeBound) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(classesToBeBound);
        Unmarshaller u = jc.createUnmarshaller();
        return u;
    }

    public static void main(String[] args) throws JAXBException, ParserConfigurationException, TransformerConfigurationException, TransformerException {
        {
            Note note = new Note();
            note.setTo("to1");
            Marshaller m = createJaxbMarshaller(Note.class);
            Document doc = createXmlDocument();
            m.marshal(note, doc);
            System.out.println(getXmlNodeAsString(doc));
        }
        {
            Unmarshaller u = createJaxbUnmarshaller(Note.class);
            InputStream inputStream = JaxbDemo.class.getResourceAsStream("/xml/note.xml");
            Note note = (Note) u.unmarshal(inputStream);
            System.out.println(note.getTo());
        }
    }
}
