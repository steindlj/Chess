package game;

import pieces.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Button extends JButton implements ActionListener{
    public AbstractPiece piece = null;
    int[] spot;
    JLabel label;
    
    Button(int[] spot, Color color) {
        this.label = new JLabel("", JLabel.CENTER);
        this.label.setFont(Game.BUTTON_FONT);
        this.add(label);
        this.spot = spot;
        this.setPreferredSize(new Dimension(100, 100));
        this.setBackground(color);
        this.addActionListener(this);
    }

    Button(AbstractPiece piece, int[] spot, Color color) {
        this(spot, color);
        this.label.setText(piece.getSymbol());
        this.piece = piece;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this) {
            if (hasPiece() && Game.from == null && this.piece.getColor().equals(Game.Player)) {
                showMoves(this.piece, this.spot);
                Game.from = this.spot;
                Game.movingPiece = this.piece;
            } else if (Game.from != null) {
                removeMoves();
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
                        Game.turnLabel.setText("Turn: " + Game.Player);
                    }
                } else {
                    if (Game.movingPiece.canMove(Game.from, Game.to)) {
                        String logMove = "\n" + Game.movingPiece.getAbbreviation() + " takes " + this.piece.getAbbreviation() + "!";
                        if (this.piece.getName().equals("King")) {
                            Game.btnsPiece[Game.from[0]][Game.from[1]].removePiece();
                            placePiece(Game.movingPiece);
                            Game.turnLabel.setText(Game.Player + " won!");
                            for (int i = 0; i < 8; i++) {
                                for (Button btn : Game.btnsPiece[i]) {
                                    btn.setEnabled(false);
                                }
                            }
                        } else {
                            Game.btnsPiece[Game.from[0]][Game.from[1]].removePiece();
                            placePiece(Game.movingPiece);
                            Game.Player = Game.Player.equals("white") ? "black" : "white";
                            Game.turnLabel.setText("Turn: " + Game.Player);
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

    private void showMoves(AbstractPiece piece, int[] from) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (piece.canMove(from, Game.btnsPiece[i][j].spot)) {
                    Game.btnsPiece[i][j].setBackground(Game.MOVE_COLOR);
                    Game.frame.validate();
                    Game.frame.repaint();
                }
            }
        }
    }

    private void removeMoves() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (Game.btnsPiece[i][j].getBackground() == Game.MOVE_COLOR) {
                    if (i % 2 == 1 && j % 2 == 0) {
                        Game.btnsPiece[i][j].setBackground(Game.DARK_COLOR);
                    } else if (i % 2 == 0 && j % 2 == 1) {
                        Game.btnsPiece[i][j].setBackground(Game.DARK_COLOR);
                    } else {
                        Game.btnsPiece[i][j].setBackground(Game.LIGHT_COLOR);
                    }
                    Game.frame.validate();
                    Game.frame.repaint();
                }
            }
        }
    }
}
