package userinterface;

import javax.swing.*;

import utilites.dao.SettingsDAO;
import utilites.dao.SettingsDAOFactory;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 15.08.2015.
 */
public class MineSweeperUserInterface extends JFrame {

    private static List<String> hiScoreTable = new ArrayList<String>();
    private static List<String> fieldSizeList = new ArrayList<String>();
    private MineSweeperMinefield minefieldPanel;

    public MineSweeperUserInterface()
    {
        minefieldPanel = new MineSweeperMinefield(this);
        JMenuBar mainMenu = new MineSweeperMenuBar(this, (MineSweeperMinefield) minefieldPanel);

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
        SettingsDAOFactory settingsFactory = SettingsDAOFactory.getDAOFactory(SettingsDAOFactory.CONFIG);
        SettingsDAO settingsDAO = settingsFactory.manageSettingsDAO();
        fieldSizeList = settingsDAO.getData();

        settingsFactory = SettingsDAOFactory.getDAOFactory(SettingsDAOFactory.HISCORES);
        settingsDAO = settingsFactory.manageSettingsDAO();
        hiScoreTable = settingsDAO.getData();

        new MineSweeperUserInterface();
    }

    public String getFieldSize() {
        return fieldSizeList.get(0);
    }

    public void setFieldSize(String fieldSize) {
        fieldSizeList.set(0, fieldSize);

        SettingsDAOFactory settingsFactory = SettingsDAOFactory.getDAOFactory(SettingsDAOFactory.CONFIG);
        SettingsDAO settingsDAO = settingsFactory.manageSettingsDAO();
        settingsDAO.saveData(fieldSizeList);
    }

    public List<String> getHiScoreTable() {
        return hiScoreTable;
    }

    public void ResetMinefieldPanel (String fieldSize) {

        setVisible(false);
        minefieldPanel.MineSweeperMinefieldReset();
        setContentPane(minefieldPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
