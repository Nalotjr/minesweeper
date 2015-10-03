package userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class extends JDialod and shows information about program at modal dialog window
 * Created by AT on 17.08.2015.
 */
public class MineSweeperAboutDialog extends JDialog {

    public MineSweeperAboutDialog () {

        JPanel aboutDialogPanel = new JPanel(new GridBagLayout ());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(10,5,0,5);
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel aboutText = new JLabel ("<html><center><font size=4 face=Arial>Just Old Good Mine Sweeper<p> <p>This " +
                "program was coded at rare times of ceasefire<p> near the frontline from Kourdymivka to Avdeevka <p> " +
                "at hot summer of 2015.</font></center></html>");
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 1;
        aboutDialogPanel.add(aboutText, gbc);

        gbc.insets = new Insets(0,0,10,0);
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.gridy = 1;
        JButton okButton = new JButton ("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        aboutDialogPanel.add(okButton, gbc);

        add(aboutDialogPanel);
        setTitle("About MineSweeper");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(400,200);
        setResizable(false);
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }
}
