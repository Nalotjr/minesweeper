package userinterface;

import utilites.dao.SettingsDAO;
import utilites.dao.SettingsDAOFactory;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Class extends JDialod class and opens form for typing of player's name at modal dialog window
 * Created by AT on 17.08.2015.
 */
public class MineSweeperAddHiScore extends JDialog {

    // Value of shift at highscore list for current field size highscores. Used at method of anonymous inner classes,
    // so it can't be described and initiated inside of constructor method.
    private Integer listShift = 0;

    /**
     * Method creates a dialog window, reads player's name and saves high score table
     * @param parent - instance of MineSweeperUserInterface class that contain methods which allows to receive and save
     *               High scores table.
     * @param position - position of high score for current field size (from 0 to 4).
     * @param score - number of seconds counted  for current game.
     */
    public MineSweeperAddHiScore(MineSweeperUserInterface parent, int position, String score) {

        ArrayList<String> hiScoreList = (ArrayList)parent.getHiScoreTable();
        String selectedSize = parent.getFieldSize();

        switch (selectedSize.toLowerCase()) {
            case "small":
                listShift = 0;
                break;
            case "medium":
                listShift = 10;
                break;
            case "large":
                listShift = 20;
                break;
        }

        GridBagLayout gbag = new GridBagLayout ();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gbag);

        // Creates text field for entering player's name, and limits number of letters.
        JTextField nameField = new JTextField("Player",17);
        nameField.setDocument(new PlainDocument(){
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                    if (getLength()< 17) {
                        super.insertString(offs, str, a);
                    }
                }
        });
        nameField.selectAll();
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weighty = 1;
        gbag.setConstraints(nameField, gbc);

        // Creates "OK" button and saves high score if it's pressed.
        JButton okButton = new JButton ("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hiScoreList.add((listShift + position * 2), score);
                hiScoreList.add((listShift + position * 2), nameField.getText());
                hiScoreList.remove(listShift + 10);
                hiScoreList.remove(listShift + 10);

                SettingsDAOFactory settingsFactory = SettingsDAOFactory.getDAOFactory(SettingsDAOFactory.HISCORES);
                SettingsDAO settingsDAO = settingsFactory.manageSettingsDAO();
                settingsDAO.saveData(hiScoreList);

                setVisible(false);
                dispose();
                new MineSweeperHiScoreDialog(parent);
            }
        });
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.weighty = 1;
        gbag.setConstraints(okButton, gbc);

        JLabel aboutText = new JLabel ("<html><center><font size=4 face=Arial>Enter Your name</font></center></html>");
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 1;
        gbag.setConstraints(aboutText, gbc);

        add(aboutText);
        add(nameField);
        add(okButton);

        setTitle("New High Score");
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setSize(300,200);
        setResizable(false);
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);

        // Saves high score if window closed by pressing "close window" button
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                hiScoreList.add((listShift + (position - 1) * 2), score);
                hiScoreList.add((listShift + (position - 1) * 2), nameField.getText());
                hiScoreList.remove(listShift + 10);
                hiScoreList.remove(listShift + 10);

                SettingsDAOFactory settingsFactory = SettingsDAOFactory.getDAOFactory(SettingsDAOFactory.HISCORES);
                SettingsDAO settingsDAO = settingsFactory.manageSettingsDAO();
                settingsDAO.saveData(hiScoreList);

                setVisible(false);
                dispose();
                new MineSweeperHiScoreDialog(parent);
            }
        });

    }
}
