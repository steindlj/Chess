package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RestartButton extends JButton implements ActionListener {
    RestartButton () {
        JLabel label = new JLabel("RESTART", JLabel.CENTER);
        this.setPreferredSize(new Dimension(90, 25));
        this.add(label);
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this) {
            Game.createBoard(Game.frame);
        }
    }
}