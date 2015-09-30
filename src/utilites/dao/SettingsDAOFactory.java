package utilites.dao;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;

/**
 * This class realize pattern DAO for different data with same access interface .
 * Created by AT on 25.08.2015.
 */
public abstract class SettingsDAOFactory {

    public static final int CONFIG = 1;
    public static final int HISCORES = 2;

    /**
     * This factory returns chosen instance of factory for classes that implements SettingsDAO interface
     * @param whichFactory - type of data source.
     * @return SettingsDAOFactory - factory for selected type of data source.
     */
    public static SettingsDAOFactory getDAOFactory(int whichFactory) {

        switch (whichFactory) {
            case CONFIG:
                return new ConfigDAOFactory(CONFIG);
            case HISCORES:
                return new HiScoresDAOFactory(HISCORES);
            default:
                return null;
        }
    }

    /**
     * Abstract method - should be overridden in classes, that inherits this one.
     */
    public abstract SettingsDAO manageSettingsDAO ();

}
