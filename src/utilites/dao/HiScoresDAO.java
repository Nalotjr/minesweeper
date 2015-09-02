package utilites.dao;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class used to read/write high scores data from configuration XML file. It use w3c.dom.library
 * for XML parsing.
 * Created by user on 25.08.2015.
 * @version 2.0
 */
public class HiScoresDAO implements SettingsDAO {

    // Constant, contains path to high scores file
    private static final String FILEPATH = new File("").getAbsolutePath() + "//Config//MineSweeperHiScores.XML";

    // Constant, contains allowed types of minefield size.
    private static final String[] TAGS = {"small","medium","large"};

    /**
     * Method returns list with high scores, which it reads from high scores data file. If file not exists or damaged,
     * method creates new high scores data file and returns automatically filled high score table.
     * @return - List<String> - list, whose contain 15 linearly situated pairs of high scores (5 for each type of
     * minefield - first 5 pairs for "small", next 5 for "medium" etc.) - first element of pair with player name,
     * second with score.
     */
    public ArrayList<String> getData() {

        ArrayList<String> hiScoreList = new ArrayList<String>();

        try {
            DocumentBuilder documentBuilder = SettingsDAOFactory.getDocumentBuilderFactory().newDocumentBuilder();
            File file = new File(FILEPATH);

            if (file.exists()) {
                Document doc = documentBuilder.parse(file);
                Element e = doc.getDocumentElement();

                // reading high scores for each field size described at TAGS (5 high score results for each)
                for (String x : TAGS) {
                    Node node = e.getElementsByTagName(x).item(0);
                    NodeList nodeList = ((Element) node).getElementsByTagName("hs");
                    for (int j = 0; j < 5; j++) {
                        NamedNodeMap attributes = nodeList.item(j).getAttributes();
                        hiScoreList.add(((Attr) attributes.item(0)).getValue());
                        hiScoreList.add(((Attr) attributes.item(1)).getValue());
                    }
                }
                return hiScoreList;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {

            hiScoreList.clear();
            for(int i = 0; i < TAGS.length * 10; i++)
            {
                if (i % 2 == 0) {
                    hiScoreList.add("Player");
                }
                else {
                    hiScoreList.add("999");
                }
            }
            createData(hiScoreList);
            return hiScoreList;
        }
    }

    /**
     * Method calls createData method to rewrite high score data file with received data.
     * @param hiScoreList - List<String> should contain 5 high scores pairs for each field size (for example:
     *                    0 element - first player name for small size, 1-st element - first player score for small
     *                    size, ... 9-th element - 5-th player score for small size, 10-th element - first player
     *                    name for medium size etc.)
     * @return - Boolean - "true", if high scores saving was successful, "false" - if not.
     */
    public Boolean saveData(List<String> hiScoreList) {

        return createData(hiScoreList);
    }

    /**
     * This internal method is used for creation (or completely rewriting) of high score data file using received
     * list of high scores.
     * @param hiScoreList - List<String> should contain 5 high scores pairs for each field size (for example:
     *                    0 element - first player name for small size, 1-st element - first player score for small
     *                    size, ... 9-th element - 5-th player score for small size, 10-th element - first player
     *                    name for medium size etc.)
     * @return - Boolean - "true", high scores saving was successful, "false" - if not.
     */
    private Boolean createData(List<String> hiScoreList) {

        try {
            DocumentBuilder documentBuilder = SettingsDAOFactory.getDocumentBuilderFactory().newDocumentBuilder();

            Document doc = documentBuilder.newDocument();

            // creating structure : <hiscores> <small> <hs name = "..." score = "..." /> ... </small> <medium> <hs ....
            Element rootElement = doc.createElement("hiscores");
            doc.appendChild(rootElement);

            for(int i = 0; i < TAGS.length; i++)
            {
                Element fieldSize = doc.createElement(TAGS[i]);
                rootElement.appendChild(fieldSize);
                for(int j = 0; j < 5; j++)
                {
                    Element hs = doc.createElement("hs");
                    fieldSize.appendChild(hs);

                    hs.setAttribute("name",hiScoreList.get(i * 10 + j * 2));
                    hs.setAttribute("score",hiScoreList.get(i * 10 + j * 2 + 1));
                }
            }

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
