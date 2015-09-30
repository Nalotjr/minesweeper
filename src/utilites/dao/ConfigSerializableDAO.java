package utilites.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class realizes interface SettingsDAO for reading/saving game configuration file
 * Created by AT on 08.09.2015.
 */
public class ConfigSerializableDAO implements SettingsDAO {

    // Constant, contains path to configuration file
    private static final String FILEPATH = new File("").getAbsolutePath() + "/config/MineSweeperConfig.dat";

    // Constant, contains allowed types of minefield size.
    private static final String[] TAGS = {"small","medium","large"};

    public List<String> getData() {

        List<String> resultList = new ArrayList<String>();

        try {
            FileInputStream file = new FileInputStream(FILEPATH);
            ObjectInputStream ois = new ObjectInputStream(file);

            resultList = (ArrayList) ois.readObject();

            ois.close();

            if (Arrays.asList(TAGS).contains(resultList.get(0))) {
                return resultList;
            }
            else {
                throw new Exception();
            }
        } catch (Exception e) {
            resultList.clear();
            resultList.add(TAGS[0]);
            createData(resultList);
            return resultList;
        }
    }

    public Boolean saveData(List<String> configData) {

        if(Arrays.asList(TAGS).contains(configData.get(0)))
            return createData(configData);
        else
            return false;
    }

    private Boolean createData(List<String> fieldSizeValue) {

        try {

            FileOutputStream file = new FileOutputStream(FILEPATH);
            ObjectOutputStream oos = new ObjectOutputStream(file);

            oos.writeObject(fieldSizeValue);

            oos.flush();
            oos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
