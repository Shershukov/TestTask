import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        FileManager fileManager = new FileManager();
        System.out.println("Создание файла...");
        String creationResult = fileManager.createFile(1024, "BigFile.txt");
        System.out.println("Результат создания: " + creationResult + "\n\n");

        System.out.println("Пинг с infotecs.ru...");
        Network.ping("infotecs.ru");
        System.out.println("\n\n");

        System.out.println("Преобразование xml в json...");
        Document doc = new FileManager().readXml("/data/Input.xml");
        String json = XmlParser.parseXml(doc);
        System.out.println("Преобразовано");

        fileManager.saveJson(json);
    }
}