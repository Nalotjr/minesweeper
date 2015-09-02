package utilites.dao;

import java.util.List;

/**
 * This interface contain methods for getting and saving data to different XML files. It insists on use of overabundant
 * parameter types for methods of a class, which doing getting/saving of field configuration but allows to unify methods
 * for both classes;
 * Created by AT on 25.08.2015.
 */
public interface SettingsDAO {

    List<String> getData();
    Boolean saveData(List<String> data);
}
