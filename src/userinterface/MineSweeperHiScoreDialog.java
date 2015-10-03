package userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Class extends JDialod and shows highscores table for current field size at modal dialog window
 * Created by AT on 17.08.2015.
 */
public class MineSweeperHiScoreDialog extends JDialog {

    /**
     * Method creates a dialog window and shows highscores for current field size.
     * @param parent - instance of MineSweeperUserInterface class that contain methods which allows to receive and save
     *               High scores table.
     */
    public MineSweeperHiScoreDialog (MineSweeperUserInterface parent) {

        String fieldSize = parent.getFieldSize();

        JPanel hiScoresDialogPanel = new JPanel(new GridBagLayout ());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(10,5,0,5);
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.gridwidth = 2;
        gbc.gridy = 0;
        gbc.gridx = 0;
        JLabel headerText = new JLabel("<html><center><font size=4 face=Arial>High Scores for "+ fieldSize.toUpperCase()
                +" minefield</font></center></html>");
        hiScoresDialogPanel.add(headerText, gbc);

        // creates JLabels for highscores data  (10 labels - 5 names and 5 scores)
        ArrayList<JLabel> nameLabels = new ArrayList<JLabel>();
        ArrayList<String> hiScoreTable = (ArrayList)parent.getHiScoreTable();

        int startElement = 0;
        switch (fieldSize.toLowerCase()) {
            case "small":
                startElement = 0;
                break;
            case "medium":
                startElement = 10;
                break;
            case "large":
                startElement = 20;
                break;
        }

        gbc.gridwidth = 1;
        for(int i = 0; i < 10; i++) {
            nameLabels.add(new JLabel(hiScoreTable.get(i+startElement)));
            if(i % 2 == 0) {
                gbc.insets = new Insets(0,50,0,0);
                gbc.gridy = i / 2 + 1;
                gbc.gridx = 0;
                gbc.anchor = GridBagConstraints.LINE_START;
                hiScoresDialogPanel.add(nameLabels.get(i), gbc);
            } else {
                gbc.insets = new Insets(0,10,0,50);
                gbc.gridy = i / 2 + 1;
                gbc.gridx = 1;
                gbc.anchor = GridBagConstraints.LINE_END;
                hiScoresDialogPanel.add(nameLabels.get(i), gbc);
            }
        }

        gbc.insets = new Insets(0,5,10,5);
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.gridwidth = 2;
        gbc.gridy = 6;
        gbc.gridx = 0;
        JButton okButton = new JButton ("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        hiScoresDialogPanel.add(okButton, gbc);

        add(hiScoresDialogPanel);
        setTitle("High Scores");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(300,300);
        setResizable(false);
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }
}
