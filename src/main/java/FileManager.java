import com.google.gson.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * Класс для работы с файлами
 */
public class FileManager {

    private final String temporaryDirectory = System.getProperty("user.dir") + "/data/Temporary/";
    private final String errorFile = System.getProperty("user.dir") + "/data/Error.txt";
    private final String SUCCESS = "Успешно";
    private final String FAILURE = "Ошибка";

    /**
     * Метод создания файла заданного размера
     *
     * @param size размер нового файла в мегабайтах
     * @param fileName имя файла
     *
     * @return статус создания файла
     */
    public String createFile(Integer size, String fileName) {
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(temporaryDirectory + fileName, "rw");
            raf.setLength(1024 * 1024 * size);
            raf.close();
            return SUCCESS;
        } catch (Exception e) {
            deleteFile(temporaryDirectory + fileName);
            errorMessage(e);
            return FAILURE;
        }
    }

    /**
     * Метод сохранения json в файл. Перед сохранением json форматируется в удобочитаемый вид
     *
     * @param json объект json
     */
    public void saveJson(String json) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(String.valueOf(json));
        String prettyJsonString = gson.toJson(je);
        FileWriter file = new FileWriter(System.getProperty("user.dir") + "/data/Output.json");
        file.write(prettyJsonString);
        file.close();
    }

    /**
     * Метод удаления файла
     *
     * @param path путь до удаляемого файла
     */
    private void deleteFile(String path) {
        File file = new File(path);
        System.out.println(path);
        boolean b = file.delete();
        System.out.println(b);
    }

    /**
     * Метод создания файла с информацией об ошибке
     * @param e обрабатываемое исключение
     */
    private void errorMessage(Exception e) {
        try {
            PrintStream ps = new PrintStream(new File(errorFile));
            e.printStackTrace(ps);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Метод чтения xml-файла
     *
     * @param fileName имя файла
     * @return xml документ
     */
    public Document readXml(String fileName) throws ParserConfigurationException, IOException, SAXException {
        final File xmlFile = new File(System.getProperty("user.dir") + File.separator + fileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc;
    }
}
