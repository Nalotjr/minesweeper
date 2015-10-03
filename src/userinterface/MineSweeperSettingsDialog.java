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

        JPanel settingsDialogPanel = new JPanel(new GridBagLayout ());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(10,5,0,5);
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.gridy = 0;
        gbc.gridx = 0;
        JLabel selectionText = new JLabel("<html><center><font size=4 face=Arial>Select size of minefield</font>" +
                "</center></html>");
        settingsDialogPanel.add(selectionText, gbc);

        Box selectionBox = Box.createHorizontalBox();
        ButtonGroup selectionGroup = new ButtonGroup();

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

        // connecting keywords, for minefield sizes, with JRadioButtons
        smallButton.setActionCommand("small");
        mediumButton.setActionCommand("medium");
        largeButton.setActionCommand("large");

        selectionGroup.add(smallButton);
        selectionGroup.add(mediumButton);
        selectionGroup.add(largeButton);

        selectionBox.add(smallButton);
        selectionBox.add(Box.createHorizontalStrut(12));
        selectionBox.add(mediumButton);
        selectionBox.add(Box.createHorizontalStrut(12));
        selectionBox.add(largeButton);

        gbc.insets = new Insets(0,5,0,5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 1;
        settingsDialogPanel.add(selectionBox, gbc);


        Box buttonBox = Box.createHorizontalBox();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        buttonBox.add(okButton);
        buttonBox.add(Box.createHorizontalStrut(12));
        buttonBox.add(cancelButton);

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
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        gbc.insets = new Insets(0,5,10,5);
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.gridy = 2;
        settingsDialogPanel.add(buttonBox, gbc);

        add(settingsDialogPanel);

        setTitle("Settings");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(300,160);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        setVisible(true);
    }
}
