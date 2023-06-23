package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ui.ChessBoardPanel;
import events.*;

public class Game {
    private State state;
    public static Map<Pos, Piece> board;
    private final List<ChessListener> listeners;
    private String logText;

    public Game() {
        state = State.WHITE_NEXT;
        listeners = new ArrayList<>();
        board = new HashMap<>();
        logText = "";
        initPieces();
    }

    public State getState() {
        return state;
    }

    public String getLogText() {
        return logText;
    }

    private void fireMovePiece(Piece piece, Pos from, Pos to) {
        for (ChessListener listener : listeners) {
            listener.pieceMoved(new ChessEvent(this, from, to, piece));
        }
    }

    public void addListener(ChessListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ChessListener listener) {
        listeners.remove(listener);
    }

    public boolean hasPiece(Pos pos) {
        return board.containsKey(pos);
    }

    public Piece getPiece(Pos pos) {
        return board.getOrDefault(pos, null);
    }

    private void removePiece(Pos pos) {
        board.remove(pos);
    }

    private void setPiece(Pos pos, Piece piece) {
        board.put(pos, piece);
    }

    public boolean isTurn(String color) {
        return color.equals("black") && state == State.BLACK_NEXT || color.equals("white") && state == State.WHITE_NEXT;
    }

    private void initPieces() {
        board.put(Pos.A_8, Piece.ROOK_BLACK);
        board.put(Pos.B_8, Piece.KNIGHT_BLACK);
        board.put(Pos.C_8, Piece.BISHOP_BLACK);
        board.put(Pos.D_8, Piece.QUEEN_BLACK);
        board.put(Pos.E_8, Piece.KING_BLACK);
        board.put(Pos.F_8, Piece.BISHOP_BLACK);
        board.put(Pos.G_8, Piece.KNIGHT_BLACK);
        board.put(Pos.H_8, Piece.ROOK_BLACK);
        for (int i = 1; i <= 8; i++) {
            board.put(Pos.get(2, i), Piece.PAWN_BLACK);
        }
        for (int i = 1; i <= 8; i++) {
            board.put(Pos.get(7, i), Piece.PAWN_WHITE);
        }
        board.put(Pos.A_1, Piece.ROOK_WHITE);
        board.put(Pos.B_1, Piece.KNIGHT_WHITE);
        board.put(Pos.C_1, Piece.BISHOP_WHITE);
        board.put(Pos.D_1, Piece.QUEEN_WHITE);
        board.put(Pos.E_1, Piece.KING_WHITE);
        board.put(Pos.F_1, Piece.BISHOP_WHITE);
        board.put(Pos.G_1, Piece.KNIGHT_WHITE);
        board.put(Pos.H_1, Piece.ROOK_WHITE);
    }

    public void placePiece(Piece piece, Pos from, Pos pos) {
        if (validateMove(piece, from, pos)) {
            logText = "\n[" + piece.getAbbreviation() + "] " + from + " to " + pos;
            nextTurn();
            if (hasPiece(pos)) {
                logText += "\n" + getPiece(from).getAbbreviation() + " takes " + getPiece(pos).getAbbreviation() + "!"; 
                if (getPiece(pos).getName().equals("King")) {
                    state = piece.getColor() == "white" ? State.WHITE_WON : State.BLACK_WON;
                    logText += "\n" + piece.getColor().toUpperCase() + " won!";
                    ChessBoardPanel.map.forEach((a,b) -> b.setEnabled(false));
                } 
            }
            removePiece(from);
            setPiece(pos, piece);
            fireMovePiece(piece, from, pos);
        }
    }

    private void nextTurn() {
        state = state == State.BLACK_NEXT ? State.WHITE_NEXT : State.BLACK_NEXT;
    }

    private boolean willJump(int[] from, int[] to) {
        int distance;
        if (from[0] == to[0]) { // same row
            distance = Math.abs(to[1]-from[1])-1;
            if (Math.abs(distance) == 0) return false;
            for (int i = 1; i <= distance; i++) {
                int n = to[1]-from[1] < 0 ? i*-1 : i;
                if (hasPiece(Pos.get(from[0]+1, from[1]+1+n))) {
                    return true;
                }
            }
        } else if (from[1] == to[1]) { // same column
            distance = Math.abs(to[0]-from[0])-1;
            if (Math.abs(distance) == 0) return false;
            for (int i = 1; i <= distance; i++) {
                int n = to[0]-from[0] < 0 ? i*-1 : i;
                if (hasPiece(Pos.get(from[0]+1+n, from[1]+1))) {
                    return true;
                }
            }
        } else if (Math.abs(to[0]-from[0]) == Math.abs(to[1]-from[1])) { // same diagonal
            distance = Math.abs(to[0]-from[0])-1;
            if (Math.abs(to[0]-from[0]) == 0) return false;
            int y = to[0]-from[0];
            int x = to[1]-from[1];
            if (y < 0) { // bottom -> top
                if (x < 0) { // right -> left
                    for (int i = 1; i <= distance; i++) {
                        if (hasPiece(Pos.get(1+from[0]-i, 1+from[1]-i))) {
                            return true;
                        }
                    }
                } else if (x > 0) { // left -> right
                    for (int i = 1; i <= distance; i++) {
                        if (hasPiece(Pos.get(1+from[0]-i, 1+from[1]+i))) {
                            return true;
                        }
                    }
                }
            } else if (y > 0) { // top -> bottom
                if (x < 0) { // right -> left
                    for (int i = 1; i <= distance; i++) {
                        if (hasPiece(Pos.get(1+from[0]+i, 1+from[1]-i))) {
                            return true;
                        }
                    }
                } else if (x > 0) { // left -> right
                    for (int i = 1; i <= distance; i++) {
                        if (hasPiece(Pos.get(1+from[0]+i, 1+from[1]+i))) {
                            return true;
                        }
                    }
                } 
            }
        }
        return false;
    }

    public boolean validateMove(Piece piece, Pos from, Pos to) {
        return isMovePossible(piece, from, to) && piece.canMove(from.toArray(), to.toArray());
    }

    private boolean isMovePossible(Piece piece, Pos from, Pos to) {
        return !willJump(from.toArray(), to.toArray()) && (!hasPiece(to) || !piece.getColor().equals(getPiece(to).getColor()));
    }
}
