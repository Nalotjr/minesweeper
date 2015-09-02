package userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Class extends JDialod class and shows highscores table for current field size at modal dialog window
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

        GridBagLayout gbag = new GridBagLayout ();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gbag);

        JButton okButton = new JButton ("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbag.setConstraints(okButton, gbc);
        add(okButton);

        JLabel headerText = new JLabel("<html><center><font size=4 face=Arial>High Scores for "+ fieldSize.toUpperCase()
                +" minefield</font></center></html>");
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbag.setConstraints(headerText, gbc);
        add(headerText);

        ArrayList<JLabel> nameLabels = new ArrayList<JLabel>();
        ArrayList<String> hiScoreTable = (ArrayList)parent.getHiScoreTable();

        int startElement = 0;
        switch (parent.getFieldSize().toLowerCase()) {
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

        // creates JLabels for highscores data  (10 labels - 5 names and 5 scores)
        for(int i = 0; i < 10; i++) {
            nameLabels.add(new JLabel(hiScoreTable.get(i+startElement)));

            if(i % 2 == 0) {
                gbc.gridy = i / 2 + 1;
                gbc.gridx = 0;
                gbc.anchor = GridBagConstraints.WEST;
                gbag.setConstraints(nameLabels.get(i), gbc);
                add(nameLabels.get(i));
            } else {
                gbc.gridy = i / 2 + 1;
                gbc.gridx = 1;
                gbc.anchor = GridBagConstraints.EAST;
                gbag.setConstraints(nameLabels.get(i), gbc);
                add(nameLabels.get(i));
            }
        }

            setTitle("High Scores");
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            setSize(300,300);
            setResizable(false);
            setLocationRelativeTo(null);
            setModal(true);
            setVisible(true);
        }
}
