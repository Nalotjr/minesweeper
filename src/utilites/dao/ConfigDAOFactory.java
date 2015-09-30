package utilites.dao;

/**
 * Factory method creates and returns object of ConfigXMLDAO class which realize methods of SettingsDAO interface for
 * configuration file.
 * Created by AT on 25.08.2015.
 */
public class ConfigDAOFactory extends SettingsDAOFactory {

    private SettingsDAO settingsDAO;

    ConfigDAOFactory(int typeOfSource) {

        switch (typeOfSource) {
            case CONFIG:
                settingsDAO = new ConfigSerializableDAO();
                break;
            default:
                break;
        }
    }

    @Override
    public SettingsDAO manageSettingsDAO() {
        return settingsDAO;
    }
}
