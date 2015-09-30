package utilites.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AT on 09.09.2015.
 */
public class HiScoresSerializableDAO implements SettingsDAO{
    // Constant, contains path to configuration file
    private static final String FILEPATH = new File("").getAbsolutePath() + "/config/MineSweeperHiScores.dat";

    // Constant, contains allowed types of minefield size.
    private static final String[] TAGS = {"small","medium","large"};

    public List<String> getData() {
        List<String> hiScoreList = new ArrayList<String>();

        try {
            FileInputStream file = new FileInputStream(FILEPATH);
            ObjectInputStream ois = new ObjectInputStream(file);

            hiScoreList = (ArrayList) ois.readObject();

            ois.close();
            return hiScoreList;

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

    public Boolean saveData(List<String> hiScoreList) {
            return createData(hiScoreList);
    }

    private Boolean createData(List<String> hiScoreList) {
        try {
            FileOutputStream file = new FileOutputStream(FILEPATH);
            ObjectOutputStream oos = new ObjectOutputStream(file);

            oos.writeObject(hiScoreList);

            oos.flush();
            oos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
