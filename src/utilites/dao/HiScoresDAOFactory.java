package utilites.dao;

/**
 * Factory method creates and returns object of HiScoresDAO class which realize methods of SettingsDAO interface for
 * high scores data file.
 * Created by AT on 25.08.2015.
 */
public class HiScoresDAOFactory extends SettingsDAOFactory {

    @Override
    public SettingsDAO manageSettingsDAO() {

        return new HiScoresDAO();
    }
}
