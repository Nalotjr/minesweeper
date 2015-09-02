package utilites.dao;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;

/**
 * Abstract class - trying to realize pattern DAO in a slightly perverted form - not same data for different sources
 * but different data for different sources (but sources have same type - XML).
 * Created by AT on 25.08.2015.
 */
public abstract class SettingsDAOFactory {

    public static final int CONFIG = 1;
    public static final int HISCORES = 2;

    private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    private static TransformerFactory transformerFactory = TransformerFactory.newInstance();

    /**
     * This factory returns chosen instance of factory for classes that implements SettingsDAO interface
     * @param whichFactory - type of data source.
     * @return SettingsDAOFactory - factory for selected type of data source.
     */
    public static SettingsDAOFactory getDAOFactory(int whichFactory) {

        switch (whichFactory) {
            case CONFIG:
                return new ConfigDAOFactory();
            case HISCORES:
                return new HiScoresDAOFactory();
            default:
                return null;
        }
    }

    /**
     * Method returns single created instance of DocumentBuilderFactory (used in both DAO classes)
     * @return instance of DocumentBuilderFactory
     */
    public static DocumentBuilderFactory getDocumentBuilderFactory() {
        return documentBuilderFactory;
    }

    /**
     * Method returns single created instance of TransformerFactory (used in both DAO classes)
     * @return instance of TransformerFactory
     */
    public static TransformerFactory getTransformerFactory() {
        return transformerFactory;
    }

    /**
     * Abstract method - should be overridden in classes, that inherits this one.
     */
    public abstract SettingsDAO manageSettingsDAO ();

}
