package userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class extends JDialod class and shows information about program at modal dialog window
 * Created by AT on 17.08.2015.
 */
public class MineSweeperAboutDialog extends JDialog {

    public MineSweeperAboutDialog () {

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
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weighty = 0.5;
        gbag.setConstraints(okButton, gbc);

        JLabel aboutText = new JLabel ("<html><center><font size=4 face=Arial>Just Old Good Mine Sweeper<p> <p>This " +
                "program was coded at rare times of ceasefire<p> near the frontline from Kourdymivka to Avdeevka <p> " +
                "at hot summer of 2015.</font></center></html>");
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 1;
        gbag.setConstraints(aboutText, gbc);

        add(aboutText);
        add(okButton);

        setTitle("About MineSweeper");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(400,200);
        setResizable(false);
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }
}
