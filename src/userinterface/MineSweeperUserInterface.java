package userinterface;

import javax.swing.*;

import utilites.dao.SettingsDAO;
import utilites.dao.SettingsDAOFactory;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class of project. Extends JFrame class and contain methods for getting/saving size of minefield and resizing
 * of frame, according to new field size.
 * Created by AT on 15.08.2015.
 */
public class MineSweeperUserInterface extends JFrame {

    // List contain high scores for all types of minefield
    private static List<String> hiScoreTable = new ArrayList<String>();
    // List contain minefield size(type)
    private static List<String> fieldSizeList = new ArrayList<String>();
    // Instance of MineSweeperMinefield class;
    private MineSweeperMinefield minefieldPanel;

    /**
     * Constructor initiates panel with minefield and menu and made all visible;
     */
    public MineSweeperUserInterface()
    {
        // initiating minefield panel and menu bar
        minefieldPanel = new MineSweeperMinefield(this);
        JMenuBar mainMenu = new MineSweeperMenuBar(this, (MineSweeperMinefield) minefieldPanel);

        // showing frame
        setJMenuBar(mainMenu);
        setContentPane(minefieldPanel);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String argc[])
    {
        // reading field size(type) from configuration file
        SettingsDAOFactory settingsFactory = SettingsDAOFactory.getDAOFactory(SettingsDAOFactory.CONFIG);
        SettingsDAO settingsDAO = settingsFactory.manageSettingsDAO();
        fieldSizeList = settingsDAO.getData();

        // reading high scores from file
        settingsFactory = SettingsDAOFactory.getDAOFactory(SettingsDAOFactory.HISCORES);
        settingsDAO = settingsFactory.manageSettingsDAO();
        hiScoreTable = settingsDAO.getData();

        // creating frame
        new MineSweeperUserInterface();
    }

    public String getFieldSize() {
        return fieldSizeList.get(0);
    }

    /**
     * Method save new field size at file
     * @param fieldSize - one of 3 types of field size ("small", "medium", "large")
     */
    public void setFieldSize(String fieldSize) {
        fieldSizeList.set(0, fieldSize);

        SettingsDAOFactory settingsFactory = SettingsDAOFactory.getDAOFactory(SettingsDAOFactory.CONFIG);
        SettingsDAO settingsDAO = settingsFactory.manageSettingsDAO();
        settingsDAO.saveData(fieldSizeList);
    }

    /**
     * Method returns highscore table as list of String
     * @return - hiScoreTable (list with highscore table)
     */
    public List<String> getHiScoreTable() {
        return hiScoreTable;
    }

    /**
     * Method repaints frame.
     */
    public void ResetMinefieldPanel () {

        setVisible(false);
        minefieldPanel.MineSweeperMinefieldReset();
        setContentPane(minefieldPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
