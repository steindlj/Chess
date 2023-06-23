package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.*;

public class ChessBoardPanel extends JPanel {
    public static final Font BUTTON_FONT = new Font("Arial Unicode MS", Font.PLAIN, 65);
    public static final Color LIGHT_COLOR = new Color(213, 168, 119);
    public static final Color DARK_COLOR = new Color(147, 73, 25);
    public static final Color MOVE_COLOR = new Color(173, 251, 133);

    private final Game game;
    public static final Map<Pos, JButton> map = new HashMap<>();
    private State state = State.NIL;
    private Piece move = null;
    private Pos from = null;

    public ChessBoardPanel(Game game) {
        this.game = game;
        setLayout(new GridLayout(8,8));
        createButtons();
    }

    private void createButtons() {
        Color bg = LIGHT_COLOR;
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                Pos pos = Pos.get(row, col);
                JLabel label = new JLabel();
                label.setFont(BUTTON_FONT);
                setPiece(label, pos);
                JButton btn = new JButton();
                btn.add(label);
                btn.setPreferredSize(new Dimension(100, 100));
                btn.setBackground(bg);
                btn.addActionListener(ae -> {
                    if (game.hasPiece(pos) && game.isTurn(game.getPiece(pos).getColor()) && state == State.NIL) {
                        from = pos;
                        showMoves(game.getPiece(pos));
                    } else if (state == State.SHOW_MOVES) {
                        removeMoves();
                        game.placePiece(move, from, pos);
                    } else {
                        removeMoves();
                    }
                });
                add(btn);
                map.put(pos, btn);
                bg = bg == LIGHT_COLOR ? DARK_COLOR : LIGHT_COLOR;
            }
            bg = bg == LIGHT_COLOR ? DARK_COLOR : LIGHT_COLOR;
        }
        game.addListener(evt -> {
            setPiece((JLabel) map.get(evt.getTo()).getComponent(0), evt.getTo());
            setPiece((JLabel) map.get(evt.getFrom()).getComponent(0), evt.getFrom());
        });
    }

    private void setPiece(JLabel label, Pos pos) {
        if (game.hasPiece(pos)) {
            label.setText(game.getPiece(pos).getSymbol());
        } else {
            label.setText("");
        }
    }

    private void showMoves(Piece piece) {
        for (Pos pos : Pos.ALL) {
            if (game.validateMove(piece, from, pos)) {
                    map.get(pos).setBackground(MOVE_COLOR);
                }
        }
        move = piece;
        state = State.SHOW_MOVES;
    }

    private void removeMoves() {
        for(int i = 1; i <= 8; i++) {
            for(int j = 1; j <= 8; j++) {
                JButton btn = map.get(Pos.get(i,j));
                if (btn.getBackground() == MOVE_COLOR) {
                    if (i % 2 == 1 && j % 2 == 0 || i % 2 == 0 && j % 2 == 1) {
                        btn.setBackground(DARK_COLOR);
                    } else {
                        btn.setBackground(LIGHT_COLOR);
                    }
                }
            }
        }
        state = State.NIL;
    }
}
