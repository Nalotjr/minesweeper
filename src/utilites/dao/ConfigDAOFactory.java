package utilites.dao;

/**
 * Factory method creates and returns object of ConfigDAO class which realize methods of SettingsDAO interface for
 * configuration file.
 * Created by AT on 25.08.2015.
 */
public class ConfigDAOFactory extends SettingsDAOFactory {

    @Override
    public SettingsDAO manageSettingsDAO() {
        return new ConfigDAO();
    }
}
