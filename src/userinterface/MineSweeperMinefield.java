package userinterface;

import internalsolutions.MineSweeperMinefieldCell;
import internalsolutions.MineSweeperMinefieldInterface;
import internalsolutions.MineSweeperMinefieldMouseEngine;
import utilites.MineSweeperTimer;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.File;
import java.text.*;
import java.util.ArrayList;

/**
 * Class extends JPanel class and contain methods for displaying minefield and surrounding elements
 * Created by AT on 18.08.2015.
 */
public class MineSweeperMinefield extends JPanel {

    // Icons for cells of minefield
    private static ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();
    // Format fo displaying time and number of flags
    private static DecimalFormat digitsFormat = new DecimalFormat("000");

    // Horizontal size of minefield
    private Integer fieldXSize = 0;
    // Vertical size of minefield
    private Integer fieldYSize = 0;
    // Number of mines at field
    private Integer minesAtField = 0;
    // Cells of minefield
    private ArrayList<MineSweeperMinefieldCell> minefieldCells = new ArrayList<MineSweeperMinefieldCell>();
    // Instance of MineSweeperMinefieldMouseEngine class.
    private MineSweeperMinefieldMouseEngine minefieldMouseEngine;
    // Instance of MineSweeperMinefieldInterface class.
    private MineSweeperMinefieldInterface minefieldInterface;
    // Indicate state of game ("true" - finished, "false" - not yet)
    private Boolean gameFinished;
    // Instance of MineSweeperTimer class
    private MineSweeperTimer gameTimer;

    private JLabel flagsCounterLabel;
    private JLabel timeCounterLabel;
    private JLabel mineImageLabel;
    private JLabel watchImageLabel;

    // Instance of MineSweeperUserInterface class.
    private MineSweeperUserInterface parent;

    // Reading images for minefield cells and surrounding elements. Also setting format for displaying timer and number
    // of flags.
    static {
        for (int i = 0; i < 15; i++) icons.add(new ImageIcon(new File("").getAbsolutePath()+"/icons/Icon"+i+".gif"));
        digitsFormat.setMaximumIntegerDigits(3);
    }

    /**
     * Method situates minefield and surrounding elements on JPanel.
     * @param parent - instance of MineSweeperUserInterface class
     */
    public MineSweeperMinefield(MineSweeperUserInterface parent) {

        this.parent = parent;
        minefieldMouseEngine = new MineSweeperMinefieldMouseEngine(this);

        mineImageLabel = new JLabel(icons.get(12));
        mineImageLabel.setSize(new Dimension(30,30));

        watchImageLabel = new JLabel(icons.get(13));
        watchImageLabel.setSize(new Dimension(30,30));

        flagsCounterLabel = new JLabel(digitsFormat.format(minesAtField));
        flagsCounterLabel.setSize(new Dimension(27,20));
        flagsCounterLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        timeCounterLabel = new JLabel(digitsFormat.format(0));
        timeCounterLabel.setSize(new Dimension(27,20));
        timeCounterLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        add(mineImageLabel);
        add(watchImageLabel);
        add(flagsCounterLabel);
        add(timeCounterLabel);

        gameTimer = new MineSweeperTimer(1000, 1000, timeCounterLabel, digitsFormat);
        MineSweeperMinefieldReset();
    }

