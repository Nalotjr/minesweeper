package userinterface;

import javax.swing.*;
import java.awt.event.*;

/**
 * Class extends JMenuBar class and creates game menu
 * Created by AT on 17.08.2015.
 */
public class MineSweeperMenuBar extends JMenuBar {

    /**
     * Method creates a game menu and implements it's behavior.
     * @param parent - instance of MineSweeperUserInterface class that transferred to the child classes.
     * @param minefield - instance of MineSweeperMinefield class that contain method to reset minefield
     */
    public MineSweeperMenuBar(MineSweeperUserInterface parent, MineSweeperMinefield minefield) {

        JMenuItem gameMenuNew = new JMenuItem("New Game");
        gameMenuNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                minefield.MineSweeperMinefieldReset();
            }
        });

        JMenuItem gameMenuHiScores = new JMenuItem("View High Scores");
        gameMenuHiScores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                new MineSweeperHiScoreDialog(parent);
            }
        });

        JMenuItem gameMenuSettings = new JMenuItem("Settings");
        gameMenuSettings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                new MineSweeperSettingsDialog(parent);
            }
        });

        JMenuItem gameMenuExit = new JMenuItem("Exit");
        gameMenuExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                System.exit(0);;
            }
        });

        JMenuItem aboutMenuAbout = new JMenuItem("About");
        aboutMenuAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
               new MineSweeperAboutDialog();
            }
        });

        JMenu gameMenu = new JMenu("Game");
        gameMenu.add(gameMenuNew);
        gameMenu.addSeparator();
        gameMenu.add(gameMenuHiScores);
        gameMenu.add(gameMenuSettings);
        gameMenu.addSeparator();
        gameMenu.add(gameMenuExit);

        JMenu aboutMenu = new JMenu("About");
        aboutMenu.add(aboutMenuAbout);

        add(gameMenu);
        add(aboutMenu);
    }
}
