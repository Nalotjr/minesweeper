package utilites.dao;

/**
 * Factory method creates and returns instance of class which realize SettingsDAO interface for selected type of
 * high scores data file.
 * Created by AT on 25.08.2015.
 */
public class HiScoresDAOFactory extends SettingsDAOFactory {

    private SettingsDAO settingsDAO;

    HiScoresDAOFactory(int typeOfSource) {

        switch (typeOfSource) {
            case HISCORES:
                settingsDAO = new HiScoresSerializableDAO();
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
