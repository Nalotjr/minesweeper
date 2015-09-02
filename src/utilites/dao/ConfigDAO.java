package utilites.dao;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class used to read/write minefield size data from configuration XML file. It use w3c.dom.library
 * for XML parsing.
 * Created by user on 25.08.2015.
 * @version 2.0
 */
public class ConfigDAO implements SettingsDAO{

    // Constant, contains path to configuration file
    private static final String FILEPATH = new File("").getAbsolutePath() + "//config//MineSweeperConfig.XML";

    // Constant, contains allowed types of minefield size.
    private static final String[] TAGS = {"small","medium","large"};

    /**
     * Method returns size of minefield, which it reads from configuration file. If file not exists or damaged,
     * method creates new configuration file and returns "small" size of minefield.
     * @return - List<String> - list, whose first element contains size of minefield
     */
    public List<String> getData() {

        List<String> resultList = new ArrayList<String>();

        try {
            DocumentBuilder documentBuilder = SettingsDAOFactory.getDocumentBuilderFactory().newDocumentBuilder();
            File file = new File(FILEPATH);

            Document document = documentBuilder.parse(file);
            Element element = document.getDocumentElement();

            // We get only first node with name "fieldsize" from document element. Standard structure of configuration
            // file don't allows any other nodes with same name.
            Node node = element.getElementsByTagName("fieldsize").item(0);
            if (Arrays.asList(TAGS).contains(node.getTextContent())) {
                resultList.add(node.getTextContent());
                return resultList;
            }
            else {
                throw new Exception();
            }
        } catch (Exception e) {
            resultList.add("small");
            createData(resultList);
            return resultList;
        }
    }

    /**
     * Method checks received field size for compliance with permitted types and calls createData method to rewrite
     * configuration file.
     * @param configData - List<String> whose first element should contain size of minefield
     * @return - Boolean - "true", if configuration saving was successful, "false" - if not.
     */
    public Boolean saveData(List<String> configData) {

        if(Arrays.asList(TAGS).contains(configData.get(0)))
            return createData(configData);
        else
            return false;
    }

    /**
     * This internal method is used for creation (or completely rewriting) of configuration file using received
     * parameter. Method don't checks the parameter because both public methods, which calls this method, have checking.
     * @param fieldSizeValue - List<String> whose first element should contain size of minefield.
     * @return - Boolean - "true", if configuration saving was successful, "false" - if not.
     */
    private Boolean createData(List<String> fieldSizeValue) {

        try {
            DocumentBuilder documentBuilder = SettingsDAOFactory.getDocumentBuilderFactory().newDocumentBuilder();

            Document doc = documentBuilder.newDocument();

            // creating structure : <gamesettings> <fieldsize>size</fieldsize> </gamesettings>
            Element rootElement = doc.createElement("gamesettings");
            doc.appendChild(rootElement);

            Element fieldSize = doc.createElement("fieldsize");
            fieldSize.appendChild(doc.createTextNode(fieldSizeValue.get(0)));
            rootElement.appendChild(fieldSize);

            Transformer transformer = SettingsDAOFactory.getTransformerFactory().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(FILEPATH));

            transformer.transform(source, result);

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
