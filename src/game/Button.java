package game;

import pieces.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Button extends JButton implements ActionListener{
    AbstractPiece piece = null;
    int[] spot;
    JLabel label;
    
    Button(int[] spot, Color color) {
        this.label = new JLabel("", JLabel.CENTER);
        this.label.setFont(new Font("Arial Unicode MS", Font.PLAIN, 65));
        this.label.setPreferredSize(new Dimension(100,100));
        this.add(label);
        this.spot = spot;
        this.setPreferredSize(new Dimension(100, 100));
        this.setBackground(color);
        this.addActionListener(this);
    }

    Button(AbstractPiece piece, int[] spot, Color color) {
        this.label = new JLabel(piece.getSymbol(), JLabel.CENTER);
        this.label.setFont(new Font("Arial Unicode MS", Font.PLAIN, 65));
        this.setPreferredSize(new Dimension(100, 100));
        this.setBackground(color);
        this.add(label);
        this.spot = spot;
        this.piece = piece;
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this) {
            if (hasPiece() && Game.from == null && this.piece.getColor().equals(Game.Player)) {
                Game.from = this.spot;
                Game.movingPiece = this.piece;
            } else if (Game.from != null) {
                Game.to = this.spot;
                if (Game.from[0] == Game.to[0] && Game.from[1] == Game.to[1]) {
                    Game.from = null;
                    Game.to = null;
                    return;
                };
                if (!hasPiece()) {
                    if (Game.movingPiece.canMove(Game.from, Game.to)) {
                        Game.btnsPiece[Game.from[0]][Game.from[1]].removePiece();
                        placePiece(Game.movingPiece);
                        Game.Player = Game.Player.equals("white") ? "black" : "white";
                        Game.status.setText("Turn: " + Game.Player);
                    }
                } else {
                    if (Game.movingPiece.canMove(Game.from, Game.to) && !Game.movingPiece.getColor().equals(this.piece.getColor())) {
                        String logMove = "\n" + Game.movingPiece.getAbbreviation() + " takes " + this.piece.getAbbreviation() + "!";
                        if (this.piece.getName().equals("King")) {
                            Game.btnsPiece[Game.from[0]][Game.from[1]].removePiece();
                            placePiece(Game.movingPiece);
                            Game.status.setText(Game.Player + " won!");
                            for (int i = 0; i < 8; i++) {
                                for (Button btn : Game.btnsPiece[i]) {
                                    btn.setEnabled(false);
                                }
                            }
                        } else {
                            Game.btnsPiece[Game.from[0]][Game.from[1]].removePiece();
                            placePiece(Game.movingPiece);
                            Game.Player = Game.Player.equals("white") ? "black" : "white";
                            Game.status.setText("Turn: " + Game.Player);
                        }
                        Game.logText.setText(Game.logText.getText() + logMove);
                    }
                }
                Game.from = null;
                Game.to = null;
            } else {
                Game.from = null;
                Game.to = null;
            }
        }
    }
    
    public boolean hasPiece() {
        if (this.piece == null) {
            return false;
        } else {
            return true;
        }
    }

    private void removePiece() {
        this.piece = null;
        this.label.setText("");
    }

    private void placePiece(AbstractPiece piece) {
        String logMove = "\n[" + piece.getAbbreviation() + "] " + piece.getChessSpot() + " to ";
        this.piece = piece;
        this.piece.spot = this.spot;
        logMove += this.piece.getChessSpot();
        Game.logText.setText(Game.logText.getText() + logMove);
        this.label.setText(piece.getSymbol());
    }
}