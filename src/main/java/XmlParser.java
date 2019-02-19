import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Класс для работы с xml
 */
public class XmlParser {

    /**
     * Метод парсит xml в json объект
     * @param doc объект xml
     */
    public static String parseXml(Document doc) throws TransformerException {
        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);

        JSONObject xmlJSONObj = XML.toJSONObject(writer.toString());
        return xmlJSONObj.toString();
    }
}
