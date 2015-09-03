package userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class extends JDialod class and allows to choose size of minefield.
 * Created by AT on 17.08.2015.
 */
public class MineSweeperSettingsDialog extends JDialog {

    /**
     * Method creates a dialog window, and allows to choose size of minefield using JRadioButtons.
     * @param parent - instance of MineSweeperUserInterface class that contain methods which allows to receive and save
     *               minefield size. Also it have method, used to resize minefield.
     */
    public MineSweeperSettingsDialog(MineSweeperUserInterface parent) {

        String selectedSize = parent.getFieldSize();

        GridBagLayout gbag = new GridBagLayout ();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gbag);

        Box selectionBox = Box.createHorizontalBox();
        Box buttonBox = Box.createHorizontalBox();

        boolean statusSmallButton = false;
        boolean statusMediumButton = false;
        boolean statusLargeButton = false;

        switch (selectedSize.toLowerCase()) {
            case "small":
                statusSmallButton=true;
                break;
            case "medium":
                statusMediumButton=true;
                break;
            case "large":
                statusLargeButton=true;
                break;
        }

        JRadioButton smallButton = new JRadioButton("Small",statusSmallButton);
        JRadioButton mediumButton = new JRadioButton("Medium",statusMediumButton);
        JRadioButton largeButton = new JRadioButton("Large",statusLargeButton);

        ButtonGroup selectionGroup = new ButtonGroup();

        // connecting keywords, for minefield sizes, with JRadioButtons
        smallButton.setActionCommand("small");
        mediumButton.setActionCommand("medium");
        largeButton.setActionCommand("large");

        selectionGroup.add(smallButton);
        selectionGroup.add(mediumButton);
        selectionGroup.add(largeButton);

        selectionBox.add(smallButton);
        selectionBox.add(mediumButton);
        selectionBox.add(largeButton);

        JButton okButton = new JButton("OK");
        JButton closeButton = new JButton("Close");

        buttonBox.add(okButton);
        buttonBox.add(Box.createHorizontalStrut(12));
        buttonBox.add(closeButton);

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                if (!selectionGroup.getSelection().getActionCommand().equals(selectedSize)) {
                    parent.setFieldSize(selectionGroup.getSelection().getActionCommand());
                    parent.ResetMinefieldPanel();
                }
                dispose();
            }
        });
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        JLabel selectionText = new JLabel("<html>Select size of minefield</html>");

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.weighty = 0.5;
        gbag.setConstraints(buttonBox, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weighty = 1;
        gbag.setConstraints(selectionBox, gbc);
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 0.5;
        gbag.setConstraints(selectionText, gbc);

        add(selectionText);
        add(selectionBox);
        add(buttonBox);

        setTitle("Settings");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(400,200);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        setVisible(true);
    }
}