    /**
     * Method places minefield and surrounding elements according to size of minefield
     */
    public void MineSweeperMinefieldReset () {

        String fieldSize = parent.getFieldSize();

        switch (fieldSize) {
            case "small":
                fieldXSize = 9;
                fieldYSize = 9;
                minesAtField = 10;
                break;
            case "medium":
                fieldXSize = 16;
                fieldYSize = 16;
                minesAtField = 40;
                break;
            case "large":
                fieldXSize = 30;
                fieldYSize = 16;
                minesAtField = 99;
                break;
            default:
                fieldXSize = 9;
                fieldYSize = 9;
                minesAtField = 10;
                break;
        }

        // cleans minefield cells of previous minefield
        for (MineSweeperMinefieldCell x : minefieldCells)
            remove(x);
        minefieldCells.clear();
        repaint();

        // creates and places minefield cells for new minefield
        for (int i = 0; i < fieldXSize*fieldYSize; i++){
            minefieldCells.add(new MineSweeperMinefieldCell());
            minefieldCells.get(i).setSize(new Dimension(20,20));
            minefieldCells.get(i).setLocation(new Point((i % fieldXSize) * 20,(i / fieldXSize) * 20));
            minefieldCells.get(i).setIcon(icons.get(11));
            minefieldCells.get(i).setEnabled(true);
            minefieldCells.get(i).addMouseListener(minefieldMouseEngine);
            add(minefieldCells.get(i));
        }

        // placing surrounding elements according to minefield size
        mineImageLabel.setLocation(new Point(fieldXSize * 15 - 40,fieldYSize * 20 + 10));
        watchImageLabel.setLocation(new Point(fieldXSize * 5 - 40,fieldYSize * 20 + 10));
        flagsCounterLabel.setText(digitsFormat.format(minesAtField));
        flagsCounterLabel.setLocation(new Point(fieldXSize * 15 + 10,fieldYSize * 20 + 15));
        timeCounterLabel.setText(digitsFormat.format(0));
        timeCounterLabel.setLocation(new Point(fieldXSize * 5 + 10,fieldYSize * 20 + 15));

        // creates interface for minefield
        minefieldInterface = new MineSweeperMinefieldInterface(this);

        setGameFinished(false);
        setGameTimer(false);
        resetGameTimer();

        setLayout(null);
        setBackground(new Color(208, 208, 208));
        setPreferredSize(new Dimension(fieldXSize * 20, fieldYSize * 20 + 50));
    }

    /**
     * Method checks player's result and if it's highscore creates dialog for saving highscore at highscores table.
     */
    public void MineSweeperMinefieldCheckHighScores() {

        Integer gameResult = getGameTimerResult();
        String fieldSize = parent.getFieldSize();
        ArrayList<String> hiScoreList = (ArrayList)parent.getHiScoreTable();
        int listShift = 0;

        switch (fieldSize) {
            case "small":
                listShift = 0;
                break;
            case "medium":
                listShift = 10;
                break;
            case "large":
                listShift = 20;
                break;
            default:
                break;
        }

        for (int i = 0; i < 5; i ++) {
            if (Integer.parseInt(hiScoreList.get(i*2 + 1 + listShift)) >= gameResult) {
                new MineSweeperAddHiScore(parent, i, gameResult.toString());
                break;
            }
        }
    }

    // Method returns icon by it`s number
    public ImageIcon getIcon(int iconNumber) {
        return icons.get(iconNumber);
    }

    // Method returns list, that contain minefield cells
    public ArrayList<MineSweeperMinefieldCell> getMinefieldCells() {
        return minefieldCells;
    }

    // Method returns horizontal size of minefield
    public Integer getFieldXSize() {
        return fieldXSize;
    }

    // Method returns vertical size of minefield
    public Integer getFieldYSize() {
        return fieldYSize;
    }

    // Method returns number of mines on minefield
    public Integer getMinesAtField() {
        return minesAtField;
    }

    // Method returns game state
    public Boolean getGameFinished() {
        return gameFinished;
    }

    // Method sets game state
    public void setGameFinished (Boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    // Method sets game timer activity
    public void setGameTimer (Boolean timerActive) {
        gameTimer.setTimerActive(timerActive);
    }


    public Boolean getGameTimer () {
        return gameTimer.getTimerActive();
    }

    public int getGameTimerResult () {
        return gameTimer.getTimeCounter();
    }

    public void resetGameTimer () {
        gameTimer.resetTimer();
    }

    public void resetFlagsCounter () {
        flagsCounterLabel.setText(digitsFormat.format(0));
    }

    public MineSweeperMinefieldInterface getMinefieldInterface () {
        return minefieldInterface;
    }

    public void setFlagsNubberAtLabel (int flagsNubber) {
        flagsCounterLabel.setText(digitsFormat.format(flagsNubber));
    }
}
