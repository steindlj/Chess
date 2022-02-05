package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RestartButton extends JButton implements ActionListener {
    RestartButton () {
        JLabel label = new JLabel("RESTART");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setFont(Game.TEXT_FONT);
        this.setPreferredSize(new Dimension(90, 30));
        this.add(label);
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this) {
            Game.createBoardGUI(Game.frame);
        }
    }
}